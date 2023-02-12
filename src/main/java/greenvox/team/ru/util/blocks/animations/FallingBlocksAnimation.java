package greenvox.team.ru.util.blocks.animations;

import greenvox.team.ru.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class FallingBlocksAnimation extends BukkitRunnable {

    public AnimatableFallingBlocks AnimatingBlocks;
    public int TickPeriod;

    public FallingBlocksAnimation(int tickPeriod, FallingBlock... blocks) {
        AnimatingBlocks = new AnimatableFallingBlocks(blocks);

        TickPeriod = tickPeriod;
    }

    public FallingBlocksAnimation(int tickPeriod, Block... blocks) {
        AnimatingBlocks = new AnimatableFallingBlocks();

        for (Block b : blocks) {
            AnimatingBlocks.AddFallingBlocks(b.getWorld().spawnFallingBlock(b.getLocation().toCenterLocation(), b.getBlockData()));
            b.setType(Material.AIR);
        }

        TickPeriod = tickPeriod;
    }

    public FallingBlocksAnimation(int tickPeriod, Location... locations) {
        AnimatingBlocks = new AnimatableFallingBlocks();

        for (Location l : locations) {
            AnimatingBlocks.AddFallingBlocks(l.getWorld().spawnFallingBlock(l.getBlock().getLocation().toCenterLocation(), l.getBlock().getBlockData()));
            l.getBlock().setType(Material.AIR);
        }

        TickPeriod = tickPeriod;
    }

    public void StartUpdating() {
        this.runTaskTimer(Main.Instance, 0, TickPeriod);
    }
}