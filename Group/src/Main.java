
public class Main {

	public static void main(String[] args) {
		System.out.println(fun1(5,3));

	}
	
	public static int fun1(int x, int y)
	{
		if(x==0)
			return y;
		return fun1(x-1,x+y);
	}

}
