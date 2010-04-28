package org.furb.bsb.utils;

/**
 * Classe que armazena as variaveis com
 * valor padrao do sistema.
 * @author Thyago Schleuss
 * @author Luiz Diego Aquino
 * @author Luiz Roberto Leicht
 */
public final class Setup {

	public static final int		ROW_SIZE		= 7; //Default 7
	public static final int		COL_SIZE		= 5; //Default 3
	public static final double	LEARN_RATE		= 0.1;
	public static final double	STOP_CRITERIA	= 0.0001;
	
	private Setup() {
		super();
	}
}
