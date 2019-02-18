function runTest(form, button)  {

	Ret = false;

	if (button.name == "1") Ret = testBox1(form);

	if (button.name == "2") Ret = testBox2(form);

	if (button.name == "3") Ret = testBox3(form);

	if (button.name == "4") Ret = testBox4(form);

	if (Ret)

		alert ("Entrada correcta!");

}



function testBox1(form) {

	Ctrl = form.inputbox1;

	if (Ctrl.value == "" || Ctrl.value.indexOf ('@', 0) == -1) {

		validatePrompt (Ctrl, "Entrar un E-mail valido")

		return (false);

	} else

		return (true);

}



function testBox2(form) {

	Ctrl = form.inputbox2;

	if (Ctrl.value.length != 5) {

		validatePrompt (Ctrl, "Escribir CINCO caracteres")

		return (false);

	} else

		return (true);

}



function testBox3(form) {

	Ctrl = form.inputbox3;

	if (Ctrl.value.length < 3) {

		validatePrompt (Ctrl, "Entrar 3 o mas caracteres")

		return (false);

	} else

		return (true);

}



function testBox4(form) {

	Ctrl = form.inputbox4;

	if (Ctrl.value == "") {

		validatePrompt (Ctrl, "Este campo no puede estar vacio")

		return (false);

	} else

		return (true);

}



function runSubmit (form, button)  {

	if (!testBox1(form)) return;

	if (!testBox2(form)) return;

	if (!testBox3(form)) return;

	if (!testBox4(form)) return;

	alert ("Todas la entradas OK!");

	//document.test.submit();	// un-comment to submit form

	return;

}



function validatePrompt (Ctrl, PromptStr) {

	alert (PromptStr)

	Ctrl.focus();

	return;

}





function loadDoc() {

	// initial focus; use if needed

	//document.test.inputbox1.focus ();

	return;

}

