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
        
        while(true){
            System.out.println("|Albanian      sq|*|Georgian      el|*|Malay         ms|*|Tagalog       tl|");
            System.out.println("|English       en|*|Danish        da|*|Maltese       mt|*|Tatar         tt|");
            System.out.println("|Arabic        ar|*|Yiddish       he|*|Macedonian    mk|*|Turkish       tr|");
            System.out.println("|Armenian      hy|*|Indonesian    id|*|Mongolian     mn|*|Uzbek         uz|");
            System.out.println("|Azerbaijan    az|*|Irish         ga|*|German        de|*|Ukrainian     uk|");
            System.out.println("|Afrikaans     af|*|Italian       it|*|Norwegian     no|*|Finish        fi|");
            System.out.println("|Basque        eu|*|Icelandic     is|*|Persian       fa|*|French        fr|");
            System.out.println("|Belarusian    be|*|Spanish       es|*|Polish        pl|*|Croatian      hr|");
            System.out.println("|Bulgarian     bg|*|Kazakh        kk|*|Portuguese    pt|*|Czech         cs|");
            System.out.println("|Bosnian       bs|*|Catalan       ca|*|Romanian      ro|*|Swedish       sv|");
            System.out.println("|Welsh         cy|*|Kyrgyz        ky|*|Russian       ru|*|Estonain      et|");
            System.out.println("|Veitnamese    vi|*|Chinese       zh|*|Serbian       sr|*|Japanese      jp|");
            System.out.println("|Hungarian     hu|*|Korean        ko|*|Slovakian     sk|*|Greek         el|");
            System.out.println("|Hatian&Creole ht|*|Latin         la|*|Slovenian     sl|*|Malagasy      mg|");
            System.out.println("|Galacian      gl|*|Latvian       lv|*|Swahili       sw|*|Thai          th|");
            System.out.println("|Dutch         nl|*|Lithuanian    lt|*|Tajik         tg|*|                |");
            
           
            System.out.println("Enter language code (type 'exit' to quit):");
            userInput = uInputStream.nextLine();
            output.println(userInput);
            if("exit".equalsIgnoreCase(userInput)){
                break;
            }
            System.out.println("Enter text to translate:");
            userInput = uInputStream.nextLine();
            output.println(userInput);

            String serverResponse = "";
            try{
                while ((serverResponse = buffReader.readLine()) != null) {
                    if (serverResponse.equals("End")) { // change call to the appropriate word if its not was originally FIN
                        break;
                    }
                    System.out.println("Translation: " + serverResponse);
                }
            }   catch(IOException ex){
                ex.getMessage();
            }
        }
    }
}
