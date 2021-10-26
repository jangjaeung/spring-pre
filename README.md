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


### 21/10/21 Spring 프레임워크
##### xml파일을 이용하여 Spring simple Java 연습
##### Annotation을 이용하여 Spring MVC연습 

+ Spring 구조
+ ![스프링 구조](https://user-images.githubusercontent.com/90733948/138259982-65c216d6-ae23-44ed-9f42-7d748c0459d6.jpg)


+ Spring MVC 요청 처리 과정
   + 클라이언트에서 /member/detail.do?memberId=user01 입력값이 들어왓다 치자(request) <br>
모든 request는 Disparcher Servlet이 받음
Dispatcher Servlet에서 Handler Mapping을 통해 어느 컨트롤러를 실행시킬지 알고 컨트롤러를 통해 비즈니스 로직 실행하고 디스패쳐서블릿으로 감
view Resolver에서 어떤 뷰로 보낼지 선택하고 보여줌
Dispatcher Servlet / View Resolver은 servlet-context.xml에
  + Handler Mapping  과 Controller를 어노테이션으로 사용
    + Handler Mapping > @RequestMapping(value="/login.do", method=RequestMethod.POST)
    ![화면 캡처 2021-10-21 193015](https://user-images.githubusercontent.com/90733948/138260456-7d647c72-c061-4417-ae98-9ad72510faa7.jpg)
    
### 21/10/22 Spring 프레임워크
##### 어노테이션을 이용하여 Spring MVC 프로젝트 연습

+ xml 파일 설정을 통해 Spring MVC 사용
+ 스프링과 마이바티스 사용
+ pom.xml HomeController.Java web.xml servlet-context.xml, root-context.xml, mybatis-config.xml, member-mapper.xml 설정 
+ 어노테이션을 이용해 인터페이스 의존성 주입
+ **mxl파일 설정 잘보기!!!!**

### 21/10/23 Spring 프레임워크
##### xml 파일 설정 복습
### 21/10/25 Spring 프레임워크 
+ 회원가입 기능 Controller 어노테이션 실습
+ 네이버지도api 네이버 주소검색api 

### 21/10/26 Spring 프레임워크
##### 어노테이션을 이용한 스프링 실습 회원정보 수정 / 탈퇴 / 주소api적용
**controller 회원가입 부분 주석과 코드 잘보기**
1. HttpServletRequest를 통해서 받기
2. @RequestParam 어노테이션 사용해서 받기<br>
  + @RequestParam을 이용하여 변수 선언해서 불러올 필요 없이 어노테이션으로 가능 배열은 request.getParameterValues로 해야함<br>
3. @ModelAttribute 어노테이션 사용해서 받기 *주의점 Member객체에 있는 변수 이름이 jsp파일 name값과 동일해야함 ex)user-id -> memberId<br>
  + 주의사항 VO(Domain)클래스 기본 생성자 존재<br>
  + setter 메소드 존재<br>
  + 요청페이지(jsp)에서 name 속석명이 domain(vo) 필드명(멤버변수명)과 같아야함<br>
4. 요청페이지에서 memberAddress가 없고 post address1,2가 있으니 RequestParam을 통해 불러와서 member객체에memberAddr변수에 setter을 이용하여 합쳐서 저장한다
