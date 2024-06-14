# phcworld-discovery
* 이 저장소는 마이크로 서비스 아키텍처에서 서비스 디스커버리와 로드 밸런싱을 담당하는 Eureka Server입니다.  
* Eureka Server와 연결된 Eureka Client는 사용자가 회원가입 후 게시글과 게시글의 답변을 사용할 수 있는 서비스입니다.  
* 단순 CRUD 게시판이지만 MSA를 이해하기 위한 프로젝트입니다.
* 도커 이미지는 현재 도커 허브에 업로드된 상태이며 도커가 설치되었다면 docker-compose yml파일을 실행할 수 있습니다.
* Eureka Server처럼 먼저 실행된 후 health check로 확인 후 client 컨테이너를 실행하기 때문에 docker-compose 실행까지 약 104초가 걸립니다.
* 카프카 커넥터는 쉘 스크립트로 자동 등록되기 때문에 별도의 등록 API를 사용하지 않아도 됩니다.
```
docker-compose -f ./docker-compose-phc-world.yml up -d
```
> API 명세서
> https://documenter.getpostman.com/view/32099163/2sA3XLEjQZ
*** 
#### Eureka 간단 설명
* Spring Cloud Eureka는 서비스들이 자동으로 등록되고 찾을 수 있도록 해주며, 이를 통해 마이크로서비스 간의 통신이 쉽게 이루어집니다. 주요 구성 요소로는 Eureka Server와 Eureka Client가 있습니다.
*** 
### Stack
> * JAVA 17
> * Spring-Boot 3.2.1
> * Spring Cloud Eureka Server
> * Gradle 8.5
*** 
### MSA
![Blank diagram](https://github.com/javamogi/phcworld-discovery/assets/40781237/937cf791-1f37-47d2-8f3c-57e1127636de)
*** 
### 설명
* 회원, 게시글, 답변은 insert, update를 kafka-connect로 DB와 연동하여 사용합니다.
* DB는 편의상 하나로 사용하였습니다.
* 모든 서비스는 JWT 토큰을 사용했으며 Gateway 서버에서 모든 요청을 처리하기 때문에 우선적으로 토큰을 검사합니다.
* MSA 특성상 각 서비스가 독립되어 있기 때문에 각 서비스에도 토큰을 검사합니다.
* 각 서비스의 JWT 관련 코드가 중복되지만 보안상 각 서비스에서도 검사해야한다고 생각했습니다.
* 모든 서비스는 토큰에 담겨있는 회원 고유 아이디를 파싱하여 사용하여 회원을 감별합니다.
* 회원은 보안상 UNIQUE로 UUID 생성 값을 사용하여 식별하였습니다.
* 게시글과 답변은 빠른 조회를 위해 DB AUTO_INCREMENT PK값을 식별자로 사용하였습니다.
* 만약 프론트에서 게시글 및 답변 생성 후 고유값이 필요하다면 AUTO_INCREMENT와 다른 값으로 대체해야합니다.
***
#### [PHC-WORLD Config](https://github.com/javamogi/phc-world-config)
#### [PHC-WORLD Config File Repository(private)](https://github.com/javamogi/phc-world-git-repo)
#### [PHC-WORLD Gateway](https://github.com/javamogi/phc-world-gateway)
#### [PHC-WORLD User-Service](https://github.com/javamogi/phc-world-user-service)
#### [PHC-WORLD Board-Service](https://github.com/javamogi/phc-world-board-service)
#### [PHC-WORLD Answer-Service](https://github.com/javamogi/phc-world-board-answer-service)
