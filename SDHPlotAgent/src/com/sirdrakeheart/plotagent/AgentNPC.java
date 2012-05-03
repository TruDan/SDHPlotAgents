package com.sirdrakeheart.plotagent;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.sirdrakeheart.plugin.SDHPlayer;
import com.sirdrakeheart.plugin.SDHPlayers;
import com.sk89q.worldedit.Vector;

import net.citizensnpcs.npctypes.CitizensNPC;
import net.citizensnpcs.npctypes.CitizensNPCType;
import net.citizensnpcs.properties.Storage;
import net.citizensnpcs.resources.npclib.HumanNPC;

public class AgentNPC extends CitizensNPC {
	
	public Vector acceptblock;
	public Vector denyblock;
	
	public String agenttype = "Plot";

	@Override
	public CitizensNPCType getType() {
		return new AgentType();
	}
	

	@Override
	public void load(Storage profiles, int UID) {
		this.agenttype = profiles.getString(UID + ".agent.type", "Plot");
		Double a_x = profiles.getDouble(UID + ".agent.acceptblock.x");
		Double a_y = profiles.getDouble(UID + ".agent.acceptblock.y");
		Double a_z = profiles.getDouble(UID + ".agent.acceptblock.z");
		this.acceptblock = new Vector(a_x,a_y,a_z);
		
		Double d_x = profiles.getDouble(UID + ".agent.denyblock.x");
		Double d_y = profiles.getDouble(UID + ".agent.denyblock.y");
		Double d_z = profiles.getDouble(UID + ".agent.denyblock.z");
		this.denyblock = new Vector(d_x,d_y,d_z);
	}

	@Override
	public void save(Storage profiles, int UID) {
		if(agenttype == null) { agenttype = "Plot";}
        profiles.setString(UID + ".agent.type", agenttype);
        profiles.setDouble(UID + ".agent.acceptblock.x", acceptblock.getX());
        profiles.setDouble(UID + ".agent.acceptblock.y", acceptblock.getY());
        profiles.setDouble(UID + ".agent.acceptblock.z", acceptblock.getZ());
        
        profiles.setDouble(UID + ".agent.denyblock.x", denyblock.getX());
        profiles.setDouble(UID + ".agent.denyblock.y", denyblock.getY());
        profiles.setDouble(UID + ".agent.denyblock.z", denyblock.getZ());
    }
	
	@Override
    public void onRightClick(Player player, HumanNPC npc) {        
		AgentNPC agent = npc.getType("agent");
		SDHPlayer sdhplayer = SDHPlayers.getPlayer(player.getName());
		if(sdhplayer.acceptBlockSelect == true) {
			sdhplayer.setAcceptBlockSelect(false);
			this.acceptblock = sdhplayer.getAcceptBlock();
			player.sendMessage(ChatColor.GREEN+"Accept block set.");
		}
		else if(sdhplayer.denyBlockSelect == true) {
			sdhplayer.setDenyBlockSelect(false);
			this.denyblock = sdhplayer.getDenyBlock();
			player.sendMessage(ChatColor.GREEN+"Deny block set.");
		}
		else if(sdhplayer.acceptDenyMode != true) {
			if(agent.agenttype == "Community") {
		        player.sendMessage(ChatColor.GRAY+"Would you like to purchase a community shop or stall?");
		        player.sendMessage(ChatColor.YELLOW+"Left click the wool to indicate your response.");
			}
			else {
		        player.sendMessage(ChatColor.GRAY+"Would you like to purchase a housing plot?");
		        player.sendMessage(ChatColor.YELLOW+"Left click the wool to indicate your response.");
			}
			sdhplayer.setAcceptBlock(acceptblock);
			sdhplayer.setDenyBlock(denyblock);
		    sdhplayer.setAcceptDenyMode(true);
		}
		else {
			sdhplayer.setAcceptDenyMode(false);
			sdhplayer.setPlotBuyMode(false);
			sdhplayer.setPurchaseMode(false);
			player.sendMessage(ChatColor.GRAY+"Plot Purchase cancelled.");
		}
	}

	public void setAgentType(String agenttype) {
		this.agenttype = agenttype;
	}
	
	public void setAcceptBlock(Vector block) {
		this.acceptblock = block;
	}
	
	public void setDenyBlock(Vector block) {
		this.denyblock = block;
	}
	
	public Vector getAcceptBlock() {
		return this.acceptblock;
	}
	
	public Vector getDenyBlock() {
		return this.denyblock;
	}
}
