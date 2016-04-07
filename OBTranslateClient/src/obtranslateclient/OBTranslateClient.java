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
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class OBTranslateClient {

    public static void main(String[] args) {
        // Client Main Code Goes Here
        System.out.println("Translator Client Started");
        
        try{
            System.out.println("Waiting for Translator Server");
            
            InetAddress localAddress = InetAddress.getLocalHost();
            
            try{
                Socket clientSocket = new Socket(localAddress, 6000); // change key leter
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new printWriter(clientSocket.getOutputStream(), true);
                System.out.println("Connected!");
                Systme.out.println()
            }
        }
    }
    
}
