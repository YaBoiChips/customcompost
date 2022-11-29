package yaboichips.customcompost.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
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


    @Redirect(method = "extractProduce", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addFreshEntity(Lnet/minecraft/entity/Entity;)Z"))
    private static boolean doThing(World world, Entity entity) {
        List<? extends String> drops = CompostConfig.drops.get();
        ((ItemEntity) entity).setItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation(drops.get(world.random.nextInt(drops.size())))).getDefaultInstance());
        return world.addFreshEntity(entity);
    }

    @Inject(method = "getContainer", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/ComposterBlock$FullInventory;<init>(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/IWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;)V"), cancellable = true)
    private void changeInventory(BlockState p_219966_1_, IWorld world, BlockPos p_219966_3_, CallbackInfoReturnable<ISidedInventory> cir) {
        List<? extends String> drops = CompostConfig.drops.get();
        cir.setReturnValue(new ComposterBlock.FullInventory(p_219966_1_, world, p_219966_3_, ForgeRegistries.ITEMS.getValue(new ResourceLocation(drops.get(world.getRandom().nextInt(drops.size())))).getDefaultInstance()));
    }
}
