package com.educacionit.validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validarCorreoelectronico")
public class ValidarCorreo implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String correo = value.toString().trim();
		
		if (!esCorreo(correo)) {
			throw new ValidatorException(new FacesMessage("No es un correo electronico valido"));
		}else if (!correo.endsWith("@educacionit.com")) {
			throw new ValidatorException(new FacesMessage("No es un dominio valido, debe ser de educaciont it"));
		}
	}

	private boolean esCorreo(String correo) {
		Pattern patron = Pattern.compile("([a-z0-9]+(.?[a-z0-9])*)+@(([a-z]+).([a-z]+))");
		Matcher comparador = patron.matcher(correo);

		return comparador.find();
	}

}
