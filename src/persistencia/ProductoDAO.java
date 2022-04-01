/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidad.Fabricante;
import entidad.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mirod
 */
public class ProductoDAO extends DAO {

    //Alta
    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("El producto no puede ser nulo");
            }

            String template = "INSERT INTO producto VALUES (NULL, '%s', %s, %s);";
            String sql = String.format(template, producto.getNombre(), producto.getPrecio(), producto.getFabricante().getCodigo());

            //System.out.println("STATEMENT");
            //System.out.println(sql);

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al guardar producto");
        }
    }

    //Modificacion
    public void modificarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("El producto no puede ser nulo");
            }

            String template = "UPDATE producto SET nombre = '%s', precio = %s, codigo_fabricante = %s WHERE codigo = %s;";
            String sql = String.format(template, producto.getNombre(), producto.getPrecio(), producto.getFabricante().getCodigo(), producto.getCodigo());

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al modificar producto");
        }
    }

    //Baja
    public void eliminarProducto(Integer codigoProducto) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE codigo = " + codigoProducto + ";";

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al eliminar producto");
        }
    }

    //obtiene toda la info de los productos con su fabricante
    public List<Producto> obtenerProductos() throws Exception {
        try {
            String sql = "SELECT * FROM producto as p INNER JOIN fabricante as f ON p.codigo_fabricante = f.codigo;"; //sql contiene la consulta que quiero realizar a la bd

            queryDatabase(sql); //queryDatabase esta implementado en DAO

            List<Producto> productos = new ArrayList<>();
            Producto producto;

            while (resultSet.next()) {
                producto = new Producto();
                /*se crea un objeto producto. Se asigna en cada atributo los datos recuperados en resulSet*/
                producto.setCodigo(resultSet.getInt(1));//el get es segun el tipo de dato traido de la bd. 1 es el nro de columna en el resultSet
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                //se crea un fabricante con la info del resultSet
                Fabricante fabricante = new Fabricante();
                fabricante.setCodigo(resultSet.getInt(4));
                fabricante.setNombre(resultSet.getString(6));
                //se asigna el fabricante al producto
                producto.setFabricante(fabricante);
                
                //System.out.println(producto.toString());
                
                productos.add(producto);
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        } finally {
            disconnectDatabase();
        }
    }

    /*Listar aquellos productos que su precio esté entre 120 y 202.*/
    public List<Producto> obtenerProductosEntre(double precioDesde, double precioHasta) throws Exception {
        try {
            //consulta parametrizada
            String template = "SELECT * FROM producto WHERE precio BETWEEN %s AND %s;";
            String sql = String.format(template, precioDesde, precioHasta);
            
            //System.out.println(sql);

            queryDatabase(sql);

            List<Producto> productos = new ArrayList<>();

            Producto producto;

            while (resultSet.next()) {
                producto = new Producto();
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                
                //fabricante
                productos.add(producto);
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los nombres y precios de los productos");
        } finally {
            disconnectDatabase();
        }
    }

    //Buscar y listar todos los Portátiles de la tabla producto
    public List<Producto> obtenerPortatiles() throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE nombre LIKE '%portatil%';";

            queryDatabase(sql);

            List<Producto> productos = new ArrayList<>();
            Producto producto;

            while (resultSet.next()) {
                producto = new Producto();
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));

                //fabricante
                productos.add(producto);
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener info de productos portatiles");
        } finally {
            disconnectDatabase();
        }
    }

    //Listar el nombre y el precio del producto más barato
    public Producto obtenerMasBarato() throws Exception {
        try {
            String sql = "SELECT codigo, nombre, precio FROM producto where precio = (SELECT MIN(precio) FROM producto);";
            queryDatabase(sql);
            Producto producto;
            producto = new Producto();
            while (resultSet.next()) {
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
            }
            return producto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener el producto mas barato");
        } finally {
            disconnectDatabase();
        }
    }

}
