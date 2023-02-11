package greenvox.team.ru;

import greenvox.team.ru.util.LocalTransform;
import greenvox.team.ru.util.blocks.animations.BlockAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class Main extends JavaPlugin {

    public static Main Instance;

    @Override
    public void onEnable() {
        Instance = this;
        LocalTransform.Transform = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);

        new BlockAnimation(Bukkit.getWorld("world").getBlockAt(100, 64, 100), 20) {
            private int Timer;
            @Override
            public void run() {
                if (Timer == 0)
                    AnimatingBlock.SetBlockType(Material.DIAMOND_BLOCK);
                else
                    AnimatingBlock.MoveBlockPosition(new Vector(0, Math.sin(Timer / 10f) * 25f, 0));

                if (Timer >= 1000)
                    this.cancel();

                Timer++;
            }
        }.StartUpdating();
    }

    @Override
    public void onDisable() {

    }
}
