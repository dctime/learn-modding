package net.dctime.learnmodding.networking.packet;

import net.dctime.learnmodding.client.ClientThirstData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ThirstDataSyncS2CPacket
{
    private final int thirst;

    public ThirstDataSyncS2CPacket(int thirst)
    {
        this.thirst = thirst;
    }

    public ThirstDataSyncS2CPacket(FriendlyByteBuf buf)
    {
        this.thirst = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(thirst);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            //CLIENT SIDE
            ClientThirstData.setPlayerThirst(this.thirst);
        });
        ctx.get().setPacketHandled(true);
    }
}
