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
    private int soldePlafond;

    public CompteRemunere(String libelle, Client titulaire, int tauxRemuneration, int soldePlafond) {
        super(libelle, titulaire);
        this.tauxRemuneration = tauxRemuneration;
        this.soldePlafond = soldePlafond;
    }

    public int getSoldePlafond() {
        return soldePlafond;
    }

    @Override
    public void crediter(int montant) throws OperationBancaireException {
        if (this.getSolde() + montant > soldePlafond){
            this.ajoutOperation(new Operation(Nature.CREDIT, Status.KO, this, this.getTitulaire(), montant));
            throw new OperationBancaireException(this.getLibelle() + " Plafond insuffisant");
        }
        super.crediter(montant);
    }

    @Override
    public String toString() {
        return super.toString() + ", " +
                "tauxRemuneration=" + tauxRemuneration +
                ", soldePlafond=" + soldePlafond;
    }
}
