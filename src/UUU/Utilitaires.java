package UUU;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import m.Aliment;
import p.Potager;

public class Utilitaires {

    private static Scanner scanner = new Scanner(System.in);

    public static int readInt() {
        while (!scanner.hasNextInt()) {
            scanner.next(); // Read and discard unwanted input
            System.out.println("Veuillez entrer un nombre entier valide :");
        }
        int number = scanner.nextInt();
        return number;
    }

    public static void printHashMap(HashMap<Aliment, Integer> gardeManger) {
        if (gardeManger.isEmpty()) {
            System.out.println("Le garde-manger est vide.");
        } else {
            System.out.println("Contenu du garde-manger:");
            for (Entry<Aliment, Integer> entry : gardeManger.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " unité(s)");
            }
        }
    }

    // Updating printArrayList to indicate maturity state of each aliment
    public static void printArrayList(ArrayList<Aliment> potager) {
        if (potager.isEmpty()) {
            System.out.println("Le potager est vide.");
        } else {
            System.out.println("État du potager:");
            for (Aliment aliment : potager) {
                char displayChar = aliment.isMature() ? Character.toUpperCase(aliment.toString().charAt(0)) : Character.toLowerCase(aliment.toString().charAt(0));
                System.out.println(displayChar + aliment.toString().substring(1));
            }
        }
    }
    
    public static void printPotager(ArrayList<Aliment> potager) {
        if (potager.isEmpty()) {
            System.out.println("Le potager est vide.");
        } else {
            System.out.println("État du potager:");
            for (Aliment aliment : potager) {
                char displayChar = aliment.isMature() ? Character.toUpperCase(aliment.getNom().charAt(0)) : Character.toLowerCase(aliment.getNom().charAt(0));
                System.out.println(displayChar + aliment.getNom().substring(1));
            }
        }
    }

    public static void vendreProduce(HashMap<Aliment, Integer> gardeManger, Potager potager) {
        if (gardeManger.isEmpty()) {
            System.out.println("Le garde-manger est vide, rien à vendre.");
        } else {
            float revenus = 0;
            for (Entry<Aliment, Integer> entry : gardeManger.entrySet()) {
                Aliment aliment = entry.getKey();
                int quantite = entry.getValue();
                revenus += quantite * aliment.prixdevente;
                System.out.println("Vendu " + quantite + " unité(s) de " + aliment.getNom() + " pour " + (quantite * aliment.prixdevente) + "€");
            }
            potager.argentDisponible += revenus;
            gardeManger.clear(); // Vide le garde-manger après la vente
            System.out.println("Total des revenus: " + revenus + "€");
            System.out.println("Argent disponible: " + potager.argentDisponible + "€");
        }
    }

    public static void croissance(ArrayList<Aliment> potager) {
        ArrayList<Aliment> aRetirer = new ArrayList<>();
        for (Aliment aliment : potager) {
            aliment.setMomentPousse(aliment.getMomentPousse() + 1); // Augmente la maturité
            if (aliment.getMomentPousse() > aliment.getDureePousse()) {
                aRetirer.add(aliment);
                System.out.println(aliment.getNom() + " est trop mûr et a été retiré du potager.");
            }
        }
        potager.removeAll(aRetirer);
    }

    private void sauvegarderPotager() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("potager.dat"))) {
            oos.defaultWriteObject();
            oos.writeFloat(0);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

}
