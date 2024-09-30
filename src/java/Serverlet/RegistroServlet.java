/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serverlet;

import DAO.usuarioDAO;
import Model.usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import tuaplicacion.util.HashingUtility;

/**
 *
 * @author domes
 */
@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            usuario usuario = new usuario();
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setCedula(request.getParameter("cedula"));
            usuario.setDireccion(request.getParameter("direccion"));
            usuario.setCorreo(request.getParameter("correo"));
            usuario.setContraseñaEnc(HashingUtility.hashPassword(request.getParameter("contraseña")));
            usuario.setTipoUsuarioId(Integer.parseInt(request.getParameter("tipo_usuario")));

            usuarioDAO usuarioDAO = new usuarioDAO();
            int resultado = usuarioDAO.registrarUsuario(usuario);

            if (resultado == 1) {
                response.sendRedirect("Login.jsp"); // Redirigir al login tras el registro exitoso
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().println("Error en el registro del usuario.");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Error en el formato del tipo de usuario.");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error interno del servidor: " + e.getMessage());
        }

    }
}
