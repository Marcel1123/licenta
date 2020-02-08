package Marcel.myutil;

public class SendFileCodeToServerThread implements Runnable {
    private String name;
    private Thread thread;

    public SendFileCodeToServerThread(String name){
        this.name = name;
        this.thread = new Thread(this, this.name);
    }

    @Override
    public void run() {

    }
}
