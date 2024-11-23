import java.util.Random;

public class Manager {
    private final Scheduler scheduler;
    private final Random random;
    private int id;

    public Manager() {
        this.scheduler = new Scheduler();
        new Thread(scheduler).start();
        random = new Random();
        id = 0;
    }

    public void addTask(Executable executable){
        double sec = (1.0*(executable.getExecutionTimeStamp()-System.currentTimeMillis()))/1000;
        System.out.printf("Adding task with id : %s, AND execution after: %f%n", executable.getId(), sec);
        scheduler.addTask(executable);
    }

    public void stop() {
        scheduler.stop();
    }

    public Executable addRandomTask() {
        long time = random.nextInt(50) * 10;
        long currTime = System.currentTimeMillis();
        return new Executable<>(id++, currTime, currTime + time, null) {
            @Override
            public void run() {
                for(int i=0;i<100000;i++) {
                    if(i%100 == 0)
                        System.out.printf("From %s printing: %s%n", this.getId(), i);
                }
            }
        };
    }
}
