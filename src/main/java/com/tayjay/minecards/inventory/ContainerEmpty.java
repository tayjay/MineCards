package com.tayjay.minecards.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by tayjm_000 on 2015-12-12.
 */
public class ContainerEmpty extends Container
{
    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return false;
    }
}
