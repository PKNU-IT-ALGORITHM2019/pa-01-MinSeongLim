package prog.assign01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	public ArrayList<Word> list = new ArrayList<Word>();
	
	public void exe()
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.print("$ ");
			String command = sc.nextLine();
			String[] split = command.split(" ");
			if((split[0].compareTo("read")==0 || split[0].compareTo("find")==0 ) && (split.length==1))
				System.out.println("Second Parameter is NULL");
			else if(split[0].compareTo("read")==0)			
				readFile(split[1]);			
			else if(split[0].compareTo("find")==0)
				findWord(split[1]);		
			else if(split[0].compareTo("exit")==0)
				break;
			else if(split[0].compareTo("size")==0)
				System.out.println(list.size());			
			else
				System.out.println("Unkown Command");
		}
		sc.close();		
	}

	private void findWord(String word) {
		int first = 0;
		int end = list.size()-1;
		int index = bi_search(word,first,end);		
		print(index);
	}

	private void print(int index) {
		if(index >= 0)
		{
			String word = list.get(index).spelling;
			int start;
			int end;
			for(start = index-1; start>=0 && list.get(start).spelling.compareTo(word)==0 ; start--) {}
			start++;
			for(end = index+1; end<list.size() && list.get(end).spelling.compareTo(word)==0 ; end++) {}
			end--;
			System.out.println(list.get(index).spelling);
			for(int i = start; i<=end; i++)			
				System.out.println(list.get(i).parts_of_speech + " " + list.get(i).mean);
		}
		else
		{
			index *= -1;
			System.out.println("Not found.");
			System.out.println((index!=list.size()-1)?(list.get(index).spelling + " " + list.get(index).parts_of_speech + "\n- - -\n" + list.get(index+1).spelling + " " + list.get(index).parts_of_speech):
				(list.get(index).spelling +"\n- - -\n"));
		}		
	}

	private int bi_search(String word, int first, int end) {
		if(first>end)
			return -1*end;
		int mid = (first+end)/2;
		String w = list.get(mid).spelling;
		if(w.compareToIgnoreCase(word)==0)
			return mid;
		else if(w.compareToIgnoreCase(word)>0)
			return bi_search(word, first, mid -1);			
		else
			return bi_search(word, mid +1, end);
	}

	private void readFile(String file) {
		try {			
			Scanner sc = new Scanner(new File(file));			
			while(sc.hasNext())
				split(sc.nextLine());			
			sc.close();				
		} catch (FileNotFoundException e) {
			System.out.println("File dose not exist");	
		}
	}

	private void split(String line) {
		if(line.compareTo("")==0)
			return;
		String spelling = "";
		String pos = "";
		String mean = "";
		int i = 0;
		for( ; line.charAt(i) != '(' ; i++)
			spelling+=line.charAt(i);
		spelling = spelling.substring(0, spelling.length()-1);
		for( ; line.charAt(i) != ')' ; i++)
			pos+=line.charAt(i);		
		pos+=')';
		if(i+2<line.length())
			mean = line.substring(i+2);
		list_add(spelling, pos, mean);
	}

	private void list_add(String spelling, String pos, String mean) {		
		Word w = new Word(spelling,pos,mean);
		list.add(w);						
	}
}
