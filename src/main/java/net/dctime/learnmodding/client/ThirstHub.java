package net.dctime.learnmodding.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ThirstHub implements IGuiOverlay
{
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, new ResourceLocation(LearnModdingMod.MODID, "textures/thirst/filled_thirst.png"));

        for (int bottleCounter = 1; bottleCounter <= 10 - ClientThirstData.getPlayerThirst(); bottleCounter++)
        {
            GuiComponent.blit(poseStack, screenWidth / 2 + 8 * bottleCounter, screenHeight - 50, 0, 0, 9, 9, 9, 9);
        }

        RenderSystem.setShaderTexture(0, new ResourceLocation(LearnModdingMod.MODID, "textures/thirst/empty_thirst.png"));

        for (int bottleCounter = 10 - ClientThirstData.getPlayerThirst() + 1; bottleCounter <= 10; bottleCounter++)
        {
            GuiComponent.blit(poseStack, screenWidth / 2 + 8 * bottleCounter, screenHeight - 50, 0, 0, 9, 9, 9, 9);
        }






    }

}
