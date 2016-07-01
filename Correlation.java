package stock;

import java.io.IOException;
import java.lang.Math;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class Correlation {
	
	public double[][] calculateDistanceMatrix() throws IOException{
		Pca pca=new Pca();
		double[][] arr= pca.importFile("20090716_20100716.csv");
		double[][] distanceMatrix= new double[6161][6161];
		double[] a = new double[252];
		double[] b = new double[252];
		int p=0; 
		int q=0;
		int x=0, y=0;
		while(x<6161){
			while(p<6161){
			for(int i =0; i<252; i++){
				a[i]=arr[p][i];
			}
			while(y<6161){
				
				for(int i =0; i<252; i++){
					b[i]=arr[q][i];
				}
				
				distanceMatrix[x][y]= calculateDistance(a, b);
				q++;
				y++;
			}
			y=0;
			q=0;
			x++;
			p++;
			}
		}

		return distanceMatrix;
	}
	
	public double calculateDistance(double[] a, double[] b){
		double dist=0; 
		for(int i=0; i<252;i++ ){
			
				dist += Math.pow((a[i]-b[i]), 2);
		}
		dist = Math.sqrt(dist);
		return dist;
		
	}
	
	public Matrix PCA() throws IOException{
		
		Pca pca = new Pca();
		double[][] arr= pca.importFile("/u/bsairamr/foxfolder/distMat.csv");
		System.out.println("imported file");
		Matrix distMat= new Matrix(arr);
		//Matrix distMat= Matrix.random(4, 4);
		EigenvalueDecomposition decomposition= distMat.eig();
		System.out.println("calculating vd");
		Matrix V = decomposition.getV();
		Matrix D = decomposition.getD();
		Matrix Q = V.transpose(); 
		V = V.getMatrix(0,6160, 0, 2);
		D = D.getMatrix(0, 2, 0, 2);
		Q = Q.getMatrix(0, 2, 0,2);
		Matrix X = V.times(D).times(Q);
		System.out.println(X.getRowDimension());
		System.out.println(X.getColumnDimension());
		
		return X;
	}
	
}
