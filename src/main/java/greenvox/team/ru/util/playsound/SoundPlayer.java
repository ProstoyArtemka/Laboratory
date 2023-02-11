package greenvox.team.ru.util.playsound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Collection;

public class SoundPlayer {

    public static void play(SoundType soundType, Player player, float volume, float pitch) {
        Location location = player.getEyeLocation();
        execute(soundType, player, location, volume, pitch);
    }

    public static void play(SoundType soundType, Player player, Location location, float volume, float pitch) {
        execute(soundType, player, location, volume, pitch);
    }

    public static void play(SoundType soundType, Collection<? extends Player> players, float volume, float pitch) {
        for(Player player: players){
            execute(soundType, player, player.getLocation(), volume, pitch);
        }
    }

    public static void play(SoundType soundType, Collection<? extends Player> players, Location location,
                            float volume, float pitch) {
        for(Player player: players){
            execute(soundType, player, location, volume, pitch);
        }
    }

    private static void execute(SoundType soundType, Player player, Location location, float volume, float pitch){

        switch (soundType) {
            case BASALT_DELTAS:
                player.playSound(location, Sound.AMBIENT_BASALT_DELTAS_LOOP, volume, pitch );
                break;

            case PISTON_EXTEND:
                player.playSound(location, Sound.BLOCK_PISTON_EXTEND, volume, pitch );
                break;

            case PISTON_CONTRACT:
                player.playSound(location, Sound.BLOCK_PISTON_CONTRACT, volume, pitch );
                break;

            case SOUL_SAND_VALLEY_MOOD:
                player.playSound(location, Sound.AMBIENT_SOUL_SAND_VALLEY_MOOD, volume, pitch );
                break;

            case SOUL_SAND_VALLEY_ADDITIONS:
                player.playSound(location, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, volume, pitch );
                break;

            case WARDEN_HEARTBEAT:
                player.playSound(location, Sound.ENTITY_WARDEN_HEARTBEAT, volume, pitch );
                break;

        }
    }
}
