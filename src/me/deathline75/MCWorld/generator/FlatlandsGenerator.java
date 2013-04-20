package me.deathline75.MCWorld.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class FlatlandsGenerator extends ChunkGenerator {

    public static String[] layers;
    public static String biome;
    public static String[] additionalItems;

    public static void setGeneratorOptions(String generatorOptions) {
	String[] generator = generatorOptions.split(";");
	biome = generator[0];
	layers = generator[1].split(",");
	additionalItems = generator[2].split(",");
	System.out.println(layers[0]);
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
	int currentHeight = 0;
	for (String s : layers) {
	    String[] block = s.split("x");
	    int blockID = Integer.parseInt(block[0]);
	    int height = Integer.parseInt(block[1]);
	    for (int y = 0; y < height; y++) {
		for (int x = 0; x < 16; x++) {
		    for (int z = 0; z < 16; z++) {
			this.setBlock(result, x, y + currentHeight, z,
				(byte) blockID);

			biomes.setBiome(x, z, Biome.valueOf(biome));
		    }
		}
	    }
	    currentHeight = height + currentHeight;
	}
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
