public class LibraryPlatform {

    static abstract class Resource {
        String id;

        Resource(String id) {
            this.id = id;
        }

        void reserve(String member) {
            System.out.println(id + " rejected: reserve unsupported");
        }

        void download() {
            System.out.println(id + " rejected: download unsupported");
        }
    }

    static class Book extends Resource {
        Book(String id) {
            super(id);
        }

        @Override
        void reserve(String member) {
            System.out.println(id + " reserved for " + member);
        }
    }

    static class EBook extends Resource {
        EBook(String id) {
            super(id);
        }

        @Override
        void reserve(String member) {
            System.out.println(id + " reserved for " + member);
        }

        @Override
        void download() {
            System.out.println(id + " downloaded");
        }
    }

    public static void main(String[] args) {
        Resource[] resources = new Resource[10];
        int count = 0;

        String[][] commands = {
            {"ADD", "Book", "B1"},
            {"ADD", "Book", "B1"},
            {"ADD", "EBook", "E1"},
            {"RESERVE", "B1", "M1"},
            {"DOWNLOAD", "B1"},
            {"DOWNLOAD", "E1"},
            {"FIND", "E1"}
        };

        for (String[] cmd : commands) {
            String op = cmd[0];
            if (op.equals("ADD")) {
                String type = cmd[1];
                String id = cmd[2];
                
                int index = -1;
                for (int i = 0; i < count; i++) {
                    if (resources[i].id.equals(id)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1) {
                    System.out.println("duplicate rejected");
                } else {
                    Resource res = type.equals("Book") ? new Book(id) : new EBook(id);
                    int insertPos = count;
                    for (int i = 0; i < count; i++) {
                        if (resources[i].id.compareTo(id) > 0) {
                            insertPos = i;
                            break;
                        }
                    }
                    for (int i = count; i > insertPos; i--) {
                        resources[i] = resources[i - 1];
                    }
                    resources[insertPos] = res;
                    count++;
                    System.out.println(id + " added");
                }
            } else if (op.equals("RESERVE")) {
                String id = cmd[1];
                String member = cmd[2];
                Resource res = null;
                for (int i = 0; i < count; i++) {
                    if (resources[i].id.equals(id)) {
                        res = resources[i];
                        break;
                    }
                }
                if (res != null) {
                    res.reserve(member);
                }
            } else if (op.equals("DOWNLOAD")) {
                String id = cmd[1];
                Resource res = null;
                for (int i = 0; i < count; i++) {
                    if (resources[i].id.equals(id)) {
                        res = resources[i];
                        break;
                    }
                }
                if (res != null) {
                    res.download();
                }
            } else if (op.equals("FIND")) {
                String id = cmd[1];
                int index = -1;
                for (int i = 0; i < count; i++) {
                    if (resources[i].id.equals(id)) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    System.out.println(id + " found at index " + index);
                } else {
                    System.out.println(id + " not found");
                }
            }
        }
    }
}
