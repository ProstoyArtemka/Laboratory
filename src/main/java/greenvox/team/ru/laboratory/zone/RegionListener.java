package greenvox.team.ru.laboratory.zone;

import greenvox.team.ru.util.LocalTransform;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class RegionListener implements Listener {

    private static final Location FirstPoint = LocalTransform.LocalToGlobal(-25, -25, -25);
    private static final Location SecondPoint = LocalTransform.LocalToGlobal(70, 80, 45);

    public static boolean InZone(Location location) {
        return location.getBlockX() >= FirstPoint.getBlockX() && location.getBlockX() <= SecondPoint.getBlockX() &&
            location.getBlockY() >= FirstPoint.getBlockY() && location.getBlockY() <= SecondPoint.getBlockY() &&
            location.getBlockZ() >= FirstPoint.getBlockZ() && location.getBlockZ() <= SecondPoint.getBlockZ();
    }

    @EventHandler
    public void OnBlockBroke(BlockBreakEvent e) {
        if (e.getPlayer().isOp()) return;

        if (InZone(e.getBlock().getLocation())) e.setCancelled(true);
    }

    @EventHandler
    public void OnBlockExplode(BlockExplodeEvent e) {
        if (InZone(e.getBlock().getLocation())) e.setCancelled(true);
    }

    @EventHandler
    public void OnBlockPiston(BlockPistonRetractEvent e) {
        if (InZone(e.getBlock().getLocation())) e.setCancelled(true);
    }

    @EventHandler
    public void OnBlockPiston(BlockPistonExtendEvent e) {
        if (InZone(e.getBlock().getLocation())) e.setCancelled(true);
    }

    @EventHandler
    public void OnBlockPlace(BlockPlaceEvent e) {
        if (e.getPlayer().isOp()) return;

        if (InZone(e.getBlock().getLocation())) e.setCancelled(true);
    }

    @EventHandler
    public void OnBlockGrow(BlockGrowEvent e) {
        if (InZone(e.getBlock().getLocation())) e.setCancelled(true);
    }

    @EventHandler
    public void OnEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager().isOp()) return;

        if (InZone(e.getEntity().getLocation())) e.setCancelled(true);
    }

//    @EventHandler
//    public void OnEntitySpawn(EntitySpawnEvent e) {
//        if (e.)
//
//        if (InZone(e.getLocation())) e.setCancelled(true);
//    }
}
