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
    
    
}
