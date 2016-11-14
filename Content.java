import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Content{
	private int number;
	private StringBuffer name;
	private int yearOfBirth;
	
	public Content(int number, StringBuffer name, int yearOfBirth){
		this.number = number;
		this.name = name;
		this.yearOfBirth = yearOfBirth;
	}
	
	public Content(){
		this.number = 0;
		this.name = null;
		this.yearOfBirth = 0;
	}
	
	public StringBuffer getName(){
		return this.name;
	}
	
	public int getYear(){
		return this.yearOfBirth;
	}
	
	public int getNum(){
		return this.number;
	}
	
	public void setName(StringBuffer names){
	   this.name = names;
	}
	
	public void setYear(int year){
		this.yearOfBirth = year;
	}
	
	public void setNum(int num){
		this.number = num;
	}
	
	public static String ReadFromFile(BufferedReader b){
		try {
		 return	b.readLine();
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;	
	}
	
	public int[] findNumbers(String[] split, Pattern p, Matcher m, boolean result, int instances, String pattern){
		int[] numbers = new int[instances];	
		p = Pattern.compile(pattern);
		int i = 0;
		int j = 0;
		
		for(i = 0; i< split.length ; i++){
			m = p.matcher(split[i]);
			result = m.matches();
			
			if(result == true){
				numbers[j] = Integer.parseInt(split[i]);
				j++;
			}
		}
		
	
		return numbers;
	}
	
	public static int findNumberOfInstances(String[] split, Pattern p, Matcher m, boolean result, String pattern){
		int instances = 0;	
		p = Pattern.compile(pattern);
		int i = 0;
		
		for(i = 0; i< split.length ; i++){
			m = p.matcher(split[i]);
			result = m.matches();
			
			if(result == true){
				instances++;
			}
		}
	
		return instances;	
	}
	

	public StringBuffer[] findNames(String[] split, Pattern p, Matcher m, boolean result, int instances, String pattern){
		StringBuffer[] buff = new StringBuffer[instances];
		int i = 0;
		int j = 0;
		int count = 0;
		
		p = Pattern.compile(pattern);
		
		for(i = 0; i < split.length; i++ ){
			m = p.matcher(split[i]);
			result = m.matches();
			
			if(result == true){
				if(count == 0){
					buff[j] = new StringBuffer();
				}
				buff[j].append(split[i] + " ");
				count++;
				if(count == 2){
					j++;
					count = 0;
				}
				
			}
		}
		
		
		return buff;
	}
	
	public  int[] findYears(String[] split, Pattern p, Matcher m, boolean result, int instances, String pattern){
		int[] years = new int[instances];
		int i = 0;
		int j = 0;
		
		p = Pattern.compile(pattern);
		
		for(i = 0; i < split.length; i++){
			m = p.matcher(split[i]);
			result = m.matches();
			
			if(result == true){
				years[j] = Integer.parseInt(split[i]);
				j++;
			}
		}
		
		return years;
		
	}
	
	public  void printResult(ArrayList<Content> list){
	    for(Content obj : list) {
	    	System.out.println(obj.getNum() + " " + obj.getName() + " " +  obj.getYear());
        }
	}
	
	public  Content[] createObjects(Content[] obj,StringBuffer[] names, int[] numbers, int[] years){
		int i = 0;
		
		for(i = 0; i < numbers.length; i++){
			obj[i] = new Content(numbers[i], names[i], years[i]);
		}
		
		return obj;
	}
	
	public  ArrayList<Content> createList(Content[] obj, ArrayList<Content> list){
		int i = 0;
		
		for(i = 0; i < obj.length; i++){
			list.add(obj[i]);
		}
	
		return list;
	}
	
	public void writeInFile( ArrayList<Content> list, String path) throws IOException{		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)))) {
			for(Content obj: list){
				writer.write(obj.getNum() + " " + obj.getName() + " " +  obj.getYear() + " ");
			}
		}
	}
	
	public  boolean checkFilePath(Pattern pattern, Matcher matcher, String path, boolean result, String filePattern){
		pattern = Pattern.compile(filePattern);
		matcher = pattern.matcher(path);
		result = matcher.matches();
		
		if(result == true){
			return result;
		}else{
			return result;
		}
	}
	
	public static String[] openFile(String path, String[] split) throws FileNotFoundException, IOException{
		String read = null;
		
		try(BufferedReader buff = new BufferedReader(new FileReader(new File(path)))){
			read = ReadFromFile(buff);
			if(read != null){
				split = read.split("\\*");
			}
		
		}
		return split;	
	}

}