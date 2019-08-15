package Marcel.myutil;

import Marcel.App;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class MyThread implements Runnable {
    private String threadName;

    private Thread thread;

    private WatchService watcher = null ;
    private Path dir = null;

    private boolean stop = false;


    public MyThread(String threadName, String directoryPath) {
        this.threadName = threadName;

        try{
            watcher = FileSystems.getDefault().newWatchService();
            dir = Paths.get(directoryPath);

            thread = new Thread(this,threadName);
            thread.start();
        } catch (IOException e){
            System.out.println("Impossible to start thread");
        }
    }



    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try{
            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            while (!stop) {
                WatchKey key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    System.out.println(kind.name() + ": " + fileName);
                    Utilitar.getAllFile(App.getAppConfiguration().getProjectFiles());
                    key.reset();
                }
            }
        } catch (IOException ioException){
            System.out.println("Problem with path... ");
        } catch (InterruptedException interruptException){
            System.out.println("Interrupt exception... ");
        }
    }

    public void stopWatch(){
        this.stop = true;
    }
}
