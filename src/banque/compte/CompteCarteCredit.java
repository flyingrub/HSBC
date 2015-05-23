package banque.compte;

import banque.client.Client;

/**
 * Created by Ronan Timinello.
 */
public class CompteCarteCredit extends Compte {
    public CompteCarteCredit(String libelle, Client titulaire) {
        super(libelle, titulaire);
    }

    @Override
    public String toString() {
        return super.toString() +
                '}';
    }
}
