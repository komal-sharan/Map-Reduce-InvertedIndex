package invertedindex;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class invertedindex {

	
    public static void main(String[] args) {
    	HashMap<String,ArrayList<String>> h = new HashMap<String, ArrayList<String>>() ;
    	HashMap<String,String> mapping = new HashMap<String, String>() ;
    	HashMap<String,String> mappingtweetoId = new HashMap<String, String>() ;
    	int tweetid=0;
        String csvFile = "/Users/komalsharan/Downloads/data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
        	
           br = new BufferedReader(new FileReader(csvFile));
          //have to put in a while loop
            while ((line = br.readLine()) != null) {

            System.out.println("dsfddfdfg");
            String[] country = line.split(cvsSplitBy);
            tweetid++;
        System.out.println("tweetid"+tweetid);
            //converting integer tweet to string
            String tweetidtostring=Integer.toString(tweetid);
            //putting in the hashmap which maps the object id to tweetid
            mapping.put(country[0], tweetidtostring);
           //putting in the hashmap which maps the tweet to the tweetid
           

            mappingtweetoId.put(country[1], tweetidtostring);
           

            int j=0;
            int i=0;
            
            for (String key :mappingtweetoId.keySet()) {
               
                  while(key.charAt(j)!='|'){
            	
                 String[] words = key.split("\\s+");
                 for (i = 0; i < words.length; i++) {
                   // You may want to check for a non-word character before blindly
                   // performing a replacement
                   // It may also be necessary to adjust the character class
                   words[i] = words[i].replaceAll("[^\\w]", "");
                   //put a check for first
                   if (h.containsKey(words[i]) && !h.get(words[i]).contains(mappingtweetoId.get(key)))
                   {   
                      
                	   ArrayList<String> list=h.get(words[i]);
                	   
                       list.add(mappingtweetoId.get(key));
                       h.put(words[i],list);
                       

                   }
                   else 
                   {  
                	  ArrayList<String> list1=new  ArrayList<String>();
                	  list1.add(mappingtweetoId.get(key));
                     h.put(words[i], list1);
                   }
                    
                            
                   for (HashMap.Entry<String, ArrayList<String>> entry : h.entrySet()) {
                 	    System.out.println(entry.getKey()+" : "+entry.getValue());
                 	} 
                   
                   
                   
                    
                 
                 }
               
                 
                 
                 
                 
            }
                  
            }
            
            
            }
            
            
            

        } catch (FileNotFoundException e) {
        	
            e.printStackTrace();
        } catch (IOException e) {
        	
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                	

                    e.printStackTrace();
                }
            }
        }

    }

}










