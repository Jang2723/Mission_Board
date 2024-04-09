# 게시글 기능

## 비밀번호
- 게시글을 생성할 때 입력한 비밀번호는 `Entity`의 속성을 정의해두고 
```java
@Getter
@Entity
@NoArgsConstructor
public class Article {
    // ...
    
    @Setter
    private String password;
    
    // ...
}
```

- 수정과 삭제를 위한 `Entity`를 받았을 때 거기 기록된 값과 일치하는지만 비교

## 게시글 작성

- 게시글 작성은 `/article/new`에서 진행
- 게시글을 작성하면서 게시판을 선택할 수 있어야 하기 때문에, `BoardService.readAll()`을 사용
- HTML에서는 `select` 요소를 사용하며, 내부에 `option`을 `th:each`를 활용하여 반복

```java
@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final BoardService boardService;
    private final ArticleService articleService;

    @GetMapping("article/new")
    public String newArticle(
            Model model
    ) {
        model.addAttribute("boards", boardService.readAll());
        return "article/new";
    }
    // ...
}
```

- 작성하고 난 뒤에는 바로 상세보기 페이지로 이동

```java
@Controller
@RequiredArgsConstructor
public class ArticleController {
    // ...
    @PostMapping("article")
    public String createArticle(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("board-id")
            Long boardId
    ) {
        Long newId = articleService.create(boardId, new ArticleDto(title, content, password)).getId();
        return String.format("redirect:/article/%d", newId);
    }
}
```

## 게시글 수정 & 삭제

- 수정과 삭제를 위해서 받은 `id`를 기준으로 `Article`을 조회하고
- 해당 `Article`에 담긴 `password`와 수정 및 삭제 시 전달받은 `password`를 비교

```java
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;
    // ...
    public ArticleDto update(Long id, ArticleDto articleDto) {
        Article article = articleRepository.findById(id)
                .orElseThrow();
        if (article.getPassword().equals(articleDto.getPassword())) {
            article.setTitle(articleDto.getTitle());
            article.setContent(articleDto.getContent());
        }
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public void delete(Long id, String password) {
        Article article = articleRepository.findById(id)
                .orElseThrow();
        if (article.getPassword().equals(password)) {
            articleRepository.delete(article);
        }
    }
}
```