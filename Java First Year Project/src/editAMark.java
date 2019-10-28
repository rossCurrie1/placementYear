// To start all the classed needed are imported 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class editAMark
{
    private static Scanner fileScannerForEdit; // delcares the scanner used to search marks.csv
    public static void editAMark()
    {
        showAllRecords();
        
        String filePath = "src\\marks.csv"; // selects require csv file       
        Scanner markInput = new Scanner(System.in); // allows imput form user 
        Scanner bCodeInput = new Scanner(System.in); // allows imput form user
        Scanner correctRecord = new Scanner(System.in); // exit while
        String userConfirmation;
        userConfirmation = "";
        boolean valueFound = false;
        String newMark = null;
            
        
        // lets user enter what record they want to edit
        System.out.print("Select the record youd like to edit: ");
        String userInput = bCodeInput.next(); // converts scanner to string 
        String bCodeEdit = userInput;
        
        try (Stream<String> lines = Files.lines(Paths.get("src\\marks.csv"))) 
        { 
            String[] bCodeArray = lines //creates an array to store B codes
            //.map allows the elements to be collected
            //B codes are stored in array to compare
            .map(line -> line.split(","))
            .map(items -> items[0])
            .toArray(String[]::new);
            
            // returns the bcodes    
            for(int i = 0; i <= bCodeArray.length-1; i++ )
            {
                while((i <= bCodeArray.length) &&((bCodeArray[i].equals(bCodeEdit)) && (!userConfirmation.equals("Yes"))))
                {
                    //While loop scans array until B code required is found 
                    System.out.println("I have found " + bCodeEdit);
                    //Validation to check that Bcode found is correct
                    System.out.println("Is this the correct value? ('Yes'/'No'): ");
                    String UserResponse = correctRecord.next();
                    userConfirmation = UserResponse;
                }
            } 
            
            /* Line 255-262: if statement allows user to confirm if b code is correct and then
            sets 'confirmation' to true of false */
            if(userConfirmation.equals("Yes") || userConfirmation.equals("yes")
               || userConfirmation.equals("Y") || userConfirmation.equals("y"))
            {
                valueFound = true;
            }
            else 
            {
                valueFound = false;
                System.out.println("Please try again");
                editAMark();
            }   
        } 
        catch (IOException e) 
        {        
        }
        
        // Line 268 - 278: if Bcode is correct, user can edit mark only
        if ((valueFound = true) && (userConfirmation.equals("Yes") || 
                userConfirmation.equals("yes") || userConfirmation.equals("Y") 
                || userConfirmation.equals("y")))
        {
            System.out.print("Please insert a new mark: ");
            String editMark = markInput.next();
            newMark = editMark;
        }  
        else
        {
            System.out.println("We couldn't find the record you entered.");
            System.out.println("Please try again");
            editAMark();
        }
        
        /*converting the studentMark value input from the user to a integer	
           to ensure it is a proper value ranging from 0-100 - more below */	
        int UserNewMark = Integer.parseInt(newMark);	
       	
        if (UserNewMark <= 100 && UserNewMark >= 0)
        {	
            //Line 98: example of message passing from the editRecod method
            editRecord(filePath, bCodeEdit, newMark);	
           System.out.println("The file is now as follows:");	
           showAllRecords();  	
        }	
        else
        {	
           System.out.println("You have entered an invalid mark"	
                   + ". All marks must range from 0 - 100");	
       }
    }
    
    //Method is used to output all the records in the file.
    public static void showAllRecords()
    {
        String filePath = "src\\marks.csv";
        File myfile = new File(filePath);
        
        try 
        {
            Scanner readAllRecords = new Scanner(myfile);
            
            while(readAllRecords.hasNext())
            {
                String marksFileData = readAllRecords.next();
                System.out.println(marksFileData);
            }
            readAllRecords.close();
            System.out.println("");
        } 
        catch (FileNotFoundException ex) 
        {
            //If an error occurs in the try statement, this block runs
            Logger.getLogger(editAMark.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    //This inhertits the Bcode from user and uses it to chnage the mark
    //Line 135: example of message passing to the main method
    public static void editRecord(String filePath, String bCodeEdit, String newMark)
    {
        String tempFile = "temp.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);
        String bCode = ""; 
        String mark ="";
        
        try
        {
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            //Delimters used to split file
            fileScannerForEdit = new Scanner(new File(filePath));
            fileScannerForEdit.useDelimiter("[,\n]");
            
            
            //does this while theres still data in file
            while(fileScannerForEdit.hasNext())
            {
                bCode = fileScannerForEdit.next();
                mark = fileScannerForEdit.next();
                
                //this finds the Bcode and updates row in .csv file
                if(bCode.equals(bCodeEdit))
                {
                    pw.print("\n" + bCodeEdit + "," + newMark);
                }
                else
                {
                    pw.print("\n" + bCode + "," + mark);
                }
            }
            
            //Closes appropriate files
            fileScannerForEdit.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(filePath);
            newFile.renameTo(dump); 
        }
        catch(IOException e)
        {
            System.out.println("Error");
        }
    }
}
