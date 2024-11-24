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

import com.example.demo.dto.CommentRequest;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;

@RequestMapping("comment")
@Controller
public class CommentController {
	
	private final CommentRepository repository;

    @Autowired 
    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }
    @Autowired 
    private CommentService commentService;
    /**
     * すべてのcomment取得
     * @param comment
     * @param model
     * @return
     */
    @GetMapping("/")
    public String getAllComments(Comment comment, Model model) {
        model.addAttribute("comments", repository.findAll());
        return "list";
    }
	
    @PostMapping("/add")
    public String addComment(@Validated Comment comment,
            BindingResult result, Model model) {

        model.addAttribute("comments", repository.findAll());
        if (result.hasErrors()) {
            return "list";
        }
        
        repository.save(comment);
     // ルートパス("/")に、リダイレクトします
        return "redirect:/comment/";
    }
    
    
     /**
      * comment編集画面
      * @PathVariable("id") Long id
      * ⇒URLの{id}の部分の値を取得することが出来る
      */
    @GetMapping("/{id}/edit")
    public String displayEdit(@PathVariable("id") Long id,Model model) {
    	Comment comment = commentService.findById(id);
    	//model又はdtoへ移す
    	CommentRequest commentRequest = new CommentRequest();
    	commentRequest.setId(comment.getId());
    	commentRequest.setComment(comment.getContent());

        model.addAttribute("commentRequest", commentRequest);
    	return "/Comment/edit";
    }
    
    /**
     * コメント更新処理
     * @param id
     * @param model
     * @return
     */
    @PostMapping("/update")
    public String update(@Validated @ModelAttribute CommentRequest commentRequest,BindingResult result,Model model) {
        
    	if(result.hasErrors()) {
	      List<String> errorList = new ArrayList<String>();
	      for (ObjectError error : result.getAllErrors()) {
	          errorList.add(error.getDefaultMessage());
	        }
	      model.addAttribute("validationError", errorList);
	      return "/Comment/edit";
    	}
        // ユーザー情報の更新
    	commentService.update(commentRequest);
        return "redirect:/comment/";
    }
    
    
    /**
     * comment削除画面
     * @PathVariable("id") Long id
     * ⇒URLの{id}の部分の値を取得することが出来る
     */
   @GetMapping("/{id}/delete")
   public String displayDelete(@PathVariable("id") Long id,Model model) {
   	Comment comment = commentService.findById(id);
   	//model又はdtoへ移す
   	CommentRequest commentRequest = new CommentRequest();
   	commentRequest.setId(comment.getId());
   	commentRequest.setComment(comment.getContent());

       model.addAttribute("commentRequest", commentRequest);
   	return "/Comment/delete";
   }
    
   /**
    * コメント削除処理
    * @param id
    * @param model
    * @return
    */
   @PostMapping("/delete")
   public String delete(@ModelAttribute CommentRequest commentRequest) {
       
       // ユーザー情報の削除
   		commentService.delete(commentRequest.getId());
       return "redirect:/comment/";
   }
    
    
}
