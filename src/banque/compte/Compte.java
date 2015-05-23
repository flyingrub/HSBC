package banque.compte;

import banque.client.Client;

/**
 * Created by Ronan Timinello.
 */
abstract public class Compte {
    private int numeroId;
    private String libelle;
    private Client titulaire;
    private int solde;
    private static int currentNumeroId;

    public Compte(String libelle, Client titulaire) {
        this.numeroId = ++currentNumeroId;
        this.libelle = libelle;
        this.titulaire = titulaire;
        titulaire.ajouterCompte(this);
    }

    public void debit(int debit){
        this.solde -= debit;
    }

    public void credit(int credit){
        this.solde += credit;
    }

    public int getSolde(){
        return this.solde;
    }

    public int getNumeroId() {
        return numeroId;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +"{" +
                "numeroId=" + numeroId +
                ", libelle='" + libelle + '\'' +
                ", titulaire=" + titulaire.getNom() +
                ", solde=" + solde;
    }
}
