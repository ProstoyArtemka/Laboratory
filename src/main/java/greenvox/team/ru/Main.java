package greenvox.team.ru;

import greenvox.team.ru.disease.DiseaseManager;
import greenvox.team.ru.laboratory.door.InnerDoorAnimation;
import greenvox.team.ru.laboratory.door.OutsideDoorAnimation;
import greenvox.team.ru.util.LocalTransform;
import greenvox.team.ru.util.blocks.animations.AnimatableFallingBlock;
import greenvox.team.ru.util.blocks.animations.BlockAnimation;
import greenvox.team.ru.util.blocks.animations.FallingBlockAnimation;
import greenvox.team.ru.util.blocks.animations.FallingBlocksAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public final class Main extends JavaPlugin {

    public static Main Instance;

    @Override
    public void onEnable() {
        Instance = this;

        World w = Bukkit.getWorld("world");
        LocalTransform.Transform = new Location(w, 0, 0, 0);

        new OutsideDoorAnimation().StartUpdating();
        new InnerDoorAnimation().StartUpdating();
    }

    @Override
    public void onDisable() {

    }
}
