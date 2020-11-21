import java.net.*;
import java.io.*;
import java.util.Scanner;

import org.apache.commons.validator.routines.InetAddressValidator;

public class UDPClient {

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {

            String input = "";

            while (!input.equals(":q")) {

                System.out.print("Enter network address: ");
                String networkAddress = in.nextLine();//192.168.0.22
                while (!InetAddressValidator.getInstance().isValid(networkAddress)) {
                    System.out.print("Invalid network address, try again: ");
                    networkAddress = in.nextLine();
                }

                int port;
                try {
                    System.out.print("Enter port number: ");
                    port = Integer.parseInt(in.nextLine());
                    while (!(port >= 0 && port <= 65535)) {
                        System.out.print("Invalid port number, try again: ");
                        port = Integer.parseInt(in.nextLine());
                    }
                } catch (NumberFormatException e) {
                    //e.printStackTrace();
                    port = 0;
                }

                System.out.print("Enter your message: ");
                String message = in.nextLine();

                sendRequest(message, networkAddress, port);

                System.out.print("Enter \":q\" to quit any other key to continue: ");
                input = in.nextLine();
                System.out.println();
            }

        }

    }

    public static void sendRequest(String message, String networkAddress, int port) {
        try (DatagramSocket aSocket = new DatagramSocket()) {
            aSocket.setSoTimeout(5000);
            byte[] m = message.getBytes();
            InetAddress aHost = InetAddress.getByName(networkAddress);
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, port);
            aSocket.send(request);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));
        } catch(SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch(SocketTimeoutException e) {
            System.out.println("Socket Timeout: " + e.getMessage());
        }  catch(IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}
