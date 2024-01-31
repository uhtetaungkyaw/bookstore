package com.jdc.bookstore.controller;

import com.jdc.bookstore.entities.Book;
import com.jdc.bookstore.entities.Item;
import com.jdc.bookstore.entities.OrderDetail;
import com.jdc.bookstore.entities.Payment;
import com.jdc.bookstore.repository.PaymentRepository;
import com.jdc.bookstore.service.BookService;
import com.jdc.bookstore.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CartController{
	private final PaymentRepository paymentRepository;
	private final BookService bookService;
	private final OrderService orderService;

	public CartController(PaymentRepository paymentRepository, BookService bookService, OrderService orderService) {
		this.paymentRepository = paymentRepository;
		this.bookService = bookService;
		this.orderService = orderService;
	}

	@GetMapping("/cart")
	public String index(HttpSession session,Model model){
		model.addAttribute("totalCost",getTotalCost(session));
		return "cartView";
	}

	@PostMapping("/cart-add/{id}")
	public String addToCart(@PathVariable int id, HttpSession session) {
		if (session.getAttribute("cart")==null){
			List<Item> cart = new ArrayList<>();
			cart.add(new Item(bookService.findById(id),1));
			session.setAttribute("cart",cart);

		}else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = isExists(id, cart);
			if (index == -1){
				cart.add(new Item(bookService.findById(id),1));
			}else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart",cart);
		}
		return "redirect:/cart";
	}

	@GetMapping("/cartRemove/{id}")
	public String cartRemove(@PathVariable int id,HttpSession session){
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int index = isExists(id, cart);
		cart.remove(index);
		session.setAttribute("cart",cart);
		return "redirect:/cart";
	}

	private double getTotalCost(HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		double totalCost = 0;
		if (cart != null){
			for (Item item : cart) {
				totalCost += item.getQuantity() * item.getBook().getOurPrice();

			}
		}
		return totalCost;
	}
	private int isExists(int id, List<Item> cart){
		for (int i = 0; i < cart.size(); i++){
			if (cart.get(i).getBook().getId() == id){
				return i;
			}
		}
		return -1;
	}
	
	@PostMapping("/checkout")
	public String checkOut() {
		return "redirect:/payment";
	}
	@GetMapping("/payment")
	public String showPayment(@ModelAttribute("payment") Payment payment){
		return "payment";
	}
	@PostMapping("/pay")
	public String pay(Payment payment, BindingResult result, HttpSession session) {
		if (result.hasErrors()){
			return "payment";
		}
		//add order
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		for (Item item: cart){
			OrderDetail order = new OrderDetail();
			order.setBook(item.getBook());
			order.setName("New Order");
			order.setDateCreate(new Date());
			order.setPrice(item.getBook().getOurPrice());
			order.setQuantity(item.getQuantity());
			orderService.create(order);
		}

		paymentRepository.save(payment);
		//delete cart
		session.removeAttribute("cart");
		return "redirect:/finalize";
	}
	@GetMapping("/finalize")
	public String showFinalize(@ModelAttribute("payment")Payment payment,
							   @ModelAttribute("book") Book book
							  ){
			return "finalize";
	}


}
