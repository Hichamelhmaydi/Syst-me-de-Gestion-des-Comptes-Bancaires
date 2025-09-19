package ui;
import metier.BanqueService;
import metier.Compte;
import metier.Operation;
import utils.ValidationUtils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class MainMenu {
    private BanqueService banqueService;
    private Scanner scanner;
    
    public MainMenu() {
        this.banqueService = new BanqueService();
        this.scanner = new Scanner(System.in);
    }
    
 
    public void afficherMenu() {
        boolean continuer = true;
        
        while (continuer) {
            try {
                afficherOptionsMenu();
                int choix = lireChoixUtilisateur();
                continuer = traiterChoix(choix);
            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
                scanner.nextLine(); 
            }
            
            if (continuer) {
                System.out.println("\nAppuyez sur Entree pour continuer...");
                scanner.nextLine();
            }
        }
        
        System.out.println("Merci d'avoir utilise notre systeme bancaire. Au revoir!");
        scanner.close();
    }
    
    private void afficherOptionsMenu() {
    	System.out.println(new String(new char[50]).replace("\0", "="));
        System.out.println("           MENU PRINCIPAL");
        System.out.println(new String(new char[50]).replace("\0", "="));
        System.out.println("1. Creer un compte courant");
        System.out.println("2. Creer un compte epargne");
        System.out.println("3. Effectuer un versement");
        System.out.println("4. Effectuer un retrait");
        System.out.println("5. Effectuer un virement");
        System.out.println("6. Consulter le solde d'un compte");
        System.out.println("7. Consulter les operations d'un compte");
        System.out.println("8. Lister tous les comptes");
        System.out.println("9. Afficher les details d'un compte");
        System.out.println("0. Quitter");
        System.out.println(new String(new char[50]).replace("\0", "="));
        System.out.print("Votre choix: ");
    }
    
    private int lireChoixUtilisateur() {
        try {
            int choix = scanner.nextInt();
            scanner.nextLine(); 
            return choix;
        } catch (InputMismatchException e) {
            scanner.nextLine(); 
            throw new IllegalArgumentException("Veuillez saisir un nombre valide");
        }
    }
    
    private boolean traiterChoix(int choix) {
        switch (choix) {
            case 1: creerCompteCourant(); break;
            case 2: creerCompteEpargne(); break;
            case 3: effectuerVersement(); break;
            case 4: effectuerRetrait(); break;
            case 5: effectuerVirement(); break;
            case 6: consulterSolde(); break;
            case 7: consulterOperations(); break;
            case 8: listerComptes(); break;
            case 9: afficherDetailsCompte(); break;
            case 0: return false;
            default: System.out.println("Choix invalide");
        }
        return true;
    }
    
    private void creerCompteCourant() {
        System.out.println("\n=== CREATION D'UN COMPTE COURANT ===");
        
        try {
            System.out.print("Solde initial: ");
            double soldeInitial = lireDouble();
            
            if (!ValidationUtils.validerMontantPositifOuNul(soldeInitial)) {
                System.out.println("Le solde initial doit etre positif ou nul.");
                return;
            }
            
            System.out.print("Decouvert : ");
            double decouvert = lireDouble();
            
            if (!ValidationUtils.validerMontantPositifOuNul(decouvert)) {
                System.out.println("Le decouvert doit etre positif ou nul.");
                return;
            }
            
            String codeCompte = banqueService.creerCompteCourant(soldeInitial, decouvert);
            System.out.println(" Compte courant cree avec succes");
            System.out.println("Code du compte: " + codeCompte);
            System.out.println("Solde initial: " + ValidationUtils.formaterMontant(soldeInitial));
            System.out.println("Decouvert aetorise: " + ValidationUtils.formaterMontant(decouvert));
            
        } catch (Exception e) {
            System.out.println("Erreur lors de la creation du compte: " + e.getMessage());
        }
    }
    
    private void creerCompteEpargne() {
        System.out.println("\n=== CREATION D'UN COMPTE EPARGNE ===");
        
        try {
            System.out.print("Solde initial: ");
            double soldeInitial = lireDouble();
            
            if (!ValidationUtils.validerMontantPositifOuNul(soldeInitial)) {
                System.out.println("Le solde initial doit etre positif ou nul.");
                return;
            }
            
            System.out.print("Taux d'interet (%): ");
            double tauxInteret = lireDouble();
            
            if (!ValidationUtils.validerTauxInteret(tauxInteret)) {
                System.out.println("Le taux d'interet doit etre entre 0 et 100%.");
                return;
            }
            
            String codeCompte = banqueService.creerCompteEpargne(soldeInitial, tauxInteret);
            System.out.println("Compte epargne cree avec succès!");
            System.out.println("Code du compte: " + codeCompte);
            System.out.println("Solde initial: " + ValidationUtils.formaterMontant(soldeInitial));
            System.out.println("Taux d'interet: " + String.format("%.2f%%", tauxInteret));
            
        } catch (Exception e) {
            System.out.println("Erreur lors de la creation du compte: " + e.getMessage());
        }
    }
    
    private void effectuerVersement() {
        System.out.println("\n=== EFFECTUER UN VERSEMENT ===");
        
        try {
            System.out.print("Code du compte: ");
            String codeCompte = scanner.nextLine().trim().toUpperCase();
            
            Compte compte = banqueService.rechercherCompte(codeCompte);
            if (compte == null) {
                System.out.println("Compte introuvable.");
                return;
            }
            
            System.out.print("Montant a verser: ");
            double montant = lireDouble();
            
            if (!ValidationUtils.validerMontantPositif(montant)) {
                System.out.println("Le montant doit etre positif.");
                return;
            }
            
            System.out.print("Source du versement: ");
            String source = scanner.nextLine().trim();
            
            if (!ValidationUtils.validerChainNonVide(source)) {
                System.out.println("La source ne peut pas etre vide.");
                return;
            }
            
            if (banqueService.effectuerVersement(codeCompte, montant, source)) {
                System.out.println("Versement effectue avec succes");
                System.out.println("Nouveau solde: " + ValidationUtils.formaterMontant(compte.getSolde()));
            } else {
                System.out.println("echec du versement.");
            }
            
        } catch (Exception e) {
            System.out.println("Erreur lors du versement: " + e.getMessage());
        }
    }
    
    private void effectuerRetrait() {
        System.out.println("\n=== EFFECTUER UN RETRAIT ===");
        
        try {
            System.out.print("Code du compte: ");
            String codeCompte = scanner.nextLine().trim().toUpperCase();
            
            Compte compte = banqueService.rechercherCompte(codeCompte);
            if (compte == null) {
                System.out.println("Compte introuvable.");
                return;
            }
            
            System.out.println("Solde actuel: " + ValidationUtils.formaterMontant(compte.getSolde()));
            
            System.out.print("Montant a retirer: ");
            double montant = lireDouble();
            
            if (!ValidationUtils.validerMontantPositif(montant)) {
                System.out.println("Le montant doit etre positif.");
                return;
            }
            
            System.out.print("Destination du retrait: ");
            String destination = scanner.nextLine().trim();
            
            if (!ValidationUtils.validerChainNonVide(destination)) {
                System.out.println("La destination ne peut pas etre vide.");
                return;
            }
            
            if (banqueService.effectuerRetrait(codeCompte, montant, destination)) {
                System.out.println(" Retrait effectue avec succes");
                System.out.println("Nouveau solde: " + ValidationUtils.formaterMontant(compte.getSolde()));
            } else {
                System.out.println("Retrait impossible (solde insuffisant ou limite de decouvert atteinte).");
            }
            
        } catch (Exception e) {
            System.out.println("Erreur lors du retrait: " + e.getMessage());
        }
    }
    
    private void effectuerVirement() {
        System.out.println("\n=== EFFECTUER UN VIREMENT ===");
        
        try {
            System.out.print("Code du compte source: ");
            String codeSource = scanner.nextLine().trim().toUpperCase();
            
            System.out.print("Code du compte destination: ");
            String codeDestination = scanner.nextLine().trim().toUpperCase();
            
            if (codeSource.equals(codeDestination)) {
                System.out.println("Les comptes source et destination ne peuvent pas etre identiques.");
                return;
            }
            
            Compte compteSource = banqueService.rechercherCompte(codeSource);
            Compte compteDestination = banqueService.rechercherCompte(codeDestination);
            
            if (compteSource == null) {
                System.out.println("Compte source introuvable.");
                return;
            }
            
            if (compteDestination == null) {
                System.out.println("Compte destination introuvable.");
                return;
            }
            
            System.out.println("Solde compte source: " + ValidationUtils.formaterMontant(compteSource.getSolde()));
            System.out.println("Solde compte destination: " + ValidationUtils.formaterMontant(compteDestination.getSolde()));
            
            System.out.print("Montant a virer: ");
            double montant = lireDouble();
            
            if (!ValidationUtils.validerMontantPositif(montant)) {
                System.out.println("Le montant doit etre positif.");
                return;
            }
            
            if (banqueService.effectuerVirement(codeSource, codeDestination, montant)) {
                System.out.println(" Virement effectue avec succes!");
                System.out.println("Nouveau solde compte source: " + ValidationUtils.formaterMontant(compteSource.getSolde()));
                System.out.println("Nouveau solde compte destination: " + ValidationUtils.formaterMontant(compteDestination.getSolde()));
            } else {
                System.out.println(" Virement impossible (solde insuffisant ou limite de decouvert atteinte).");
            }
            
        } catch (Exception e) {
            System.out.println("Erreur lors du virement: " + e.getMessage());
        }
    }
    
    private void consulterSolde() {
        System.out.println("\n=== CONSULTATION DE SOLDE ===");
        
        try {
            System.out.print("Code du compte: ");
            String codeCompte = scanner.nextLine().trim().toUpperCase();
            
            Compte compte = banqueService.rechercherCompte(codeCompte);
            if (compte == null) {
                System.out.println("Compte introuvable.");
                return;
            }
            
            System.out.println("Informations du compte:");
            System.out.println("Type: " + compte.getTypeCompte());
            System.out.println("Code: " + compte.getCode());
            System.out.println("Solde: " + ValidationUtils.formaterMontant(compte.getSolde()));
            
            double interets = compte.calculerInteret();
            if (interets > 0) {
                System.out.println("Interets potentiels: " + ValidationUtils.formaterMontant(interets));
            }
            
        } catch (Exception e) {
            System.out.println("Erreur lors de la consultation: " + e.getMessage());
        }
    }
    
    private void consulterOperations() {
        System.out.println("\n=== CONSULTATION DES OPERATIONS ===");
        
        try {
            System.out.print("Code du compte: ");
            String codeCompte = scanner.nextLine().trim().toUpperCase();
            
            Compte compte = banqueService.rechercherCompte(codeCompte);
            if (compte == null) {
                System.out.println("Compte introuvable.");
                return;
            }
            
            List<Operation> operations = compte.getListeOperations();
            
            if (operations.isEmpty()) {
                System.out.println("Aucune operation trouvee pour ce compte.");
                return;
            }
            
            System.out.println("\nHistorique des operations pour le compte " + codeCompte + ":");
            System.out.println(new String(new char[80]).replace("\0", "="));
            
            for (int i = operations.size() - 1; i >= 0; i--) {
                Operation operation = operations.get(i);
                System.out.println((operations.size() - i) + ". " + operation.toString());
            }
            
            System.out.println(new String(new char[80]).replace("\0", "="));
            System.out.println("Total des operations: " + operations.size());
            
        } catch (Exception e) {
            System.out.println("Erreur lors de la consultation: " + e.getMessage());
        }
    }
    
    private void listerComptes() {
        System.out.println("\n=== LISTE DE TOUS LES COMPTES ===");
        
        List<Compte> comptes = banqueService.listerComptes();
        
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte n'a ete cree.");
            return;
        }
        
        System.out.println("Nombre total de comptes: " + comptes.size());
        System.out.println(new String(new char[70]).replace("\0", "="));
        
        for (Compte compte : comptes) {
            System.out.printf("%-15s | %-15s | %15s%n", 
                compte.getCode(), 
                compte.getTypeCompte(), 
                ValidationUtils.formaterMontant(compte.getSolde()));
        }
        
        System.out.println(new String(new char[70]).replace("\0", "="));
    }
    
    private void afficherDetailsCompte() {
        System.out.println("\n=== DETAILS D'UN COMPTE ===");
        
        try {
            System.out.print("Code du compte: ");
            String codeCompte = scanner.nextLine().trim().toUpperCase();
            
            Compte compte = banqueService.rechercherCompte(codeCompte);
            if (compte == null) {
                System.out.println("Compte introuvable.");
                return;
            }
            
            compte.afficherDetails();
            
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage des details: " + e.getMessage());
        }
    }
    
    private double lireDouble() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                Double valeur = ValidationUtils.parseDouble(input);
                if (valeur != null) {
                	return valeur.doubleValue(); 
                } else {
                    System.out.print("Valeur invalide. Veuillez saisir un nombre: ");
                }
            } catch (Exception e) {
                System.out.print("Erreur de saisie. Veuillez reessayer: ");
            }
        }
    }
}