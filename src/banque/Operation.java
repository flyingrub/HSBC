package banque;

import banque.compte.Compte;
import banque.compte.remunere.CompteRemunere;

import java.util.Date;

enum Nature{DEBIT, CREDIT}
enum Status{OK, KO, ATTENTE}


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

    public Operation(Nature nature, Compte compte, Client client, int montant) throws OperationBancaireException {
        this.nature = nature;
        this.numeroId = ++currentNumeroId;
        this.compte = compte;
        this.client = client;
        this.montant = montant;

        if (montant <= 0){
            this.status = Status.KO;
            compte.ajoutOperation(this);
            throw new OperationBancaireException("Montant invalide.");
        }
        if (nature == Nature.CREDIT && compte instanceof CompteRemunere && compte.getSolde() + montant > ((CompteRemunere) compte).getSoldePlafond()) {
            this.status = Status.KO;
            compte.ajoutOperation(this);
            throw new OperationBancaireException("Plafond insufisant");
        }
        if (nature == Nature.DEBIT && compte.getSolde() - montant < 0){
            this.status = Status.KO;
            compte.ajoutOperation(this);
            throw new OperationBancaireException("Solde insufisant");
        }

        switch (nature){
            case CREDIT:
                compte.credit(montant);
                break;
            case DEBIT:
                compte.debit(montant);
        }
        this.status = Status.OK;
        compte.ajoutOperation(this);
    }

    public Operation(String libelle, Nature nature, Compte compte, Client client, int montant) throws OperationBancaireException {
        this(nature, compte, client, montant);
        this.libelle = libelle;
    }

    public class OperationBancaireException extends Exception {
        public OperationBancaireException(String message) {
            System.out.println(message);
        }
    }

    @Override
    public String toString() {
        return "Operation{" +
                "nature=" + nature +
                ", status=" + status +
                ", numeroId=" + numeroId +
                ", compte=" + compte +
                ", client=" + client.getNom() +
                ", libelle='" + libelle + '\'' +
                ", date=" + date +
                ", montant=" + montant +
                '}';
    }
}
