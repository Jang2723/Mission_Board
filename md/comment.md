# 댓글 기능

- 작성 & 삭제만 필요
- 따로 댓글을 확인하는 UI 없음(게시글 내부에서 확인).

## `@Controller`
```java
@Slf4j
@Controller
@RequestMapping("article/{articleId}/comment")
@RequiredArgsConstructor
public class CommentController {
    // ...
}
```

## 댓글 작성 & 삭제

- 댓글을 작성하든 삭제하든, 이후 이동하는 곳은 댓글이 있는 게시글 페이지 이므로 이를 위해 `articleId`를 활용

```java
@Slf4j
@Controller
@RequestMapping("article/{articleId}/comment")
@RequiredArgsConstructor
public class CommentController {
    // ...
    @PostMapping
    public String createComment(
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ) {
        commentService.createComment(articleId, new CommentDto(content, password));
        return String.format("redirect:/article/%d", articleId);
    }

    @PostMapping("{commentId}/delete")
    public String deleteComment(
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("commentId")
            Long commentId,
            @RequestParam("password")
            String password
    ) {
        commentService.deleteComment(commentId, password);
        return String.format("redirect:/article/%d", articleId);
    }
}
```