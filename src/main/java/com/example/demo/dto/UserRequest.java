package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
//dtoクラス
//⇒クライアントとサーバー間でデータをやり取りするためのオブジェクト
//データベースの構造に依存せず、必要なデータだけを含めて構成できる
//外部に後悔したくない情報(passwordなど)は含めない様にすることでセキュリティも向上
public class UserRequest {

    private Long id;
	//(message = "{NotNull.required-field-name}")の部分はメッセージ内容を定義したファイルがありその中の値を出力する
	//場所はresourcesのvalidationMessages.proparties
	@NotBlank(message = "{NotNull.required-field-name}")
	private String name;
	@NotBlank(message = "{NotNull.required-field-email}")
	@Email
    private String email;
	@NotBlank(message = "{NotNull.required-field-age}")
	@Max(150)
    private String age;
}
