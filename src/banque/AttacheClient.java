package banque;

import banque.enumP.Status;
import banque.exception.PersonnelNonAutoriseException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ronan Timinello.
 */
public class AttacheClient {
    private int numeroId;
    private static int currentNumeroId;
    private ArrayList<Client> clients = new ArrayList<>();
    public ArrayList<Operation> operationsEnAttente = new ArrayList<>();

    /**
     * Constructeur
     */
    public AttacheClient() {
        this.numeroId = ++currentNumeroId;
    }

    /** Permet d'ajouter un/des client(s)
     * @param clients les clients à ajouter
     */
    public void ajouterClient(Client... clients){
        this.clients.addAll(Arrays.asList(clients));
    }

    /** permet d'ajouter un/des operation(s)
     * @param operations les operations à ajouter
     */
    public void ajouterOperation(Operation... operations){
        this.operationsEnAttente.addAll(Arrays.asList(operations));
    }

    /**
     * @return le numero d'id de l'attache
     */
    public int getNumeroId() {
        return numeroId;
    }

    /** Permet de valider une operation a la main
     * @param operation l'operation a valider
     * @throws PersonnelNonAutoriseException si l'attache n'est pas autorisé valider l'operation
     */
    public void validerOperation(Operation operation) throws PersonnelNonAutoriseException {
        if (operationsEnAttente.contains(operation)){
            switch (operation.getNature()){
                case CREDIT:
                    operation.getCompte().forcerCredit(operation.getMontant(), operation, this);
                    break;
                case DEBIT:
                    operation.getCompte().forcerDebit(operation.getMontant(), operation, this);
            }
        }
    }

    /**
     * @return les operations en attentes de validations
     */
    public ArrayList<Operation> getOperationsEnAttente() {
        return operationsEnAttente;
    }

    /**
     * @return tout les noms des clients
     */
    public String allClientName(){
        String allClientName = "";
        for (Client c: clients){
            allClientName += c.getNom() + ", ";
        }
        return allClientName;
    }

    @Override
    public String toString() {
        return "AttacheClient{" +
                "clients=" + allClientName() +
                '}';
    }

}
