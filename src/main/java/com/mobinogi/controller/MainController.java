package com.mobinogi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mobinogi.mainService.SearchLoofService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final SearchLoofService searchLoofService;
	
	@GetMapping("/main")
	public String startMain() {
		
		return "main";
	}
	
	@GetMapping("db_update")
	public String db_update() {
		
		return "main";
	}
}
