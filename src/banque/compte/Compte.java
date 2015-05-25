package banque.compte;

import banque.AttacheClient;
import banque.Client;
import banque.enumP.Nature;
import banque.Operation;
import banque.enumP.Status;
import banque.exception.OperationBancaireException;
import banque.exception.PersonnelNonAutoriseException;

import java.util.ArrayList;

/**
 * Created by Ronan Timinello.
 */
abstract public class Compte {
    private int numeroId;
    private String libelle;
    private Client titulaire;
    private double solde;
    private static int currentNumeroId;
    private ArrayList<Operation> operations = new ArrayList<>();

    /** Constructeur
     * @param libelle un libelle associé a un compte
     * @param titulaire le titulaire du compte
     */
    public Compte(String libelle, Client titulaire) {
        this.numeroId = ++currentNumeroId;
        this.libelle = libelle;
        this.titulaire = titulaire;
        titulaire.ajouterCompte(this);
    }

    /** Permet de debiter un montant
     * @param montant le montant à debiter
     * @throws OperationBancaireException lorsque le montant est invalide ou le solde insuffisant
     */
    public void debiter(double montant) throws OperationBancaireException {
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

    /** Permet de crediter un montant
     * @param montant le montant à crediter
     * @throws OperationBancaireException lorsque le montant est invalide
     */
    public void crediter(double montant) throws OperationBancaireException {
        if (montant <= 0){
            this.ajoutOperation(new Operation(Nature.CREDIT, Status.KO, this, this.getTitulaire(), montant));
            throw new OperationBancaireException(this.libelle + " Montant invalide");
        }
        this.ajoutOperation(new Operation(Nature.CREDIT, Status.OK, this, this.getTitulaire(), montant));
        this.solde += montant;
    }

    /**  Permet de debiter un montant
     * @param montant le montant à debiter
     * @param libelle un libelle pour cette operations
     * @throws OperationBancaireException voir debiter(montant)
     */
    public void debiter(double montant, String libelle) throws OperationBancaireException {
        try {
            this.debiter(montant);
            this.operations.get(this.operations.size() -1).setLibelle(libelle);
        } catch (OperationBancaireException e) {
            throw e;
        }
    }

    /** Permet de crediter un montant
     * @param montant le montant à crediter
     * @param libelle un libelle pour cette operations
     * @throws OperationBancaireException voir crediter(montant)
     */
    public void crediter(double montant, String libelle) throws OperationBancaireException {
        try {
            this.crediter(montant);
            this.operations.get(this.operations.size() -1).setLibelle(libelle);
        } catch (OperationBancaireException e) {
            throw e;
        }
    }

    /** Permet a un attache de clientele de forcer un debit.
     * @param montant le montant à debiter
     * @param operation l'operation à forcer
     * @param attacheClient l'attache client d'ou provient l'operation
     * @return le nouveau montant du solde
     * @throws PersonnelNonAutoriseException si l'attache n'est pas autorisé
     */
    public double forcerDebit(double montant, Operation operation, AttacheClient attacheClient) throws PersonnelNonAutoriseException {
        if (this.titulaire.getAttacheClient().getNumeroId() != attacheClient.getNumeroId()) {
            throw new PersonnelNonAutoriseException("Mauvais attache Clientele");
        }
        operation.setStatus(Status.OK);
        this.solde -= montant;
        return this.solde;
    }

    /** Permet a un attache de clientele de forcer un credit.
     * @param montant le montant à crediter
     * @param operation  l'operation à forcer
     * @param attacheClient l'attache client d'ou provient l'operation
     * @return le nouveau montant du solde
     * @throws PersonnelNonAutoriseException si l'attache n'est pas autorisé
     */
    public double forcerCredit(double montant, Operation operation, AttacheClient attacheClient) throws PersonnelNonAutoriseException {
        if (this.titulaire.getAttacheClient().getNumeroId() != attacheClient.getNumeroId()) {
            throw new PersonnelNonAutoriseException("Mauvais attache Clientele");
        }
        operation.setStatus(Status.OK);
        this.solde += montant;
        return this.solde;
    }

    /** Permet de debiter a partir d'une operation
     * @param operation operation a partir de laquelle on realise le debit
     * @return vrai si l'operation reussi, faux dans le cas contraire
     */
    public boolean debiter(Operation operation) {
        this.ajoutOperation(operation);
        if (operation.getMontant() <= 0 || this.getSolde() - operation.getMontant() < 0){
            operation.setStatus(Status.KO);
            return false;
        }
        this.ajoutOperation(new Operation(Nature.DEBIT, Status.OK, this, this.getTitulaire(), operation.getMontant()));
        this.solde -= operation.getMontant();
        return true;
    }

    /** Permet de crediter a partir d'une operation
     * @param operation operation a partir de laquelle on realise le credit
     * @return vrai si l'operation reussi, faux dans le cas contraire
     */
    public boolean crediter(Operation operation){
        this.ajoutOperation(operation);
        if (operation.getMontant() <= 0){
            operation.setStatus(Status.KO);
            return false;
        }
        operation.setStatus(Status.OK);
        this.solde += operation.getMontant();
        return true;
    }

    /** Definit un nouveau solde
     * @param solde le nouveau solde
     */
    public void setSolde(double solde) {
        this.solde = solde;
    }

    /** ajoute une operation
     * @param operation operation à ajouter
     */
    public void ajoutOperation(Operation operation){
        this.operations.add(operation);
    }

    /**
     * @return le solde actuel
     */
    public double getSolde(){
        return this.solde;
    }

    /**
     * @return le titulaire du comtpe
     */
    public Client getTitulaire() {
        return titulaire;
    }

    /**
     * @return les operations de ce compte
     */
    public ArrayList<Operation> getOperations() {
        return operations;
    }

    /**
     * @return le numero d'id du compte
     */
    public int getNumeroId() {
        return numeroId;
    }

    /**
     * @return le libelle du compte
     */
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
