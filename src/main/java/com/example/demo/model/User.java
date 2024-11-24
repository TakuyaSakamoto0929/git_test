package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
@Entity
@Data

@Table(name = "t_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
