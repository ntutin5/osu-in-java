package dev.osuclone;

import dev.osuclone.core.GameLoop;
import dev.osuclone.ui.DemoScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(1280, 720);
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, 1280, 720);

        DemoScreen demo = new DemoScreen(canvas);
        GameLoop loop = new GameLoop(demo);
        demo.setFpsSupplier(loop::getFps);

        stage.setTitle("osu! Java");
        stage.setScene(scene);
        stage.show();

        loop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}