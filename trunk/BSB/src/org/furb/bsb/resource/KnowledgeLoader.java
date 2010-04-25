package org.furb.bsb.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.furb.bsb.model.Matrix;
import org.furb.bsb.utils.Setup;

/**
 * Classe responsavel por ler e popular
 * a base de conhecimento para que o programa
 * possa treinar.
 * @author Thyago Schleuss
 * @author Luiz Diego Aquino
 * @since 20/04/2010
 */
public class KnowledgeLoader {
	
	public KnowledgeLoader() {
		super();
	}
	
	/**
	 * Le o arquivo 'knowledgeBase.txt'
	 * e carrega a matrix de valores
	 */
	public List<Matrix> load()
	{
		List<Matrix> retList = null;
		
		try {
			
			final URI filePath = KnowledgeLoader.class.getResource("knowledgeBase.txt").toURI();
			final File file = new File(filePath);
			Reader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			StringTokenizer tokenizer = null;
			
			retList = new ArrayList<Matrix>();
			double[][] matrix = new double[Setup.ROW_SIZE][Setup.COL_SIZE];
			String line = null;
			String content = null;
			int rows = 0;
			int cols = 0;
			
			while( (line = br.readLine()) != null ) 
			{
				if( !line.startsWith("--") )
				{
					if( !line.trim().isEmpty() )
					{
						tokenizer = new StringTokenizer(line,",");
						
						while( tokenizer.hasMoreTokens() )
						{
							content = tokenizer.nextToken();
							matrix[rows][cols] = Float.valueOf(content.trim());
							cols++;
						}	
						
						cols = 0;
						rows++;
					}
					else
					{
						if( rows != Setup.ROW_SIZE && cols != Setup.COL_SIZE && tokenizer != null ) 
						{
							throw new Exception("Inconsist�ncia no arquivo !");
						}
						else
						{
							if( tokenizer != null )
							{
								retList.add( new Matrix( this.convertMatrix(matrix) ) );
								matrix = new double[Setup.ROW_SIZE][Setup.COL_SIZE];
								rows = 0;
								cols = 0;
							}
						}
					}
				}
			}
			
			retList.add( new Matrix( this.convertMatrix( matrix ) ) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retList;
	}
	
	public Matrix loadRandomPattern()
	{
		Matrix retMatrix = null;
		
		try {
			
			final URI filePath = KnowledgeLoader.class.getResource("knowledgeRandom.txt").toURI();
			final File file = new File(filePath);
			Reader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			StringTokenizer tokenizer = null;
			
			double[][] matrix = new double[Setup.ROW_SIZE][Setup.COL_SIZE];
			String line = null;
			String content = null;
			int rows = 0;
			int cols = 0;
			
			while( (line = br.readLine()) != null ) 
			{
				if( !line.startsWith("--") )
				{
					if( !line.trim().isEmpty() )
					{
						tokenizer = new StringTokenizer(line,",");
						
						while( tokenizer.hasMoreTokens() )
						{
							content = tokenizer.nextToken();
							matrix[rows][cols] = Float.valueOf(content.trim());
							cols++;
						}	
						
						cols = 0;
						rows++;
					}
					else
					{
						if( rows != Setup.ROW_SIZE && cols != Setup.COL_SIZE && tokenizer != null ) 
						{
							throw new Exception("Inconsist�ncia no arquivo !");
						}
						else
						{
							if( tokenizer != null )
							{
								retMatrix = new Matrix( this.convertMatrix(matrix) );
								matrix = new double[Setup.ROW_SIZE][Setup.COL_SIZE];
								rows = 0;
								cols = 0;
							}
						}
					}
				}
			}
			
			retMatrix = new Matrix( this.convertMatrix(matrix) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retMatrix;
	}
	
	/**
	 * Converte a matrix de NxM para uma matrix
	 * de 1 coluna s�.
	 * @param matrix
	 * @return
	 */
	private double[][] convertMatrix(double[][] matrix)
	{
		final int rowSize = matrix.length * matrix[0].length;
		double[][] newMatrix = new double[rowSize][1];
		int row = 0;
		
		for( int i = 0; i < matrix[0].length; i++ )
		{
			for( int j = 0; j < matrix.length; j++ )
			{
				newMatrix[row][0] = matrix[j][i];
				row++;
			}
		}
		
		return newMatrix;
	}
}
