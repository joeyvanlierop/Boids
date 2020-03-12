package persistence;

import model.Boid;
import model.Vector;
import model.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WorldWriterTest {
    private static final String TEST_FILE = "./data/testWorldWriter.world";
    private static final String TEST_FILE_IO_EXCEPTION = "./path/does/not/exist/testWorld.world";
    private World world;

    @BeforeEach
    void runBefore() {
        WorldWriter worldWriter = new WorldWriter();
        world = new World.WorldBuilder().setWidth(20).setHeight(30).build();
        world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(1, 2)).setVelocity(new Vector(3, 4)).build());
        world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(5, 6)).setVelocity(new Vector(7, 8)).build());
    }

    @Test
    void testWriteWorld() {
        try {
            WorldWriter.write(world, TEST_FILE);
            World loadedWorld = WorldReader.read(TEST_FILE);

            assertEquals(world.getWidth(), loadedWorld.getWidth());
            assertEquals(world.getHeight(), loadedWorld.getHeight());
            assertEquals(world.getBoids().size(), loadedWorld.getBoids().size());
            assertEquals(world.getBoids().get(0), loadedWorld.getBoids().get(0));
            assertEquals(world.getBoids().get(1), loadedWorld.getBoids().get(1));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (ClassNotFoundException e) {
            fail("ClassNotFoundException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            WorldWriter.write(world, TEST_FILE_IO_EXCEPTION);
            fail("IOException should have been thrown");
        } catch (IOException e) {
            // Expected
        }
    }
}
