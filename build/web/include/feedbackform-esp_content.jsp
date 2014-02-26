<script Language="Javascript">
 //Feedback form validation and submit functions
 function myForm_Validate(theForm){
	if(theForm.feedback.value==""){
	   alert ("Favor de escribir su pregunta o comentario antes de enviar su mensaje.");
	   theForm.feedback.focus();
	   return(false);
	}
	if(theForm.feedback.value.length>2500) {
	   alert ("Su comentario o pregunta no debe exceder los 2500 caracteres.");
	   theForm.feedback.focus();
	   return(false);
	}
	return (true);
 }

 function sendForm(){
	if (myForm_Validate(document.myForm)){
	   document.myForm.submit();
	}
 }
</script>
<noscript>You must select a feedback category and enter a feedback
message to submit the form.</noscript>

<p>Use el formulario que se presenta a continuaci&oacute;n para enviar
cualquier pregunta o comentario que tenga sobre GobiernoUSA.gov o el
Gobierno de los EE. UU. Si nos proporciona su correo electr&oacute;nico,
un miembro de nuestro equipo de informaci&oacute;n al p&uacute;blico le
responder&aacute; en dos d&iacute;as laborables. Nos puede escribir en
ingl&eacute;s o espa&ntilde;ol.</p>
<p>Si prefiere hablar en espa&ntilde;ol con alguno de nuestros
representantes, por favor llame libre de costo al 1&nbsp;(800)&nbsp;FED&nbsp;INFO
(1-800-333-4636) y presione la opci&oacute;n #2 para espa&ntilde;ol.</p>
<form name="myForm" id="myForm" method="POST"
	action="PostFeedback-esp.jsp" onSubmit="return false"><input
	type="hidden" name="category" value="254"> <br />
	<p><label for="email"> Direcci&oacute;n electr&oacute;nica: <span class="FontRequired">(s&oacute;lo si desea recibir respuesta)</span></label><br />
	<input type='TEXT' name='email' id='email' size='40' tabindex="110" maxlength='70'> </p>
	<p><label for="feedback"> Sus preguntas o comentarios: <span class="FontRequired">(no deben exceder los 2500 caracteres; requerido)</span><br /></label>
	<textarea name='feedback' id="feedback" cols="60" rows="7" wrap="hard" tabindex="111"></textarea> </p>
	<p>
	<label for='label_for_p_submit'> <input type='button'
	name='p_submit' id='label_for_p_submit' value='Enviar comentario'
	onClick='sendForm()' tabindex="112"> </label></p>
</form>