package greenvox.team.ru;

import greenvox.team.ru.util.LocalTransform;
import greenvox.team.ru.util.blocks.animations.AnimatableFallingBlock;
import greenvox.team.ru.util.blocks.animations.BlockAnimation;
import greenvox.team.ru.util.blocks.animations.FallingBlockAnimation;
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
    }

    @Override
    public void onDisable() {

    }
}
