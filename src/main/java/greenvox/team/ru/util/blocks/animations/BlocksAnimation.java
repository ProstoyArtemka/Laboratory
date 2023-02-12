package greenvox.team.ru.util.blocks.animations;

import greenvox.team.ru.Main;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;

public class BlocksAnimation extends BukkitRunnable {

    public AnimatableBlocks AnimatingBlocks;
    private final int TickPeriod;

    @Override
    public void run() {

    }

    public BlocksAnimation(int tickPeriod, Block... blocks) {
        AnimatingBlocks = new AnimatableBlocks(blocks);
        TickPeriod = tickPeriod;
    }

    public BlocksAnimation(int tickPeriod, Location... locations) {
        AnimatingBlocks = new AnimatableBlocks();

        for (Location l : locations)
            AnimatingBlocks.AddBlock(l.getBlock());

        TickPeriod = tickPeriod;
    }

    public BlocksAnimation StartUpdating() {
        this.runTaskTimer(Main.Instance, 0, TickPeriod);

        return this;
    }
}
