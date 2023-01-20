package net.dctime.learnmodding.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExampleC2SPacket
{
    public ExampleC2SPacket()
    {

    }

    public ExampleC2SPacket(FriendlyByteBuf buffer)
    {

    }

    public void toByte(FriendlyByteBuf buffer)
    {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        // SERVER SIDE
        context.enqueueWork(() -> {
            EntityType.COW.spawn(supplier.get().getSender().getLevel(), supplier.get().getSender().blockPosition(), MobSpawnType.MOB_SUMMONED);
        });
        return true;
    }



}
