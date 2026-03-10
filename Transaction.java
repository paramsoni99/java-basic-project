public class Transaction {

    private int bookId;
    private String userName;
    private String borrowDate;
    private String dueDate;

    public Transaction(int bookId, String userName, String borrowDate, String dueDate) {
        this.bookId = bookId;
        this.userName = userName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public String toCSV() {
        return bookId + "," + userName + "," + borrowDate + "," + dueDate;
    }

}