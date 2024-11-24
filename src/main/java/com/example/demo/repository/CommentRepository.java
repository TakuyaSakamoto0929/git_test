package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Comment;

//JpaRepositoryの引数には<理対象のエンティティのクラス,エンティティの主キー（ID）となるフィールドの型>
public interface  CommentRepository extends JpaRepository<Comment, Long>{

}
