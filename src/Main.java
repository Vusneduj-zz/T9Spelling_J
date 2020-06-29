import spelling.T9Spelling;

public class Main
{
    public static void main(String[] args)
    {
        T9Spelling  spelling    = new T9Spelling();

        String input_str = "4\nhi\nyes\nfoo  bar\nhello world";

	    System.out.println("Input:");
	    System.out.println(input_str);

	    String result   = spelling.MainProcess(input_str);

        System.out.println("Output:");
	    System.out.println(result);
    }
}
