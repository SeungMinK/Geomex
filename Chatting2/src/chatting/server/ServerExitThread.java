package chatting.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class ServerExitThread extends Thread {

    // 서버를 종료하기 위한 String
    String exit = null;

    // 서버를 종료하기 위한 문구를 입력받기 위해 사용
    Scanner scanner;
    ServerSocket serverScoket;

    public ServerExitThread(ServerSocket serverScoket) {
        // TODO Auto-generated constructor stub
        this.serverScoket = serverScoket;
        scanner = new Scanner(System.in);

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();

         System.out.println("[서버를 종료하려면 q를 입력하세요.]");
        exit = scanner.next();

        if (exit.equals("q")) {
            close(serverScoket);
        }
    }
    
    //closeable 을 사용해 모든 버퍼들을 매개변수로 받아서 종료 변수의object와 비슷한 느낌
    public void close(Closeable closeable) {

        try {
            System.out.println("[log]" + closeable.toString() + " 이 serverExitThread 에서 종료되어 메모리가 반환되었습니다.");
            closeable.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
