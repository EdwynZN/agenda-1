package net.tecgurus.agenda.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.gson.Gson;

import net.tecgurus.agenda.model.Contacto;
import net.tecgurus.agenda.service.ContactoService;

/**
 * Servlet implementation class ContactoController
 */
@WebServlet("/ContactoController")
public class ContactoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContactoService contactoService;
	
    //Permite usar la anotación Autowired en el contexto de los servlets.
    public void init (ServletConfig servletConfig) {
    	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletConfig.getServletContext());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		System.out.println("accion: "+accion);
		
		if(accion.equals("traerContactos")) {
			String id = request.getParameter("idUser");
			String limit = request.getParameter("limit");
			String offset = request.getParameter("offset");
			List<Contacto> contactos= contactoService.getContactByPage(Integer.valueOf(id), Integer.valueOf(offset), Integer.valueOf(limit));
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(new Gson().toJson(contactos));
			response.setStatus(200);
			
		} else if(accion.equals("traerContacto")) {
			String idUser = request.getParameter("idUser");
			String idContact = request.getParameter("idContact");
			Contacto contacto = contactoService.getById( Integer.parseInt(idUser), Integer.parseInt(idContact));
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(new Gson().toJson(contacto));
			response.setStatus(200);
			
		} else if(accion.equals("search")) {
			String id = request.getParameter("idUser");
			String limit = request.getParameter("limit");
			String page = request.getParameter("page");
			String search = request.getParameter("search");
			Map<String, Object> contactos = contactoService.search(Integer.valueOf(id), search, Integer.valueOf(page), Integer.valueOf(limit));
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(new Gson().toJson(contactos));
			response.setStatus(200);
		} else if(accion.equals("agregarContacto")) {
			System.out.println("agregar contacto");
			
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String email = request.getParameter("email");
			String telefono = request.getParameter("telefono");
			String direccion = request.getParameter("direccion");
			String fechaNacimiento = request.getParameter("cumple");
			String idUsuario = request.getParameter("idUsuario");
			System.out.println("fecha: "+fechaNacimiento);
			Date currentDate = null;
			try {
				currentDate =new SimpleDateFormat("yyy-MM-dd").parse(fechaNacimiento);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Contacto contacto = new Contacto();
			contacto.setNombre(nombre);
			contacto.setApellido(apellidos);
			contacto.setEmail(email);
			contacto.setTelefono(telefono);
			contacto.setDireccion(direccion);
			contacto.setFechaNacimiento(currentDate);
			contacto.setId_usuario(Integer.parseInt(idUsuario));
			
			contactoService.insertContact(contacto);
			
			System.out.println("agregar contacto");
			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(contacto));
			response.setCharacterEncoding("utf-8"); 
			response.setStatus(200);
			
		} else if(accion.equals("eliminarContacto")) {
			String idUser = request.getParameter("idUser");
			String idContact = request.getParameter("idContact");
			contactoService.deleteById( Integer.parseInt(idUser), Integer.parseInt(idContact));
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(new Gson().toJson("Contato eliminado"));
			response.setStatus(200);
			
		} else if(accion.equals("actualizarContacto")) {
			System.out.println("Actualizar");
			Integer id = Integer.parseInt(request.getParameter("id"));
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String email = request.getParameter("email");
			String telefono = request.getParameter("telefono");
			String direccion = request.getParameter("direccion");
			String fechaNacimiento = request.getParameter("cumple");
			Integer idUsuario = Integer.valueOf(request.getParameter("idUsuario"));
			
			System.out.println("fecha: "+fechaNacimiento);
			Date currentDate = null;
			try {
				currentDate =new SimpleDateFormat("yyy-MM-dd").parse(fechaNacimiento);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Contacto contacto = new Contacto();
			contacto.setId(id);
			contacto.setNombre(nombre);
			contacto.setApellido(apellidos);
			contacto.setEmail(email);
			contacto.setTelefono(telefono);
			contacto.setDireccion(direccion);
			contacto.setFechaNacimiento(currentDate);
			contacto.setId_usuario(idUsuario);
			
			contactoService.updateContact(contacto);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(new Gson().toJson(contactoService.getById(idUsuario, id)));
			response.setStatus(200);
			
		}else {
			System.out.println("no match index");
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("No match index");
			response.setStatus(404);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
