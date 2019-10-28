// To start all the classed needed are imported 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class enterNewRecord 
{
    public static void enterNewRecord() throws IOException
    {
        String filePath = "src\\marks.csv";
        File myFile = new File(filePath);
        Scanner myScanner;
        String studentNumber;
        String studentMark;
        String studentRecord;
        String retryEntry;
        
        myScanner = new Scanner(System.in);
        
        System.out.println("Enter the Student number to be input:");
        studentNumber = myScanner.nextLine();
        //Validation incase the user has input a space
        studentNumber.replaceAll(" ","");
        
        //Read the data to be split
        try (Stream<String> lines = Files.lines(Paths.get("src\\marks.csv"))) 
        { 
            String[] bCodeArray = lines //creates an array to store B codes
            //.map allows the elements to be collected
            //B codes are stored in array to compare
            .map(line -> line.split(","))
            .map(items -> items[0])
            .toArray(String[]::new);
            
            //loops round array so each Bcode can be compared
            //i is incremented so same bcode isnt being compared constantly
            for(int i = 0; i <= bCodeArray.length-1; i++ )
            {
                /* compares the user input to data in file and if they match 
                any record in the file, they are told to try again and allowed 
                to enter a new record 
                
                if the user input it equal to poistion of i then record 
                denied */
                if(studentNumber.equals(bCodeArray[i]))
                {
                    System.out.println("Sorry that record already exists, "
                            + "please try again.");
                    enterNewRecord();
                }
                
                /* this is the format validation:
                   - if b code doesn't match B[0-9][0-9][0-9][0-9][0-9]
                   - if b code is shorter or longer than
                */
                if(((!studentNumber.matches("B00\\d{3}"))||(!studentNumber.matches("b00\\d{3}")))
                   &&(studentNumber.length() != 6))
                {
                    System.out.println("Sorry you have entered a B Code that "
                    + "doesn't match the requiremnts, please try again");
                    enterNewRecord();
                }
            }
        }
        catch (IOException e) 
        {        
        }
        
       System.out.println("Enter the Student mark to be input:");
        studentMark = myScanner.nextLine();
        //Validation incase the user has input a space
        studentMark.replaceAll("\\s+","");
        
        /*converting the studentMark value input from the user to a integer
        to ensure it is a proper value ranging from 0-100 - more below */
        int markAsInt = Integer.parseInt(studentMark);
        
        /*if statement ensures that the student id record follows the format
        of B00000 - i.e. the letter 'B'(or lowercase 'b') followed by 5 numbers)
        - Also ensures that the user has entered a proper mark in the range of
        0-100 */
        if (markAsInt <= 100 && markAsInt >= 0)
        {
            //following lines format the reccord to that of a CSV file
            studentRecord = studentNumber + "," + studentMark;
            studentRecord.replaceAll("\\s+","");
            String writeInto = (studentRecord.replaceAll("\\s+",""));  
        
            /* The following informs the user that they have met the proper reccord 
            format and that their record has been written to the file */
            System.out.println("The student number you entered " + studentNumber 
                    + " the mark of " + studentMark + " have been added to the "
                    + "marks file.");
        
            //The following takes care of appending the new record to the file
            try(FileWriter writer = new FileWriter("src\\marks.csv", true))
            {
                StringBuilder sb = new StringBuilder();
                sb.append("\n" + writeInto);
                writer.write(sb.toString());
            }
            //If an error occurs in the try statement, this block runs
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(enterNewRecord.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        
        /* if the first if statement fails, the user is informed about their 
        mistake and are presented with the chance to re enter their intended
        record*/
        else
        {
            System.out.println("The record you are trying to write is incorrect."
                    + "Would you like to try again? (Yes/No)");
            retryEntry = myScanner.nextLine();
            
            if (("yes".equals(retryEntry)) || ("Yes".equals(retryEntry))
                 || ("Y".equals(retryEntry)) || ("y".equals(retryEntry)))
            {
                enterNewRecord();
            }
            else
            {
                System.out.println("Ok!");
                System.out.println("");
            }
        }
    }
    
}
