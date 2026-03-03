package com.lukeonuke.fastleafdecay;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class CascadeBreakUtil {
    public static final IntegerProperty DISTANCE;
    public static final BooleanProperty PERSISTENT;

    public static void cascadeBreak(@NotNull ServerLevel level, @NotNull BlockPos currentPos, boolean breakCurrentBlock){
        cascadeBreakRec(level, currentPos, breakCurrentBlock, new HashSet<>());
    }

    private static void cascadeBreakRec(ServerLevel level, BlockPos currentPos, boolean breakCurrentBlock, Set<BlockPos> visited){
        if(breakCurrentBlock) {
            level.destroyBlock(currentPos, true);
        }
        visited.add(currentPos);

        for (Direction dir : Direction.values()){
            final BlockPos relativePos = currentPos.relative(dir);
            final BlockState relativeState = level.getBlockState(relativePos);

            if(relativeState.is(BlockTags.LEAVES)){
                if (decaying(relativeState) && !visited.contains(relativePos)){
                    level.destroyBlock(relativePos, true);
                    visited.add(relativePos);
                    cascadeBreakRec(level, relativePos, false, visited);
                }
            }
        }
    }

    private static boolean decaying(BlockState blockState) {
        return !(Boolean)blockState.getValue(PERSISTENT) && (Integer)blockState.getValue(DISTANCE) == 7;
    }

    static {
        DISTANCE = BlockStateProperties.DISTANCE;
        PERSISTENT = BlockStateProperties.PERSISTENT;
    }
}
