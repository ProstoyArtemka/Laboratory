package greenvox.team.ru.util.blocks.animations;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimatableBlocks {

    private ArrayList<Block> Blocks = new ArrayList<>();

    public AnimatableBlocks(Block... blocks) {
        Blocks.addAll(Arrays.asList(blocks));
    }

    public AnimatableBlocks AddBlock(Block block) {
        Blocks.add(block);

        return this;
    }

    public AnimatableBlocks SetType(Material type) {
        for (Block b : Blocks)
            b.setType(type);

        return this;
    }

    public AnimatableBlocks SetTypes(Material... types) {
        for (int i = 0; i < Blocks.size(); i++) {
            Blocks.get(i).setType(types[i]);
        }

        return this;
    }

    public AnimatableBlocks MoveBlocks(Vector velocity) {
        ArrayList<PBlock> blocks = new ArrayList<>();

        for (Block b : Blocks) {
            blocks.add(new PBlock(b.getLocation().add(velocity), b.getType()));

            b.setType(Material.AIR);
        }

        for (int i = 0; i < Blocks.size(); i++) {
            PBlock pBlock = blocks.get(i);

            pBlock.Location.getBlock().setType(pBlock.Material);

            Blocks.set(i, pBlock.Location.getBlock());
        }

        return this;
    }

    public AnimatableBlocks SetPositionToBlocks(Vector position) {
        for (int i = 0; i < Blocks.size(); i++) {
            Block block = Blocks.get(i);
            Block newBlock = position.toLocation(block.getWorld()).getBlock();

            newBlock.setType(block.getType());
            Blocks.set(i, newBlock);

            block.setType(Material.AIR);
        }

        return this;
    }

    public Location GetBlockLocation(int index) {
        return Blocks.get(index).getLocation();
    }
}
class PBlock {
    public Material Material;
    public Location Location;

    public PBlock(Location location, Material material) {
        Material = material;
        Location = location;
    }
}