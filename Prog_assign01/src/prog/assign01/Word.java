package prog.assign01;

public class Word implements Comparable<Word>{
	public String spelling;
	public String parts_of_speech;
	public String mean;
	
	public Word(String spelling, String parts_of_speech, String mean)
	{
		this.spelling = spelling;
		this.parts_of_speech = parts_of_speech;
		this.mean = mean;
	}

	@Override
	public int compareTo(Word o) {
		if(this.spelling.compareTo(o.spelling)>0)
			return 1;
		else if(this.spelling.compareTo(o.spelling)<0)
			return -1;
		else
			return 0;
	}	
}
