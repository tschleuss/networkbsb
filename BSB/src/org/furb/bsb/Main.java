package org.furb.bsb;

import java.util.List;

import org.furb.bsb.controller.OperBSB;
import org.furb.bsb.controller.TreinBSB;
import org.furb.bsb.model.Matrix;
import org.furb.bsb.resource.KnowledgeLoader;


public class Main {

	public static void main(String[] args) 
	{
		try 
		{
			
			//classe teste.. original e BSBView.java

			//Classe que carrega a base de conhecimendo
			KnowledgeLoader kl = new KnowledgeLoader();
			
			//Matriz com as letras
			List<Matrix> knowledgeList = kl.load();
			System.out.println(	knowledgeList.size() + " PADROES A SEREM TREINADOS");
			
			//Treina os padroes
			Matrix wObj = new TreinBSB().treinBSB(knowledgeList, 0.1f, 0.0001f);
			System.out.println("TREINADO: ");
			System.out.println( wObj.toString() );
			
			//Realiza a operacao sobre a base treinada e o padrao definido
			Matrix result = new OperBSB().operBSB(wObj, kl.loadRandomPattern() );
			System.out.println("RESULTADO: ");
			System.out.println( result.toMatrixString() );
			
		} catch (Exception m) {
			m.printStackTrace();
		}
	}
}
