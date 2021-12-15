package fr.doranco.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ligneDeCommande")
@XmlAccessorType(XmlAccessType.FIELD)
public class LigneDeCommande implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private Integer id;
	@XmlElement
	private Integer quantite;
	@XmlElement
	private Double prixUnitaire;
	@XmlElement
	private Double remiseArticlr;
	@XmlElement
	private Commande commande;
	@XmlElement
	private Article article;
	
	public LigneDeCommande() {}
	
	@Override
	public String toString() {
		return "LigneDeCommande [id=" + id + ", quantite=" + quantite + ", prixUnitaire=" + prixUnitaire
				+ ", remiseArticlr=" + remiseArticlr + ", commande=" + commande + ", article=" + article + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantite() {
		return quantite;
	}
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	public Double getPrixUnitaire() {
		return prixUnitaire;
	}
	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	public Double getRemiseArticlr() {
		return remiseArticlr;
	}
	public void setRemiseArticlr(Double remiseArticlr) {
		this.remiseArticlr = remiseArticlr;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	

}
