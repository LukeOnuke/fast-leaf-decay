package com.lukeonuke.fastleafdecay.mixin;

import com.lukeonuke.fastleafdecay.CascadeBreakUtil;
import com.lukeonuke.fastleafdecay.FastLeafDecay;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @Inject(method = "sendBlockUpdated", at = @At("TAIL"))
    public void onBlockUpdated(BlockPos blockPos, BlockState oldState, BlockState newState, int i, CallbackInfo ci) {
        // Check prerequisites
        if (!newState.isAir()) return;  // exit if new state isn't air
        if (oldState.isAir()) return;   // exit if old state is air

        final ServerLevel level = (ServerLevel) (Object) this; // Required fuckery

        // Test
        if (oldState.is(BlockTags.LEAVES)){
            CascadeBreakUtil.cascadeBreak(level, blockPos, true);
        } else if (oldState.is(BlockTags.LOGS)){
            CascadeBreakUtil.cascadeBreak(level, blockPos, false);
        }
    }
}
