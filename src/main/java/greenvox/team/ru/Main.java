package greenvox.team.ru;

import greenvox.team.ru.laboratory.crafting.CraftGrid;
import greenvox.team.ru.laboratory.crafting.CraftStart;
import greenvox.team.ru.laboratory.door.InnerDoorAnimation;
import greenvox.team.ru.laboratory.door.OutsideDoorAnimation;
import greenvox.team.ru.laboratory.zone.RegionListener;
import greenvox.team.ru.util.LocalTransform;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main Instance;

    private static final Material[][] VaccineCraft = new Material[][] {
            {Material.SCULK_SENSOR, Material.SEA_PICKLE, Material.CHORUS_FLOWER},
            {Material.EXPERIENCE_BOTTLE, Material.GOLDEN_APPLE, Material.DRAGON_BREATH},
            {Material.CRIMSON_FUNGUS, Material.AMETHYST_SHARD, Material.WITHER_ROSE}
    };

    @Override
    public void onEnable() {
        Instance = this;

        World w = Bukkit.getWorld("world");
        LocalTransform.Transform = new Location(w, 0, 0, 0);

        new OutsideDoorAnimation().StartUpdating();
        new InnerDoorAnimation().StartUpdating();

        new CraftGrid(VaccineCraft);
        new Visual().runTaskTimer(this, 0, 20*3);

        Bukkit.getPluginManager().registerEvents(new RegionListener(), this);
        Bukkit.getPluginManager().registerEvents(new CraftStart(), this);
    }

    @Override
    public void onDisable() {

    }
}
