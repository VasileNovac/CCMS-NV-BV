package digitalnation.NovacVasile;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InitC {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id ;
	private String sezon, dela, pila ;
	private int cort, cortPers, cortEE, rulota, rulotaPers, rulotaEE, auto, moto ;
	public static LinkedHashMap<String, String> actIdList ;

	public InitC() { }

	public InitC( String sezon, String dela, String pila, int cort, int cortPers, int cortEE, int rulota, int rulotaPers, int rulotaEE, int auto, int moto ) {
		super();
		this.sezon = sezon ;
		this.dela = dela ;
		this.pila = pila ;
		this.cort = cort;
		this.cortPers = cortPers;
		this.cortEE = cortEE;
		this.rulota = rulota;
		this.rulotaPers = rulotaPers;
		this.rulotaEE = rulotaEE;
		this.auto = auto;
		this.moto = moto;
	}

	public String getSezon() {
		return sezon;
	}
	public void setSezon(String sezon) {
		this.sezon = sezon;
	}

	public String getDela() {
		return dela;
	}
	public void setDela(String dela) {
		this.dela = dela;
	}

	public String getPila() {
		return pila;
	}
	public void setPila(String pila) {
		this.pila = pila;
	}

	public int getCort() {
		return cort;
	}
	public void setCort(int cort) {
		this.cort = cort;
	}

	public int getCortPers() {
		return cortPers;
	}
	public void setCortPers(int cortPers) {
		this.cortPers = cortPers;
	}

	public int getCortEE() {
		return cortEE;
	}
	public void setCortEE(int cortEE) {
		this.cortEE = cortEE;
	}

	public int getRulota() {
		return rulota;
	}
	public void setRulota(int rulota) {
		this.rulota = rulota;
	}

	public int getRulotaPers() {
		return rulotaPers;
	}
	public void setRulotaPers(int rulotaPers) {
		this.rulotaPers = rulotaPers;
	}

	public int getRulotaEE() {
		return rulotaEE;
	}
	public void setRulotaEE(int rulotaEE) {
		this.rulotaEE = rulotaEE;
	}

	public int getAuto() {
		return auto;
	}
	public void setAuto(int auto) {
		this.auto = auto;
	}

	public int getMoto() {
		return moto;
	}
	public void setMoto(int moto) {
		this.moto = moto;
	}

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}

	public static LinkedHashMap<String, String> listAI() {
		actIdList = new LinkedHashMap<String, String>() ;
		actIdList.put(" ", " ") ;
		actIdList.put("CI", "Carte identitate") ;
		actIdList.put("CN", "Certificat nastere") ;
		actIdList.put("BI", "Buletin identitate");
		actIdList.put("CIP", "Carte identitate provizorie");
		actIdList.put("CR", "Carte rezidenta");
		actIdList.put("P", "Pasaport");
		actIdList.put("DI", "Document identitate");
		actIdList.put("PST", "Permis de sedere temporara");
		actIdList.put("PSTL", "Permis sedere pe termen lung");
		actIdList.put("CIN", "Certificat inregistrare");
		return actIdList ;
	}
	
	public static boolean verifSejur ( String dela, String pila, String dtData) {
		LocalDate inData = LocalDate.parse(dela, DateTimeFormatter.ofPattern("dd-MM-yyyy")) ;
		LocalDate endData = LocalDate.parse(pila, DateTimeFormatter.ofPattern("dd-MM-yyyy")) ;
		LocalDate dttData = LocalDate.parse(dtData, DateTimeFormatter.ofPattern("dd-MM-yyyy")) ;
		return inData.compareTo(dttData) * dttData.compareTo(endData) >= 0 ;
	}

	public static int nrZile ( String dela, String pila) {
		return Period.between(LocalDate.parse(dela, DateTimeFormatter.ofPattern("dd-MM-yyyy")), LocalDate.parse(pila, DateTimeFormatter.ofPattern("dd-MM-yyyy"))).getDays();
//	    long xnrZile = ChronoUnit.DAYS.between(LocalDate.parse(sejur.getDtSosit(), DateTimeFormatter.ofPattern("dd-MM-yyyy")), LocalDate.parse(sejur.getDtPlecat(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

	}

	@Override
	public String toString() {
		return "Init [sezon=" + sezon + ", dela=" + dela + ", pila=" + pila +", cort=" + cort + ", cortPers=" + cortPers + ", cortEE=" + cortEE + ", rulota=" + rulota
				+ ", rulotaPers=" + rulotaPers + ", rulotaEE=" + rulotaEE + ", auto=" + auto + ", moto=" + moto
				+ "]";
	}
}

