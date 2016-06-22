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
	
	public void PCA() throws IOException{
		Pca pca= new Pca();
		
		//double[][] distArr= pca.importFile("PCA.csv");
		//Matrix distMat= new Matrix(distArr);
		Matrix distMat= Matrix.random(4, 4);
		EigenvalueDecomposition decomposition= distMat.eig();
		Matrix V = decomposition.getV();
		Matrix D = decomposition.getD();
		Matrix Q = V; 
		V = V.getMatrix(0, 3, 0, 1);
		D = D.getMatrix(0, 1, 0, 3);
		Q = Q.getMatrix(0, 3, 0,1);
	}
	
	
	

}
