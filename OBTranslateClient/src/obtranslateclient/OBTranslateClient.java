/*
Author: Bryan Martin and Owen Enders
Class: CSI-235-01
Assignment: Final Project - OBTranslate
Due Date: 04/24/2016
Description:
    The OBTranslateClient allows the users to communicate with the OBTranslate
Server. Using the client the user can translate text to different languages.

Certification of Authenticity:
    I certify that this is entirely my own work, except where I have given fullydocumented
    references to the work of others. I understand the definition and
    consequences of plagiarism and acknowledge that the assessor of this assignment
    may, for the purpose of assessing this assignment:
    - Reproduce this assignment and provide a copy to another member of
    academic staff; and/or
    - Communicate a copy of this assignment to a plagiarism checking service
    (which may then retain a copy of this assignment on its database for
    the purpose of future plagiarism checking)
*/
package obtranslateclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class OBTranslateClient {

    public static void main(String[] args) {
        System.out.println("Translator Client Started");

        InetAddress localAddress = null;
        Socket clientSocket;
        BufferedReader buffReader = null;
        PrintWriter output = null;
        Scanner uInputStream = new Scanner(System.in);
        String userInput = "";

        try{
            localAddress = InetAddress.getLocalHost(); 
        }catch(UnknownHostException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        
        try{
            clientSocket= new Socket(localAddress, 6000);
            buffReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("Connected!");               
        } catch(IOException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        
        System.out.println("Enter language code:");
        userInput = uInputStream.nextLine();
        output.println(userInput);
        System.out.println("Enter text to translate:");
        userInput = uInputStream.nextLine();
        output.println(userInput);
        
        String serverResponse = "";
        try{
            while ((serverResponse = buffReader.readLine()) != null) {
                if (serverResponse.equals("callback")) { // change call to the appropriate word if its not was originally FIN
                    break;
                }
                System.out.println("Translation: " + serverResponse);
            }            
        }   catch(IOException ex){
            ex.getMessage();
        }
    }
    
}
