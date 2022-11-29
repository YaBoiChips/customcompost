package yaboichips.customcompost.mixin;


import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ComposterBlock.OutputContainer.class)
public class ComposterBlockInvMixin {

    /**
     * @author yaboichips
     * @reason changed drops
     */
    @Overwrite
    public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
        return !((ChangedAccessor)this).getChanged() && p_180461_3_ == Direction.DOWN;
    }
    @Mixin(ComposterBlock.OutputContainer.class)
    public interface ChangedAccessor{
        @Accessor("changed")
        boolean getChanged();
    }
}
