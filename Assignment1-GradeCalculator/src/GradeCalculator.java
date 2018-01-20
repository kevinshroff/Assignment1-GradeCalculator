
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GradeCalculator
{
	// Init variables
	int classSize;
	int assignment1Weight;
	int assignment2Weight;
	int finalExamWeight;
	int userChoice;
	int tempInputVerify;
	String stringTempInputVerify;
	boolean inputVerified;
	ArrayList<Student> classList = new ArrayList<Student>();
	ArrayList<Integer> classListStudentNumbers = new ArrayList<Integer>();
	Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		GradeCalculator mainInstance = new GradeCalculator();
		mainInstance.runUI();
		
	}
	
	public void runUI()
	{
		System.out.println("Grade Calculator (Version 0.1). Author: Kevin Shroff");
		System.out.println(" ");
		System.out.println("1 - Simulate Course Marks");
		System.out.println("2 - View/Update Student Marks");
		System.out.println("3 - Run Mark Statistics");
		System.out.println(" ");
		System.out.println("Select Option [1, 2 or 3] (9 to Quit)");
		
		// Capturing user's input from System.in
		int userChoice = userInput.nextInt(); // Captures input as an int
		
		if (userChoice == 1) // Option 1 - Simulate Course Marks
		{
			runSimulateCourseMarks();
		}
		
		if (userChoice == 2) // Option 2 - View/Update Student Marks
		{
			if (classList.isEmpty() == false)
			{
				runViewUpdateStudentMarks();
			}
			
			if (classList.isEmpty() == true)
			{
				System.out.println("<< Error: empty class list>> Run option 1 first.");
				System.out.println(" ");
				runUI();
			}
		}
		
	}
	
	public void runSimulateCourseMarks() // Option 1 - Simulate Course Marks
	{
		
		System.out.println("Enter course enrollment size: ");
		
		classSize = userInput.nextInt();
		//Student[] classList = new Student[classSize];
		
		do
		{
			inputVerified = false;
			System.out.println("Enter weight assignment 1 (20-30): "); // Assignment 1 weight
			try
			{
				tempInputVerify = userInput.nextInt();
				if (tempInputVerify >= 20 && tempInputVerify <= 30)
				{
					assignment1Weight = tempInputVerify;
					inputVerified = true;
				}
				else
				{
					System.out.println("Error: input is not within specified range (20-30)");
				}
			}
			catch (InputMismatchException exception)
			{
				System.out.println("Not a number. Please input a number.");
				tempInputVerify = userInput.nextInt();
			}
			
		}
		while (!(inputVerified));
		
		do
		{
			inputVerified = false;
			System.out.println("Enter weight assignment 2 (20-30): "); // Assignment 2 weight
			try
			{
				tempInputVerify = userInput.nextInt();
				if (tempInputVerify >= 20 && tempInputVerify <= 30)
				{
					assignment2Weight = tempInputVerify;
					inputVerified = true;
				}
				else
				{
					System.out.println("Error: input is not within specified range (20-30)");
				}
			}
			catch (InputMismatchException exception)
			{
				System.out.println("Not a number. Please input a number.");
				tempInputVerify = userInput.nextInt();
			}
			
		}
		while (!(inputVerified));
		
		do
		{
			inputVerified = false;
			System.out.println("Enter weight Final Exam (40-60): "); // Final Exam weight
			try
			{
				tempInputVerify = userInput.nextInt();
				if (tempInputVerify >= 40 && tempInputVerify <= 60)
				{
					finalExamWeight = tempInputVerify;
					inputVerified = true;
				}
				else
				{
					System.out.println("Error: input is not within specified range (40-60)");
				}
			}
			catch (InputMismatchException exception)
			{
				System.out.println("Not a number. Please input a number.");
				tempInputVerify = userInput.nextInt();
			}
			
		}
		while (!(inputVerified));
		
		if (finalExamWeight + assignment1Weight + assignment2Weight != 100)
		{
			System.out.println("<<< Error: weights do not add up to 100% >>>");
			runUI();
		}
		
		if (finalExamWeight + assignment1Weight + assignment2Weight == 100) // If all criteria passed proceed with simulation													
		{
			// Generate N student objects
			int count = 0;
			while (count != classSize)
			{
				//classList[count] = generateStudentMarks(ThreadLocalRandom.current().nextInt(10000000, 99999999 + 1));
				classList.add(generateStudentMarks(ThreadLocalRandom.current().nextInt(10000000, 99999999 + 1)));
				classListStudentNumbers.add(classList.get(count).getStudentNumber());
				count = count + 1;
			}
			
		}
		
		runUI();
	}
	
	public void runViewUpdateStudentMarks() // Option 2 - View/Update Student Marks
	{
		int verifiedStudentNumber = 0;
		
		do
		{
			inputVerified = false;
			System.out.println("Enter Student Number: ");
			try
			{
				tempInputVerify = userInput.nextInt();
				if (tempInputVerify >= 10000000 && tempInputVerify <= 99999999)
				{
					//
					if (!classListStudentNumbers.contains(tempInputVerify))
					{
						System.out.println("[" + tempInputVerify + "] is invalid");
						runUI();
					}
					
					if (classListStudentNumbers.contains(tempInputVerify))
					{
						verifiedStudentNumber = tempInputVerify;
						
						inputVerified = true;
					}
				}
				else
				{
					System.out.println("[" + tempInputVerify + "] is invalid");
					runUI();
				}
			}
			catch (InputMismatchException exception)
			{
				System.out.println("Not a number. Please input a number.");
				tempInputVerify = userInput.nextInt();
			}
			
		}
		while (!(inputVerified));
		
		System.out.println("View or Update? (V/U): ");
		
		userInput.nextLine();		//Consumes empty character, fixes nextLine glitch which would otherwise occur when reusing Scanner for string
		stringTempInputVerify = userInput.nextLine();
		
		if (stringTempInputVerify.equals("V"))
		{
			// Display course marks for selected student (verifiedStudentNumber)
			printSingleStudentReport(verifiedStudentNumber);
		}
		
		if (stringTempInputVerify.equals("U"))
		{
			// Display Update Mark menu
			updateMarkMenu(verifiedStudentNumber);
		}
	}
	
	public Student generateStudentMarks(int studentNumber)
	{
		Student genStudent = new Student(studentNumber); // new object of class Student
		
		genStudent.updateMark("A1", ThreadLocalRandom.current().nextInt(0, 100 + 1), assignment1Weight);
		genStudent.updateMark("A2", ThreadLocalRandom.current().nextInt(0, 100 + 1), assignment2Weight);
		genStudent.updateMark("FE", ThreadLocalRandom.current().nextInt(0, 100 + 1), finalExamWeight);
		
		System.out.println("simulated student code: " + genStudent.getStudentNumber());
		
		return genStudent;
	}
	
	public int computeStudentGrade(Student studentObject)
	{
		// Computes the student's final grade
		return studentObject.getMark("A1") + studentObject.getMark("A2") + studentObject.getMark("FE");
	}
	
	public void printClassReport(ArrayList<Student> ClassArrayList)
	{
		// Displays a report for the entire class
		
	}
	
	public void printSingleStudentReport(int studentNumber)
	{
		// Displays a report for a single specified student
				
		int A1 = classList.get(classListStudentNumbers.indexOf(studentNumber)).getMark("A1");
		int A2 = classList.get(classListStudentNumbers.indexOf(studentNumber)).getMark("A2");
		int FE = classList.get(classListStudentNumbers.indexOf(studentNumber)).getMark("FE");
		
		System.out.println("Student Number     Assignment 1 Mark    Assignment 2 Mark    Final Exam Mark");
		System.out.println("**************      **************       **************       **************");
		System.out.println("   " + studentNumber + "               " + A1 + "                    " + A2 + "                   " + FE);
		System.out.println(" ");
		System.out.println("Enter (Y) to return to selection menu: ");
		
		stringTempInputVerify = userInput.nextLine();
		if (stringTempInputVerify.equals("Y"))
		{
			runUI();			
		}
	}
	
	public void updateMarkMenu(int studentNumber)
	{
		System.out.println("Mark Type? (A1, A2, or FE): ");
		stringTempInputVerify = userInput.nextLine();
		if (stringTempInputVerify.equals("A1") || stringTempInputVerify.equals("A2") || stringTempInputVerify.equals("FE"))
		{
			String verifiedMarkType = stringTempInputVerify;
			
			//Retrieves mark of specified student of selected mark type
			System.out.println(verifiedMarkType + " is " + classList.get(classListStudentNumbers.indexOf(studentNumber)).getMark(verifiedMarkType));
			System.out.println(" ");
			System.out.println("Updated Mark (0 - 100): ");
			tempInputVerify = userInput.nextInt();
			
			if (tempInputVerify > 100 || tempInputVerify < 0)
			{
				System.out.println(tempInputVerify + " is an invalid mark");
				runUI();
			}
			
			if (tempInputVerify <= 100 && tempInputVerify >= 0)
			{
				int verifiedMark = tempInputVerify;
				// Updates specified student with given mark for given mark type.
				classList.get(classListStudentNumbers.indexOf(studentNumber)).updateMark(verifiedMarkType, verifiedMark, classList.get(classListStudentNumbers.indexOf(studentNumber)).getWeight(verifiedMarkType));
				
				System.out.println(verifiedMarkType + " mark for Student Number [" + studentNumber + "] is now updated to " + verifiedMark);
				
				System.out.println(" ");
				System.out.println("Enter (Y) to return to selection menu: ");
				
				userInput.nextLine();
				stringTempInputVerify = userInput.nextLine();
				if (stringTempInputVerify.equals("Y"))
				{
					runUI();			
				}
				
			}
		}
	}
	
	
}
