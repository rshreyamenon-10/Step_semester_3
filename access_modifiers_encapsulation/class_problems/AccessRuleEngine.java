import java.util.LinkedHashMap;
import java.util.Map;

public class AccessRuleEngine {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) return "DENIED";

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";
            case "protected":
            case "default":
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        Map<String, int[]> counts = new LinkedHashMap<>();
        counts.put("private", new int[]{0, 0});
        counts.put("default", new int[]{0, 0});
        counts.put("protected", new int[]{0, 0});
        counts.put("public", new int[]{0, 0});

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String modifier = attempt[0];
                    if (counts.containsKey(modifier)) {
                        String result = classifyAccess(modifier, attempt[1]);
                        if ("ALLOWED".equals(result)) {
                            counts.get(modifier)[0]++;
                        } else {
                            counts.get(modifier)[1]++;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, int[]> entry : counts.entrySet()) {
            sb.append(entry.getKey())
              .append(": ")
              .append(entry.getValue()[0])
              .append(" allowed / ")
              .append(entry.getValue()[1])
              .append(" denied");
            if (++i < counts.size()) {
                sb.append(" | ");
            }
        }

        return sb.toString();
    }
}

class LibraryMember {
    private String membershipId;
    private String branchCode;
    private double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid membershipId");
        }
        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    private LibraryMember() {
        throw new UnsupportedOperationException();
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public double getFinesOwed() {
        return finesOwed;
    }

    public String getDisplayName() {
        return displayName;
    }
}