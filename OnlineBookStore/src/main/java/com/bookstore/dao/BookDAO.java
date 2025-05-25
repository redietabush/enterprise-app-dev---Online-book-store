package com.bookstore.dao;

import com.bookstore.model.Book;
import com.bookstore.util.DBUtil;

import java.sql.*;

public class BookDAO {
	public boolean addBook(Book book) {
	    Connection conn = null;
	    PreparedStatement stmt = null;

	    try {
	        conn = DBUtil.openConnection();
	        String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, book.getTitle());
	        stmt.setString(2, book.getAuthor());
	        stmt.setDouble(3, book.getPrice());

	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace(); // 🔴 This will show the actual problem in the console
	        return false;
	    } finally {
	        DBUtil.close(conn, stmt, null);
	    }
	}

}
