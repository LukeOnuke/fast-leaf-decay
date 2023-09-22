package com.lukeonuke.fastleafdecay.event;

import com.lukeonuke.fastleafdecay.FastLeafDecay;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakEventListener implements Listener {
    ArrayList<BlockFace> neighbours = new ArrayList<>(List.of(BlockFace.values()));

    public BlockBreakEventListener() {
        neighbours.remove(BlockFace.SELF);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(@NotNull BlockBreakEvent event) {
        final Block block = event.getBlock();

        if (Tag.LEAVES.isTagged(block.getType())) {
            breakLeaf(block, isValidLeaf(block));
        }

        if (Tag.LOGS.isTagged(block.getType())) {
            breakLeaf(block, false);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onLeavesDecay(LeavesDecayEvent event) {
        breakLeaf(event.getBlock(), isValidLeaf(event.getBlock()));
    }

    public void breakLeaf(Block block, boolean breakFirstBlock) {
        if(breakFirstBlock) block.breakNaturally();

        neighbours.forEach(neighbour -> {
            Block neighbourBlock = block.getRelative(neighbour);
            if(!isValidLeaf(neighbourBlock)) return;
            breakLeaf(neighbourBlock, true);
        });
    }

    boolean isValidLeaf(Block block){
        if(!(block.getBlockData() instanceof Leaves leafBlock)) return false;
        if(leafBlock.getDistance() < 7) return false;
        if(leafBlock.isPersistent()) return false;
        return true;
    }
}
