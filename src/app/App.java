package app;

import ui.MainMenu;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Systeme de Gestion de Comptes Bancaires ===");
        System.out.println("Bienvenue dans votre banque digitale\n");
        
        try {
            MainMenu menu = new MainMenu();
            menu.afficherMenu();
        } catch (Exception e) {
            System.err.println("Erreur lors du demarrage de l'application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}