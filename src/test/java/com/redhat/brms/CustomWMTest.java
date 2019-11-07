package com.redhat.brms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.HashMap;
import java.util.Map;

import com.redhat.brms.listeners.CustomAgendaListener;
import com.redhat.brms.model.Order;

public class CustomWMTest {

	private static Logger logger = Logger.getLogger(CustomWMTest.class);
	
	private KieSession kSession;
	private Order order;
	
	private CustomAgendaListener listener = new CustomAgendaListener();
	
	
	@Before
	public void initSession() {
		logger.info("Initializing KieSession");
		
        KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	kSession = kContainer.newKieSession("custom-reproducer-session");
    	kSession.addEventListener(listener);
    		
		// insert all facts
    	logger.info("Creating an order WM");
    	order = new Order("ORDER-1");
    	kSession.insert(order);
	}
	
	@After
	public void closeSession() {
		logger.info("Cleanup");
		kSession.removeEventListener(listener);
		kSession.dispose();
		
		listener.getRulesFired().clear();
		listener = null;
		kSession = null;
		order = null;
	}
	
	@Test
	public void testRulesLoop() {
	    
	    long startTime = System.currentTimeMillis();
	    
	    // Set the input process variable
	    Map<String,Object> inputMap = new HashMap<String,Object>();
	    	    	    
	    // fire rules
		int nbRulesFired = kSession.fireAllRules();
	    kSession.startProcess("TestRuleflow", inputMap);
	    long endTime = System.currentTimeMillis();
	    logger.info("Time to fire "+nbRulesFired+" rules: " + (endTime - startTime)  + "ms" );
	    logger.info("Resulting data: "+order.toString());

	    assertNotNull(order.getAmount());
	    assertEquals(101, order.getAmount(), 0);
	    assertNotNull(order.getDiscount());
	    assertEquals(5.5, order.getDiscount(), 0);
	    assertEquals(2, listener.getRulesFired().size());
	}
	
}
