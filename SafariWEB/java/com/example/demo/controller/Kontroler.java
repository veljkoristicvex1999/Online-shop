package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.KategorijaRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.LicitacijaRepository;
import com.example.demo.repository.PredmetRepository;
import com.example.demo.repository.StanjeRepository;
import com.example.demo.repository.UlogaRepository;

import model.Kategorija;
import model.Korisnik;
import model.Licitacija;
import model.Predmet;
import model.Stanje;
import model.Uloga;

@Controller
@RequestMapping(value = "/kontroler")
public class Kontroler {

	@Autowired
	UlogaRepository ur;
	@Autowired
	KorisnikRepository kr;
	@Autowired
	PredmetRepository pr;
	@Autowired
	KategorijaRepository katrep;
	@Autowired
	StanjeRepository sr;
	@Autowired
	LicitacijaRepository lr;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping(value = "/getUlogas", method = RequestMethod.GET)
	public String getUlogas(HttpServletRequest req) {
		List<Uloga> lista = ur.findAll();
		req.getSession().setAttribute("uloge", lista);

		return "registracija";
	}

	@RequestMapping(value = "/saveKorisnik", method = RequestMethod.POST)
	public String saveKorisnik(String ime, String prezime, String jmbg, String username, String password, String adresa,
			Integer uloga, HttpServletRequest req) {
		Korisnik k = new Korisnik();
		Uloga u = ur.findById(uloga).get();
		k.setAdresa(adresa);
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setJmbg(jmbg);
		k.setUsername(username);
		k.setPassword(password);
		k.setUloga(u);
		kr.save(k);

		return "index";
	}

	@RequestMapping(value = "/getAukcije", method = RequestMethod.GET)
	public String getAukcije(HttpServletRequest req) {
		this.getInfo(req);
		List<Predmet> lista = pr.findAll();
		req.getSession().setAttribute("predmeti", lista);
		List<Double> najveceCene = new ArrayList<Double>();
		for (Predmet predmet : lista) {
			if (predmet.getLicitacijas().size() == 0) {
				najveceCene.add(0.0);
			} else {
				najveceCene.add(predmet.getLicitacijas().get(0).getIznos());
			}
		}
		req.getSession().setAttribute("najveceCene", najveceCene);

		return "PrikazAukcija";
	}

	@RequestMapping(value = "/checkInfo", method = RequestMethod.POST)
	public String checkInfo(String username, String password, HttpServletRequest req) {
		this.getAukcije(req);
		this.getInfo(req);
		Korisnik k = kr.findByUsername(username);
		String message = "";
		if (k != null) {
			if (!(k.getPassword().equals(password))) {
				message = "Pogresna lozinka";
				req.getSession().setAttribute("poruka", message);
				return "index";
			}
			req.getSession().setAttribute("korisnik", k);
			return "regIndex";
		} else {
			message = "Ne postoji korisnik sa tim username-om";
			req.getSession().setAttribute("poruka", message);
			return "index";
		}
	}

	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	public String getInfo(HttpServletRequest req) {
		List<Stanje> stanja = sr.findAll();
		List<Kategorija> kategorije = katrep.findAll();
		req.getSession().setAttribute("stanja", stanja);
		req.getSession().setAttribute("kategorije", kategorije);
		return "dodajPredmet";
	}

	@RequestMapping(value = "/savePredmet", method = RequestMethod.POST)
	public String savePredmet(String naziv, String opis, Integer pocetnaCena, Date rokZavAuk, String slikaUrl,
			Integer stanje, Integer kategorija, HttpServletRequest req) {
		Predmet p = new Predmet();
		Stanje s = sr.findById(stanje).get();
		Kategorija k = katrep.findById(kategorija).get();
		Korisnik kor = (Korisnik) req.getSession().getAttribute("korisnik");
		p.setNaziv(naziv);
		p.setOpis(opis);
		p.setPocetnaCena(pocetnaCena);
		p.setRokZavAuk(rokZavAuk);
		p.setSlikaUrl(slikaUrl);
		p.setStanje(s);
		p.setKategorija(k);
		p.setKorisnik(kor);

		pr.save(p);
		req.getSession().setAttribute("prk", "Uspesno dodat predmet na aukciju!");
		return "dodajPredmet";
	}

	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public String filter(Integer kat, Integer st, String naziv, HttpServletRequest req) {
		List<Predmet> lista = pr.findAll();
		List<Predmet> glavnaLista = new ArrayList<Predmet>();
		
		//kategorija i stanje i naziv
		if (kat != 0 && st != 0 && naziv != null) {
			Kategorija k = katrep.findById(kat).get();
			Stanje s = sr.findById(st).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getKategorija().equals(k) && x.getStanje().equals(s) && x.getNaziv().contains(naziv))
					.collect(Collectors.toList());

		}
		
		//kategorija i stanje
		else if (kat!=0 && st!=0 && naziv==null) {
			Kategorija k = katrep.findById(kat).get();
			Stanje s = sr.findById(st).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getKategorija().equals(k) && x.getStanje().equals(s))
					.collect(Collectors.toList());	
		}
		
		//kategorija i naziv
		else if (kat!=0 && st==0 && naziv!=null) {
			Kategorija k = katrep.findById(kat).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getKategorija().equals(k) && x.getNaziv().contains(naziv))
					.collect(Collectors.toList());
		}
		
		//stanje i naziv 
		else if (kat==0 && st!=0 && naziv!=null) {
			Stanje s = sr.findById(st).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getStanje().equals(s) && x.getNaziv().contains(naziv))
					.collect(Collectors.toList());
		}
		
		//kategorija
		else if (kat!=0 && st==0 && naziv==null) {
			Kategorija k = katrep.findById(kat).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getKategorija().equals(k))
					.collect(Collectors.toList());
		}
		
		//stanje
		else if (kat==0 && st!=0 && naziv==null) {
			Stanje s = sr.findById(st).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getStanje().equals(s))
					.collect(Collectors.toList());
			
		}
		
		//naziv
		else if (kat==0 && st==0 && naziv!=null) {
			glavnaLista = lista.stream()
					.filter(x ->x.getNaziv().contains(naziv))
					.collect(Collectors.toList());	
		}
		
		req.getSession().setAttribute("predmeti", glavnaLista);
		return "regIndex";
	}
	
	//Neregistrovani
	
	@RequestMapping(value = "/filter1", method = RequestMethod.GET)
	public String filter1(Integer kat, Integer st, String naziv, HttpServletRequest req) {
		List<Predmet> lista = pr.findAll();
		List<Predmet> glavnaLista = new ArrayList<Predmet>();
		
		//kategorija i stanje i naziv
		if (kat != 0 && st != 0 && naziv != null) {
			Kategorija k = katrep.findById(kat).get();
			Stanje s = sr.findById(st).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getKategorija().equals(k) && x.getStanje().equals(s) && x.getNaziv().contains(naziv))
					.collect(Collectors.toList());

		}
		
		//kategorija i stanje
		else if (kat!=0 && st!=0 && naziv==null) {
			Kategorija k = katrep.findById(kat).get();
			Stanje s = sr.findById(st).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getKategorija().equals(k) && x.getStanje().equals(s))
					.collect(Collectors.toList());	
		}
		
		//kategorija i naziv
		else if (kat!=0 && st==0 && naziv!=null) {
			Kategorija k = katrep.findById(kat).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getKategorija().equals(k) && x.getNaziv().contains(naziv))
					.collect(Collectors.toList());
		}
		
		//stanje i naziv 
		else if (kat==0 && st!=0 && naziv!=null) {
			Stanje s = sr.findById(st).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getStanje().equals(s) && x.getNaziv().contains(naziv))
					.collect(Collectors.toList());
		}
		
		//kategorija
		else if (kat!=0 && st==0 && naziv==null) {
			Kategorija k = katrep.findById(kat).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getKategorija().equals(k))
					.collect(Collectors.toList());
		}
		
		//stanje
		else if (kat==0 && st!=0 && naziv==null) {
			Stanje s = sr.findById(st).get();
			glavnaLista = lista.stream()
					.filter(x -> x.getStanje().equals(s))
					.collect(Collectors.toList());
			
		}
		
		//naziv
		else if (kat==0 && st==0 && naziv!=null) {
			glavnaLista = lista.stream()
					.filter(x ->x.getNaziv().contains(naziv))
					.collect(Collectors.toList());	
		}
		
		req.getSession().setAttribute("predmeti", glavnaLista);
		return "PrikazAukcija";
	}
	@RequestMapping(value = "/getLicitacije", method = RequestMethod.GET)
	public String getLicitacije(Integer idPr, HttpServletRequest req) {
		Predmet p = pr.findById(idPr).get();
		double prvi = p.getPocetnaCena();
		List<Licitacija> lista = lr.findByPredmet(p, Sort.by("iznos").descending());
		if (lista.size()!=0) {
			 prvi = lista.get(0).getIznos();
		}
		req.getSession().setAttribute("najveca", prvi);
		req.getSession().setAttribute("licitacije", lista);
		req.getSession().setAttribute("pr", p);
		return "prikazLicitacija";
	}
	
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String logOut(HttpServletRequest req) {
		req.getSession().removeAttribute("korisnik");
		req.getSession().removeAttribute("poruka");
		return "index";
	}
	
	@RequestMapping(value = "/dodajPonudu", method = RequestMethod.POST)
	public String dodajPonudu(double ponuda, HttpServletRequest req) {
		Predmet p = (Predmet)req.getSession().getAttribute("pr");
		Korisnik k = (Korisnik)req.getSession().getAttribute("korisnik");
		Licitacija l = new Licitacija();
		l.setIznos(ponuda);
		l.setKorisnik(k);
		l.setPredmet(p);
		lr.save(l);
		
		return "regIndex";
	}
	
	@RequestMapping(value = "/svojeAukcije", method = RequestMethod.GET)
	public String svojeAukcije(HttpServletRequest req) {
		Korisnik k = (Korisnik)req.getSession().getAttribute("korisnik");
		List<Predmet> svoji = pr.findByKorisnik(k);
		List<Predmet> nova = new ArrayList<Predmet>();
		Date date = new Date();
		for (Predmet predmet : svoji) {
			if (date.compareTo(predmet.getRokZavAuk())>0) {
				nova.add(predmet);
			}
		}
		req.getSession().setAttribute("svojiPredmeti", nova);
		
		
		return "svojeAukcije";
	}
	

}
