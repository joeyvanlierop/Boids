package persistence;

import model.World;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

// A writer that can write world data to file
public class WorldWriter {
    // MODIFIES: this
    // EFFECTS: Writes the given world to file
    public static void write(World world, String file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(world);

        objectOutputStream.close();
        fileOutputStream.close();
    }
}
