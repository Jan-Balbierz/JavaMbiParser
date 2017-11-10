import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
//import java.io.*;

/** Assumes UTF-8 encoding. JDK 7+. */
public class ReadWithScanner {

  public static void main(String... aArgs) throws IOException {

	System.out.println(System.getProperty("user.dir"));
    ReadWithScanner parser = new ReadWithScanner("mib.txt");
    parser.ProcessMibFile();
    log("Done.");
  }
  //TODO: Dorobic funkcje na znajdywanie obiektow (znajdz linijke poczatku i konca), danych z obiektow (przejedz od linijki x do y) itd
  /**
   Constructor.
   @param aFileName full name of an existing, readable file.
  */
  public ReadWithScanner(String aFileName){
    fFilePath = Paths.get(aFileName);
  }
  
  public void ProcessMibFile() throws IOException{
	//fFilePath = Paths.get(aFileName);
     Scanner s =  new Scanner(fFilePath);
	    while (s.hasNextLine()){
	    	String line = s.nextLine();
	    	//System.out.println(line.replaceAll("\\s+",""));
	    	if(line.indexOf("IMPORTS") != -1){
	    		System.out.println("lol");
	    		s.nextLine();
	    		System.out.println(s.next());
	    	}
	    	else{
	    		//System.out.println(line.replaceAll("\\s+",""));
	    		//System.out.print("lul: ");
	    		//System.out.println(s.next());
	    	}
	    	//System.out.println("lol");
	    }
  }
	
  
  
  // PRIVATE 
  private Path fFilePath;
  private final static Charset ENCODING = StandardCharsets.UTF_8;  
  
  private static void log(Object aObject){
    System.out.println(String.valueOf(aObject));
  }
  
  private String quote(String aText){
    String QUOTE = "'";
    return QUOTE + aText + QUOTE;
  }
} 