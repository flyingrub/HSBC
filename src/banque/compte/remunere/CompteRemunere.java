package banque.compte.remunere;

import banque.Client;
import banque.enumP.Nature;
import banque.Operation;
import banque.enumP.Status;
import banque.compte.Compte;
import banque.exception.OperationBancaireException;

/**
 * Created by Ronan Timinello.
 */
abstract public class CompteRemunere extends Compte {
    private int tauxRemuneration;
    private double soldePlafond;

    /** Constructeur
     * @param libelle le libelle du compte
     * @param titulaire le titulaire du compte
     * @param tauxRemuneration le taux de remuneration du compte
     * @param soldePlafond le solde de plafond du compte
     */
    public CompteRemunere(String libelle, Client titulaire, int tauxRemuneration, int soldePlafond) {
        super(libelle, titulaire);
        this.tauxRemuneration = tauxRemuneration;
        this.soldePlafond = soldePlafond;
    }

    /**
     * @return le solde plafond du compte
     */
    public double getSoldePlafond() {
        return soldePlafond;
    }

    /** Permet de crediter un compte
     * @param montant le montant à crediter
     * @throws OperationBancaireException si le plafond est insuffisant ou le montant invalide
     */
    @Override
    public void crediter(double montant) throws OperationBancaireException {
        if (this.getSolde() + montant > soldePlafond){
            this.ajoutOperation(new Operation(Nature.CREDIT, Status.KO, this, this.getTitulaire(), montant));
            throw new OperationBancaireException(this.getLibelle() + " Plafond insuffisant");
        }
        super.crediter(montant);
    }

    /** Permet de crediter un compte a partir d'une operation
     * @param operation operation a partir de laquelle on realise le credit
     * @return vrai si l'operation reussi, faux dans le cas contraire
     */
    public boolean crediter(Operation operation){
        this.ajoutOperation(operation);
        if (operation.getMontant() <= 0){
            operation.setStatus(Status.KO);
            return false;
        }
        operation.setStatus(Status.OK);
        this.setSolde(getSolde() + operation.getMontant());
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", " +
                "tauxRemuneration=" + tauxRemuneration +
                ", soldePlafond=" + soldePlafond;
    }
}
