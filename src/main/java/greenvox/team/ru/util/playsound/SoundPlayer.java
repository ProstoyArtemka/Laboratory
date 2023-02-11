package greenvox.team.ru.util.playsound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Collection;

public class SoundPlayer {

    public static void play(SoundType soundType, Player player) {

        Location location = player.getEyeLocation();
        execute(soundType, player, location);

    }

    public static void play(SoundType soundType, Player player, Location location){

        execute(soundType, player, location);

    }

    public static void play(SoundType soundType, Collection<? extends Player> players){

        for(Player player: players){
            execute(soundType, player, player.getLocation());
        }

    }

    public static void play(SoundType soundType, Collection<? extends Player> players, Location location){

        for(Player player: players){
            execute(soundType, player, location);
        }

    }

    private static void execute(SoundType soundType, Player player, Location location){

        switch (soundType) {

            case BASALT_DELTAS:
                player.playSound(location, Sound.AMBIENT_BASALT_DELTAS_LOOP, 1, 1 );
                break;

            case PISTON_EXTEND:
                player.playSound(location, Sound.BLOCK_PISTON_EXTEND, 1, 1 );
                break;

            case PISTON_CONTRACT:
                player.playSound(location, Sound.BLOCK_PISTON_CONTRACT, 1, 1 );
                break;

            case SOUL_SAND_VALLEY_MOOD:
                player.playSound(location, Sound.AMBIENT_SOUL_SAND_VALLEY_MOOD, 1, 1 );
                break;

            case SOUL_SAND_VALLEY_ADDITIONS:
                player.playSound(location, Sound.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 1, 1 );
                break;

            case WARDEN_HEARTBEAT:
                player.playSound(location, Sound.ENTITY_WARDEN_HEARTBEAT, 1, 1 );
                break;

        }
    }
}
