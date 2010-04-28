package org.furb.bsb.utils;

/**
 * Armazena o pattern de erro para as operacoes
 * sobre a matriz
 * @author Thyago Schleuss
 * @author Luiz Diego Aquino
 * @author Luiz Roberto Leicht
 * @since 07/04/2010
 */
public enum MatrixOperartions {

	ADD				("Erro de operacao: nao posso adicionar uma matriz {0} por {1} a uma matriz {2} por {3}"),
	SUBTRACT		("Erro de produto: nao posso subtrair a matriz {0} por {1} a matriz {2} por {3}"),
	PRODUCT			("Erro de operacao: nao posso multiplicar uma matriz {0} por {1} com uma matriz {2} por {3}"),
	PRODUCT_VECTOR	("Erro no porduto: a matriz {0} por {1} nao pode ser multiplicada por um vector {2} de dimensao {3}");
	
	private String pattern;
	
	private MatrixOperartions(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
