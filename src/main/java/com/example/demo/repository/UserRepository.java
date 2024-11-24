package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

//JpaRepositoryの引数には<理対象のエンティティのクラス,エンティティの主キー（ID）となるフィールドの型>
public interface  UserRepository extends JpaRepository<User, Long>{

}
