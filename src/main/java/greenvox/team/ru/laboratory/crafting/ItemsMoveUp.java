package greenvox.team.ru.laboratory.crafting;

import greenvox.team.ru.Main;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class ItemsMoveUp extends BukkitRunnable {
    private final ArrayList<Item> Items;
    private int index = 0;

    public ItemsMoveUp(ArrayList<Item> items) {
        Items = items;
    }

    @Override
    public void run() {
        if (index >= Items.size()) {

            new ItemsRotationAnimation(Items).runTaskTimer(Main.Instance, 120, 5);

            cancel();
            return;
        }

        Item i = Items.get(index);
        new ItemMoveUp(i).runTaskTimer(Main.Instance, 0, 2);

        index++;
    }
}
class ItemMoveUp extends BukkitRunnable {

    private final Item item;

    public ItemMoveUp(Item item) {
        this.item = item;
    }

    @Override
    public void run() {
        if (!item.isGlowing() || item.getLocation().getY() > 70) {
            cancel();
            return;
        }

        if (item.getLocation().getY() < 70) item.setVelocity(new Vector(0, 0.01f, 0));
        else item.setVelocity(new Vector(0, 0, 0));
    }
}