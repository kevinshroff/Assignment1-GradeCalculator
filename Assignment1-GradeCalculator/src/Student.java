
public class Student 
{
	//Init variables
	int studentNumber;
	boolean status;
	int assignment1Mark;
    int assignment2Mark;
	int finalExamMark;
	int A1weight;
	int A2weight;
	int FEweight;
	
	public Student(int studentNumber)		//Constructor
	{
		setStudentNumber(studentNumber);
		if (studentNumber >= 10000000 && studentNumber <= 99999999)		//Checks validity of given studentNumber
		{
			status = true;
		}
		
		else
		{
			status = false;
		}
	} 
	
	public boolean updateMark(String markType, int mark, int weight)
	{
		boolean requirementsPassed = true;
		
		if (mark < 0 || mark > 100)
		{
			requirementsPassed = false;
		}
		
		if (weight < 20 || weight > 60)
		{
			requirementsPassed = false;
		}
		
		//All verification passed - update mark
		if (markType.equals("A1") && requirementsPassed == true)
		{
			A1weight = weight;
			assignment1Mark = mark;
		}
		
		if (markType.equals("A2") && requirementsPassed == true)
		{
			A2weight = weight;
			assignment2Mark = mark;
		}
		
		if (markType.equals("FE") && requirementsPassed == true)
		{
			FEweight = weight;
			finalExamMark = mark;
		}
		
		return requirementsPassed;
	}
	
	
	public int getMark(String markType)
	{
		int returnMark = -1;
		
		if (markType.equals("A1"))
		{
			returnMark = assignment1Mark;
		}
		
		if (markType.equals("A2"))
		{
			returnMark = assignment2Mark;
		}
		
		if (markType.equals("FE"))
		{
			returnMark = finalExamMark;
		}
		
		return returnMark;
	}
	
	public int getWeightedMark(String markType)
	{
		int returnMark = -1;
		
		if (markType.equals("A1"))
		{
			returnMark = assignment1Mark * A1weight/100;
		}
		
		if (markType.equals("A2"))
		{
			returnMark = assignment2Mark * A2weight/100;
		}
		
		if (markType.equals("FE"))
		{
			returnMark = finalExamMark * FEweight/100;
		}
		
		return returnMark;
	}
	
	public int getStudentNumber()
	{
		return studentNumber;
	}
	
	public boolean getStatus()
	{
		return status;
	}
	
	public void setStudentNumber(int studentNumber)
	{
		if (studentNumber >= 10000000 && studentNumber <= 99999999)		//Checks validity of given studentNumber
		{
			status = true;
			this.studentNumber = studentNumber;
		}
		
		else
		{
			status = false;
		}
	}
	
	public int getWeight(String markType)
	{
		int getWeightVar = 0;
		
		if (markType.equals("A1"))
		{
			getWeightVar = A1weight;
		}
		
		if (markType.equals("A2"))
		{
			getWeightVar = A2weight;
		}
		
		if (markType.equals("FE"))
		{
			getWeightVar = FEweight;
		}
		
		return getWeightVar;
	}
}

