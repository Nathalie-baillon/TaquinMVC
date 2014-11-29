package Main;

import Controleur.Controleur;
import Modele.PlateauDeJeu;
import Vue.VueGraphique;

public class Main {

	public static void main(String[] args) {
		int nl = 4;
		int nc = 4;
		PlateauDeJeu modele = new PlateauDeJeu(nl,nc);
		VueGraphique fenetre = new VueGraphique(modele);
		Controleur controleur =  new Controleur(fenetre, modele);

		fenetre.getBoutonMelange().addActionListener(controleur);

		for (int i = 0 ; i < nl; ++i) {
			for (int j = 0; j < nc; ++j) {
				fenetre.getBoutonCase(i,j).addActionListener(controleur);
			}
		}
	}
}
