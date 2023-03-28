# 카카오톡 봇

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