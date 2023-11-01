package com.doubleDB.project.echo;

import org.jpos.iso.ISOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
	@Autowired
	public EchoService echo;
	
	
	
	@Scheduled(fixedRate = 10000)
 	@GetMapping("/echo")
	public String getEcho() throws ISOException {
		return echo.doEcho();
	}
}
