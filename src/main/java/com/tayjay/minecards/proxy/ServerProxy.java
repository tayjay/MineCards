package com.tayjay.minecards.proxy;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by tayjm_000 on 2015-10-18.
 */
public class ServerProxy extends CommonProxy
{

    @Override
    public void preInit()
    {

    }

    @Override
    public void init()
    {

    }

    @Override
    public void postInit()
    {

    }

    @Override
    public EntityPlayer getClientPlayer()
    {
        //NOOP
        return null;
    }

}
