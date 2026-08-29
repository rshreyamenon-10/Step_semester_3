class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public void allot(String name) {
        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to room " + roomNo);
        }
    }

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) return null;
        for (HostelRoom room : rooms) {
            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }
        return null;
    }

    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);
        if (room != null) {
            room.allot(studentName);
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }
}

public class HostelAllocation {
    public static void main(String[] args) {
        HostelRoom[] rooms1 = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };
        HostelRoom.safeAllot(rooms1, "Divya");

        HostelRoom[] rooms2 = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };
        HostelRoom.safeAllot(rooms2, "Divya");
    }
}