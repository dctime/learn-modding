package net.dctime.learnmodding.block.custom;

import net.dctime.learnmodding.item.ModItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BlueBerryCropBlock extends CropBlock
{
    public static final int MAX_AGE = 6;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);

    public BlueBerryCropBlock(BlockBehaviour.Properties properties)
    {
        super(properties);
    }

    public int getMaxAge() {
        return MAX_AGE;
    }

    protected ItemLike getBaseSeedId() {
        return ModItems.BLUEBERRY_SEED.get();
    }
}
