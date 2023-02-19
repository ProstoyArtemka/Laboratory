package greenvox.team.ru.laboratory.crafting;

import greenvox.team.ru.Main;
import greenvox.team.ru.util.LocalTransform;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.ArrayList;

public class ItemsRotationAnimation extends BukkitRunnable {

    public final static Location ItemsCenter = LocalTransform.LocalToGlobal(50.5f, 70, 15.5f);
    private final ArrayList<Item> Items;
    private final ArrayList<BukkitTask> Runnables = new ArrayList<>();
    private int index = 0;
    public ItemsRotationAnimation(ArrayList<Item> items) {
        Items = items;
    }


    @Override
    public void run() {
        if (index >= Items.size()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (BukkitTask r : Runnables) r.cancel();

                    new ExplosionAndVaccine(Items);
                }
            }.runTaskLater(Main.Instance, 20 * 20);

            cancel();
            return;
        }

        BukkitTask runnable = new RotateItem(Items.get(index)).runTaskTimer(Main.Instance, 0, 1);
        Runnables.add(runnable);

        index++;
    }
}
class RotateItem extends BukkitRunnable {
    private final Item item;
    private int Timer;
    public RotateItem(Item item) {
        this.item = item;
    }

    @Override
    public void run() {
        double cos = Math.cos(Timer);
        double sin = Math.sin(Timer);

        Location toLocation = ItemsRotationAnimation.ItemsCenter.clone().add(cos, 0, sin);
        item.setVelocity(toLocation.subtract(item.getLocation().toVector()).toVector().normalize().multiply(0.1f));

        if (Timer / 2 >= 160) {
            new ParticleBuilder(ParticleEffect.SOUL)
                    .setSpeed(0.25f)
                    .setAmount(2)
                    .setLocation(item.getLocation())
                    .display();
        }
        if (Timer / 2 >= 80) {
            new ParticleBuilder(ParticleEffect.FLAME)
                    .setSpeed(0.25f)
                    .setAmount(2)
                    .setLocation(item.getLocation())
                    .display();
        }

        Timer += 2;
    }
}