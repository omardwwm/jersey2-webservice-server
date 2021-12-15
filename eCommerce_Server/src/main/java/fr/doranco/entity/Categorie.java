package fr.doranco.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "categorie")
@XmlAccessorType(XmlAccessType.FIELD)
public class Categorie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private Integer id;
	@XmlElement
	private String nom;
	@XmlElement
	private Double remise;
	@XmlElement
	private Boolean isRemiseCummulable;
	
	public Categorie() {}
	
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + ", remise=" + remise + ", isRemiseCummulable="
				+ isRemiseCummulable + ", articles=" + articles + "]";
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
	public Double getRemise() {
		return remise;
	}
	public void setRemise(Double remise) {
		this.remise = remise;
	}
	public Boolean getIsRemiseCummulable() {
		return isRemiseCummulable;
	}
	public void setIsRemiseCummulable(Boolean isRemiseCummulable) {
		this.isRemiseCummulable = isRemiseCummulable;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	private List<Article> articles;

}
