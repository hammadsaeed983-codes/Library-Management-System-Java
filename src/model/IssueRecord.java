package model;

public class IssueRecord {
    private Book book;
    private Member member;

    public IssueRecord(Book book, Member member) {
        this.book = book;
        this.member = member;
    }

    public Book getBook() { return book; }

    @Override
    public String toString() {
        return book.toString() + " issued to " + member.toString();
    }
}