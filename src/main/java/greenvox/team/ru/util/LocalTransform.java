package greenvox.team.ru.util;

import org.bukkit.Location;

public class LocalTransform {
    public static Location Transform;

    public static Location LocalToGlobal(Location local) {
        return Transform.clone().add(local);
    }

    public static Location GlobalToLocal(Location global) {
        return Transform.clone().subtract(global);
    }
}
