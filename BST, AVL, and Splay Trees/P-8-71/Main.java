import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        BST<Integer> tree = new BST<Integer>();

        while (running) {
            System.out.println("Enter 1 to display the family tree in order traversal,  2 to add a person, or 3 to exit");
            int option = scan.nextInt();
            if (option == 1) {
                System.out.println();
                tree.iOT();
            }
            else if (option == 2) {
                System.out.println("What age do you want to add?");
                tree.insert(scan.nextInt());
            }
            else if (option == 3) {
                running = false;
            }
            else {
                System.out.println("Try again");
            }
        }

        scan.close();
    }
}

interface TreeNode<E> {
    E getData();
    void setData(E data);

    void setLeft(TreeNode<E> left);
    void setRight(TreeNode<E> right);

    TreeNode<E> getLeft();
    TreeNode<E> getRight();
}

class SimpleTreeNode<E extends Comparable<E>> implements TreeNode<E> {
    
    private E data;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public SimpleTreeNode(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public E getData() {
        return this.data;
    }

    @Override
    public void setData(E data) {
        this.data = data;
    }

    @Override
    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    @Override
    public void setRight(TreeNode<E> right) {
       this.right = right;
    }

    @Override
    public TreeNode<E> getLeft() {
        return this.left;
    }

    @Override
    public TreeNode<E> getRight() {
        return this.right;
    }
}


class BST<E extends Comparable<E>> {
    private TreeNode<E> root;

    public BST() {
        this.root = null;
    }

    public void insert(E data) {
        if (this.root == null) {
            this.root = new SimpleTreeNode<E>(data);
        }
        else {
            insertRecursively(this.root, data);
        }
    }

    private TreeNode<E> insertRecursively(TreeNode<E> current, E data) {
        if (current == null) { // Base case
            return new SimpleTreeNode<E>(data);
        }

        if (data.compareTo(current.getData()) < 0) {
            current.setLeft(insertRecursively(current.getLeft(), data));
        }
        else if (data.compareTo(current.getData()) > 0) {
            current.setRight(insertRecursively(current.getRight(), data));
        }

        return current;
    }

    public void iOT() {
        iOTRec(this.root);
    }

    private void iOTRec(TreeNode<E> current) {
        if (current != null) {
            iOTRec(current.getLeft());
            System.out.println(current.getData() + " ");
            iOTRec(current.getRight());
        }
    }
}