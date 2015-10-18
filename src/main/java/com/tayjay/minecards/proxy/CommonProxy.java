package com.tayjay.minecards.proxy;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by tayjm_000 on 2015-10-18.
 */
public abstract class CommonProxy
{
    public abstract void preInit();
    public abstract void init();
    public abstract void postInit();

    public abstract EntityPlayer getClientPlayer();

}
