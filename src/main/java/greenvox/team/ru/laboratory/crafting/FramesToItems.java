package greenvox.team.ru.laboratory.crafting;

import greenvox.team.ru.Main;
import greenvox.team.ru.util.LocalTransform;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.GlowItemFrame;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.ArrayList;

public class FramesToItems extends BukkitRunnable {

    private static final Location FramesCenter = LocalTransform.LocalToGlobal(50, 67, 15);
    private final ArrayList<GlowItemFrame> Frames;
    private final ArrayList<Item> Items = new ArrayList<>();
    private int index = 0;

    public FramesToItems() {
        Frames = (ArrayList<GlowItemFrame>) FramesCenter.getNearbyEntitiesByType(GlowItemFrame.class, 7);
    }

    private void spawnItem(Location location, ItemStack stack) {
        Item i = location.getWorld().dropItem(location, stack);

        i.setVelocity(new Vector());
        i.setGravity(false);

        i.setItemStack(stack);
        i.setUnlimitedLifetime(true);
        i.setGlowing(true);

        i.setCanMobPickup(false);
        i.setCanPlayerPickup(false);
        i.setPickupDelay(Integer.MAX_VALUE);


        i.setInvulnerable(true);
        i.setSilent(true);

        new ParticleBuilder(ParticleEffect.CRIT)
                .setAmount(20)
                .setSpeed(0.75f)
                .setLocation(i.getLocation())
                .display();

        i.getWorld().playSound(i.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);

        new ParticlesAroundItems(i).runTaskTimer(Main.Instance, 0, 2);

        Items.add(i);
    }

    @Override
    public void run() {
        if (index >= Frames.size()) {

            new ItemsMoveUp(Items).runTaskTimer(Main.Instance, 15, 15);

            cancel();
            return;
        }

        GlowItemFrame frame = Frames.get(index);
        spawnItem(frame.getLocation().add(0,1, 0), frame.getItem());

        index++;
    }
}
class ParticlesAroundItems extends BukkitRunnable {

    private Item item;

    public ParticlesAroundItems(Item item) {
        this.item = item;
    }

    @Override
    public void run() {
        if ((item.getVelocity().getX() != 0 || item.getVelocity().getZ() != 0)) {
            cancel();
            return;
        }

        new ParticleBuilder(ParticleEffect.CRIT_MAGIC)
                .setSpeed(0)
                .setLocation(item.getLocation().add(0, 0.25, 0))
                .setOffset(0.15f, 0.15f, 0.15f)
                .setAmount(2)
                .display();
    }
}