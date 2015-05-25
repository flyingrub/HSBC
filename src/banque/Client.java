package banque;

import banque.AttacheClient;
import banque.compte.Compte;
import banque.enumP.Nature;
import banque.enumP.Status;
import banque.exception.OperationBancaireException;
import banque.exception.OrdreVirementException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ronan Timinello.
 */
public class Client {
    private int numeroId;
    private String nom;
    private ArrayList<Compte> comptes = new ArrayList<>();
    private AttacheClient attacheClient;
    private static int currentNumeroId;

    /** Constructeur
     * @param nom nom du client
     * @param attacheClient l'attache de clientele du client
     */
    public Client(String nom, AttacheClient attacheClient) {
        this.numeroId = ++currentNumeroId;
        this.nom = nom;
        this.attacheClient = attacheClient;
        attacheClient.ajouterClient(this);
    }

    /** Ajoute un ou des comptes aux compte actuels de ce client
     * @param comptes les comptes à ajouter
     */
    public void ajouterCompte(Compte... comptes){
        this.comptes.addAll(Arrays.asList(comptes));
    }

    /** Effectue un ordre de virement
     * @param compteOrigine le compte a partir duquel on effectue le virement
     * @param compteDestinataire le compte vers lequel on effectue le virement
     * @param montant le montant du virement
     * @throws OrdreVirementException Si le titulaire est invalide ou si le solde est insuffisant
     */
    public void ordreVirement(Compte compteOrigine, Compte compteDestinataire, int montant) throws OrdreVirementException {
        if (compteOrigine.getTitulaire().getNumeroId() != this.getNumeroId() || compteDestinataire.getTitulaire().getNumeroId() !=  this.getNumeroId()) {
            throw new OrdreVirementException("Titulaire invalide sur un ou plusieurs comptes");
        }
        if (compteOrigine.getSolde() - montant < 0){
            throw new OrdreVirementException("Solde insuffisant sur le compte origine");
        }
        try {
            compteOrigine.debiter(montant);
            compteDestinataire.crediter(montant);
        } catch (OperationBancaireException e) {
            if (e.getMessage().contains("Plafond insuffisant")) {
                Operation debit = compteOrigine.getOperations().get(compteOrigine.getOperations().size() -1);
                Operation credit = compteDestinataire.getOperations().get(compteDestinataire.getOperations().size() -1);
                this.attacheClient.ajouterOperation(debit);
                this.attacheClient.ajouterOperation(credit);
            }
        }
    }

    /**
     * @return les attache de clienteles du clients
     */
    public AttacheClient getAttacheClient() {
        return attacheClient;
    }

    /**
     * @return le numero d'id du client
     */
    public int getNumeroId() {
        return numeroId;
    }

    /**
     * @return le nom du client
     */
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numeroId=" + numeroId +
                ", nom='" + nom + '\'' +
                ", comptes=" + comptes +
                ", attacheClient=" + attacheClient.getOperationsEnAttente() +
                '}';
    }
}
