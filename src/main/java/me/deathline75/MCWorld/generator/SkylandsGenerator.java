package me.deathline75.MCWorld.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.NoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;

/*
 * Under Construction
 */

public class SkylandsGenerator extends ChunkGenerator{

    private NoiseGenerator generator;

    private NoiseGenerator getGenerator(World world) {
        if (generator == null) {
            generator = new SimplexNoiseGenerator(world);
        }

        return generator;
    }

    private void setBlock(byte[][] rslt, int x, int y, int z, int blkid) {
	if (rslt[y / 16] == null) {
	    rslt[y / 16] = new byte[4096];
	}
	rslt[y / 16][((y & 0xF) << 8) | (z << 4) | x] = (byte) blkid;
    }

    @Override
    public byte[][] generateBlockSections(World world, Random random, int cx,
	    int cz, BiomeGrid biomes) {
	byte[][] result = new byte[16][];
	double humidity = world.getHumidity(cx, cz);
	return result;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
	return new ArrayList<BlockPopulator>();
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
	int x = random.nextInt(200) - 100;
	int z = random.nextInt(200) - 100;
	int y = world.getHighestBlockYAt(x, z);
	return new Location(world, x, y, z);
    }

}
