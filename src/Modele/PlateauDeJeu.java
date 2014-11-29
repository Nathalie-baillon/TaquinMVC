package Modele;
import java.util.Observable;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

public class PlateauDeJeu extends Observable {	

	private int X;
	private int Y;
	public int[][] tableau;
	protected int[][] reference;
	private int xvide;
	private int yvide;

	/*
	 * Constructeur
	 */
	public PlateauDeJeu(int x, int y){
		X=x;
		Y=y;
		xvide = 0;
		yvide = 0;
		// Initialisation des tableaux
		tableau = new int[X][Y];
		reference = new int[X][Y];
		for(int i=0; i<X; i++){
			for(int j=0;j<Y; j++){
				tableau[i][j] = j+i*Y;
				reference[i][j] = j+i*Y;
			}
		}
	}

	/*
	 * Renvoie le nombre de colonnes
	 */
	public int getNC() {
		return Y;
	}

	/* 
	 * Renvoie le nombre de lignes
	 */
	public int getNL() {
		return X;
	}
	
	/*
	 * Retourne num�ro de ligne de la case vide
	 */
	public int getXvide() {
		return xvide;
	}
	
	/*
	 * Retourne num�ro de colonne de la case vide
	 */
	public int getYvide() {
		return yvide;
	}

	/*
	 *  Renvoie la valeur de la case de coordonn�es i, j
	 */
	public int getValue(int i,int j)
	{
		return tableau[i][j];
	}

	/*
	 * M�lange du tableau
	 */
	public void melanger(){
		Random generator = new Random(); 
		
		// D�placement al�atoire de la case vide 99 fois
		for (int rep=1;rep<100;rep++)
		{

			int i = generator.nextInt(4) + 1;
			if (i==1)
				deplacerPiece(xvide+1,yvide);
			else if (i==2)
				deplacerPiece(xvide-1,yvide);
			else if (i==3)
				deplacerPiece(xvide,yvide-1);
			else if (i==4)
				deplacerPiece(xvide,yvide+1);

		}
		/*boolean 
		Integer[] array = new Integer[X*Y];

		int z = 0;
		// conversion en un tableau � 1 dimension
		for (int i=0; i< X; i++){
			for (int j=0; j< Y; j++){
				array[z] = tableau[i][j];
				z++;
			}
		}

		// M�lange des cases du plateau
		Collections.shuffle(Arrays.asList(array));

		// Reconversion en un tableau � 2 dimensions
		for (int j=0; j<X; j++){
			for (int k=0; k<Y; k++){
				tableau[j][k]=array[k+j*Y];
				System.out.print(tableau[j][k]+"   ");
			}
			System.out.println();
		}
		System.out.println();*/
	}


	/*
	 * D�placement d'une pi�ce
	 */
	public boolean deplacerPiece(int x, int y){
		int x0=xvide ,y0=yvide;

		// V�rifie si la case peut �tre deplac�e
		if ( 
				(x>=0) && (x<X) && (y>=0) && (y<Y) && 
				(   
						( (x==x0) && ( (y==y0-1) || (y==y0+1)  ) )
						||( (y==y0)  && ( (x==x0-1) || (x==x0+1)  ) )
						)

				){

			// D�place la case
			int a = tableau[x][y];
			tableau[x][y] = tableau[x0][y0];
			tableau[x0][y0] = a;
			xvide = x;
			yvide = y;
			for (int j=0; j<X; j++){
				for (int k=0; k<Y; k++){
					System.out.print(tableau[j][k]+"   ");
				}
				System.out.println();
			}
			
			// Mise � jour de la vue
			setChanged();
			notifyObservers(this.tableau);

			return true;
		}
		return false;
	}

	/*
	 * V�rifie si le jeu est termin�
	 */
	public boolean jeuTermine(){
		boolean fini = false;
		// Compare les tableaux 
		fini = ArrayUtils.isEquals(reference, tableau);
		return(fini); 
	}
}

