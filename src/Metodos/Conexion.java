package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {

    public static boolean login(ResultSet rs, Statement st, String nombre, String contrseña) {
        int cont = 0;

        try {
            rs = st.executeQuery("select * from usuarios");
            while (rs.next()) {
                if (nombre.equals(rs.getString("Nombre")) && contrseña.equals(rs.getString("Contraseña"))) {
                    cont++;
                    return true;
                }
            }
            if (cont == 0) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                System.out.println("Usuario o contraseña incorrectos");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static void mostrar(Statement st, ResultSet rs) {
        try {
            rs = st.executeQuery("select * from usuarios");
            while (rs.next()) {
                System.out.println("Usuario:" + rs.getString("Nombre") + "\nContraseña: " + rs.getString("contraseña") + "\n");
            }
            //con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean insertar(PreparedStatement ps, String nombre, String contraseña, ResultSet rs, Statement st) {
        int cont = 0;
        try {
            rs = st.executeQuery("select * from usuarios");
            while (rs.next()) {
                if (nombre.equals(rs.getString("Nombre"))) {
                    cont++;
                    return true;
                }
            }
            if (cont > 0) {
                JOptionPane.showMessageDialog(null, "Ese nombre ya esta en uso");
                System.out.println("Hola");
            } else {
                ps.setString(1, nombre);
                ps.setString(2, contraseña);
                ps.executeUpdate();
                System.out.println("cuenta creada");
                JOptionPane.showMessageDialog(null, "Cuenta creada con exito!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
