package com.sirdrakeheart.plotagent;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.sirdrakeheart.plugin.SDHPlayer;
import com.sirdrakeheart.plugin.SDHPlayers;

import net.citizensnpcs.commands.CommandHandler;
import net.citizensnpcs.resources.npclib.HumanNPC;
import net.citizensnpcs.resources.sk89q.Command;
import net.citizensnpcs.resources.sk89q.CommandContext;
import net.citizensnpcs.resources.sk89q.CommandPermissions;
import net.citizensnpcs.resources.sk89q.CommandRequirements;

@CommandRequirements(
		requireSelected = true,
		requireOwnership = true,
		requireEconomy = true,
		requiredType = "agent")
public class AgentCommands extends CommandHandler {
	public static final AgentCommands INSTANCE = new AgentCommands();
	
	@CommandRequirements(
			requireEconomy = false,
			requireSelected = true,
			requiredType = "agent")
	@Command(
			aliases = "agent",
			usage = "agent settype [Community|Plot]",
			desc = "set the Type of agent",
			modifiers = "settype",
			min = 2,
			max = 2)
	@CommandPermissions("agent.setup.type")
	public static void settype(CommandContext args, Player player, HumanNPC npc) {
		AgentNPC agent = npc.getType("agent");
		if(args.getString(1).contains("Community")) {
			agent.setAgentType("Community");
			player.sendMessage(ChatColor.GREEN+"This NPC is now a Community Plot trader.");
		}
		else {
			agent.setAgentType("Plot");
			player.sendMessage(ChatColor.GREEN+"This NPC is now a Housing Plot trader.");
		}
	}
	
	@CommandRequirements(
			requireEconomy = false,
			requireSelected = true,
			requiredType = "agent")
	@Command(
			aliases = "agent",
			usage = "agent set [Accept|Deny]",
			desc = "set the Accept/Deny block of agent",
			modifiers = "set",
			min = 2,
			max = 2)
	@CommandPermissions("agent.setup.blocks")
	public static void set(CommandContext args, Player player, HumanNPC npc) {
		SDHPlayer p = SDHPlayers.getPlayer(player.getName());
		if(args.getString(1).contains("ac")) {
			p.setDenyBlockSelect(false);
			p.setAcceptBlockSelect(true);
			player.sendMessage(ChatColor.GRAY+"Please left click the block you wish to be the accept block.");
		}
		else {
			p.setDenyBlockSelect(true);
			p.setAcceptBlockSelect(false);
			player.sendMessage(ChatColor.GRAY+"Please left click the block you wish to be the deny block.");
		}
	}

	@Override
	public void addPermissions() {
		// TODO Auto-generated method stub
		
	}
	
}