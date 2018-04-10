    

import java.io.File;  
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  
    
    
public class word_ladder {
	public static int ladder(String start, String end, List<String> dict) {
		if (start == null || end == null || start.length() == 0 || end.length() == 0 || start.length() != end.length() ) {
			System.out.println("No ladder.");
			return 0;
		}
		if (start.equals(end)) {
			System.out.println("Same words.");
			return 0;
		}
		LinkedList<String> cases = new LinkedList<String>();
		LinkedList<LinkedList<String>> all = new LinkedList<LinkedList<String>>();
		cases.add(start);
		all.add(cases);
		int n = all.size();
		char[] endunit = end.toCharArray();
		while(true) {
			for(int i = 0; i < n; i++) {
				cases = all.get(i);
				for(int j = 0; j < start.length(); j++) {
					String temp = cases.getLast();
					char[] wordunit = temp.toCharArray();
					wordunit[j] = endunit[j];
					temp = new String(wordunit);
					if(temp.equals(end)) {
						cases.add(temp);
						System.out.println("Found ladder:");
						for (int a = 0; a < cases.size(); a++) {
							System.out.println(cases.get(a));
						}
						return 0;
					}
					else if(dict.contains(temp)&&!cases.contains(temp)) {
						cases.add(temp);
						all.add(cases);
					}
					else temp = cases.getLast();
				}
				for(int k = 0; k < n; k++) all.removeFirst();
				n = all.size();
				if(n == 0) { 
					System.out.println("No ladder.");
					return 0;
				}
			}
		}
	}
	    public static List<String> read(File txt){
	        StringBuilder result = new StringBuilder();
	        List<String> dict = new ArrayList<String>();
	        try{
	            BufferedReader br = new BufferedReader(new FileReader(txt));
	            String s = null;
	            while((s = br.readLine())!=null){
	            	dict.add(s);
	                result.append(System.lineSeparator()+s);
	            }
	            br.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return dict;
	    }
	    
	    public static String ReadTest(){   
	        System.out.println("Please Enter a word:");   
	        InputStreamReader is = new InputStreamReader(System.in);    
	        BufferedReader br = new BufferedReader(is);
	        String word = "";
	        try{   
	          word = br.readLine();
	        }   
	        catch(IOException e){   
	          e.printStackTrace();   
	        }
	        return word;
	      }  
	    
	    public static void main(String[] args){
	        File direct = new File("smalldict1.txt");
	        List<String> dict = read(direct);
	        String start = ReadTest();
	        String end = ReadTest();
	        ladder(start,end,dict);
	    }
	}
