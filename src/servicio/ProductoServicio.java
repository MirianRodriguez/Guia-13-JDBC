/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import entidad.Fabricante;
import entidad.Producto;
import java.util.List;
import persistencia.ProductoDAO;

/**
 *
 * @author mirod
 */
public class ProductoServicio {
    
    private final ProductoDAO productoDao;
    
    public ProductoServicio() {
        productoDao = new ProductoDAO();
    }
    
    //Ingresar un producto a la base de datos.
    public void crearProducto(String nombre, Double precio, Fabricante fabricante) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }

            if (precio == null || precio.toString().trim().isEmpty()) {
                throw new Exception("El precio es obligatorio");
            }

            if (fabricante == null) {
                throw new Exception("El fabricante es obligatorio");
            }

            Producto producto = new Producto();

            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setFabricante(fabricante);

            productoDao.guardarProducto(producto);
        } catch (Exception e) {
            throw e;
        }
    }
    
    //Ingresar un producto a la base de datos.
    public void modificarProducto(Integer codigo, String nombre, Double precio, Fabricante fabricante) throws Exception {
        try {
            if (codigo == null || codigo.toString().trim().isEmpty()) {
                throw new Exception("Debe indicar el producto");
            }
            
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }

            if (precio == null || precio.toString().trim().isEmpty()) {
                throw new Exception("El precio es obligatorio");
            }

            if (fabricante == null) {
                throw new Exception("El fabricante es obligatorio");
            }

            Producto producto = new Producto();
            producto.setCodigo(codigo);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setFabricante(fabricante);

            productoDao.modificarProducto(producto);
        } catch (Exception e) {
            throw e;
        }
    }
    
    //Eliminar un producto de la base de datos
    public void eliminarProducto(Integer codigoProducto) throws Exception{
        try{
            if(codigoProducto == null){
                throw new Exception("Debe indicar el codigo del producto");
            }
            productoDao.eliminarProducto(codigoProducto);
        }catch(Exception e){
            throw e;
        }
    }
    
    //Lista el nombre de todos los productos que hay en la tabla producto
    public void imprimirNombresDeProductos() throws Exception {
        try {
            List<Producto> productos = productoDao.obtenerProductos();

            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE PRODUCTOS");
                System.out.println("Nombre");
                for (Producto producto : productos) {
                    System.out.println(producto.getNombre());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    //Lista los nombres y los precios de todos los productos de la tabla producto
    public void imprimirNombresYPreciosDeProductos() throws Exception {
        try {
            List<Producto> productos = productoDao.obtenerProductos();

            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE PRODUCTOS");
                System.out.printf("%-50s%-20s\n", "NOMBRE", "PRECIO");
                for (Producto producto : productos) {
                    System.out.printf("%-50s%-20s\n", producto.getNombre(), producto.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    //Listar aquellos productos que su precio esté entre 120 y 202.
    public void imprimirProductosEntre() throws Exception {
        try{  
            double desde, hasta;
            desde = 120;
            hasta = 202;
            List<Producto> productos = productoDao.obtenerProductosEntre(desde, hasta);

            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE PRODUCTOS ENTRE 120 y 202");
                System.out.printf("%-50s%-20s\n", "NOMBRE", "PRECIO");
                for (Producto producto : productos) {
                    System.out.printf("%-50s%-20s\n", producto.getNombre(), producto.getPrecio());
                }
            }
        }catch (Exception e) {
            throw e;
        }
    }
    
    //Buscar y listar todos los Portátiles de la tabla producto.
    public void imprimirProductosPortatiles() throws Exception {
        try {
            List<Producto> productos = productoDao.obtenerPortatiles();

            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE PRODUCTOS PORTATILES");
                System.out.println("Nombre");
                for (Producto producto : productos) {
                    System.out.println(producto.getNombre());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    //Listar el nombre y el precio del producto más barato.
    public void imprimirMasBarato() throws Exception {
        try {
            Producto producto = productoDao.obtenerMasBarato();

            if (producto == null) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("PRODUCTO MAS BARATO");
                System.out.printf("%-50s%-20s\n", "NOMBRE", "PRECIO");
                System.out.printf("%-50s%-20s\n", producto.getNombre(), producto.getPrecio());
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
