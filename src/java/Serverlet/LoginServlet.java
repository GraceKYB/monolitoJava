/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serverlet;

import Confi.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        String password = request.getParameter("contraseña");
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.getConnection();
            String query = "SELECT usu_id, usu_contra_enc, tusu_id FROM tbl_usuario WHERE usu_cedula = ? AND usu_estado = 'activo'";
            ps = con.prepareStatement(query);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String storedPassword = rs.getString("usu_contra_enc");
                if (BCrypt.checkpw(password, storedPassword)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("tusu_id", rs.getInt("usu_id"));
                    
                    // Redireccionar según el tipo de usuario
                    int tipoUsuario = rs.getInt("tusu_id");
                    if (tipoUsuario == 1) {
                        this.getServletConfig().getServletContext().getRequestDispatcher("/Admin.jsp").forward(request, response);
                    } else if (tipoUsuario == 2) {
                        this.getServletConfig().getServletContext().getRequestDispatcher("/Usuario.jsp").forward(request, response);
                    } else {
                        // Tipo de usuario desconocido, manejar como desees
                        request.setAttribute("error", "Tipo de usuario desconocido.");
                        request.getRequestDispatcher("Login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "Credenciales inválidas.");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Usuario no encontrado o inactivo.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error de acceso a la base de datos. Por favor, intente nuevamente.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error de sistema. Por favor, intente nuevamente.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}