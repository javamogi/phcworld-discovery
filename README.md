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
![msa](https://github.com/javamogi/phcworld-discovery/assets/40781237/ba8b1fde-093d-46d7-874c-f92c2cc7fa60)
*** 
### 설명
* 회원, 게시글, 답변은 insert, update를 kafka-connect로 DB와 연동하여 사용합니다.
* DB는 편의상 하나로 사용하였습니다.
* 모든 서비스는 JWT 토큰을 사용했으며 Gateway 서버에서 모든 요청을 처리하기 때문에 우선적으로 토큰을 검사합니다.
* MSA 특성상 각 서비스가 독립되어 있기 때문에 각 서비스에도 토큰을 검사합니다.
* 각 서비스의 JWT 관련 코드가 중복되지만 보안상 각 서비스에서도 검사해야한다고 생각했습니다.
* 모든 서비스는 토큰에 담겨있는 회원 고유 아이디를 파싱하여 사용하여 회원을 감별합니다.
* 회원은 보안상 UNIQUE 제약 조건으로 UUID 생성 값을 사용하여 식별하였습니다.
* 게시글과 답변은 빠른 조회를 위해 DB AUTO_INCREMENT PK값을 식별자로 사용하였습니다.
* 하나의 DB를 사용하지만 각 서비스별 DB가 있다는 가정으로 각 서비스와 통신합니다.
  * ~~board-service에서는 조회 때 사용할 회원 정보를 따로 DB에 저장하여 사용합니다.~~
  * ~~회원 등록 및 수정에 사용하는 kafka-topic으로 consumer로 구현하여 user-service에서 등록 및 수정이 이루어진다면 board-service에도 식별자 user_id로 등록 및 수정이 진행됩니다.~~
  * ~~회원 아이디만 저장하여 회원 정보가 필요한 요청에서 user-service에서 회원 정보를 호출하여 사용하였지만 성능 저하와 service의 독립성을 확보하기 위해서 Sub Entity로 저장하였습니다.~~
  * 만약 모든 서비스에서 회원 정보가 필요하다면 모든 서비스에 User Sub Entity가 필요하므로 이에 대해서는 고민하여 통신을 통하여 정보를 받는 것으로 변경하였습니다.
  * 회원의 정보는 JWT가 보장하기 때문에 따로 회원 정보에 대해서 확인을 안해도 될거라 생각하지만 이 부분도 고민을 하고 있습니다.
* 만약 프론트에서 게시글 및 답변 생성 후 고유값이 필요하다면 AUTO_INCREMENT와 다른 값으로 대체해야합니다.
* 회원 서비스에만 CQRS 패턴을 적용하였고 데이터베이스를 replica로 나누어 Master는 Command(insert, update, delete)를 담당하고 ~~Slave는 Query(select)를 담당합니다~~.
* MySQL replica는 데이터 백업용으로 나두고 Redis를 사용하여 읽기 측면의 성능을 향상시켰습니다.
***
#### [PHC-WORLD Config](https://github.com/javamogi/phc-world-config)
#### [PHC-WORLD Config File Repository(private)](https://github.com/javamogi/phc-world-git-repo)
#### [PHC-WORLD Gateway](https://github.com/javamogi/phc-world-gateway)
#### [PHC-WORLD User-Service](https://github.com/javamogi/phc-world-user-service)
#### [PHC-WORLD Board-Service](https://github.com/javamogi/phc-world-board-service)
#### [PHC-WORLD Answer-Service](https://github.com/javamogi/phc-world-board-answer-service)
