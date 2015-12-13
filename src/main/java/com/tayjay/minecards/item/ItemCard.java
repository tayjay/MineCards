package com.tayjay.minecards.item;

import com.tayjay.minecards.GuiHandler;
import com.tayjay.minecards.MineCards;
import com.tayjay.minecards.card.Card;
import com.tayjay.minecards.card.CardRegistry;
import com.tayjay.minecards.init.ModItems;
import com.tayjay.minecards.reference.Names;
import com.tayjay.minecards.util.NBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by tayjm_000 on 2015-10-18.
 */
public class ItemCard extends ItemMC
{
    public ItemCard()
    {
        super();
        this.setUnlocalizedName(Names.Items.CARD);
        this.maxStackSize = 1;
        setHasSubtypes(true); // This allows the item to be marked as a metadata item.
        setMaxDamage(0); // This makes it so your item doesn't have the damage bar at the bottom of its icon, when "damaged" similar to the Tools.
        setCreativeTab(CreativeTabs.tabMisc);
        ModItems.register(this);
    }

    /*
    @Override
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        return super.getIconFromDamage(p_77617_1_);
    }
    */

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {

        for(Card card: Card.cards.values())
        {
            ItemStack i = new ItemStack(this);
            card.writeToItemStack(i);
            list.add(i);
        }

        //super.getSubItems(item,tabs,list);

    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        super.onUpdate(itemStack, world, entity, p_77663_4_, p_77663_5_);

        //this.setDamage(itemStack, NBTHelper.getInt(itemStack, "id"));
        /*
        id = NBTHelper.getInt(itemStack, "id");
        cardName = NBTHelper.getString(itemStack, "cardName");
        rarity = NBTHelper.getInt(itemStack, "rarity");
        setName = NBTHelper.getString(itemStack, "setName");
        */
    }


    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        String name;
        return (name = NBTHelper.getString(itemStack,"cardName"))==null ? "null" : name;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {
        String rarity = NBTHelper.getString(itemStack, "rarityName");
        String setName = NBTHelper.getString(itemStack,"setName");
        list.add("Rarity: " + rarity);
        list.add("Set: " + setName);


    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if(world.isRemote)
        {
            entityPlayer.setCurrentItemOrArmor(1, new ItemStack(Items.diamond_sword));
            entityPlayer.openGui(MineCards.instance, GuiHandler.GuiIDs.CARD.ordinal(),world,(int)entityPlayer.posX,(int)entityPlayer.posY,(int)entityPlayer.posZ);
        }
        return super.onItemRightClick(itemStack, world, entityPlayer);
    }
}
