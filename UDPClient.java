import java.net.*;
import java.io.*;
import java.util.Scanner;

import org.apache.commons.validator.routines.InetAddressValidator;

public class UDPClient {

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {

            String input = "";

            while (!input.equals(":q")) {//repeat the process until the user decides to end

                System.out.print("Enter network address: ");
                String networkAddress = in.nextLine();
                while (!InetAddressValidator.getInstance().isValid(networkAddress)) {//repeat until a valid ip address is entered
                    System.out.print("Invalid network address, try again: ");
                    networkAddress = in.nextLine();
                }

                int port;
                try {//make sure a string isn't entered for the port number
                    System.out.print("Enter port number: ");
                    port = Integer.parseInt(in.nextLine());
                    while (!(port >= 0 && port <= 65535)) {//check port number is valid
                        System.out.print("Invalid port number, try again: ");
                        port = Integer.parseInt(in.nextLine());
                    }
                } catch (NumberFormatException e) {
                    //e.printStackTrace();
                    port = 0;
                }

                System.out.print("Enter your message: ");
                String message = in.nextLine();//collect the users message

                sendRequest(message, networkAddress, port);

                System.out.print("Enter \":q\" to quit any other key to continue: ");//any other key will continue the process
                input = in.nextLine();
                System.out.println();
            }

        }

    }

    public static void sendRequest(String message, String networkAddress, int port) {
        try (DatagramSocket aSocket = new DatagramSocket()) {//initialize the datagram
            aSocket.setSoTimeout(5000);//for invalid requests timeout
            byte[] m = message.getBytes();//collect the users message
            InetAddress aHost = InetAddress.getByName(networkAddress);//collect the ip address
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, port);//initialize the request
            aSocket.send(request);//send the request
            byte[] buffer = new byte[1000];//will store the response from the server
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);//receive the response
            System.out.println("Reply: " + new String(reply.getData()));//print the message tied to the response
        } catch(SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch(SocketTimeoutException e) {
            System.out.println("Socket Timeout: " + e.getMessage());
        }  catch(IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}
