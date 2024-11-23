public abstract class Executable<T> {

    private final int id;

    private final long currentTimeStamp;

    private final long executionTimeStamp;

    private T data;

    public Executable(int id, long currentTimeStamp, long executionTimeStamp, T data) {
        this.id = id;
        this.currentTimeStamp = currentTimeStamp;
        this.executionTimeStamp = executionTimeStamp;
        this.data = data;
    }

    public long getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public long getExecutionTimeStamp() {
        return executionTimeStamp;
    }

    public T getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public void setData(T data) {
        this.data = data;
    }

    public abstract void run();
}
