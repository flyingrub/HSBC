package banque.compte.remunere;

import banque.client.Client;

import java.util.Date;

/**
 * Created by Ronan Timinello.
 */
public class PlanEpargneBloque extends CompteRemunere {
    private Date miseADispoition;

    public PlanEpargneBloque(String libelle, Client titulaire, int tauxRemuneration, int soldePlafond, Date miseADispoition) {
        super(libelle, titulaire, tauxRemuneration, soldePlafond);
        this.miseADispoition = miseADispoition;
    }

    @Override
    public String toString() {
        return super.toString() + ", " +
                "miseADispoition=" + miseADispoition +
                '}';
    }
}
