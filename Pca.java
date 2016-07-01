package stock;

import Jama.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;


public class Pca {
	
	public double[][] importFile(String file) throws IOException{
		double dataArr[][] = new double[6161][6161];
		
	
		Scanner sc = new Scanner(new File(file));
		//double dataArr[][] = new double[6161][6161];
		String col= sc.nextLine();
		for(int i=0; i<6161;i++){
			String line =  sc.nextLine();
			String[] vals = line.split(",");
			
			for(int j=0; j< 6161; j++){
				//System.out.println("i is "+i+" , j is "+j);
				dataArr[i][j]= Double.parseDouble(vals[j]);
			}
		}
		
	/*	for(int i= 0; i<6161; i++){
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
		}*/
		return dataArr;
	}
	
	public Matrix SVD() throws IOException{
		Pca pca = new Pca();
		double[][] arr= pca.importFile("/u/bsairamr/foxfolder/distMat.csv");
		Matrix mat= new Matrix(arr);
		SingularValueDecomposition svd= mat.svd();
		
		Matrix U = svd.getU();
		Matrix V= svd.getV();
		Matrix S = svd.getS();
		
		
		Matrix newU = U.getMatrix(0, 6160, 0, 2);
		Matrix newV = V.getMatrix(0, 2, 0, 6160);
		Matrix newS = S.getMatrix(0, 2, 0, 2);
		
		
		Matrix X = newU.times(newS).times(newV);
		//Matrix Y = X.arrayTimes(V.transpose());
		
		System.out.println(X.getRowDimension());
		System.out.println(X.getColumnDimension());
		
		return X;
		
	}
	
/*	public static void main(String[] args) throws IOException {
		
		
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
	}*/

}
