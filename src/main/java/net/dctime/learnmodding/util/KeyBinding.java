package net.dctime.learnmodding.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

public class KeyBinding
{
    public static final KeyMapping DRINKING_KEY = new KeyMapping("key.learnmodding.drinkingkey", InputConstants.Type.KEYSYM,
            InputConstants.KEY_O, "key.categories.learnmodding.learn");
}
