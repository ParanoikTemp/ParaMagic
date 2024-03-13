package me.parax.paramagic.altars;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class checkNetherAltar {
    public static boolean checkAltar(Block center) {
        int x = center.getX();
        int y = center.getY();
        int z = center.getZ();
        World world = center.getWorld();
        if (world.getBlockAt(new Location(world, x, y - 1, z)).getType() != Material.GLOWSTONE) return false;
        if (world.getBlockAt(new Location(world, x + 1, y - 1, z)).getType() == Material.GOLD_BLOCK) {

            if (world.getBlockAt(new Location(world, x - 1, y - 1, z)).getType() != Material.GLOWSTONE) return false;
            if (world.getBlockAt(new Location(world, x, y - 1, z + 1)).getType() != Material.GLOWSTONE) return false;
            if (world.getBlockAt(new Location(world, x, y - 1, z - 1)).getType() != Material.GLOWSTONE) return false;

            if (world.getBlockAt(new Location(world, x - 2, y, z)).getType() != Material.NETHERRACK) return false;
            if (world.getBlockAt(new Location(world, x - 1, y, z + 1)).getType() != Material.BLACKSTONE) return false;
            if (world.getBlockAt(new Location(world, x - 1, y, z - 1)).getType() != Material.BLACKSTONE) return false;
            if (world.getBlockAt(new Location(world, x, y, z + 2)).getType() != Material.NETHERRACK) return false;
            if (world.getBlockAt(new Location(world, x, y, z - 2)).getType() != Material.NETHERRACK) return false;

            if (world.getBlockAt(new Location(world, x - 2, y + 1, z)).getType() != Material.FIRE) return false;
            if (world.getBlockAt(new Location(world, x, y + 1, z + 2)).getType() != Material.FIRE) return false;
            if (world.getBlockAt(new Location(world, x, y + 1, z - 2)).getType() != Material.FIRE) return false;

            return true;

        } else if (world.getBlockAt(new Location(world, x - 1, y - 1, z)).getType() == Material.GOLD_BLOCK) {

            if (world.getBlockAt(new Location(world, x + 1, y - 1, z)).getType() != Material.GLOWSTONE) return false;
            if (world.getBlockAt(new Location(world, x, y - 1, z + 1)).getType() != Material.GLOWSTONE) return false;
            if (world.getBlockAt(new Location(world, x, y - 1, z - 1)).getType() != Material.GLOWSTONE) return false;

            if (world.getBlockAt(new Location(world, x + 2, y, z)).getType() != Material.NETHERRACK) return false;
            if (world.getBlockAt(new Location(world, x + 1, y, z + 1)).getType() != Material.BLACKSTONE) return false;
            if (world.getBlockAt(new Location(world, x + 1, y, z - 1)).getType() != Material.BLACKSTONE) return false;
            if (world.getBlockAt(new Location(world, x, y, z + 2)).getType() != Material.NETHERRACK) return false;
            if (world.getBlockAt(new Location(world, x, y, z - 2)).getType() != Material.NETHERRACK) return false;

            if (world.getBlockAt(new Location(world, x + 2, y + 1, z)).getType() != Material.FIRE) return false;
            if (world.getBlockAt(new Location(world, x, y + 1, z + 2)).getType() != Material.FIRE) return false;
            if (world.getBlockAt(new Location(world, x, y + 1, z - 2)).getType() != Material.FIRE) return false;

            return true;

        } else if (world.getBlockAt(new Location(world, x, y - 1, z - 1)).getType() == Material.GOLD_BLOCK) {

            if (world.getBlockAt(new Location(world, x, y - 1, z + 1)).getType() != Material.GLOWSTONE) return false;
            if (world.getBlockAt(new Location(world, x + 1, y - 1, z)).getType() != Material.GLOWSTONE) return false;
            if (world.getBlockAt(new Location(world, x - 1, y - 1, z)).getType() != Material.GLOWSTONE) return false;

            if (world.getBlockAt(new Location(world, x, y, z + 2)).getType() != Material.NETHERRACK) return false;
            if (world.getBlockAt(new Location(world, x + 1, y, z + 1)).getType() != Material.BLACKSTONE) return false;
            if (world.getBlockAt(new Location(world, x - 1, y, z + 1)).getType() != Material.BLACKSTONE) return false;
            if (world.getBlockAt(new Location(world, x + 2, y, z)).getType() != Material.NETHERRACK) return false;
            if (world.getBlockAt(new Location(world, x - 2, y, z)).getType() != Material.NETHERRACK) return false;

            if (world.getBlockAt(new Location(world, x, y + 1, z + 2)).getType() != Material.FIRE) return false;
            if (world.getBlockAt(new Location(world, x + 2, y + 1, z)).getType() != Material.FIRE) return false;
            if (world.getBlockAt(new Location(world, x - 2, y + 1, z)).getType() != Material.FIRE) return false;

            return true;

        } else if (world.getBlockAt(new Location(world, x, y - 1, z + 1)).getType() == Material.GOLD_BLOCK) {

            if (world.getBlockAt(new Location(world, x, y - 1, z - 1)).getType() != Material.GLOWSTONE) return false;
            if (world.getBlockAt(new Location(world, x + 1, y - 1, z)).getType() != Material.GLOWSTONE) return false;
            if (world.getBlockAt(new Location(world, x - 1, y - 1, z)).getType() != Material.GLOWSTONE) return false;

            if (world.getBlockAt(new Location(world, x, y, z - 2)).getType() != Material.NETHERRACK) return false;
            if (world.getBlockAt(new Location(world, x + 1, y, z - 1)).getType() != Material.BLACKSTONE) return false;
            if (world.getBlockAt(new Location(world, x - 1, y, z - 1)).getType() != Material.BLACKSTONE) return false;
            if (world.getBlockAt(new Location(world, x + 2, y, z)).getType() != Material.NETHERRACK) return false;
            if (world.getBlockAt(new Location(world, x - 2, y, z)).getType() != Material.NETHERRACK) return false;

            if (world.getBlockAt(new Location(world, x, y + 1, z - 2)).getType() != Material.FIRE) return false;
            if (world.getBlockAt(new Location(world, x + 2, y + 1, z)).getType() != Material.FIRE) return false;
            if (world.getBlockAt(new Location(world, x - 2, y + 1, z)).getType() != Material.FIRE) return false;

            return true;

        }
        return false;
    }
}
