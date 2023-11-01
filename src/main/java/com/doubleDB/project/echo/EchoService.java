package com.doubleDB.project.echo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.MUX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EchoService {
	@Autowired
	private MUX mux;
	
	DateFormat date = new SimpleDateFormat("MMddHHmmss");
	
	public String doEcho() throws ISOException {
		
		LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);

	     // Define a custom date-time format (MMDDHHmmss)
	     DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MMddHHmmss");

	     // Format the current date and time using the custom format
	     String formattedDateTime = customFormatter.format(currentDateTime);
		
		ISOMsg msg = new ISOMsg();
		msg.setMTI("0800");
		msg.set(7, formattedDateTime);
		msg.set(11, getRandomNumberString());
		msg.set(70, "01");
		
		ISOMsg res = new ISOMsg();
		res = mux.request(msg, 5000);
		return res.toString();
	}
	
	
	public static String getRandomNumberString() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    return String.format("%06d", number);
	}
}
