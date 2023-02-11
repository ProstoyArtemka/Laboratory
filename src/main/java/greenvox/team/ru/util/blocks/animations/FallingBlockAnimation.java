package greenvox.team.ru.util.blocks.animations;

import greenvox.team.ru.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class FallingBlockAnimation extends BukkitRunnable {

    public AnimatableFallingBlock AnimatingBlock;
    public int TickPeriod;

    public FallingBlockAnimation(FallingBlock block, int tickPeriod) {
        AnimatingBlock = new AnimatableFallingBlock(block);
        TickPeriod = tickPeriod;
    }

    public FallingBlockAnimation(Location location, BlockData data, int tickPeriod) {
        FallingBlock block = location.getWorld().spawnFallingBlock(location, data);

        AnimatingBlock = new AnimatableFallingBlock(block);
        TickPeriod = tickPeriod;
    }

    public FallingBlockAnimation(Location location, Material material, int tickPeriod) {
        FallingBlock block = location.getWorld().spawnFallingBlock(location, material, (byte) 0);

        AnimatingBlock = new AnimatableFallingBlock(block);
        TickPeriod = tickPeriod;
    }

    public void StartUpdating() {
        this.runTaskTimer(Main.Instance, 0, TickPeriod);
    }
}