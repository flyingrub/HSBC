package banque;

import banque.compte.Compte;
import banque.enumP.Nature;
import banque.enumP.Status;

import java.util.Date;


/**
 * Created by Ronan Timinello.
 */
public class Operation {
    private Nature nature;
    private Status status;
    private int numeroId;
    private Compte compte;
    private Client client;
    private String libelle;
    private Date date = new Date();
    private int montant;
    private static int currentNumeroId;

    public Operation(Nature nature, Status status, Compte compte, Client client, int montant) {
        this.nature = nature;
        this.status = status;
        this.compte = compte;
        this.client = client;
        this.montant = montant;
        this.numeroId = ++currentNumeroId;
        libelle = "Pas de libellé";
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "nature=" + nature +
                ", status=" + status +
                ", numeroId=" + numeroId +
                ", compte=" + compte.getLibelle() +
                ", client=" + client.getNom() +
                ", libelle='" + libelle + '\'' +
                ", date=" + date +
                ", montant=" + montant +
                '}';
    }
}
