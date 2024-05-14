package m;
import java.io.Serializable;
public class Poireau extends Legume implements Serializable {

	
	
	public Poireau() {
		super("Poireau", 5, 2, 3, 2);
		
	}

	@Override
	public String toString() {
		return "Poireau [nom=" + Nom + ", dureeDeLaPoussePourMaturite=" + dureeDeLaPoussePourMaturite
				+ ", quantiteObtenuLorsDeLaRecolte=" + quantiteObtenuLorsDeLaRecolte + ", niveauDeMaturite="
				+ niveauDeMaturite + ", prixDeVente=" + prixdevente + ", prixPourPlanter=" + prixpourplanter + "]";
	}

	

	
	
	
}