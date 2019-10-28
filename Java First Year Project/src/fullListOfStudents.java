// To start all the classed needed are imported 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class fullListOfStudents // defines method 1
{
    public static void fullListOfStudents()
    {
        String filePath = "src\\marks.csv"; // locates the file
        File file = new File(filePath); // creates a var that hold the file path
      
        try 
        {
            // Line 104: used ro read the content of the marks.csv file
            Scanner inputStream = new Scanner(file);
          
            System.out.println("The file is as follows: ");
            // Line 118 - 123: loops round file reading data until no data left
            while(inputStream.hasNext()) 
            {
                String marksFileData = inputStream.next();
                System.out.println(marksFileData);
            }
            inputStream.close();
            System.out.println(""); 
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(fullListOfStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
