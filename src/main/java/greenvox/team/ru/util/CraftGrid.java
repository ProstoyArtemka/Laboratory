package greenvox.team.ru.util;

import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import javax.xml.stream.Location;
import java.util.ArrayList;

public class CraftGrid implements Listener {
    public CraftGrid(Location location, ArrayList<ItemStack> craft) {

    }

    public static boolean containsCraft() {
        return false;
    }

    @EventHandler
    public void onItemPutInFrame(PlayerItemFrameChangeEvent e) {
        // Do glow for correct items for craft
    }
}
