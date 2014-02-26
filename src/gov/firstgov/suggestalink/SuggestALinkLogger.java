/*
 * Created on May 11, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.firstgov.suggestalink;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author RussellGONeill
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SuggestALinkLogger {
	static private SuggestALinkLogger theLogger = null;
	static Logger log4jLogger =  Logger.getLogger(SuggestALinkLogger.class.getName());
	private SuggestALinkLogger() {
		InputStream is = SuggestALinkLogger.class.getResourceAsStream(SuggestALinkConstants.LOG4JCONFIG);
		Properties p = new Properties();
		try {
			p.load(is);
		}
		catch (IOException ioe) {System.out.println(ioe.toString());}
		PropertyConfigurator.configure(p);
	}
	public static synchronized Logger getLogger() {
		if (theLogger == null) theLogger = new SuggestALinkLogger();
		return log4jLogger;
	}
}
