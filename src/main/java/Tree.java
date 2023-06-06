public class Tree<V extends Comparable<V>> {
    private Node root;

    private class Node {
        private V value;
        private Node left;
        private Node right;
    }

    public boolean contains(V value) {
        return contains(root, value);
    }

    private boolean contains(Node node, V value) {
        if (node == null) {
            return false;
        }
        int cmp = value.compareTo(node.value);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return contains(node.left, value);
        } else {
            return contains(node.right, value);
        }
    }

    public class RedBlackTree<V extends Comparable<V>> {
        private Node root;
        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private class Node {
            private V value;
            private Node left;
            private Node right;
            private boolean color;

            Node(V value) {
                this.value = value;
                this.color = RED; // Новые узлы всегда красные
            }
        }

        public void insert(V value) {
            root = insert(root, value);
            root.color = BLACK; // Корень всегда черный
        }

        private Node insert(Node node, V value) {
            if (node == null) {
                return new Node(value);
            }

            int cmp = value.compareTo(node.value);
            if (cmp < 0) {
                node.left = insert(node.left, value);
            } else if (cmp > 0) {
                node.right = insert(node.right, value);
            }

            if (isRed(node.right) && !isRed(node.left)) {
                node = rotateLeft(node);
            }
            if (isRed(node.left) && isRed(node.left.left)) {
                node = rotateRight(node);
            }
            if (isRed(node.left) && isRed(node.right)) {
                flipColors(node);
            }

            return node;
        }

        private boolean isRed(Node node) {
            if (node == null) {
                return false;
            }
            return node.color == RED;
        }

        private Node rotateLeft(Node node) {
            Node x = node.right;
            node.right = x.left;
            x.left = node;
            x.color = node.color;
            node.color = RED;
            return x;
        }

        private Node rotateRight(Node node) {
            Node x = node.left;
            node.left = x.right;
            x.right = node;
            x.color = node.color;
            node.color = RED;
            return x;
        }

        private void flipColors(Node node) {
            node.color = RED;
            node.left.color = BLACK;
            node.right.color = BLACK;
        }
    }
}


