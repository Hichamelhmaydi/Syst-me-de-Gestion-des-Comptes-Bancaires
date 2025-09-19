package metier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BanqueService {
    private Map<String, Compte> comptes;
    private int numeroSequence;
    
    public BanqueService() {
        this.comptes = new HashMap<>();
        this.numeroSequence = 1;
    }
    
   
    private String genererCodeCompte() {
        return String.format("CPT-%05d", numeroSequence++);
    }
    
 
    public String creerCompteCourant(double soldeInitial, double decouvert) {
        String code = genererCodeCompte();
        CompteCourant compte = new CompteCourant(code, soldeInitial, decouvert);
        comptes.put(code, compte);
        return code;
    }
    

    public String creerCompteEpargne(double soldeInitial, double tauxInteret) {
        String code = genererCodeCompte();
        CompteEpargne compte = new CompteEpargne(code, soldeInitial, tauxInteret);
        comptes.put(code, compte);
        return code;
    }
    
    
    public Compte rechercherCompte(String code) {
        return comptes.get(code);
    }
 
    public boolean effectuerVersement(String codeCompte, double montant, String source) {
        try {
            Compte compte = rechercherCompte(codeCompte);
            if (compte == null) {
                return false;
            }
            
            compte.verser(montant, source);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public boolean effectuerRetrait(String codeCompte, double montant, String destination) {
        Compte compte = rechercherCompte(codeCompte);
        if (compte == null) {
            return false;
        }
        
        return compte.retirer(montant, destination);
    }
    
  
    public boolean effectuerVirement(String codeSource, String codeDestination, double montant) {
        Compte compteSource = rechercherCompte(codeSource);
        Compte compteDestination = rechercherCompte(codeDestination);
        
        if (compteSource == null || compteDestination == null) {
            return false;
        }
        
        if (compteSource == compteDestination) {
            return false; 
        }
        
        if (compteSource.retirer(montant, "Virement vers " + codeDestination)) {
            compteDestination.verser(montant, "Virement de " + codeSource);
            return true;
        }
        
        return false;
    }
    
 
    public List<Compte> listerComptes() {
        return new ArrayList<>(comptes.values());
    }
    

    public int getNombreComptes() {
        return comptes.size();
    }
}
