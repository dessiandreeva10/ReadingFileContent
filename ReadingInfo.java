import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadingInfo extends Content{
	
	private static String  filePattern = "^([A-Z]{1}|[a-z]{1,10})((:\\\\)|(/))([A-Za-z0-9-._\\\\]|[A-Za-z0-9/]){1,100}([.a-z]{1,10})$";
	private static String NumberPattern = "[0-9]{1,3}";
	private static String NamePattern = "[A-Z]{1}[(a-z)]{1,20}";
	private static String YearPattern = "[0-9]{4}";
	private static Pattern p;
	private static Matcher m;
	
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Content c = new Content();
		String path = null;
		String[] split = null;
		int[] numbers = null;
		int[] years = null;
		StringBuffer[] names = null;
		int num = 0;
		boolean ret = true;
			
		try {
			System.out.println("Enter the file path (absolute path)");
			path = sc.nextLine();
			ret = c.checkFilePath(p, m, path, ret, filePattern);
			
			if(ret == true){
				split = Content.openFile(path, split);
				num = Content.findNumberOfInstances(split, p,  m, ret, NumberPattern);
				numbers = new int[num];
				years = new int[num];
				names = new StringBuffer[num];
				
				numbers = c.findNumbers(split,p, m, ret, num, NumberPattern);
			    years = c.findYears(split,p, m, ret, num, YearPattern);
			    names = c.findNames(split,p, m, ret, num, NamePattern);
			    
			    Content[] obj = new Content[numbers.length];
			    obj = c.createObjects(obj, names, numbers, years);
			    ArrayList<Content> arrList = new ArrayList<>();
			    arrList = c.createList( obj, arrList);
			    
			    Collections.sort(arrList, (obj1, obj2) -> obj1.getNum() - obj2.getNum());
			    c.printResult(arrList);
			    c.writeInFile(arrList, path);
			}else{
			    	System.out.println("This file doensn't exist!");
		    }
		} catch (Exception e) {
			System.out.println("This file doensn't exist!");
			sc.close();
		}
		
	}


}
