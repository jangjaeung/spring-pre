+ java 수업정리 https://fierce-form-86b.notion.site/JAVA-c935d1cc431448dfa111f4bfa8619680<br>
+ Oracle SQL 수업정리 https://fierce-form-86b.notion.site/Oracle-SQL-e2517766ffa84561b978c67c675d04c3<br>
# spring-pre
### 21/10/20 Spring 프레임워크 
+ 스프링의 특징
  + **DI(Dependancy Injection 의존성 주입)** - xml파일이나 어노테이션을 이용해 객체간 의존 관계를 설정하여 개발자가 직접 의존하는 객체를 생성할 필요가없음
  + **AOP(관점 지향 프로그래밍)** - 여러 모듈에서 공통으로 필요로 하는 기능의 경우 해당 기능을 분리하여 관리하는 것
  + **SpringMVC** - MVC 디자인 패턴을 통해 웹 어플리케이션의 Model, View, Controller 사이의 의존 관계를 DI 컨테이너에서 관리하여 개발자가 아닌 서버가 객체들을 관리하는 웹 어플리케이션을 구축할 수 있다.
  + **POJO(Plain Old Java Object)** - 자바 객체로 다른 객체와 서로 관계가 설정(상속,구현)되어 잇지 않은 하나의 객체
  + **IOC** - 제어의 역행이라는 뜻으로 객체의 관계를 개발자가 아닌 컨테이너가 설정대로 처리하는것
  
+ Maven의 종속성
  + 이때까지 프로젝트 내의 lib 폴더에 직접 넣어서 관리 해왔다면, maven은 pom.xml 문서 하나 만으로 필요한 라이브러리를 찾아서 자동으로 설치하고
관리할 수 있다.
  + POM(Project Object Model)은 하나의 프로젝트에서 사용하는 자바 버전, 라이브러리, 플러그인을 구성을 통합하여 관리할 수 있게 각 설정 정보를
XML문서화 한것
  + pom.xml에 <dependencies>메이븐 레포지토리에서 복사한것</dependencies> 저장하면 자동 다운됨
