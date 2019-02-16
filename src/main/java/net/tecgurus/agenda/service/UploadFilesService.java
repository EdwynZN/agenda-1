package net.tecgurus.agenda.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.tecgurus.agenda.dao.ContactoDao;
import net.tecgurus.agenda.model.Contacto;

@Component
public class UploadFilesService {
	@Autowired
	private ContactoDao contactoDao;
	
	public String cargarImagen(HttpServletRequest request, Integer idContacto, Integer idUser) {
		String valorRetorno = "";
		
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			
			for(FileItem item : items) {
				String nombreImagen = item.getName();
				long tamanoImagen = item.getSize();
	
				System.out.println("tamano: "+tamanoImagen);
				System.out.println("Field Name: "+item.getFieldName());
				System.out.println("ContenType: "+item.getContentType());
				
				Contacto contacto = contactoDao.getById(idUser, idContacto);
				
				//Validar archivo de carga
				File archivoCargado = new File("/Users/JoaquinCoronado/eclipse-workspace-guide/agenda/src/main/webapp/img", nombreImagen);
				item.write(archivoCargado);
				valorRetorno = "Imagen guardada correctamente";
				
				//Guardar imagen en la base de datos.
				String imagePath = "http://localhost:8080/img/"+nombreImagen;
				contactoDao.saveImage(imagePath, idContacto, idUser);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			valorRetorno = "Erro al cargar imagen";
		}
		
		return valorRetorno;
	}
}
