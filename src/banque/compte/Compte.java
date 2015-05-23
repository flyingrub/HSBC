package banque.compte;

import banque.Client;
import banque.Operation;

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

    public void debit(int debit){
        this.solde -= debit;
    }

    public void credit(int credit){
        this.solde += credit;
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
