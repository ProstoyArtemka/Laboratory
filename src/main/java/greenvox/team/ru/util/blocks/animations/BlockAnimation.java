package greenvox.team.ru.util.blocks.animations;

import greenvox.team.ru.Main;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class BlockAnimation extends BukkitRunnable {
    public AnimatableBlock AnimatingBlock;
    private final int TickPeriod;

    public BlockAnimation(Block block, int tickPeriod) {
        AnimatingBlock = new AnimatableBlock(block);
        TickPeriod = tickPeriod;
    }

    public BlockAnimation StartUpdating() {
        this.runTaskTimer(Main.Instance, 0, TickPeriod);

        return this;
    }
}
