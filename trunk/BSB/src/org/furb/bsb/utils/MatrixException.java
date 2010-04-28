package org.furb.bsb.utils;

import java.text.MessageFormat;

/**
 * Classe utilizada como exception dos erros que 
 * podem ocorrer nas operacoes sobre a matriz.
 * @author Thyago Schleuss
 * @author Luiz Diego Aquino
 * @author Luiz Roberto Leicht
 * @since 07/04/2010
 */
public class MatrixException extends Exception {

	/**
	 * Serial Padrao
	 */
	private static final long serialVersionUID = -2313692975150332548L;

	/**
	 * Construtor da exception
	 * @param type
	 * @param params
	 */
	public MatrixException(MatrixOperartions type, Object ... params) {
		super( new MessageFormat( type.getPattern() ).format( params ) );
	}
}
