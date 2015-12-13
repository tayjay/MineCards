package com.tayjay.minecards;

import com.tayjay.minecards.card.CardRegistry;
import com.tayjay.minecards.init.ModItems;
import com.tayjay.minecards.proxy.CommonProxy;
import com.tayjay.minecards.reference.Reference;
import com.tayjay.minecards.util.LogHelper;
import com.tayjay.minecards.util.RenderUtil;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * Created by tayjm_000 on 2015-10-18.
 */
@Mod(modid = Reference.MOD_ID,name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class MineCards
{

    @Mod.Instance
    public static MineCards instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event)
    {

    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LogHelper.debug("Starting Pre-Initialization.");

        //CardRegistry.init();
        proxy.preInit();
        ModItems.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        LogHelper.debug("Ending Pre-Initialization.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        LogHelper.debug("Starting Initialization.");

        LogHelper.debug("Ending Initialization.");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.debug("Starting Post-Initialization.");

        //RenderUtil.init();

        LogHelper.debug("Ending Post-Initialization.");
    }

}
