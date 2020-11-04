package com.lyh.guguanjia.controller;

import com.baomidou.kaptcha.Kaptcha;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {


	@Autowired
	private Kaptcha kaptcha;

	@GetMapping("/render")
	public void render() {
		kaptcha.render();
	}


}
