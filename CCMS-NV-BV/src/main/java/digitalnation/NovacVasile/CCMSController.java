package digitalnation.NovacVasile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.time.LocalDate;
//import java.time.Period;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit ;
import java.util.ArrayList ;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@EnableAutoConfiguration
public class CCMSController {

		public static Connection xconn = null ;
		public static Statement stmt = null;

		public boolean erori = false ;
		public boolean nrView = true ;
		public boolean xcmdExt = false ;
		public boolean err1 = true ;
		public boolean err2 = false ;
		public boolean err = false ;
		public boolean err11 = true ;
		public boolean err0 = false ;
		public String zmodul, sql, insUpd, ycif, zcif, ysezon, yactId, yserieActId, ynrActId, ydtSosit, ydtPlecat, zsezonS, zsezonP ;
		public String tcif ;
		public static String an ;
		public int valoare, nrZile ;
		public String tactId, tserieActId, tnrActId, tnume, tprenume ;
		public ArrayList<InitC> listIN ;
		public ArrayList<Sejur> listSJ ;
		public ArrayList<InitF> listF ;
		public ArrayList<Sejur> listSV ;
		public ArrayList<Client> listCL ;

		@GetMapping("/rap")
		public String rap(Model model) {

			switch (zmodul) {
				case "initf" :
					model.addAttribute("initF", initFRepository.findAll()) ;
					model.addAttribute("ymodul", "rap") ;
					model.addAttribute("zmodul", "initf") ;
					model.addAttribute("tcif", tcif) ;
					break ;
				case "initc":
					model.addAttribute("initC", initCRepository.findAll()) ;
					model.addAttribute("ymodul", "rap") ;
					model.addAttribute("zmodul", "initc") ;
					model.addAttribute("tcif", tcif) ;
					break ;
				case "client" :
					model.addAttribute("client", clientRepository.findAll()) ;
					model.addAttribute("ymodul", "rap") ;
					model.addAttribute("zmodul","client") ;
					model.addAttribute("tcif", tcif) ;
					break ;
				case "sejur" :
					model.addAttribute("sejur", sejurRepository.findAll()) ;
					model.addAttribute("ymodul", "rap") ;
					model.addAttribute("zmodul","sejur") ;
					model.addAttribute("tcif", tcif) ;
					model.addAttribute("tactId", tactId) ;
					model.addAttribute("tserieActId", tserieActId) ;
					model.addAttribute("tnrActId", tnrActId) ;
					model.addAttribute("tnume", tnume) ;
					model.addAttribute("tprenume", tprenume) ;
					break ;
			}
			return "rap";
		}

		@Autowired
		private InitFRepository initFRepository ;
				
		@GetMapping("/initf")
		public void initf(Model model) {
			zmodul = "initf" ;
			model.addAttribute("ymodul", "initf") ;
			model.addAttribute("nrView", nrView) ;
			model.addAttribute("xcmdExt", xcmdExt) ;
			InitF initF = new InitF() ;
			if (xcmdExt) {
				for ( InitF i : listF) {
					initF.setCif(i.getCif()) ;
					initF.setAn(i.getAn()) ;
					initF.setDenUnitate(i.getDenUnitate()) ;
					initF.setNrORC(i.getNrORC()) ;
					initF.setContB(i.getContB()) ;
					initF.setBanca(i.getBanca()) ;
   				}
			}
			model.addAttribute("initF", initF) ;
		}

		@PostMapping("/errInitF")
		public String errInitF(@ModelAttribute InitF initF, Model model) {
			zmodul = "initf" ;
			model.addAttribute("ymodul", "initf") ;
			nrView = false ;
			ycif = initF.getCif();

			xconn = CcmsNvBvApplication.conn ;
			try {
				stmt = xconn.createStatement();
				ResultSet rsF = stmt.executeQuery("SELECT count(*) FROM initF") ;
				if ( rsF.next() ) {
					stmt = xconn.createStatement();
					sql = "SELECT * FROM initF WHERE cif=?" ;
					PreparedStatement sqlInitF = xconn.prepareStatement(sql);
					sqlInitF.setString(1, ycif);
					ResultSet rs = sqlInitF.executeQuery();
					if ( rs.next()) {
//			exista cif - se va afisa comanda tip buton-submit MODIFICARE - VALIDARE
						xcmdExt = true ;
						listF = new ArrayList<InitF>() ;
						InitF initf = new InitF(rs.getString("cif"), rs.getString("an"), rs.getString("denUnitate"), rs.getString("nrORC"), rs.getString("contB"), rs.getString("banca")) ;
						listF.add(initf) ;
					}
				} else {
//			 nu exista cif - se va afisa comanda tip buton-submit ADAUGARE
					xcmdExt = false ;
				}
			} catch(SQLException se) { 
//		Handle errors for JDBC 
				se.printStackTrace(); 
			}
			return "redirect:initf" ;
		}

		@PostMapping("/addInitF")
		public String addInitF(@ModelAttribute InitF initF, Model model) {
			zmodul = "initf" ;
			nrView = true ;
			ycif = "";
			initFRepository.save(initF) ;
			
			xconn = CcmsNvBvApplication.conn ;
			try {
				if (xcmdExt) {
					insUpd = "UPDATE initF SET denUnitate=?, nrORC=?, contB=?, banca=? WHERE cif=? AND an=?" ;
				} else {
					insUpd = "INSERT INTO initF (denUnitate, nrORC, contB, banca, cif, an) VALUES (?, ?, ?, ?, ?, ?)" ;
				}
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, initF.getDenUnitate());
				iS.setString(2, initF.getNrORC()) ;
				iS.setString(3, initF.getContB()) ;
				iS.setString(4, initF.getBanca()) ;
				iS.setString(5, initF.getCif()) ;
				iS.setString(6, initF.getAn()) ;
				iS.executeUpdate();
				iS.close();
				tcif = initF.getCif() ;
				an = initF.getAn();
				xcmdExt = false ;
					
				stmt = xconn.createStatement(); 
				sql = "SELECT * FROM initF WHERE cif=? AND an=?" ;
				PreparedStatement isql = xconn.prepareStatement(sql);
				isql.setString(1, tcif);
				isql.setString(2,  an) ;
				ResultSet rs = isql.executeQuery();
//		Extract data from result set
				listF = new ArrayList<InitF>() ;
				while(rs.next()) { 
					InitF initf = new InitF(rs.getString("cif"), rs.getString("an"), rs.getString("denUnitate"), rs.getString("nrORC"), rs.getString("contB"), rs.getString("banca")) ;
					listF.add(initf) ;
				}
			} catch(SQLException se) { 
//		Handle errors for JDBC 
				se.printStackTrace(); 
			}
			return "redirect:rap";
//			return "redirect:initf" ;
		}

		@Autowired
		private InitCRepository initCRepository ;

		@GetMapping("/initc")
		public void initc(Model model) {
			zmodul = "initc" ;
			model.addAttribute("ymodul", "initc") ;
			model.addAttribute("nrView", nrView) ;
			model.addAttribute("xcmdExt", xcmdExt) ;
			model.addAttribute("listIN", listIN) ;
			model.addAttribute("tcif", tcif) ;
			model.addAttribute("an", an) ;
			InitC initC = new InitC() ;
			initC.setSezon(ysezon) ;
			if (xcmdExt) {
				for ( InitC i : listIN) {
					initC.setSezon(i.getSezon()) ;
					initC.setDela(i.getDela()) ;
					initC.setPila(i.getPila()) ;
					initC.setCort(i.getCort()) ;
					initC.setCortPers(i.getCortPers()) ;
					initC.setCortEE(i.getCortEE()) ;
					initC.setRulota(i.getRulota()) ;
					initC.setRulotaPers(i.getRulotaPers()) ;
					initC.setRulotaEE(i.getRulotaEE()) ;
					initC.setAuto(i.getAuto()) ;
					initC.setMoto(i.getMoto()) ;
   				}
			}
			model.addAttribute("initC", initC) ;
		}

		@PostMapping("/errInitC")
		public String errInitC(@ModelAttribute InitC initC, Model model) {
			zmodul = "initc" ;
			model.addAttribute("ymodul", "initc") ;
			nrView = false ;
			ysezon = initC.getSezon();

			xconn = CcmsNvBvApplication.conn ;
			try {
				stmt = xconn.createStatement();
				ResultSet rsC = stmt.executeQuery("SELECT count(*) FROM initC") ;
				if ( rsC.next() ) {
					stmt = xconn.createStatement();
					sql = "SELECT * FROM initC WHERE cif=? AND an=? AND sezon=?" ;
					PreparedStatement sqlInitC = xconn.prepareStatement(sql);
					sqlInitC.setString(1, tcif);
					sqlInitC.setString(2, an) ;
					sqlInitC.setString(3, ysezon) ;
					ResultSet rs = sqlInitC.executeQuery();
					if ( rs.next()) {
//			exista cif - se va afisa comanda tip buton-submit MODIFICARE - VALIDARE
						xcmdExt = true ;
						listIN = new ArrayList<InitC>() ;
						InitC initc = new InitC(rs.getString("sezon"), rs.getString("dela"), rs.getString("pila"), rs.getInt("cort"), rs.getInt("cortPers"), rs.getInt("cortEE"), rs.getInt("rulota"), rs.getInt("rulotaPers"), rs.getInt("rulotaEE"), rs.getInt("auto"), rs.getInt("moto")) ;
						listIN.add(initc) ;
					}
				} else {
//			 nu exista cif - se va afisa comanda tip buton-submit ADAUGARE
					xcmdExt = false ;
				}
			} catch(SQLException se) { 
//		Handle errors for JDBC 
				se.printStackTrace(); 
			}
			return "redirect:initc" ;
		}
		
		@PostMapping("/addInitC")
		public String addInitC(@ModelAttribute InitC initC, Model model) {
			zmodul = "initc" ;
			nrView = true ;
			ysezon = "";
			initCRepository.save(initC) ;
			
			xconn = CcmsNvBvApplication.conn ;
			try {
				if (xcmdExt) {
					insUpd = "UPDATE initC SET dela=?, pila=?, cort=?, cortPers=?, cortEE=?, rulota=?, rulotaPers=?, rulotaEE=?, auto=?, moto=? WHERE sezon=? AND cif=? AND an=?" ;
				} else {
					insUpd = "INSERT INTO initC (dela, pila, cort, cortPers, cortEE, rulota, rulotaPers, rulotaEE, auto, moto, sezon, cif, an) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
				}
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, initC.getDela()) ;
				iS.setString(2, initC.getPila()) ;
				iS.setInt(3, initC.getCort());
				iS.setInt(4, initC.getCortPers()) ;
				iS.setInt(5, initC.getCortEE()) ;
				iS.setInt(6, initC.getRulota()) ;
				iS.setInt(7, initC.getRulotaPers()) ;
				iS.setInt(8, initC.getRulotaEE()) ;
				iS.setInt(9, initC.getAuto()) ;
				iS.setInt(10, initC.getMoto()) ;
				iS.setString(11, initC.getSezon()) ;
				iS.setString(12, tcif) ;
				iS.setString(13, an) ;
				iS.executeUpdate();
				iS.close();
				xcmdExt = false ;

				stmt = xconn.createStatement(); 
				sql = "SELECT * FROM initC WHERE cif=? AND an=? ORDER BY sezon" ;
				PreparedStatement isql = xconn.prepareStatement(sql);
				isql.setString(1, tcif);
				isql.setString(2, an) ;
				ResultSet rs = isql.executeQuery();
//		Extract data from result set 
				listIN = new ArrayList<InitC>() ;
				while(rs.next()) { 
					InitC initc = new InitC(rs.getString("sezon"), rs.getString("dela"), rs.getString("pila"), rs.getInt("cort"), rs.getInt("cortPers"), rs.getInt("cortEE"), rs.getInt("rulota"), rs.getInt("rulotaPers"), rs.getInt("rulotaEE"), rs.getInt("auto"), rs.getInt("moto")) ;
					listIN.add(initc) ;
				}
			} catch(SQLException se) { 
//		Handle errors for JDBC 
				se.printStackTrace(); 
			}
			return "redirect:rap";
//			return "redirect:initc" ;
		}

		@Autowired
		private ClientRepository clientRepository ;

		@GetMapping("/client")
		public void client(Model model) {
			zmodul = "client" ;
			model.addAttribute("ymodul", "client") ;
			model.addAttribute("nrView", nrView) ;
			model.addAttribute("xcmdExt", xcmdExt) ;
			model.addAttribute("tcif", tcif) ;
			model.addAttribute("an", an) ;
			model.addAttribute("err", err) ;
			model.addAttribute("err1", err1) ;
			model.addAttribute("err2", err2) ;
			Client client = new Client() ;
			client.setActId(yactId) ;
			client.setSerieActId(yserieActId) ;
			client.setNrActId(ynrActId) ;
			LinkedHashMap<String, String> aiList = InitC.listAI();
			model.addAttribute("aiList", aiList) ;
			if (xcmdExt) {
				for ( Client i : listCL) {
					client.setNume(i.getNume()) ;
					client.setPrenume(i.getPrenume()) ;
					client.setNrIAuto(i.getNrIAuto()) ;
					client.setDataNastere(i.getDataNastere()) ;
					client.setLocNastere(i.getLocNastere()) ;
					client.setCetatenie(i.getCetatenie()) ;
					client.setLoc(i.getLoc()) ;
					client.setStr(i.getStr()) ;
					client.setNr(i.getNr()) ;
					client.setJud(i.getJud()) ;
					client.setTara(i.getTara()) ;
					client.setScopCal(i.getScopCal()) ;
					client.setActId(i.getActId()) ;
					client.setSerieActId(i.getSerieActId()) ;
					client.setNrActId(i.getNrActId()) ;
					client.setDataExpActId(i.getDataExpActId()) ;
   				}
			}
			model.addAttribute("client", client) ;
		}

		@PostMapping("/err1Client")
		public String err1Client ( @ModelAttribute Client client, Model model) {
			err1 = false ;
			err2 = true ;
			yactId = client.getActId();
			return "redirect:client" ;
		}

		@PostMapping("/err2Client")
		public String err2Client ( @ModelAttribute Client client, Model model) {
			err2 = false ;
			err = true ;
			yserieActId = client.getSerieActId();
			return "redirect:client" ;
		}
		
		@PostMapping("/errClient")
		public String errClient ( @ModelAttribute Client client, Model model) {
			zmodul = "client" ;
			err = false ;
			err1 = true ;
			model.addAttribute("ymodul", "client") ;
			nrView = false ;
//			yactId = client.getActId() ;
//			yserieActId = client.getSerieActId() ;
			ynrActId = client.getNrActId() ;
			
			xconn = CcmsNvBvApplication.conn ;
			try {
				stmt = xconn.createStatement();
				ResultSet rsC = stmt.executeQuery("SELECT count(*) FROM client") ;
				if ( rsC.next()) {
					stmt = xconn.createStatement();
					sql = "SELECT * FROM client WHERE cif=? AND actId=? AND serieActId=? AND nrActId=?" ;
					PreparedStatement sqlClient = xconn.prepareStatement(sql);
					sqlClient.setString(1, tcif) ;
					sqlClient.setString(2, yactId);
					sqlClient.setString(3, yserieActId) ;
					sqlClient.setString(4, ynrActId) ;
					ResultSet rs = sqlClient.executeQuery();
					if ( rs.next()) {
//			exista client - se va afisa comanda tip buton-submit MODIFICARE - VALIDARE
						xcmdExt = true ;
						listCL = new ArrayList<Client>() ;
						Client aclient = new Client(rs.getString("nume"), rs.getString("prenume"), rs.getString("nrIAuto"), rs.getString("dataNastere"), rs.getString("LocNastere"), rs.getString("cetatenie"), rs.getString("loc"), rs.getString("str"), rs.getString("nr"), rs.getString("jud"), rs.getString("tara"), rs.getString("scopCal"), rs.getString("actId"), rs.getString("serieActId"), rs.getString("nrActId"), rs.getString("dataExpActId")) ;
						listCL.add(aclient) ;
					}
				} else {
//			 nu exista client - se va afisa comanda tip buton-submit ADAUGARE
					xcmdExt = false ;
				}
			} catch(SQLException se) { 
//		Handle errors for JDBC 
				se.printStackTrace(); 
			}
			return "redirect:client" ;
		}

		@PostMapping("/addClient")
		public String addClient(@ModelAttribute Client client, Model model) {
			zmodul = "client" ;
			nrView = true ;
			yactId = "";
			yserieActId = "" ;
			ynrActId = "" ;
			LinkedHashMap<String, String> aiList = InitC.listAI();
			model.addAttribute("aiList", aiList) ;
			clientRepository.save(client) ;

			xconn = CcmsNvBvApplication.conn ;
			try {
				if (xcmdExt) {
					insUpd = "UPDATE client SET nume=?, prenume=?, nrIAuto=?, dataNastere=?, locNastere=?, cetatenie=?, loc=?, str=?, nr=?, jud=?, tara=?, scopCal=?, dataExpActId=? WHERE cif=? AND actId=? AND serieActId=? AND nrActId=?" ;
				} else {
					insUpd = "INSERT INTO client (nume, prenume, nrIAuto, dataNastere, locNastere, cetatenie, loc, str, nr, jud, tara, scopCal, dataExpActId, cif, actId, serieActId, nrActId ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
				}
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, client.getNume());
				iS.setString(2, client.getPrenume()) ;
				iS.setString(3, client.getNrIAuto()) ;
				iS.setString(4, client.getDataNastere()) ;
				iS.setString(5, client.getLocNastere()) ;
				iS.setString(6, client.getCetatenie()) ;
				iS.setString(7, client.getLoc()) ;
				iS.setString(8, client.getStr()) ;
				iS.setString(9, client.getNr()) ;
				iS.setString(10, client.getJud()) ;
				iS.setString(11, client.getTara()) ;
				iS.setString(12, client.getScopCal()) ;
				iS.setString(13, client.getDataExpActId()) ;
				iS.setString(14, tcif) ;
				iS.setString(15, client.getActId()) ;
				iS.setString(16, client.getSerieActId()) ;
				iS.setString(17, client.getNrActId()) ;
				iS.executeUpdate();
				iS.close();
				tactId = client.getActId() ;
				tserieActId = client.getSerieActId();
				tnrActId = client.getNrActId();
				tnume = client.getNume();
				tprenume = client.getPrenume();
				xcmdExt = false ;
//		Execute a query
				stmt = xconn.createStatement(); 
				sql = "SELECT * FROM client WHERE cif=?" ;
				PreparedStatement isql = xconn.prepareStatement(sql);
				isql.setString(1, tcif);
				ResultSet rs = isql.executeQuery();
//		Extract data from result set 
				while(rs.next()) {
					Client aclient = new Client(rs.getString("nume"), rs.getString("prenume"), rs.getString("nrIAuto"), rs.getString("dataNastere"), rs.getString("LocNastere"), rs.getString("cetatenie"), rs.getString("loc"), rs.getString("str"), rs.getString("nr"), rs.getString("jud"), rs.getString("tara"), rs.getString("scopCal"), rs.getString("actId"), rs.getString("serieActId"), rs.getString("nrActId"), rs.getString("dataExpActId")) ;
					listCL.add(aclient) ;
				}
			} catch(SQLException se) { 
//		Handle errors for JDBC 
				se.printStackTrace(); 
			}

			return "redirect:rap" ;
//			return "redirect:client" ;
		}
		
		@Autowired
		private SejurRepository sejurRepository ;

		@GetMapping("/sejur")
		public void sejur(Model model) {
			zmodul = "sejur" ;
			model.addAttribute("ymodul", "sejur") ;
			model.addAttribute("erori", erori) ;
			model.addAttribute("nrView", nrView) ;
			model.addAttribute("xcmdExt", xcmdExt) ;
			model.addAttribute("listSJ", listSJ) ;
			model.addAttribute("listSV", listSV) ;
			model.addAttribute("tcif", tcif) ;
			model.addAttribute("an", an) ;
			model.addAttribute("err11", err11) ;
			model.addAttribute("err0", err0) ;
			model.addAttribute("tactId", tactId) ;
			model.addAttribute("tserieActId", tserieActId) ;
			model.addAttribute("tnrActId", tnrActId) ;
			model.addAttribute("tnume", tnume) ;
			model.addAttribute("tprenume", tprenume) ;
			Sejur sejur = new Sejur() ;
			sejur.setDtSosit(ydtSosit) ;
			sejur.setDtPlecat(ydtPlecat) ;
			if (xcmdExt) {
				for ( Sejur i : listSJ) {
					sejur.setDtSosit(i.getDtSosit()) ;
					sejur.setDtPlecat(i.getDtPlecat()) ;
					sejur.setNrCort(i.getNrCort()) ;
					sejur.setNrPCort(i.getNrPCort()) ;
					sejur.setNrPersC(i.getNrPersC()) ;
					sejur.setNrEEC(i.getNrEEC()) ;
					sejur.setNrRulota(i.getNrRulota()) ;
					sejur.setNrPersR(i.getNrPersR()) ;
					sejur.setNrEER(i.getNrEER()) ;
					sejur.setNrAuto(i.getNrAuto()) ;
					sejur.setNrMoto(i.getNrMoto()) ;
   				}
			}
			model.addAttribute("sejur", sejur) ;
		}

		@PostMapping("/err1Sejur")
		public String err1Sejur ( @ModelAttribute Sejur sejur, Model model) {
			err11 = false ;
			err0 = true ;
			erori = false ;
			ydtSosit = sejur.getDtSosit();
			zsezonS = " " ;
			for ( InitC i : listIN) {
				if ( InitC.verifSejur( i.getDela(), i.getPila(), ydtSosit ) ) {
					zsezonS = i.getSezon() ;
					break ;
				}
			}
			if ( zsezonS == " ") {
				ydtSosit = "" ;
				erori = true ;
//		eroare - dtSosit este in extrasezon 
			}			
			return "redirect:sejur" ;
		}

		@PostMapping("/errSejur")
		public String errSejur ( @ModelAttribute Sejur sejur, Model model) {
			zmodul = "sejur" ;
			erori = false ;
			err0 = false ;
			err11 = true ;
			model.addAttribute("ymodul", "sejur") ;
			nrView = false ;
//			ydtSosit = sejur.getDtSosit() ;
			ydtPlecat = sejur.getDtPlecat();
			zsezonP = " " ;
			for ( InitC i : listIN ) {
				if ( InitC.verifSejur( i.getDela(), i.getPila(), ydtPlecat ) ) {
					zsezonP = i.getSezon();
					break ;
				}
			}
			if ( zsezonP == " ") {
				ydtPlecat = "" ;
				erori = true ;
//		eroare - dtPlecat este in extrasezon
			}
			if ( ! erori ) {
				xconn = CcmsNvBvApplication.conn ;
				try {
					stmt = xconn.createStatement();
					ResultSet rsS = stmt.executeQuery("SELECT count(*) FROM sejur") ;
					if ( rsS.next()) {
						stmt = xconn.createStatement();
						sql = "SELECT * FROM sejur WHERE cif=? AND actId=? AND serieActId=? AND nrActId=? AND dtSosit=? AND dtPlecat=?" ;
						PreparedStatement sqlSejur = xconn.prepareStatement(sql);
						sqlSejur.setString(1, tcif);
						sqlSejur.setString(2, tactId) ;
						sqlSejur.setString(3, tserieActId) ;
						sqlSejur.setString(4, tnrActId) ;
						sqlSejur.setString(5, ydtSosit) ;
						sqlSejur.setString(6, ydtPlecat) ;
						ResultSet rs = sqlSejur.executeQuery();
						if ( rs.next()) {
//			exista sejur - se va afisa comanda tip buton-submit MODIFICARE - VALIDARE
							xcmdExt = true ;
							listSJ = new ArrayList<Sejur>() ;
							Sejur asejur = new Sejur(rs.getString("dtSosit"), rs.getString("dtPlecat"), rs.getInt("nrCort"), rs.getInt("nrPCort"), rs.getInt("nrPersC"), rs.getInt("nrEEC"), rs.getInt("nrRulota"), rs.getInt("nrPersR"), rs.getInt("nrEER"), rs.getInt("nrAuto"), rs.getInt("nrMoto")) ;
							listSJ.add(asejur) ;
						}
					} else {
//			nu exista sejur - se va afisa comanda tip buton-submit ADAUGARE
						xcmdExt = false ;
					}
				} catch(SQLException se) { 
//		Handle errors for JDBC 
					se.printStackTrace(); 
				}
			}
			return "redirect:sejur" ;
		}

		@PostMapping("/addSejur")
		public String addSejur(@ModelAttribute Sejur sejur, Model model) {
			zmodul = "sejur" ;
			nrView = true ;
			erori = false ;
			ydtSosit = "" ;
			ydtPlecat = "" ;
			sejurRepository.save(sejur) ;
			
			xconn = CcmsNvBvApplication.conn ;
			try {
				if (xcmdExt) {
					insUpd = "UPDATE sejur SET nrCort=?, nrPCort=?, nrPersC=?, nrEEC=? , nrRulota=?, nrPersR=?, nrEER=?, nrAuto=?, nrMoto=? WHERE cif=? AND actId=? AND serieActId=? AND nrActId=? AND dtSosit=? AND dtPlecat=?" ;
				} else {
					insUpd = "INSERT INTO sejur ( nrCort, nrPCort, nrPersC, nrEEC, nrRulota, nrPersR, nrEER, nrAuto, nrMoto, cif, actId, serieActId, nrActId, dtSosit, dtPlecat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
				}
				PreparedStatement iS = xconn.prepareStatement(insUpd);
 				iS.setInt(1, sejur.getNrCort());
				iS.setInt(2, sejur.getNrPCort()) ;
				iS.setInt(3, sejur.getNrPersC()) ;
				iS.setInt(4, sejur.getNrEEC()) ;
				iS.setInt(5, sejur.getNrRulota()) ;
				iS.setInt(6, sejur.getNrPersR()) ;
				iS.setInt(7, sejur.getNrEER()) ;
				iS.setInt(8, sejur.getNrAuto()) ;
				iS.setInt(9, sejur.getNrMoto()) ;
				iS.setString(10, tcif) ;
				iS.setString(11, tactId) ;
				iS.setString(12, tserieActId) ;
				iS.setString(13, tnrActId) ;
				iS.setString(14, sejur.getDtSosit()) ;
				iS.setString(15, sejur.getDtPlecat()) ;
				iS.executeUpdate();
				iS.close();
				xcmdExt = false ;
//		Execute a query 
				stmt = xconn.createStatement(); 
				sql = "SELECT * FROM sejur WHERE cif=? AND actId=? AND serieActId=? AND nrActId=?" ;
				PreparedStatement isql = xconn.prepareStatement(sql);
				isql.setString(1, tcif);
				isql.setString(2, tactId) ;
				isql.setString(3, tserieActId) ;
				isql.setString(4, tnrActId) ;
				ResultSet rs = isql.executeQuery();
//		Extract data from result set 
				listSJ = new ArrayList<Sejur>() ;
				while(rs.next()) { 
					Sejur asejur = new Sejur(rs.getString("dtSosit"), rs.getString("dtPlecat"), rs.getInt("nrCort"), rs.getInt("nrPCort"), rs.getInt("nrPersC"), rs.getInt("nrEEC"), rs.getInt("nrRulota"), rs.getInt("nrPersR"), rs.getInt("nrEER"), rs.getInt("nrAuto"), rs.getInt("nrMoto")) ;
					listSJ.add(asejur) ;
				}
				listSV = new ArrayList<Sejur>() ;
				for ( Sejur i : listSJ) {
					valoare = 0 ;
					for ( InitC j : listIN) {
						if ( valoare == 0) {
							if ( InitC.verifSejur( j.getDela(), j.getPila(), i.getDtSosit()) && InitC.verifSejur( j.getDela(), j.getPila(), i.getDtPlecat())) {
								nrZile = InitC.nrZile(i.getDtSosit(), i.getDtPlecat()) ;
								valoare = nrZile * ((i.getNrPCort()>4 ? 2 : 1)*j.getCort()*i.getNrCort() + j.getCortPers()*i.getNrPersC() + j.getCortEE()*i.getNrEEC() + j.getRulota()*i.getNrRulota() + j.getRulotaPers()*i.getNrPersR() + j.getRulotaEE()*i.getNrEER() + j.getAuto()*i.getNrAuto() + j.getMoto()*i.getNrMoto()) ;
								Sejur vSejur = new Sejur ( i.getDtSosit(), i.getDtPlecat(), nrZile, valoare) ;
								listSV.add(vSejur) ;
								break ;
							} else {
								if ( InitC.verifSejur( j.getDela(), j.getPila(), i.getDtSosit())) {
									nrZile = 1 + InitC.nrZile( i.getDtSosit(), j.getPila()) ;
									valoare = nrZile * ((i.getNrPCort()>4 ? 2 : 1)*j.getCort()*i.getNrCort() + j.getCortPers()*i.getNrPersC() + j.getCortEE()*i.getNrEEC() + j.getRulota()*i.getNrRulota() + j.getRulotaPers()*i.getNrPersR() + j.getRulotaEE()*i.getNrEER() + j.getAuto()*i.getNrAuto() + j.getMoto()*i.getNrMoto()) ;
									String pila1 = LocalDate.parse(j.getPila(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).plusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString() ;
									Sejur vSejur = new Sejur ( i.getDtSosit(), pila1, nrZile, valoare) ;
									listSV.add(vSejur) ;
								}
							}
						} else {
							if ( InitC.verifSejur( j.getDela(), j.getPila(), i.getDtPlecat() )) {
								nrZile = InitC.nrZile(j.getDela(), i.getDtPlecat()) ;
								valoare = nrZile * ((i.getNrPCort()>4 ? 2 : 1)*j.getCort()*i.getNrCort() + j.getCortPers()*i.getNrPersC() + j.getCortEE()*i.getNrEEC() + j.getRulota()*i.getNrRulota() + j.getRulotaPers()*i.getNrPersR() + j.getRulotaEE()*i.getNrEER() + j.getAuto()*i.getNrAuto() + j.getMoto()*i.getNrMoto()) ;
								Sejur vSejur = new Sejur ( j.getDela(), i.getDtPlecat(), nrZile, valoare) ;
								listSV.add(vSejur) ;
								break ;
							} else {
								nrZile = 1 + InitC.nrZile(j.getDela(), j.getPila()) ;
								valoare = nrZile * ((i.getNrPCort()>4 ? 2 : 1)*j.getCort()*i.getNrCort() + j.getCortPers()*i.getNrPersC() + j.getCortEE()*i.getNrEEC() + j.getRulota()*i.getNrRulota() + j.getRulotaPers()*i.getNrPersR() + j.getRulotaEE()*i.getNrEER() + j.getAuto()*i.getNrAuto() + j.getMoto()*i.getNrMoto()) ;
								String pila1 = LocalDate.parse(j.getPila(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).plusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString() ;
								Sejur vSejur = new Sejur ( j.getDela(), pila1, nrZile, valoare) ;
								listSV.add(vSejur) ;
							}
						}
					}
				}
			} catch(SQLException se) { 
//		Handle errors for JDBC 
				se.printStackTrace(); 
			}
			return "redirect:rap" ;
//			return "redirect:sejur" ;
		}
}
