package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CommentRequest;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

/**
 * commentサービスクラス
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentService {
	
	  /**
	   * コメント情報 Repository
	   */
	  @Autowired
	  private CommentRepository commentRepository;
	
	  
	  /**
	   * コメント主キー検索
	   */
	  public Comment findById(Long id) {
	    return commentRepository.findById(id).get();
	  }
	  
	  /**
	   * コメント更新処理
	   * 戻り値はないのでvoid
	   */
	  public void update(CommentRequest commentRequest) {
		  Comment comment = findById(commentRequest.getId());
		  comment.setContent(commentRequest.getComment());
		  commentRepository.save(comment);
	  }
	  
	  /**
	   * コメント削除処理
	   * 戻り値はないのでvoid
	   */
	  public void delete(Long id) {
		  Comment comment = findById(id);
		  commentRepository.delete(comment);
	  }
}
