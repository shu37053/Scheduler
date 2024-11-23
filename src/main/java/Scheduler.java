import java.util.Comparator;
import java.util.PriorityQueue;

public class Scheduler implements Runnable {
    PriorityQueue<Executable> pq;
    private boolean stop;

    public Scheduler() {
        this.pq = new PriorityQueue<>(Comparator.comparingLong(Executable::getExecutionTimeStamp));
        stop = false;
    }

    public synchronized <T> boolean addTask(Executable<T> executable) {
        if (executable == null || stop) {
            return false;
        }
        pq.offer(executable);
        notifyAll();
        return true;
    }

    public synchronized void stop() {
        stop = true;
    }

    @Override
    public void run() {
        synchronized (this) {
            while (!stop || pq.size() > 0) {
                long currentTime = System.currentTimeMillis();
                try {
                    if (pq.size() == 0) {
                        this.wait();
                    } else {
                        long sleepTime = pq.peek().getExecutionTimeStamp() - currentTime;
                        if (sleepTime <= 0) {
                            execute(pq.poll());
                        } else {
                            this.wait(sleepTime);
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
            }
        }
    }

    private void execute(Executable executable) {
        new Thread(executable::run).start();
    }
}
