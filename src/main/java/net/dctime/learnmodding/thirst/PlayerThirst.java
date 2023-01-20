package net.dctime.learnmodding.thirst;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PlayerThirst
{
    private int thirst = 0;

    public int getThirst()
    {
        return thirst;
    }

    public void setThirst(int input)
    {
        thirst = input;
    }

    public void saveNBTData(CompoundTag nbt)
    {
        nbt.putInt("thirst", thirst);
    }

    public void loadNBTData(CompoundTag nbt)
    {
        thirst = nbt.getInt("thirst");
    }

    public void copyFrom(PlayerThirst source)
    {
        source.thirst = this.thirst;
    }

}
