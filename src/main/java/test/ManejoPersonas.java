package test;

import datos.Conexion;
import datos.PersonaJDBC;
import domain.Persona;
import java.sql.*;

public class ManejoPersonas {

    public static void main(String[] args) {

        //definimos la variable conexion
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            //el autocommit por default es true, lo pasamos a false
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaJDBC personaJdbc = new PersonaJDBC(conexion);
            
            Persona cambioPersona = new Persona();
            cambioPersona.setId_persona(2);
            cambioPersona.setNombre("Harry");
            cambioPersona.setApellido("Potter");
            cambioPersona.setEmail("Hpotter@mail.com");
            cambioPersona.setTelefono("12345678910");
            personaJdbc.update(cambioPersona);
            
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Mario");
            //nuevaPersona.setApellido("Asturias==========999999999999999999999999=====================================");
            nuevaPersona.setApellido("Monteforte");
            personaJdbc.insert(nuevaPersona);
            
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

    }
}
