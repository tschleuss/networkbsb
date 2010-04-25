package org.furb.bsb.controller;

import org.furb.bsb.model.Matrix;
import org.furb.bsb.utils.MatrixException;

public class OperBSB {

	public OperBSB() {
		super();
	}
	
	public Matrix operBSB(Matrix matrix, Matrix pattern)
	{
		Matrix xObj = pattern;
		Matrix sObj = null;
		Matrix yObj = null;
		boolean finish = false;
		
		try {
			
			oper: 
			{
				if( !finish )
				{
					for( int i = 0; i < 100; i++ )
					{
						sObj = matrix.product( xObj );
						yObj = transferRampa( sObj );
						
						if( yObj.equals( xObj ) ) {
							finish = !finish;
							break oper;
						}
						
						xObj = yObj;
					}
				}
			}
			
		} catch (MatrixException e) {
			e.printStackTrace();
		}
		
		return xObj;
	}
	
	public Matrix transferRampa(Matrix matrix)
	{
		Matrix matrixMin = matrix.min( 1 );
		Matrix matrixMax = matrixMin.max( -1 );
		return matrixMax;
	}
}
