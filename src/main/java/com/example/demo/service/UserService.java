package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

//依存性注入: @Serviceで宣言すると、Springがこのクラスを管理し、他のクラスで依存性注入を行えるようになる
@Service

//途中でエラーが発生した場合にロールバックするために使用する
//ロールバックとは:エラーが発生したときに変更を取り消す。（例：送金や振り込み）
@Transactional(rollbackFor = Exception.class)

//Serviceクラスの主な役割
//・データを取得・加工
//・@Transactionalを使いデータベース操作が失敗した場合に一連の操作をロールバックする
//・メンテナンスしやすく
public class UserService {

	
	  /**
	   * コメント情報 Repository
	   */
	  @Autowired
	  private UserRepository userRepository;
	
	  
	  /**
	   * ユーザーを主キーで検索
	   */
	  public User findById(Long id) {
	    return userRepository.findById(id).get();
	  }
	  
	  /**
	   * ユーザー更新処理
	   * 戻り値はないのでvoid
	   */
	  public void update(UserRequest userRequest) {
		  //データベースに保存を行う為Entityを使用
		  User user = findById(userRequest.getId());
		  user.setName(userRequest.getName());
		  user.setEmail(userRequest.getEmail());
		  user.setAge(userRequest.getAge());

		  //データベースに保存
		  userRepository.save(user);
	  }
	  
	  /**
	   * ユーザー削除処理
	   * 戻り値はないのでvoid
	   */
	  public void delete(Long id) {
		  User user = findById(id);
		  userRepository.delete(user);
	  }
	  
	  
}
