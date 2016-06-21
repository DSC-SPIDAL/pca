package stock;

import Jama.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Pca {
	
	public double[][] importFile() throws IOException{
		Scanner sc = new Scanner(new File("20090716_20100716.csv"));
		double dataArr[][] = new double[6161][252];
		for(int i=0; i<6161;i++){
			String line =  sc.nextLine();
			String[] vals = line.split("\\s+");
			for(int j=0; j< 252; j++){
				dataArr[i][j]= Float.parseFloat(vals[j+2]);
			}
		}
		
		for(int i= 0; i<6161; i++){
			double sum=0; 
			int j=0; 
			if(j<252){
			sum+= dataArr[i][j];
			double mean = sum/6161;
			for(int x=0; x<6161; x++){
				dataArr[x][j]-=mean;
			}
			j++;
			}
		}
		return dataArr;
	}
	
	public Matrix SVD() throws IOException{
		Pca pca = new Pca();
		double[][] arr= pca.importFile();
		Matrix mat= new Matrix(arr);
		SingularValueDecomposition svd= mat.svd();
		
		Matrix U = svd.getU();
		Matrix V= svd.getV();
		Matrix S = svd.getS();
		
		
		Matrix newU = U.getMatrix(0, 6160, 0, 2);
		Matrix newV = V.getMatrix(0, 2, 0, 2);
		Matrix newS = S.getMatrix(0, 2, 0, 2);
		
		
		Matrix X = newU.times(newS).times(newV.transpose());
		//Matrix Y = X.arrayTimes(V.transpose());
		
		System.out.println(X.getRowDimension());
		System.out.println(X.getColumnDimension());
		
		return X;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		
		Pca pca= new Pca();
		Matrix X= pca.SVD();
		double[][] arr= X.getArray();
		FileWriter fw= new FileWriter("/home/helloworld/Documents/PCA.csv");
		
		for(int i=0; i<6161;i++){
			for(int j =0; j<3; j++){
				fw.write(arr[i][j]+",");
			}
			fw.write("\n");
		}
	}

}
