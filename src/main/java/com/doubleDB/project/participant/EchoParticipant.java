package com.doubleDB.project.participant;

import java.io.IOException;
import java.io.Serializable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

import com.doubleDB.project.entity.ResponseEntity;
import com.doubleDB.project.service.TransactionService;
import com.doubleDB.project.specialenum.ContextEnum;
import com.doubleDB.project.springcontext.SpringContext;

public class EchoParticipant implements TransactionParticipant {
	
	public TransactionService saveEcho() {
		return SpringContext.getBean(TransactionService.class);
	}


	@Override
	public int prepare(long id, Serializable context) {
		// TODO Auto-generated method stub
		
		Logger logger=new Logger();
		logger.addListener (new SimpleLogListener (System.out));
		
		Context ctx = (Context)context;
		ISOMsg req = ctx.get(ContextEnum.request);
		ISOSource source = ctx.get(ContextEnum.isosource);
		
		try {
			req.setResponseMTI();
			req.set(39, "00");
			
			ResponseEntity responseEntity = new ResponseEntity();
			responseEntity = saveEcho().saveEntity(req);
			saveEcho().saveRepo(responseEntity);
			
			source.send(req);
		} catch (ISOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return PREPARED;
	}
}
