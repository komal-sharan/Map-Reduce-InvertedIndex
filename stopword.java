package com.osproj.stopword;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class stopword {
	public static String[] stopwords;
	public static Set<String> stopWordSet;

	public static void innitialize() //call only once
	{		
		try (Stream<String> stream = Files.lines(Paths.get("StopWord.txt"))) {
			stopwords = (String[]) stream.toArray(size -> new String[size]);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//System.out.println(stopwords.length);
		stopWordSet = new HashSet<String>(Arrays.asList(stopwords));
	}
	
	public static boolean isStopword(String word) {
		if(word.length() < 2) return true;
		if(word.charAt(0) >= '0' && word.charAt(0) <= '9') return true; //remove numbers, "25th", etc
		if(stopWordSet.contains(word)) return true;
		else return false;
	}
	
	public static String getStopWordRemovedString(String uncleanedString)
	{
		String stopWordRemovedString = "";
		String[] words = uncleanedString.split("\\W+");
		for (String word : words) {
			if(!isStopword(word))
			{
				stopWordRemovedString = stopWordRemovedString + " " + word.trim();
			}
		}
		return stopWordRemovedString;
	}
}
