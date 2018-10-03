/**
 * This class will be responsible to read data from file
 * 
 * @author  Swati Gupta
 * @version 2018.09.27
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Reader {
ArrayList<String> allReadData = new ArrayList<>();
String data;
    public void readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("test.csv"));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
        	data=scanner.next();
            System.out.print(data+"|");
            
        }
        scanner.close();
    }

}