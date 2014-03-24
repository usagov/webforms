<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<!--Beginning of Title Section-->
<p><a href="#" name="Content"> </a> USA.gov welcomes and reviews all
recommendations for additional links to useful, timely, citizen-centered
information and services. To suggest a new link, follow these simple
steps:</p>
<!--End of Title Section-->
<!--Beginning of Description-->
<OL>
	<LI>Review USA.gov's <a href="/About/Linking_Policy.shtml"
		title="Linking Policy" accesskey="l"><Strong>Linking Policy</Strong></a>.
	</LI>
	<LI>Complete the Suggest-A-Link Form below.</LI>
	<LI>Click on the "Submit" button and your suggestion will go directly
	to USA.gov for consideration.</LI>
</OL>
<!--End of Description-->

<strong> Suggest-A-Link Form </strong>
<!-- Feedback Form goes here -->
<%
// CR#657: Suggest-a-link enhancement to reduce spam submissions
String answer = "";
if (session.getAttribute("mathAnswer") == null) {
    // starting a new test
    int num = (int)(Math.random()*100.0 + 1);
    session.setAttribute("mathAnswer", new Integer(num));
} else {
    // preserve user's answer
    answer = request.getParameter("answer");
    answer = (answer == null) ? "" : answer;
}
// pull existing values out of session
Integer value1o = (Integer)session.getAttribute("mathAnswer");

%>
<a name="form"></a>
<html:form action="/suggestALink">
	<html:hidden property="language" value="EN" />

	<fieldset id="Link_form">
	<span style="display: none; visibility: hidden">
		<label for="invisibleBlank">Please do not enter any information into this field.  It is used to detect spam messages.  If you enter any text into this field, your message will not be sent.</label>
		<input type="text" name="invisibleBlank" id="invisibleBlank" size="1" value="" />
	</span>
	<ol>
		<li><!-- URL --> <label for="url"><bean:message key="enter.url" /> <span
			class="FontRequired">(<bean:message key="field.required" />)</span></label>
		<br />
		<!-- error --> <html:errors property="url" /> <html:text
			property="url" size="50" tabindex="101" maxlength="500" styleId="url" />
		<br />
		</li>
		<li><bean:message key="enter.name" /><br />
		<table cellpadding="0" title="Name Table"
			summary="Table for a visitor to enter their name when suggesting a link to be included on USA.gov">
			<tr>
				<td align="left" valign="top" id="first_name" width="175"><label for="firstName"><bean:message
					key="first.name" />: </label><br />
				<html:text property="firstName" size="25" tabindex="102"
					maxlength="75" styleId="firstName" /></td>
				<td align="left" valign="top" id="last_name"><label for="lastName"><bean:message
					key="last.name" />: </label><br />
				<html:text property="lastName" size="25" tabindex="103"
					maxlength="75" styleId="lastName" /></td>
			</tr>
		</table>
		</li>
		<li><label for="emailAddress"><bean:message key="enter.emailAddress" /></label>
		<br />
		<html:errors property="emailAddress" /> <html:text
			property="emailAddress" size="50" tabindex="104" maxlength="150"
			styleId="emailAddress" /> <br />
		</li>
		<li><label for="telephoneNumber"><bean:message
			key="enter.telephoneNumber" /></label> <br />
		<html:text property="telephoneNumber" size="50" tabindex="105"
			maxlength="25" styleId="telephoneNumber" /> <br />
		</li>
		<li><label for="linkDescription"><bean:message
			key="enter.linkDescription" /></label> <br />
		<html:errors property="linkDescription" /> <html:textarea
			property="linkDescription" cols="50" rows="5" tabindex="106"
			styleId="linkDescription" /> <br />
		</li>
		<li><label for="suggestedCategoryDescription"><bean:message
			key="enter.suggestedCategoryDescription" /></label> <br />
		<html:errors property="suggestedCategoryDescription" /> <html:textarea
			property="suggestedCategoryDescription" cols="50" rows="5"
			tabindex="107" styleId="suggestedCategoryDescription" /> <br />
		</li>
		<li><label for="math_test">Please type <%= value1o %> into the following box </label> <br />
			<input name="answer" id="math_test" tabindex= "108" value=""><br /><br />
			<b>Are you human?</b> We do this because it is possible for search engines and other tools
			to submit this form, either accidentally or on purpose, which can cause unnecessary
			server traffic.<br />
		</li>
	</ol>
	<html:submit value="Submit" /> <html:reset value="Cancel and Clear Form" /></fieldset>
</html:form>
<br />
<p align="center">Thanks for submitting your Suggest-A-Link!
<br />
<a href="http://www.usa.gov/index.shtml"
	title="USA.gov"> <STRONG> USA.gov </STRONG>
</a>
<br />
</p>
