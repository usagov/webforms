package gov.firstgov.photocontest;

import gov.firstgov.photocontest.forms.PhotoContestForm;
import gov.firstgov.util.taskrunner.Runner;
import gov.firstgov.util.taskrunner.TaskInterface;

public class PhotoContestRunner extends Runner {

    private PhotoContestForm entryForm;

    private void setEntryForm(PhotoContestForm theEntryForm) {
        entryForm = theEntryForm;
    }

    public PhotoContestRunner(PhotoContestForm theEntryForm) {
        entryForm = theEntryForm;
    }

    public void addTask(TaskInterface task) {
        task.setBean(entryForm);
        super.addTask(task);
    }
}
