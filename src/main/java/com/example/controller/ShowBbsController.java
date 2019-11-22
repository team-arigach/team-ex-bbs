package com.example.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;


public class ShowBbsController {

	@Autowired
	private ArticleService service;
	
	@RequestMapping
	public String form(Model model) {
		// 計測スタート
		LocalDateTime time = LocalDateTime.now();
		
		List<Article> articleList = service.findAll();
		
		// 記事サイズをスコープに格納する
		model.addAttribute("listSize", articleList.size());
		// 件数が多いと表示は時間がかかるので最初の10個のみスコープへ格納する
		if(articleList.size() >= 10) {
			articleList = articleList.subList(0, 10);
		}
		// 記事リストをスコープに格納する
		model.addAttribute("articleList", articleList);

		// 計測開始からここまでの時間の差分を取得しスコープへ格納
		Long lapTime = ChronoUnit.MILLIS.between(time, LocalDateTime.now());
		model.addAttribute("lapTime", lapTime);
		
		return "bbsview";
	}

}
