package banque.compte;

import banque.Client;
import banque.enumP.Nature;
import banque.Operation;
import banque.enumP.Status;
import banque.exception.OperationBancaireException;

import java.util.ArrayList;

/**
 * Created by Ronan Timinello.
 */
abstract public class Compte {
    private int numeroId;
    private String libelle;
    private Client titulaire;
    private int solde;
    private static int currentNumeroId;
    private ArrayList<Operation> operations = new ArrayList<>();

    public Compte(String libelle, Client titulaire) {
        this.numeroId = ++currentNumeroId;
        this.libelle = libelle;
        this.titulaire = titulaire;
        titulaire.ajouterCompte(this);
    }

    public void debiter(int montant) throws OperationBancaireException {
        if (montant <= 0){
            this.ajoutOperation(new Operation(Nature.DEBIT, Status.KO, this, this.getTitulaire(), montant));
            throw new OperationBancaireException(this.libelle + " Montant invalide");
        }
        if (this.getSolde() - montant < 0){
            this.ajoutOperation(new Operation(Nature.DEBIT, Status.KO, this, this.getTitulaire(), montant));
            throw new OperationBancaireException(this.libelle + " Solde insufisant");
        }
        this.ajoutOperation(new Operation(Nature.DEBIT, Status.OK, this, this.getTitulaire(), montant));
        this.solde -= montant;
    }

    public void crediter(int montant) throws OperationBancaireException {
        if (montant <= 0){
            this.ajoutOperation(new Operation(Nature.CREDIT, Status.KO, this, this.getTitulaire(), montant));
            throw new OperationBancaireException(this.libelle + " Montant invalide");
        }
        this.ajoutOperation(new Operation(Nature.CREDIT, Status.OK, this, this.getTitulaire(), montant));
        this.solde += montant;
    }

    public void debiter(int montant, String libelle) throws OperationBancaireException {
        try {
            this.debiter(montant);
            this.operations.get(this.operations.size() -1).setLibelle(libelle);
        } catch (OperationBancaireException e) {
            throw e;
        }
    }

    public void crediter(int montant, String libelle) throws OperationBancaireException {
        try {
            this.crediter(montant);
            this.operations.get(this.operations.size() -1).setLibelle(libelle);
        } catch (OperationBancaireException e) {
            throw e;
        }
    }

    public void ajoutOperation(Operation operation){
        this.operations.add(operation);
    }

    public int getSolde(){
        return this.solde;
    }

    public Client getTitulaire() {
        return titulaire;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
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
