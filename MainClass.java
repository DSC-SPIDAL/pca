package stock;

import java.io.FileWriter;
import java.io.IOException;

import Jama.*;

public class MainClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Pca pca= new Pca();
	/*	Matrix X= pca.SVD();
		double[][] arr= X.getArray();
		FileWriter fw= new FileWriter("/home/helloworld/Documents/outputPCA.csv");
		
		for(int i=0; i<6161;i++){
			for(int j =0; j<3; j++){
				fw.write(arr[i][j]+",");
			}
			fw.write("\n");
		}*/
		Correlation correlation=new Correlation();
		Matrix dm= pca.SVD();
		System.out.println("done with svd");
		double[][] ar = dm.getArray();
		FileWriter fw= new FileWriter("outputPCA.csv");
		System.out.println("starting to write");
		for(int i=0; i<dm.getRowDimension();i++){
			for(int j =0; j<dm.getColumnDimension(); j++){
				fw.write(ar[i][j]+"\\s+");
			}
			fw.write("\n");
		}
		//System.out.println(ar[0][6160]);
	}
		
}
