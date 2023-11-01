package com.doubleDB.project;

import org.jpos.iso.ISOUtil;
import org.jpos.iso.MUX;
import org.jpos.q2.Q2;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar.NotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
    public MUX mux (Q2 q2) throws NotFoundException {
    while (!q2.ready()) {
    ISOUtil.sleep(10);
    	}
    return QMUX.getMUX("my-mux");
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
