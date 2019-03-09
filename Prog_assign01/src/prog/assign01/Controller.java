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
		
		if(index >= 0)
		{
			String w = list.get(index).spelling;
			int v1= index;
			int v2 = index;
			while(v1>=1 && list.get(v1-1).spelling.compareTo(w)==0)
				v1--;
			while(v2<list.size()-1 && list.get(v2+1).spelling.compareTo(w)==0)
				v2++;
			for(int i = v1; i<=v2; i++)			
				System.out.println(w + " " + list.get(i).parts_of_speech + " " + list.get(i).mean);
			
		}
		else
		{
			System.out.println("Not found.");
			System.out.println(list.get(-1*index -1).spelling + " " + list.get(-1*index -1).parts_of_speech);
			System.out.println("- - -");
			System.out.println(list.get(-1*index).spelling + " " + list.get(-1*index).parts_of_speech);
		}
			
		
	}

	private int bi_search(String word, int first, int end) {
		if(first>end)
			return -1*first;
		int mid = (first+end)/2;
		
		if(list.get(mid).spelling.compareToIgnoreCase(word)==0)
			return mid;
		else if(list.get(mid).spelling.compareToIgnoreCase(word)>0)
			return bi_search(word, first, mid -1);			
		else
			return bi_search(word, mid +1, end);
	}

	private void readFile(String file) {
		try {
			
			Scanner sc = new Scanner(new File(file));			
			while(sc.hasNext())
			{						
				String spelling = sc.next();				
				String next = sc.next();				
				while(next.charAt(0)!='(')
				{					
					spelling = spelling + " " +next;					
					if(sc.hasNext())
						next = sc.next();
				}
				String pos = next;
				if(pos.charAt(pos.length()-1)!=')')
				{
					next = sc.next();
					while(next.charAt(next.length()-1)!=')')
					{					
						pos = pos + " " +next;					
						if(sc.hasNext())
							next = sc.next();
					}
					if(next.charAt(next.length()-1)==')')
						pos = pos + " " +next;		
					
				}
				String mean = sc.next() + sc.nextLine();
				list.add(new Word(spelling,pos,mean));					
			}
			sc.close();			
			
		} catch (FileNotFoundException e) {
			System.out.println("File dose not exist");			
		}
	}

}
