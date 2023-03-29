# 카카오톡 봇

![샘플 이미지](https://user-images.githubusercontent.com/31335146/228534637-baf0a3bb-6042-4b48-923c-fa21e95b28d8.gif)

## 원리
안드로이드 기기의 notification을 이용해 수신된 카카오톡 메시지를 서버로 전달받아 처리 후 응답합니다.

0. 안드로이드 기기에서 메신저봇R을 이용해 안드로이드 notifiction을 읽습니다.
0. 카카오톡 메시지 notification 정보를 기기 밖 UDP 서버에 전달합니다. (msgbotR 디렉토리)
0. UDP 서버에서 메시지를 처리 후 적절한 응답을 기기에 전달합니다.
0. 메신저봇R이 받아온 응답을 카카오톡 notification에 답장합니다.

## 실행 방법
0. 안드로이드 기기에 카카오톡을 설치 후 로그인합니다.
0. 안드로이드 기기에 메신저봇R을 설치합니다.
0. 메신저봇R에 msgbotR/source.js 코드를 넣습니다.
0. PC에서 `ipconfig` 또는 `ifconfig`을 통해 IP 확인 후 source.js 코드 상단의 IP를 수정합니다.
0. server/src/main/resources/application.properties.sample 파일의 이름을 application.properties로 수정 후 내용을 적절히 변경합니다.
0. server 디렉토리에서 `mvn compile` 명령어를 입력해 서버를 컴파일합니다.
0. server 디렉토리에서 `mvn exec:java` 명령어를 입력해 서버를 실행합니다.
0. 카카오톡으로 `테스트`라는 메시지를 수신받아 테스트합니다.

## 커스텀 서비스 추가
`com.youhogeon.kakaobot.service.Service` 인터페이스를 구현하면 서비스로 등록됩니다.

## 기본 제공 서비스
* TestService : 테스트용 서비스
* HelpService : 도움말 서비스
* SearchService : 검색엔진 연결 서비스
* OpenAIService : OpenAI API를 이용한 ChatGPT 채팅 지원 서비스