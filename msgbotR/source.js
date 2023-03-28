const bot = BotManager.getCurrentBot();

const server = {
    address: "192.168.1.33",
    port: 21318,
}

/**
 * (string) msg.content: 메시지의 내용
 * (string) msg.room: 메시지를 받은 방 이름
 * (User) msg.author: 메시지 전송자
 * (string) msg.author.name: 메시지 전송자 이름
 * (Image) msg.author.avatar: 메시지 전송자 프로필 사진
 * (string) msg.author.avatar.getBase64()
 * (boolean) msg.isGroupChat: 단체/오픈채팅 여부
 * (boolean) msg.isDebugRoom: 디버그룸에서 받은 메시지일 시 true
 * (string) msg.packageName: 메시지를 받은 메신저의 패키지명
 * (void) msg.reply(string): 답장하기
 */
function onMessage(msg) {
    if (msg.packageName != "com.kakao.talk") return;

    const dto = {
        content: msg.content,
        room: msg.room,
        auther: {
            name: msg.author.name,
            avatar: msg.author.avatar.getBase64(),
        },
        isGroupChat: msg.isGroupChat,
        isDebugRoom: msg.isDebugRoom,
    }

    const dtoString = JSON.stringify(dto);

    const thread = new java.lang.Thread({
        run() {
            const socket = new java.net.DatagramSocket();
            const address = java.net.InetAddress.getByName(server.address);

            const receiveBuffer = java.lang.reflect.Array.newInstance(java.lang.Byte.TYPE, 32767);
            const sendBuffer = new java.lang.String(dtoString).getBytes();

            const packet = new java.net.DatagramPacket(sendBuffer, sendBuffer.length, address, server.port);
            socket.send(packet);

            const received = new java.net.DatagramPacket(receiveBuffer, receiveBuffer.length);
            while (true) {
                socket.setSoTimeout(300000);
                socket.receive(received);

                const response = String(
                    new java.lang.String(
                        received.getData(),
                        received.getOffset(),
                        received.getLength()
                    )
                );

                msg.reply(response);

                break;
            }

            socket.close();
        },
    });

    thread.start();
}
bot.addListener(Event.MESSAGE, onMessage);


function onCreate(savedInstanceState, activity) {
  var textView = new android.widget.TextView(activity);
  textView.setText("Hello, World!");
  textView.setTextColor(android.graphics.Color.DKGRAY);
  activity.setContentView(textView);
}

function onStart(activity) {}

function onResume(activity) {}

function onPause(activity) {}

function onStop(activity) {}

function onRestart(activity) {}

function onDestroy(activity) {}

function onBackPressed(activity) {}

bot.addListener(Event.Activity.CREATE, onCreate);
bot.addListener(Event.Activity.START, onStart);
bot.addListener(Event.Activity.RESUME, onResume);
bot.addListener(Event.Activity.PAUSE, onPause);
bot.addListener(Event.Activity.STOP, onStop);
bot.addListener(Event.Activity.RESTART, onRestart);
bot.addListener(Event.Activity.DESTROY, onDestroy);
bot.addListener(Event.Activity.BACK_PRESSED, onBackPressed);