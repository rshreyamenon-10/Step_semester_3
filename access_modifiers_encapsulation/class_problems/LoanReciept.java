import java.util.Arrays;

public final class LoanReciept {

    private final String memberId;
    private final String[] bookIds;

    static {
        // Shared one-time static setup
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        if (bookIds == null) {
            throw new IllegalArgumentException("Book IDs cannot be null");
        }

        for (String id : bookIds) {
            if (id == null || !id.matches("^BK-\\d{3}$")) {
                throw new IllegalArgumentException("Invalid book ID format: " + id);
            }
        }

        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        String[] updatedIds = Arrays.copyOf(bookIds, bookIds.length);
        updatedIds[index] = newId;
        return new LoanReceipt(this.memberId, updatedIds);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "Processed: 0 | Reference Only: 0";
        }

        int totalCount = 0;
        int referenceCount = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                continue;
            }

            totalCount++;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceCount++;
            }
        }

        return "Processed: " + totalCount + " | Reference Only: " + referenceCount;
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}