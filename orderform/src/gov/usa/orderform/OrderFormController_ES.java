/**
 *
 * Created July, 2011 for USA.gov print copy order form
 *
 **/
package gov.usa.orderform;

import java.io.*;
import java.util.ResourceBundle;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

/**
 *
 * Controller for Spanish form
 *
 **/
public class OrderFormController_ES extends OrderFormController {
    private static Logger logger =Logger.getLogger(OrderFormController_ES.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response)
      	throws IOException, ServletException {
        rb = ResourceBundle.getBundle("LocalSettings_es");  //use a different property file
        super.doPost(request, response);
    }
}