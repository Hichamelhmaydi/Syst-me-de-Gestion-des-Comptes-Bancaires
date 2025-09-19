package metier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public abstract class Operation {
    protected String numero;
    protected LocalDateTime date;
    protected double montant;
    
    public Operation(double montant) {
        this.numero = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.montant = montant;
    }
    
    public String getNumero() { return numero; }
    public LocalDateTime getDate() { return date; }
    public double getMontant() { return montant; }
    
 
    public abstract String getDescription();
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("[%s] %s - %.2f DH - %s", 
            date.format(formatter), 
            getClass().getSimpleName(), 
            montant, 
            getDescription());
    }
}