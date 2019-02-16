package net.tecgurus.agenda.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import net.tecgurus.agenda.service.UploadFilesService;

/**
 * Servlet implementation class UploadFilesController
 */
@WebServlet("/UploadFilesController")
public class UploadFilesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UploadFilesService uploadFilesService;

	// Permite usar la anotación Autowired en el contexto de los servlets.
	public void init(ServletConfig servletConfig) {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletConfig.getServletContext());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		//Obtenemos los parametros que llegan por la URL.
		int idUser = Integer.parseInt(request.getParameter("idUser"));
		int idContacto = Integer.parseInt(request.getParameter("idContacto"));
		
		try {
			uploadFilesService.cargarImagen(request, idContacto, idUser);
		} catch (BadHttpRequest e) {
			e.printStackTrace();
		}
	}

}
