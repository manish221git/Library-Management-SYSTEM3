package ui;

import dao.BookDAO;
import model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class LibraryUI extends JFrame {
    private JTextField titleField, authorField, quantityField;
    private JTextArea displayArea;

    public LibraryUI() {
        setTitle("Library Management System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        inputPanel.add(authorField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        JButton addButton = new JButton("Add Book");
        JButton showButton = new JButton("Show Books");

        inputPanel.add(addButton);
        inputPanel.add(showButton);

        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        addButton.addActionListener(e -> addBook());
        showButton.addActionListener(e -> showBooks());

        setVisible(true);
    }

    private void addBook() {
        try {
            String title = titleField.getText();
            String author = authorField.getText();
            int qty = Integer.parseInt(quantityField.getText());
            BookDAO dao = new BookDAO();
            dao.addBook(new Book(0, title, author, qty));
            JOptionPane.showMessageDialog(this, "Book Added Successfully!");
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void showBooks() {
        try {
            BookDAO dao = new BookDAO();
            List<Book> books = dao.getAllBooks();
            displayArea.setText("");
            for (Book b : books) {
                displayArea.append("ID: " + b.getId() + ", Title: " + b.getTitle() +
                        ", Author: " + b.getAuthor() + ", Qty: " + b.getQuantity() + "\n");
            }
        } catch (Exception e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        quantityField.setText("");
    }
}