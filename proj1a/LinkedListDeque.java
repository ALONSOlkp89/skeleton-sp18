public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;

    private class TNode {
        private TNode prev;
        private T item;
        private TNode next;
        public TNode(TNode pre, T i, TNode nex) {
            prev = pre;
            item = i;
            next = nex;
        }
    }

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel, index);

    }

    private T getRecursiveHelper(TNode p, int index) {
        if (p.next == null) {
            return null;
        }
        if (index == 0) {
            return p.next.item;
        }
        if (index >= size) {
            index = size - 1;
        }
        p = p.next;
        return getRecursiveHelper(p, index - 1);
    }

    public void addFirst(T item) {
        TNode temp = new TNode(sentinel, item, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size++;
    }
    public void addLast(T item) {
        TNode temp = new TNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size++;
    }
    public boolean isEmpty() {
        return 0 == size;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        TNode p = sentinel.next;
        while (p != null && p != sentinel) {
            System.out.println(p.item);
            p = p.next;
        }
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        TNode result = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        result.prev = null;
        result.next = null;
        size--;
        return result.item;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        TNode result = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        result.prev = null;
        result.next = null;
        size--;
        return result.item;

    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        TNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

}
