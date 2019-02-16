package net.tecgurus.agenda.dao;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.tecgurus.agenda.model.Contacto;
import net.tecgurus.agenda.service.ContactoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/applicationContext.xml"} )
public class ContactoDaoTest {
	
	@Autowired
	ContactoDao contactoDao;
	@Autowired
	ContactoService contactoService;
	
	@Test
	public void insertContact() {
		Contacto contacto = new Contacto();
		contacto.setApellido("nuevo");
		contacto.setDireccion("nuevo");
		contacto.setEmail("nuevo");
		contacto.setFechaNacimiento(new Date());
		contacto.setId_usuario(1);
		contacto.setNombre("nuevo");
		contacto.setTelefono("nuevo");
		contactoDao.insertContact(contacto);
		contactoDao.getAllByUser(1).forEach(System.out::println);
	}
	
	
	@Test
	public void pagination() {
		int idUser= 1;
		int page = 2;
		int limit = 8;
		
		long elements = contactoDao.contactCountByUser(idUser);
		
		double totalPages = (double)elements/(double)limit;
		if(totalPages%1 != 0) {
			totalPages++;
			System.out.println("Pagina redondeada");
		}
		
		System.out.println("Pages: "+(int)totalPages);
		int offset = (page-1)*limit;

		contactoDao.getContactByPage(idUser,offset, limit).forEach(System.out::println);
	}
	
	@Test
	public void search(){
		String search = "coronado";
		int idUser= 1;
		int page = 1;
		int limit = 8;
		contactoService.search(idUser, search, page, limit).forEach((k, v) -> {
			System.out.println("k: "+k+" - v: "+v);
		});
	}
	
	@Test
	public void getAllByUser() {
		contactoDao.getAllByUser(1).forEach(System.out::println);
	}
	
	@Test
	public void getById() {
		System.out.println(contactoDao.getById(1,3));
	}
	
	@Test
	public void updateContact() {
		Contacto contacto = contactoDao.getById(1,3);
		contacto.setNombre("editado");
		
		contactoDao.updateContact(contacto);
		System.out.println(contactoDao.getById(1,3));
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1995);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 19);
		
		contacto.setFechaNacimiento(calendar.getTime());
		System.out.println("EDAD: "+contacto.getEdad());
		
		contacto.setNombre("Claudia Adriana");
		contactoDao.updateContact(contacto);
	}

}
