package org.furb.bsb.controller;

import java.util.List;

import org.furb.bsb.model.Matrix;
import org.furb.bsb.utils.MatrixException;

/**
 * Controller de trainamento utilizado
 * para o treinamento da rede sobre
 * os padroes aplicados a ela.
 * @author Thyago Schleuss
 * @author Luiz Diego Aquino
 * @author Luiz Roberto Leicht
 */
public class TreinBSB {

	public TreinBSB() {
		super();
	}
	
	/**
	 * Realiza operacao de treinamento sobre os padroes
	 * definidos.
	 * @param knowledgeList
	 * @param learnTaxe
	 * @param stopCriter
	 * @return
	 */
	public Matrix treinBSB(List<Matrix> knowledgeList, double learnTaxe, double stopCriter)
	{
		Matrix wObj = null;
		
		try {
			
			//Matriz com a letra A
			final int n = knowledgeList.get(0).getRows();

			//Matriz com pesos iguais a zero
			double wZero[][] = new double[n][n];
			wObj = new Matrix(wZero).zeroMatrix();
			
			//Matriz de correcao dos pesos
			Matrix wDelta = null;
			Matrix xObj = null;
			Matrix sObj = null;
			boolean finish = false;
			
			
			trein: 
			{
				if( !finish ) 
				{
					//Laco de repeticao das epocas de treinamento
					for( int i = 1; i <= 100; i++ )
					{
						//Laco de repeticao sobre todos os padroes
						for( Matrix matrix : knowledgeList )
						{
							xObj = matrix.getColumn( 0 );
							
							//Calcula vetor de ativacoes
							sObj = wObj.product( xObj );

							//Calcula a matriz de correcao dos pesos
							wDelta = xObj.subtract( sObj ).product( learnTaxe ).product( xObj.transpose() );
							
							//Ajusta os pesos
							wObj = wObj.add( wDelta );
						}
						
						//Se atingiu o criterio de parada
						if( wDelta.abs().max() < stopCriter ) {
							finish = !finish;
							break trein;
						}	
					}	
				}	
			}
			
			wObj = wObj.add( wObj.identity() );
			
		} catch (MatrixException m) {
			m.printStackTrace();
		}
		
		return wObj;
	}
}
