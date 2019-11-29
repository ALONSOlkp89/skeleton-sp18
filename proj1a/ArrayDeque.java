public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static int factor = 2;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resizeLarge();
        }
        items[nextFirst] = item;
        nextFirst--;
        nextFirst = resizeIdx(nextFirst);
        size++;
    }

    private void resizeLarge() {
        T[] temp = (T[]) new Object[items.length * factor];
        int copyNum = 0;
        int desIdx = items.length * factor / 4;
        int orgIdx = nextFirst + 1;
        while (copyNum < size) {
            orgIdx = resizeIdx(orgIdx);
            temp[desIdx] = items[orgIdx];
            orgIdx++;
            desIdx++;
            copyNum++;
        }
        nextFirst = items.length * factor / 4 - 1;
        nextLast = desIdx;
        items = temp;
    }


    public void addLast(T item) {
        if (nextFirst == nextLast) {
            resizeLarge();
        }
        items[nextLast] = item;
        nextLast++;
        nextLast = resizeIdx(nextLast);
        size++;
    }

    private int resizeIdx(int idx) {
        if (idx >= items.length) {
            idx = 0;
        }
        if (idx < 0) {
            idx = items.length - 1;
        }
        return idx;
    }

    public boolean isEmpty() {
        return 0 == size;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size > 0) {
            int p = nextFirst + 1;
            p = resizeIdx(p);
            while (p != nextLast) {
                System.out.println(items[p]);
                p = resizeIdx(p + 1);
            }
        }
    }
    public T removeFirst() {
        nextFirst = resizeIdx(nextFirst + 1);
        T result = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (items.length > 16 && size < items.length / 4.0) {
            resizeSmall();
        }
        return result;
    }

    public T removeLast() {
        nextLast = resizeIdx(nextLast - 1);
        T result = items[nextLast];
        items[nextLast] = null;
        size--;
        if (items.length > 16 && size < items.length / 4.0) {
            resizeSmall();
        }
        return result;
    }

    private void resizeSmall() {
        T[] temp = (T[]) new Object[items.length / 2];
        int copyNum = 0;
        int desIdx = temp.length / 4;
        int orgIdx = nextFirst + 1;
        while (copyNum < size) {
            orgIdx = resizeIdx(orgIdx);
            temp[desIdx] = items[orgIdx];
            orgIdx++;
            desIdx++;
            copyNum++;
        }
        nextFirst = temp.length / 4 - 1;
        nextLast = desIdx;
        items = temp;
    }

    public T get(int index) {
        index = resizeIdx(nextFirst + 1 + index);
        return items[index];
    }
}
