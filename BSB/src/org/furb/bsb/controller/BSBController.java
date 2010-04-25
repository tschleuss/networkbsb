package org.furb.bsb.controller;

import java.util.List;

import org.furb.bsb.model.Matrix;
import org.furb.bsb.resource.KnowledgeLoader;
import org.furb.bsb.utils.Setup;

/**
 * Controller da rede BSB
 * @author Thyago Schleuss
 */
public class BSBController {

	//Matriz com os dados fornecidos pelo usuarios
	private double[][] userMatrix = new double[Setup.ROW_SIZE][Setup.COL_SIZE];
	
	//Matriz de treinamento
	private List<Matrix> knowledgeList = null;
	
	//Matriz com todos os padroes treinados
	private Matrix treinedMatrix = null;
	
	//Matriz de resultado
	private Matrix resultMatrix = null;
	
	public void initBSBController() 
	{
		this.initMatrix();
		this.initKnowledgeList();
	}
	
	/**
	 * Realiza a operacao sobre a matriz
	 * de conhecimento trainda e a entrada do usuario.
	 */
	public void operBSB()
	{
		//Realiza a operacao sobre a base treinada e o padrao definido
		resultMatrix = new OperBSB().operBSB(treinedMatrix, new Matrix(userMatrix).convertInColumn() );
	}
	
	/**
	 * Realiza o treinamento sobre a matriz
	 * de conhecimento.
	 * @param learnTaxe
	 * @param stopCriter
	 */
	public void treinBSB(float learnTaxe, float stopCriter)
	{
		//Treina os padroes
		treinedMatrix = new TreinBSB().treinBSB(knowledgeList, Setup.LEARN_RATE, Setup.STOP_CRITERIA);
	}
	
	/**
	 * Inicializa a matriz de conhecimento
	 * a ser treinada
	 */
	private void initKnowledgeList()
	{
		//Classe que carrega a base de conhecimendo
		KnowledgeLoader kl = new KnowledgeLoader();
		
		//Matriz com as letras
		knowledgeList = kl.load();
	}
	
	/**
	 * Inicializa a matriz de entrada do usuario
	 * com o valor 1.
	 */
	private void initMatrix() 
	{
        for( int i = 0; i < Setup.COL_SIZE; i++ )
        {
        	for( int j = 0; j < Setup.ROW_SIZE; j++ )
        	{
        		userMatrix[j][i] = 1;
        	}
        }
	}

	public double[][] getUserMatrix() {
		return userMatrix;
	}

	public void setUserMatrix(double[][] userMatrix) {
		this.userMatrix = userMatrix;
	}

	public List<Matrix> getKnowledgeList() {
		return knowledgeList;
	}

	public void setKnowledgeList(List<Matrix> knowledgeList) {
		this.knowledgeList = knowledgeList;
	}

	public Matrix getTreinedMatrix() {
		return treinedMatrix;
	}

	public void setTreinedMatrix(Matrix treinedMatrix) {
		this.treinedMatrix = treinedMatrix;
	}

	public Matrix getResultMatrix() {
		return resultMatrix;
	}

	public void setResultMatrix(Matrix resultMatrix) {
		this.resultMatrix = resultMatrix;
	}
}
