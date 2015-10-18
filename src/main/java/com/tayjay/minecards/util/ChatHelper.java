package com.tayjay.minecards.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

/**
 * Created by tayjm_000 on 2015-10-18.
 */
public class ChatHelper
{
    /**
     * Send a chat message to the world.
     * @param msg Message to be sent
     */
    public static void send(String msg) //Send to world
    {
        MinecraftServer mcServer = MinecraftServer.getServer();
        mcServer.getConfigurationManager().sendChatMsg(new ChatComponentText(msg));
    }

    /**
     * Send a chat message to a player.
     * @param player Recipient of message
     * @param msg Message to be sent.
     */
    public static void send(EntityPlayer player, String msg) //Send to player
    {
        if(player!=null)
        {
            player.addChatComponentMessage(new ChatComponentText(msg));
        }
    }
}
