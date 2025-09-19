package metier;

import metier.Compte;


public class CompteCourant extends Compte {
    private double decouvert; 
    
    public CompteCourant(String code, double soldeInitial, double decouvert) {
        super(code, soldeInitial);
        this.decouvert = Math.abs(decouvert); 
    }
    
    public double getDecouvert() { return decouvert; }
    public void setDecouvert(double decouvert) { this.decouvert = Math.abs(decouvert); }
    @Override
    public boolean retirer(double montant, String destination) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit etre positif");
        }
        
        double soldeApresRetrait = solde - montant;
        if (soldeApresRetrait < -decouvert) {
            return false; 
        }
        
        solde = soldeApresRetrait;
        Retrait retrait = new Retrait(montant, destination);
        listeOperations.add(retrait);
        return true;
    }
    
    @Override
    public double calculerInteret() {
        return 0; 
    }
    
    @Override
    public void afficherDetails() {
        System.out.println("=== DETAILS DU COMPTE COURANT ===");
        System.out.println("Code: " + code);
        System.out.println("Solde: " + String.format("%.2f DH", solde));
        System.out.println("DEcouvert autorisE: " + String.format("%.2f DH", decouvert));
        System.out.println("Solde disponible: " + String.format("%.2f DH", solde + decouvert));
        System.out.println("Nombre d'opErations: " + listeOperations.size());
    }
    
    @Override
    public String getTypeCompte() {
        return "Compte Courant";
    }
    
}
