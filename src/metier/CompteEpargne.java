package metier;
import metier.Compte;

public class CompteEpargne extends Compte {
    private double tauxInteret; 
    
    public CompteEpargne(String code, double soldeInitial, double tauxInteret) {
        super(code, soldeInitial);
        this.tauxInteret = tauxInteret;
    }
    
    public double getTauxInteret() { return tauxInteret; }
    public void setTauxInteret(double tauxInteret) { this.tauxInteret = tauxInteret; }
    
    @Override
    public boolean retirer(double montant, String destination) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit etre positif");
        }
        
        if (solde < montant) {
            return false; 
        }
        
        solde -= montant;
        Retrait retrait = new Retrait(montant, destination);
        listeOperations.add(retrait);
        return true;
    }
    
    @Override
    public double calculerInteret() {
        return solde * (tauxInteret / 100);
    }
    
    @Override
    public void afficherDetails() {
        System.out.println("=== DETAILS DU COMPTE EPARGNE ===");
        System.out.println("Code: " + code);
        System.out.println("Solde: " + String.format("%.2f DH", solde));
        System.out.println("Taux d'interait: " + String.format("%.2f%%", tauxInteret));
        System.out.println("Interaits potentiels: " + String.format("%.2f DH", calculerInteret()));
        System.out.println("Nombre d'operations: " + listeOperations.size());
    }
    
    @Override
    public String getTypeCompte() {
        return "Compte Epargne";
    }
}

