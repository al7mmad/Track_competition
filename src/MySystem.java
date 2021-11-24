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

	DataFormatter Format = new DataFormatter();


	public void read() throws FileNotFoundException, IOException {
		// Notice we had two kind of Excel file for comp. solo & team
		try
		{
			//creating Workbook instance that refers to .xlsx file
			Workbook wb = new XSSFWorkbook(new File("Competitions Participations.xlsx"));

			int numberOfSheets = wb.getNumberOfSheets(); // which = num  of competitions
//			System.out.println("Number of sheets in this excel file is: "+NumberOfSheets+"\n");


			for(int i =0 ; i<numberOfSheets ; i++){
				Sheet sheet = wb.getSheetAt(i);
				Iterator<Row> RowItr = sheet.iterator();

				Row row = RowItr.next();// row#1
				String compName =   row.getCell(1).getStringCellValue() ;

				row = RowItr.next();//#2

				String compURL =  row.getCell(1 ).getStringCellValue() ; //why don't we use Hperlink class?

				row = RowItr.next();//#3
				String compDate =  Format.formatCellValue(row.getCell(1 )) ;

//				row = RowItr.next(); //empty one row#4 // NO NEED THE RowItr Automaticaly skip it
//				System.out.println(row.getCell(0));

				row = RowItr.next(); //#5 Help me to decide whither it teams/solo based

				// Now Read Competition Students Info
				if(row.getCell(4).getStringCellValue().equals("team")){
					Competition comp = new Competition( compName,compURL,compDate,false );
					compArray.add(comp);
					readStdTeamBased(RowItr,comp);
				}
				else{
					Competition comp = new Competition( compName,compURL,compDate,true );
					compArray.add(comp);
					readStdSoloBased(RowItr,comp);
				}


			}

//_________________________Just print it in console to check did he reed it ?
			System.out.println("Student ID\t\tStudent Name\t\t Major\t\t Teem\t\t Teem Name\t\tRank\t\t");
			System.out.println("__________________________________________________________________________");
			for(Competition c : compArray){
								System.out.println("Name--> " + c.compName + "\n" +
						"Link--> " + c.compURL + "\n" +
						"Date--> " + c.CompDate + "\n");
				for(Student s : c.stdArray){
					System.out.print(s.id + " \t" +s.name + " \t\t" +s.major + " \t\t" +s.teamNum + " \t\t" +s.teamName +" \t\t"+s.rank+"\n" );
				}
			}
//________________________________________________________________________________________________

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	// if it is team based V
	private void readStdTeamBased(Iterator<Row> RowItr,Competition C ){
		ArrayList<Student> stdArr = new ArrayList<>(); // I think we should list them in App serial ID

		while(RowItr.hasNext()){
			Row row = RowItr.next();
			Iterator<Cell> cellItr = row.cellIterator();
			Student std = new Student(); //!

			Cell cell = cellItr.next() ; // col#1  no need
			cell = cellItr.next() ;
			std.id = Format.formatCellValue(cell); // use this guy
			cell = cellItr.next() ;
			std.name = cell.getStringCellValue();
			cell = cellItr.next() ;
			std.major = cell.getStringCellValue();
			cell = cellItr.next() ;
			std.teamNum =  Format.formatCellValue(cell);
			cell = cellItr.next() ;
			std.teamName = cell.getStringCellValue();
			cell = cellItr.next() ;
			std.rank = Format.formatCellValue(cell);
			//Now add the student to the competition
			C.stdArray.add(std);
		}
	}
	private void readStdSoloBased(Iterator<Row> RowItr , Competition C ){
		while(RowItr.hasNext()){
			Row row = RowItr.next();
			Iterator<Cell> cellItr = row.cellIterator();
			Student std = new Student(); //!

			Cell cell = cellItr.next() ; // col#1  no need
			cell = cellItr.next() ;
			std.id = Format.formatCellValue(cell); // use this guy
			cell = cellItr.next() ;
			std.name = cell.getStringCellValue();
			cell = cellItr.next() ;
			std.major = cell.getStringCellValue();
			cell = cellItr.next() ;
			std.rank = Format.formatCellValue(cell);
			//Now add the student to the competition
			C.stdArray.add(std);
		}
	}

//___________________________________________________________________________________
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
