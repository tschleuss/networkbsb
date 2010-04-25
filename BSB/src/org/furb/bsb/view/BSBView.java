package org.furb.bsb.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import org.furb.bsb.controller.BSBController;
import org.furb.bsb.model.Matrix;

/**
 * Classe que representa a tela de interacao com o usuario
 * @author Thyago Schleuss
 * @since 23/04/2010
 */
public class BSBView extends javax.swing.JFrame {

	private static final long serialVersionUID = 2900640123739586996L;
	
	//Armazena os paineis que renderizam a saida
	private JButton[][] matrizSaida = new JButton[7][5];
	
	//Paineis
	private JPanel		panelMatrizes;
    private JPanel		panelMatrizEntrada;
    private JPanel		panelMatrizSaida;
    
    //Misc
    private JButton		btOperar;
    private JButton		btTreinar;
    private JTextField	inputCriterioParada;
    private JTextField	inputTaxaAprendizagem;
    private JLabel		lbCriterioParada;
    private JLabel		lbMatrizEntrada;
    private JLabel		lbMatrizSaida;
    private JLabel		lbTaxaAprendizagem;
    
    //Toolbar
    private JMenuBar	menuBar;
    
    //Menu 'Arquivo' e sub-itens
    private JMenu		menuArquivo;
    private JMenuItem	miCarregarMatrizEntrada;
    private JMenuItem	miCarregarMatrizPesos;
    private JMenuItem	miFechar;
    private JMenuItem	miNovaRede;
    private JMenuItem	miSalvarMatrizPesos;
    
    //Menu 'Exibir' e sub-itens
    private JMenu		menuExibir;
    private JMenuItem	miExibirMatrizEntrada;
    private JMenuItem	miExibirMatrizPesos;

    //Menu Sobre' e sub-itens
    private JMenu		menuSobre;
    private JMenuItem	miSobreEquipe;
    private JMenuItem	miSobrePrograma;
    
    private BSBController controller = null;
    
    //Construtor padrao
    public BSBView() 
    {
    	controller = new BSBController();
    	controller.initBSBController();
        initComponents();
    }

    //Inicializa os componentes graficos
    private void initComponents()
    {
        panelMatrizes			= new JPanel();
        panelMatrizEntrada		= new JPanel();
        panelMatrizSaida		= new JPanel();
        
        btOperar				= new JButton();
        btTreinar				= new JButton();
        inputCriterioParada		= new JTextField();
        inputTaxaAprendizagem	= new JTextField();
        lbCriterioParada		= new JLabel();
        lbMatrizEntrada			= new JLabel();
        lbMatrizSaida			= new JLabel();
        lbTaxaAprendizagem		= new JLabel();
       
        menuBar					= new JMenuBar();
        menuArquivo				= new JMenu();
        miNovaRede				= new JMenuItem();
        miSalvarMatrizPesos		= new JMenuItem();
        miCarregarMatrizPesos	= new JMenuItem();
        miCarregarMatrizEntrada = new JMenuItem();
        miFechar				= new JMenuItem();
        
        menuExibir				= new JMenu();
        miExibirMatrizEntrada	= new JMenuItem();
        miExibirMatrizPesos		= new JMenuItem();
        
        menuSobre				= new JMenu();
        miSobrePrograma			= new JMenuItem();
        miSobreEquipe			= new JMenuItem();

        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setTitle("[Redes Neurais] - BSB");
        super.setResizable(false);

        panelMatrizes.setLayout(new GridBagLayout());
        
        panelMatrizEntrada.setBackground(new Color(204, 204, 204));
        panelMatrizEntrada.setLayout(new GridBagLayout());

        panelMatrizSaida.setBackground(new Color(204, 204, 204));
        panelMatrizSaida.setLayout(new GridBagLayout());
        
        this.buildMatriz(panelMatrizEntrada, panelMatrizes, 0, 1, true);
        this.buildMatriz(panelMatrizSaida, panelMatrizes,   1, 1, false);

        lbMatrizEntrada.setFont(new Font("Lucida Grande", 1, 13));
        lbMatrizEntrada.setText("Entrada");
        panelMatrizes.add(lbMatrizEntrada, new GridBagConstraints());

        lbMatrizSaida.setFont(new Font("Lucida Grande", 1, 13));
        lbMatrizSaida.setText("Saida");
        panelMatrizes.add(lbMatrizSaida, new GridBagConstraints());

        lbTaxaAprendizagem.setFont(new Font("Lucida Grande", 1, 13));
        lbTaxaAprendizagem.setText("Taxa de aprendizagem:");

        lbCriterioParada.setFont(new Font("Lucida Grande", 1, 13));
        lbCriterioParada.setText("Critério de parada:");

        inputTaxaAprendizagem.setText("0,1");

        inputCriterioParada.setText("0,0001");

        btTreinar.setText("Treinar");

        btOperar.setText("Operar");

        menuArquivo.setText("Arquivo");

        miNovaRede.setText("Nova rede");
        menuArquivo.add(miNovaRede);

        miSalvarMatrizPesos.setText("Salvar matriz de pesos");
        menuArquivo.add(miSalvarMatrizPesos);

        miCarregarMatrizPesos.setText("Carregar matriz de pesos");
        menuArquivo.add(miCarregarMatrizPesos);

        miCarregarMatrizEntrada.setText("Carregar matriz de entrada");
        menuArquivo.add(miCarregarMatrizEntrada);

        miFechar.setText("Fechar");
        menuArquivo.add(miFechar);

        menuBar.add(menuArquivo);

        menuExibir.setText("Exibir");

        miExibirMatrizEntrada.setText("Matriz de entrada");
        menuExibir.add(miExibirMatrizEntrada);

        miExibirMatrizPesos.setText("Matriz de pesos");
        menuExibir.add(miExibirMatrizPesos);

        menuBar.add(menuExibir);

        menuSobre.setText("Sobre");

        miSobrePrograma.setText("O Programa");
        menuSobre.add(miSobrePrograma);

        miSobreEquipe.setText("A Equipe");
        menuSobre.add(miSobreEquipe);

        menuBar.add(menuSobre);

        super.setJMenuBar(menuBar);
        
        btTreinar.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e) 
			{
				final float learnTaxe = Float.valueOf( String.valueOf( inputTaxaAprendizagem.getText().replace(",", ".") ) );
				final float stopCriter = Float.valueOf( String.valueOf( inputCriterioParada.getText().replace(",", ".") ) );
				controller.treinBSB(learnTaxe, stopCriter);
			}
        });
        
        btOperar.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e) 
			{
				controller.operBSB();
				Matrix resultMatrix = controller.getResultMatrix().convertInRowColumnMatrix(7,5);
				
		        for( int i = 0; i < 5; i++ )
		        {
		        	for( int j = 0; j < 7; j++ )
		        	{
		        		final int row = j;
		        		final int col = i;
		        		JButton button = matrizSaida[row][col];
		        		float value = resultMatrix.getElementAt(row, col);
		        		
						if( value == -1 ) {
							button.setIcon(new ImageIcon(getClass().getResource("/org/furb/bsb/resource/icons/black.png")));
						} else if ( value == 1 ) {
							button.setIcon(new ImageIcon(getClass().getResource("/org/furb/bsb/resource/icons/white.png")));
						} else {
							button.setIcon(new ImageIcon(getClass().getResource("/org/furb/bsb/resource/icons/gray.png")));
						}
		        	}
		        }
			}
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelMatrizes, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbCriterioParada, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTaxaAprendizagem, GroupLayout.Alignment.LEADING))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputCriterioParada)
                            .addComponent(inputTaxaAprendizagem, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(btTreinar, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(btOperar, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))))
                .addContainerGap())
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTaxaAprendizagem)
                    .addComponent(inputTaxaAprendizagem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTreinar))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCriterioParada)
                    .addComponent(inputCriterioParada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btOperar))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMatrizes, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }
    
    /**
     * Metodo utilizado para montar as matrizes
     * @param matriz
     * @param panelPai
     * @param x
     * @param y
     */
    private void buildMatriz(JPanel matriz, JPanel panelPai, int x, int y, boolean addAction)
    {
    	GridBagConstraints gridBagConstraints = null;
    	JButton squareButton = null;
    	
        //Criar os blocos da matriz de saida
        for( int i = 0; i < 5; i++ )
        {
        	for( int j = 0; j < 7; j++ )
        	{
        		final int row = j;
        		final int col = i;
        		
        		squareButton = new JButton();
        		
        		squareButton.setBackground(new Color(255, 255, 255));
        		squareButton.setIcon(new ImageIcon(getClass().getResource("/org/furb/bsb/resource/icons/white.png")));
        		squareButton.setSelectedIcon(new ImageIcon(getClass().getResource("/org/furb/bsb/resource/icons/white.png")));
        		squareButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        		squareButton.setPreferredSize(new Dimension(32, 32));

                GroupLayout squarePanelLayout = new GroupLayout(squareButton);
                squareButton.setLayout(squarePanelLayout);
                
                squarePanelLayout.setHorizontalGroup(
                	squarePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 30, Short.MAX_VALUE)
                );
                
                squarePanelLayout.setVerticalGroup(
                	squarePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 30, Short.MAX_VALUE)
                );
                
                if( addAction )
                {
	                //Listener para os cliques do usuario
                	squareButton.addActionListener(new ActionListener()
	                {
						public void actionPerformed(ActionEvent e) 
						{
							float value = controller.getUserMatrix()[row][col];
							JButton button = (JButton)e.getSource();
							
							if( value == -1 ) {
								button.setIcon(new ImageIcon(getClass().getResource("/org/furb/bsb/resource/icons/gray.png")));
								value = 0;
							} else if ( value == 1 ) {
								button.setIcon(new ImageIcon(getClass().getResource("/org/furb/bsb/resource/icons/black.png")));
								value = -1;
							} else {
								button.setIcon(new ImageIcon(getClass().getResource("/org/furb/bsb/resource/icons/white.png")));
								value = 1;
							}
							
							controller.getUserMatrix()[row][col] = value;
						}	
	                });
                	
                } else {
                	matrizSaida[row][col] = squareButton;
                }
                
                gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = i;
                gridBagConstraints.gridy = j;
                matriz.add(squareButton, gridBagConstraints);
        	}
        }
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        panelPai.add(matriz, gridBagConstraints);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	BSBView bsbView = new BSBView();
            	bsbView.setLocationRelativeTo(null);
            	bsbView.setVisible(true);
            }
        });
    }
}
