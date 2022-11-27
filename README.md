# JPA-basic1
스프링부트 기초에 대해 스터디
- JPA
- Junit
- RestTemplate

TDD란? : 테스트 주도 개발
- Junit5는 Jupiter, platform, vintage로 구성됨
- Jupiter는 구현체
platform 은 인터페이스
vintage는 juit 3,4 실행


@SpringBootTest
- 통합 테스트용도로 사용됨
- @SpringBootApplication을 찾아가 하위의 모든 Bean을 스캔
- Test용 Application Context를 만들어서 Bean을 추가하고 MockBean을 찾아서 교체

@ExtendWith
- Junit4에서 @RunWith로 사용되던게 => @ExtendWith로 변경됨
- @ExtendWith는 메인으로 실행 될 class를 지정할 수 있음
- @SpringBootTest는 기본적으로 @ExtendWith가 추가되어있음

@WebMvcTest(class명)
- ()에 작성된 클래스만 실제로 로드해서 테스트 진행
- 매개변수를 지정해주지 않으면 @Controller, @RestController, @RestControllerAdvice 등 컨트롤러와 연관된 Bean이 모두 로드 됨

@Mockbean
- Controller의 API를 테스트하는 용도인 MockMvc 객체를 주입받음
- perform() 메소드를 활용하여 컨트롤러의 동작을 확인 할 수 있음
- andExpect(), andDo(), andReturn()등의 메소드를 같이 활용
