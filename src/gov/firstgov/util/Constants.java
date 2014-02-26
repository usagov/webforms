/*
 * Created on Sep 29, 2006
 *
 * FIXME To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.firstgov.util;

/**
 * @author HenryChang
 *
 * FIXME To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Constants {

	/**
	 * Max number of to email addresses
	 */
	public static final int TO_EMAIL_MAXSIZE        = 100;
	/**
	 * Max length constants
	 */
	public static final int LTS_MAXLENGTH           = 1024;
	public static final int FRNM_MAXLENGTH          = 512;
	public static final int FIRSTNAME_MAXLENGTH     = 256;
	public static final int LASTNAME_MAXLENGTH      = 256;
	public static final int TITLE_MAXLENGTH         = 1024;
	public static final int DESCRIPTION_MAXLENGTH   = 256;
	public static final int SUGGESTION_MAXLENGTH    = 256;
	public static final int FEEDBACK_MAXLENGTH      = 2500;
	public static final int ZIP_MAXLENGTH           = 32;
	public static final int CITY_STATE_MAXLENGTH    = 128;
	// HTML encoding for Spanish characters
	public static final String aACUTE_REGEXP		= "&aacute;";
	public static final String aACUTE_IN_URL		= ".aacute;";
	public static final String aACUTE_HTMLENCODED	= "&amp;aacute&#59;";
	public static final String eACUTE_REGEXP		= "&eacute;";
	public static final String eACUTE_IN_URL		= ".eacute;";
	public static final String eACUTE_HTMLENCODED	= "&amp;eacute&#59;";
	public static final String iACUTE_REGEXP		= "&iacute;";
	public static final String iACUTE_IN_URL		= ".iacute;";
	public static final String iACUTE_HTMLENCODED	= "&amp;iacute&#59;";
	public static final String oACUTE_REGEXP		= "&oacute;";
	public static final String oACUTE_IN_URL		= ".oacute;";
	public static final String oACUTE_HTMLENCODED	= "&amp;oacute&#59;";
	public static final String uACUTE_REGEXP		= "&uacute;";
	public static final String uACUTE_IN_URL		= ".uacute;";
	public static final String uACUTE_HTMLENCODED	= "&amp;uacute&#59;";
	public static final String AACUTE_REGEXP		= "&Aacute;";
	public static final String AACUTE_IN_URL		= ".Aacute;";
	public static final String AACUTE_HTMLENCODED	= "&amp;Aacute&#59;";
	public static final String EACUTE_REGEXP		= "&Eacute;";
	public static final String EACUTE_IN_URL		= ".Eacute;";
	public static final String EACUTE_HTMLENCODED	= "&amp;Eacute&#59;";
	public static final String IACUTE_REGEXP		= "&Iacute;";
	public static final String IACUTE_IN_URL		= ".Iacute;";
	public static final String IACUTE_HTMLENCODED	= "&amp;Iacute&#59;";
	public static final String OACUTE_REGEXP		= "&Oacute;";
	public static final String OACUTE_IN_URL		= ".Oacute;";
	public static final String OACUTE_HTMLENCODED	= "&amp;Oacute&#59;";
	public static final String UACUTE_REGEXP		= "&Uacute;";
	public static final String UACUTE_IN_URL		= ".Uacute;";
	public static final String UACUTE_HTMLENCODED	= "&amp;Uacute&#59;";
	public static final String nTILDE_REGEXP		= "&ntilde;";
	public static final String nTILDE_IN_URL		= ".ntilde;";
	public static final String nTILDE_HTMLENCODED	= "&amp;ntilde&#59;";
	public static final String NTILDE_REGEXP		= "&Ntilde;";
	public static final String NTILDE_IN_URL		= ".Ntilde;";
	public static final String NTILDE_HTMLENCODED	= "&amp;Ntilde&#59;";
	public static final String uUML_REGEXP			= "&uuml;";
	public static final String uUML_IN_URL			= ".uuml;";
	public static final String uUML_HTMLENCODED		= "&amp;uuml&#59;";
	public static final String UUML_REGEXP			= "&Uuml;";
	public static final String UUML_IN_URL			= ".Uuml;";
	public static final String UUML_HTMLENCODED		= "&amp;Uuml&#59;";
	public static final String IEXCL_REGEXP			= "&iexcl;";
	public static final String IEXCL_IN_URL			= ".iexcl;";
	public static final String IEXCL_HTMLENCODED	= "&amp;iexcl&#59;";
	public static final String ORDF_REGEXP			= "&ordf;";
	public static final String ORDF_IN_URL			= ".ordf;";
	public static final String ORDF_HTMLENCODED		= "&amp;ordf&#59;";
	public static final String IQUEST_REGEXP		= "&iquest;";
	public static final String IQUEST_IN_URL		= ".iquest;";
	public static final String IQUEST_HTMLENCODED	= "&amp;iquest&#59;";
	public static final String ORDM_REGEXP			= "&ordm;";
	public static final String ORDM_IN_URL			= ".ordm;";
	public static final String ORDM_HTMLENCODED		= "&amp;ordm&#59;";

	/**
	 * When suspicious email is found in a string variable, the original string value is replaced
	 * with this message.
	 */
	public static final String SUSPICIOUS_EMAIL_MSG = "---SUSPICIOUS EMAIL WAS FOUND AND REMOVED---"; 

	/*
	 * Constant patterns to check for embedded email
	 */
	public static final String CONTENT_TYPE_PATTERN  = "CONTENT-TYPE:";
	public static final String BOUNDARY_PATTERN      = "BOUNDARY=";

}
