// Custom exception for empty stack
class EmptyStackException extends Exception {
    public EmptyStackException(String message) {
        super(message);
    }
}

// Custom exception for invalid input
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class GenericStack<T> {
    private Node<T> top;
    private int size;

    public GenericStack() {
        top = null;
        size = 0;
    }

    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty. Cannot pop from an empty stack.");
        }
        T item = top.data;
        top = top.next;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        try {
            // Example usage with different data types

            GenericStack<Integer> intStack = new GenericStack<>();
            intStack.push(1);
            intStack.push(2);
            intStack.push(3);

            System.out.println("Integer Stack:");
            while (!intStack.isEmpty()) {
                System.out.println(intStack.pop());
            }

            GenericStack<String> stringStack = new GenericStack<>();
            stringStack.push("Hello");
            stringStack.push("World");

            System.out.println("\nString Stack:");
            while (!stringStack.isEmpty()) {
                System.out.println(stringStack.pop());
            }

            // Example of handling invalid character input
            char invalidChar = '$';
            if (!Character.isLetterOrDigit(invalidChar)) {
                throw new InvalidInputException("Invalid character input: " + invalidChar);
            }
        } catch (EmptyStackException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
