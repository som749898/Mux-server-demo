package com.doubleDB.project.requestlistener;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;
import org.jpos.util.Log;

import com.doubleDB.project.specialenum.ContextEnum;

public class RequestListener extends Log implements ISORequestListener, Configurable {
	public String queue;
	public Space<String, Context> sp;
	
	public void setConfiguration(Configuration cfg) {
		queue = cfg.get("queue"); // key name;
		sp = SpaceFactory.getSpace(cfg.get("space"));
	}
	
	
	@Override
	public boolean process(ISOSource source, ISOMsg m) {
		// TODO Auto-generated method stub
		Context ctx = new Context();
		ctx.put(ContextEnum.request, m);
		ctx.put(ContextEnum.isosource, source);
		
		sp.out(queue, ctx);
		
		return true;
	}
}
