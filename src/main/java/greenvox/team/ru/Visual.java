package greenvox.team.ru;

import com.destroystokyo.paper.ParticleBuilder;
import greenvox.team.ru.util.LocalTransform;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.particle.ParticleEffect;

public class Visual extends BukkitRunnable {

    private static final Location enchantLocation = LocalTransform.LocalToGlobal(52,69.5f, 27);
    private static final Location waterLocation = LocalTransform.LocalToGlobal(60.6f, 70.8f, 8.5f);

    @Override
    public void run() {
        enchant();
        water();
    }

    private static void enchant(){
        ParticleBuilder builder = new ParticleBuilder(Particle.ENCHANTMENT_TABLE)
                .allPlayers()
                .count(100).offset(5, 1.5, 3)
                .location(enchantLocation);
        builder.spawn();

    }

    private static void water(){
        ParticleBuilder builder = new ParticleBuilder(Particle.DRIP_WATER)
                .allPlayers()
                .count(7).offset(1, 0, 0.3f)
                .location(waterLocation);
        builder.spawn();
    }
}
