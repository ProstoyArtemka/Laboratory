package greenvox.team.ru.craft;

import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CraftGrid implements Listener {
    private static final ArrayList<ItemStack> StacksInFrames = new ArrayList<>();

    public CraftGrid(Location location, ArrayList<ItemStack> craft) {
        Entity entities = (Entity) location.toCenterLocation().getBlock().getBoundingBox().expand(-1,0,-1,1,1,1);

        getStackInFrame(location, entities);

        containsCraft(craft);
    }

    public static boolean containsCraft(ArrayList<ItemStack> craft) {
        return StacksInFrames.equals(craft);
    }

    @EventHandler
    public void onItemPutInFrame(PlayerItemFrameChangeEvent e) {
        //later :cry:
    }

    private static void getStackInFrame(Location location, Entity nearEntities) {
        if (!(location.toCenterLocation().getNearbyEntities(0,1,0) instanceof ItemFrame)) return;

        if (location.toCenterLocation().getBlock().getBoundingBox().expand(-1,0,-1,1,1,1) instanceof ItemFrame) {

            List<ItemFrame> frameList = new ArrayList<>();
            frameList.add((ItemFrame) nearEntities);

            for (ItemFrame entity : frameList) {

                ItemStack itemStackInFrame = entity.getItem();

                CraftGrid.StacksInFrames.add(itemStackInFrame);
            }
        }
    }
}
