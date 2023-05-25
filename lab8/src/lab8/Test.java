package lab8;

public class Test {
    public static void main(String[] args) throws ItemDuplicated {
        BSTree<String> tree = new BSTree<>();
        tree.insert("Sales");
        tree.insert("Domestic");
        tree.insert("International");
        tree.insert("Canada");
        tree.insert("S. America");
        tree.insert("Overseas");
        tree.insert("Africa");
        tree.insert("Europe");
        tree.insert("Asia");
        tree.insert("Australia");
        tree.insert("Giancarlo Quispe Taco :V");
        tree.parenthesize();
    }
}

