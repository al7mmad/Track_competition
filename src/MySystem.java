import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.model.RowBlocksReader;
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
		compArray.clear();

		try {
			// creating Workbook instance that refers to .xlsx file
			Workbook wb = new XSSFWorkbook(new File("Competitions Participations.xlsx"));

			int numberOfSheets = wb.getNumberOfSheets(); // which = num of competitions
//        	System.out.println("Number of sheets in this excel file is: "+NumberOfSheets+"\n");

			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = wb.getSheetAt(i);
				Iterator<Row> RowItr = sheet.iterator();

				Row row = RowItr.next();// row#1
				String compName = row.getCell(1).getStringCellValue();

				row = RowItr.next();// #2

				String compURL = row.getCell(1).getStringCellValue();

				row = RowItr.next();// #3
				String compDate = Format.formatCellValue(row.getCell(1));
				compDate = dateParser(compDate); // all comps with the same date format!

				row = RowItr.next();// #4

				if (i > 1) // to avoid NULL values from the created sheets, dont remove!!
					row = RowItr.next();

				// for checking the cell name
//        		if (row.getCell(4) != null) {
//        		String cell2Update = sheet.getRow(row.getRowNum()).getCell(4).getStringCellValue();
//        		System.out.println(cell2Update);
//        		}

				// Now Read Competition Students Info

				sheetName = sheet.getSheetName();
//        		System.out.println(row.getCell(4).getStringCellValue());
				if (row.getCell(4).getStringCellValue().equalsIgnoreCase("TEAM")) {
					Competition comp = new Competition(compName, compURL, compDate, false, sheetName);
					compArray.add(comp);
					readStdTeamBased(RowItr, comp);

				} else {// student Based
//        			System.out.println("STUDENT");
					Competition comp = new Competition(compName, compURL, compDate, true, sheetName);
					compArray.add(comp);
					readStdSoloBased(RowItr, comp);
				}

				wb.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//--------------------------- Finish Read ---------------------
	public void view() {
		System.out.println(
				"\n\n***********************************************************************************************");
		for (Competition c : compArray) {

			System.out.println(
					"Name--> " + c.compName + "\n" + "Link--> " + c.compURL + "\n" + "Date--> " + c.CompDate + "\n");
			System.out.println("Student ID\t\tStudent Name\t\t Major\t\t Team\t\t Team Name\t\tRank\t\t");
			System.out.println("__________________________________________________________________________");

			for (Student s : c.stdArray) {
				System.out.printf("%-15s %-20s %-12s %-10s %-15s %-15s %n", s.id, s.name, s.major, s.teamNum,
						s.teamName, s.rank);
			}

			System.out.println("\n");
		}
	}

	public void view(Competition c, Student s) {
		System.out.println(
				"\n\n***********************************************************************************************");

		System.out.println("Name--> " + c.compName + "\n" + "Date--> " + c.CompDate + "\n");
		System.out.println("Student ID\t\tStudent Name\t\t Major\t\t Team\t\t Team Name\t\tRank\t\t");
		System.out.println(
				"_______________________________________________________________________________________________");

		System.out.printf("%-15s %-20s %-12s %-10s %-15s %-15s %n", s.id, s.name, s.major, s.teamNum, s.teamName,
				s.rank);

	}

	// if it is team based V
	private void readStdTeamBased(Iterator<Row> RowItr, Competition C) {
		// ^ Add it for serial num
		while (RowItr.hasNext()) {
			Row row = RowItr.next();
			Iterator<Cell> cellItr = row.cellIterator();

			// dealing with columns here
			Cell cell = cellItr.next(); // col#1 no need
			int std_serial = Integer.parseInt(Format.formatCellValue(cell));
			cell = cellItr.next();
			String std_id = Format.formatCellValue(cell); // use this guy
			cell = cellItr.next();
			String std_name = cell.getStringCellValue();
			cell = cellItr.next();
			String std_major = cell.getStringCellValue();
			cell = cellItr.next();
			String std_teamNum = Format.formatCellValue(cell);
			cell = cellItr.next();
			String std_teamName = cell.getStringCellValue();
			cell = cellItr.next();
			String std_rank = Format.formatCellValue(cell);
			Student std = new Student(std_serial, std_name, std_id, std_major, std_rank, std_teamNum, std_teamName);

			// Now add the student to the competition
			C.stdArray.add(std);
		}
		// here C****
	}

	private void readStdSoloBased(Iterator<Row> RowItr, Competition C) {
		while (RowItr.hasNext()) {
			Row row = RowItr.next();
			Iterator<Cell> cellItr = row.cellIterator();

			Cell cell = cellItr.next(); // col#1 no need
			int std_serial = Integer.parseInt(Format.formatCellValue(cell));
			cell = cellItr.next();
			String std_id = Format.formatCellValue(cell); // use this guy
			cell = cellItr.next();
			String std_name = cell.getStringCellValue();
			cell = cellItr.next();
			String std_major = cell.getStringCellValue();
			cell = cellItr.next();
			String std_rank = Format.formatCellValue(cell);
			Student std = new Student(std_serial, std_name, std_id, std_major, std_rank, "-", "-");

			// Now add the student to the competition
			C.stdArray.add(std);
		}
		// C*****
		System.out.println("\n");
	}

	// ___________________________________________________________________________________
	// D*** write() no need

	public void addCompetition() throws IOException, InvalidFormatException, ParseException {
		String compName = "";
		boolean x = false, stdBased = false, loopStd = false;
		Scanner sc = new Scanner(System.in);

		System.out.println("Start the process of adding a team\n");

		do {
			System.out.println("Enter the name of the comp: ");
			compName = sc.next();
			for (Competition c : compArray) {
				if (compName.equalsIgnoreCase(c.compName) || compName.equalsIgnoreCase(c.sheet)) {
					System.out.println("The competition is already added. Please re-enter the competition name");
					x = true;
					break;
				} else { // base case
					x = false;
				}

			}
		} while (x);

		System.out.println("Enter the URL of the comp: ");
		String URL = sc.next();
		System.out.println("Enter the date of the comp [yyyy-mm-dd]: ");
		String date = sc.next();
		date = dateParser(date);
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
		FileInputStream file = new FileInputStream("Competitions Participations.xlsx");
		Workbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.createSheet(compName);
		Row RowItr = null;

		Map<String, Object[]> studentData = new TreeMap<String, Object[]>();

		studentData.put("1", new Object[] { "Competition Name", compName });
		studentData.put("2", new Object[] { "Competition URL", URL });
		studentData.put("3", new Object[] { "Competition date", date });
		studentData.put("4", new Object[] { "", "", "", "", "" }); // leave an empty row

		// Student based
		if (stdBased)
			studentData.put("5", new Object[] { "", "Student ID", "Student Name", "Major", "Rank" });
		else
			studentData.put("6",
					new Object[] { "", "Student ID", "Student Name", "Major", "team", "Team Name", "Rank" });

		// writing the data into the sheets...

		Set<String> keyid = studentData.keySet();

		int rowid = 0;

		for (String key : keyid) {
			RowItr = sheet.createRow(rowid++);
			Object[] objectArr = studentData.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = (RowItr).createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
//		file.close();
		FileOutputStream out = new FileOutputStream(new File("Competitions Participations.xlsx"));

		wb.write(out);
		wb.close();
		out.close();

		System.out.println("Done\n");

	}

	public void addStudent() throws InvalidFormatException, IOException, NullPointerException {
		// First student will be at ROW6!
		Scanner sc = new Scanner(System.in);
		boolean loopId = false, cNameCheck = false;

		// Printing comp names
		System.out.print("\nCompetition names: ");
		for (Competition c : compArray)
			System.out.print(c.compName + " - ");

		String teamNum = "-", teamName = "-", compType = "", rank = "", id = "", sheetName = "";
		LocalDate currDate = LocalDate.now();
		LocalDate compDate = null;

		System.out.println("\n\nStart the process of adding a student");

		System.out.println("Enter competition name: ");
		String compName = sc.next();
		for (Competition c : compArray) {
//			System.out.println(c.compName); //checking
			if (compName.equalsIgnoreCase(c.compName))
				cNameCheck = true;
		}
		if (!cNameCheck)
			throw new IllegalArgumentException("There is no competition with this name! ");

		// Automating competition type & student Rank
		for (Competition c : compArray)
			if (c.compName.equalsIgnoreCase(compName)) {
				if (c.compTypeStd) // true == student based
					compType = "STUDENT";

				compDate = LocalDate.parse(c.CompDate); // to get competition date

			}

		System.out.println("Enter student first name: ");
		String name = sc.next();
		System.out.println("Enter student last name: ");
		name += " " + sc.next();

		do {
			System.out.println("Enter student ID: ");
			id = sc.next();
			for (Competition c : compArray) {
				if (c.compName.equalsIgnoreCase(compName)) {
					for (Student s : c.stdArray) {
						if (s.id.equals(id)) {
							// once we find the ID we stop &leave student loop, but we ask again for the ID
							loopId = true;
							System.out.println("Id is already added, try another one");
							break;
						} else
							// if we did not find the ID in stdArray these mean its unique ID,hence, leave
							// the loop
							loopId = false;
					}
					break;
				}

			}
		} while (loopId);

		System.out.println("Enter student major: ");
		String major = sc.next().toUpperCase();

//		System.out.println(currDate);
//		System.out.println(currDate.isAfter(compDate));
		if (currDate.isAfter(compDate)) {
			System.out.println("Enter student rank: ");
			rank = sc.next();
		} else
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
		Student s1 = new Student(-1, name, id, major, rank, teamNum, teamName);
		for (Competition c : compArray)
			if (c.compName.equalsIgnoreCase(compName)) {
				c.stdArray.add(s1);
				sheetName = c.sheet;
			}

		try {
			FileInputStream file = new FileInputStream("Competitions Participations.xlsx");

			Workbook wb = new XSSFWorkbook(file);

			Sheet sheet = wb.getSheet(sheetName);
			XSSFRow RowItr = null;

//			System.out.println(sheet.getSheetName());

			Map<String, Object[]> studentData = new TreeMap<String, Object[]>();

			if (compType.equals("STUDENT"))
				studentData.put("1", new Object[] { sheet.getLastRowNum() - 3, id, name, major, rank });
			else
				studentData.put("1",
						new Object[] { sheet.getLastRowNum() - 3, id, name, major, teamNum, teamName, rank });

			Set<String> keyid = studentData.keySet();

			int rowid = sheet.getLastRowNum() + 1;

			for (String key : keyid) {
				RowItr = (XSSFRow) sheet.createRow(rowid++);
				Object[] objectArr = studentData.get(key);
				int cellid = 0;

				for (Object obj : objectArr) {
					Cell cell = ((Row) RowItr).createCell(cellid++);
					cell.setCellValue(obj.toString());
				}
			}

			FileOutputStream out = new FileOutputStream(new File("Competitions Participations.xlsx"));

			wb.write(out);
			wb.close();
			out.close();

		} catch (Exception e) {
			System.out.println("Error: The competition does not exist.");
			System.out.println(e.getMessage());
		}

		System.out.println("Done\n");
	}

	public void edit(String compName, String id) throws IOException {
		String sheetName = "", compType = "", teamNum = "", teamName = "";
		int rowNum = 5, n = 0, sheetIndex = 0;
		boolean loopId = false;

		Scanner sc = new Scanner(System.in);

		for (Competition c : compArray) {
			n++;
			if (compName.equalsIgnoreCase(c.compName)) {
				sheetName = c.sheet;
				if (c.compTypeStd) // true == student based
					compType = "STUDENT";
				for (Student s : c.stdArray) {
					if (id.equals(s.id)) {

						System.out.println("Enter student first name: ");
						String name = sc.next();
						System.out.println("Enter student last name: ");
						name += " " + sc.next();

//						do {
						System.out.println("Enter student ID: ");
						id = sc.next();

//							for (Student ss : c.stdArray) {
//								if (ss.id.equals(id)) {
//									// once we find the ID we stop &leave student loop, but we ask again for the ID
//									loopId = true;
//									System.out.println("Id is already added, try another one");
//									break;
//								} else
//									// if we did not find the ID in stdArray this means that its a unique ID,hence, leave
//									// the loop
//									loopId = false;
//							}
//							break;
//						} while (loopId);

						System.out.println("Enter student major: ");
						String major = sc.next().toUpperCase();

						System.out.println("Enter student rank: ");
						String rank = sc.next();

						if (compType.equalsIgnoreCase("STUDENT")) {
							teamNum = "-";
							teamName = "-";
						} else {
							System.out.println("Enter team number: ");
							teamNum = sc.next();
							System.out.println("Enter team name");
							teamName = sc.next();
						}
						s.name = name;
						s.id = id;
						s.major = major;
						s.rank = rank;

//***********************************
						FileInputStream file = new FileInputStream("Competitions Participations.xlsx");
						Workbook wb = new XSSFWorkbook(file);
						Row RowItr = null;

						sheetIndex = wb.getSheetIndex(sheetName);
						wb.removeSheetAt(sheetIndex);
						Sheet sheet = wb.createSheet(sheetName);

						Map<String, Object[]> studentData = new TreeMap<String, Object[]>();

						studentData.put("1", new Object[] { "Competition Name", c.compName });
						studentData.put("2", new Object[] { "Competition URL", c.compURL });
						studentData.put("3", new Object[] { "Competition date", c.CompDate });
						studentData.put("4", new Object[] { "", "", "", "", "" }); // leave an empty row

						// Student based

						if (compType.equalsIgnoreCase("STUDENT"))
							studentData.put("5", new Object[] { "", "Student ID", "Student Name", "Major", "Rank" });
						else
							studentData.put("6", new Object[] { "", "Student ID", "Student Name", "Major", "team",
									"Team Name", "Rank" });

						int Z = 6, num = 1;
						for (Student x : c.stdArray) {
							if (c.compTypeStd == true)
								studentData.put((Z + ""), new Object[] { num + "", x.id, x.name, x.major, x.rank });
							else
								studentData.put(Z + 1 + "", new Object[] { num + "", x.id, x.name, x.major, x.teamNum,
										x.teamName, x.rank });
							Z++;
							num++;
						}
						// writing the data into the sheets...

						Set<String> keyid = studentData.keySet();

						int rowid = 0;

						for (String key : keyid) {
							RowItr = sheet.createRow(rowid++);
							Object[] objectArr = studentData.get(key);
							int cellid = 0;

							for (Object obj : objectArr) {
								Cell cell = (RowItr).createCell(cellid++);
								cell.setCellValue((String) obj);
							}
						}
//						file.close();
						FileOutputStream out = new FileOutputStream(new File("Competitions Participations.xlsx"));

						wb.write(out);
						wb.close();
						out.close();
					}
				}
				break;
			}
		}

//		222253860
		System.out.println("Done\n");
	}

	public void browse() {

	}

	public void openEmail() {

	}

//  2000-10-23
//	2-Oct-21

	String dateParser(String date) throws ParseException {
//		MM gives a number
//		MMM give Oct
//		MMMM gives October

		if (date.substring(1, 2).equals("-") || date.substring(2, 3).equals("-")) {

			SimpleDateFormat dt = new SimpleDateFormat("d-MMM-yy");
			Date newDate = dt.parse(date);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");

//			System.out.println(dt1.format(newDate));
			date = dt1.format(newDate);

		}
		return date;

	}

	public void notifaction() throws ParseException {
		String currentDate, compD;

		LocalDate currDate = LocalDate.now();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy MM dd");
		currentDate = currDate.format(fmt); // String
//		LocalDate compD = LocalDate.of(2000, 10, 1); // Create a date object

		for (Competition c : compArray) {
			LocalDate comp = LocalDate.parse(c.CompDate);
			compD = comp.format(fmt); // for printing

			if (currDate.isAfter(comp)) // true= due date is over
				if (!c.notification) {
					for (Student s : c.stdArray) {
//						System.out.println("VV " + s.rank);
						if (!s.rank.equals("-")) { // the News team update the ranks
							c.notification = true;
							break;
						}
					}
					// send a remainder to the news team
					if (c.notification == false) {
						System.out.println(
								"Update the ranks for the " + c.compName + " competition\nDue Date was: " + compD);
						c.notification = true;
					}

				} // notif is true
		}
	}

	public void viewStudent() {
		String id;
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter student ID: ");
		id = sc.next();

		for (Competition c : compArray)
			for (Student s : c.stdArray)
				if (id.equals(s.id)) {
					view(c, s);

				}
	}

	public void deleteStudent() throws IOException {
		String id, compName, sheetName, compType = "";
		int n = 0;
		ArrayList<Student> temp = new ArrayList<Student>();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter competition name: ");
		compName = sc.next();
		System.out.println("Enter student id: ");
		id = sc.next();

		for (Competition c : compArray) {
			if (compName.equalsIgnoreCase(c.compName)) {
				sheetName = c.sheet;
				if (c.compTypeStd) // true == student based
					compType = "STUDENT";
				for (Student s : c.stdArray) {
					if (!id.equals(s.id))
						temp.add(s);

				}
				c.stdArray = temp;

				FileInputStream file = new FileInputStream("Competitions Participations.xlsx");
				Workbook wb = new XSSFWorkbook(file);
				Row RowItr = null;

				wb.removeSheetAt(wb.getSheetIndex(sheetName));
				Sheet sheet = wb.createSheet(sheetName);

				Map<String, Object[]> studentData = new TreeMap<String, Object[]>();

				studentData.put("1", new Object[] { "Competition Name", c.compName });
				studentData.put("2", new Object[] { "Competition URL", c.compURL });
				studentData.put("3", new Object[] { "Competition date", c.CompDate });
				studentData.put("4", new Object[] { "", "", "", "", "" }); // leave an empty row

				// Student based

				if (compType.equalsIgnoreCase("STUDENT"))
					studentData.put("5", new Object[] { "", "Student ID", "Student Name", "Major", "Rank" });
				else
					studentData.put("6",
							new Object[] { "", "Student ID", "Student Name", "Major", "team", "Team Name", "Rank" });

				int Z = 6, num = 1;
				for (Student x : c.stdArray) {
					if (c.compTypeStd == true)
						studentData.put((Z + ""), new Object[] { num + "", x.id, x.name, x.major, x.rank });
					else
						studentData.put(Z + 1 + "",
								new Object[] { num + "", x.id, x.name, x.major, x.teamNum, x.teamName, x.rank });
					Z++;
					num++;
				}
				// writing the data into the sheets...

				Set<String> keyid = studentData.keySet();

				int rowid = 0;

				for (String key : keyid) {
					RowItr = sheet.createRow(rowid++);
					Object[] objectArr = studentData.get(key);
					int cellid = 0;

					for (Object obj : objectArr) {
						Cell cell = (RowItr).createCell(cellid++);
						cell.setCellValue((String) obj);
					}
				}
//				file.close();
				FileOutputStream out = new FileOutputStream(new File("Competitions Participations.xlsx"));

				wb.write(out);
				wb.close();
				out.close();
				break;

			}
		}
		System.out.println("Done");
	}

	public void delComp() throws IOException {
		String compName, sheetName = "";
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter competition name: ");
		compName = sc.next();

		for (Competition c : compArray)
			if (compName.equalsIgnoreCase(c.compName)) {
				sheetName = c.sheet;
				break;
			}
//		#########
		if (!sheetName.equals("")) {
			FileInputStream file = new FileInputStream("Competitions Participations.xlsx");
			Workbook wb = new XSSFWorkbook(file);

			wb.removeSheetAt(wb.getSheetIndex(sheetName));

			FileOutputStream out = new FileOutputStream(new File("Competitions Participations.xlsx"));

			wb.write(out);
			wb.close();
			out.close();

			System.out.println("Done");
		} else
			System.out.println("There is no competition with this name.");
	}

	public static void main(String[] args) throws IOException, InvalidFormatException, ParseException {
		String option = "";
		boolean x = true;
		Scanner sc = new Scanner(System.in);
		MySystem sys = new MySystem();

		sys.read();
		sys.notifaction();
		while (x) {
			sys.read();
//			CompetitionApplication.main(null);
			// just to know that which comps were added
			System.out.print("Competitions: ");
			for (Competition c : compArray)
				System.out.print(c.compName + " - ");

			System.out.print(
					"\nEnter your choice?\n\t1) Add a competition\n\t2) Add a student to a competition\n\t3) notification \n\t4) View competitions \n\t5) Show a particular student \n\t6) Edit \n\t7) End \n\t8) Remove Student \n\t9) Remove Competition \nEnter: ");
			option = sc.next();

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
				sys.view();
				break;
			}
			case "5": {// show student in multiple comps
				sys.viewStudent();
				break;
			}
			case "6": {
				System.out.println("Enter competition: ");
				String comp = sc.next(), id = "";
				System.out.println("Enter student ID to edit");
				id = sc.next();
				sys.edit(comp, id);
				sys.read();
				break;
			}
			case "7": {
				System.out.println("Terminate");
				x = false;
				break;
			}
			case "8": {
				sys.deleteStudent();
				sys.read();
				break;
			}
			case "9": {
				sys.delComp();
				break;
			}
			default: {
				System.out.println("Invalid option");
			}
			}
		}
	}
}