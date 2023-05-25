package lab8;
import java.util.Stack;

public class BSTree<E extends Comparable<E>> {
    class Node {
        protected E data;
        protected Node left, right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public BSTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E x) throws ItemDuplicated {
        root = insertNode(root, x);
    }

    private Node insertNode(Node node, E x) throws ItemDuplicated {
        if (node == null) {
            return new Node(x);
        }

        int compareResult = x.compareTo(node.data);

        if (compareResult < 0) {
            node.left = insertNode(node.left, x);
        } else if (compareResult > 0) {
            node.right = insertNode(node.right, x);
        } else {
            throw new ItemDuplicated("El artículo ya existe en el BST.");
        }

        return node;
    }

    public E search(E x) throws ItemNotFound {
        Node foundNode = searchNode(root, x);
        if (foundNode == null) {
            throw new ItemNotFound("Artículo no encontrado en el BST.");
        }
        return foundNode.data;
    }

    private Node searchNode(Node node, E x) {
        if (node == null || x.equals(node.data)) {
            return node;
        }

        int compareResult = x.compareTo(node.data);

        if (compareResult < 0) {
            return searchNode(node.left, x);
        } else {
            return searchNode(node.right, x);
        }
    }

    public void remove(E x) throws ItemNotFound {
        root = removeNode(root, x);
    }

    private Node removeNode(Node node, E x) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Artículo no encontrado en el BST");
        }

        int compareResult = x.compareTo(node.data);

        if (compareResult < 0) {
            node.left = removeNode(node.left, x);
        } else if (compareResult > 0) {
            node.right = removeNode(node.right, x);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                Node successor = findMin(node.right);
                node.data = successor.data;
                node.right = removeNode(node.right, successor.data);
            }
        }

        return node;
    }

    private Node findMin(Node node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        return sb.toString();
    }

    private void toString(Node node, StringBuilder sb) {
        if (node != null) {
            toString(node.left, sb);
            sb.append(node.data.toString()).append(" ");
            toString(node.right, sb);
        }
    }

    public void inOrden() {
        inOrden(root);
    }

    protected void inOrden(Node actual) {
        if (actual != null) {
            inOrden(actual.left);
            System.out.println(actual.data);
            inOrden(actual.right);
        }
    }
    public int countNonLeafNodes() {
        return countNonLeafNodes(root);
    }

    private int countNonLeafNodes(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }

        return 1 + countNonLeafNodes(node.left) + countNonLeafNodes(node.right);
    }
    public int getNodeHeight(E x) {
        Node node = searchNode(root, x);
        if (node != null) {
            return getNodeHeight(node);
        }
        return -1;
    }

    private int getNodeHeight(Node node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = getNodeHeight(node.left);
        int rightHeight = getNodeHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }
    public void iterativePreOrder() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.data);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
    public int calculateArea() {
        int leafCount = countLeafNodes(root);
        int height = getHeight(root);
        return leafCount * height;
    }

    private int countLeafNodes(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeafNodes(node.left) + countLeafNodes(node.right);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    public Node getMinNode() {
        if (root == null) {
            return null;
        }

        Node current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }
    public Node getMaxNode() {
        if (root == null) {
            return null;
        }

        Node current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    public void parenthesize() {
        parenthesize(root, 0);
    }

    private void parenthesize(Node node, int depth) {
        if (node == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(" ");
        }
        sb.append(node.data);
        System.out.println(sb.toString());

        parenthesize(node.left, depth + 1);
        parenthesize(node.right, depth + 1);
    }

}

