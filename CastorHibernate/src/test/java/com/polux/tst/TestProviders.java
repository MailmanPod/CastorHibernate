package com.polux.tst;

import java.security.Provider;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class TestProviders {
	
	public static Logger logger = Logger.getLogger(TestProviders.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		for (Provider p : Security.getProviders()) {
//		    for (Object o : p.keySet()) {
//		        //System.out.println(o);
//		    	logger.info(o.toString());
//		    }
//		}
		
		long testDate = 64060513200000L;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String newDate = sdf.format(new Date(testDate));
		System.out.println(newDate);
		
	}
}
