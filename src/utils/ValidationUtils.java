package utils;

import java.util.regex.Pattern;


public class ValidationUtils {
    private static final Pattern PATTERN_CODE_COMPTE = Pattern.compile("^CPT-\\d{5}$");
    
   
    public static boolean validerCodeCompte(String code) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        return PATTERN_CODE_COMPTE.matcher(code.trim().toUpperCase()).matches();
    }
    

    public static boolean validerMontantPositif(double montant) {
        return montant > 0;
    }
    
 
    public static boolean validerMontantPositifOuNul(double montant) {
        return montant >= 0;
    }
    
 
    public static boolean validerChainNonVide(String chaine) {
        return chaine != null && !chaine.trim().isEmpty();
    }
    

    public static boolean validerTauxInteret(double taux) {
        return taux >= 0 && taux <= 100;
    }
    

    public static String formaterMontant(double montant) {
        return String.format("%,.2f MAD", montant); 
    }


    public static Double parseDouble(String str) {
        try {
            return Double.valueOf(str.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            return null;
        }
    }

}