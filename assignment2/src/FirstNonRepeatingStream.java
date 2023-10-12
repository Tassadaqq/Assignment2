import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingStream {
    private Node head;
    private Node tail;
    private Map<Character, Node> charToNodeMap;
    private Map<Character, Integer> charCount;

    public FirstNonRepeatingStream() {
        head = new Node('-');
        tail = new Node('-');
        head.next = tail;
        tail.prev = head;
        charToNodeMap = new HashMap<>();
        charCount = new HashMap<>();
    }

    public void add(char c) {
        charCount.put(c, charCount.getOrDefault(c, 0) + 1);

        if (!charToNodeMap.containsKey(c)) {
            Node newNode = new Node(c);
            newNode.prev = tail.prev;
            newNode.next = tail;
            tail.prev.next = newNode;
            tail.prev = newNode;
            charToNodeMap.put(c, newNode);
        } else {
            Node node = charToNodeMap.get(c);
            if (node != null) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                charToNodeMap.put(c, null);
            }
        }
    }

    public char getFirstNonRepeating() {
        Node firstNode = head.next;
        if (firstNode != null && firstNode != tail && charCount.get(firstNode.data) == 1) {
            return firstNode.data;
        }
        return '-';
    }

    private static class Node {
        char data;
        Node prev;
        Node next;

        public Node(char data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        FirstNonRepeatingStream stream = new FirstNonRepeatingStream();

        stream.add('a');
        stream.add('b');
        System.out.println("Stream: ['a','b'] First non-repeating character: " + stream.getFirstNonRepeating()); // Output: 'b'

        stream.add('a');
        System.out.println("Stream: ['a','b','a'] First non-repeating character: " + stream.getFirstNonRepeating()); // Output: '-'

        stream.add('a');
        stream.add('b');
        System.out.println("Stream: ['a','b','a','a','b'] First non-repeating character: " + stream.getFirstNonRepeating()); // Output: 'a'
    }
}
