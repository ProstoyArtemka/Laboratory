package greenvox.team.ru.laboratory.door;

import com.destroystokyo.paper.ParticleBuilder;
import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.util.LocalTransform;
import greenvox.team.ru.util.blocks.animations.BlocksAnimation;
import greenvox.team.ru.util.playsound.SoundPlayer;
import greenvox.team.ru.util.playsound.SoundType;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class InnerDoorAnimation extends BlocksAnimation {

    private static int timer = 4;
    private static final Location[] blockLocations = new Location[16];

    static {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                blockLocations[x + (y * 4)] = LocalTransform.LocalToGlobal(35 + x, 68 + y, 34);
            }
        }
    }
    public InnerDoorAnimation() {
        super(20, blockLocations);
    }

    private void playParticles() {
        for (int x = 0; x < 4; x++) {
            Location builderLocation = LocalTransform.LocalToGlobal(35.5f + x, 68.1f + timer, 34f);

            ParticleBuilder builder = new ParticleBuilder(Particle.REDSTONE)
                    .allPlayers()
                    .count(7).offset(0.4f, 0.4f, 0.4f)
                    .color(Color.WHITE)
                    .location(builderLocation);

            builder.spawn();
        }
    }

    private void moveDown() {
        if (AnimatingBlocks.GetBlockLocation(15).getY() >= LocalTransform.LocalToGlobal(0, 68, 0).getY()) {
            AnimatingBlocks.MoveBlocks(new Vector(0, -1, 0));

            SoundPlayer.play(SoundType.FIRE_EXTINGUISH, AnimatingBlocks.GetBlockLocation(8), 1, 1.4f);
            playParticles();
            timer--;
        }
    }

    private void moveUp() {
        if (AnimatingBlocks.GetBlockLocation(0).getY() <= LocalTransform.LocalToGlobal(0, 67, 0).getY()) {
            AnimatingBlocks.MoveBlocks(new Vector(0, 1, 0));

            SoundPlayer.play(SoundType.FIRE_EXTINGUISH, AnimatingBlocks.GetBlockLocation(8), 1, 1.4f);
            timer++;
            playParticles();

        }
    }

    private static boolean IsInfectedPlayerBetweenDoors() {
        boolean result = false;
        Location location = LocalTransform.LocalToGlobal(36, 69, 37);

        for (Player p : location.getNearbyPlayers(4)) {
            if (DatabaseManager.isPlayerIsInfected(p)) result = true;
        }

        if (location.getNearbyPlayers(4).size() == 0) return true;

        return result;
    }

    @Override
    public void run() {
        if (OutsideDoorAnimation.DoorClosed && !IsInfectedPlayerBetweenDoors()) {
            moveDown();

            LocalTransform.LocalToGlobal(41, 69, 37).getBlock().setType(Material.REDSTONE_BLOCK);
        } else {
            moveUp();

            LocalTransform.LocalToGlobal(41, 69, 37).getBlock().setType(Material.AIR);
        }
    }
}
