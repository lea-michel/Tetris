package model;

public class Ordonnanceur extends Thread{
    Runnable r;
    private long pause;
    boolean play = true;
    boolean stopped = false ;

    public Ordonnanceur (Runnable r, long pause) {
        this.r=r;
        this.pause=pause;
    }

    public long getPause() {
        return pause;
    }

    public void setPause(long pause) {
        this.pause = pause;
    }

    public void onOff() {
        if (play==true) {
            play = false;
        } else {
            play = true;
        }
    }

    public void stopThread(){
        stopped=true;
        this.interrupt();
    }

    public void restart(){
        play = true;
    }

    @Override
    public void run() {
        while(!stopped){
            if (play) {
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
