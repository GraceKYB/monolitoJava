/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Confi.Conexion;
import Model.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author domes
 */
public class usuarioDAO {
    public int registrarUsuario(usuario usuario) {
        int resultado = 0;
        try (Connection conn = Conexion.getConnection()) {
            // Actualiza la consulta SQL para reflejar la estructura actual de la base de datos
            String sql = "INSERT INTO tbl_usuario (usu_nombre, usu_cedula, usu_direccion, usu_correo, usu_contra_enc, usu_fecha_creacion, usu_estado, tusu_id) VALUES (?, ?, ?, ?, ?, CURDATE(), ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario.getNombre());
                stmt.setString(2, usuario.getCedula());
                stmt.setString(3, usuario.getDireccion());
                stmt.setString(4, usuario.getCorreo());
                stmt.setString(5, usuario.getContraseñaEnc()); // Solamente usamos la contraseña encriptada
                stmt.setString(6, "activo");
                stmt.setInt(7, usuario.getTipoUsuarioId());
                resultado = stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Puedes añadir más métodos aquí para gestionar logins, actualizaciones, etc.
}

