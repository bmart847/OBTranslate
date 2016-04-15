/*
Author: Bryan Martin and Owen Enders
Class: CSI-235-01
Assignment: Final Project - OBTranslate
Due Date: 04/24/2016
Description:
    The OBTranslateServer acts as a relay between the users of OBTranslateClient
and Yandex (the translation service we are using). The server recieves the text
that the user desires to translate and then responds to the user with the
translated text.

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
package obtranslateserver;

import java.net.*;
import java.io.*;
import org.json.*;

public class OBTranslateServer {
    public static String genURL(String lang, String text){
        String urlFriendly = "";
        int size = text.length();
        char tmp;
        for(int i = 0; i < size ; i++){
            tmp = text.charAt(i);
            if(tmp == ' '){
                urlFriendly = urlFriendly + "+";
            }
            else{
                urlFriendly = urlFriendly + tmp;
            }
        }
        String apiKey = "trnsl.1.1.20160412T184233Z.a3cfaa8887cf6a35.292722aa316a1c74fa6466c47208ab78ff36d00a";
        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + apiKey + "&lang=" + lang + "&text=" + urlFriendly;
        return url;
    }
    
    public static String parseTranslation(String jsonString){
        
        String translatedText = "";
        JSONObject jsonResponse = null;
        JSONArray translationJSON = null;
        
        try{
            jsonResponse = new JSONObject(jsonString);
            translationJSON = jsonResponse.getJSONArray("text");
            translatedText = translationJSON.getString(0);
        } catch (JSONException ex){
            System.out.println("Error retrieving translation from JSON...");
            System.out.println(ex.getMessage());
        }
        
        return translatedText;
    }
    public static void main(String[] args) {
        
        System.out.println("OBTranslate Server");
        System.out.println("------------------");
        System.out.println("starting server...");
        
        Socket sSocket = null;
        ServerSocket serverSocket;
        
        try{
            serverSocket = new ServerSocket(6000);
            sSocket = serverSocket.accept();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("connected to client...");
        
        // ADD IN CODE HERE TO RETRIEVE LINE FROM CLIENT
        
        String connectionURL = genURL("ru", "Hello World.");
        URL yandexTranslate = null;
        try{
            yandexTranslate = new URL(connectionURL);
        }catch(MalformedURLException err){
            System.out.println("Error with the fetch url...");
            System.out.println(err.getMessage());
        }
        
        String apiResponse = "";
        URLConnection apiConnection = null;
        
        try{
            System.out.println("Requesting translation from Yandex...");
            apiConnection = yandexTranslate.openConnection();
            BufferedReader incoming = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
            apiResponse = incoming.readLine();
            System.out.println("Response Recieved!");         
        }catch(IOException err){
            System.out.println("Error fetching from API...");
            System.out.println(err.getMessage());
        }
        
        String translatedText = parseTranslation(apiResponse);
        System.out.println("Translation: " + translatedText);
    }
}


