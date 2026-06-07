package ui;

import service.LibraryService;
import model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class LibraryGUI extends JFrame {

    private LibraryService service = new LibraryService();

    private JTextArea displayArea;

    public LibraryGUI() {
        setTitle("Library Management System");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2, 10, 10));

        // ADD BOOK PANEL
        JPanel bookPanel = new JPanel(new GridLayout(4, 2));
        bookPanel.setBorder(new TitledBorder("Add Book"));

        JTextField bookId = new JTextField();
        JTextField bookTitle = new JTextField();
        JTextField bookAuthor = new JTextField();

        JButton addBookBtn = new JButton("Add Book");

        bookPanel.add(new JLabel("Book ID:"));
        bookPanel.add(bookId);
        bookPanel.add(new JLabel("Book Title:"));
        bookPanel.add(bookTitle);
        bookPanel.add(new JLabel("Book Author:"));
        bookPanel.add(bookAuthor);
        bookPanel.add(new JLabel(""));
        bookPanel.add(addBookBtn);

        // ADD MEMBER PANEL
        JPanel memberPanel = new JPanel(new GridLayout(3, 2));
        memberPanel.setBorder(new TitledBorder("Add Member"));

        JTextField memberId = new JTextField();
        JTextField memberName = new JTextField();

        JButton addMemberBtn = new JButton("Add Member");

        memberPanel.add(new JLabel("Member ID:"));
        memberPanel.add(memberId);
        memberPanel.add(new JLabel("Member Name:"));
        memberPanel.add(memberName);
        memberPanel.add(new JLabel(""));
        memberPanel.add(addMemberBtn);

        // ISSUE PANEL
        JPanel issuePanel = new JPanel(new GridLayout(3, 2));
        issuePanel.setBorder(new TitledBorder("Issue Book"));

        JTextField issueBookId = new JTextField();
        JTextField issueMemberId = new JTextField();

        JButton issueBtn = new JButton("Issue");

        issuePanel.add(new JLabel("Book ID:"));
        issuePanel.add(issueBookId);
        issuePanel.add(new JLabel("Member ID:"));
        issuePanel.add(issueMemberId);
        issuePanel.add(new JLabel(""));
        issuePanel.add(issueBtn);

        // RETURN PANEL
        JPanel returnPanel = new JPanel(new GridLayout(2, 2));
        returnPanel.setBorder(new TitledBorder("Return Book"));

        JTextField returnBookId = new JTextField();
        JButton returnBtn = new JButton("Return");

        returnPanel.add(new JLabel("Book ID:"));
        returnPanel.add(returnBookId);
        returnPanel.add(new JLabel(""));
        returnPanel.add(returnBtn);

        // ADD PANELS TO MAIN
        mainPanel.add(bookPanel);
        mainPanel.add(memberPanel);
        mainPanel.add(issuePanel);
        mainPanel.add(returnPanel);

        add(mainPanel, BorderLayout.NORTH);

        // DISPLAY AREA
        displayArea = new JTextArea();
        displayArea.setBorder(new TitledBorder("Library Records"));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JButton showBtn = new JButton("Refresh Records");
        add(showBtn, BorderLayout.SOUTH);

        // ================= EVENTS =================

        addBookBtn.addActionListener(e -> {
            try {
                service.addBook(
                        Integer.parseInt(bookId.getText()),
                        bookTitle.getText(),
                        bookAuthor.getText()
                );
                JOptionPane.showMessageDialog(this, "Book Added");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        addMemberBtn.addActionListener(e -> {
            try {
                service.addMember(
                        Integer.parseInt(memberId.getText()),
                        memberName.getText()
                );
                JOptionPane.showMessageDialog(this, "Member Added");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        issueBtn.addActionListener(e -> {
            try {
                service.issueBook(
                        Integer.parseInt(issueBookId.getText()),
                        Integer.parseInt(issueMemberId.getText())
                );
                JOptionPane.showMessageDialog(this, "Book Issued");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        returnBtn.addActionListener(e -> {
            try {
                service.returnBook(
                        Integer.parseInt(returnBookId.getText())
                );
                JOptionPane.showMessageDialog(this, "Book Returned");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        showBtn.addActionListener(e -> displayAll());
    }

    private void displayAll() {
        displayArea.setText("");

        displayArea.append("=== BOOKS ===\n");
        for (Book b : service.getBooks()) {
            displayArea.append(b + "\n");
        }

        displayArea.append("\n=== MEMBERS ===\n");
        for (Member m : service.getMembers()) {
            displayArea.append(m + "\n");
        }

        displayArea.append("\n=== ISSUED RECORDS ===\n");
        for (IssueRecord r : service.getRecords()) {
            displayArea.append(r + "\n");
        }
    }
}