/*
 * Created on May 4, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.firstgov.suggestalink;

import gov.firstgov.util.taskrunner.TaskInterface;

/**
 * @author RussellGONeill
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendEmailReplyTask implements TaskInterface {
	/**
	 * Comment for <code>suggestalinkbean</code>
	 */
	private SuggestALinkBean suggestalinkbean;

	/**
	 * @param theSuggestalinkbean The suggestalinkbean to set.
	 */
	public void setBean(Object bean) {
		suggestalinkbean = (SuggestALinkBean) bean;
	}

	/**
	 * @return
	 */
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}
}
