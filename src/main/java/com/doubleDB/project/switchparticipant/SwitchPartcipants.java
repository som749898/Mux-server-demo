package com.doubleDB.project.switchparticipant;

import java.io.Serializable;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;

import com.doubleDB.project.specialenum.ContextEnum;

public class SwitchPartcipants implements GroupSelector, Configurable {

	@Override
	public int prepare(long id, Serializable context) {
		// TODO Auto-generated method stub
		return PREPARED | ABORTED | NO_JOIN;
	}

	@Override
	public String select(long id, Serializable context) {
		// TODO Auto-generated method stub
		Context ctx = (Context)context;
		ISOMsg reqMsg = (ISOMsg)ctx.get(ContextEnum.request);
		String selector = "";
		try {
			selector = configuration.get(reqMsg.getMTI());
		}catch(ISOException e) {
			e.printStackTrace();
		}
		return selector;
	}

	private Configuration configuration;
	
	@Override
	public void setConfiguration(Configuration cfg) throws ConfigurationException {
		// TODO Auto-generated method stub
		this.configuration = cfg;
		
	}
}
