package fr.doranco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "commande")
@XmlAccessorType(XmlAccessType.FIELD)
public class Commande implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private Integer id;
	@XmlElement
	private String numero;
	@XmlElement
	private Date dateCreation;
	@XmlElement
	private Date dateLivraison;
	@XmlElement
	private Double totalRemise;
	@XmlElement
	private Double fraisLivraison;
	@XmlElement
	private Double fraisExpedition;
	@XmlElement
	private Double totalGeneral;
	@XmlElement
	private Adresse adresseFacturation;
	@XmlElement
	private Adresse adresseLivraison;
	@XmlElement
	private CartePaiement cartePaiementDefault;
	@XmlElement
	private Utilisateur utilisateur;
	@XmlElement
	private List <LigneDeCommande> lignesCommande;
	
	public Commande() {}
	
	@Override
	public String toString() {
		return "Commande [id=" + id + ", numero=" + numero + ", dateCreation=" + dateCreation + ", dateLivraison="
				+ dateLivraison + ", totalRemise=" + totalRemise + ", fraisLivraison=" + fraisLivraison
				+ ", fraisExpedition=" + fraisExpedition + ", totalGeneral=" + totalGeneral + ", adresseFacturation="
				+ adresseFacturation + ", adresseLivraison=" + adresseLivraison + ", cartePaiementDefault="
				+ cartePaiementDefault + ", utilisateur=" + utilisateur + ", lignesCommande=" + lignesCommande + "]";
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public Double getTotalRemise() {
		return totalRemise;
	}
	public void setTotalRemise(Double totalRemise) {
		this.totalRemise = totalRemise;
	}
	public Double getFraisLivraison() {
		return fraisLivraison;
	}
	public void setFraisLivraison(Double fraisLivraison) {
		this.fraisLivraison = fraisLivraison;
	}
	public Double getFraisExpedition() {
		return fraisExpedition;
	}
	public void setFraisExpedition(Double fraisExpedition) {
		this.fraisExpedition = fraisExpedition;
	}
	public Double getTotalGeneral() {
		return totalGeneral;
	}
	public void setTotalGeneral(Double totalGeneral) {
		this.totalGeneral = totalGeneral;
	}
	public Adresse getAdresseFacturation() {
		return adresseFacturation;
	}
	public void setAdresseFacturation(Adresse adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}
	public Adresse getAdresseLivraison() {
		return adresseLivraison;
	}
	public void setAdresseLivraison(Adresse adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}
	public CartePaiement getCartePaiementDefault() {
		return cartePaiementDefault;
	}
	public void setCartePaiementDefault(CartePaiement cartePaiementDefault) {
		this.cartePaiementDefault = cartePaiementDefault;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public List<LigneDeCommande> getLignesCommande() {
		return lignesCommande;
	}
	public void setLignesCommande(List<LigneDeCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}
	
	
	

}
