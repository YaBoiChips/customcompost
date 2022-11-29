package yaboichips.customcompost.mixin;


import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yaboichips.customcompost.CompostConfig;

import java.util.List;

@Mixin(ComposterBlock.class)
public class ComposterBlockMixin {


    @Redirect(method = "extractProduce", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private static boolean doThing(Level world, Entity entity) {
        List<? extends String> drops = CompostConfig.drops.get();
        ((ItemEntity) entity).setItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation(drops.get(world.random.nextInt(drops.size())))).getDefaultInstance());
        return world.addFreshEntity(entity);
    }

    @Inject(method = "getContainer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/ComposterBlock$OutputContainer;<init>(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)V"), cancellable = true)
    private void changeInventory(BlockState state, LevelAccessor level, BlockPos pos, CallbackInfoReturnable<WorldlyContainer> cir) {
        List<? extends String> drops = CompostConfig.drops.get();
        cir.setReturnValue(new ComposterBlock.OutputContainer(state, level, pos, ForgeRegistries.ITEMS.getValue(new ResourceLocation(drops.get(level.getRandom().nextInt(drops.size())))).getDefaultInstance()));
    }
}
