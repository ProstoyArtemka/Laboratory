package greenvox.team.ru.util.blocks.animations;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.util.Vector;

public class AnimatableBlock {

    private Block Block;

    public AnimatableBlock(Block block) {
        Block = block;
    }

    public AnimatableBlock SetBlockData(BlockData data) {
        Block.setBlockData(data);

        return this;
    }

    public AnimatableBlock SetBlockType(Material type) {
        Block.setType(type);

        return this;
    }

    public AnimatableBlock MoveBlockPosition(Vector position) {
        BlockData data = Block.getBlockData();
        Location newLocation = Block.getLocation().add(position);

        Block.setType(Material.AIR);
        newLocation.getBlock().setBlockData(data);

        Block = newLocation.getBlock();

        return this;
    }

    public AnimatableBlock SetBlockPosition(Vector position) {
        BlockData data = Block.getBlockData();
        Location newLocation = position.toLocation(Block.getWorld());

        Block.setType(Material.AIR);
        newLocation.getBlock().setBlockData(data);

        Block = newLocation.getBlock();

        return this;
    }

}