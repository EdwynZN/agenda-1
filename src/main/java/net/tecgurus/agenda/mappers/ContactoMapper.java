package net.tecgurus.agenda.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.tecgurus.agenda.model.Contacto;

public interface ContactoMapper {
	
	/**
	 * 
	 * @param contacto
	 */
	@Insert("INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario) " + 
			"values( #{nombre}, #{apellido}, #{direccion}, #{telefono},#{email}, #{fechaNacimiento}, #{id_usuario})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insertContact(Contacto contacto);
	
	/**
	 * 
	 * @param search
	 * @param id
	 * @return
	 */
	public List<Contacto> search(@Param("search") String search, Integer id);
	
	/**
	 * 
	 * @param search
	 * @param id
	 * @return
	 */
	public Long searchCount(@Param("search") String search, @Param("id") Integer id);
	
	/**
	 * 
	 * @param imagePath
	 */
	@Update("UPDATE contactos set imagen = #{imagePath} WHERE id = #{idContacto} and id_usuario = #{idUser}")
	public void saveImage(@Param("imagePath") String imagePath, @Param("idContacto") Integer id, @Param("idUser") Integer idUser);
	
	/**
	 * 
	 * @param idUsuario
	 * @return
	 */
	@Select("select * from contactos where id_usuario = #{idUsuario}")
	public List<Contacto> getAllByUser(Integer idUsuario);
	
	@Select("select count(*) from contactos where id_usuario = #{idUser}")
	public Long contactCountByUser(Integer idUser);
		
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Select("select * from contactos where id = #{idContact} and id_usuario=#{idUser}")
	public Contacto getById(@Param("idUser") Integer idUser, @Param("idContact") Integer idContact);
	
	/**
	 * 
	 */
	@Delete("delete from contactos where id = #{idContact} and id_usuario = #{idUser} ")
	public void deleteById(@Param("idUser") Integer idUser, @Param("idContact") Integer idContact);
	
	/**
	 * 
	 * @param contacto
	 */
	@Update("update contactos set " + 
			"nombre = #{nombre}, " + 
			"apellido = #{apellido}, " + 
			"direccion = #{direccion}, " + 
			"telefono =  #{telefono}, " + 
			"email =  #{email}, " + 
			"fechaNacimiento = #{fechaNacimiento} " + 
			"where id = #{id};")
	public void updateContact(Contacto contacto);
}
