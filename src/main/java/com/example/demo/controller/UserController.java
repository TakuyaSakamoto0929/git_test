package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.UserRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {
	
	private final UserRepository repository;

    //@Autowired ← コンストラクタが１つの場合、@Autowiredは省略できます
    public UserController(UserRepository repository) {
        this.repository = repository;
    }
    @Autowired 
    private UserService userService;
    /**
     * すべてのユーザーを取得
     * @param comment
     * @param model
     * @return
     */
    @GetMapping("/")
    public String getAllUsers(User user, Model model) {
//    	repositoryのfindAllメソッドを使用して、DBのt_userテーブルの情報を全件取得
        model.addAttribute("users", repository.findAll());
        return "user/list";
    }
	
    @PostMapping("/add")
    public String addUser(@Validated User user,
            BindingResult result, Model model) {
//    	エラーの有無に限らす全件を取得処理を行う必要があるので記載
        model.addAttribute("users", repository.findAll());
        if (result.hasErrors()) {
            return "user/list";
        }
//エンティティがまだデータベースに存在しない場合、save() を使って新しいデータを保存します。
        repository.save(user);
     // ルートパス("/")に、リダイレクトします
        return "redirect:/user/";
    }

    
    
    /**
     * ユーザー編集画面
     * @PathVariable("id") Long id
     * ⇒URLの{id}の部分の値を取得することが出来る
     */
   @GetMapping("/{id}/edit")
   public String displayEdit(@PathVariable("id") Long id,Model model) {
	   //上で@Autowiredの記載必要あり
   	User user = userService.findById(id);
   	
   	//dtoへ移す(クライアントにデータを為に)
   	UserRequest userRequest = new UserRequest();
   	userRequest.setId(user.getId());
   	userRequest.setName(user.getName());
   	userRequest.setEmail(user.getEmail());
   	userRequest.setAge(user.getAge());

   	//Dtoクラスをクライアントに返す
       model.addAttribute("userRequest", userRequest);
   	return "/user/edit";
   }
   
   
   /**
    * ユーザー更新処理
    * @param id
    * @param model
    * @return
    */
   @PostMapping("/update")
   public String update(@Validated @ModelAttribute UserRequest userRequest,BindingResult result,Model model) {
       
   	if(result.hasErrors()) {
	      List<String> errorList = new ArrayList<String>();
	      for (ObjectError error : result.getAllErrors()) {
	          errorList.add(error.getDefaultMessage());
	        }
	      model.addAttribute("validationError", errorList);
	      return "/user/edit";
   	}
       // ユーザー情報の更新
   	userService.update(userRequest);
       return "redirect:/user/";
   }
   
   
   /**
    * ユーザー削除画面
    * @PathVariable("id") Long id
    * ⇒URLの{id}の部分の値を取得することが出来る
    */
  @GetMapping("/{id}/delete")
  public String displayDelete(@PathVariable("id") Long id,Model model) {
	   //上で@Autowiredの記載必要あり
  	User user = userService.findById(id);
  	
  	//dtoへ移す(クライアントにデータを為に)
  	UserRequest userRequest = new UserRequest();
  	userRequest.setId(user.getId());
  	userRequest.setName(user.getName());
  	userRequest.setEmail(user.getEmail());
  	userRequest.setAge(user.getAge());

  	//Dtoクラスをクライアントに返す
      model.addAttribute("userRequest", userRequest);
  	return "/user/delete";
  }
  
  
  /**
   * ユーザー更新処理
   * @param id
   * @param model
   * @return
   */
  @PostMapping("/delete")
  public String delete(@ModelAttribute UserRequest userRequest) {
      
      // ユーザー情報の更新
  	userService.delete(userRequest.getId());
      return "redirect:/user/";
  }
}
