package greenvox.team.ru.util.blocks.animations;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

public class AnimatableFallingBlock {

    private FallingBlock Block;

    public AnimatableFallingBlock(FallingBlock fallingBlock) {
        Block = fallingBlock;

        Block.setGravity(false);
        Block.setDropItem(false);
    }

    public AnimatableFallingBlock SetBlockData(BlockData data) {
        FallingBlock newBlock = Block.getWorld().spawnFallingBlock(Block.getLocation(), data);
        Block.remove();

        Block = newBlock;
        return this;
    }

    public AnimatableFallingBlock SetBlockType(Material type) {
        FallingBlock newBlock = Block.getWorld().spawnFallingBlock(Block.getLocation(), type, (byte) 0);
        Block.remove();

        Block = newBlock;
        return this;
    }

    public AnimatableFallingBlock SetBlockVelocity(Vector velocity) {
        Block.setVelocity(velocity);

        return this;
    }
}
