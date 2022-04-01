/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidad.Fabricante;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mirod
 */
public class FabricanteDAO extends DAO {
    
    //Alta
    public void guardarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("El fabricante no puede ser nulo");
            }

            String template = "INSERT INTO fabricante VALUES (NULL, '%s');";
            String sql = String.format(template, fabricante.getNombre());

            //System.out.println("STATEMENT");
            //System.out.println(sql);

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al guardar fabricante");
        }
    }

    //Modificacion
    public void modificarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("El fabricante no puede ser nulo");
            }

            String template = "UPDATE fabricante SET nombre = '%s' WHERE codigo = %s;";
            String sql = String.format(template, fabricante.getNombre(), fabricante.getCodigo());

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al modificar fabricante");
        }
    }

    //Baja
    public void eliminarFabricante(Integer codigoFabricante) throws Exception {
        try {
            String sql = "DELETE FROM fabricante WHERE codigo = " + codigoFabricante + ";";

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al eliminar fabricante");
        }
    }
    
    public List<Fabricante> obtenerFabricantes() throws Exception {
        try {
            String sql = "SELECT * FROM fabricante;";

            queryDatabase(sql);

            List<Fabricante> fabricantes = new ArrayList<>();
            Fabricante fabricante;

            while (resultSet.next()) {
                fabricante = new Fabricante();

                fabricante.setCodigo(resultSet.getInt(1));
                fabricante.setNombre(resultSet.getString(2));

                fabricantes.add(fabricante);
            }

            return fabricantes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener fabricantes");
        } 
    }
}
