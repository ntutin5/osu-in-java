package dev.osuclone.ui;

import dev.osuclone.core.LoopListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.function.IntSupplier;

public class DemoScreen implements LoopListener {

    private static final double SPEED = 400.0; 
    private static final double SIZE = 60.0;

    private final Canvas canvas;
    private final GraphicsContext g;
    private IntSupplier fpsSupplier = () -> 0;

    private double x = 0;

    public DemoScreen(Canvas canvas) {
        this.canvas = canvas;
        this.g = canvas.getGraphicsContext2D();
    }

    public void setFpsSupplier(IntSupplier fpsSupplier) {
        this.fpsSupplier = fpsSupplier;
    }

    @Override
    public void update(double deltaSeconds) {
        x += SPEED * deltaSeconds;           // vitesse × temps
        if (x > canvas.getWidth()) {
            x = -SIZE;
        }
    }

    @Override
    public void render() {
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        g.setFill(Color.HOTPINK);
        g.fillRect(x, canvas.getHeight() / 2 - SIZE / 2, SIZE, SIZE);

        g.setFill(Color.WHITE);
        g.fillText("FPS : " + fpsSupplier.getAsInt(), 20, 30);
    }
}