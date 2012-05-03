package com.sirdrakeheart.plotagent;

import net.citizensnpcs.commands.CommandHandler;
import net.citizensnpcs.npctypes.CitizensNPC;
import net.citizensnpcs.npctypes.CitizensNPCType;
import net.citizensnpcs.properties.Properties;

public class AgentType extends CitizensNPCType {

	@Override
	public CommandHandler getCommands() {
		return AgentCommands.INSTANCE;
	}

	@Override
	public CitizensNPC getInstance() {
		return new AgentNPC();
	}

	@Override
	public String getName() {
		return "agent";
	}

	@Override
	public Properties getProperties() {
		return AgentProperties.INSTANCE;
	}

}
