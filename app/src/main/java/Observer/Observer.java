package Observer;

public interface Observer {

    //method to update the observer, used by subject
    public Object update();

    //attach with subject to observe
    public void setSubject(Subject sub);
}
