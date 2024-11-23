public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        for(int i=0;i<10;i++) {
            manager.addTask(manager.addRandomTask());
        }
        manager.stop();
    }
}
