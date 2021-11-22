import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

	public void addCompetition() {
		Scanner sc = new Scanner(System.in);
		boolean x = false;
		boolean stdBased = false;
		boolean loopStd = false;

		System.out.println("Start the process of adding a team");

		System.out.println("Enter the name of the comp: ");
		String name = "";

		do {
			name = sc.next().toUpperCase();
			for (int i = 0; i < compArray.size(); i++) {
				if (compArray.get(i).compName.equals(name)) {
					System.out.println("Please re-enter the competition name");
					x = true;
				} else { // base case
					x = false;
				}

			}
		} while (x);

		System.out.println("Enter the URL of the comp: ");
		String URL = sc.next();
		System.out.println("Enter the date of the comp [yyyy-mm-dd]: ");
		String date = sc.next();
		String choice = "";
		do {
			System.out.println("Is the competition student based? [yes/no]");
			choice = sc.next().toUpperCase();

			if (choice.equals("YES") || choice.equals("NO")) {
				loopStd = false;
				if (choice.equals("YES")) {
					stdBased = true; // student based
					loopStd = false;
				} // else => team based

			} else // wrong input
				loopStd = true;

		} while (loopStd);
		Competition c1 = new Competition(name, URL, date, stdBased);
		compArray.add(c1);

	}

	public void addStudent() {
		boolean loopId = false;
		String teamNum = "-", teamName = "-", compType = "", rank = "", id = "";
		LocalDate currDate = LocalDate.now();
		LocalDate compDate = null;

		System.out.println("Start the process of adding a student");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter competition name: ");
		String compName = sc.next().toUpperCase();

		// Automating competition type & student Rank
		for (int i = 0; i < compArray.size(); i++)
			if (compArray.get(i).compName.equals(compName)) {
				if (compArray.get(i).compTypeStd) // true == student based
					compType = "STUDENT";
				compDate = LocalDate.parse(compArray.get(i).CompDate);

			}

//		System.out.println("Enter competition type [team/student]: ");
//		String compType = sc.next().toUpperCase();

		System.out.println("Enter student name: ");
		String name = sc.next();
//		public Student(String name, String id, String major, String rank, String teamNum, String teamName) {

		do {
			System.out.println("Enter student ID: ");
			id = sc.next();
			for (int i = 0; i < compArray.size(); i++) {
				if (compArray.get(i).compName.equals(compName)) {
					for (int j = 0; j < compArray.get(i).stdArray.size(); j++) {
						if (compArray.get(i).stdArray.get(j).id.equals(id))
							loopId = true;
					}
				}

			}
		} while (loopId);

		System.out.println("Enter student major: ");
		String major = sc.next();

		System.out.println("Enter student rank: ");
		if (currDate.isAfter(compDate))
			rank = sc.next();
		else
			rank = "-";

		if (compType.equals("STUDENT")) {
			teamNum = "-";
			teamName = "-";
		} else {
			System.out.println("Enter team number: ");
			teamNum = sc.next();
			System.out.println("Enter team name");
			teamName = sc.next();
		}

		Student s1 = new Student(name, id, major, rank, teamNum, teamName);
		for (int i = 0; i < compArray.size(); i++)
			if (compArray.get(i).compName.equals(compName)) {
				compArray.get(i).stdArray.add(s1);
//				System.out.println("ssad");

			}

	}

	public void browse() {

	}

	public void openEmail() {

	}

	public void notifaction() {
		String currentDate, compD;

		LocalDate currDate = LocalDate.now();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
		currentDate = currDate.format(fmt); // String

//		LocalDate compD = LocalDate.of(2000, 10, 1); // Create a date object

		for (int i = 0; i < compArray.size(); i++) {
			LocalDate comp = LocalDate.parse(compArray.get(i).CompDate);
			compD = comp.format(fmt);

			if (currDate.isAfter(comp)) // true= due date is over
				for (Student x : compArray.get(i).stdArray) {
					if (!compArray.get(i).notification) {
						if (!x.rank.equals("-")) { // the News team update the ranks
							compArray.get(i).notification = true;
							return;

						} else { // send a remainder to the news team
							System.out.println("Update the ranks for the " + compArray.get(i).compName
									+ " competition\nDue Date was: " + compD);
							compArray.get(i).notification = true;
						}
					}
				}
		}
	}

	public void view() {
		for (int i = 0; i < compArray.size(); i++) {
			System.out.println(compArray.get(i));

		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		String option="";	
		Scanner scan= new Scanner(System.in);
		MySystem sys = new MySystem();
//		sys.addCompetition();
//		sys.addCompetition();
//		sys.addStudent();
//		sys.notifaction();
//		sys.notifaction();
		
		System.out.print(
				"Enter your choice?\n\t1) Add a competition\n\t2) Add a student to a competition\n\t3) notification \n\t4) \n\t5) \n\t6) \n\t7) End\nEnter: ");
		option = scan.next();

		switch(option){
			case "1":{
				sys.addCompetition();
			}
			case "2":{
				sys.addStudent();
			}
			case "3":{
				sys.notifaction();
			}
			case "7":{
				System.out.println("Terminate");
				break;
			}
			default: {
				System.out.println("Invalid option");
			}
		}
//		File file2 = new File("Input.txt");
//		Scanner scan = new Scanner(file2);
//
//		String list = scan.nextLine();
//
//		while (scan.hasNext())
//			list += " " + scan.nextLine();
//
//		String[] ansArr = list.split(" ");
//		for(String x: ansArr)

	}
}
