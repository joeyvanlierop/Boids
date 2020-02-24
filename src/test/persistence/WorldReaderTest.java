package persistence;

import model.Boid;
import model.Vector;
import model.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WorldReaderTest {
    private static final String TEST_FILE = "./data/testWorldReader.world";
    private static final String TEST_FILE_IO_EXCEPTION = "./path/does/not/exist/testWorld.world";
    private static final String TEST_FILE_CLASS_NOT_FOUND = "./data/testWorldReaderClassNotFound.world";
    private World world;
    private Boid boid1;
    private Boid boid2;

    @BeforeEach
    void runBefore() {
        boid1 = new Boid(new Vector(2, 3), new Vector(4, 5), new Vector(6, 7), 8);
        boid2 = new Boid(new Vector(9, 10), new Vector(11, 12), new Vector(13, 14), 15);
        world = new World(20, 30);
        world.addBoid(boid1);
        world.addBoid(boid2);
    }

    @Test
    void testReadWorld() {
        try {
            WorldWriter.write(world, TEST_FILE);
            World loadedWorld = WorldReader.read(TEST_FILE);

            assertEquals(world.getWidth(), loadedWorld.getWidth());
            assertEquals(world.getHeight(), loadedWorld.getHeight());
            assertEquals(world.getBoidList().size(), loadedWorld.getBoidList().size());

            assertEquals(world.getBoidList().get(0), loadedWorld.getBoidList().get(0));
            assertEquals(world.getBoidList().get(1), loadedWorld.getBoidList().get(1));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (ClassNotFoundException e) {
            fail("ClassNotFoundException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            WorldReader.read(TEST_FILE_IO_EXCEPTION);
            fail("IOException should have been thrown");
        } catch (ClassNotFoundException e) {
            fail("ClassNotFoundException should not have been thrown");
        } catch (IOException e) {
            // Expected
        }
    }

    @Test
    void testClassNotFoundException() {
        try {
            WorldReader.read(TEST_FILE_CLASS_NOT_FOUND);
            fail("ClassNotFoundException should have been thrown");
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (ClassNotFoundException e) {
            // Expected
        }
    }
}
