package p;

import java.util.ArrayList;
import java.util.HashMap;

import UUU.Utilitaires;

import java.io.*;
import m.Aliment;
import m.Pdt;
import m.Carotte;
import m.Poireau;
import m.Pomme;

public class Potager {

	public HashMap<Aliment, Integer> gardeManger = new HashMap<Aliment, Integer>();
	public ArrayList<Aliment> potager = new ArrayList<Aliment>();
	public float argentDisponible = 10;

	public void cestparti() {
		
		System.out.println("Bonjour");
		System.out.println("Bienvenue dans votre potager!");

		boolean appliOn = true;
		
		do {
			System.out.println("Choix :");
			System.out.println("1 Planter");
			System.out.println("2 Etat du potager");
			System.out.println("3 Recolter");
			System.out.println("4 Vendre au marché");
			System.out.println("5 Sauvegarder");
			System.out.println("6 Exit");

			int choix = Utilitaires.readInt();
			
			switch (choix) {
			case 1:
				System.out.println("Que voulez-vous planter?"
						+ " 1 Pdt, 2 Carotte, 3 Poireau, 4 Pomme");
				int typePlante = Utilitaires.readInt();
				switch (typePlante) {
					case 1:
						potager.add(new Pdt());
						break;
					case 2:
						potager.add(new Carotte());
						break;
					case 3:
						potager.add(new Poireau());
						break;
					case 4:
						potager.add(new Pomme());
						break;
					default:
						System.out.println("Selection non valide.");
				}
				break;
			case 2:
				Utilitaires.printPotager(potager);
				break;
			case 3:
				System.out.print("Quel fruit ou legume voulez vous recolter?");
				int idx = Utilitaires.readInt() - 1;  // Assume input is 1-indexed
				if (idx >= 0 && idx < potager.size()) {
					gardeManger.put(potager.get(idx), gardeManger.getOrDefault(potager.get(idx), 0) + 1);
					potager.remove(idx);
				}
				break;
			case 4 : 
				System.out.println("Vente au marché");
				Utilitaires.vendreProduce(gardeManger, this); // Assumes this function adjusts the 'gardeManger' and 'argentDisponible'
				break;
			
			case 5:
				System.out.println("Sauvegarde dans un fichier");
				sauvegarderPotager();
				break;
			case 6:
				appliOn = false;
				break;
			default:
				System.out.println("Choix invalide, veuillez réessayer.");
			}
			
			Utilitaires.croissance(potager); // Updates growth stages and removes overripe items
		} while (appliOn);
	}

	private void sauvegarderPotager() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("potager.dat"))) {
			oos.writeObject(potager);
			oos.writeObject(gardeManger);
			oos.writeFloat(argentDisponible);
		} catch (IOException e) {
			System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
		}
	}
}
