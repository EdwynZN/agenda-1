package net.tecgurus.agenda.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;
import net.tecgurus.agenda.dao.ContactoDao;
import net.tecgurus.agenda.model.Contacto;

/***************************************************************
 * ----------------------- !!CUIDADO!! ------------------------
 * Guardar archivos dentro del servidor es una mala practica 
 * por lo general se contrata un servicio de Amazon o similar
 * para guardar nuestros archivos y acceder a ellos, esto es
 * un ejemplo para que vean como cargar archivos con java.
 ***************************************************************/

@Component
public @Log class UploadFilesService {
	
	@Autowired
	private ContactoDao contactoDao;
	
	/**
	 * Guarda un archivo a partir de una petición HTTP Multipart con ayuda del objeto @Code HttpServletRequest.
	 * Una vez guardada la imagen se asigna al usuario especificado, si el usuario ya tiene una imgen asiganda se 
	 * sobre escribe.
	 * @param request HttpServletRequest peticion http multipart.
	 * @param idContacto Integer con el Id del contacto.
	 * @param idUser Integet con el ID del usuario.
	 * @return boolean true en caso de guardar la imagen con exito.
	 * @throws BadHttpRequest en caso de tener problemas al cargar la imagen.
	 */
	public boolean cargarImagen(HttpServletRequest request, Integer idContacto, Integer idUser) throws BadHttpRequest {
		
		try {
			
			//obtenemos todas las partes de nuestro request multipart
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			
			
			/***************************************************************
			 * Cada Item en el arreglo es un parametro dentro de la petición, 
			 * se espera una imagen por cada item.
			 ***************************************************************/
			
			//Se itera sobre cada parte de la petición.
			for (FileItem item : items) {
				
				//Obtenemos la información de cada item.
				String nombreImagen = item.getName();
				long tamanoImagen = item.getSize();
				
				//Separamos el mobre del archivo por punto y se obtiene la última parte
				//para guardar la extención del archivo.
				String[] arrayNombre = nombreImagen.split("\\.");
				String extencion = arrayNombre[arrayNombre.length-1];

				log.info("tamano: " + tamanoImagen);
				log.info("Field Name: " + item.getFieldName());
				log.info("ContenType: " + item.getContentType());

				Contacto contacto = contactoDao.getById(idUser, idContacto);
				
				//En caso de que el contacto yatengao una foto se borra del servidor.
				if(contacto.getImagen() != null) {
					String[] image = contacto.getImagen().split("/");
					File limpiarArchivo = new File("/Users/JoaquinCoronado/tomcat/apache-tomcat-9.0.10/webapps/public/images/"+image[image.length-1]);
					limpiarArchivo.delete();
				}
				
				//Se genera elnombre de la foto con el correo del usuario y la fecha actual 
				//en milisegundos para evitar problemas de cache en al front.
				String nameImage = contacto.getEmail() + new Date().getTime() + "." + extencion;
				
				// Se crea la instancia de un archivo en memoria con al ruta donde se guardará.
				File archivoCargado = new File("/Users/JoaquinCoronado/tomcat/apache-tomcat-9.0.10/webapps/public/images", nameImage);
				
				//Se guarda la imagen en la ruta definida tomando la información del item.
				item.write(archivoCargado);

				// Se asigna una url con la que se pueda acceder a la iamgen por el servidor.
				String imagePath = "http://localhost:8080/img/" + nameImage;
				
				//Se le asigna la nueva imagen al contacto.
				contactoDao.saveImage(imagePath, idContacto, idUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			//Lanza un error en caso de no poder guardar la foto.
			throw new BadHttpRequest(e);
		}
		return true;
	}
}
