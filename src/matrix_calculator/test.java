package matrix_calculator;
import java.io.*;
import java.util.*;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try
         {
                
                 //csv file containing data
                 String strFile = "example.csv";
                
                 //create BufferedReader to read csv file
                 BufferedReader br = new BufferedReader( new FileReader(strFile));
                 String strLine = "";
                 StringTokenizer st = null;
                 int lineNumber = 0, tokenNumber = 0, i=0, j=0;
                 int[] dimensions = new int[2];
                
                 //read comma separated file line by line
                 while( (strLine = br.readLine()) != null && lineNumber<1)//while there is still data lines to read
                 {
                         
                         //break comma separated line using ","
                         st = new StringTokenizer(strLine, ",");
                         while(st.hasMoreTokens())//while the line still has data
                         {
                                 //display csv values
                                tokenNumber++;
                                System.out.println(tokenNumber);
                                 //st.nextToken();
                                 dimensions[i++]=Integer.parseInt(st.nextToken());
                        	 
                         }
                         //reset token number
                         //tokenNumber = 0; 
                        // lineNumber++;
                 }
                 double[][] fileArray = new double[(dimensions[0])][(dimensions[1])];
                 
               i=0;
                 while( (strLine = br.readLine()) != null )//while there is still data lines to read
                 {
                         j=0;
                         //break comma separated line using ","
                         //st = new StringTokenizer(strLine, ",");
                         while(st.hasMoreTokens())//while the line still has data
                         {
                                fileArray[i][j]=Double.parseDouble(st.nextToken());
                        	 
                         }
                         //reset token number
                         //tokenNumber = 0; 
                        // lineNumber++;
                         i++;
                 }	 
                 System.out.println(Arrays.deepToString(fileArray));//tests array generation
                 
                
         }
         catch(Exception e)
         {
                 System.out.println("Exception while reading csv file: " + e);                  
         }
	}

}
