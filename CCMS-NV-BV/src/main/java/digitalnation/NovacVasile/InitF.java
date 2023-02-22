package digitalnation.NovacVasile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InitF {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id ;
	private String cif, an, denUnitate, nrORC, contB, banca ;

	public InitF() { }

	public InitF(String cif, String an, String denUnitate, String nrORC, String contB, String banca ) {
		super();
		this.cif = cif ;
		this.an = an ;
		this.denUnitate = denUnitate;
		this.nrORC = nrORC;
		this.contB = contB;
		this.banca = banca;
	}

	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;

	}
	public String getAn() {
		return an;
	}
	public void setAn(String an) {
		this.an = an;
	}

	public String getDenUnitate() {
		return denUnitate;
	}
	public void setDenUnitate(String denUnitate) {
		this.denUnitate = denUnitate;
	}

	public String getNrORC() {
		return nrORC;
	}
	public void setNrORC(String nrORC) {
		this.nrORC = nrORC;
	}

	public String getContB() {
		return contB;
	}
	public void setContB(String contB) {
		this.contB = contB;
	}

	public String getBanca() {
		return banca;
	}
	public void setBanca(String banca) {
		this.banca = banca;
	}

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "Init [ cif=" + cif + ", an=" + an + ", denUnitate=" + denUnitate + ", nrORC=" + nrORC + ", contB=" + contB + ", banca="
				+ banca +  "]";
	}
}
