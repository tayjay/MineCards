package com.tayjay.minecards.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL20;

/**
 * Created by tayjm_000 on 2015-12-12.
 */
public class RenderUtil
{
    private static Minecraft mc = Minecraft.getMinecraft();
    private static RenderItem itemRender = new RenderItem();
    private static FontRenderer fontRendererObj = mc.fontRenderer;

    /**
     * Initialize the RenderUtil Class in one of the Initialization Phases of mod loading.
     */
    public static void init()
    {
        mc = Minecraft.getMinecraft();
        itemRender = new RenderItem();
        fontRendererObj = mc.fontRenderer;
    }

    public static void drawItemStack(ItemStack itemStack, int x, int y)
    {
        drawItemStack(itemStack,x,y,4f,25f);
    }

    /**
     * Draw an itemstack onto the screen
     * @param itemStack     ItemStack to draw
     * @param x             x position on screen
     * @param y             y position on screen
     */
    public static void drawItemStack(ItemStack itemStack, int x, int y,float scale, float rotate)
    {
        //todo: Dynamic Scaling, positioning, custom layer
        GL11.glPushMatrix();
        float defaultzLevel = itemRender.zLevel;
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(rotate,0,1,0);
        itemRender.zLevel = 10.0F;
        FontRenderer font = null;
        if (itemStack != null) font = itemStack.getItem().getFontRenderer(itemStack);
        if (font == null) font = fontRendererObj;
        itemRender.renderItemIntoGUI(font, mc.getTextureManager(), itemStack, x / (int) (scale), y / (int) (scale));
        itemRender.zLevel = 0.0F;
        itemRender.zLevel = defaultzLevel;
        GL11.glPopMatrix();
    }

    /**
     * Default code from Minecraft's GuiInventory to render the player on the screen<br/>
     * Seems to work best when called in the drawBackground method of the GUI
     * @param x         x position on screen
     * @param y         y position on screen
     * @param scale     scale of the entity
     * @param yaw       rotational yaw of the entity
     * @param pitch     rotational pitch of the entity
     * @param entity    entity to be rendered
     */
    public static void drawEntityLiving(int x, int y, int scale, float yaw, float pitch, EntityLivingBase entity)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, 50.0F);
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = entity.renderYawOffset;
        float f3 = entity.rotationYaw;
        float f4 = entity.rotationPitch;
        float f5 = entity.prevRotationYawHead;
        float f6 = entity.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(pitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        entity.renderYawOffset = (float)Math.atan((double)(yaw / 40.0F)) * 20.0F;
        entity.rotationYaw = (float)Math.atan((double)(yaw / 40.0F)) * 40.0F;
        entity.rotationPitch = -((float)Math.atan((double)(pitch / 40.0F))) * 20.0F;
        entity.rotationYawHead = entity.rotationYaw;
        entity.prevRotationYawHead = entity.rotationYaw;
        GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        entity.renderYawOffset = f2;
        entity.rotationYaw = f3;
        entity.rotationPitch = f4;
        entity.prevRotationYawHead = f5;
        entity.rotationYawHead = f6;
        GL11.glPopMatrix();
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
