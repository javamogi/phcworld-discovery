# phcworld-discovery
마이크로 서비스 아키텍처에서 서비스 디스커버리와 로드 밸런싱을 담당하는 Eureka Server입니다.  
Eureka Server와 연결된 Eureka Client는 사용자가 회원가입 후 게시글과 게시글의 댓글을 사용할 수 있는 서비스입니다.  
단순 CRUD 게시판이지만 MSA를 이해하기 위한 프로젝트입니다.  
*** 
#### Eureka 간단 설명
Spring Cloud Eureka는 서비스들이 자동으로 등록되고 찾을 수 있도록 해주며, 이를 통해 마이크로서비스 간의 통신이 쉽게 이루어집니다. 주요 구성 요소로는 Eureka Server와 Eureka Client가 있습니다.
*** 
### Stack
> * JAVA 17
> * Spring-Boot 3.2.1
> * Spring Cloud Eureka Server
> * Gradle 8.5
*** 
### MSA
![Blank diagram](https://github.com/javamogi/phcworld-discovery/assets/40781237/937cf791-1f37-47d2-8f3c-57e1127636de)
