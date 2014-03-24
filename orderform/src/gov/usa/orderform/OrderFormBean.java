/**
 *
 * Created July, 2011 for USA.gov print copy order form
 *
 **/
package gov.usa.orderform;

import java.text.*;
import java.util.*;

import javax.servlet.http.*;
import org.apache.log4j.*;

/**
 * 
 * The names are the names used as labels for each field and attribute in
 * LocalSetting.properties.
 * 
 **/
public class OrderFormBean {
	private static Logger logger = Logger.getLogger(OrderFormBean.class);
	private static String newline = System.getProperty("line.separator");

	// input field information, added quant1, quant2, and changed Total
	private final int Total = 16;
	private final String fieldNames[] = { "firstname", "lastname",
			"organization", "address1", "address2", "city", "state", "zip",
			"phone", "handbook", "guia", "quantity", "quant1", "quant2", "email",
			"explanation"};

	private enum fieldIndexes {
		firstname, lastname, organization, address1, address2, city, state, zip, phone, handbook, guia, quantity, quant1, quant2, email, explanation
	};

	private final String attributeNames[] = { "name", "label", "required",
			"pattern" };

	private enum attributes {
		name, label, required, pattern
	};

	private FieldType fields[] = new FieldType[Total];
	private String blankInput = "Blank Input";
	private String invalidInput = "Invalid Input";

	public void setFields(FieldType fields[]) {
		this.fields = fields;
	}

	public FieldType[] getFields() {
		return this.fields;
	}

	// replaced by getQuant1 and getQuant2
	public int getQuantity() {
		if (fields[fieldIndexes.quantity.ordinal()].getValue() == null) {
			return -1;
		}
		return Integer.parseInt(fields[fieldIndexes.quantity.ordinal()]
				.getValue());
	}

	// return number of English Copies requested
	public int getQuant1() {
		if (fields[fieldIndexes.quant1.ordinal()].getValue() == null) {
			return -1;
		}

		return Integer.parseInt(fields[fieldIndexes.quant1.ordinal()]
				.getValue());
	}

	// return number of Spanish Copies requested
	public int getQuant2() {
		if (fields[fieldIndexes.quant2.ordinal()].getValue() == null) {
			return -1;
		}
		return Integer.parseInt(fields[fieldIndexes.quant2.ordinal()]
				.getValue());
	}

//	// return number of Spanish Copies requested
//	public String getHandbook() {
//		if (fields[fieldIndexes.handbook.ordinal()].getValue() == null) {
//			return "";
//		}
//		return fields[fieldIndexes.handbook.ordinal()].getValue().toString();
//	}
//
//	// return number of Spanish Copies requested
//	public String getGuia() {
//		if (fields[fieldIndexes.guia.ordinal()].getValue() == null) {
//			return "";
//		}
//		return fields[fieldIndexes.guia.ordinal()].getValue().toString();
//	}

	// get local settings
	public boolean getLocatSettings(ResourceBundle rb) {
		boolean success = true;

		if (!Utils.isEmptyStr(rb.getString("error.blankinput"))) {
			this.blankInput = rb.getString("error.blankinput");
		}
		if (!Utils.isEmptyStr(rb.getString("error.invalidinput"))) {
			this.invalidInput = rb.getString("error.invalidinput");
		}

		// get information for input fields
		for (int i = 0; i < Total; i++) {
			fields[i] = new FieldType();
			String fn = "field." + fieldNames[i];
			String name, label, required, pattern;
			name = rb.getString(fn + "."
					+ attributeNames[attributes.name.ordinal()]);
			if (Utils.isEmptyStr(name)) {
				// log
				success = false;
			} else {
				fields[i].setName(name.trim());
			}

			label = rb.getString(fn + "."
					+ attributeNames[attributes.label.ordinal()]);
			if (Utils.isEmptyStr(label)) {
				// log
				success = false;
			} else {
				fields[i].setLabel(label.trim());
			}

			required = rb.getString(fn + "."
					+ attributeNames[attributes.required.ordinal()]);
			if (Utils.isEmptyStr(required)) {
				required = "false";
			}
			fields[i].setRequired(Boolean.parseBoolean(required.trim()));

			pattern = rb.getString(fn + "."
					+ attributeNames[attributes.pattern.ordinal()]);
			if (pattern == null) {
				pattern = "";
			}
			fields[i].setPattern(pattern.trim());
		}

		return success;
	}

	// read parameters and validate them
	public Vector readParameters(HttpServletRequest request) {
		Vector errors = new Vector();
		boolean quantFlag = true;
		boolean quant1Flag = true;
		boolean quant2Flag = true;
		int quantIndex = 0;

		for (int i = 0; i < Total; i++) {
			FieldType field = fields[i];
			String value = request.getParameter(field.getName());

			if (value == null) {
				value = "";
			}
			value = value.trim();
			if (field.isRequired() && Utils.isEmptyStr(value)) { // blank
																	// required
																	// field --
																	// error
				if (field.getName().equals("quantity")) {
					logger.error("quantity is empty");
					quantFlag = false;
					quantIndex = i;
				} else if (field.getName().equals("quant1")) {
					logger.error("quant1 is empty");
					quant1Flag = false;
				} else if (field.getName().equals("quant2")) {
					logger.error("quant2 is empty");
					quant2Flag = false;
				} else {

					logger.error(field.getName() + " is Blank\n");
					field.setError(this.blankInput);
					errors.add(field);
				}
			} else if (!Utils.isEmptyStr(value)
					&& !Utils.isEmptyStr(field.getPattern())) { // check the
																// pattern of
																// non-blank
																// field
				if (Utils.validation(value, field.getPattern())) { // match
					field.setValue(value);
				} else { // invalidate
					field.setError(this.invalidInput);
					errors.add(field);
				}
			} else {
				field.setValue(value);
			}
		}

		if (!quant1Flag && !quant2Flag) {
			// logger.error("quant1 and 2 empty");
			if (!quantFlag) {
				// logger.error("quantity empty");
				fields[quantIndex].setError(this.blankInput);
				errors.add(fields[quantIndex]);
			}
		}
		return errors;
	}

	// get content for email
	public String getEmailContent() {
		// create email content
		String name = Utils.addDayToName("order.txt");
		Utils.writeDoc(name, "Getting email Content");
		StringBuffer content = new StringBuffer();
		FieldType ft[] = this.fields;

		content.append("Last Name: "
				+ ft[fieldIndexes.lastname.ordinal()].getValue() + newline);
		content.append("First Name: "
				+ ft[fieldIndexes.firstname.ordinal()].getValue() + newline);
		content.append("Organization Name: "
				+ ft[fieldIndexes.organization.ordinal()].getValue() + newline);
		content.append("Address 1: "
				+ ft[fieldIndexes.address1.ordinal()].getValue() + newline);
		content.append("Address 2: "
				+ ft[fieldIndexes.address2.ordinal()].getValue() + newline);
		content.append("City: " + ft[fieldIndexes.city.ordinal()].getValue()
				+ newline);
		content.append("State: " + ft[fieldIndexes.state.ordinal()].getValue()
				+ newline);
		content.append("Zip: " + ft[fieldIndexes.zip.ordinal()].getValue()
				+ newline);
		content.append("Phone: " + ft[fieldIndexes.phone.ordinal()].getValue()
				+ newline);
		// replaced by quant1 and quant2
		// content.append(ft[fieldIndexes.doctype.ordinal()].getValue() + ": "
		// + ft[fieldIndexes.quantity.ordinal()].getValue() + newline);
		if(getQuant1()>0)
			content.append(ft[fieldIndexes.handbook.ordinal()].getValue()+": " + ft[fieldIndexes.quant1.ordinal()].getValue() + newline);
		else
			content.append(ft[fieldIndexes.handbook.ordinal()].getValue()+": " + "0" + newline);
		if(getQuant2()>0)
			content.append(ft[fieldIndexes.guia.ordinal()].getValue()+": " + ft[fieldIndexes.quant2.ordinal()].getValue() + newline);
		else
			content.append(ft[fieldIndexes.guia.ordinal()].getValue()+": " + "0" + newline);

		content.append("Explanation on use: "
				+ ft[fieldIndexes.explanation.ordinal()].getValue() + newline);
		

		return content.toString();
	}

	// get content for file output
	public String getFileContent() {
		String output = "";
		// output.append(ft[fieldIndexes.quantity.ordinal()].getValue() + "\t");
		// // QUANTITY
		// output.append(ft[fieldIndexes.quant1.ordinal()].getValue() + "\t");
		// // QUANT1 eng
		// output.append(ft[fieldIndexes.quant2.ordinal()].getValue() + "\t");
		// // QUANT2 spanish
		// output.append(ft[fieldIndexes.doctype.ordinal()].getValue() + "\t");
		// // DEPT

		FieldType ft[] = this.fields;
		if (getQuant1() > 0) {
			output = getFileContentHelper(ft[fieldIndexes.handbook.ordinal()].getValue(), getQuant1());
			if (getQuant2() > 0)
				output = output + getFileContentHelper(ft[fieldIndexes.guia.ordinal()].getValue(), getQuant2());
		} else if (getQuant2() > 0) {
			output = getFileContentHelper(ft[fieldIndexes.guia.ordinal()].getValue(), getQuant2());
		} else if (getQuantity() > 0) {
			output = getFileContentHelper(
					ft[fieldIndexes.handbook.ordinal()].getValue(),
					getQuantity());
		}
		return output;

	}

	private String getFileContentHelper(String dept, int quant) {
		// create output
		StringBuffer output = new StringBuffer();
		FieldType ft[] = this.fields;

		Date day = new Date();
		SimpleDateFormat dFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm");

		output.append(dFormat.format(day) + "\t"); // DATE
		output.append(tFormat.format(day) + "\t"); // TIME
		output.append(ft[fieldIndexes.firstname.ordinal()].getValue() + "\t"); // FIRST
																				// NAME
		output.append(ft[fieldIndexes.lastname.ordinal()].getValue() + "\t"); // LAST
																				// NAME
		output.append(ft[fieldIndexes.address1.ordinal()].getValue() + "\t"); // ADDRESS1
		output.append(ft[fieldIndexes.address2.ordinal()].getValue() + "\t"); // ADDRESS2
		output.append(ft[fieldIndexes.city.ordinal()].getValue() + "\t"); // CITY
		output.append(ft[fieldIndexes.state.ordinal()].getValue() + "\t"); // STATE
		output.append(ft[fieldIndexes.zip.ordinal()].getValue() + "\t"); // ZIP
		output.append(quant + "\t"); // QUANTITY
		output.append(dept + "\t"); // DEPT
		output.append(ft[fieldIndexes.email.ordinal()].getValue() + newline); // EMAIL

		return output.toString();
	}
}
