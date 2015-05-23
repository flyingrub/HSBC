package banque.compte.remunere;

import banque.client.Client;
import banque.compte.Compte;

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

    @Override
    public String toString() {
        return super.toString() + ", " +
                "tauxRemuneration=" + tauxRemuneration +
                ", soldePlafond=" + soldePlafond;
    }
}