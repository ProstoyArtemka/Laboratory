package greenvox.team.ru.laboratory.crafting;

import com.destroystokyo.paper.ParticleBuilder;
import greenvox.team.ru.Main;
import greenvox.team.ru.util.LocalTransform;
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.entity.GlowItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CraftGrid extends BukkitRunnable implements Listener {

    public static Location Location = LocalTransform.LocalToGlobal(49 ,67, 14);;
    public static Material[][] Craft;
    public static Material[][] FramesContent = new Material[3][3];

    public CraftGrid(Material[][] craft) {
        Craft = craft;
        UpdateFrameContent();
        UpdateEndPortals();

        this.runTaskTimer(Main.Instance, 0, 50);
        Bukkit.getPluginManager().registerEvents(this, Main.Instance);
    }

    private void UpdateFrameContent() {
        Collection<GlowItemFrame> frames = Location.getNearbyEntitiesByType(GlowItemFrame.class, 5);

        for (GlowItemFrame frame : frames) {
            Location gridFrameLocation = frame.getLocation().subtract(Location);

            FramesContent[gridFrameLocation.getBlockX()][gridFrameLocation.getBlockZ()] = frame.getItem().getType();
        }
    }

    private void UpdateEndPortal(Location frameLocation, Material item) {
        Block endPortalFrame = frameLocation.subtract(0, 1, 0).getBlock();
        EndPortalFrame data = (EndPortalFrame) endPortalFrame.getBlockData();

        data.setEye(item != Material.AIR);
        endPortalFrame.setBlockData(data);
    }

    private void UpdateEndPortals() {
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                Location frameLocation = Location.clone().add(x, 0, z);

                UpdateEndPortal(frameLocation, FramesContent[x][z]);
            }
        }
    }

    @EventHandler
    public void OnPlayerPutItem(PlayerItemFrameChangeEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                UpdateFrameContent();
                UpdateEndPortals();
            }
        }.runTaskLater(Main.Instance, 1);
    }

    public static boolean ContainsCraft() {
        boolean result = true;

        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                if (FramesContent[z][x] != Craft[x][z]) return false;
            }
        }

        return result;
    }

    private void spawnParticles(Location frameLocation) {
        ParticleBuilder builder = new ParticleBuilder(Particle.SPELL)
                .location(frameLocation.toCenterLocation())
                .count(5)
                .allPlayers()
                .offset(0, 0.2f, 0);

        builder.spawn();
    }

    @Override
    public void run() {
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                Material craftItem = Craft[x][z];
                Material gridItem = FramesContent[z][x];
                Location frameLocation = Location.clone().add(x, 0, z);

                if (craftItem == gridItem) spawnParticles(frameLocation);
            }
        }
    }
}
