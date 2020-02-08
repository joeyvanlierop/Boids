package ui;

import model.World;

public class Main {
    public static void main(String[] args) {
        World world = new World(10, 10, 4);
        System.out.println(world.getBoidList());
        world.tick();
        System.out.println(world.getBoidList());
    }
}
