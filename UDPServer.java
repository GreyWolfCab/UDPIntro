import java.net.*;
import java.io.*;

public class UDPServer {

    public static void main(String[] args) {

        try (DatagramSocket aSocket = new DatagramSocket(6789)) {//initialize the socket with a hardcoded port number
            byte[] buffer = new byte[1000];//will store the incoming request

            while (true) {//infinitly listen for requests
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);//store incoming request

                DatagramPacket reply = new DatagramPacket(request.getData(),
                        request.getLength(), request.getAddress(), request.getPort());//build a reply based on the request received

                aSocket.send(reply);//send response to client
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }

    }

}
