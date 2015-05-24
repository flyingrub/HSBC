package banque;

import banque.AttacheClient;
import banque.compte.Compte;
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

    public Client(String nom, AttacheClient attacheClient) {
        this.numeroId = ++currentNumeroId;
        this.nom = nom;
        this.attacheClient = attacheClient;
        attacheClient.ajouterClient(this);
    }

    public void ajouterCompte(Compte... comptes){
        this.comptes.addAll(Arrays.asList(comptes));
    }

    public void supprimerCompte(int numeroId, String libelle){
        for(Compte compte: comptes){
            if (compte.getNumeroId() == numeroId && compte.getLibelle().equals(libelle)){
                this.comptes.remove(compte);
                return;
            }
        }
    }

    public void ordreVirement(Compte compteOrigine, Compte compteDestinataire, int montant) throws OrdreVirementException, OperationBancaireException {
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
            throw e;
        }
    }

    public int getNumeroId() {
        return numeroId;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numeroId=" + numeroId +
                ", nom='" + nom + '\'' +
                ", comptes=" + comptes +
                ", attacheClient=" + attacheClient +
                '}';
    }
}
