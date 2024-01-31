# bookstore
A simple crud book store project using spring boot starter data jpa, spring boot starter security,thymeleaf,security,h2 database.

A user can register and login as default user.
There are 2 role - ADMIN and USER which is already defined as init data.So can login as username(admin) and password(admin) to get the admin authority.

As ADMIN,You can add new book and book image, crud operation can do.Because of my requiremnets, when you add an image for the new book add,you have to firstly choose the image which i said in that bookadd form.

And the added book name must be the number.png which will be the next book in the initial data i.e if the book number in data.sql is 13th book, the added book'name must be 14.png now .You have to rename th book's name as current book id + 1 as in data.sql. 

Docker is used here.So if you installed docker in your machine 
You can run from terminal as 
docker run -d -p 8080:8080 waves/bookstore:0.0.1-SNAPSHOT 

