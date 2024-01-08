package com.example.board;

import com.example.board.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    // 하나의 게시판의 게시글 목록을 전부 보기
    @GetMapping
    public String article(Model model){
        model.addAttribute("articles",articleService.readAll());
        return "index";
    }

    // 게시글 만들기
    @PostMapping
    public String articleCreate(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ){
        Long newId = articleService.create(new ArticleDto(title, content, password)).getId();
        return String.format("redirect:/article/%d", newId);
    }

    @GetMapping("write")
    public String articleWrite() {
        return "write";
    }

    @GetMapping("{id}")
    public String articleReadOne(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("article", articleService.readOne(id));
        return "read";
    }

    @GetMapping("{id}/edit")
    public String articleEdit(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("article",articleService.readOne(id));
        return "edit";
    }

    @PostMapping("{id}/update")
    public String articleUpdate(
            @PathVariable("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content
    ){
        articleService.update(id, new ArticleDto(title, content));
        return String.format("redirect:/article/%d", id);
    }

    @PostMapping("{id}/delete")
    public String articleDelete(
            @PathVariable("id")
            Long id
    ){
        articleService.delete(id);
        return "redirect:/article";
    }

}
