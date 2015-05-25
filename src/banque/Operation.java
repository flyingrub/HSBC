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
    private double montant;
    private static int currentNumeroId;

    /** Constructeur
     * @param nature la nature de l'operation
     * @param status le statut de l'operation
     * @param compte le compte sur lequel l'operation est effectué
     * @param client le client auquel appartient le compte
     * @param montant le montant de l'operation
     */
    public Operation(Nature nature, Status status, Compte compte, Client client, double montant) {
        this.nature = nature;
        this.status = status;
        this.compte = compte;
        this.client = client;
        this.montant = montant;
        this.numeroId = ++currentNumeroId;
        libelle = "Pas de libellé";
    }

    /** Met a jour le statut
     * @param status le statut à mettre à jour
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return la nature de l'operation
     */
    public Nature getNature() {
        return nature;
    }

    /**
     * @return le compte de l'operation
     */
    public Compte getCompte() {
        return compte;
    }

    /**
     * @return le montant de l'operation
     */
    public double getMontant() {
        return montant;
    }

    /** Met a jour le libelle
     * @param libelle le libelle à mettre à jour
     */
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
