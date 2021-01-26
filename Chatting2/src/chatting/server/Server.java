package chatting.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    // 클라이언트와 연결할 때만 필요한 ServerSocket 클래스
    ServerSocket serverScoket;

    // 서버로 접속한 클라이언트 Socket을 저장할 멤버 변수
    Socket scoket;

    // 접속 클라이언트 정보 실시간 저장
    // 서버에 접속한 모든 사용자에게 메세지를 boradCasting 및 send 하기 위함
    public Vector<ServerThread> vector;

    // ServerThread 자료형 멤버 변수 선언, has-a 관계 설정을 위함
    ServerThread serverThread;

    // 서버를 종료하기 위한 String
    String serverExit = null;

    // 서버를 종료하기 위한 문구를 입력받기 위해 사용
    Scanner sc = new Scanner(System.in);

    // 생성자, 멤버 변수 초기화
    public Server() {
        // 사용자 정보를 담을 v를 Vector 객체로 초기화
        vector = new Vector<ServerThread>();
        //q를 입력받으면 종료될수있게 스레드 생성
       
        // 접속이 될 수도 있고 안 될 수도 있기 때문에 예외 처리
        try {
           
            // ServerSocket 객체 생성 → 포트 번호 생성(임의의 번호 부여)
            serverScoket = new ServerSocket(2236);
            
            //서버가 종료될수있게 개별적으로 작동하는 Thread 생성
            ServerExitThread serverExitThread = new ServerExitThread(serverScoket);
            serverExitThread.start();
            
            System.out.println("[ServerSocket port : ]" + serverScoket.getLocalPort());
            System.out.println("[서버 가동 중]");
           

            // 서버 가동: 클라이언트가 접속할 때까지 기다리는 것(무한 대기)
            while (true) {
            
               
                // 소켓이 접속할때까지 대기
                scoket = serverScoket.accept();

                System.out.println("Accepted from" + scoket);

                /*
                 * 접속 클라이언트와 서버로 st객체 생성 
                 * scoket = 접속 클라이언트 
                 * this = server 본인
                 */
                serverThread = new ServerThread(this, scoket);

                // 접속할 때마다 v에 접속 클라이언트 스레드 추가
                addThread(serverThread);

                // Thread 가동 -> run() -> broadCast() -> send() 실시간 메소드 호출
                serverThread.start();
             
            }

        } catch (Exception e) {
            // 접속 실패시 간단한 Error 메세지 출력
            System.out.println("서버 접속 실패>>>" + e);
        } finally {

            /*
             * 서버에 접속을 실패하거나 서버의 while(true)문이 끝나는 경우( try문이 종료된 경우) 메모리를 반환
             */
            if (scoket != null) close(scoket);// 접속해있는 사용자 정보가 있을 경우만 사용자 정보 종료
            if(serverScoket != null)close(serverScoket);// 서버가 있는 경우만 서버 종료
        }
    }

    public void close(Closeable closeable) {
        try {
            System.out.println("[log]" + closeable.toString() + "이 server에서 종료되어 메모리가 반환되었습니다.");
            closeable.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 벡터 vector에 접속 클라이언트의 스레드 저장
    public void addThread(ServerThread serverThread) {
        vector.add(serverThread);
    }

    // 퇴장한 클라이언트 스레드 제거
    public void removeThread(ServerThread serverThread) {
        vector.remove(serverThread);

    }

    // 각 클라이언트에게 메세지를 출력하는 메소드, send() 호출
    public void broadCast(String str) {
        for (int i = 0; i < vector.size(); i++) {
            // 각각의 클라이언트를 ServerThread 객체로 형 변환
            ServerThread serverThread = (ServerThread) vector.elementAt(i);

            // 각 스레드 객체에 str 문자열을 전송
            serverThread.send(str);
        }
    }

}
