package greenvox.team.ru.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class LocalTransform {
    public static Location Transform;

    public static Location LocalToGlobal(Location local) {
        return Transform.clone().add(local);
    }
    public static Location LocalToGlobal(Vector local) {
        return Transform.clone().add(local);
    }
    public static Location LocalToGlobal(float x, float y, float z) {
        return Transform.clone().add(new Vector(x, y, z));
    }
    public static Location GlobalToLocal(Location global) {
        return Transform.clone().subtract(global);
    }
    public static Location GlobalToLocal(Vector global) {
        return Transform.clone().subtract(global);
    }
    public static Location GlobalToLocal(float x, float y, float z) {
        return Transform.clone().subtract(new Vector(x, y, z));
    }
}
