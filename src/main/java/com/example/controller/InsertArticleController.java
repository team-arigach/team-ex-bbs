package com.example.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.JoinedArticleForm;
import com.example.form.JoinedCommentForm;
import com.example.service.ArticleServise;

@Controller
@RequestMapping
public class InsertArticleController {
	@Autowired
	private ArticleServise articleService;

	/**
	 * 記事のフォームを初期化します.
	 * 
	 * @return 記事フォーム
	 */
	@ModelAttribute
	public JoinedArticleForm setUpArticleForm() {
		return new JoinedArticleForm();
	}

	/**
	 * コメントのフォームを初期化します.
	 * 
	 * @return コメントフォーム
	 */
	@ModelAttribute
	public JoinedCommentForm setUpCommentForm() {
		return new JoinedCommentForm();
	}

	/**
	 * 掲示板を表示します.
	 * 
	 * @param model モデル
	 * @return 掲示板画面
	 */
	@RequestMapping
	public String form(Model model) {
		// 計測スタート
		LocalDateTime time = LocalDateTime.now();
		
		List<Article> articleList = articleService.findAll();
		
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
		
		return "joined/joinedbbsview";
	}

	/**
	 * 記事を投稿します.
	 * 
	 * @param form
	 *            フォーム
	 * @param result
	 *            リザルト
	 * @param model
	 *            モデル
	 * @return 掲示板画面
	 */
	@RequestMapping(value = "/postarticle")
	public String postarticle(@Validated  JoinedArticleForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return form(model);
		}
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleService.save(article);
		return "redirect:/joinedbbs";
	}
}
