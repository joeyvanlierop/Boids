package persistence;

import model.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

// A reader that can read world data from file
public class WorldReader {
    // MODIFIES: this
    // EFFECTS: Reads the given world from file
    public static World read(String file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File(file));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        World world = (World) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        return world;
    }
}
