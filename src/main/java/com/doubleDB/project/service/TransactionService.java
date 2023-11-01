package com.doubleDB.project.service;


import java.math.BigDecimal;
import java.util.Date;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.doubleDB.project.entity.ResponseEntity;
import com.doubleDB.project.repository.UserRepository;
import com.doubleDB.project.repository2.UserRepository2;

@Service
@ComponentScan(basePackages = "com.doubleDB.project.repository")
public class TransactionService {
	
	
	@Autowired
	public UserRepository repo;
	
	@Autowired
	public UserRepository2 repo2;
	
	public ResponseEntity saveEntity(ISOMsg msg) throws ISOException {
		
		ResponseEntity responseEntity = new ResponseEntity();
		
//		responseEntity.setMti(msg.getMTI());
		responseEntity.setCardnumber(msg.getString(2));
		responseEntity.setProcessing_code(msg.getString(3));
		responseEntity.setAmount(new BigDecimal(msg.getString(4)).movePointLeft(2));
		responseEntity.setReq_in_time(new Date());
		responseEntity.setStan(msg.getString(11));
		responseEntity.setRrn(msg.getString(37));
		responseEntity.setResponse_code(msg.getString(39));
		responseEntity.setChipdatareq(msg.getString(55));
		

		
		responseEntity.setIsReversed(responseEntity.getIsReversed() == null ? false : responseEntity.getIsReversed());
		responseEntity.setIscredit(responseEntity.isIscredit() || false);
		responseEntity.setIsVoid(false);
		responseEntity.setAcquirecode((String) msg.getValue(32) == null ? "" : msg.getString(32));
		responseEntity.setAmount((String) msg.getValue(4) == null ? new BigDecimal("0")
				: new BigDecimal(msg.getString(4)).movePointLeft(2));
		//For saving the encrypted card number in DB
		if((String) msg.getValue(2) == null ) {
			responseEntity.setCardnumber("");
		}else {
			String encrypted_cardnumber = (msg.getString(2));
			responseEntity.setCardnumber(encrypted_cardnumber);
		}
		responseEntity.setReq_out_time(new Date());
		responseEntity.setExpdate(msg.getString(35).substring(msg.getString(35).indexOf("=")+1, msg.getString(35).indexOf("=")+5));
		responseEntity.setCurrency_code(msg.getString(49));
		responseEntity.setCardtype("ATM");
		responseEntity.setProcessing_code((String) msg.getValue(3) == null ? "" : msg.getString(3));
		responseEntity.setRrn((String) msg.getValue(37) == null ? "" : msg.getString(37));
		responseEntity.setStan((String) msg.getValue(11) == null ? "" : msg.getString(11));
		responseEntity.setResponse_code((String) msg.getValue(39));
		responseEntity.setMid((String) msg.getValue(42) == null ? "" : (String) msg.getValue(42));
		responseEntity.setTid((String) msg.getValue(41) == null ? "" : msg.getString(41));
//		responseEntity.setCurrency_code((String) msg.getValue(51) == null ? "" : msg.getString(51));
//		responseEntity.setLocation(
//				((String) msg.getValue(43)) == null ? "" : ((String) msg.getValue(43)).substring(23));
		responseEntity.setLocation(msg.getString(43));
		if (msg.getString(03).substring(0, 2).equals("01")) // previously "00"

			responseEntity.setTxntype("CASHWITHDRAWL.");

		else if (msg.getString(03).substring(0, 2).equals("31"))

			responseEntity.setTxntype("BALANE ENQUIRY.");

		// System.out.println(((String) res.getValue(43)).substring(23));

//		responseEntity.setExpdate((String) msg.getValue(14) == null ? "" : msg.getString(14));
		responseEntity.setChipdatareq(msg.getString(55));
		responseEntity.setChipdatares(msg.getString(55));

		System.out.println("Data Saved Successfully" +responseEntity);
		return responseEntity;
	}
	
	public void saveRepo(ResponseEntity responseEntity) {
		
		
		try {
			repo.save(responseEntity);
		}catch(Exception e) {
			repo2.save(responseEntity);
		}
	}
}
