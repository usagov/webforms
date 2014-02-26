package gov.firstgov.photocontest.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class PhotoContestForm extends ValidatorForm {
    //private static final long serialVersionUID = 4758699871085901238L;

    private String fullName = null;
    private String emailAddress = null;
    private String photoUrl= null;
    private String parentFullName= null;
    private String parentEmailAddress= null;
    private String parentPhoneNumber= null;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String string) {
        emailAddress = string;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String string) {
        photoUrl = string;
    }

    public String getParentEmailAddress() {
        return parentEmailAddress;
    }

    public void setParentEmailAddress(String parentEmailAddress) {
        this.parentEmailAddress = parentEmailAddress;
    }

    public String getParentFullName() {
        return parentFullName;
    }

    public void setParentFullName(String parentFullName) {
        this.parentFullName = parentFullName;
    }

    public String getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    public void setParentPhoneNumber(String parentPhoneNumber) {
        this.parentPhoneNumber = parentPhoneNumber;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }

    public String toString() {

        StringBuffer retValue = new StringBuffer("");
        retValue.append("<div id='entry_form' style='font-family: verdana; font-size: 12pt;'>");
        retValue.append("<p><h3>Submission Entry Form</h3></p>");
        retValue.append("<table border='1' cellpadding='5' cellspacing='0' width='100%'>");
        retValue.append("<tr>");
        retValue.append("<td width='20%' align='right'>Full Name/Nombre completo:</td>");
        retValue.append("<td>").append(this.fullName).append("</td>");
        retValue.append("</tr>");

        retValue.append("<tr>");
        retValue.append("<td width='20%' align='right'>E-mail:</td>");
        retValue.append("<td>").append(this.emailAddress).append("</td>");
        retValue.append("</tr>");

        retValue.append("<tr>");
        retValue.append("<td width='20%' align='right'>Photo URL (please make sure the photo is visible to the public)/URL de la foto (asegúrese de que la foto esté a la vista del público):</td>");
        retValue.append("<td>").append(this.photoUrl).append("</td>");
        retValue.append("</tr>");

        retValue.append("<tr>");
        retValue.append("<td  colspan='2'>").append("Parent/Guardian Information (required if you are under 13)/Información del padre/tutor (requerida si es menor de 13):").append("</td>");
        retValue.append("</tr>");

        retValue.append("<tr>");
        retValue.append("<td width='20%' align='right'>Parent/Guardian Full Name/Nombre completo del padre/tutor:</td>");
        if (this.parentFullName == null || this.parentFullName.equals(""))
            retValue.append("<td>").append("N/A").append("</td>");
        else
            retValue.append("<td>").append(this.parentFullName).append("</td>");
        retValue.append("</tr>");

        retValue.append("<tr>");
        retValue.append("<td width='20%' align='right'>Parent/Guardian Email/E-mail del padre/tutor:</td>");
        if (this.parentEmailAddress == null || this.parentEmailAddress.equals(""))
            retValue.append("<td>").append("N/A").append("</td>");
        else
            retValue.append("<td>").append(this.parentEmailAddress).append("</td>");
        retValue.append("</tr>");

        retValue.append("<tr>");
        retValue.append("<td width='20%' align='right'>Parent/Guardian Phone/Teléfono del padre/tutor:</td>");
        if (this.parentPhoneNumber == null || this.parentPhoneNumber.equals(""))
            retValue.append("<td>").append("N/A").append("</td>");
        else
            retValue.append("<td>").append(this.parentPhoneNumber).append("</td>");
        retValue.append("</tr>");

        retValue.append("</table>");
        retValue.append("</div>");

        return retValue.toString();
    }
}
