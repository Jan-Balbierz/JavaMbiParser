import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.regex.Matcher;
import java.io.*;
//import java.util.regex.Pattern;

/** Assumes UTF-8 encoding. JDK 7+. */
public class ReadWithScanner {

  public static void main(String... aArgs) throws IOException {

	System.out.println(System.getProperty("user.dir"));
    ReadWithScanner parser = new ReadWithScanner("mib.txt");
    //parser.ProcessMibFile();
    parser.MibGroupsMapping();
  }
  //TODO: Dorobic funkcje na znajdywanie obiektow (znajdz linijke poczatku i konca), danych z obiektow (przejedz od linijki x do y) itd
  /**
   Constructor.
   @param aFileName full name of an existing, readable file.
  */
  public ReadWithScanner(String aFileName){
    fFilePath = Paths.get(aFileName);
  }
  
  ArrayList<MibType> ArrayOfTypes = new ArrayList<MibType>();
  int[] mib2 = {1,3,6,1,2,1};
  ArrayList<String> MibGroups = new ArrayList<String>();
 
  public Map<String, Integer> MibGroupsMapping() throws IOException
  {
	  Scanner sc =  new Scanner(fFilePath);
	  Map<String, Integer> Groups = new HashMap<String, Integer>();
	  int group_oid;
	  while(sc.hasNextLine()){
		String grline = sc.nextLine();
		Matcher mGroup  = Pattern.compile("\\s+(\\w+)\\s+OBJECT\\sIDENTIFIER\\s::=\\s\\{\\s(mib-2)\\s(\\d+)\\s\\}").matcher(grline); 
		if(mGroup.find()){
			
//			System.out.println("Group 1 = " + mGroup.group(0));
//			System.out.println("Group 2 = " + mGroup.group(1)); // name
//			System.out.println("Group 3 = " + mGroup.group(2));
//			System.out.println("Group 3 = " + mGroup.group(3)); // number
			group_oid = Integer.parseInt(mGroup.group(3));
			Groups.put(mGroup.group(1),group_oid);
			
		}
		
	  }
	  for (Map.Entry<String, Integer> entry : Groups.entrySet())
		{
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
//	  System.out.println(Groups.get(1));
	  
	  return Groups;
  }
  
  
  public void ProcessMibFile() throws IOException{
	//fFilePath = Paths.get(aFileName);
     Scanner s =  new Scanner(fFilePath);
     String mib_name ="";
     String access="";
     String Description="";
     int child_oid;
	    while (s.hasNextLine()){
	    	String line = s.nextLine();
	    	//System.out.println(line.replaceAll("\\s+",""));
	    	/*if(line.indexOf("IMPORTS") != -1){
	    		System.out.println("lol");
	    		//s.nextLine();
	    		for( int i = 0; i < 20; i++)
	    		{
	    		System.out.println(s.next());
	    		}
	    	
	    	
	    		
	    	} // CHUJ
	    	else{
	    		//System.out.println(line.replaceAll("\\s+",""));
	    		//System.out.print("lul: ");
	    		//System.out.println(s.next());
	    	}
	    	//System.out.println("lol");
	    */
	    	//System.out.println(line);
	    	Matcher mNAME  = Pattern.compile("\\s+(.*?)\\sOBJECT-TYPE").matcher(line); 
	    	Matcher mACCESS  = Pattern.compile("\\s+ACCESS+.\\s(.*)").matcher(line); 
	    	//Matcher DESCRIPTION  = Pattern.compile("\\s+(.*?)\\sOBJECT-TYPE").matcher(line); 
	    	Matcher mOID = Pattern.compile("(::=\\s+\\{(.*?)\\})").matcher(line);
	
	    	
	    	
	    	if (mNAME.find()) {
	    		//NAME.group().replace("OBJECT-TYPE","");
	           // System.out.println("NAME = " + mNAME.group().replace("OBJECT-TYPE","").trim()); 
	            
	            mib_name = mNAME.group().replace("OBJECT-TYPE","").trim();
	            
	           // System.out.println(mib_name);
	            //MibType mib = new MibType(NAME, );
	            //ArrayOfTypes.add(object);
	            
	    	}
	    	
	    	 if(mACCESS.find()){
	            	//access = mACCESS.group().replace("ACCESS","").trim();
	    		 access = mACCESS.group().replace("ACCESS","").trim();
	            	//System.out.println(access);
	
	            }
	    	if(mOID.find()){
            	//System.out.println(access);
            	
	    		
	    	}
	    	 
	    	 //System.out.println(mib_name); 
	    	 //
	    	 //System.out.println("ACCESS = " + mACCESS.group().replace("ACCESS","").trim()); 
	    }
  }
	/*public static void regexChecker(String theRegex, String str2Check)
	{
		
		Pattern checkRegex = Pattern.compile(theRegex);
		
		Matcher regexMatcher = checkRegex.matcher(str2Check);
		
		
		
		
	}*/
  
  
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


class MibType {
	public String NAME;
	public String ACCESS;
	public String DESCRIPTION;
	List<Integer> oid = new ArrayList<Integer>();
	MibType(String in_NAME, String in_ACCESS, String in_DESCRIPTION, List<Integer> parent_mib, int children_mib){
		oid = parent_mib;
		oid.add(children_mib);
	    NAME = in_NAME;
	    ACCESS = in_ACCESS;
	    DESCRIPTION = in_DESCRIPTION;
    
    }
   }

 class MibIdentifiers{
	 MibIdentifiers(){};
	 public List<String> mib_string_list;
	 
	 void GetStringList(){
		 List<String> list = new ArrayList<String>();
		// "system 1.1.235.6.5"
		/* while(hasnextline){
		 
				 
		 }*/
		 mib_string_list = list;
	 }
	// List<Integer> ParseStringList(List<String> listofstrings, String input){
		 //List<String> list = new ArrayList<String>();
	 }
	 
	 
	 
 
 
 
