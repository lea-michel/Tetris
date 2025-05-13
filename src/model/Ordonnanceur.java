package model;

public class Ordonnanceur extends Thread{
    Runnable r;
    long pause;

    public Ordonnanceur (Runnable r, long pause) {
        this.r=r;
        this.pause=pause;
    }


    @Override
    public void run() {
        while(true){
            r.run();
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
