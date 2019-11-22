package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * コメントを登録します.
	 * 
	 * @param comment
	 *            コメント情報
	 * @return 登録したコメント情報
	 */
	public void save(Comment comment) {
		commentRepository.insert(comment);
	}
}
