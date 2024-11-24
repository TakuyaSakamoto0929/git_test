package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * コメント情報 リクエストデータ
 */
@Data
public class CommentRequest {

	  /**
	   * ユーザーID
	   */
	  @NotNull
	  private Long id;
	  
	  /**
	   * コメント
	   */
	  @NotBlank(message = "{NotNull.required-field-name}")
	  @Size(max = 100, message = "名前は100桁以内で入力してください")
	  private String comment
	  ;
}
