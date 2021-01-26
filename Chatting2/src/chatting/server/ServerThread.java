package chatting.server;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class ServerThread extends Thread{

    
 // 클라이언트 소켓 저장
    Socket scoket;

    // Server 클래스의 객체를 멤버 변수로 선언, has-a 관계를 위함
    /*
     * thread를 상속받아서 servcer을 상속받지 못하기 때문에
     * 멤버변수로 선언하고 생성자에서 server를 파라미터로 받아서 사용
     */
    Server server;

    // 입출력
    BufferedReader bufferReader;
    PrintWriter prinWriter;

    // 전달할 문자열
    String str;

    // 대화명(ID)
    String name;
    
    //
  

    // 생성자
    public ServerThread(Server se, Socket s) {

        
        /*has-a 형식을 위해 서버 객체 생성 
       has-a는 포함해서 사용 ( 상속같은거)
       ex) 경찰과 총, 경찰은 총을 갖고 있다의 구조
        서버스레드는 서버를 갖고있다 구조를 사용
        서버에서 정의해논 함수들을 사용함
        server에서 구현한 broadCasting 함수나 addThread 및 remove
        */
        server = se;

        // 접속한 클라이언트 정보 저장
        scoket = s;


      
        // 데이터 전송을 위한 입출력 스트림 생성
        try {
            // =========== 입력 ===========
            // scoket.getInputStream() => 접속 클라이언트(소켓 객체)의 InputStream을 얻어 옴
            bufferReader = new BufferedReader(new InputStreamReader(scoket.getInputStream(),"UTF-8"));

            // =========== 출력 ===========
            /*
            BufferedWriter의 경우 버퍼링 기능을 가지기 때문에 PrintWriter 스트림 사용
            PrintWriter 스트림의 경우 생성자의 두 번째 인자로 autoFlush 기능을 지정할 수 있음
            BufferedWriter를 사용하는 경우 flush() 메소드를 사용해야 함
            */
            prinWriter = new PrintWriter(new OutputStreamWriter(scoket.getOutputStream(), "UTF-8"),true);
        } catch (Exception e) {
            System.out.println("에러 발생>>>>>" + e);
        }
    }

    
    


    // 메세지(입력 문자열) 출력 메소드
    public void send(String str) {
        // 문자열 출력
        prinWriter.println(str);

        // 혹시나 버퍼에 남아있는 것을 비워냄
        prinWriter.flush();
    }

    // run()_ServerThread -> broadCast(str)_ChatGUIServer -> send(str)_ServerThread
    public void run() {
        try {
            // 대화명 입력 받기
            prinWriter.println("대화명을 입력하세요");
            name = bufferReader.readLine();

            // 서버에서 각 클라이언트에 대화명 출력
            server.broadCast("[" + name + "]" + "님이 입장했습니다.");

            // 무한 대기하며 입력한 메세지를 각 클라이언트에 계속 전달
            while ((str = bufferReader.readLine()) != null) {
                server.broadCast("[" + name + "]: " + str);
            }
        } catch (Exception e) {
            // 예외 발생시(ui창을 닫을시) -> 쓰레드를 종료시키기 
            // 접속자 퇴장,  vector에서 해당 클라이언트 스레드 제거
            server.removeThread(this); // this: ServerThread 객체 => 접속 클라이언트
             // 서버에서 각 클라이언트에 출력
            server.broadCast("[" + name + "]" + "님이 퇴장했습니다.");

            // 콘솔에 퇴장 클라이언트가 설정한 name이 표시됨
            System.out.println(name + "의 연결이 종료됨!");
         
        }
        finally {
            //메모리 반환
            close(bufferReader); //클라이언트 inputBuffer
            close(prinWriter); // 클라이언트 outputBuffer
            close(scoket); // 클라이언트 scoket
        }
    }
    //closeable 을 사용해 모든 버퍼들을 매개변수로 받아서 종료 변수의object와 비슷한 느낌
    public void close(Closeable closeable){
        
        try {
            System.out.println("[log]"+closeable.toString()+" 이 serverThread 에서 종료되어 메모리가 반환되었습니다.");
            closeable.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
