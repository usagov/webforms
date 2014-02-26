package gov.firstgov.suggestalink.actions;

import gov.firstgov.util.*;
import gov.firstgov.actions.BaseAction;
import gov.firstgov.suggestalink.InsertDBRecordTask;
import gov.firstgov.suggestalink.SendSuggestionTask;
import gov.firstgov.suggestalink.SuggestALinkConstants;
import gov.firstgov.suggestalink.SuggestALinkException;
import gov.firstgov.suggestalink.SuggestALinkRunner;
import gov.firstgov.suggestalink.forms.SuggestALinkForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Copyright (c) 2006 by BEA Systems.All Rights Reserved
 * 
 */
public class SuggestALinkAction extends BaseAction {
	
    /*
     * Gets new Log instance for this class
     */
    private static transient Log log = LogFactory.getLog(SuggestALinkAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = SUCCESS;
		
		try {
			SuggestALinkForm suggestALinkForm = (SuggestALinkForm) form;						
			String language = suggestALinkForm.getLanguage();

			if( language.toUpperCase().equals("ES") ) {
				   processEspanol(suggestALinkForm);
			}
			else {
				   processEnglish(suggestALinkForm, request);
			}
		}catch (SuggestALinkException e) {
			target = ERROR;
			ActionErrors errors = new ActionErrors();
			ActionMessages messages = new ActionMessages();
			messages.add("ApplicationError", new ActionMessage("error.application.message"));
			errors.add(messages);
		}
		
		//forward to appropriate page
		return mapping.findForward(target);

	}

	/* Authenticate hidden form field and math answer */
	private boolean authenticateFormFields(SuggestALinkForm suggestALinkForm, HttpServletRequest request) {
		// CR#657: Suggest-a-link enhancement to reduce spam submissions
		// The invisible form field has to remain blank
		// The math answer has to match the value in session
		String answerString= StringUtils.trim(suggestALinkForm.getAnswer());
		String invisibleString= StringUtils.trim(suggestALinkForm.getInvisibleBlank());
		log.info("answerString = " + answerString);
		log.info("invisibleString = " + invisibleString);
		HttpSession session = request.getSession(false);
		boolean authentic = false;
		if (answerString != null && session != null && invisibleString.length() == 0)
		{
			try {
				int answer = Integer.parseInt(answerString);
			    Integer num = (Integer) session.getAttribute("mathAnswer");
			    if (num != null && answer == num.intValue()) {
			        authentic = true;
			        session.removeAttribute("mathAnswer");
			    }
				log.info("answer in session = " + num);
			} catch (NumberFormatException ignored) {
				// ignored ... someone is just really bad at math
			} catch (Exception e) {
			    // unexpected
			    System.out.println(e);
			}
		}
		return authentic;
	}
	
	/* Process the Suggest-A-Link English form data */
	private void processEnglish(SuggestALinkForm suggestALinkForm, HttpServletRequest request) throws SuggestALinkException{
		// CR#657: Suggest-a-link enhancement to reduce spam submissions
		// Form will not be submitted if it has not been authenticated
		if (!authenticateFormFields(suggestALinkForm, request)) throw new SuggestALinkException();
		SuggestALinkRunner runner = new SuggestALinkRunner(suggestALinkForm);
		runner.addTask(new SendSuggestionTask(SuggestALinkConstants.ENGLISH_TO_ADDRESS));
		if (!runner.execute()) throw new SuggestALinkException();
	}
	

	/* Process the Suggest-A-Link Espanol form data */
	private void processEspanol(SuggestALinkForm suggestALinkForm_es) throws SuggestALinkException{
		SuggestALinkRunner runner = new SuggestALinkRunner(suggestALinkForm_es);
		runner.addTask(new SendSuggestionTask(SuggestALinkConstants.ESPANOL_TO_ADDRESS));
		runner.addTask(new InsertDBRecordTask());
		if (!runner.execute()) throw new SuggestALinkException();
	}


}
