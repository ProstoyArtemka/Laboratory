package greenvox.team.ru.laboratory.door;

import com.destroystokyo.paper.ParticleBuilder;
import greenvox.team.ru.util.LocalTransform;
import greenvox.team.ru.util.blocks.animations.BlocksAnimation;
import greenvox.team.ru.util.playsound.SoundPlayer;
import greenvox.team.ru.util.playsound.SoundType;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class OutsideDoorAnimation extends BlocksAnimation {

    private static final Location[] blockLocations = new Location[16];

    public static boolean DoorClosed = true;

    static {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                blockLocations[x + (y * 4)] = LocalTransform.LocalToGlobal(35 + x, 68 + y, 40);
            }
        }
    }
    public OutsideDoorAnimation() {
        super(20, blockLocations);
    }

    private void playParticles() {
        for (int x = 0; x < 4; x++) {
            Location builderLocation = LocalTransform.LocalToGlobal(35.5f + x, 68.1f, 40f);

            ParticleBuilder builder = new ParticleBuilder(Particle.CRIT)
                    .allPlayers()
                    .count(5).offset(0.4f, 0.4f, 0.4f)
                    .location(builderLocation);

            builder.spawn();
        }
    }

    private void moveDown() {
        if (AnimatingBlocks.GetBlockLocation(15).getY() >= LocalTransform.LocalToGlobal(0, 68, 0).getY()) {
            AnimatingBlocks.MoveBlocks(new Vector(0, -1, 0));

            SoundPlayer.play(SoundType.PISTON_CONTRACT, AnimatingBlocks.GetBlockLocation(8), 1, 1);
            playParticles();
        }

        DoorClosed = false;
    }

    private void moveUp() {
        if (AnimatingBlocks.GetBlockLocation(0).getY() <= LocalTransform.LocalToGlobal(0, 67, 0).getY()) {
            AnimatingBlocks.MoveBlocks(new Vector(0, 1, 0));

            SoundPlayer.play(SoundType.PISTON_EXTEND, AnimatingBlocks.GetBlockLocation(8), 1, 1);
            playParticles();
        }

        DoorClosed = true;
    }

    @Override
    public void run() {
        if (LocalTransform.LocalToGlobal(39, 69, 43).getBlock().getBlockPower() != 0)
            moveDown();
        else
            moveUp();
    }
}
