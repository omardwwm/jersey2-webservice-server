package fr.doranco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "utilisateur")
@XmlAccessorType(XmlAccessType.FIELD)
public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private Integer id;
	@XmlElement
	private String nom;
	@XmlElement
	private String prenom;
	@XmlElement
	private Date dateNaissance;
	@XmlElement
	private Boolean isActif;
//	faire en ENUM 
	@XmlElement
	private String profil;
	@XmlElement
	private String email;
	
	private String password;
	@XmlElement
	private String telephone;
	@XmlElement
	private List<Adresse> adresses;
	
	@XmlElement
	private List<Commande> commandes;
	@XmlElement
	private List<CartePaiement> cartesPaiement;
	@XmlElement
	private List<Commentaire> commentaire;
	@XmlElement
	private List<ArticlePanier> panier;
	
	
	
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", isActif=" + isActif + ", profil=" + profil + ", email=" + email + ", password="
				+password + ", telephone=" + telephone + "]";
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Date getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public Boolean getIsActif() {
		return isActif;
	}


	public void setIsActif(Boolean isActif) {
		this.isActif = isActif;
	}


	public String getProfil() {
		return profil;
	}


	public void setProfil(String profil) {
		this.profil = profil;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public List<Adresse> getAdresses() {
		return adresses;
	}


	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}


	public List<Commande> getCommandes() {
		return commandes;
	}


	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}


	public List<CartePaiement> getCartesPaiement() {
		return cartesPaiement;
	}


	public void setCartesPaiement(List<CartePaiement> cartesPaiement) {
		this.cartesPaiement = cartesPaiement;
	}


	public List<Commentaire> getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(List<Commentaire> commentaire) {
		this.commentaire = commentaire;
	}


	public List<ArticlePanier> getPanier() {
		return panier;
	}


	public void setPanier(List<ArticlePanier> panier) {
		this.panier = panier;
	}
	
	
	public Utilisateur() {}
	
	

}
