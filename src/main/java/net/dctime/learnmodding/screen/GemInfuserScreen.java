package net.dctime.learnmodding.screen;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class GemInfuserScreen extends AbstractContainerScreen<GemInfuserMenu>
{
    public GemInfuserScreen(GemInfuserMenu menu, Inventory inventory, Component component)
    {
        super(menu, inventory, component);
    }

    @Override
    protected void init()
    {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, new ResourceLocation(LearnModdingMod.MODID, "textures/gui/gem_infusing_station_gui.png"));
        this.blit(poseStack, (width-imageWidth)/2, (height-imageHeight)/2, 0, 0, imageWidth, imageHeight);
        this.blit(poseStack, (width-imageWidth)/2+105, (height-imageHeight)/2+33, 176, 0, 8, (int) (26*menu.getProcessPercent()));
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta)
    {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);

    }
}
