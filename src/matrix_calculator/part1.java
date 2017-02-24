//By Alex Everitt, 1019771, 15aje
package matrix_calculator;
import java.util.*;
import java.io.*;


public class part1 {

	public static void main(String[] args) throws IOException{
		
		System.out.printf("Welcome to matrix calculator by Alex Everitt\n\nMatrix operations include:\nAdd(1), Subtract(2), Multiply-Matrices(3), Multiply by Scalar(4), Divide(5), Inverse(6), Determinant(7), Square-Checker(8) and Transpose(9)"+"\nWhat operation would you like to test(enter as shown above)?\n");
		Scanner in = new Scanner(System.in);
		int operation = in.nextInt();
		if(operation==1||operation==2||operation==3||operation==5){
			System.out.println("Please enter values for the matrix that the operation is being done on");
			Matrix M1 = new Matrix();
			System.out.println("Please enter values for the argument matrix");
			Matrix M2 = new Matrix();
			if(operation==1){
				M1.add(M2);
				String newMatrix= M1.toString();
				System.out.println("After the operation, the resultant matrix is(First row represents #rows then #columns):\n"+newMatrix);
			}
			else if(operation==2){
				M1.subtract(M2);
				String newMatrix= M1.toString();
				System.out.println("After the operation, the resultant matrix is(First row represents #rows then #columns):\n"+newMatrix);
			}
			else if(operation==3){
				M1.multiply(M2);
				String newMatrix= M1.toString();
				System.out.println("After the operation, the resultant matrix is(First row represents #rows then #columns):\n"+newMatrix);
			}
			else if(operation==5){
				M1.divide(M2);
				String newMatrix= M1.toString();
				System.out.println("After the operation, the resultant matrix is(First row represents #rows then #columns):\n"+newMatrix);
			}
		}
		else if(operation==4||operation==6||operation==7||operation==8||operation==9){
			System.out.println("Please enter values for the matrix that the operation is being done on");
			Matrix M1 = new Matrix();
			
			if(operation==4){
				System.out.println("Please enter the value to multiply by:");
				double x = in.nextDouble();
				M1.multiply(x);
				String newMatrix= M1.toString();
				System.out.println("After the operation, the resultant matrix is(First row represents #rows then #columns):\n"+newMatrix);
			}
			else if(operation==6){
				M1.inverse();
				String newMatrix= M1.toString();
				System.out.println("After the operation, the resultant matrix is(First row represents #rows then #columns):\n"+newMatrix);
			}
			else if(operation==7){
				M1.determinant();
				String newMatrix= M1.toString();
				System.out.println("After the operation, the resultant matrix is(First row represents #rows then #columns):\n"+newMatrix);
			}
			else if(operation==8){
				System.out.println(M1.isSquare());
			}
			else if(operation==9){
				M1.transpose();
				String newMatrix= M1.toString();
				System.out.println("After the operation, the resultant matrix is(First row represents #rows then #columns):\n"+newMatrix);
			}
		}
	}	
}


	
	
