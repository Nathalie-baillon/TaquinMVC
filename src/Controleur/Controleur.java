package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.PlateauDeJeu;
import Vue.JButtonPosition;
import Vue.VueGraphique;

public class Controleur implements ActionListener {
	private VueGraphique vueGraphique;
	private PlateauDeJeu plateauDeJeu;

	public Controleur(VueGraphique vueGraphique, PlateauDeJeu plateauDeJeu) {
		this.vueGraphique = vueGraphique;
		this.plateauDeJeu = plateauDeJeu;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();

		//bouton melanger
		if (source == vueGraphique.getBoutonMelange()){
			System.out.println("mélange");

			// Mélange du plateau et adaptation de la vue
			plateauDeJeu.melanger();
			System.out.println();
		}

		//déplacement pièce du jeu
		else {
			int x0 = plateauDeJeu.getXvide();
			int y0 = plateauDeJeu.getYvide();;
			JButtonPosition bouton = (JButtonPosition) source; 
			int i = bouton.getI();
			int j = bouton.getJ();
			vueGraphique.getBoutonCase(i, j);

			// vérifie si permutation possible
			if (plateauDeJeu.deplacerPiece(i, j) == true){
				
				// vérifie si jeu terminé
				if (plateauDeJeu.jeuTermine() == true){
					
					vueGraphique.jeuTermine();
				}

			}

		}
		
	}
	
}
