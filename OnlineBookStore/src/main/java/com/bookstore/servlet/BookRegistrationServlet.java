package com.bookstore.servlet;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class BookRegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get parameters
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String priceStr = request.getParameter("price");

        // Validate and process
        if (title == null || title.trim().isEmpty() ||
            author == null || author.trim().isEmpty() ||
            priceStr == null || priceStr.trim().isEmpty()) {

            out.println("❌ All fields are required.");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);

            Book book = new Book(title, author, price);
            BookDAO bookDAO = new BookDAO();
            boolean success = bookDAO.addBook(book);

            if (success) {
                out.println("✅ Book registered successfully.");
            } else {
                out.println("❌ Failed to register book. Check database and driver setup.");
            }

        } catch (NumberFormatException e) {
            out.println("❌ Invalid price. Please enter a valid number (e.g., 12.99).");
        }
    }
}
