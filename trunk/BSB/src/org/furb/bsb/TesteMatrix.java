package org.furb.bsb;

import org.furb.bsb.model.Matrix;
import org.furb.bsb.utils.MatrixException;

public class TesteMatrix {

	public static void main(String[] args) throws MatrixException {
		
		double[][] v1 = new double[][]{
				{2,3},
				{1,0},
				{4,5}
		};
		
		double[][] v2 = new double[][]{
				{3,1},
				{2,4}
		};
		
		Matrix m1 = new Matrix(v1);
		Matrix m2 = new Matrix(v2);
		
		Matrix re = m1.product(m2);
		
		System.out.println( re.toMatrixString() );
	}
}
