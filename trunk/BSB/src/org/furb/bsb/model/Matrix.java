package org.furb.bsb.model;

import org.furb.bsb.utils.MatrixException;
import org.furb.bsb.utils.MatrixOperartions;

/**
 * Classe utilizada para manipulacao de matrizes
 * 
 * @author Thyago Schleuss
 * @since 07/04/2010
 */
public class Matrix {

	private double[][] elements;

	/**
	 * Inicializa a matriz no contrutor
	 * 
	 * @param matrix
	 */
	public Matrix(double[][] matrix) {
		elements = matrix;
	}

	/**
	 * Retorna uma nova matrix resultante da soma das duas matrizes
	 * 
	 * @param matrix
	 * @return
	 * @throws MatrixException 
	 */
	public Matrix add(Matrix matrix) throws MatrixException 
	{
		if (matrix.getRows() != this.getRows() || matrix.getColumns() != this.getColumns()) 
		{
			throw new MatrixException(
				MatrixOperartions.ADD, 
				matrix.getRows(), 
				matrix.getColumns(), 
				this.getRows(), 
				this.getColumns()
			);
		}

		return new Matrix(this.addElements(matrix));
	}

	/**
	 * Retorna uma nova matrix resultante da subtracao das duas matrizess
	 * 
	 * @param matrix
	 * @return
	 * @throws MatrixException
	 */
	public Matrix subtract(Matrix matrix) throws MatrixException 
	{
		if (matrix.getRows() != this.getRows() || matrix.getColumns() != this.getColumns()) 
		{
			throw new MatrixException(
				MatrixOperartions.SUBTRACT,
				matrix.getRows(), 
				matrix.getColumns(), 
				this.getRows(), 
				this.getColumns()
			);
		}

		return new Matrix(this.subtractElements(matrix));
	}

	/**
	 * Retorna uma nova matrix resultante da multiplicacao das duas matrizes
	 * 
	 * @param matrix
	 * @return
	 * @throws MatrixException 
	 */
	public Matrix product(Matrix matrix) throws MatrixException
	{
		if (matrix.getRows() != this.getColumns()) 
		{
			throw new MatrixException(
				MatrixOperartions.PRODUCT, 
				this.getRows(), 
				this.getColumns(), 
				matrix.getRows(), 
				matrix.getColumns()
			);
		}

		return new Matrix(this.productElements(matrix));
	}

	/**
	 * Retorna uma nova matrix transposta da matriz atual
	 * 
	 * @return
	 */
	public Matrix transpose() {

		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] newComponents = new double[m][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newComponents[j][i] = this.getElementAt(i, j);
			}
		}

		return new Matrix(newComponents);
	}
	
	/**
	 * Retorna uma nova matrix com a diagonal principal zerada.
	 * 
	 * @return
	 */
	public Matrix zeroMainDiagonal()
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] newComponents = new double[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if( i == j ) {
					newComponents[i][j] = 0.0f;
				} else {
					newComponents[i][j] = this.getElementAt(i, j);
				}
			}
		}
		
		return new Matrix(newComponents);
	}
	
	/**
	 * Retorna uma nova matrix com todos os valores do vetor zerados
	 * 
	 * @return
	 */
	public Matrix zeroMatrix() 
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] newComponents = new double[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newComponents[i][j] = 0.0f;
			}
		}
		
		return new Matrix(newComponents);
	}
	
	/**
	 * Valida e retorna <code>true</code>
	 * caso as matrizes sejam iguais,
	 * retorna <code>false</code> caso
	 * contrario.
	 * @param matrix
	 * @return
	 */
	public boolean equals(Matrix matrix)
	{
		final int n = getRows();
		final int m = getColumns();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				final double elA = this.getElementAt(i, j);
				final double elB = matrix.getElementAt(i, j);
				if( elA != elB ) {
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * Retorna true se a matriz for uma matriz quadrada, retorna false caso
	 * contrario.
	 * 
	 * @return
	 */
	public boolean isSquare() 
	{
		return this.getRows() == this.getColumns();
	}

	/**
	 * Retorna uma nova matrix resultante da multiplicacao de cada elemento da
	 * matriz atual pelo valor informado por parametro
	 * 
	 * @param value
	 * @return
	 */
	public Matrix product(double value) 
	{
		return new Matrix(this.productComponents(value));
	}

	/**
	 * Retorna um vetor com os valores da multiplicacao
	 * 
	 * @param vetor
	 * @return
	 * @throws MatrixException 
	 */
	public double[] product(double[] vetor) throws MatrixException 
	{
		final int m = this.getColumns();

		if (vetor.length != m) 
		{
			throw new MatrixException(MatrixOperartions.PRODUCT_VECTOR, m, vetor.length);
		}

		return secureProduct(vetor);
	}
	
	/**
	 * Retorna o maior elemento existente na matrix
	 * 
	 * @return
	 */
	public double max() 
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double maxElement = this.getElementAt(0, 0);
		
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < m; j++) 
			{
				final double atual = this.getElementAt(i, j);
				
				if( atual > maxElement ) {
					maxElement = atual;
				}
			}
		}
		
		return maxElement;
	}
	
	/**
	 * Retorna uma nova matrix onde os elementos maiores que
	 * <code>value</code> ficarao com os valore em <code>value</code>
	 * @return
	 */
	public Matrix max(double value) 
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] newComponents = new double[n][m];
		
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < m; j++) 
			{
				final double atual = this.getElementAt(i, j);
				
				if( value > atual ) {
					newComponents[i][j] = value;
				} else {
					newComponents[i][j] = atual;
				}
			}
		}
		
		return new Matrix(newComponents);
	}
	
	/**
	 * Retorna uma nova matrix onde os elementos menores que
	 * <code>value</code> ficarao com os valore em <code>value</code>
	 * @return
	 */
	public Matrix min(double value) 
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] newComponents = new double[n][m];
		
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < m; j++) 
			{
				final double atual = this.getElementAt(i, j);
				
				if( value < atual ) {
					newComponents[i][j] = value;
				} else {
					newComponents[i][j] = atual;
				}
			}
		}
		
		return new Matrix(newComponents);
	}
	
	/**
	 * Retorna uma nova matrix com o valor absoluto de cada elemento
	 * da matrix atual.
	 * 
	 * @return
	 */
	public Matrix abs() 
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] newComponents = new double[n][m];
		
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < m; j++) 
			{
				newComponents[i][j] = Math.abs( this.getElementAt(i, j) );
			}
		}
		
		return new Matrix( newComponents );
	}
	
	/**
	 * Retorna uma nova matrix identidade da matrix atual
	 * 
	 * @param n
	 * @return
	 */
    public Matrix identity() 
    {
		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] newComponents = new double[n][m];
		
		for (int i = 0; i < n; i++) 
		{
			newComponents[i][i] = 1.0f;
		}
		
		return new Matrix( newComponents );
    }

	/**
	 * Soma os valores da nova matriz
	 * 
	 * @param matrix
	 * @return
	 */
	private double[][] addElements(Matrix matrix) 
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] newElements = new double[n][m];

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++) 
			{
				newElements[i][j] = this.getElementAt(i, j) + matrix.getElementAt(i, j);
			}
		}

		return newElements;
	}

	/**
	 * Subtrai os valores da nova matriz
	 * 
	 * @param matrix
	 * @return
	 */
	private double[][] subtractElements(Matrix matrix) 
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] newElements = new double[n][m];

		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < m; j++) 
			{
				newElements[i][j] = this.getElementAt(i, j) - matrix.getElementAt(i, j);
			}
		}
		
		return newElements;
	}

	/**
	 * Multiplica os valores da nova matrizs
	 * 
	 * @param matrix
	 * @return
	 */
	private double[][] productElements(Matrix matrix)
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		final int p = matrix.getColumns();
		
		double[][] newElements = new double[n][p];

		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < p; j++)
			{
				for (int k = 0; k < m; k++) 
				{
					newElements[i][j] += this.getElementAt(i, k) * matrix.getElementAt(k, j);
				}
			}
		}

		return newElements;
	}

	/**
	 * Multiplica os valores da matriz pelo valor passado por parametro
	 * 
	 * @param value
	 * @return
	 */
	private double[][] productComponents(double value) 
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] newComponents = new double[n][m];

		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < m; j++) 
			{
				newComponents[i][j] = value * this.getElementAt(i, j);
			}
		}

		return newComponents;
	}

	/**
	 * Multiplica os valores da matriz com o vetor passado por parametro
	 * 
	 * @param v
	 * @return
	 */
	private double[] secureProduct(double[] v) 
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double[] vectorComponents = new double[n];

		for (int i = 0; i < n; i++) 
		{
			vectorComponents[i] = 0;
			
			for (int j = 0; j < m; j++)
			{
				vectorComponents[i] += this.getElementAt(i, j) * v[j];
			}
		}

		return vectorComponents;
	}
	
	/**
	 * Retorna uma nova Matrix contento os valores
	 * da coluna especificada.
	 * @param column
	 * @return
	 */
	public Matrix getColumnMatrix(int column)
	{
		final int n = this.getRows();
		final int m = this.getColumns();
		double[][] vectorComponents = new double[n][1];
		
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < m; j++) 
			{
				final double value = this.getElementAt(i, j);
				
				if( j == column ) {
					vectorComponents[i][0] = value;
				}
			}
		}
		
		return new Matrix( vectorComponents );
	}
	
	/**
	 * Converte a matrix de NxM para uma matrix
	 * de 1 coluna s—.
	 * @param matrix
	 * @return
	 */
	public Matrix convertInColumnMatrix()
	{
		double[][] newMatrix = new double[this.getElements().length * this.getElements()[0].length][1];
		int row = 0;
		
		for( int i = 0; i < this.getElements()[0].length; i++ )
		{
			for( int j = 0; j < this.getElements().length; j++ )
			{
				newMatrix[row][0] = this.getElements()[j][i];
				row++;
			}
		}
		
		return new Matrix(newMatrix);
	}
	
	/**
	 * Converte a matrix de Nx1 para uma matrix
	 * de NxM (7x5)
	 * @return
	 */
	public Matrix convertInRowColumnMatrix(int n, int m)
	{
		double[][] newElement = new double[n][m];
		int row = 0;
		int col = 0;
		
		for( int i = 0; i < this.getElements().length; i++ )
		{
			newElement[row][col] = this.getElementAt(i, 0);
			
			if( row < newElement.length-1 ) {
				row++;
			} else {
				row = 0;
				col++;
			}
		}
		
		return new Matrix(newElement);
	}

	/**
	 * Retorna o numero de linhas da matriz
	 * 
	 * @return
	 */
	public int getRows() 
	{
		return elements.length;
	}

	/**
	 * Retorna o numero de colunas da matriz
	 * 
	 * @return
	 */
	public int getColumns() 
	{
		return elements[0].length;
	}

	/**
	 * Retorna o elemento da matriz existente na posicao i,j
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public double getElementAt(int i, int j) 
	{
		return elements[i][j];
	}

	/**
	 * Retorna a matriz
	 * 
	 * @return
	 */
	public double[][] getElements() 
	{
		return elements;
	}

	/**
	 * Seta a matriz
	 * 
	 * @param elements
	 */
	public void setElements(double[][] elements) 
	{
		this.elements = elements;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder retValue = new StringBuilder();
		
		for( int j = 0; j < this.getRows(); j++ ) 
		{
			for( int i = 0; i < this.getColumns(); i++ ) 
			{ 
				final double value = elements[j][i];
				
				if( value == Float.valueOf(-1) ) {
					retValue.append(0);
				} else if( value == Float.valueOf(1) ){
					retValue.append(1);
				} else {
					retValue.append(value);
				}
				
				if( i <= this.getColumns() ) {
					retValue.append(",");
				}
			}
			
			retValue.append("\n");
		}
		
		return retValue.toString();
	}
}
