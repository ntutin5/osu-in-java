package dev.osuclone.core;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    private static final double NANOS_PER_SECOND = 1_000_000_000.0;
    private static final double MAX_DELTA = 0.1; 

    private final LoopListener listener;
    private long lastNanos = 0;

    private int framesThisSecond = 0;
    private double timeThisSecond = 0;
    private int fps = 0;

    public GameLoop(LoopListener listener) {
        this.listener = listener;
    }

    @Override
    public void start() {
        lastNanos = 0;
        super.start();
    }

    @Override
    public void handle(long now) {
        if (lastNanos == 0) {      
            lastNanos = now;
            return;
        }

        double rawDelta = (now - lastNanos) / NANOS_PER_SECOND;
        lastNanos = now;

        // Mesure des FPS sur une seconde
        framesThisSecond++;
        timeThisSecond += rawDelta;
        if (timeThisSecond >= 1.0) {
            fps = framesThisSecond;
            framesThisSecond = 0;
            timeThisSecond -= 1.0;
        }

        // Si le programme a geler/stoper, on limite le delta
        double delta = Math.min(rawDelta, MAX_DELTA);

        listener.update(delta);
        listener.render();
    }

    public int getFps() {
        return fps;
    }
}