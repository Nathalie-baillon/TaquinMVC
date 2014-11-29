package Vue;
import java.awt.*;
import javax.swing.*;

import java.awt.image.*;

/* utilise:
*  Toolkit.getImage()
*  Image.getScaledInstance()
*  ImageProducer
*  CropImageFilter
*  FilteredImageSource(ImageProducer, CropImageFilter)
*  Toolkit.createImage(FilteredImageSource)
*/

public class ImageCutter {
	
	Toolkit tk;
	ImageIcon img;

	/**
	 * Constructeur
	 * @param chemin chemin du fichier
	 */
	public ImageCutter(String chemin) {
		tk = Toolkit.getDefaultToolkit();
		img = new ImageIcon(tk.getImage(chemin));
    }

	/**
	 * Constructeur
	 * @param chemin chemin du fichier
	 * @param width largeur de l'image
	 * @param height longueur de l'image
	 */
	public ImageCutter(String chemin, int width, int height) {
		tk = Toolkit.getDefaultToolkit();
		Image src = tk.getImage(chemin);
		img = new ImageIcon(src.getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }

	/**
	 * Fournit un objet ImageIcon a une position donnee issu du decoupage de l'image
	 * @param i numero de la ligne
	 * @param j numero de la colonne
	 * @param largeur largeur d'une unite d'image issue du decoupage
	 * @param hauteur hauteur d'une unite d'image issue du decoupage
	 * @return ImageIcon
	 */
    public ImageIcon getIcon(int i, int j, int largeur, int hauteur) {
	    ImageProducer ip = img.getImage().getSource();
	    CropImageFilter cif = new CropImageFilter(j * largeur, i * hauteur, largeur , hauteur);
	    FilteredImageSource fis = new FilteredImageSource(ip, cif);
	    Image img2 = tk.createImage(fis);
	    return new ImageIcon(img2);
    }

	/**
	 * Decoupe l'image en un tableau d'objets ImageIcon
	 * @param nl nombre de lignes
	 * @param nc nombre de colonnes
	 * @return ImageIcon[][]
	 */
    public ImageIcon[][] divide(int nl, int nc) {
	    int largeur = img.getIconWidth() / nc;
	    int hauteur = img.getIconHeight() / nl;
	    ImageIcon[][] res = new ImageIcon[nl][nc];
	    for (int i = 0 ; i < nl; ++i) {
		    for (int j = 0; j < nc; ++j) {
		    	res[i][j] = getIcon(i, j, largeur, hauteur);
		    }
	    }
	    return res;
    }

	/**
	 * Fonction Main pour donner un exemple de scenario
	 * @param args
	 */
    public static void main(String[] args) {
	    // creation des images
	    final int nl = 4; //nombre lignes
	    final int nc = 4; //nombre colonnes
	    final int tailleDisplayX = 500;
	    final int tailleDisplayY = 500;
	    ImageCutter vi = new ImageCutter("image.jpg", tailleDisplayX,tailleDisplayY);
	    ImageIcon[][] icones = vi.divide(nl, nc);
	    
	    // creation des boutons
	    JFrame jf = new JFrame();
	    jf.setLayout(new GridLayout(nl, nc));
	    JButton[][] jbs = new JButton[nl][nc];
	    Insets insets = new Insets(0, 0, 0, 0);
	    for (int i = 0 ; i < nl; ++i) {
		    for (int j = 0; j < nc; ++j) {
			    jbs[i][j] = new JButton(icones[i][j]);
			    jbs[i][j].setMargin(insets);
			    jf.add(jbs[i][j]);
		    }
	    }
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.pack();             // calcule la taille de la fenetre
	    jf.setVisible(true);
	    // A ce stade l'application ne doit plus intervenir sur la fenetre
	    // pour ne pas creer des problemes d'affichage
    }
    
}