package chatting.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener, Runnable {

    // ======== GUI =========
    JTextField tf; // 전송할 텍스트 입력창
    JTextArea ta; // 전송받은 텍스트 출력

    JScrollPane js; // 스크롤바 생성

    // ======== Socket =======
    Socket scoket; // 서버와의 통신을 위함

    // ======== Stream =======
    BufferedReader bufferReader; // 클라이언트에서의 문자열 입력 스트림
    PrintWriter printWriter; // 문자열 출력 스트림

    // 서버로 전송할 문자열과 서버에서 받아올 문자열 변수
    String str, str1;

    // ======== 생성자 ========
    public Client() {
        // 창, 부착할 컴포넌트 생성 및 연결
        tf = new JTextField();
        ta = new JTextArea();

        // 텍스트 출력창에 스크롤 바 연결
        js = new JScrollPane(ta);

        // BorderLayout 배치관리자, JTextArea를 정중앙에 부착
        add(js, "Center");

        // 텍스트 필드를 하단에 부착
        add(tf, BorderLayout.SOUTH);

        // 텍스트 필드에서 이벤트(enter)를 입력받고 해당 객체에서 이벤트 처리
        tf.addActionListener(this);

        // 창 크기 지정
        setBounds(200, 200, 500, 350);

        // 창이 보이도록 설정
        setVisible(true);

        // 텍스트 필드에 커서 입력
        tf.requestFocus();

        // X버튼 클릭시 정상 종료되도록 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 서버와 연결, 연결되지 않을 수도 있기 때문에 예외 처리 필수
        try {
            // 클라이언트 측 소켓 정보 초기화
            // Socket(host, port), host: 접속 서버 IP 주소, port: 서버 포트 번호
            scoket = new Socket("127.0.0.1", 2236);
            System.out.println("scoket>>>" + scoket);

            // ========== Server와 Stream 연결 ===========
            bufferReader = new BufferedReader(new InputStreamReader(scoket.getInputStream(),"UTF-8"));

            // PrintWriter 스트림의 autoFlush 기능 활성화
            printWriter = new PrintWriter(new OutputStreamWriter(scoket.getOutputStream(),"UTF-8"), true);
            
            
            
            
        } catch (Exception e) {
            System.out.println("접속 오류>>>" + e);
        }
        finally {
          //  close(bufferReader);
           // close(printWriter);
          // close(scoket);
        }
       
     

        // Thread 객체 생성, Runnable 인터페이스를 구현하기 때문에 this 작성
        // ui를 보여주기 위해 Jframe을 상속받아서 Thread을 Ruunable 임플리먼트를 사용함
        Thread ct = new Thread(this);

        // 클라이언트 스레드 실행 start()함수를 실행하면→ run() 호출
        ct.start();
    }
   


    // Runnable 인터페이스 run() 메소드 오버라이딩
    public void run() {
        // 더 이상 입력을 받을 수 없을 때까지 JTextArea(채팅창)에 출력
        try {
            while ((str1 = bufferReader.readLine()) != null) {
                ta.append(str1 + "\n"); // 상대방이 보낸 문자를 채팅창에 세로로 출력
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
 
   
    }
    
    
    public void close(Closeable closeable){
        try {
            System.out.println("[log]"+closeable.toString()+" 이 Client에서 종료되어 메모리가 반환되었습니다.");
            closeable.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    // ActionListener 메소드 오버라이딩, 입력란에서 enter입력시 실행할 코드
    public void actionPerformed(ActionEvent e) {
        // 내가 쓴 메세지를 str 변수에 저장
        str = tf.getText();

        // 변수에 저장 후 텍스트필드 초기화
        tf.setText("");

        // 내가 쓴 메세지 출력 -> 상대방은 br.readLine()으로 읽어들임
        printWriter.println(str);
        printWriter.flush();
    }



}
