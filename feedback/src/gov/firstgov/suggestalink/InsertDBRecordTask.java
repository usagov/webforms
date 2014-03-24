/*
 * Created on May 4, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.firstgov.suggestalink;

import gov.firstgov.util.taskrunner.TaskInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * @author RussellGONeill
 *
 * This task will enter the suggestion into the suggestion database table.
 * It is designed to be used with a SuggeestALinkRunner.
 * @see	SuggestALinkRunner
 */
public class InsertDBRecordTask implements TaskInterface {
	/**
	 * The suggestion to be added to the database.
	 */
	private SuggestALinkBean suggestion;

	/**
	 * @param theSuggestalinkbean The suggestion to set.
	 */
	public void setBean(Object bean) {
		suggestion = (SuggestALinkBean) bean;
	}

	/**
	 * @param suggestion	The suggestion to be added to the database.
	 */
	public void setSuggestion(SuggestALinkBean theSuggestion) {
		suggestion = theSuggestion;
	}

	/**
	 * @return	True if the insertion into the database is successful, false otherwise.
	 */
	public boolean execute() {
		boolean retValue = true;
		Context ctx = null;
		Connection conn = null;
		PreparedStatement insertSuggestion = null;
		final String sql = "INSERT INTO FIRSTGOV.SUGGESTEDLINKS (CREATEDDATE,URL,FNAME,LNAME,EMAIL,DAYPHONE,DESCRIPTION,LOCATIONSUGGESTION,LANG) VALUES (?,?,?,?,?,?,?,?,?)";
		Logger logger = SuggestALinkLogger.getLogger();
		try {
		  logger.debug("Getting contxt to write a database");
		  ctx = new InitialContext();
		  logger.debug("Getting datasource and connection");
		  DataSource ds = (DataSource) ctx.lookup (SuggestALinkConstants.DATASOURCE_JNDI);
		  conn = ds.getConnection();
		  logger.debug("Preparing statement");
		  insertSuggestion = conn.prepareStatement(sql);
		  insertSuggestion.setDate(1,new java.sql.Date(new java.util.Date().getTime()));
		  insertSuggestion.setString(2,suggestion.getUrl());
		  insertSuggestion.setString(3,suggestion.getFname());
		  insertSuggestion.setString(4,suggestion.getLname());
		  insertSuggestion.setString(5,suggestion.getEmail());
		  insertSuggestion.setString(6,suggestion.getDayPhone());
		  insertSuggestion.setString(7,suggestion.getDescription());
		  insertSuggestion.setString(8,suggestion.getLocationSuggestion());
		  insertSuggestion.setString(9,suggestion.getLang());
		  logger.debug("Executing statement");
		  insertSuggestion.execute();
		  insertSuggestion.close();
		  insertSuggestion=null;
		  conn.close();
		  conn=null;
		 }
		 catch (Exception e) {
		 	logger.error(e.toString(),e);
		 	retValue = false;
		 }
		 finally {    
		 	try { 
		 		ctx.close(); 
		 	} catch (Exception e) {
			 	logger.error(e.toString(),e);
		 		retValue = false; }
		 	try { 
		 		if (insertSuggestion != null) insertSuggestion.close(); 
		 	} catch (Exception e) {  
			 	logger.error(e.toString(),e);
		 		retValue = false; }
		 	try { 
		 		if (conn != null) conn.close(); 
		 	} catch (Exception e) {  
			 	logger.error(e.toString(),e);
		 		retValue = false; }
		 }
		return retValue;
	}
}
