package ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
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
import persistence.WorldReader;
import persistence.WorldWriter;

import java.util.ArrayList;
import java.util.Optional;

public class WorldApp extends Application {
    // Default world settings
    private static final int DEFAULT_WIDTH = 500;
    private static final String WORLD_FILE = "./data/autosave.world";
    private static final int DEFAULT_HEIGHT = 500;

    // Boid Simulation Settings
    private static final int VIEW_ANGLE = 120;
    private static final double NOISE_WEIGHT = 0.07;
    private static final double SEPARATION_WEIGHT = 0.05;
    private static final double SEPARATION_RADIUS = 10;
    private static final double ALIGNMENT_WEIGHT = 0.02;
    private static final double ALIGNMENT_RADIUS = 20;
    private static final double COHESION_WEIGHT = 0.02;
    private static final double COHESION_RADIUS = 20;

    private World world;
    private Double[] boidPoints;

    public WorldApp() {
        boidPoints = new Double[]{2.0, 0.0, -3.0, -2.0, -3.0, 2.0};
    }

    @Override
    public void start(Stage stage) {
        loadWorld();

        Group root = new Group();
        Scene scene = new Scene(root);
        Pane canvas = new Pane();

        root.getChildren().add(canvas);
        canvas.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render(canvas);
            }
        }.start();

        scene.setOnMousePressed(event -> world.addRandomBoid(new Vector(event.getX(), event.getY())));
        stage.widthProperty().addListener((obs, oldVal, newVal) -> world.setWidth(newVal.intValue()));
        stage.heightProperty().addListener((obs, oldVal, newVal) -> world.setHeight(newVal.intValue()));
        stage.setTitle("Boids");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        WorldWriter.write(world, WORLD_FILE);
    }

    private void loadWorld() {
        try {
            World loadedWorld = WorldReader.read(WORLD_FILE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Would you like to load the autosaved world?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    world = loadedWorld;
                } else {
                    newWorld();
                }
            }
        } catch (Exception e) {
            newWorld();
        }
    }

    private void newWorld() {
        TextInputDialog dialog = new TextInputDialog("600");
        dialog.setHeaderText("How many boids would you like to initialize");

        Optional<String> result = dialog.showAndWait();
        int boidCount = 0;
        if (result.isPresent()) {
            try {
                boidCount = Integer.parseInt(result.get());
            } catch (NumberFormatException ignored) {
                System.out.println("Invalid starting boid count");
            }
        }

        world = new World.WorldBuilder()
                .setWidth(DEFAULT_WIDTH)
                .setHeight(DEFAULT_HEIGHT)
                .setViewAngle(VIEW_ANGLE)
                .addRule(new Noise(true, NOISE_WEIGHT))
                .addRule(new Separation(true, SEPARATION_WEIGHT, SEPARATION_RADIUS))
                .addRule(new Alignment(true, ALIGNMENT_WEIGHT, ALIGNMENT_RADIUS))
                .addRule(new Cohesion(true, COHESION_WEIGHT, COHESION_RADIUS))
                .build();
        world.addRandomBoids(boidCount);
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
            c.setRotate(Math.toDegrees(boid.getVelocity().angle()));

            canvas.getChildren().addAll(c);
        }
    }
}
