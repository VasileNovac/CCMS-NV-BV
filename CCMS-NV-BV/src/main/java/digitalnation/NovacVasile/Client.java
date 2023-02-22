package digitalnation.NovacVasile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id ;
	private String nume, prenume, nrIAuto, dataNastere, locNastere, cetatenie, loc, str, nr, jud, tara, scopCal, actId, serieActId, nrActId, dataExpActId ;
	
	public Client() { }

	public Client(String nume, String prenume, String nrIAuto, String dataNastere, String locNastere, String cetatenie,
			String loc, String str, String nr, String jud, String tara, String scopCal,
			String actId, String serieActId, String nrActId, String dataExpActId) {
		super();
		this.nume = nume;
		this.prenume = prenume;
		this.nrIAuto = nrIAuto;
		this.dataNastere = dataNastere;
		this.locNastere = locNastere;
		this.cetatenie = cetatenie;
		this.loc = loc;
		this.str = str;
		this.nr = nr;
		this.jud = jud;
		this.tara = tara;
		this.scopCal = scopCal;
		this.actId = actId;
		this.serieActId = serieActId;
		this.nrActId = nrActId;
		this.dataExpActId = dataExpActId ;
	}

	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getNrIAuto() {
		return nrIAuto;
	}
	public void setNrIAuto(String nrIAuto) {
		this.nrIAuto = nrIAuto;
	}

	public String getDataNastere() {
		return dataNastere;
	}
	public void setDataNastere(String dataNastere) {
		this.dataNastere = dataNastere;
	}

	public String getLocNastere() {
		return locNastere;
	}
	public void setLocNastere(String locNastere) {
		this.locNastere = locNastere;
	}

	public String getCetatenie() {
		return cetatenie;
	}
	public void setCetatenie(String cetatenie) {
		this.cetatenie = cetatenie;
	}

	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}

	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getJud() {
		return jud;
	}
	public void setJud(String jud) {
		this.jud = jud;
	}

	public String getTara() {
		return tara;
	}
	public void setTara(String tara) {
		this.tara = tara;
	}

	public String getScopCal() {
		return scopCal;
	}
	public void setScopCal(String scopCal) {
		this.scopCal = scopCal;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}
	public String getSerieActId() {
		return serieActId;
	}

	public void setSerieActId(String serieActId) {
		this.serieActId = serieActId;
	}

	public String getNrActId() {
		return nrActId;
	}
	public void setNrActId(String nrActId) {
		this.nrActId = nrActId;
	}

	public String getDataExpActId() {
		return dataExpActId;
	}
	public void setDataExpActId(String dataExpActId) {
		this.dataExpActId = dataExpActId;
	}

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "Client [nume=" + nume + ", prenume=" + prenume + ", nrIAuto=" + nrIAuto + ", dataNastere=" + dataNastere
				+ ", locNastere=" + locNastere + ", cetatenie=" + cetatenie + ", loc=" + loc + ", str=" + str + ", nr="
				+ nr + ", jud=" + jud + ", tara=" + tara + ", scopCal="	+ scopCal + ", actId=" + actId + ", serieActId=" + serieActId 
				+ ", nrActId=" + nrActId + ", dataExpActId=" + dataExpActId + "]";
	}
	
}
