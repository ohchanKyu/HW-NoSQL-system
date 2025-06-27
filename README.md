# NoSQL-System

## HW 개요
﻿이 애플리케이션은 Spring Boot와 MongoDB를 기반으로 장소 데이터를 관리하며, <br>
사용자가 입력한 주소를 기반으로 데이터들을 거리 순으로 반환하는 기능을 제공합니다. <br>
MongoDB의 지리공간 데이터를 효율적으로 처리할 수 있는 특성을 활용하여, <br>
장소 데이터를 위도와 경도로 저장하고, 거리 기반 정렬 쿼리를 수행합니다. <br>

## 주요 시스템 특징
#### ﻿MongoDB의 2dsphere를 활용한 지리 데이터 관리
MongoDB의 2dsphere 인덱스를 사용해 장소 데이터를 위도/경도 기반으로 저장하고, <br>
거리 기반 정렬 및 검색을 지원합니다. 장소는 GeoJSON의 Point 형식([경도, 위도])으로 저장되며, <br>
$near, $geoWithin 등의 쿼리를 통해 특정 좌표 기준의 근접 장소를 효율적으로 조회할 수 있습니다. <br>

#### Kakao API를 통한 주소 → 좌표 변환
Kakao의 주소-좌표 변환 API를 통해 사용자가 입력한 주소를 위도/경도로 변환합니다. <br> 
이로써 복잡한 좌표 입력 없이도 주소 기반 검색이 가능하며, <br>
외부 API와 연동하여 정확한 지리 데이터를 확보합니다. <br>

#### Spring Boot 기반 거리 기반 검색 API
Spring Boot로 구현된 REST API는 주소 입력 → 좌표 변환 → 거리 정렬까지의 전체 흐름을 처리합니다. <br>
거리 계산은 Spherical Law of Cosines 알고리즘을 사용해 정밀하게 수행되며, 필요 시 커스텀 <br>
로직 확장이 가능합니다. MongoDB와의 연동을 통해 대규모 장소 데이터도 효율적으로 관리합니다. <br>

## **Directory**
```
📦NoSQL-System
 ┣ 📂src
 ┃ ┗ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂kr
 ┃ ┃ ┃ ┃ ┗ 📂ac
 ┃ ┃ ┃ ┃ ┃ ┗ 📂dankook
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂NoSQLProject
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlaceController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂document
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Place.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LocationRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlaceRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CoordinateResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlaceDistResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlaceResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ApiJsonParsingException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BadRequestNearbyPlaceException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BadRequestSaveEntityException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜GlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlaceRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LocationService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlaceService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NoSqlProjectApplication.java
 ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┗ 📜application.properties
 ┣ 📜build.gradle
 ┗ 📜settings.gradle
```

## **Settings**
### Ubuntu 기본 환경 구축
#### 사용자 홈 디렉토리로 이동 및 패키지 목록 업데이트
```Bash
cd ~
sudo apt update
```
#### JDK 21 다운로드 및 설정
```Bash
﻿wget https://download.oracle.com/java/21/archive/jdk-21_linux-x64_bin.tar.gz
﻿tar -xzf jdk-21_linux-x64_bin.tar.gz
﻿sudo mv jdk-21 /usr/local/
﻿nano ~/.bashrc
```
﻿※ 파일의 맨 아래에 다음 내용을 추가
```Bash
﻿export JAVA_HOME=/usr/local/jdk-21
export PATH=$JAVA_HOME/bin:$PATH
```
※ 변경 사항 적용
```Bash
source ~/.bashrc
```
#### 프로젝트 clone 및 해당 프로젝트로 이동
```Bash
git clone https://github.com/ohchanKyu/HW-NoSQL-system.git
cd ./HW-NoSQL-system
```

<br>

### MongoDB 설치 및 설정
#### MongoDB 설치
```Bash
sudo apt install wget curl gnupg2 software-properties-common apt-transport-https ca-certificates
curl -fsSL https://pgp.mongodb.com/server-7.0.asc | \
sudo gpg --dearmor -o /etc/apt/trusted.gpg.d/mongodb-server-7.0.gpg
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu \
$(lsb_release -cs)/mongodb-org/7.0 multiverse" | \
sudo tee /etc/apt/sources.list.d/mongodb-org-7.0.list
sudo apt update
sudo apt install mongodb-org
```
#### MongoDB 실행 & 버전 및 상태 확인
```Bash
sudo systemctl enable --now mongod
sudo systemctl status mongod
mongosh --eval 'db.runCommand({ connectionStatus: 1 })'
mongod --version
```
#### Mongo DB Shell 접근 ( 데이터베이스 및 Collection 생성 )
```Bash
mongosh
> use testdb
> db.places.createIndex({ location: "2dsphere" })
```
<br>

### Spring boot 어플리케이션 실행
```Bash
cd ~/HW-NoSQL-system/NoSQLProject
chmod +x gradlew
./gradlew generateproto
./gradlew build
cd ./build/libs
 java -jar NoSQLProject-0.0.1-SNAPSHOT.jar
```
<br>

### Bash 함수 생성
※ 한글 데이터를 curl 명령어로 전송할 때 URL 규격에 맞는 형식으로 변환하기 위해 urlencode() 함수 생성
```Bash
urlencode() { 
  echo -n "$1"| xxd -plain | sed 's/\(..\)/%\1/g'
}
declare -f urlencode
```

<br>

## API Endpoints

#### **GET** `/places`
- **Description**: 모든 장소데이터를 반환
- **Curl**
```Bash
curl -X GET http://localhost:8080/places
```
- **Success Response**
```JS
 [
  {
    "name": "죽전역",
    "address": "경기도 용인시 수지구 죽전동 1286 죽전역",
    "latitude": 37.325461845165435,
    "longitude": 127.10742517493931
  },
  {
    "name": "기흥역",
    "address": "경기도 용인시 기흥구 구갈동 657 기흥역 롯데캐슬 레이시티/주상복합",
    "latitude": 37.27454920301085,
    "longitude": 127.11505231923752
  },
 ...
]
```

<br>

#### **GET** `/place/nearby`
- **Description**: 주소 데이터를 기반으로 장소 데이터를 거리순으로 정렬하여 반환
- **Curl**
```Bash
 curl -X GET http://localhost:8080/place/nearby \
-H "Content-Type: application/json" \
-d '{"address": "경기도 용인시 기흥구 마북동 19-1 단국대학교" }'
```
- **Success Response**
```JS
[
  {
    "name": "죽전역",
    "address": "경기도 용인시 수지구 죽전동 1286 죽전역",
    "distance": "2.1km"
  },
  {
    "name": "기흥역",
    "address": "경기도 용인시 기흥구 구갈동 657 기흥역 롯데캐슬 레이시티/주상복합",
    "distance": "5.0km"
  },
  {
    "name": "판교역",
    "address": "경기도 성남시 분당구 판교역로 지하160 (백현동)",
    "distance": "7.5km"
  }, ...
]
```
- **Error Response**
```JS
Status: 406 - 부정확한 주소를 입력한 경우
{
  "timestamp": "2025-06-03T03:01:26.086188293",
  "statusCode": 406,
  "error": "NOT_ACCEPTABLE"
  "message": "주소를 좌표로 변환 중 에러가 발생하였습니다. 주소를 정확히 입력해주세요."
}
```
<br>

#### **POST** `/place`
- **Description**: 새로운 장소 데이터를 삽입
- **Curl**
```Bash
curl -X POST http://localhost:8080/place \
-H "Content-Type: application/json" \
-d '{ "name": "서울역", "address": "서울특별시 용산구 동자동 14-151 4호선서울역" }'
```
- **Success Response**
```JS
{
  "name": "서울역",
  "address": "서울특별시 용산구 동자동 14-151 4호선서울역",
  "latitude": 37.5531136106698, "longitude": 126.972801329209
}
```
- **Error Response**
```JS
Status: 406 - 부정확한 주소를 입력한 경우
{
  "timestamp": "2025-06-03T03:01:26.086188293",
  "statusCode": 406,
  "error": "NOT_ACCEPTABLE"
  "message": "주소를 좌표로 변환 중 에러가 발생하였습니다. 주소를 정확히 입력해주세요."
}

Status: 400 - 주소 혹은 장소명을 입력하지 않은 경우
{
  "timestamp": "2025-06-03T03:01:26.086188293",
  "statusCode": 400,
  "error": "BAD_REQUEST",
  "message": "장소의 이름과 주소를 반드시 입력해주세요."
}
```
<br>

#### **POST** `/place/dummy`
- **Description**: 테스트를 진행하기 위해 5개의 dummy 데이터 삽입
- **Curl**
```Bash
curl -X POST http://localhost:8080/place/dummy
```
- **Success Response**
```JS
성공적으로 테스트 데이터 5개를 삽입하였습니다.
```
<br>

#### **DELETE** `/place/{name}`
- **Description**: 해당 장소명으로 데이터 삭제
- **Curl**
```Bash
curl -X DELETE http://localhost:8080/place/{name}
```
- **Success Response**
```JS
데이터를 성공적으로 삭제하였습니다.
```
<br>

#### **DELETE** `/places`
- **Description**: 모든 장소 데이터 삭제
- **Curl**
```Bash
curl -X DELETE http://localhost:8080/places
```
- **Success Response**
```JS
성공적으로 데이터를 모두 삭제하였습니다.
```
<br>
