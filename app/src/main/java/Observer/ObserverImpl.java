package Observer;

public class ObserverImpl implements Observer{

    private static ObserverImpl instance = null;
    private Subject topic;  // references 1 subject

    private ObserverImpl(){

    }

    public static ObserverImpl getInstance(){
        synchronized (ObserverImpl.class){
            if (instance == null){
                instance = new ObserverImpl();
            }

            return instance;
        }
    }


    // this method called after notifyObseevers from subject
    @Override
    public Object update() {
        return topic.getUpdate(this); // the message is determined based on message attribute from Subject


    }

    //ability to change the subjects, the leader
    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }
}
