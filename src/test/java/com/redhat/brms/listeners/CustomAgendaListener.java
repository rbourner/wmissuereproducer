package com.redhat.brms.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
 

public class CustomAgendaListener implements AgendaEventListener {
	
	private static Logger logger = Logger.getLogger(CustomAgendaListener.class);
	
	private List<String> rulesFired = new ArrayList<String>();
	
	public List<String> getRulesFired() {
		return this.rulesFired;
	}
	
	public void setRulesFired(List<String> listRules) {
		this.rulesFired = listRules;
	}

	public void beforeMatchFired(BeforeMatchFiredEvent evt) {
		String ruleName = evt.getMatch().getRule().getName();
		rulesFired.add(ruleName);
		Iterator<Object> iter = evt.getMatch().getObjects().iterator();
		StringBuffer buf = new StringBuffer();
		while (iter.hasNext()) {
			buf.append(iter.next().toString());
			buf.append(", ");
		}
		logger.info("***** Rule fired: '"+ruleName+"' with following data: "+buf.toString());
	}

	public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent evt) {
	}

	public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent evt) {
	}

	public void matchCancelled(MatchCancelledEvent evt) {
	}
	
	public void afterMatchFired(AfterMatchFiredEvent evt) {
	}

	public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent evt) {
	}

	public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent evt) {
	}

	public void agendaGroupPopped(AgendaGroupPoppedEvent evt) {
	}

	public void agendaGroupPushed(AgendaGroupPushedEvent evt) {
	}

	public void matchCreated(MatchCreatedEvent arg0) {
	}
}
