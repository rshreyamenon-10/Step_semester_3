class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    public BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }

    public void printName() {
        System.out.println(name);
    }
}

class FixedLibraryMember {
    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "Central Library";
    static int memberCount = 1000;

    public FixedLibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + memberCount;
    }

    public void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
}

public class LibrarySystemDemo {
    public static void main(String[] args) {
        BrokenLibraryMember m1 = new BrokenLibraryMember("Aditi", "LM-1001", 2);
        BrokenLibraryMember m2 = new BrokenLibraryMember("Rohan", "LM-1002", 5);

        m1.printName();
        m2.printName();
        System.out.println("(Aditi's data was overwritten \u2014 both members now show \"Rohan\")\n");

        FixedLibraryMember member1 = new FixedLibraryMember("Aditi", 2);
        FixedLibraryMember member2 = new FixedLibraryMember("Rohan", 5);

        member1.printMemberCard();
        member2.printMemberCard();
        FixedLibraryMember.printTotalMembers();
    }
}