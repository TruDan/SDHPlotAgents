package com.sirdrakeheart.plotagent;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

import net.citizensnpcs.properties.Node;
import net.citizensnpcs.properties.Properties;
import net.citizensnpcs.properties.PropertyManager;
import net.citizensnpcs.resources.npclib.HumanNPC;

public class AgentProperties extends PropertyManager implements Properties {
	private static final String isAgent = ".agent.toggle";
	public static final AgentProperties INSTANCE = new AgentProperties();
	
	private AgentProperties() {

	}

	@Override
	public List<Node> getNodes() {
		return null;
	}

	@Override
	public Collection<String> getNodesForCopy() {
		return nodesForCopy;
	}
	
	private static final List<String> nodesForCopy = Lists.newArrayList(
			"agent.toggle","agent.type","agent.acceptblock","agent.denyblock");
	
	@Override
	public void saveState(HumanNPC npc) {
		if (exists(npc)) {
			setEnabled(npc, npc.isType("agent"));
			AgentNPC agent = npc.getType("agent");
			agent.save(profiles, npc.getUID());
		}
	}

	@Override
	public void loadState(HumanNPC npc) {
		if (isEnabled(npc)) {
			if (!npc.isType("agent"))
				npc.registerType("agent");
			AgentNPC agent = npc.getType("agent");
			agent.load(profiles, npc.getUID());
		}
		saveState(npc);
	}

	@Override
	public void setEnabled(HumanNPC npc, boolean value) {
		profiles.setBoolean(npc.getUID() + isAgent, value);
	}
	
	@Override
	public boolean isEnabled(HumanNPC npc) {
		return profiles.getBoolean(npc.getUID() + isAgent);
	}
}
