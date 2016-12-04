
package invertedindex;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class invertedindex {

	public static HashMap<String, ArrayList<String>> h = new HashMap<String, ArrayList<String>>();
	public static HashMap<String, String> mapping = new HashMap<String, String>();
	public static HashMap<String, String> mappingtweetoId = new HashMap<String, String>();
	
	 public static void main(String[] args) {
		
		int tweetid = 0;
		String csvFile = "/Users/komalsharan/Downloads/data.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			// have to put in a while loop
			
			while ((line = br.readLine()) != null) {

				String[] country = line.split(cvsSplitBy);
				System.out.println(country[1]);
				System.out.println(country[0]);
				tweetid++;
				
				System.out.println("tweetid" + tweetid);
				// converting integer tweet to string
				String tweetidtostring = Integer.toString(tweetid);
				// putting in the hashmap which maps the object id to tweetid
				mapping.put(country[0], tweetidtostring);
				country[1]=country[1]+"|";
				
				
				// putting in the hashmap which maps the tweet to the tweetid

				mappingtweetoId.put(country[1], tweetidtostring);
              
				int j = 0;
				int i = 0;

		/*		for (String key : mappingtweetoId.keySet()) {*/
                    String  key=country[1];
                  //  System.out.println(key.length());
					
						i++;
						//System.out.println(key.length());
						String[] words = key.split("\\s+");
						for (i = 0; i < words.length; i++) {

							words[i] = words[i].replaceAll("[^\\w]", "");
							// put a check for first
							if (h.containsKey(words[i]) && !h.get(words[i]).contains(mappingtweetoId.get(key))) {

								ArrayList<String> list = h.get(words[i]);

								list.add(mappingtweetoId.get(key));
								h.put(words[i], list);

							} else {
								ArrayList<String> list1 = new ArrayList<String>();
								list1.add(mappingtweetoId.get(key));
								h.put(words[i], list1);
							}

							
							
						}
						
						

			}
			for (HashMap.Entry<String, ArrayList<String>> entry : h.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			

		}

	 catch (FileNotFoundException e) {
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
