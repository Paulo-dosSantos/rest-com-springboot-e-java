package br.com.erudio.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.converters.NumberConverter;
import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.math.SimpleMath;

@RestController
public class MathController {
	
	private static final String template="Hello, %s!";
	
	private SimpleMath math=new SimpleMath();
	private final AtomicLong counter= new AtomicLong();
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}",method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value="numberOne")String numberOne,
			@PathVariable(value="numberTwo")String numberTwo) throws Exception{
		if(!NumberConverter.isNumeric(numberOne)|| !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(
					"Por favor, insira um valor numérico válido");
		}
		
		return math.sum(NumberConverter.convertToDouble(numberOne),
				NumberConverter.convertToDouble(numberTwo));
		
		
	}

	@RequestMapping(value="/subtraction/{numberOne}/{numberTwo}",method=RequestMethod.GET)
	public Double subtraction(
			@PathVariable(value="numberOne")String numberOne,
			@PathVariable(value="numberTwo")String numberTwo) throws Exception{
		if(!NumberConverter.isNumeric(numberOne)|| !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(
					"Por favor, insira um valor numérico válido");
		}
		return math.subtraction(NumberConverter.convertToDouble(numberOne),
				NumberConverter.convertToDouble(numberTwo));
		
		
		
	}

	
	@RequestMapping(value="/multiplication/{numberOne}/{numberTwo}",method=RequestMethod.GET)
	public Double multiplication(
			@PathVariable(value="numberOne")String numberOne,
			@PathVariable(value="numberTwo")String numberTwo) throws Exception{
		if(!NumberConverter.isNumeric(numberOne)|| !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(
					"Por favor, insira um valor numérico válido");
		}
		return math.multiplication(NumberConverter.convertToDouble(numberOne),
				NumberConverter.convertToDouble(numberTwo));
		
		
		
		
	}
	@RequestMapping(value="/division/{numberOne}/{numberTwo}",method=RequestMethod.GET)
	public Double division(
			@PathVariable(value="numberOne")String numberOne,
			@PathVariable(value="numberTwo")String numberTwo) throws Exception{
		if(!NumberConverter.isNumeric(numberOne)|| !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(
					"Por favor, insira um valor numérico válido");
		}
		
		return math.division(NumberConverter.convertToDouble(numberOne),
				NumberConverter.convertToDouble(numberTwo));
		
		
		
	}

	@RequestMapping(value="/average/{numberOne}/{numberTwo}",method=RequestMethod.GET)
	public Double average(
			@PathVariable(value="numberOne")String numberOne,
			@PathVariable(value="numberTwo")String numberTwo) throws Exception{
		if(!NumberConverter.isNumeric(numberOne)|| !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(
					"Por favor, insira um valor numérico válido");
		}
		
		return math.average(NumberConverter.convertToDouble(numberOne),
				NumberConverter.convertToDouble(numberTwo));
		
		
		
	}
	@RequestMapping(value="/squareRoot/{number}",method=RequestMethod.GET)
	public Double squareRoot(
			@PathVariable(value="number")String number) throws Exception{
		if(!NumberConverter.isNumeric(number)) {
			throw new UnsupportedMathOperationException(
					"Por favor, insira um valor numérico válido");
		}
		return math.squareRoot(NumberConverter.convertToDouble(number));
	}

	
}
