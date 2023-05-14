package it.prova.myebay;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;

@SpringBootApplication
public class MyebayApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	
	@Autowired
	private CategoriaService categoriaServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(MyebayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", "ROLE_CLASSIC_USER"));
		}

		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Matteo", "Castiello", LocalDate.now());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente classicUser = new Utente("user", "user", "Giovanni", "Bianchi", LocalDate.now());
			classicUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser);
			utenteServiceInstance.changeUserAbilitation(classicUser.getId());
		}

		if (utenteServiceInstance.findByUsername("user1") == null) {
			Utente classicUser1 = new Utente("user1", "user1", "Anna", "Rossi", LocalDate.now());
			classicUser1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser1);
			utenteServiceInstance.changeUserAbilitation(classicUser1.getId());
		}

		if (utenteServiceInstance.findByUsername("user2") == null) {
			Utente classicUser2 = new Utente("user2", "user2", "Marco", "Franchi", LocalDate.now());
			classicUser2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser2);
			utenteServiceInstance.changeUserAbilitation(classicUser2.getId());
		}
		
		if (categoriaServiceInstance.cercaPerDescrizione("Motori") == null) {
			Categoria categoria1 = new Categoria("Motori", "001");
			categoriaServiceInstance.inserisciNuovo(categoria1);
		}
		
		if (categoriaServiceInstance.cercaPerDescrizione("Gaming") == null) {
			Categoria categoria2 = new Categoria("Gaming", "002");
			categoriaServiceInstance.inserisciNuovo(categoria2);
		}
		
		if (categoriaServiceInstance.cercaPerDescrizione("Elettronica") == null) {
			Categoria categoria3 = new Categoria("Elettronica", "003");
			categoriaServiceInstance.inserisciNuovo(categoria3);
		}
		
	}

}
