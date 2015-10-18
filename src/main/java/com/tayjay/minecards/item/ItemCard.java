package com.tayjay.minecards.item;

import com.tayjay.minecards.card.Card;
import com.tayjay.minecards.card.CardRegistry;
import com.tayjay.minecards.init.ModItems;
import com.tayjay.minecards.reference.Names;
import com.tayjay.minecards.util.NBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
        ModItems.register(this);
    }

    @Override
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        return super.getIconFromDamage(p_77617_1_);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        //super.getSubItems(item, tabs, list);
        for(int i = 0; i <= CardRegistry.totalCards; ++i){
            list.add(new ItemStack(item,1,i));
        }

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
        return CardRegistry.getCardById(this.getDamage(itemStack)).getCardName();
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {
        Card card = CardRegistry.getCardById(this.getDamage(itemStack));
        //this.setDamage(itemStack, NBTHelper.getInt(itemStack, "id"));
        list.add("ID: "+card.getId());
        list.add("Rarity: "+card.getRarity());
        list.add("Set: " + card.getSetName());
            /*
            String owner = itemStack.stackTagCompound.getString("owner");
            int code = itemStack.stackTagCompound.getInteger("code");
            list.add("owner: " + owner);
            if (owner.equals(player.username)) {
                list.add(EnumChatFormatting.GREEN + "code: " + code);
            } else {
                list.add(EnumChatFormatting.RED + "code: "
                        + EnumChatFormatting.OBFUSCATED + code);
            }
            */


    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if(!world.isRemote)
        {
            Random rand = new Random();
            NBTHelper.setInteger(itemStack, "id", rand.nextInt(2));
            this.setDamage(itemStack,NBTHelper.getInt(itemStack,"id"));
        }
        return super.onItemRightClick(itemStack, world, entityPlayer);
    }
}
