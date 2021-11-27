import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.TextTable.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.sun.rowset.internal.Row;

public class MySystem {
	static ArrayList<Competition> compArray = new ArrayList<Competition>();
	DataFormatter Format = new DataFormatter();
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-mm-dd");

	public void read() throws FileNotFoundException, IOException {
		String sheetName = "";
		// Notice we had two kind of Excel file for comp. solo & team
		try {
			// creating Workbook instance that refers to .xlsx file
			Workbook wb = new XSSFWorkbook(new File("Competitions Participations.xlsx"));

			int numberOfSheets = wb.getNumberOfSheets(); // which = num of competitions
//			System.out.println("Number of sheets in this excel file is: "+NumberOfSheets+"\n");

			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = wb.getSheetAt(i);
				Iterator<Row> RowItr = sheet.iterator();

				Row row = RowItr.next();// row#1
				String compName = row.getCell(1).getStringCellValue();

				row = RowItr.next();// #2

				String compURL = row.getCell(1).getStringCellValue(); // why don't we use Hperlink class?

				row = RowItr.next();// #3
				String compDate = Format.formatCellValue(row.getCell(1));

//				row = RowItr.next(); //empty one row#4 // NO NEED THE RowItr Automatically skip it
//				System.out.println(row.getCell(0));

				row = RowItr.next(); // #5 Help me to decide whither it teams/solo based

				// Now Read Competition Students Info
//				Competition comp;
				sheetName = sheet.getSheetName();
				if (row.getCell(4).getStringCellValue().equals("team")) {
					Competition comp = new Competition(compName, compURL, compDate, false, sheetName);
					compArray.add(comp);
					readStdTeamBased(RowItr, comp);

				} else {// student Based
					Competition comp = new Competition(compName, compURL, compDate, true, sheetName);
					compArray.add(comp);
					readStdSoloBased(RowItr, comp);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void view(ArrayList<Competition> compArray) {
		for (Competition c : compArray) {
			System.out.println(
					"Name--> " + c.compName + "\n" + "Link--> " + c.compURL + "\n" + "Date--> " + c.CompDate + "\n");
			System.out.println("Student ID\t\tStudent Name\t\t Major\t\t Team\t\t Team Name\t\tRank\t\t");
			System.out.println("__________________________________________________________________________");

			for (Student s : c.stdArray) {
				System.out.print(s.id + " \t" + s.name + " \t\t" + s.major + " \t\t" + s.teamNum + " \t\t" + s.teamName
						+ " \t\t" + s.rank + "\n");
			}

			System.out.println("\n");
		}
	}

	// if it is team based V
	private void readStdTeamBased(Iterator<Row> RowItr, Competition C) {
		int n = 5;
		while (RowItr.hasNext()) {
			Row row = RowItr.next();
			Iterator<Cell> cellItr = row.cellIterator();
			Student std = new Student(); // !

			// dealing with columns here
			Cell cell = cellItr.next(); // col#1 no need
			cell = cellItr.next();
			std.id = Format.formatCellValue(cell); // use this guy
			cell = cellItr.next();
			std.name = cell.getStringCellValue();
			cell = cellItr.next();
			std.major = cell.getStringCellValue();
			cell = cellItr.next();
			std.teamNum = Format.formatCellValue(cell);
			cell = cellItr.next();
			std.teamName = cell.getStringCellValue();
			cell = cellItr.next();
			std.rank = Format.formatCellValue(cell);
			// Now add the student to the competition
			C.stdArray.add(std);
			n++;
		}
		C.setNumber(n);

	}

	private void readStdSoloBased(Iterator<Row> RowItr, Competition C) {
		int n = 5;
		while (RowItr.hasNext()) {
			Row row = RowItr.next();
			Iterator<Cell> cellItr = row.cellIterator();
			Student std = new Student(); // !

			Cell cell = cellItr.next(); // col#1 no need
			cell = cellItr.next();
			std.id = Format.formatCellValue(cell); // use this guy
			cell = cellItr.next();
			std.name = cell.getStringCellValue();
			cell = cellItr.next();
			std.major = cell.getStringCellValue();
			cell = cellItr.next();
			std.rank = Format.formatCellValue(cell);
			std.teamNum = "-";
			std.teamName = "-";
			// Now add the student to the competition
			C.stdArray.add(std);
			n++;
		}
		C.setNumber(n);
		System.out.println("\n");
	}

	// ___________________________________________________________________________________
	public void write() throws InvalidFormatException, IOException, InvalidFormatException {

	}

	public void addCompetition() throws IOException, InvalidFormatException {
		String compName = "";
		Scanner sc = new Scanner(System.in);
		boolean x = false, stdBased = false, loopStd = false;

		System.out.println("Start the process of adding a team");

		System.out.println("Enter the name of the comp: ");

		do {
			compName = sc.next().toUpperCase();
			for (Competition c : compArray) {
				if (compName.equals(c.compName)) {
					System.out.println("The competition is already added. Please re-enter the competition name");
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
		Competition c1 = new Competition(compName, URL, date, stdBased, compName);
		compArray.add(c1);

//		------------------
//		Here we deal with the excel file
		Workbook wb = new XSSFWorkbook(new File("Competitions Participations.xlsx"));
		Sheet sheet = wb.createSheet(compName);
		XSSFRow RowItr = null;

		Map<String, Object[]> studentData = new TreeMap<String, Object[]>();

		studentData.put("1", new Object[] { "Competition Name", compName });
		studentData.put("2", new Object[] { "Comp URL", URL });
		studentData.put("3", new Object[] { "Comp date", date });
		studentData.put("4", new Object[] {}); // leave an empty row

		// Student based
		if (stdBased)
			studentData.put("5", new Object[] { "", "Student ID", "Student Name", "Major", "Rank" });
		else
			studentData.put("5",
					new Object[] { "", "Student ID", "Student Name", "Major", "Team ", "Team Name", "Rank" });

		// writing the data into the sheets...

		Set<String> keyid = studentData.keySet();

		int rowid = 0;

		for (String key : keyid) {

			RowItr = (XSSFRow) sheet.createRow(rowid++);
			Object[] objectArr = studentData.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = ((Row) RowItr).createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}

		FileOutputStream out = new FileOutputStream(new File("NewWB.xlsx"));

		wb.write(out);
		out.close();

	}

	public void addStudent() throws InvalidFormatException, IOException, NullPointerException {
		// First student will be at ROW6!
		int number = 0;
		Scanner sc = new Scanner(System.in);

		boolean loopId = false, cNameCheck = false;

		String teamNum = "-", teamName = "-", compType = "", rank = "", id = "", sheetName = "";
		LocalDate currDate = LocalDate.now();
		LocalDate compDate = null;

		System.out.println("Start the process of adding a student");

		System.out.println("Enter competition name: ");
		String compName = sc.next().toUpperCase();
		for (Competition c : compArray) {
//			System.out.println(compName); //checking
			if (compName.equalsIgnoreCase(c.compName))
				cNameCheck = true;
		}
		if (!cNameCheck)
			throw new IllegalArgumentException("There is no competition with this name! ");

		// Automating competition type & student Rank
		for (int i = 0; i < compArray.size(); i++)
			if (compArray.get(i).compName.equalsIgnoreCase(compName)) {
				if (compArray.get(i).compTypeStd) // true == student based
					compType = "STUDENT";
//				compDate = LocalDate.parse(compArray.get(i).CompDate); //to get competition date

			}

		System.out.println("Enter student name: ");
		String name = sc.next();

		do {
			System.out.println("Enter student ID: ");
			id = sc.next();
			for (Competition c : compArray) {
				if (c.compName.equalsIgnoreCase(compName)) {
					for (int j = 0; j < c.stdArray.size(); j++) {
						if (c.stdArray.get(j).id.equals(id)) {
							loopId = true;
							System.out.println("Id is already added, try another one");
						}
					}
				}

			}
		} while (loopId);

		System.out.println("Enter student major: ");
		String major = sc.next().toUpperCase();

		System.out.println("Enter student rank: ");

		// **Check how to change date format
//		System.out.println(compArray.get(0).CompDate);
//		System.out.println(currDate);
//		System.out.println(currDate.isAfter(compDate));
//		if (currDate.isAfter(compDate))
		rank = sc.next();
//		else
//			rank = "-";

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
		for (Competition c : compArray)
			if (c.compName.equalsIgnoreCase(compName)) {
				c.stdArray.add(s1);
				sheetName = c.sheet;
				number = c.getNumber();
			}

		try {

			Workbook wb = new XSSFWorkbook(new File("Competitions Participations.xlsx"));
			Sheet sheet = wb.getSheet(sheetName);
			XSSFRow RowItr = null;

//			System.out.println(sheet.getSheetName());

			Map<String, Object[]> studentData = new TreeMap<String, Object[]>();

			if (compType.equals("STUDENT"))
				studentData.put("1", new Object[] { number - 4, id, name, major, rank });
			else
				studentData.put("1", new Object[] { number - 4, id, name, major, teamNum, teamName, rank });

			Set<String> keyid = studentData.keySet();

			int rowid = number;

			for (String key : keyid) {
				RowItr = (XSSFRow) sheet.createRow(rowid++);
				Object[] objectArr = studentData.get(key);
				int cellid = 0;

				for (Object obj : objectArr) {
					Cell cell = ((Row) RowItr).createCell(cellid++);
					cell.setCellValue(obj.toString());
				}
			}

			FileOutputStream out = new FileOutputStream(new File("X.xlsx"));

			wb.write(out);
			out.close();

		} catch (Exception e) {
			System.out.println("Error: The competition does not exist.");
			System.out.println(e.getMessage());
		}

		System.out.println("Done\n");
	}

	public void browse() {

	}

	public void openEmail() {

	}

	public void notifaction() {
		String currentDate, compD;

		LocalDate currDate = LocalDate.now();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy MM dd");
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

//	public void view() {
//		for (int i = 0; i < compArray.size(); i++) {
//			System.out.println(compArray.get(i));
//
//		}
//
//	}

	public static void main(String[] args) throws IOException, InvalidFormatException, ParseException {
		String option = "";
		boolean x = true;
		Scanner scan = new Scanner(System.in);
		MySystem sys = new MySystem();

//		sys.read();
//		sys.addC();
//		sys.read();
//		sys.addS();
//		 String OLD_FORMAT = "dd/MM/yyyy";
//		 String NEW_FORMAT = "yyyy-MM-dd";
//
//		// August 12, 2010
//		String oldDateString = "12/08/2010";
//		String newDateString;
//
//		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
//		Date d = sdf.parse(oldDateString);
//		sdf.applyPattern(NEW_FORMAT);
//		newDateString = sdf.format(d);
//		System.out.println(newDateString);
		sys.read();
		sys.view(compArray);

		while (x) {

			System.out.print(
					"Enter your choice?\n\t1) Add a competition\n\t2) Add a student to a competition\n\t3) notification \n\t4)View competitions \n\t5) \n\t6) \n\t7) End\nEnter: ");
			option = scan.next();

			switch (option) {
			case "1": {
				sys.addCompetition();
				break;
			}
			case "2": {
				sys.addStudent();
				break;
			}
			case "3": {
				sys.notifaction();
				break;
			}
			case "4": {
				sys.read();
				break;
			}
			case "7": {
				System.out.println("Terminate");
				x = false;
				break;
			}
			default: {
				System.out.println("Invalid option");
			}
			}
		}
	}
}