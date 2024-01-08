package com.doubleDB.project.participant;

import static org.jpos.transaction.ContextConstants.REQUEST;
import static org.jpos.transaction.ContextConstants.SOURCE;

import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;
import org.springframework.stereotype.Service;

import com.doubleDB.project.entity.ResponseEntity;
import com.doubleDB.project.service.TransactionService;
import com.doubleDB.project.specialenum.ContextEnum;
import com.doubleDB.project.springcontext.SpringContext;

@Service
public class AuthorizationResponse implements TransactionParticipant {

	Context ctx = null;
	ISOMsg msg = null;
	ISOSource source = null;

	public TransactionService getTransactionService() {
		return SpringContext.getBean(TransactionService.class);
	}

	@Override
	public int prepare(long id, Serializable context) {
		// TODO Auto-generated method stub
		Logger logger = new Logger();
		logger.addListener(new SimpleLogListener(System.out));

		ctx = (Context) context;
		msg = (ISOMsg) ctx.get(REQUEST.toString());
		source = (ISOSource) ctx.get(SOURCE.toString());

		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// For isoMessage 55 tag 91
		String tagName = "91";
		String tagValue = "438F553283A03D69";
		String tagLength = "0F";
		String chipDataTag91 = tagName + tagValue + tagLength;

		try {
			msg.setResponseMTI();
			msg.set(39, "00");
			msg.set(38, Integer.toString(number));
			msg.set(48, "054001M051005POS01");
			msg.set(55, chipDataTag91);

			ResponseEntity responseEntity = getTransactionService().saveEntity(msg);
			getTransactionService().saveRepo(responseEntity);

			msg.unset(18);
			msg.unset(22);
			msg.unset(25);
			msg.unset(35);
			msg.unset(40);
			msg.unset(42);
			msg.unset(43);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return PREPARED;
	}

	public void commit(long l, Serializable serializable) {
		try {
			source.send(msg);
		} catch (IOException | ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
