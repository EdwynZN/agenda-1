package net.tecgurus.agenda.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.tecgurus.agenda.dao.ContactoDao;
import net.tecgurus.agenda.model.Contacto;

@Component
public class ContactoService {
	
	@Autowired
	private ContactoDao contactoDao;
	
	public void insertContact(Contacto contacto) {
		contactoDao.insertContact(contacto);
	}
	
	public void saveImage(String imagePath, Integer idContacto, Integer idUser) {
		contactoDao.saveImage(imagePath, idContacto, idUser);
	}
	
	public List<Contacto> getContactByPage(int idContacto, int offset, int limit) {
		return contactoDao.getContactByPage(idContacto, offset, limit);
	}
	
	public Map<String, Object> search(int idUsuario, String search, int page, int limit){
		System.out.println("Service search: "+search);
		
		long elements = contactoDao.searchCount(search, idUsuario);
		
		double totalPages = (double)elements/(double)limit;
		if(totalPages%1 != 0) {
			totalPages++;
			System.out.println("Pagina redondeada");
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("pages", (int)totalPages);
		
		System.out.println("Pages: "+(int)totalPages);
		int offset = (page-1)*limit;
		
		response.put("contactos",contactoDao.search(idUsuario, search, offset, limit));
		return response;
	}
	
	public Long searchCount(String search, Integer id) {
		return contactoDao.searchCount(search, id);
	}
	
	public List<Contacto> getAllByUser(Integer idUsuario){
		return contactoDao.getAllByUser(idUsuario);
	}	
	
	public Long contactCountByUser(Integer idUser) {
		return contactCountByUser(idUser);
	}

	public Contacto getById(Integer idUser, Integer idContact) {
		return contactoDao.getById(idUser, idContact);
	}

	public void deleteById(Integer idUser, Integer idContact) {
		contactoDao.deleteById(idUser, idContact);
	}
	
	public void updateContact(Contacto contacto) {
		contactoDao.updateContact(contacto);
	}
}
