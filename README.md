# phcworld-discovery
* 마이크로 서비스 아키텍처에서 서비스 디스커버리와 로드 밸런싱을 담당하는 Eureka Server입니다.  
* Eureka Server와 연결된 Eureka Client는 사용자가 회원가입 후 게시글과 게시글의 댓글을 사용할 수 있는 서비스입니다.  
* 단순 CRUD 게시판이지만 MSA를 이해하기 위한 프로젝트입니다.
* 도커 이미지는 현재 도커 허브에 업로드된 상태이며 도커가 설치되었다면 docker-compose yml파일을 실행할 수 있습니다.
* Eureka Server처럼 먼저 실행된 후 health check로 확인 후 client 컨테이너를 실행하기 때문에 docker-compose 실행까지 약 104초가 걸립니다.
* 카프카 커넥터는 쉘 스크립트로 자동 등록되기 때문에 별도의 등록 API를 사용하지 않아도 됩니다.
```
docker-compose -f ./docker-compose-phc-world.yml up -d
```
* https://documenter.getpostman.com/view/32099163/2sA3XLEjQZ
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
#### [PHC-WORLD Config](https://github.com/javamogi/phc-world-config)
#### [PHC-WORLD Config File Repository(private)](https://github.com/javamogi/phc-world-git-repo)
#### [PHC-WORLD Gateway](https://github.com/javamogi/phc-world-gateway)
#### [PHC-WORLD User-Service](https://github.com/javamogi/phc-world-user-service)
#### [PHC-WORLD Board-Service](https://github.com/javamogi/phc-world-board-service)
#### [PHC-WORLD Answer-Service](https://github.com/javamogi/phc-world-board-answer-service)
