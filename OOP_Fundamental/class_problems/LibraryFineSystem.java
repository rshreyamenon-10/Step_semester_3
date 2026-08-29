class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    public double fineAmount() {
        return daysOverdue > 0 ? daysOverdue * 5.0 : 0.0;
    }

    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    public static double totalFineCollected(BookIssue[] issues) {
        if (issues == null) return 0.0;
        double total = 0;
        for (BookIssue issue : issues) {
            if (issue != null) {
                total += issue.fineAmount();
            }
        }
        return total;
    }
}

public class LibraryFineSystem {
    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "User1", 18),
            new BookIssue("Effective Java", "User2", 5),
            new BookIssue("Refactoring", "User3", 0),
            new BookIssue("DSA Handbook", "User4", 21),
            new BookIssue("Design Patterns", "User5", 9)
        };

        for (BookIssue book : issues) {
            String status = book.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(book.title + " - " + book.daysOverdue + " days - " + status);
        }

        double totalFine = BookIssue.totalFineCollected(issues);
        System.out.println("Total fine collected: Rs " + totalFine);
    }
}