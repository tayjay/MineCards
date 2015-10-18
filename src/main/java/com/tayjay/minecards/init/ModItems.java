package com.tayjay.minecards.init;

import com.tayjay.minecards.item.ItemCard;
import com.tayjay.minecards.item.ItemMC;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by tayjm_000 on 2015-10-18.
 */
public class ModItems
{
    public static ItemMC card;

    public static void init()
    {
        card = new ItemCard();
    }

    /**
     * Try to register an item.
     *
     * @param item  Item to register.
     */
    public static void register(final ItemMC item)
    {
        String name = item.getUnwrappedUnlocalizedName(item.getUnlocalizedName());
        if(isEnabled(item)) GameRegistry.registerItem(item, name.substring(name.indexOf(":") + 1));
    }

    /**
     * Check if an item can be registered.
     *
     * @param item  Item to check.
     * @return      Item is NOT on blacklist.
     */
    public static boolean isEnabled(Item item)
    {
        //return !ConfigHandler.disabledNamesList.contains(item.getUnlocalizedName());
        return true;
    }
}
