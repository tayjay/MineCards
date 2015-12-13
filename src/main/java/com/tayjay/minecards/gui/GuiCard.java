package com.tayjay.minecards.gui;

import com.tayjay.minecards.card.Card;
import com.tayjay.minecards.inventory.ContainerEmpty;
import com.tayjay.minecards.reference.Reference;
import com.tayjay.minecards.util.NBTHelper;
import com.tayjay.minecards.util.RenderUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by tayjm_000 on 2015-12-12.
 */
public class GuiCard extends GuiContainer
{

    private ResourceLocation guiTexture;
    private EntityPlayer player;
    private ItemStack heldItem;
    private Card card;
    private ItemStack itemToRender;
    private int rotateEntity;
    private EntityLiving entityLiving;
    String cardName;
    String rarityName;
    String setName;

    public GuiCard(Container p_i1072_1_)
    {
        super(p_i1072_1_);
    }

    public GuiCard(EntityPlayer player, String guiTextureName)
    {
        super(new ContainerEmpty());
        //todo Implement this so that the rarity will dictate which background to use
        guiTexture = new ResourceLocation(Reference.MOD_ID_LOWER + ":textures/gui/" + guiTextureName + ".png");
        xSize=185;
        ySize=255;
        this.player = player;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        heldItem = player.getHeldItem();
        card = Card.cards.get(NBTHelper.getString(heldItem,"cardName"));

        itemToRender = card.getItemForCard();
        entityLiving = card.getEntityLivingForCard();

        cardName = card.getCardName();
        rarityName = card.getRarityName();
        setName = card.getSetName();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        super.drawGuiContainerForegroundLayer(p_146979_1_, p_146979_2_);

        fontRendererObj.drawString(card.getCardName(), 18, 16, 0);
        fontRendererObj.drawSplitString("Set: " + setName + "\n\nRarity: " + rarityName, 28, 123,xSize-60, 4210752);
        fontRendererObj.drawSplitString("This will be the description of the item. I'll probably put this in the localization file.", 28, 170, xSize - 60, 4210752);

        if(itemToRender!=null)
        {
            RenderUtil.drawItemStack(itemToRender,xSize/3-5,ySize/5-30);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        //Bind GUI Texture to the Background Layer
        mc.getTextureManager().bindTexture(guiTexture);
        //Starting Pos on screen left, top, Starting pos of texture left, top, xSize,ySize
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if(entityLiving!=null)
        {
            RenderUtil.drawEntityLiving(guiLeft + xSize / 2, guiTop + ySize / 3+16, 40, 80, -20, this.entityLiving);
        }
    }
}
