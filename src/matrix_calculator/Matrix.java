//By Alex Everitt, 1019771, 15aje
package matrix_calculator;
import java.util.*;
import java.io.*;

public class Matrix {
	//data fields declaration
		private int numRows;
		private int numColumns;
		private double[][] array;
		
		
		//CONCTRUCTORS
		//Constructor generating empty matrix using input args
		public Matrix(int m, int n){
			this.numRows=m;
			this.numColumns=n;
			this.array= new double[m][n];	
			
		}
		//Constructor to enter in terminal
		public Matrix(){
			Scanner read = new Scanner(System.in);
			System.out.print("Please enter the number of rows: \n");
			this.numRows=read.nextInt();
			System.out.print("Please enter the number of columns: \n");
			this.numColumns=read.nextInt();
			this.array= new double[numRows][numColumns];
			System.out.print("Enter starting at the top row of the matrix \n");
			
			for(int i=0 ; i<this.numRows;i++){
				System.out.print("Enter a row of "+numColumns+" values, press enter after each value \n");
				for(int j=0;j<this.numColumns;j++){
					this.array[i][j]=read.nextDouble();		
				}//end inner for loop
			}//end outer for loop
		}
		//input file constructor
		public Matrix(String fileName) throws FileNotFoundException{
			final String seperator = ",";
			FileReader input = new FileReader(fileName);
	    	@SuppressWarnings("resource")
			Scanner in = new Scanner(input);
	    	int i = 0;
	    	
	    	String size = in.nextLine();
	    	StringTokenizer st = new StringTokenizer(size,seperator);
	    	
	    	//get row size and column size
	    	this.numRows = Integer.parseInt((st.nextToken()));
	    	this.numColumns = Integer.parseInt(st.nextToken());
	    	
	    	this.array = new double[numRows][numColumns];
	   
	    	String str;
	    	StringTokenizer stt;
	    	while(in.hasNextLine()){
	    		str = in.nextLine();
	    		stt = new StringTokenizer(str,seperator);
	    		for (int j=0 ; j<this.numColumns; j++){
	    			String strg = stt.nextToken();
	    			this.array[i][j] = Double.parseDouble(strg);
	    			
	    		}
	    		i++;
	    	}
		}
		//END CONSTRUCTORS
		
		//BEGIN GETTERS
		public int getN(){
			if(this.numColumns==0){
				return 0;
			}
			int x = this.numColumns;
			return x;
		}
		public int getM(){
			if(this.numRows==0){
				return 0;
			}
			int x = this.numRows;
			return x;
		}
		public double get(int i, int j){
			if(this.numRows==0||this.numColumns==0){
				return 0;
			}
				double x = this.array[i][j];
				return x;
		}
		//END GETTERS
		
		//OPERATION METHODS
		public Matrix add(Matrix m){
			if(this.numRows!=m.numRows||this.numColumns!=m.numColumns){
				return this;
			}
			for(int i=0 ; i<this.numRows;i++){
				for(int j=0;j<this.numColumns;j++){
					this.array[i][j]+=(m.array[i][j]);		
				}//end inner for loop
			}//end outer for loop
			return this;
		}
	
		public Matrix subtract(Matrix m){
			if(this.numRows!=m.numRows||this.numColumns!=m.numColumns){
				return this;
			}
			for(int i=0 ; i<this.numRows;i++){
				for(int j=0;j<this.numColumns;j++){
					this.array[i][j]=(this.array[i][j])-(m.array[i][j]);		
				}//end inner for loop
			}//end outer for loop	
			return this;
		}
		
		public Matrix multiply(Matrix m){
		    	if(this.numColumns != m.numRows){
		    		System.out.println("Invalid Multiplication");
		    		return this;
		    	}
		    	else{
		    		double[][] result = new double[this.numRows][m.numColumns];
		    		for(int i = 0; i < this.numRows; i++){
		    	        for(int j = 0; j < m.numColumns; j++){
		    	            for(int k = 0; k < this.numColumns; k++){
		    	                result[i][j] += this.array[i][k] * m.array[k][j];
		    	            }
		    	        }
		    	    }
		    		 this.array = result;
		    		 return this;
		    	}
		    }//this is checked
			
		public Matrix multiply(double x){
			for(int i=0 ; i<this.numRows;i++){
				for(int j=0;j<this.numColumns;j++){
					this.array[i][j]=(this.array[i][j])*x;		
				}//end inner for loop
			}//end outer for loop	
			return this;
		}//this is good and checked
		
		public Matrix divide (Matrix m){
			Matrix n = new Matrix(m.numColumns, m.numRows);
			n.numColumns=m.numColumns;
			n.numRows=m.numRows;
			n.array= m.array;
			n.inverse();
			this.multiply(n);
			return this;
		}//works
		
		public double determinant(){
			if (!isSquare() || this.numRows>3||this.numRows==1){
				System.out.println("This is an invalid operation on this matrix");
				return 0;
			}
			else if (numRows ==2 ){
				double x = this.array[0][0]*this.array[1][1];
				double y = this.array[0][1]*this.array[1][0];
				return (x-y);
			}
			else{
				double x = this.array[0][0]* ((this.array[1][1]*this.array[2][2])-(this.array[1][2]*this.array[2][1]));
				double y = this.array[0][1]* ((this.array[1][0]*this.array[2][2])-(this.array[1][2]*this.array[2][0]));
				double z = this.array[0][2]* ((this.array[1][0]*this.array[2][1])-(this.array[1][1]*this.array[2][0]));
				return (x-y+z);
			}
		}//this one is good and checked
		
		public Matrix inverse(){
			if (!this.isSquare() || this.numRows>3||this.numRows==1|| (this.determinant()==0)){
				System.out.println("Inverse is an invalid operation on this matrix");
				return this;
			}//if matrix isnt square, is larger than 3x3 or smaller than 2x2, is a 2x2 and determinant is 0 or is 
			else if(this.numRows==2){
				Matrix newMatrix = new Matrix(this.numRows,this.numColumns);
				newMatrix.array[0][0] = this.array[1][1];
				newMatrix.array[0][1] = -1*(this.array[0][1]);
				newMatrix.array[1][0] = -1*(this.array[1][0]);
				newMatrix.array[1][1] = this.array[0][0];
				double det = this.determinant();
				newMatrix.multiply(1/det);
				this.array=newMatrix.array;
				return this;
			}
			else{
				Matrix newMatrix = new Matrix(3,3);
				Matrix tempMatrix = new Matrix(2,2);
				
				//generate matrix of determinants		
				tempMatrix.array[0][0]=this.array[1][1];
				tempMatrix.array[0][1]=this.array[1][2];
				tempMatrix.array[1][0]=this.array[2][1];
				tempMatrix.array[1][1]=this.array[2][2];
				newMatrix.array[0][0]=tempMatrix.determinant();
				
				tempMatrix.array[0][0]=this.array[1][0];
				tempMatrix.array[0][1]=this.array[1][2];
				tempMatrix.array[1][0]=this.array[2][0];
				tempMatrix.array[1][1]=this.array[2][2];
				newMatrix.array[0][1]=tempMatrix.determinant();
				
				tempMatrix.array[0][0]=this.array[1][0];
				tempMatrix.array[0][1]=this.array[1][1];
				tempMatrix.array[1][0]=this.array[2][0];
				tempMatrix.array[1][1]=this.array[2][1];
				newMatrix.array[0][2]=tempMatrix.determinant();
				//row1
				
				tempMatrix.array[0][0]=this.array[0][1];
				tempMatrix.array[0][1]=this.array[0][2];
				tempMatrix.array[1][0]=this.array[2][1];
				tempMatrix.array[1][1]=this.array[2][2];
				newMatrix.array[1][0]=tempMatrix.determinant();
				
				tempMatrix.array[0][0]=this.array[0][0];
				tempMatrix.array[0][1]=this.array[0][2];
				tempMatrix.array[1][0]=this.array[2][0];
				tempMatrix.array[1][1]=this.array[2][2];
				newMatrix.array[1][1]=tempMatrix.determinant();
				//prob
				
				tempMatrix.array[0][0]=this.array[0][0];
				tempMatrix.array[0][1]=this.array[0][1];
				tempMatrix.array[1][0]=this.array[2][0];
				tempMatrix.array[1][1]=this.array[2][1];
				newMatrix.array[1][2]=tempMatrix.determinant();
				
				tempMatrix.array[0][0]=this.array[0][1];
				tempMatrix.array[0][1]=this.array[0][2];
				tempMatrix.array[1][0]=this.array[1][1];
				tempMatrix.array[1][1]=this.array[1][2];
				newMatrix.array[2][0]=tempMatrix.determinant();
				
				tempMatrix.array[0][0]=this.array[0][0];
				tempMatrix.array[0][1]=this.array[0][2];
				tempMatrix.array[1][0]=this.array[1][0];
				tempMatrix.array[1][1]=this.array[1][2];
				newMatrix.array[2][1]=tempMatrix.determinant();
				
				tempMatrix.array[0][0]=this.array[0][0];
				tempMatrix.array[0][1]=this.array[0][1];
				tempMatrix.array[1][0]=this.array[1][0];
				tempMatrix.array[1][1]=this.array[1][1];
				newMatrix.array[2][2]=tempMatrix.determinant();
			
				newMatrix.array[0][1]=-1*newMatrix.array[0][1];
				newMatrix.array[1][0]=-1*newMatrix.array[1][0];
				newMatrix.array[1][2]=-1*newMatrix.array[1][2];
				newMatrix.array[2][1]=-1*newMatrix.array[2][1];
				
				newMatrix.transpose();
				double det = 1/(this.determinant());
				newMatrix.multiply(det);
				this.array=newMatrix.array;
				return this;
			}
		}//finished and tested
		
		public boolean isSquare(){
			if(this.numColumns==this.numRows){
				return true;
			}
			else{
				return false;
			}
		}
		
		public Matrix transpose(){
			double[][] newArray = new double[this.numColumns][this.numRows];
			for(int i=0 ; i<this.numColumns;i++){
				for(int j=0;j<this.numRows;j++){
					newArray[i][j]= this.array[j][i];		
				}//end inner for loop
			}//end outer for loop
			this.array = newArray;		
			return this;
		}
		//END OPERATIONS
		//START OTHER
		public String toString(){
			String matrix = "";
			String dimensions = Double.toString(this.numRows)+","+Double.toString(this.numColumns);
			//matrix[i]=Double.toString(this.array[0][0]);
			
			for(int j=0;j<this.numRows;j++){
				for(int i=0;i<this.numColumns;i++){
					matrix+= this.array[j][i];
					if(i < numColumns - 1)
						matrix +=",";
					matrix+=" ";
				}
				matrix+= "\n";
			}
			
			String matrixString = dimensions +"\n"+matrix;
			return matrixString;
		}//checked
		
		public void print(String filename) throws IOException{
			String matrix = this.toString();
			FileWriter filewriter = new FileWriter(filename+".csv");
			BufferedWriter buffwriter = new BufferedWriter(filewriter);
			buffwriter.write(matrix);
			buffwriter.flush();
			buffwriter.close();
		}
		
		public static Matrix identity(int size){
			double [][] newArray = new double[size][size];
			Matrix newMatrix = new Matrix();
			newMatrix.numColumns=size;
			newMatrix.numColumns=size;
			for(int i =0;i<size;i++){
				for(int j=0;j<size;j++){
				if(i==j){
					newArray[i][j]=1;
				}
				else{
					newArray[i][j]=0;
				}
					
				}	
			}
			newMatrix.array = newArray;
			return newMatrix;
		}
		//END OTHER
			
}//END OF MATRIX CLASS
