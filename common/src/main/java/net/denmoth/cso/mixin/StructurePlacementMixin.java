package net.denmoth.cso.mixin;

import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.denmoth.cso.config.CSOConfig;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.core.Vec3i;

@Mixin(RandomSpreadStructurePlacement.class)
public class StructurePlacementMixin {
    @Mutable @Shadow private int spacing;
    @Mutable @Shadow private int separation;

    @Inject(method = "<init>(IILnet/minecraft/world/level/levelgen/structure/placement/RandomSpreadType;I)V", at = @At("RETURN"))
    private void onConstruct(int spacing, int separation, RandomSpreadType type, int salt, CallbackInfo ci) {
        // 45141315208 is OVERWORLD, 2091392285 is NETHER
        if (salt == 45141315208L || salt == 2091392285) {
            this.spacing = CSOConfig.INSTANCE.defaultSpacing;
            this.separation = CSOConfig.INSTANCE.defaultSeparation;
        }
    }
}
