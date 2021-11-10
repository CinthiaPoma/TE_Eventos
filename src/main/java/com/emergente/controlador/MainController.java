
package com.emergente.controlador;

import com.emergente.modelo.Evento;
import com.emergente.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PreparedStatement ps;
            ConexionDB canal = new ConexionDB();
            Connection conn= canal.conectar();
            ResultSet rs;
            String op;
            int id;
            ArrayList<Evento>lista = new ArrayList<Evento>();
            
            op=(request.getParameter("op")!=null)?request.getParameter("op") : "list";
            
            if(op.equals("list")){
                //operacion para listar datos
                String sql= "select * from  seminarios";
                try {
                    ps = conn.prepareStatement(sql);
                    rs=ps.executeQuery();
                    while(rs.next()){
                        Evento eve=new Evento ();
                        eve.setId(rs.getInt("id"));
                        eve.setTitulo(rs.getString("titulo"));
                        eve.setExpositor(rs.getString("expositor"));
                        eve.setFecha(rs.getString("fecha"));
                        eve.setHora(rs.getString("hora"));
                        eve.setCupo(rs.getInt("cupo"));
                        
                        
                        lista.add(eve);
                    }
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if(op.equals("nuevo")){
                //operaciones para desplegar formulario
                
                Evento li=new Evento();
                
                request.setAttribute("eve", li);
                request.getRequestDispatcher("editar.jsp").forward(request,response);
            }
            
            if(op.equals("Editar")){
                 
                try {
                     id=Integer.parseInt(request.getParameter("id"));
                    Evento pro1 = new Evento();
                    
                    ps=conn.prepareStatement("select * from seminarios where id= ?");
                    ps.setInt(1, id);
                    rs=ps.executeQuery();
                    if(rs.next()){
                        pro1.setId(rs.getInt("id"));
                        pro1.setTitulo(rs.getString("titulo"));
                        pro1.setExpositor(rs.getString("expositor"));
                        pro1.setFecha(rs.getString("fecha"));
                        pro1.setHora(rs.getString("hora"));
                        pro1.setCupo(rs.getInt("cupo"));
                        
                    }
                    request.setAttribute("eve", pro1);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            if(op.equals("Eliminar")){
                //operaciones para eliminar un registro
                id=Integer.parseInt(request.getParameter("id"));
                
                try {
                    ps=conn.prepareStatement("delete from seminarios where id = ?");
                    ps.setInt(1, id);
                    
                    ps.executeUpdate();
                    response.sendRedirect("MainController");
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }} catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id=Integer.parseInt(request.getParameter("id"));
            String titulo=request.getParameter("titulo");
            String expositor=request.getParameter("expositor");
            String fecha=request.getParameter("fecha");
            String hora=request.getParameter("hora");
            int cupo =Integer.parseInt(request.getParameter("cupo"));
            
            
            Evento eve = new Evento();
            eve.setId(id);
            eve.setTitulo(titulo);
            eve.setExpositor(expositor);
            eve.setFecha(fecha);
            eve.setHora(hora);
            eve.setCupo(cupo);
            
            ConexionDB canal =new ConexionDB();
            Connection conn =canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if(id==0){
                //insertar registro
                String sql ="insert into seminarios (titulo,expositor,fecha,hora,cupo)values(?,?,?,?,?)";
                try {
                    ps= conn.prepareStatement(sql);
                    ps.setString(1,eve.getTitulo());
                    ps.setString(2,eve.getExpositor());
                    ps.setString(3,eve.getFecha());
                     ps.setString(4,eve.getHora());
                    ps.setInt(5,eve.getCupo());
                    
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else{
                //Update registro
                String sql1="update seminarios set titulo=?, expositor=? ,fecha=?,hora=?,cupo=? where id=?";
                try {
                    ps= conn.prepareStatement(sql1);
                    ps.setInt(1,eve.getId());
                    ps.setString(2,eve.getTitulo());
                     ps.setString(3,eve.getExpositor());
                     ps.setString(4,eve.getFecha());
                      ps.setString(5,eve.getHora());
                    ps.setInt(6,eve.getCupo());
                    
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            response.sendRedirect("MainController");
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
        
    

    
}
