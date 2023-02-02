package net.dctime.learnmodding.block.custom;

import net.dctime.learnmodding.block.entity.GemInfusingStationBlockEntity;
import net.dctime.learnmodding.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class GemInfusingStationBlock extends BaseEntityBlock
{
    public GemInfusingStationBlock(BlockBehaviour.Properties properties)
    {
        super(properties);

    }

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    // set the block state when the block is being placed
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext)
    {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    // default doesn't rotate at all
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    // default doesn't mirror at all
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder)
    {
        stateBuilder.add(FACING);
    }

    // set the hitbox size
    public VoxelShape getShape(BlockState p_50952_, BlockGetter p_50953_, BlockPos p_50954_, CollisionContext p_50955_) {
        return Shapes.or(Block.box(0, 4, 0, 16, 8, 16),
                Block.box(0, 0, 0, 2, 4, 2),
                Block.box(14, 0, 0, 16, 4, 2),
                Block.box(14, 0, 14, 16, 4, 16),
                Block.box(0, 0, 14, 2, 4, 16));
    }

    /* Block Entity Related*/


    // default is invisible and i dont know why
    @Override
    public RenderShape getRenderShape(BlockState p_49232_)
    {
        return RenderShape.MODEL;
    }

    // when the block is destroyed, pop everything out of the inventory
    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newBlockState, boolean idkBool)
    {
        if (blockState.getBlock() != newBlockState.getBlock())
        {
            BlockEntity targetBlockEntity = level.getBlockEntity(blockPos);
            if (targetBlockEntity instanceof GemInfusingStationBlockEntity)
            {
                ((GemInfusingStationBlockEntity) targetBlockEntity).dropEverythingWhenBreak();
            }
        }
        super.onRemove(blockState, level, blockPos, newBlockState, idkBool);
    }

    // when it is right-clicked, open a screen
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
                                 InteractionHand interactionHand, BlockHitResult blockHitResult)
    {
        if (!level.isClientSide())
        {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof GemInfusingStationBlockEntity)
            {
                NetworkHooks.openScreen((ServerPlayer) player, (GemInfusingStationBlockEntity) blockEntity, blockPos);
            }
            else
            {
                throw new IllegalStateException("FUCK YOU GEM INFUSER BLOCK INTERACTION RESULT HAS POOP IN IT");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    // let the block know that it has a block entity
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return new GemInfusingStationBlockEntity(blockPos, blockState);
    }

    // get the function which runs at every tick
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType)
    {
        // createTickerHelper 3rd arg => functional interface => a lambda function
        return createTickerHelper(blockEntityType, ModBlockEntities.GEM_INFUSER_BLOCK_ENTITY.get(), GemInfusingStationBlockEntity::tick);
    }
}
