package digitalnation.NovacVasile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sejur {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id ;
	private String dtSosit, dtPlecat ;
	private int nrCort, nrPCort, nrPersC, nrEEC, nrRulota, nrPersR, nrEER, nrAuto, nrMoto, valoare ; 
	private int nrZile ;
	public Sejur() { }

	public Sejur( String dtSosit, String dtPlecat, int nrCort, int nrPCort, int nrPersC, int nrEEC, 
			int nrRulota, int nrPersR, int nrEER, int nrAuto, int nrMoto) {
		super();
		this.dtSosit = dtSosit;
		this.dtPlecat = dtPlecat;
		this.nrCort = nrCort;
		this.nrPCort = nrPCort ;
		this.nrPersC = nrPersC;
		this.nrEEC = nrEEC ;
		this.nrRulota = nrRulota;
		this.nrPersR = nrPersR ;
		this.nrEER = nrEER ;
		this.nrAuto = nrAuto;
		this.nrMoto = nrMoto;
	}
	
	public Sejur ( String dtSosit, String dtPlecat, int nrZile, int valoare) {
		super() ;
		this.dtSosit = dtSosit ;
		this.dtPlecat = dtPlecat ;
		this.nrZile = nrZile ;
		this.valoare = valoare ;
	}


	public String getDtSosit() {
		return dtSosit;
	}
	public void setDtSosit(String dtSosit) {
		this.dtSosit = dtSosit;
	}

	public String getDtPlecat() {
		return dtPlecat;
	}
	public void setDtPlecat(String dtPlecat) {
		this.dtPlecat = dtPlecat;
	}

	public int getNrCort() {
		return nrCort;
	}
	public void setNrCort(int nrCort) {
		this.nrCort = nrCort;
	}

	public int getNrPCort() {
		return nrPCort;
	}
	public void setNrPCort(int nrPCort) {
		this.nrPCort = nrPCort;
	}

	public int getNrPersC() {
		return nrPersC;
	}
	public void setNrPersC(int nrPersC) {
		this.nrPersC = nrPersC;
	}

	public int getNrEEC() {
		return nrEEC;
	}
	public void setNrEEC(int nrEEC) {
		this.nrEEC = nrEEC;
	}

	public int getNrRulota() {
		return nrRulota;
	}
	public void setNrRulota(int nrRulota) {
		this.nrRulota = nrRulota;
	}

	public int getNrPersR() {
		return nrPersR;
	}
	public void setNrPersR(int nrPersR) {
		this.nrPersR = nrPersR;
	}

	public int getNrEER() {
		return nrEER;
	}
	public void setNrEER(int nrEER) {
		this.nrEER = nrEER;
	}

	public int getNrAuto() {
		return nrAuto;
	}
	public void setNrAuto(int nrAuto) {
		this.nrAuto = nrAuto;
	}

	public int getNrMoto() {
		return nrMoto;
	}
	public void setNrMoto(int nrMoto) {
		this.nrMoto = nrMoto;
	}

	public int getValoare() {
		return valoare;
	}
	public void setValoare(int valoare) {
		this.valoare = valoare;
	}

	public int getNrZile() {
		return nrZile;
	}
	public void setNrZile(int nrZile) {
		this.nrZile = nrZile;
	}

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "Sejur [dtSosit=" + dtSosit + ", dtPlecat=" + dtPlecat + ", nrCort=" + nrCort + ", nrPCort=" + nrPCort 
				+ ", nrPersC=" + nrPersC + ", nrEEC=" + nrEEC + ", nrRulota=" + nrRulota + ", nrPersR=" + nrPersR + ", nrEER=" + nrEER+", nrAuto=" + nrAuto + ", nrMoto=" + nrMoto + ",  valoare=" + valoare + "]";
	}

}
