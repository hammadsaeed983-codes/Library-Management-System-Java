package service;

import model.*;
import java.util.ArrayList;

public class LibraryService {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<IssueRecord> records = new ArrayList<>();

    // BOOK METHODS
    public void addBook(int id, String title, String author) throws Exception {
        if (title.isEmpty() || author.isEmpty()) {
            throw new Exception("Invalid book data");
        }

        for (Book b : books) {
            if (b.getId() == id) {
                throw new Exception("Book ID exists");
            }
        }

        books.add(new Book(id, title, author));
    }

    // MEMBER METHODS
    public void addMember(int id, String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("Name cannot be empty");
        }

        for (Member m : members) {
            if (m.getId() == id) {
                throw new Exception("Member ID exists");
            }
        }

        members.add(new Member(id, name));
    }

    // ISSUE BOOK
    public void issueBook(int bookId, int memberId) throws Exception {
        Book book = null;
        Member member = null;

        for (Book b : books) {
            if (b.getId() == bookId) book = b;
        }

        for (Member m : members) {
            if (m.getId() == memberId) member = m;
        }

        if (book == null) throw new Exception("Book not found");
        if (member == null) throw new Exception("Member not found");

        for (IssueRecord r : records) {
            if (r.getBook().getId() == bookId) {
                throw new Exception("Book already issued");
            }
        }

        records.add(new IssueRecord(book, member));
    }

    // RETURN BOOK
    public void returnBook(int bookId) throws Exception {
        IssueRecord found = null;

        for (IssueRecord r : records) {
            if (r.getBook().getId() == bookId) {
                found = r;
                break;
            }
        }

        if (found == null) {
            throw new Exception("Book not issued");
        }

        records.remove(found);
    }

    // GET DATA
    public ArrayList<Book> getBooks() { return books; }
    public ArrayList<Member> getMembers() { return members; }
    public ArrayList<IssueRecord> getRecords() { return records; }
}