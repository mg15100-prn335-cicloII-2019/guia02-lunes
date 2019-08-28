/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ues.occ.edu.sv.ingenieria.prn335.cinedatalib.entities.Director;

/**
 *
 * @author Nest
 */
@WebServlet(name = "ServletCRUD", urlPatterns = {"/ServletCRUD"})
public class ServletCRUD extends HttpServlet {

    @EJB
    private DirectorBean utilidades;
    List<Director> lista;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletCRUD</title>");

            String botonBuscarId = request.getParameter("buscarId");
            String botonBuscarRango = request.getParameter("buscarRango");
            String botonAgregar = request.getParameter("crear");
            String botonConsultar = request.getParameter("consultar");
            String botonActualizar = request.getParameter("actualizar");
            String botonEliminar = request.getParameter("eliminar");
            //Muestra todos los datos de la entidad meta
            try {
                if (botonConsultar.equals("Consultar")) {
                    out.println("<h2>Tabla Consumibles Consultar</h2>");
                    out.println("<table border=1>");
                    out.println("<tr>");
                    out.println("<th>Id</th>");
                    out.println("<th>Nombre</th>");
                    out.println("<th>Apellido</th>");
                    out.println("<th>Activo</th>");
                    out.println("<tr>");

                    lista = utilidades.selectAll();
                    for (int i = 0; i < lista.size(); i++) {
                        out.println("<tr>");
                        out.println("<td>" + lista.get(i).getIdDirector() + "</td>");
                        out.println("<td>" + lista.get(i).getNombre() + "</td>");
                        out.println("<td>" + lista.get(i).getApellido() + "</td>");
                        out.println("<td>" + lista.get(i).getActivo() + "</td>");
                        out.println("<tr>");
                    }
                    out.println("</table>");
                }
            } catch (Exception e) {
                //Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }

            //agregar un nuevo dato 
            try {
                if (botonAgregar.equals("Crear")) {
                    String id = request.getParameter("ID");
                    String nombre = request.getParameter("Nombre");
                    String apellido = request.getParameter("Apellido");
                    boolean activo = request.getParameter("activo") != null;
                    System.err.println("activo resutados           " + activo);
                    Director guardar;
                    Integer idEnviar = Integer.parseInt(id);

                    if (!nombre.isEmpty()) {
                        guardar = new Director();
                        guardar.setIdDirector(idEnviar);
                        guardar.setNombre(nombre);
                        guardar.setApellido(apellido);
                        
                        if (activo == false) {
                            guardar.setActivo(false);
                        } else {
                            guardar.setActivo(true);
                        }

                        //guardar.setPrecio(precio);
                        utilidades.insert(guardar);
                        out.println("<h2>El registro de menuConsumible se ha creado con exito:<br><br> "
                                + "Nombre: " + nombre + "<br>Apellido: " + apellido +"<br>Activo: " + activo
                                + "</h2>");
                    }
                    out.println("<h2>Tabla Consumibles Consultar</h2>");
                    out.println("<table border=1>");
                    out.println("<tr>");
                    out.println("<th>Id</th>");
                    out.println("<th>Nombre</th>");
                    out.println("<th>Apellido</th>");
                    out.println("<th>Activo</th>");
                    out.println("<tr>");

                    lista = utilidades.selectAll();
                    for (int i = 0; i < lista.size(); i++) {
                        out.println("<tr>");
                        out.println("<td>" + lista.get(i).getIdDirector() + "</td>");
                        out.println("<td>" + lista.get(i).getNombre() + "</td>");
                        out.println("<td>" + lista.get(i).getApellido() + "</td>");
                        out.println("<td>" + lista.get(i).getActivo() + "</td>");
                        out.println("<tr>");
                    }
                    out.println("</table>");

                }
            } catch (Exception e) {
                //Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }

            //eliminar datos de la tabla menuConsumible
            try {
                if (botonEliminar.equals("Eliminar")) {

                    String id = request.getParameter("ID");
                    Director eliminar = new Director();
                    int idConvetido = Integer.parseInt(id);
                    eliminar.setIdDirector(null);
                    eliminar.setIdDirector(idConvetido);
                    eliminar.setNombre("");
                    // eliminar.setPrecio();

                    utilidades.delete(eliminar);
                    out.println("<h2>Tabla Consumibles Consultar</h2>");
                    out.println("<table border=1>");
                    out.println("<tr>");
                    out.println("<th>Id</th>");
                    out.println("<th>Nombre</th>");
                    out.println("<th>Apellido</th>");
                    out.println("<th>Activo</th>");
                    out.println("<tr>");

                    lista = utilidades.selectAll();
                    for (int i = 0; i < lista.size(); i++) {
                        out.println("<tr>");
                        out.println("<td>" + lista.get(i).getIdDirector() + "</td>");
                        out.println("<td>" + lista.get(i).getNombre() + "</td>");
                        out.println("<td>" + lista.get(i).getApellido() + "</td>");
                        out.println("<td>" + lista.get(i).getActivo() + "</td>");
                        out.println("<tr>");
                    }
                    out.println("</table>");
                }
            } catch (Exception e) {
                //Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }

            out.println("<br><form action=\"index.html\"><input type=\"submit\" value=\"REGRESAR AL INDEX\" /></form>");

            out.println("</head>");
            out.println("<body>");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}













