import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MySystem {
	ArrayList<Competition> compArray = new ArrayList<Competition>();

	public void read() throws FileNotFoundException, IOException {

		File file = new File("");
		Scanner scan = new Scanner(file);
		while (scan.hasNext()) {
		}
	}

	public void write() {

	}

	public  void addCompetition() {
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter the name of the comp: ");
		String name=sc.next();
		System.out.println("Enter the URL of the comp: ");
		String URL=sc.next();
		System.out.println("Enter the date of the comp: ");
		String date=sc.next();
		
		Competition c1 = new Competition(name, URL, date, true);
		compArray.add(c1);
	}

	public void addStudent() {
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter the name of the comp: ");
		String input=sc.next();
		
		Student s1=new Student("1","2","3","4","5","6");
		for(int i=0;i<compArray.size();i++) {
			if(compArray.get(i).compName.equals(input)) {
				compArray.get(i).stdArray.add(s1);
				System.out.println("ssad");
				
			}
		}

	}

	public void browse() {

	}

	public void openEmail() {

	}

	public void notifaction() {

	}

	public void view() {
		for(int i=0;i<compArray.size();i++) {
			System.out.println(compArray.get(i));
			
		}

	}

	public static void main(String[] args) {
		//System.out.println("Choose an option");
		//System.out.println();
		MySystem sys = new MySystem();
		sys.addCompetition();
		sys.addCompetition();
		
		sys.addStudent();
		System.out.println(sys.compArray.get(1));
		System.out.println(sys.compArray.get(1).stdArray.get(0));
		
	}

}
