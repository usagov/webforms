package gov.firstgov.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;

public class StringUtils {
	
	public static final String EMPTY = "";
	public static final String COMMA = ",";
	
	/*
	 * This method is a work-around to fix invalid URL encoding of Spanish characters in the
	 * current content and CMS system. Proper fix of this problem shall fix content, CMS, JSP,
	 * and WebLogic character setting. 
	 * In order to pass acute characters through HTTP variables to support Spanish language,
	 * the "&" is replaced by ".". On the server side the variable is HTML-encoded to protect
	 * against cross side scripting vulnerability. But, it also disable the browser to render
	 * acute characters correctly. The solution is to restore acute syntax in the HTML encoded
	 * string. The acute syntax applies to all 18 HTML encoding Spanish characters.
	 * The variable is processed on the server side by these steps in order:
	 * 1. Restore acute syntax. For example, replace ".aacute;" with "&aacute;".
	 * 2. HTML encode the variable.
	 * 3. Restore acute syntax in HTML encoded string. For example, replace "&amp;aacute&#59;"
	 * with "&aacute;".
	 */
	public static String HtmlEncodeAcute(String str) {
		// .aacute; ---> &aacute;
		str = str.replaceAll(Constants.aACUTE_IN_URL, Constants.aACUTE_REGEXP);
		// .eacute; ---> &eacute;
		str = str.replaceAll(Constants.eACUTE_IN_URL, Constants.eACUTE_REGEXP);
		// .iacute; ---> &iacute;
		str = str.replaceAll(Constants.iACUTE_IN_URL, Constants.iACUTE_REGEXP);
		// .oacute; ---> &oacute;
		str = str.replaceAll(Constants.oACUTE_IN_URL, Constants.oACUTE_REGEXP);
		// .uacute; ---> &uacute;
		str = str.replaceAll(Constants.uACUTE_IN_URL, Constants.uACUTE_REGEXP);
		// .Aacute; ---> &Aacute;
		str = str.replaceAll(Constants.AACUTE_IN_URL, Constants.AACUTE_REGEXP);
		// .Eacute; ---> &Eacute;
		str = str.replaceAll(Constants.EACUTE_IN_URL, Constants.EACUTE_REGEXP);
		// .Iacute; ---> &Iacute;
		str = str.replaceAll(Constants.IACUTE_IN_URL, Constants.IACUTE_REGEXP);
		// .Oacute; ---> &Oacute;
		str = str.replaceAll(Constants.OACUTE_IN_URL, Constants.OACUTE_REGEXP);
		// .Uacute; ---> &Uacute;
		str = str.replaceAll(Constants.UACUTE_IN_URL, Constants.UACUTE_REGEXP);
		// .ntilde; ---> &ntilde;
		str = str.replaceAll(Constants.nTILDE_IN_URL, Constants.nTILDE_REGEXP);
		// .Ntilde; ---> &Ntilde;
		str = str.replaceAll(Constants.NTILDE_IN_URL, Constants.NTILDE_REGEXP);
		// .uuml; ---> &uuml;
		str = str.replaceAll(Constants.uUML_IN_URL, Constants.uUML_REGEXP);
		// .Uuml; ---> &Uuml;
		str = str.replaceAll(Constants.UUML_IN_URL, Constants.UUML_REGEXP);
		// .iexcl; ---> &iexcl;
		str = str.replaceAll(Constants.IEXCL_IN_URL, Constants.IEXCL_REGEXP);
		// .ordf; ---> &ordf;
		str = str.replaceAll(Constants.ORDF_IN_URL, Constants.ORDF_REGEXP);
		// .iquest; ---> &iquest;
		str = str.replaceAll(Constants.IQUEST_IN_URL, Constants.IQUEST_REGEXP);
		// .ordm; ---> &ordm;
		str = str.replaceAll(Constants.ORDM_IN_URL, Constants.ORDM_REGEXP);
		
		// HTML Encode
		str = HTMLEncode.encode(str);

		// &amp;aacute&#59; ---> &aacute;
		str = str.replaceAll(Constants.aACUTE_HTMLENCODED, Constants.aACUTE_REGEXP);
		// &amp;eacute&#59; ---> &eacute;
		str = str.replaceAll(Constants.eACUTE_HTMLENCODED, Constants.eACUTE_REGEXP);
		// &amp;iacute&#59; ---> &iacute;
		str = str.replaceAll(Constants.iACUTE_HTMLENCODED, Constants.iACUTE_REGEXP);
		// &amp;oacute&#59; ---> &oacute;
		str = str.replaceAll(Constants.oACUTE_HTMLENCODED, Constants.oACUTE_REGEXP);
		// &amp;uacute&#59; ---> &uacute;
		str = str.replaceAll(Constants.uACUTE_HTMLENCODED, Constants.uACUTE_REGEXP);
		// &amp;Aacute&#59; ---> &Aacute;
		str = str.replaceAll(Constants.AACUTE_HTMLENCODED, Constants.AACUTE_REGEXP);
		// &amp;Eacute&#59; ---> &Eacute;
		str = str.replaceAll(Constants.EACUTE_HTMLENCODED, Constants.EACUTE_REGEXP);
		// &amp;Iacute&#59; ---> &Iacute;
		str = str.replaceAll(Constants.IACUTE_HTMLENCODED, Constants.IACUTE_REGEXP);
		// &amp;Oacute&#59; ---> &Oacute;
		str = str.replaceAll(Constants.OACUTE_HTMLENCODED, Constants.OACUTE_REGEXP);
		// &amp;Uacute&#59; ---> &Uacute;
		str = str.replaceAll(Constants.UACUTE_HTMLENCODED, Constants.UACUTE_REGEXP);
		// &amp;ntilde&#59; ---> &ntilde;
		str = str.replaceAll(Constants.nTILDE_HTMLENCODED, Constants.nTILDE_REGEXP);
		// &amp;Ntilde&#59; ---> &Ntilde;
		str = str.replaceAll(Constants.NTILDE_HTMLENCODED, Constants.NTILDE_REGEXP);
		// &amp;uuml&#59; ---> &uuml;
		str = str.replaceAll(Constants.uUML_HTMLENCODED, Constants.uUML_REGEXP);
		// &amp;Uuml&#59; ---> &Uuml;
		str = str.replaceAll(Constants.UUML_HTMLENCODED, Constants.UUML_REGEXP);
		// &amp;iexcl&#59; ---> &iexcl;
		str = str.replaceAll(Constants.IEXCL_HTMLENCODED, Constants.IEXCL_REGEXP);
		// &amp;ordf&#59; ---> &ordf;
		str = str.replaceAll(Constants.ORDF_HTMLENCODED, Constants.ORDF_REGEXP);
		// &amp;iquest&#59; ---> &iquest;
		str = str.replaceAll(Constants.IQUEST_HTMLENCODED, Constants.IQUEST_REGEXP);
		// &amp;ordm&#59; ---> &ordm;
		str = str.replaceAll(Constants.ORDM_HTMLENCODED, Constants.ORDM_REGEXP);

		return str;
	}
	
    /*
     * If email boundary found in the source string, return true. Otherwise, return false.
     */
    public static boolean foundEmbeddedEmail(String sourceStr)
    {
    	boolean foundEmail = false;
    	// Case insensitive check for the embedded email patterns
    	sourceStr = StringUtils.trim(sourceStr).toUpperCase();
    	if (sourceStr.indexOf(Constants.CONTENT_TYPE_PATTERN) >= 0 && 
    		sourceStr.indexOf(Constants.BOUNDARY_PATTERN) >= 0)
    	{
    		foundEmail = true;
    	}
    	return foundEmail;
    }

	public static String trim(String s) {
		return (s == null ? EMPTY : s.trim());
	}

	/**
	 * Hungjen E. Sun Truncate a Java String against maximum allowed length
	 */
	public static final String truncate(String target, final int maxSize) {
		if (target == null)
			return EMPTY;
		return (target.trim().length() > maxSize ? target.substring(0, maxSize)
				: target);
	}

	public static boolean isEmptyOrNull(String str) {
		return (str == null || str.length() == 0);
	}

	public static String nonNull(String str) {
		return (str == null ? EMPTY : str.trim());
	}
	
	public static String cleanUpString(String s) {
		if (s == null) {
			return "0";
		}

		//String cleanString = s.trim();
		String cleanString = s;

		if (cleanString.equals("")) {
			return "0";
		}

		return cleanString;

	}

	public static int parseInt(String stringToConvert)
			throws NumberFormatException {
		return Integer.parseInt(cleanUpString(stringToConvert));
	}

	public static long parseLong(String stringToConvert)
			throws NumberFormatException {
		return Long.parseLong(cleanUpString(stringToConvert));
	}

	public static double parseDouble(String stringToConvert)
			throws NumberFormatException {
		return Double.parseDouble(cleanUpString(stringToConvert));
	}
}
