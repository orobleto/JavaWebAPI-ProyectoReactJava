package com.educacionit.controladores;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Inicio {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String inicio(Locale locale, Model model) {
		model.addAttribute("saludo", "Hola desde Spring MVC 111 ");
		return "index";
	}

}
