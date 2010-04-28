package org.furb.bsb.controller;

import org.furb.bsb.model.Matrix;
import org.furb.bsb.utils.MatrixException;

/**
 * Controller que realiza as operacoes
 * sobre a matriz treinada e o padrao
 * informado pelo usuario
 * @author Thyago Schleuss
 * @author Luiz Diego Aquino
 * @author Luiz Roberto Leicht
 */
public class OperBSB {

	public OperBSB() {
		super();
	}
	
	/**
	 * Realiza a operacao sobre a matriz treinada e
	 * o padrao informado pelo usuario.
	 * @param matrix
	 * @param pattern
	 * @return
	 */
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
	
	/**
	 * Funcao transfer rampa utilizada
	 * durante a operacao de reconhecimento.
	 * @param matrix
	 * @return
	 */
	public Matrix transferRampa(Matrix matrix)
	{
		Matrix matrixMin = matrix.min( 1 );
		Matrix matrixMax = matrixMin.max( -1 );
		return matrixMax;
	}
}
