package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorSuma {
	
	@RequestMapping("/evaluar")
	public ModelAndView irAEvaluarCuandoNoSeLePasanValores(){
		return new ModelAndView("evaluar");
	}
	
	@RequestMapping(path = "/evaluar/{num1}/{num2}", method = RequestMethod.GET)
	public ModelAndView evaluarNumeros(@PathVariable("num1") String num1, @PathVariable("num2") String num2){
		
		try{
			
			Integer numero1 = Integer.parseInt(num1);
			Integer numero2 = Integer.parseInt(num2);
			ModelMap model = new ModelMap();
			model.put("numero1", numero1);
			model.put("numero2", numero2);
			model.put("suma", numero1 + numero2);
			
			return new ModelAndView("evaluar", model);
		}
		catch(NumberFormatException e){
			return new ModelAndView("redirect:/error");
		}
	}
	
	@RequestMapping("/error")
	public ModelAndView irAError(){
		return new ModelAndView("error");
	}
}
