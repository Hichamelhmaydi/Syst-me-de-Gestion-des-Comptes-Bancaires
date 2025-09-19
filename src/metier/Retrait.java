package metier;

public class Retrait extends Operation {
    private String destination;
    
    public Retrait(double montant, String destination) {
        super(montant);
        this.destination = destination;
    }
    
    public String getDestination() { return destination; }
    
    @Override
    public String getDescription() {
        return "Destination: " + destination;
    }
}
