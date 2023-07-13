package bridge;

//A refined abstraction.
public class Queue<T> implements FIFOCollection<T>{

    private LinkedList<T> list;

    public Queue(LinkedList<T> list) {
        this.list = list;
    }

    @Override
    public void offer(T element) {
        list.addLast(element);
    }

    @Override
    public T poll() {
        return list.removeFirst();
    }

    @Override
    public T get(int pos) {
        return list.getElement(pos);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public void removeAll() {
        list.deleteEverything();
    }


}