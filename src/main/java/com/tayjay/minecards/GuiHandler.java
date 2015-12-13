package com.tayjay.minecards;

import com.tayjay.minecards.gui.GuiCard;
import com.tayjay.minecards.inventory.ContainerEmpty;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-12-12.
 */
public class GuiHandler implements IGuiHandler
{
    public enum GuiIDs
    {
        CARD
    }
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (GuiIDs.values()[ID])
        {
            case CARD:
                return new ContainerEmpty();
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (GuiIDs.values()[ID])
        {
            case CARD:
                return new GuiCard(player,"card");
        }
        return null;
    }
}
