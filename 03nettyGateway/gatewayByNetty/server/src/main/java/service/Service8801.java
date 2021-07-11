package service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Service8801 {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) throws IOException{
        PrintWriter printWriter=null;
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            System.out.println("请求：");
            while( (line = bufferedReader.readLine())!=null && line.length() > 0 ){
                System.out.println(line);
            }

            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,service1";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.flush();
            System.out.println("发送:");
            System.out.println(body);


        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            printWriter.close();
            socket.close();
        }
    }
}
