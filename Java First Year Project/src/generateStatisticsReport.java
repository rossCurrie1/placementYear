// To start all the classed needed are imported 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.IntSummaryStatistics;
import java.util.Scanner;
import java.util.stream.Stream;

public class generateStatisticsReport 
{
    public static void generateStatisticsReport()
    {
        //The user is met with a secondary menu to chose the static report 
        Scanner secondaryMenu = new Scanner(System.in);
        System.out.println("");
        System.out.print("What would you like to find out?\n"
                + "Please type the following number which matches"
                + " the statistic you require.\n"
                + "1. Number of Stundents in the file.\n"
                + "2. The average mark in the file.\n"
                + "3. The Maximum and Minimum mark in the file.\n"
                + "Number (e.g. '1'/'One'): ");
        
        // reads users choice
        String reportSelect = secondaryMenu.next();
        
        //Runs method based on user choice
        switch (reportSelect) 
        {
            case "1":
                numOfRecords(); // Calls the numOfRecords() function
                break;
            case "One":
                numOfRecords(); // Calls the numOfRecords() function
                break;
            case "2":
                averageMark(); // Calls the averageMark() function
                break;
            case "Two":
                averageMark(); // Calls the averageMark() function
                break;
            case "3":
                minAndMax(); // Calls the minAndMax() function
                break;
            case "Three":
                minAndMax(); // Calls the minAndMax() function
                break;
            default:
                System.out.println("Option selected is incorrect, "
                                    + "please try again");
                generateStatisticsReport();
                break;
        }
    }
    
    //method to return average mark of file
    private static void averageMark()
    {
        //The file needs to be located and then a new file type is made
        String filePath = "src\\marks.csv";
        File myfile = new File(filePath);
        
        /* Block of code to be tested for errors while being executed. System 
        will try first block first & if they fail will execute second block of 
        code*/
        try
        {
            // Defining variables needed and set to values required
            Scanner readMarksFile = new Scanner(myfile);
            int sumOfMarks = 0; // used as an iteration
            int numOfRecords = 0; 
            int ModuleMarksAsInt;
            String[] ModuleMarks;
            String dataInFile;
              
            //loops round until no data left to read
            while(readMarksFile.hasNext())
            {
                dataInFile = readMarksFile.next();
                //slipts file at commas
                ModuleMarks = dataInFile.split(",");
                //converts them to an integer
                ModuleMarksAsInt = Integer.parseInt(ModuleMarks[1]);
                //added up to give a total
                sumOfMarks += ModuleMarksAsInt;
                //increments everytime a reord is read
                numOfRecords++;
            }                     
            
            System.out.println("You selected the avergae mark across the "
                    + "file. The Average is  " + (sumOfMarks / numOfRecords));
        }
        catch(FileNotFoundException | NumberFormatException e)
        {                        
        }
    }
    
    //method to return the number of students in the file
    private static void numOfRecords()
    {
        //collects correct file
        String filePath = "src\\marks.csv";
        File myfile = new File(filePath);
                       
        try 
        {
            Scanner readMarksFile = new Scanner(myfile);
            int lineInFile = 0;
            String dataInMarksFile;
                
            //Loops round file until nothing is left
            while(readMarksFile.hasNext())
            {
                dataInMarksFile = readMarksFile.next();
                lineInFile++;
            }
            
            System.out.println("You selected the the number of students in"
                        + "the file. The number is " + lineInFile);            
        }
        catch(FileNotFoundException | NumberFormatException e)
        {                      
        }
    }
    
    //method to return max and min marks in the file
    private static void minAndMax() 
    {
        try (Stream<String> stream = Files.lines(Paths.get("src\\marks.csv"))) 
        {
            IntSummaryStatistics statistics = stream
            //.map allows the elements to be collected
            .map(s -> s.split(",")[1])
            //converts to int
            .mapToInt(Integer::valueOf)
            .summaryStatistics();
            
            System.out.println("You selected the maximum mark and the minimum"
                    + " mark. The lowest mark is " + statistics.getMin() + 
                    " and the highest is " + statistics.getMax());
        }
        catch (IOException e) 
        {
        }
    }    
}