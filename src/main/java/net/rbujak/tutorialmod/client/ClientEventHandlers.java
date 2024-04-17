package net.rbujak.tutorialmod.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rbujak.tutorialmod.item.ModItems;
import net.minecraftforge.client.event.RenderHandEvent;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.GameRenderer;
import com.mojang.blaze3d.vertex.VertexFormat;



@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEventHandlers {

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null && mc.player.isUsingItem() && mc.player.getUseItem().getItem() == ModItems.EYE_OF_HELENA.get()) {
            if (event.getOverlay().equals(VanillaGuiOverlay.HOTBAR.type())) {
                PoseStack poseStack = new PoseStack(); // Assuming you have a way to get PoseStack
                tintScreen(poseStack);
                event.setCanceled(true); // Optional: cancel the event to prevent other overlays from rendering
            }
        }
    }

    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null &&
                mc.player.isUsingItem() &&
                mc.player.getUseItem().getItem() == ModItems.EYE_OF_HELENA.get()) {
            event.setCanceled(true);  // Prevents the rendering of the hand and item
        }
    }

    public static void tintScreen(PoseStack poseStack) {
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(0.0f, 128.0f / 255.0f, 128.0f / 255.0f, 0.5f);  // Set color and alpha

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();

        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR); // Proper use of BufferBuilder
        bufferBuilder.vertex(poseStack.last().pose(), 0, Minecraft.getInstance().getWindow().getGuiScaledHeight(), 0).color(0.0f, 128.0f / 255.0f, 128.0f / 255.0f, 0.5f).endVertex();
        bufferBuilder.vertex(poseStack.last().pose(), Minecraft.getInstance().getWindow().getGuiScaledWidth(), Minecraft.getInstance().getWindow().getGuiScaledHeight(), 0).color(0.0f, 128.0f / 255.0f, 128.0f / 255.0f, 0.5f).endVertex();
        bufferBuilder.vertex(poseStack.last().pose(), Minecraft.getInstance().getWindow().getGuiScaledWidth(), 0, 0).color(0.0f, 128.0f / 255.0f, 128.0f / 255.0f, 0.5f).endVertex();
        bufferBuilder.vertex(poseStack.last().pose(), 0, 0, 0).color(0.0f, 128.0f / 255.0f, 128.0f / 255.0f, 0.5f).endVertex();

        tesselator.end();
        RenderSystem.disableBlend();
    }

}
