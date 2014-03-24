/**
 * Created on July, 2011
 *
 **/
package gov.usa.orderform;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

/**
 * Controller for Order Form of printed copies. It will save the orders in a
 * file or sent an e-mail if it is bulk order. The locat settings are loaded
 * from LocalSettings.properties. It includes the name and label of each input
 * files, the minmum number of orders for bulk order, output file directory or
 * email address for bulk order.
 * 
 **/
public class OrderFormController extends HttpServlet {
	private static Logger logger = Logger.getLogger(OrderFormController.class);

	// general information
	private String successRedirect = "/orderform/thankyou.html"; // message sent
																	// successfully
	private String failureRedirect = "/orderform/error.jsp"; // message failure
	private int minmumBulkOrder = 11;
	private String outputFile = "order.txt";
	private String toEmail = "action.handbook@gsa.gov";
	private String fromEmail = "Bulk.2008.CAH.Orders@pueblo.gsa.gov";
	private String emailHost = "localhost";
	private String serverError = "Server Error!";
	private String engCode = "D30";
	private String esCode = "D55";

	protected ResourceBundle rb = ResourceBundle.getBundle("LocalSettings");
	OrderFormBean form = new OrderFormBean();
	Vector errors = new Vector();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		boolean success = true;

		try {
			// set locat settings
			success = getLocatSettings();

			if (!success) {
				logger.error("The getLocatSettings function returns an error.");
				request.getSession().setAttribute("errorString", serverError);
				response.sendRedirect(failureRedirect);
			}

			// read parameters and validate them
			errors = form.readParameters(request);

			if (errors.size() > 0) { // found errors, forward to error page
				request.getSession().setAttribute("errorMessage", errors);
				response.sendRedirect(failureRedirect);
				Iterator it = errors.iterator();
				while (it.hasNext()) {
					FieldType ft = (FieldType) it.next();
					logger.error("Label: " + ft.getLabel() + "Error: "
							+ ft.getError());
				}
			} else {
				// replaced by logic for quant1 and quant2
				// int quantity = form.getQuantity();
				
				// choose larger of english and spanish quantities, then chooses whether to send an email or append a log
				// if decided to base bulk orders on sum, replace quant1 and quant2 declarations, as well as quantity with:
				// quantity = form.getQuant1()+form.getQuant2(); 
				int quant1 = form.getQuant1();
				int quant2 = form.getQuant2();
//				logger.error("quant1 = "+ quant1);
//				logger.error("quant2 = "+ quant2);
				int quantity;
				if (quant1 == -1 && quant2 == -1){//use old form
					System.out.println("using old form.");
					quantity = form.getQuantity();
				}else
					quantity = quant1>quant2 ? quant1 : quant2;
				if (quantity >= this.minmumBulkOrder) { // bulk order
					success = sentEmail();
				} else { // append to a text file
					success = appendToFile();
				}
				
				if (success) {
					response.sendRedirect(successRedirect);
				} else {
					request.getSession().setAttribute("errorString",
							serverError);
					response.sendRedirect(failureRedirect);
				}
			}
		} catch (Exception e) {
			logger.error("At the end of OrderFormController ", e);
			request.getSession().setAttribute("errorString", serverError);
			response.sendRedirect(failureRedirect);
		}
	}

	// get local settings
	private boolean getLocatSettings() {

		boolean success = true;
		// get general information
		if (!Utils.isEmptyStr(rb.getString("info.successRedirect"))) {
			this.successRedirect = rb.getString("info.successRedirect");
		}
		if (!Utils.isEmptyStr(rb.getString("info.failureRedirect"))) {
			this.failureRedirect = rb.getString("info.failureRedirect");
		}
		if (!Utils.isEmptyStr(rb.getString("info.minmumBulkOrder"))) {
			this.minmumBulkOrder = Integer.parseInt(rb
					.getString("info.minmumBulkOrder"));
		}
		if (!Utils.isEmptyStr(rb.getString("info.outputFile"))) {
			this.outputFile = rb.getString("info.outputFile");
		}
		if (!Utils.isEmptyStr(rb.getString("info.toEmail"))) {
			this.toEmail = rb.getString("info.toEmail");
		}
		if (!Utils.isEmptyStr(rb.getString("info.fromEmail"))) {
			this.fromEmail = rb.getString("info.fromEmail");
		}
		if (!Utils.isEmptyStr(rb.getString("info.emailHost"))) {
			this.emailHost = rb.getString("info.emailHost");
		}
		
		if (!Utils.isEmptyStr(rb.getString("error.servererror"))) {
			this.serverError = rb.getString("error.servererror");
		}

		if (Utils.isEmptyStr(this.successRedirect)
				|| Utils.isEmptyStr(this.failureRedirect)
				|| this.minmumBulkOrder < 1
				|| Utils.isEmptyStr(this.outputFile)
				|| Utils.isEmptyStr(this.toEmail)) {
			logger.error("It is not able to read general information from property file.");
			success = false;

			return success;
		}

		success = form.getLocatSettings(rb);

		return success;
	}

	// sent an email for bulk order
	private boolean sentEmail() {
		boolean success = false;

		// create email content
		String content = form.getEmailContent();
		if (!Utils.isEmptyStr(content)) {
			// sent email
			logger.info("Sent out an email.");
			String subject = "Print Copy Bulk Order";
			
			success = Utils.sentEmail(this.toEmail, this.fromEmail, subject,
					content, this.emailHost);
		}
		

		return success;
	}

	// write to a file for non-bulk order
	private boolean appendToFile() {
		boolean success = false;

		// create output
		String output = form.getFileContent();
		if (!Utils.isEmptyStr(output)) {
			// append to a file
			logger.info("Append a new order to a file.");
			
			// add date to the file name
			String name = Utils.addDayToName(this.outputFile);
			// logger.info("The file name: " + name);
			success = Utils.writeDoc(name, output);
		}

		return success;
	}
}