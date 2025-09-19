package metier;
import java.util.ArrayList;
import java.util.List;


public abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> listeOperations;
    
    public Compte(String code, double soldeInitial) {
        this.code = code;
        this.solde = soldeInitial;
        this.listeOperations = new ArrayList<>();
        
        if (soldeInitial > 0) {
            Versement ouverture = new Versement(soldeInitial, "Ouverture de compte");
            this.listeOperations.add(ouverture);
        }
    }
    
    public String getCode() { return code; }
    public double getSolde() { return solde; }
    public List<Operation> getListeOperations() { return new ArrayList<>(listeOperations); }
    
    public void verser(double montant, String source) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        
        solde += montant;
        Versement versement = new Versement(montant, source);
        listeOperations.add(versement);
    }
    
    public abstract boolean retirer(double montant, String destination);
    public abstract double calculerInteret();
    public abstract void afficherDetails();
    public abstract String getTypeCompte();
}