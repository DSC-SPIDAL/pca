package stock;

import java.io.FileWriter;
import java.io.IOException;

import Jama.Matrix;

public class MainClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Pca pca= new Pca();
		Matrix X= pca.SVD();
		double[][] arr= X.getArray();
		FileWriter fw= new FileWriter("/home/helloworld/Documents/outputPCA.csv");
		
		for(int i=0; i<6161;i++){
			for(int j =0; j<3; j++){
				fw.write(arr[i][j]+",");
			}
			fw.write("\n");
		};
		
/*		double[][] ar= correlation.calculateDistanceMatrix();
		FileWriter fw= new FileWriter("/home/helloworld/Documents/PCA.csv");
		
		for(int i=0; i<6161;i++){
			for(int j =0; j<6161; j++){
				fw.write(ar[i][j]+",");
			}
			fw.write("\n");
		}
		System.out.println(ar[0][6160]);*/
	}
		
}
