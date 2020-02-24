package ui;

import model.Boid;
import model.Vector;
import model.World;
import persistence.WorldReader;
import persistence.WorldWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorldApp {
    private static final String WORLD_FILE = "./data/autosave.world";
    private World world;
    private Scanner input;

    // EFFECTS: runs the teller application
    public WorldApp() {
        runWorld();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runWorld() {
        boolean running = true;
        String command;
        input = new Scanner(System.in);

        loadWorld();

        while (running) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                saveWorld();
                running = false;
            } else {
                processCommand(command);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: Loads world from WORLD_FILE, if that file exists
    //          otherwise initializes world with default values
    private void loadWorld() {
        try {
            World loadedWorld = WorldReader.read(WORLD_FILE);

            System.out.print("Would you like to load the autosaved world (y/n): ");
            String answer = input.next();
            if (answer.equals("y")) {
                world = loadedWorld;
            } else {
                newWorld();
            }
        } catch (IOException | ClassNotFoundException e) {
            newWorld();
        }
    }

    // EFFECTS: Saves world state to WORLD_FILE
    private void saveWorld() {
        try {
            WorldWriter.write(world, WORLD_FILE);
        } catch (IOException e) {
            System.out.println("Unable to save world to " + WORLD_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes world
    private void newWorld() {
        System.out.print("Enter the width of the world: ");
        int width = input.nextInt();

        System.out.print("Enter the height of the world: ");
        int height = input.nextInt();

        System.out.print("Enter amount of boids to populate world with: ");
        int amount = input.nextInt();

        world = new World(width, height, amount);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> manually add a boid");
        System.out.println("\tr -> randomly add a boid");
        System.out.println("\tl -> list all boids");
        System.out.println("\tu -> update all boids");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "a":
                manuallyAddBoid();
                break;
            case "r":
                randomlyAddBoid();
                break;
            case "l":
                listAllBoids();
                break;
            case "u":
                updateAllBoids();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts a user for the position and velocity of a boid to add to the world
    private void manuallyAddBoid() {
        int positionX;
        int positionY;
        int velocityX;
        int velocityY;

        System.out.print("Enter x position of boid: ");
        positionX = input.nextInt();

        System.out.print("Enter y position of boid: ");
        positionY = input.nextInt();

        System.out.print("Enter x velocity of boid: ");
        velocityX = input.nextInt();

        System.out.print("Enter y velocity of boid: ");
        velocityY = input.nextInt();

        Boid boid = new Boid(new Vector(positionX, positionY), new Vector(velocityX, velocityY));
        world.addBoid(boid);
    }

    // MODIFIES: this
    // EFFECTS: randomly adds a boid to the world
    private void randomlyAddBoid() {
        world.addRandomBoid();
    }

    // EFFECTS: prints list of boids in the world to the screen
    private void listAllBoids() {
        ArrayList<Boid> boidList = world.getBoidList();
        for (Boid boid : boidList) {
            System.out.println(boid);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates all boids in the world
    private void updateAllBoids() {
        world.tick();
    }
}
