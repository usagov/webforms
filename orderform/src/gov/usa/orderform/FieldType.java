/**
 *
 * Created July, 2011 for USA.gov print copy order form
 *
 **/
package gov.usa.orderform;

/**
 *
 * Store information for each input field. The name, label, patten, and required are loaded from LocalSetting.properties.
 * The value is from input and if it fails the validation, an error message will be added.
 *
 **/
public class FieldType {
   private String  name;      //input field name
   private String  label;     //input field display label
   private String  value;     //input value
   private boolean required;  //Is it required?
   private String  pattern;   //pattern used for validation
   private String  error;     //error message


   public void setName (String name) {
       this.name = name;
   }

   public void setLabel (String label) {
       this.label = label;
   }

   public void setValue (String value) {
       this.value = value;
   }

   public void setRequired (boolean required) {
       this.required = required;
   }

   public void setPattern (String pattern) {
       this.pattern = pattern;
   }

   public void setError (String error) {
       this.error = error;
   }

   public String getName () {
       return this.name;
   }

   public String getLabel () {
       return this.label;
   }

   public String getValue () {
       return this.value;
   }

   public boolean getRequired () {
       return this.required;
   }

   public String getPattern () {
       return this.pattern;
   }

   public String getError () {
       return this.error;
   }

   public boolean isRequired() {
       return this.required;
   }
}