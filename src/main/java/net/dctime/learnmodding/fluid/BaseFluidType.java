package net.dctime.learnmodding.fluid;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class BaseFluidType extends FluidType
{
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowTexture;
    private final ResourceLocation overlayTexture;
    private final int tintColor;
    private final Vector3f fogColor;

    public BaseFluidType(ResourceLocation stillTexture, ResourceLocation flowTexture, ResourceLocation overlayTexture,
                         int tintColor, Vector3f fogColor, Properties properties)
    {
        super(properties);
        this.stillTexture = stillTexture;
        this.flowTexture = flowTexture;
        this.overlayTexture = overlayTexture;
        this.tintColor = tintColor;
        this.fogColor = fogColor;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer)
    {
        consumer.accept(new IClientFluidTypeExtensions()
        {
            public ResourceLocation getStillTexture()
            {
                return stillTexture;
            }

            public ResourceLocation getFlowingTexture()
            {
                return flowTexture;
            }

            public ResourceLocation getOverlayTexture()
            {
                return overlayTexture;
            }

            public int getTintColor()
            {
                return tintColor;
            }

            public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor)
            {
                return fogColor;
            }

            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape)
            {
                RenderSystem.setShaderFogStart(1f);
                RenderSystem.setShaderFogEnd(6f);
            }
        });
    }

    public ResourceLocation getStillTexture()
    {
        return stillTexture;
    }

    public ResourceLocation getFlowTexture()
    {
        return flowTexture;
    }

    public ResourceLocation getOverlayTexture()
    {
        return overlayTexture;
    }

    public int getTintColor()
    {
        return tintColor;
    }

    public Vector3f getFogColor()
    {
        return fogColor;
    }



}
