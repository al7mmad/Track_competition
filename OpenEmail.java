import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URI;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;


public class OpenEmail {

    public static void main(String[] args) throws FileNotFoundException {

    		
    	String stdName="Ahmed";
    	String compName="Cyber";
    	String rank ="first";
    	String id="201926710";
    	String email=""+id+"@kfupm.edu.sa";
    	String body="Dear "+stdName+",%0D%0A%0D%0AConguratulation on your achievement in "+compName+". This achievement is deeply appreciated by the unversity and we will announce it in the approbrite medias.%0D%0A%0D%0AIn case you have Photos you want to share with the news post, reply to this email with the photos.%0D%0A%0D%0ARegards and Congrats,%0D%0A%0D%0A"
    					+ "KFUPM News Team";
				
				System.out.println(body);
        try {       
         Runtime.getRuntime().exec(
                  new String[] {"rundll32", "url.dll,FileProtocolHandler",
                        "mailto:"+email+"?subject= Congratulation on achieving "+rank+" place in "+compName+"&body="+body}, null
                  );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}