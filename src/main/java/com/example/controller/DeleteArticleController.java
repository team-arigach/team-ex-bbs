package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;



public class DeleteArticleController {

	@Autowired
	private ArticleService articleService;

	
	/**
	 * 記事を削除します.
	 * 
	 * @param form
	 *            記事フォーム
	 * @return 記事登録画面
	 */
	@RequestMapping(value = "/deletearticle")
	public String deletearticle(ArticleForm form) {
		articleService.delete(form.getId());
		return "redirect:/joinedbbs";
	}
}
