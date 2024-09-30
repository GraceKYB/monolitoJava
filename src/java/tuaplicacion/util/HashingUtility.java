/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuaplicacion.util;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author domes
 */
public class HashingUtility {
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    
}
