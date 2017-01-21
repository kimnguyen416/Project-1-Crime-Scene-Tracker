//Name: Kim Nguyen
//80813737
//Section#:9504
//TA: Val
//Project Number: 1
//Brief Description of File Contents:SuspicionCalculatorforCampusCrime
import java.util.Scanner; //imports Scanner from java.util package
public class SuspicionCalculator {
	public static void main(String[] args) {
	//Creates Scanner object
	Scanner input = new Scanner(System.in);
	
	//Declaration of variables
	int crimeTime;
	int crimeLatitude;
	int crimeLongitude;
	int ufid;
	int studentTime;
	int studentLatitude;
	int studentLongitude;
	int studentHeartRate;
	String typeOfStudent;
	String heartRateActivity;
	
	
	//Suspicion Calculator introduction
	System.out.println("Hello and welcome to the UF suspect " +
	"suspcion calculator.");
	
	//Printed statements followed by User inputs.
	//Enter military time, Ex: 12:00 am as 0000 or 1:00 am as 0100
	System.out.print("Please enter the time of the crime: ");
	crimeTime = input.nextInt();
	System.out.print("Please enter the latitude of the crime: ");
	crimeLatitude = input.nextInt();
	System.out.print("Please enter the longitude of the crime: ");
	crimeLongitude = input.nextInt();
	System.out.print("Please enter the student's UFID: ");
	ufid = input.nextInt();
	System.out.print("Please enter their last timestamp: ");
	studentTime = input.nextInt();
	System.out.print("Please enter the latitude of the student: ");
	studentLatitude = input.nextInt();
	System.out.print("Please enter the longitude of the student: ");
	studentLongitude = input.nextInt();
	System.out.print("Please enter their heart rate: ");
	studentHeartRate = input.nextInt();
	
	//Conditions for whether student is unsafe/safe
	//Student UFID information
	if (ufid % 100 <= 49)
		typeOfStudent = "safe";
	else 
		typeOfStudent = "unsafe";
	
	
	
	//Student hearRate activity 
	if (studentHeartRate >= 100)
		heartRateActivity = "moving";
	else 
		heartRateActivity = "sedentary";
	
	//Distance formula 
		double latitude = Math.pow((studentLatitude - crimeLatitude), 2);
		double longitude = Math.pow((studentLongitude - crimeLongitude), 2);
		double totalDistance = Math.sqrt(latitude + longitude);
		String finalDistance = String.format("%.1f", totalDistance);
	
	/*If typeOfStudent = "safe" list, suspicion levels: highly likely, likely,unsure,unlikely,
	 * highly likely
	 */
		String levelOfSuspicion = null;
		
	if (typeOfStudent == "safe"){
		if ((crimeTime - 30 <= studentTime || crimeTime + 30 <= studentTime) &&
		(crimeLatitude == studentLatitude) && (crimeLongitude == studentLongitude) 
		&& studentHeartRate >= 100)
			levelOfSuspicion = "Highly Likely";
		else if ((crimeTime - 60 <= studentTime || crimeTime + 60 <= studentTime)
		&& (totalDistance <= 1) && studentHeartRate >= 100)
			levelOfSuspicion = "Likely";
		else if ((crimeTime - 90 <= studentTime || crimeTime + 90 <= studentTime) && 
		(totalDistance > 3) && studentHeartRate >= 100)
			levelOfSuspicion = "Unsure";
		else if ((crimeTime - 120 <= studentTime || crimeTime + 120 <= studentTime) &&
		(totalDistance > 4) && studentHeartRate < 100)
			levelOfSuspicion = "Unlikely";
		else
			levelOfSuspicion = "Highly Unlikely";
	}	 
	/*If typeOfStudent = "unsafe" list, suspicion levels: highly likely, likely, unsure, 
	 * unlikely, highly likely
	 */
	if (typeOfStudent == "unsafe"){
		if ((crimeTime - 60 <= studentTime || crimeTime + 60 <= studentTime) &&
		(totalDistance <= 1))
			levelOfSuspicion = "Highly Likely";
		else if ((crimeTime - 90 <= studentTime || crimeTime + 90 <= studentTime)
		&& (totalDistance <= 2) && studentHeartRate >= 100)
			levelOfSuspicion = "Likely";
		else if ((crimeTime - 120 <= studentTime || crimeTime + 120 <= studentTime) && 
		(totalDistance > 2))
			levelOfSuspicion = "Unsure";
		else if ((crimeTime - 150 <= studentTime || crimeTime + 150 <= studentTime) &&
		(totalDistance > 3) && studentHeartRate < 100)
			levelOfSuspicion = "Unlikely";
		else
			levelOfSuspicion = "Highly Unlikely";
	}
	
	/*Final output that determines whether a student is on the safe
	 * or unsafe list, and whether they are the criminal or not.
	 */
	System.out.println("Student " + ufid + " who is on the " + typeOfStudent + " list, and was "
	+ finalDistance + " block units away,at location (" + studentLatitude + "," + 
	studentLongitude + ") at " + studentTime + " and determined to be " + heartRateActivity + 
	" is " + levelOfSuspicion + " to be the Criminal.");
		
	}
}
