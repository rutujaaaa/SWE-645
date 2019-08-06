


/**
 * Mean and Standard Deviation computed by the
  StudentService using the data entered in the Raffle
 */
package supportingClass;
import java.util.ArrayList;

import managedBean.Student;

public class StudentService 
{  
public static ArrayList<Student> arraylist = new ArrayList<Student>();

public static void add(Student student)
{
arraylist.add(student);
}

	public float calculatingMean(String rafflestring)
	{
		float mean = 0;
		String[] array = rafflestring.split(",");
		int i = 0;
				do{
					mean = mean + Integer.parseInt(array[i].trim());
					i++;
			}while(i<array.length);
		return (mean/10);
	}
	public float calculatingStandardDeviation(String rafflestring)
	{
		float standarddeviation = 0;
		String[] array = rafflestring.split(",");
		float mean = calculatingMean(rafflestring);
		float[] difference = new float[10];
		int i=0;
		while(i<array.length)
		{
			difference[i] = mean - Integer.parseInt(array[i].trim());
			i++;
		}
		for(i=0; i<difference.length; i++){
			 difference[i]*=difference[i];
			 standarddeviation += difference[i];
		}
		return standarddeviation/10;
	}
}
