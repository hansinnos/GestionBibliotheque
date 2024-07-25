package com.yourlibrary.services;
import com.yourlibrary.models.*;
import java.util.HashMap;
import java.util.Scanner;
import com.yourlibrary.exceptions.BookNotFoundException;
public class Bibliotheque {
    private HashMap<Integer, Livre> livres = new HashMap<>();
    private int idCourant = 1;

    public Bibliotheque(){
        this.livres=FileService.loadData();
    }

    public void ajouterLivre(Livre livre) {
        livres.put(livre.getId(), livre);
        System.out.println("Livre ajouté: " + livre);
        FileService.savaData(livres);
    }

    public void supprimerLivre(int id) {
        if (livres.containsKey(id)) {
            livres.remove(id);
            System.out.println("Livre avec ID " + id + " supprimé.");
            FileService.savaData(livres);
        } else {
            System.out.println("Livre avec ID " + id + " non trouvé.");
        }
    }

    public void modifierLivre(int id, String nom, String auteur) throws BookNotFoundException {
        if (livres.containsKey(id)) {
            Livre livre = livres.get(id);
            livre.setNom(nom);
            livre.setAuteur(auteur);
            System.out.println("Livre modifié: " + livre);
            FileService.savaData(livres);
        } else {
            throw new BookNotFoundException("Livre avec ID " + id + " non trouvé.");
        }
    }

    public void rechercherLivreParNom(String nom) {
        livres.values().stream()
                .filter(livre -> livre.getNom().equalsIgnoreCase(nom))
                .forEach(System.out::println);
    }

    public void listerLivresParLettre(char lettre) {
        livres.values().stream()
                .filter(livre -> livre.getNom().toLowerCase().charAt(0) == Character.toLowerCase(lettre))
                .forEach(System.out::println);
    }

    public void afficherNombreDeLivres() {
        System.out.println("Nombre total de livres: " + livres.size());
    }

    public void afficherLivresParCategorie(String categorie) {
        livres.values().stream()
                .filter(livre -> livre.getClass().getSimpleName().equalsIgnoreCase(categorie))
                .forEach(System.out::println);
    }

    public void afficherDetailsLivre(int id) throws BookNotFoundException {
        if (livres.containsKey(id)) {
            System.out.println(livres.get(id));
        } else {
            throw new BookNotFoundException("Livre avec ID " + id + " non trouvé.");
        }
    }

    // Fonction supplémentaire 1 : Lister tous les livres
    public void listerTousLesLivres() {
        livres.values().forEach(System.out::println);
    }

    // Fonction supplémentaire 2 : Rechercher des livres par auteur
    public void rechercherLivresParAuteur(String auteur) {
        livres.values().stream()
                .filter(livre -> livre.getAuteur().equalsIgnoreCase(auteur))
                .forEach(System.out::println);
    }

    public int genererId() {
        return idCourant++;
    }
}
