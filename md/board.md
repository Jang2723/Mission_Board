# 게시판 기능

## 기본 게시판 생성하기
- 자유 게시판
- 개발 게시판
- 일상 게시판
- 사건사고 게시판


- 원래는 data.sql에 게시판의 정보를 넣어주는 방식으로 작성했지만 리팩토링을 할 때는 `BoardService`가 실행될 때 존재하는 게시판을 확인 후 없는 게시판을 생성하는 방식을 선택
- 네개의 게시판을 `static final` 속성으로 클래스에 정의

```java
@Slf4j
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private static final String[] basicBoardNames = {
            "자유 게시판",
            "개발 게시판",
            "일상 게시판",
            "사건사고 게시판"
    };

    // ...
}
```

## 게시판별 보기

- 각 게시판은 `@PathVariable`을 이용해 `boardId`를 받도록 설정
- 전에는 html에서 `<tr th:each="index: ${#numbers.sequence(articles.size() - 1, 0, -1)}">`를 이용해 역순으로 출력했지만
- 리팩토링 후에는 `Collections.reverse`를 이용해 게시글의 순서를 `id`의 역순으로 전달하여 출력
    - `id`가 클 수록 나중에 작성된 글

```java
@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    // ...
    @GetMapping("{boardId}")
    public String listOneBoard(
            @PathVariable("boardId")
            Long boardId,
            Model model
    ) {
        BoardDto boardDto = boardService.read(boardId);
        model.addAttribute("boards", boardService.readAll());
        model.addAttribute("selected", boardDto);
        List<ArticleDto> articles = boardDto.getArticles();
        Collections.reverse(articles);
        model.addAttribute("articles", articles);
        return "board";
    }
}
```

- 게시글 전체를 볼때는 `@PathVariable`이 없으며, `model.addAttribute("selected", boardDto)`가 생략
- 전체 게시글 조회를 위해 `ArticleService`의 기능을 활용(`readAll`)

```java
@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;

    @GetMapping
    public String listAllBoards(
            Model model
    ) {
        model.addAttribute("boards", boardService.readAll());
        model.addAttribute("selected", null);
        List<ArticleDto> articles = articleService.readAll();
        Collections.reverse(articles);
        model.addAttribute("articles", articles);
        return "board";
    }
    // ...
}
```

