package greenvox.team.ru.util.blocks.animations;

import greenvox.team.ru.Main;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class FallingBlockAnimation extends BukkitRunnable {
    public void StartUpdating(int tickPeriod) {
        this.runTaskTimer(Main.Instance, 0, tickPeriod);
    }
}
