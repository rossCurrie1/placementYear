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
import javax.swing.JOptionPane;

public class deleteARecord 
{
    private static Scanner fileScannerForDelete; 
    public static void deleteARecord()
    {
        showAllRecords();
        
        String filePath = "src\\marks.csv"; // selects require csv file       
        Scanner bCodeInput = new Scanner(System.in); // allows imput form user 
        Scanner correctRecord = new Scanner(System.in); // exit while
        String userConfirmation;
        userConfirmation = "";
        boolean valueFound = false;
            
        
        // lets user enter what record they want to edit
        System.out.print("Select the record you'd like to delete: ");
        String userInput = bCodeInput.next(); // converts scanner to string 
        String removeTerm = userInput;
        
        try (Stream<String> lines = Files.lines(Paths.get("src\\marks.csv"))) 
        { 
            //Same code as option 3
            String[] result = lines
            .map(line -> line.split(","))
            .map(items -> items[0])
            .toArray(String[]::new);
            
            // returns the bcodes    
            for(int i = 0; i <= result.length-1; i++ )
            {
                while((i <= result.length) &&((result[i].equals(removeTerm)) 
                        && (!userConfirmation.equals("Yes"))))
                {
                    /* Informs user that record has been found and asks 
                    for confirmation */
                    System.out.println("I have found " + removeTerm);
                    System.out.print("Are you sure you want to delete? "
                            + "('Yes'/'No'): ");
                    String UserResponse = correctRecord.next();
                    
                    if(UserResponse.equals("Yes") || UserResponse.equals("yes")
                      || UserResponse.equals("Y") || UserResponse.equals("y"))
                    {
                        userConfirmation = "Yes";
                    }
                    else
                    {
                        System.out.println("As it wasn't the correct record, "
                                + "you will have to try again");   
                        deleteARecord();
                    }
                }
            }
            
            //If user confirms record is right, delete record is executed
            if(userConfirmation.equals("Yes"))
            {
                valueFound = true;
            } 
            else 
            {
                valueFound = false;   
            }   
        } 
        catch (IOException e) 
        {
        }
        
        // if user has entered yes, record is removed
        if ((valueFound = true) && userConfirmation.equals ("Yes"))
        {  
        removeRecord(filePath, removeTerm);
        
        System.out.println("Your record is now deleted.");
        System.out.println("The file is now as follows:");
        
        showAllRecords();
        }  
        else
        {
            System.out.println("We couldn't find the record you entered. "
                               + "Please try again");
            deleteARecord();
        }
    }
    
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
            Logger.getLogger(deleteARecord.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    //method removes reocrd from .csv
    public static void removeRecord(String filePath, String removeTerm)
    {
        String tempFile = "temp.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);
        String bCode = "";
        String mark = "";
        
        // similar code to edit record code, but z is used instead of x
        try
        {
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            //Delimters used to split file
            fileScannerForDelete = new Scanner(new File(filePath));
            fileScannerForDelete.useDelimiter("[,\n]");
            
            //does this while theres still data in file
            while(fileScannerForDelete.hasNext())
            {
                bCode = fileScannerForDelete.next();
                mark = fileScannerForDelete.next();
                
                //this finds the Bcode and deletes row in .csv file
                if(!bCode.equals(removeTerm))
                {
                    pw.print(bCode + "," + mark + "\n");
                }
            }
            
            //Closes appropriate files
            fileScannerForDelete.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(filePath);
            newFile.renameTo(dump);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"Error");
        }
    }
}
