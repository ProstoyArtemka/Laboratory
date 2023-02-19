package greenvox.team.ru.laboratory.crafting;

import greenvox.team.ru.Main;
import greenvox.team.ru.util.LocalTransform;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class CraftStart implements Listener {

    public static boolean CraftProcessing = false;

    private final Location ButtonPosition = LocalTransform.LocalToGlobal(62, 69,13);

    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;
        if (!e.getAction().isRightClick()) return;
        if (CraftProcessing) return;
        if (!e.getClickedBlock().getLocation().toBlockLocation().equals(ButtonPosition.toBlockLocation())) return;
        if (!CraftGrid.ContainsCraft()) return;

        CraftProcessing = true;
        new FramesToItems().runTaskTimer(Main.Instance, 40, 40);
    }
}
