package net.tecgurus.agenda.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.gson.Gson;

import net.tecgurus.agenda.dao.ContactoDao;
import net.tecgurus.agenda.model.Contacto;
import net.tecgurus.agenda.service.UploadFilesService;

/**
 * Servlet implementation class UploadFilesController
 */
@WebServlet("/UploadFilesController")
public class UploadFilesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UploadFilesService uploadFilesService;
	
	@Autowired
	private ContactoDao contactoDao;

	// Permite usar la anotación Autowired en el contexto de los servlets.
	public void init(ServletConfig servletConfig) {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletConfig.getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		
		int idUser = Integer.parseInt(request.getParameter("idUser"));
		int idContacto = Integer.parseInt(request.getParameter("idContacto"));

		System.out.println("idUser: " + idUser);
		System.out.println("idContact: " + idContacto);

		String valorRetorno = "";

		try {
			//obtenemos todas las partes de nuestro request multipart
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			
			//Se itera sobre cada parte de la petición.
			for (FileItem item : items) {
				String nombreImagen = item.getName();
				long tamanoImagen = item.getSize();
				String[] arrayNombre = nombreImagen.split("\\.");
				String extencion = arrayNombre[arrayNombre.length-1];
				
				
				//System.out.println("Extencion: " + extencion);

				System.out.println("tamano: " + tamanoImagen);
				System.out.println("Field Name: " + item.getFieldName());
				System.out.println("ContenType: " + item.getContentType());

				Contacto contacto = contactoDao.getById(idUser, idContacto);
				
				// Validar archivo de carga
				if(contacto.getImagen() != null) {
					String[] image = contacto.getImagen().split("/");
					File limpiarArchivo = new File("/Users/JoaquinCoronado/tomcat/apache-tomcat-9.0.10/webapps/public/images/"+image[image.length-1]);
					limpiarArchivo.delete();
				}
				
				String nameImage = contacto.getEmail() + new Date().getTime() + "." + extencion;
				
				// Validar archivo de carga
				//File archivoCargado = new File("/Users/JoaquinCoronado/eclipse-workspace-guide/agenda/src/main/webapp/img", nameImage);
				File archivoCargado = new File("/Users/JoaquinCoronado/tomcat/apache-tomcat-9.0.10/webapps/public/images", nameImage);
				item.write(archivoCargado);
				valorRetorno = "Imagen guardada correctamente";
				

				// Guardar imagen en la base de datos.
				String imagePath = "http://localhost:8080/img/" + nameImage;
				contactoDao.saveImage(imagePath, idContacto, idUser);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			valorRetorno = "Erro al cargar imagen";
		}

		// Integer idContacto = Integer.valueOf(idUserStr);
		// Integer idUser = Integer.valueOf(idContactoStr);

		// System.out.println(uploadFilesService.cargarImagen(request, idContacto,
		// idUser));

		/*
		 * switch(accion) { case "subirFoto":
		 * System.out.println(uploadFilesService.cargarImagen(
		 * "Usuarios/joaquinCoronado/uploads", request)); break; }
		 */
	}

}
