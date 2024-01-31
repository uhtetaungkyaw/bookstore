package com.jdc.bookstore.controller;

import com.jdc.bookstore.entities.Book;
import com.jdc.bookstore.entities.OrderDetail;
import com.jdc.bookstore.repository.OrderRepository;
import com.jdc.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class BookController {
     private final BookService bookService;
     @Autowired
     private OrderRepository orderRepository;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/bookList")
    public String bookshelf(Model model) {

        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);
        return "bookList";
    }

    @RequestMapping("/bookDetail")
    public String bookDetail(
            @PathParam("id") int id, Model model) {

        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "bookDetail";
    }

    @GetMapping("/bookEdit")
    public String showBookEdit(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "bookEdit";
    }

    @GetMapping("/bookEdit/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "bookUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable int id, @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            book.setId(id);
            return "bookUpdate";
        }
        bookService.save(book);
        return "redirect:/bookEdit";
    }

    @GetMapping("/bookDelete/{id}")
    public String deleteBook(@PathVariable int id) {
        Book book = bookService.findById(id);
        bookService.delete(book);
        return "redirect:/bookEdit";
    }

    @GetMapping("/bookAdd")
    public String showBookAddForm(){
        return "bookAdd";
    }

    @PostMapping("/book-add")
    public String showBookAddPost(@ModelAttribute @Valid Book book, BindingResult result){
        if (result.hasErrors()){
            return "bookAdd";
        }
        bookService.save(book);
        return "redirect:/bookList";
    }
    @GetMapping("/orderList")
    public String showOrderBookList(Model model){
        List<OrderDetail> orderList = orderRepository.findAll();
        model.addAttribute("orderList", orderList);
        return "orderList";
    }

    @ModelAttribute("book")
    public Book book(){ return new Book();}
}
