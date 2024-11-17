// implements Red left-leaning Red Black tree structure

public class BalancedSearchTree<Key extends Comparable<Key>,Value> {
    private static final boolean Red = true;
    private static final boolean Black = false;

    private Node root;
    private int n;


    // bst helper class node data type
    private class Node{
        private Key key;    // key
        private Value val;  // associated data
        private Node left, right; // left and right subtree nodes
        private boolean color;  // red vs black in RBBST implementation

        public Node(Key key, Value val, boolean color){
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    private boolean isRed(Node x){
        if(x == null){
            return false;
        }
        return x.color == Red;
    }

    // implementing standard bst search

    public Value get(Key key){
        return get(root, key);
    }

    public Value get(Node x, Key key){
        while(x != null){
            int cmp = key.compareTo(x.key);
            if(cmp < 0){
                x = x.left;
            }
            if(cmp > 0){
                x = x.right;
            }
            else return x.val;
        }
        return null;
    }

    // implementing Red-Black bst insert

    public void put(Key key, Value val){
        root = insert(root, key, val);
        root.color = Black;
        assert check();
    }

    private Node insert(Node h, Key key, Value val){
        if(h == null){
            n++;
            return new Node(key, val, Red);
        }

        int cmp = key.compareTo(h.key);
        if(cmp < 0){
            h.left = insert(h.left, key, val);
        }
        if(cmp > 0){
            h.right = insert(h.right, key, val);
        }
        else h.val = val;

        // fix right-leaning links
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);

        return h;
    }

    // helper functions

    // rotate right
    private Node rotateRight(Node h) {
        assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = Red;
        return x;
    }

    // rotate left
    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = Red;
        return x;
    }

    // precondition: two children are red, node is black
    // postcondition: two children are black, node is red
    private void flipColors(Node h) {
        assert !isRed(h) && isRed(h.left) && isRed(h.right);
        h.color = Red;
        h.left.color = Black;
        h.right.color = Black;
    }

    // check if the tree is proper.
    private boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        if (!is23())             StdOut.println("Not a 2-3 tree");
        if (!isBalanced())       StdOut.println("Not balanced");
        return isBST() && is23() && isBalanced();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() { return is23(root); }
    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    // iterating using inorder traversal
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue) {
        if (x == null) return;
        keys(x.left, queue);
        queue.enqueue(x.key);
        keys(x.right, queue);
    }

}
