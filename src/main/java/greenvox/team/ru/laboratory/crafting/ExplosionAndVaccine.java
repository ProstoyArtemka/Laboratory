package greenvox.team.ru.laboratory.crafting;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class ExplosionAndVaccine {

    private void spawnVaccine(Location l) {
        Item vaccine = l.getWorld().dropItem(l, new ItemStack(Material.APPLE, 4));

        vaccine.setSilent(true);
        vaccine.setVelocity(new Vector(0, -0.01f, 0));
        vaccine.setInvulnerable(true);
        vaccine.setGravity(false);
        vaccine.setGlowing(true);
        vaccine.setUnlimitedLifetime(true);
    }

    public ExplosionAndVaccine(ArrayList<Item> items) {
        Item f = items.get(0);

        f.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, f.getLocation(), 1);

        spawnVaccine(f.getLocation());

        for (Item i : items) {
            i.remove();
        }
    }
}
