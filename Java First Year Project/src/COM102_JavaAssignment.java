/*
This is the COM102 Java Assigment 2 completed by:
- Ross Currie (B00752174)
- Brógán McShane (B00757848)
*/

// To start all the classed needed are imported 
import java.io.IOException;
import java.util.Scanner;

public class COM102_JavaAssignment
{
    public static void main(String[] args) throws IOException 
    {
        //Option menu is presented to the user
        System.out.println("Welcome to your own Student/Module mangement system.");
        System.out.println("");
        System.out.println("This System allows you to: \n"
                + "1. Display a full list of Student IDs and module marks. \n"
                + "2. Enter a new record into the marks file. \n"
                + "3. Edit a mark of an existing record in the file. \n"
                + "4. Delete a record that is in the file. \n"
                + "5. Generate Statistics such as... \n"
                + "   - number of students in a file. \n"
                + "   - produce the average mark in the file. \n"
                + "   - output the maximum mark and the minimum mark in the file. \n"
                + "NOTE: This system will save all edits to the marks.csv file.");
        
        //Line44: This takes the user input and allows them to start the program.
        System.out.println("");
        Scanner input = new Scanner(System.in); // used to allow & use input 
        System.out.print("Would you like to start (Yes/No): ");
        String startProgram = input.next(); // reads the input to use in while loop
        String userStart = startProgram; // aid readability
        String userContinue;
        
        // if startTerm is 'Yes' or 'yes' then program continue
        if((userStart.equals("Yes")) || (userStart.equals("yes")) || 
                (userStart.equals("Y")) || (userStart.equals("y"))) 
        {
            do //DO_WHILE loop used
            {
                System.out.println("");
                System.out.print("Please select a menu option by entering the "
                    + "option number (e.g. '1' / 'One'): ");
                // allows user to choose option
                Scanner menuOption = new Scanner(System.in);
                // stores user value in varible to use in switch statement
                String userOption = menuOption.next();
            
                switch (userOption) 
                { // switch is more efficent than if, else if
                    case "1": // each case is the users input
                        fullListOfStudents.fullListOfStudents(); // runs method Option1
                       break;
                    case "One":
                        fullListOfStudents.fullListOfStudents();
                        break;
                    case "one":
                        fullListOfStudents.fullListOfStudents();
                        break;
                    case "2":
                        enterNewRecord.enterNewRecord(); // runs method Option2
                        break;
                    case "Two":
                        enterNewRecord.enterNewRecord();
                        break;
                    case "two":
                        enterNewRecord.enterNewRecord();
                        break;
                    case "3":
                        editAMark.editAMark(); // runs method Option3
                        break; //exits switch statement and executes code
                    case "Three":
                        editAMark.editAMark();
                        break;
                    case "three":
                        editAMark.editAMark();
                        break;
                    case "4":
                        deleteARecord.deleteARecord(); // runs method Option4
                        break;
                    case "Four":
                        deleteARecord.deleteARecord();
                        break;
                    case "four":
                        deleteARecord.deleteARecord();
                        break;
                    case "5":
                        generateStatisticsReport.generateStatisticsReport(); // runs method Option5
                        break;
                    case "Five": //If user inputs number as word this is allowed
                        generateStatisticsReport.generateStatisticsReport();
                        break;
                    case "five": //If user inputs number as word this is allowed
                        generateStatisticsReport.generateStatisticsReport();
                        break;
                    default:
                        // Line 79: handles IP that is inaccurate
                        System.out.println("The input isn't recognized.");
                        break;
                }
            
                // Line 84 - 91: allows user to exit or continue while loop
                System.out.println("");
                System.out.print("Would you like to continue(Yes/No): ");
                Scanner continueOption = new Scanner(System.in);
                userContinue = continueOption.next();
            
                // if proceed isn't 'Yes' or 'yes' then end loop
                if(userContinue.equals("Yes") || userContinue.equals("yes")
                  || userContinue.equals("y") || userContinue.equals("Y"))
                {
                }
                else if(userContinue.equals("No") || userContinue.equals("no")
                  || userContinue.equals("n") || userContinue.equals("N"))
                {
                    System.out.println("You have chosen to leave the program"
                                        + ". Goodbye");
                    System.exit(0);           
                }
                else
                {
                    System.out.println("I didn't understand your input of '" + userContinue 
                                      + "', please press F6 to start again.");
                    System.exit(0); 
                }
                
            /* 
               Loop happens while:
               - userStart = "Yes"
               - userContine = "Yes"
            */    
            }while((userStart.equals("Yes")) || (userStart.equals("yes")) || (userStart.equals("Y"))|| (userStart.equals("y")));
        }
        // if startTerm isn't 'Yes' or 'yes' then end loop
        else if((userStart.equals("No")) || (userStart.equals("no")) 
                || (userStart.equals("N")) || (userStart.equals("n")))
        {
            System.out.println("You have chosen to leave the program, Goodbye");
            System.exit(0);
        }
        else
        {    
            System.out.println("I didn't understand your input of '" + userStart 
                    + "', please press F6 to start again.");
        }
    }
}