/*
 * Created on May 4, 2006
 *
 */
package gov.firstgov.suggestalink;

import gov.firstgov.suggestalink.forms.SuggestALinkForm;
import gov.firstgov.util.taskrunner.Runner;
import gov.firstgov.util.taskrunner.TaskInterface;

/**
 * @author RussellGONeill
 *
 * Processes a suggestion in a workflow-like fashion.  Add tasks using the 
 * addTask method, and then execute them all (in order) using the execute() method.
 */
public class SuggestALinkRunner extends Runner {
	/**
	 * The suggestion to be processed
	 */
	private SuggestALinkBean suggestion;

	/**
	 * Sets the suggestion to be processed.
	 * @param theSuggestion The suggestion to set.
	 */
	private void setSuggestion(SuggestALinkBean theSuggestion) {
		suggestion = theSuggestion;
	}

	/**
	 * @param suggestion	The suggestion to be processed
	 */
	public SuggestALinkRunner(SuggestALinkBean theSuggestion) {
		suggestion = theSuggestion;
	}
	
	/**
	 * @param theSuggestionForm	The suggestion to be processed, as a SugggestALinkForm
	 * @see SuggestALinkForm
	 */
	public SuggestALinkRunner(SuggestALinkForm theSuggestionForm) {
		SuggestALinkBean theSuggestion = new SuggestALinkBean();
		theSuggestion.setDescription(theSuggestionForm.getLinkDescription());
		theSuggestion.setFname(theSuggestionForm.getFirstName());
		theSuggestion.setLname(theSuggestionForm.getLastName());
		theSuggestion.setEmail(theSuggestionForm.getEmailAddress());
		theSuggestion.setUrl(theSuggestionForm.getUrl());
		theSuggestion.setLang(theSuggestionForm.getLanguage());
		theSuggestion.setLocationSuggestion(theSuggestionForm.getSuggestedCategoryDescription());
		theSuggestion.setDayPhone(theSuggestionForm.getTelephoneNumber());
		suggestion = theSuggestion;
	}
	
	/**
	 *  Adds a task that needs to be executed on the suggestion.
	 */
	public void addTask(TaskInterface task) {
		task.setBean(suggestion);
		super.addTask(task);
	}
}
