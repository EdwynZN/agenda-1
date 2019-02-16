package net.tecgurus.agenda.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.tecgurus.agenda.mappers.ContactoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/applicationContext.xml"} )
public class ContactoMapperTest {
	
	@Autowired
	private ContactoMapper contactoMapper;
	
	@Test
	public void getAllByUser() {
		contactoMapper.getAllByUser(1).forEach(System.out::println);
	}
	
	@Test
	public void contactCountByUser() {
		System.out.println(contactoMapper.contactCountByUser(1));
	}
	
	@Test
	public void searchCount() {
		System.out.println(contactoMapper.searchCount("coronado", 1));
	}
}
