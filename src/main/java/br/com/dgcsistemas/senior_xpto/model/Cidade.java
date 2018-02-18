package br.com.dgcsistemas.senior_xpto.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="cidade") 
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
    private Integer idIbge;
    private String estado;
    private String nome;
    private String nomeSemAcento;
    private String nomeAlternativo;
    private String capital;
    private String microregiao;
    private String mesoregiao;
    private Float latitude;
    private Float longitude;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public Integer getIdIbge() {
		return idIbge;
	}
	public void setIdIbge(Integer idIbge) {
		this.idIbge = idIbge;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeSemAcento() {
		return nomeSemAcento;
	}
	public void setNomeSemAcento(String nomeSemAcento) {
		this.nomeSemAcento = nomeSemAcento;
	}
	public String getNomeAlternativo() {
		return nomeAlternativo;
	}
	public void setNomeAlternativo(String nomeAlternativo) {
		this.nomeAlternativo = nomeAlternativo;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getMicroregiao() {
		return microregiao;
	}
	public void setMicroregiao(String microregiao) {
		this.microregiao = microregiao;
	}
	public String getMesoregiao() {
		return mesoregiao;
	}
	public void setMesoregiao(String mesoregiao) {
		this.mesoregiao = mesoregiao;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
		
    

    
}
