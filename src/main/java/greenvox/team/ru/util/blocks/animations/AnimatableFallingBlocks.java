package greenvox.team.ru.util.blocks.animations;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimatableFallingBlocks {
    private final ArrayList<FallingBlock> Blocks = new ArrayList<>();


    private void setDefaultBlocks() {
        for (FallingBlock block : Blocks) {
            block.setGravity(false);
            block.setDropItem(false);
        }
    }
    public AnimatableFallingBlocks(FallingBlock... blocks) {
        Blocks.addAll(Arrays.asList(blocks));

        setDefaultBlocks();
    }

    public AnimatableFallingBlocks AddFallingBlocks(FallingBlock... blocks) {
        Blocks.addAll(Arrays.asList(blocks));
        setDefaultBlocks();

        return this;
    }

    public AnimatableFallingBlocks SetVelocityForFallingBlocks(Vector velocity) {
        for (FallingBlock block : Blocks)
            block.setVelocity(velocity);

        return this;
    }

    public AnimatableFallingBlocks SetVelocitiesForFallingBlocks(Vector... velocities) {
        for (int i = 0; i < velocities.length; i++) {
            Blocks.get(i).setVelocity(velocities[i]);
        }

        return this;
    }

    private FallingBlock copyFallingBlock(FallingBlock fallingBlock, Material material) {
        FallingBlock newBlock = fallingBlock.getWorld().spawnFallingBlock(fallingBlock.getLocation(), material, (byte) 0);

        newBlock.setGravity(fallingBlock.hasGravity());
        newBlock.setDropItem(fallingBlock.getDropItem());
        newBlock.setVelocity(fallingBlock.getVelocity());
        newBlock.setGlowing(fallingBlock.isGlowing());

        return newBlock;
    }

    public AnimatableFallingBlocks SetTypesForFallingBlocks(Material... types) {
        for (int i = 0; i < types.length; i++) {
            FallingBlock b = Blocks.get(i);

            FallingBlock newBlock = copyFallingBlock(b, types[i]);
            Blocks.set(i, newBlock);

            b.remove();
        }

        return this;
    }

    public AnimatableFallingBlocks SetTypeForFallingBlocks(Material type) {
        for (int i = 0; i < Blocks.size(); i++) {
            FallingBlock b = Blocks.get(i);

            FallingBlock newBlock = copyFallingBlock(b, type);
            Blocks.set(i, newBlock);

            b.remove();
        }

        return this;
    }

    public Location GetBlockLocation(int index) {
        return Blocks.get(index).getLocation();
    }
}
