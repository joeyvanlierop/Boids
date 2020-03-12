package ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import model.Boid;
import model.Vector;
import model.World;
import model.behaviour.rules.Alignment;
import model.behaviour.rules.Cohesion;
import model.behaviour.rules.Noise;
import model.behaviour.rules.Separation;

import java.util.ArrayList;

public class WorldApp extends Application {
    private World world;
    private Double[] boidPoints;

    public WorldApp() {
        world = new World.WorldBuilder().setWidth(500)
                .setHeight(500)
                .addRule(new Separation(true, 0.015, 15))
                .addRule(new Alignment(true, 0.01, 20))
                .addRule(new Cohesion(true, 0.01, 20))
                .addRule(new Noise(true, 0.05))
                .build();
        boidPoints = new Double[]{2.0, 0.0, -3.0, -2.0, -3.0, 2.0};
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        Pane canvas = new Pane();

        root.getChildren().add(canvas);
        canvas.setPrefSize(500, 500);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render(canvas);
            }
        }.start();

        stage.widthProperty().addListener((obs, oldVal, newVal) -> world.setWidth(newVal.intValue()));
        stage.heightProperty().addListener((obs, oldVal, newVal) -> world.setHeight(newVal.intValue()));
        stage.setTitle("Boids");
        stage.setScene(scene);
        stage.show();

        world.addRandomBoids(600);
    }

    private void update() {
        world.update();
    }

    private void render(Pane canvas) {
        canvas.getChildren().clear();
        ArrayList<Boid> boidList = world.getBoids();

        for (Boid boid : boidList) {
            Vector boidPosition = boid.getPosition();
            Polygon c = new Polygon();

            c.getPoints().addAll(boidPoints);
            c.setTranslateX(boidPosition.getX());
            c.setTranslateY(boidPosition.getY());
            c.setRotate(Math.toDegrees(boid.getVelocity().rot()));

            canvas.getChildren().addAll(c);
        }
    }
}
