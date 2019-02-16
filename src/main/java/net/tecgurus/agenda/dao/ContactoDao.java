package net.tecgurus.agenda.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.tecgurus.agenda.mappers.ContactoMapper;
import net.tecgurus.agenda.model.Contacto;

@Component
public class ContactoDao {

	@Autowired
	private ContactoMapper contactoMapper;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public void saveImage(String imagePath, Integer idContacto, Integer idUser) {
		contactoMapper.saveImage(imagePath, idContacto, idUser);
	}
	
	public List<Contacto> getContactByPage(int idContacto, int offset, int limit) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("getAllByUser", idContacto, new RowBounds(offset, limit));
	}	
	
	public List<Contacto> search(int idUsuario, String search, int offset, int limit){
		
		System.out.println("Dao search: "+search);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("search", search);
		parameters.put("id", idUsuario);
		
		return sqlSession.selectList("search", parameters, new RowBounds(offset, limit));
	}
	
	public Long searchCount(String search, Integer id) {
		return contactoMapper.searchCount(search, id);
	}

	public void insertContact(Contacto contacto) {
		contactoMapper.insertContact(contacto);
	}

	public List<Contacto> getAllByUser(Integer idUsuario) {
		return contactoMapper.getAllByUser(idUsuario);
	}
	
	public Long contactCountByUser(Integer idUser) {
		return contactoMapper.contactCountByUser(idUser);
	}

	public Contacto getById(Integer idUser, Integer idContact) {
		return contactoMapper.getById(idUser, idContact);
	}

	public void deleteById(Integer idUser, Integer idContact) {
		contactoMapper.deleteById(idUser, idContact);
	}

	public void updateContact(Contacto contacto) {
		contactoMapper.updateContact(contacto);
	}
}
