/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import entidad.Fabricante;
import servicio.ProductoServicio;

/**
 *
 * @author mirod
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductoServicio productoServicio = new ProductoServicio();

        try {
            productoServicio.imprimirNombresDeProductos();
            System.out.println("");
            productoServicio.imprimirNombresYPreciosDeProductos();
            System.out.println("");
            productoServicio.imprimirProductosEntre();
            System.out.println("");
            productoServicio.imprimirProductosPortatiles();
            System.out.println("");
            productoServicio.imprimirMasBarato();
            System.out.println("");
            Fabricante fabricante = new Fabricante(1, null);
            //productoServicio.crearProducto("mi producto", 550.30d, fabricante);
            productoServicio.modificarProducto(12, "mi producto modificado", 13.0d, fabricante);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
