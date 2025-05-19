package model;

public class Ordonnanceur extends Thread{
    Runnable r;
    long pause;
    boolean play = true;

    public Ordonnanceur (Runnable r, long pause) {
        this.r=r;
        this.pause=pause;
    }

    public void onOff() {
        if (play==true) {
            play = false;
        } else {
            play = true;
        }
    }

    @Override
    public void run() {
        while(true){
            if (play==true) {
                r.run();
            }
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
