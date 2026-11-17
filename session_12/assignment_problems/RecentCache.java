import java.util.HashMap;
import java.util.Map;

public class RecentCache {

    static class Node {
        int key;
        String page;
        Node prev;
        Node next;

        Node(int key, String page) {
            this.key = key;
            this.page = page;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;

    public RecentCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        
        // Dummy head and tail to avoid null checks on boundaries
        head = new Node(0, "");
        tail = new Node(0, "");
        head.next = tail;
        tail.prev = head;
    }

    public String get(int key) {
        if (!map.containsKey(key)) {
            System.out.println("get(" + key + ") -> -1");
            return "-1";
        }
        Node node = map.get(key);
        remove(node);
        addToFront(node);
        System.out.println("get(" + key + ") -> " + node.page);
        return node.page;
    }

    public void put(int key, String page) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.page = page;
            remove(node);
            addToFront(node);
            System.out.println("put(" + key + "," + page + ") updated");
        } else {
            if (map.size() >= capacity) {
                // Evict least recently used (node just before tail)
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
                System.out.println("put(" + key + "," + page + ") evicted " + lru.key);
            }
            Node newNode = new Node(key, page);
            map.put(key, newNode);
            addToFront(newNode);
            System.out.println("put(" + key + "," + page + ")");
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToFront(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public void printCacheState() {
        StringBuilder sb = new StringBuilder("Cache (most recent first): ");
        Node curr = head.next;
        boolean first = true;
        while (curr != tail) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(curr.key).append("=").append(curr.page);
            first = false;
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        RecentCache cache = new RecentCache(2);

        cache.put(1, "Home");
        cache.put(2, "Notes");
        cache.get(1);
        cache.put(3, "Quiz");
        cache.get(2);
        cache.put(1, "Home2");
        cache.put(4, "Mail");
        cache.get(3);
        cache.get(1);
        cache.printCacheState();
    }
}
