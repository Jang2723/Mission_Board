# 익명 의견 교환 웹 페이지

## 프로젝트 개요
- 사용자들이 자기 자신의 정보를 직접 드러낼 필요 없이 의견을 교환할 수 있는 웹 페이지 구현
- 자신이 누군지 드러낼 필요는 없지만 작성한 사람이 원한다면 수정, 삭제는 가능해야 함

## 스택
- Spring Boot 3.2.1
- Spring Boot Data JPA
- SQLite
- Thymeleaf

### 실행 방법
- 본 Repository를 clone 받는다.
- intellij IDEA로 열어서 [BoardAllication.java](src/main/java/com/example/board/BoardApplication.java)을 실행한다.
- 단, JPA - Hibernate를 사용하여, 최초 실행시 `appllication.yaml`에서 `jpa.hiberante.ddl-auto: create` 의 설정으로 실행되어야 한다.   
- 최초 실행 이후 appllication.yaml에서`jpa.hiberante.ddl-auto: update`로 바꿔주어야 한다.

### 필수 기능

- [데이터 다루기](md/data.md)
- [게시판 기능](md/board.md)
- [게시글 기능](md/article.md)
- [댓글 기능](md/comment.md)

### 회고
처음 진행했던 개인 미션 프로젝트였는데 내가 만들었지만 복잡한 구조 떄문에 시간이 지난 후 다시 읽어볼 때 흐름이 이상한 곳이 많다고 느꼈다.
그래서 필요없는 부분은 과감하게 버렸는데 특히 html 구조 부분을 전체적으로 수정했다. 요구사항에 존재했떤 필수 게시판 4개를 처음에는 더미 데이터에 넣어서 
프로젝트 실행하면 한번 넣어주는 방식으로 진행했는데 리팩토링 이후 service 부분에 정의하는 것으로 수정했다.
댓글 또한 수정 기능을 삭제하고 간단한게 작성과 삭제만 있도록 했다. 개인 미션을 한지 시간이 꽤 지난 후인데도 thymeleaf는 아직도 어려운거 같다...