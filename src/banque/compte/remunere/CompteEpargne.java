package banque.compte.remunere;

import banque.Client;
import banque.exception.OperationBancaireException;

/**
 * Created by Ronan Timinello.
 */
public class CompteEpargne extends CompteRemunere {
    public CompteEpargne(String libelle, Client titulaire, int tauxRemuneration, int soldePlafond) {
        super(libelle, titulaire, tauxRemuneration, soldePlafond);
    }

    @Override
    public String toString() {
        return super.toString() +
                '}';
    }
}
