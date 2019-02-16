<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- Se agrega la libreria de JSTL -->

<form id="formUserInput">
	<div class="row margin-ligth justify-content-center">
	    <div class="col-12 col-lg-4 text-center avatar-container margin-ligth align-self-center">
	      <div>  
		        <img id="profileImage" class="img-fluid rounded" style="margin-bottom: 25px;" height="230px" width="230px" src="<c:url value='/img/perfil.png'/>" alt="avatar">
		        <div class="input-group mb-3 d-block justify-content-center">
		            <div class="row justify-content-center">
		                <div class="col-12">
		                    <h4>Foto de perfil</h4>
		                    <div class="input-group d-block justify-content-center">
		                        <label class="input-group-btn">
		                            <div class="row justify-content-center">
		                                <div class="col">
		                                    <span class="btn btn-primary">
	                                        	Seleccionar imagen
	                                        	<input type="file" id="inputSubir" style="display: none;">
	                                        </span>
	                                    </div>
	                                </div>
	                            </label>
	                            	<button class="btn btn-primary" type="button" id="btnSubir" >subir</button>
	                        </div>
	                    </div>
	                </div>
	            </div>
            </div>
            
        </div>
	        <div class="col-12 col-lg-6 align-self-center avatar-container margin-ligth ">
	            <div action="">
	                <div class="form-row">
	                    <div class="form-group col-sm-6">
	                        <label for="nombre">Nombre</label>
	                        <input type="text" class="form-control is-valid" placeholder="Nombre" name="nombre" id="nombre">
	                    </div>
	                    <div class="form-group col-sm-6">
	                        <label for="apellidos">Apellidos</label>
	                        <input type="text" class="form-control" placeholder="Apellidos" name="apellidos" id="apellidos">
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="direccion">Dirección</label>
	                    <input type="text" class="form-control" placeholder="5 de Mayo #515, Col. Analco..." name="direccion" id="direccion">
	                </div>
	                <div class="form-row">
	                    <div class="form-group col-sm-6">
	                        <label for="telefono">Teléfono</label>
	                        <input type="text" class="form-control" placeholder="Teléfono" name="telefono" id="telefono">
	                    </div>
	                    <div class="form-group col-sm-6">
	                        <label for="email">Email</label>
	                        <input type="email" class="form-control" placeholder="Email" name="email" id="email">
	                    </div>
	                </div>
	                <div class="form-row">
	                    <div class="form-group col-12">
	                        <label for="cumple">Cumpleaños</label>
	                        <input type="date" class="form-control" name="cumple" id="cumple">                            
	                    </div>
	                </div>
	                <input id="accion" type="hidden" value="agregarContacto" name="accion"/>
	                <input id="idUsuario" type="hidden" value="1" name="idUsuario"/>
	                <!-- div class="form-group">
	                    <label for="descripcion">Acerca de mi</label>
	                    <textarea class="form-control" name="descripcion" id="descripcion"></textarea>
	                </div -->
	            </div>
	        </div>
	    </div>
	</form>