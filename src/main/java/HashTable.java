import java.util.Objects;

public class HashTable<K, V> {
    private class Entity {
        private K key;
        private V value;

        public Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int INIT_BASKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Basket[] baskets;
    private int size = 0;

    public HashTable() {
        this(INIT_BASKET_COUNT);
    }

    public HashTable(int initSize) {
        baskets = new Basket[initSize];
    }

    private int calculateBasketIndex(K key) {
        return Math.abs(Objects.hashCode(key)) % baskets.length;
    }

    public V get(K key) {
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index];
        if (basket != null) {
            return basket.get(key);
        }
        return null;
    }

    public void put(K key, V value) {
        if (baskets.length * LOAD_FACTOR < size) {
            recalculate();
        }
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index];
        if (basket == null) {
            basket = new Basket();
            baskets[index] = basket;
        }
        Entity entity = new Entity(key, value);
        basket.add(entity);
        size++;
    }

    public boolean remove(K key) {
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index];
        if (basket != null && basket.remove(key)) {
            size--;
            return true;
        }
        return false;
    }

    private void recalculate() {
        Basket[] old = baskets;
        baskets = new Basket[old.length * 2];
        size = 0;
        for (Basket basket : old) {
            if (basket != null) {
                for (Entity entity : basket) {
                    put(entity.key, entity.value);
                }
            }
        }
    }

    private class Basket implements Iterable<Entity> {
        private Node head;

        public V get(K key) {
            Node node = head;
            while (node != null) {
                if (Objects.equals(node.value.key, key)) {
                    return node.value.value;
                }
                node = node.next;
            }
            return null;
        }

        public boolean remove(K key) {
            if (head == null) {
                return false;
            }
            if (Objects.equals(head.value.key, key)) {
                head = head.next;
                return true;
            }
            Node current = head;
            Node prev = null;
            while (current != null) {
                if (Objects.equals(current.value.key, key)) {
                    prev.next = current.next;
                    return true;
                }
                prev = current;
                current = current.next;
            }
            return false;
        }

        public void add(Entity entity) {
            Node node = new Node(entity);
            if (head == null) {
                head = node;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = node;
            }
        }

        @Override
        public java.util.Iterator<Entity> iterator() {
            return new BasketIterator();
        }

        private class BasketIterator implements java.util.Iterator<Entity> {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Entity next() {
                Entity entity = current.value;
                current = current.next;
                return entity;
            }
        }
    }

}
