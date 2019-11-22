package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Comment;
import com.example.form.JoinedCommentForm;


@Controller
public class InsertCommentController {
	/**
	 * コメントを投稿します.
	 * 
	 * @param form
	 *            フォーム
	 * @param result
	 *            リザルト
	 * @param model
	 *            モデル
	 * @return 掲示板画面
	 */
	@RequestMapping(value = "/postcomment")
	public String postcomment(@Validated JoinedCommentForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return form(model);
		}
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		commentService.save(comment);
		return "redirect:/joinedbbs";
	}

}
