package com.lyh.guguanjia.controller;


import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.lyh.guguanjia.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = KaptchaException.class)
	public Result kaptchaExceptionHandler(KaptchaException kaptchaException) {
		if (kaptchaException instanceof KaptchaIncorrectException ) {
			return new Result(false,"验证码不正确",null);
		} else if (kaptchaException instanceof KaptchaNotFoundException ) {
			return new Result(false,"验证码未找到",null);
		} else if (kaptchaException instanceof KaptchaTimeoutException ) {
			return new Result(false,"验证码过期",null);
		} else {
			return new Result(false,"验证码渲染失败",null);
		}

	}

	@ExceptionHandler(Exception.class)
	public Result doHandler(Exception e){
		return new Result(false,e.getMessage(),null);
	}
}
