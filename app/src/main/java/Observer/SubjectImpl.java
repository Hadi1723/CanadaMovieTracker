package Observer;

import java.util.ArrayList;
import java.util.List;
import favoriteListDatabase.builder.Favorite;
import model.Movie;

public class SubjectImpl implements Subject{
    private List<Observer> observers; // 1 subject and many observers
    private Movie task;
    private boolean changed;
    private final Object lock= new Object();

    private static SubjectImpl instance = null;

    private SubjectImpl(){
        this.observers=new ArrayList<>();
    }

    public static SubjectImpl getInstance(){
        synchronized (SubjectImpl.class){
            if (instance == null){
                instance = new SubjectImpl();
            }

            return instance;
        }
    }

    // adding an observer to subscriber list
    @Override
    public void register(Observer obj) {
        if(obj == null) throw new NullPointerException("Null Observer");
        synchronized (lock) {
            if(!observers.contains(obj)) observers.add(obj);
        }
    }

    //removoing observer from subscriber list
    @Override
    public void unregister(Observer obj) {
        synchronized (lock) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (lock) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);  // only works if postMessage is called before
            this.changed=false;
        }
        for (Observer obj : observersLocal) {  // notifying each observer who is registered before messgae
            obj.update();
        }

    }

    @Override
    public Object getUpdate(Observer obj) {  // needs onj input because called when each separate obj gets notified about changes caused by PostMessage()
        return this.task;
    }

    //method to post message to the topic
    public void postTask(Object task){
        this.task = (Movie) task;
        this.changed=true;
        notifyObservers();
    }
}

