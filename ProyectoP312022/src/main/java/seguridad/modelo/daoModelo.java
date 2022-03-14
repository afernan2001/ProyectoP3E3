/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.modelo;

import seguridad.controlador.clsModelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class daoModelo {

    private static final String SQL_SELECT = "SELECT Id_Modulo, Codigo, Nombre, Estatus FROM modulo";
    private static final String SQL_INSERT = "INSERT INTO modulo(Codigo, Nombre, Estatus) VALUES(?, ?, ? )";
    private static final String SQL_UPDATE = "UPDATE modulo SET Codigo=?, Nombre=?, Estatus=? WHERE Id_Modulo = ?";
    private static final String SQL_DELETE = "DELETE FROM modulo WHERE Id_Modulo=?";
    private static final String SQL_QUERY = "SELECT Id_Modulo, Codigo, Nombre, Estatus FROM modulo WHERE Id_Modulo = ?";

    public List<clsModelo> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsModelo modelo = null;
        List<clsModelo> modelos = new ArrayList<clsModelo>();
        try {
            conn = clsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Id_Modulo = rs.getInt("Id_Modulo");
                String Codigo = rs.getString("Codigo");
                String Nombre = rs.getString("Nombre");
                String Estatus = rs.getString("Estatus");

                modelo = new clsModelo();
                modelo.fSetid_modulo(Id_Modulo);
                modelo.fSetcodigo(Codigo);
                modelo.fSetnombre(Nombre);
                modelo.fSetestatus(Estatus);
                modelos.add(modelo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(rs);
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return modelos;
    }

    public int insert(clsModelo modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = clsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, modelo.fGetcodigo());
            stmt.setString(2, modelo.fGetnombre());
            stmt.setString(3, modelo.fGetstatus());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public int update(clsModelo modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = clsConexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, modelo.fGetcodigo());
            stmt.setString(2, modelo.fGetnombre());
            stmt.setString(3, modelo.fGetstatus());
            stmt.setInt(4, modelo.fGetid_modulo());

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public int delete(clsModelo modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = clsConexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, modelo.fGetid_modulo());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        return rows;
    }

    public clsModelo query(clsModelo modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = clsConexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, modelo.fGetcodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Id_Modulo = rs.getInt("Id_Modulo");
                String Codigo = rs.getString("Codigo");
                String Nombre = rs.getString("Nombre");
                String Estatus = rs.getString("Estatus");

                modelo = new clsModelo();
                modelo.fSetid_modulo(Id_Modulo);
                modelo.fSetcodigo(Codigo);
                modelo.fSetnombre(Nombre);
                modelo.fSetestatus(Estatus);
              
            }
            //System.out.println("Registros buscado:" + persona);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            clsConexion.close(rs);
            clsConexion.close(stmt);
            clsConexion.close(conn);
        }

        //return personas;  // Si se utiliza un ArrayList
        return modelo;
    }
}
