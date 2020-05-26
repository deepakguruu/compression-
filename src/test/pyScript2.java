package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Deepak
 *
 */
public class pyScript2 {

	public static void main(String args[]) {

        float s ;

        try {
            
	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
        	String file="count.py";
            Process p = Runtime.getRuntime().exec("py C:\\work_files\\"+file+" E:/alice29.txt");
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
//            j.out.println("Here is the standard output of the command:\n");
            s = Float.parseFloat(stdInput.readLine()) ;
            System.out.println(s);
            s = Float.parseFloat(stdInput.readLine()) ;
            System.out.println(s);
            String ss ;
            // read any errors from the attempted command
//            System.out.println("Here is the standard error of the command (if any):\n");
            while ((ss = stdError.readLine()) != null) {
                System.out.println(ss);
            }
            
            
            
            
            
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
