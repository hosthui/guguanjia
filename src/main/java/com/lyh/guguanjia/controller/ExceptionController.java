package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {


	@ExceptionHandler(Exception.class)
	public Result doHandler(Exception e){
		return new Result(false,e.getMessage(),null);
	}
}
