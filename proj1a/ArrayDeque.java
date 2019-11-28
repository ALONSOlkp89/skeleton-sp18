public class ArrayDeque<T> {
    public T[] items;
    public int size;
    public int nextFirst;
    public int nextLast;
    private static int factor = 2;

    public ArrayDeque(){
        items = (T[])new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public void addFirst(T item){
        if(size == items.length){
            resizeLarge();
        }
        items[nextFirst] = item;
        nextFirst--;
        nextFirst = resizeIdx(nextFirst);
        size++;
    }

    private void resizeLarge() {
            T[] temp = (T[])new Object[size*factor];
            System.arraycopy(items,0,temp,0,items.length);
            items = temp;
    }


    public void addLast(T item){
        if(size == items.length){
            resizeLarge();
        }
        items[nextLast] = item;
        nextLast++;
        nextLast = resizeIdx(nextLast);
        size++;
    }

    private int resizeIdx(int idx) {
        if(idx >= items.length){
            idx = 0;
        }
        if(idx < 0){
            idx = items.length-1;
        }
        return idx;
    }

    public boolean isEmpty(){
        return 0==size;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if(size > 0){
            int p = nextFirst + 1;
            p = resizeIdx(p);
            while(p != nextLast){
                System.out.println(items[p]);
                p = resizeIdx(p+1);
            }
        }
    }
    public T removeFirst(){
        nextFirst = resizeIdx(nextFirst + 1);
        T result = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if(items.length > 16 && size < items.length/4.0){
            resizeSmall();
        }
        return result;
    }

    public T removeLast(){
        nextLast = resizeIdx(nextLast - 1);
        T result = items[nextLast];
        items[nextLast] = null;
        size--;
        if(items.length > 16 && size < items.length/4.0){
            resizeSmall();
        }
        return result;
    }

    private void resizeSmall() {
        T[] temp = (T[])new Object[items.length/2];
        if(resizeIdx(nextLast-1) > resizeIdx(nextFirst+1)){
            System.arraycopy(items,nextFirst+1,temp,0,size);
        }
        else{
            System.arraycopy(items,nextFirst+1,temp,0,items.length-nextFirst-1);
            System.arraycopy(items, 0, temp, items.length-nextFirst-1, nextLast);
        }
        items = temp;
    }

    public T get(int index){
        index = resizeIdx(nextFirst+1+index);
        return items[index];
    }
}
