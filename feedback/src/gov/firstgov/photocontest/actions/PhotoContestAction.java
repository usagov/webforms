package gov.firstgov.photocontest.actions;

import gov.firstgov.actions.BaseAction;
import gov.firstgov.photocontest.SendPhotoContestEntryFormTask;
import gov.firstgov.photocontest.PhotoContestConstants;
import gov.firstgov.photocontest.PhotoContestException;
import gov.firstgov.photocontest.PhotoContestRunner;
import gov.firstgov.photocontest.forms.PhotoContestForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PhotoContestAction extends BaseAction {

    /*
     * Gets new Log instance for this class
     */
    //private static transient Log log = LogFactory.getLog(PhotoContestAction.class);
    private Log log = LogFactory.getLog(this.getClass());

    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String target = SUCCESS;
        try {
            PhotoContestForm photoContestForm = (PhotoContestForm) form;
            PhotoContestRunner runner = new PhotoContestRunner(photoContestForm);
            //Prepare and send new entry form to jessica.milcetich@gsa.gov
            runner.addTask(new SendPhotoContestEntryFormTask(PhotoContestConstants.DEFAULT_TO_EMAIL_ADDRESS));
            if (!runner.execute()) {
                throw new PhotoContestException();
            }
        } catch (PhotoContestException e) {
            target = ERROR;
            ActionErrors errors = new ActionErrors();
            ActionMessages messages = new ActionMessages();
            messages.add("ApplicationError", new ActionMessage("error.photocontest.application.message"));
            errors.add(messages);
        }

        //forward to appropriate page
        return mapping.findForward(target);

    }
}
