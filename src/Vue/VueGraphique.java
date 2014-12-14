package Vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modele.PlateauDeJeu;


public class VueGraphique extends JFrame implements Observer{
	private JPanel panel = new JPanel();
	private JPanel grille = new JPanel();
	private JButton boutonMelange = new JButton("Mélanger");
	int nl = 4; //nombre lignes
	int nc = 4; //nombre colonnes
	final int tailleDisplayX = 403;
	final int tailleDisplayY = 403;
	private PlateauDeJeu plateauDeJeu;
	public JButtonPosition[][] jbs = new JButtonPosition[nl][nc];
	private ImageIcon[][] icones;
	JOptionPane p = new JOptionPane();

	/*private JPanel commandes = new JPanel();
	private JLabel lignes = new JLabel("Nombre de lignes : ");
	private JLabel colonnes = new JLabel("Nombre de colonnes : ");
	private String tab[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private JComboBox col = new JComboBox(tab);
	private JComboBox lig = new JComboBox(tab);*/


	// Constructeur de la vue
	public VueGraphique(PlateauDeJeu plateauDeJeu){
		this.plateauDeJeu = plateauDeJeu;

		plateauDeJeu.addObserver(this);

		nl = this.plateauDeJeu.getNL();
		nc = this.plateauDeJeu.getNC();

		setTitle("Taquin");
		setLocationRelativeTo(null);
		setSize(415,455);
		setContentPane(buildContentPane());
		setResizable(false);
		setVisible(true);
	}

	//Conteneur principal
	private JPanel buildContentPane(){
		panel.setLayout(new BorderLayout());

		/*commandes.setLayout(new GridLayout(2,2));

		commandes.add(colonnes);
		commandes.add(col);
		commandes.add(lignes);
		commandes.add(lig);

		panel.add(commandes, BorderLayout.PAGE_START);*/

		// Découpage de l'image et affichage de l'image
		ImageCutter vi = new ImageCutter("image.jpg", tailleDisplayX,tailleDisplayY);
		icones = vi.divide(nl, nc);
		icones[0][0] = null;

		grille.setLayout(new GridLayout(nl, nc));
		Insets insets = new Insets(0, 0, 0, 0);
		for (int i = 0 ; i < nl; ++i) {
			for (int j = 0; j < nc; ++j) {

				jbs[i][j] = new JButtonPosition(icones[i][j],i,j);

				jbs[i][j].setMargin(insets);

				grille.add(jbs[i][j]);
			}
		}
		panel.add(grille, BorderLayout.CENTER);	    

		// Bouton de mélange
		panel.add(boutonMelange, BorderLayout.PAGE_END);

		return panel;
	}


	public void jeuTermine(){
		int reponse = p.showConfirmDialog(this, "Bravo! Vous avez terminé le jeu. \n" +
				"Voulez-vous rejouer?","Bravo", JOptionPane.YES_NO_OPTION);
		if (reponse == JOptionPane.NO_OPTION){
			System.exit(0);
		}
		if (reponse == JOptionPane.YES_OPTION){
		}
	}

	@Override
	public void update(Observable obs, Object button) {
		for (int i = 0 ; i < nl; ++i) {
			for (int j = 0; j < nc; ++j) {
				int numCase = this.plateauDeJeu.getValue(i, j);
				jbs[i][j].setIcon(icones[(int)numCase/(int)nc][numCase % nc]);
			}
		}
		panel.repaint();
	}

	public JButton getBoutonMelange() {
		return boutonMelange;
	}
	
	public JButton getBoutonCase(int i, int j) {
		return (jbs[i][j]);
	}
}


