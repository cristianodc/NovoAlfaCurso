/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.controler;

import br.com.model.Curso;
import br.com.model.CursoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristiano
 */
public class CursoControler extends HttpServlet {

    private Connection conn;
    private String nome,resm,desc, val;
    public Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String host = "jdbc:mysql://localhost/alfacursos";
            String user = "root";
            String pass = "";
            con = DriverManager.getConnection(host, user, pass);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

  public void getCampos(HttpServletRequest request, HttpServletResponse response)
  {
      String nome = request.getParameter("nome");
      String resm = request.getParameter("resm");
      String desc = request.getParameter("desc");
      String val = request.getParameter("val");
      
  }

   
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CursoControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CursoControler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        // processRequest(request, response);
       
        String act = request.getParameter("act");
        try {
            if (act.equalsIgnoreCase("insert")) {
               String nome = request.getParameter("nome");
                String resm = request.getParameter("resm");
                String desc = request.getParameter("desc");
                String val = request.getParameter("val");
              
                Float valor = Float.parseFloat(val);
                Curso c = new Curso();
                c.setNome(nome);
                c.setResumo(resm);
                c.setDescricao(desc);
                c.setValor(valor);
                CursoDao dao = new CursoDao();
              int res=   dao.insert(c);
               if(res > 0)
                {
                    response.sendRedirect("admin/listarcursos.jsp");
                }
                
            } else if (act.equalsIgnoreCase("update")) {

                doPut(request, response);
            }else if(act.equalsIgnoreCase("delete"))
                {
                    doDelete(request, response);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
                String id = req.getParameter("id");
                String nome = req.getParameter("nome");
                String resm = req.getParameter("resm");
                String desc = req.getParameter("desc");
                String val = req.getParameter("val");
                Float valor = Float.parseFloat(val);
                Long idL= Long.parseLong(id);
                Curso c = new Curso();
                c.setId(idL);
                c.setNome(nome);
                c.setResumo(resm);
                c.setDescricao(desc);
                c.setValor(valor);
                CursoDao dao = new CursoDao();
                int res = dao.atualizar(c);
                if(res > 0)
                    {
                        resp.sendRedirect("admin/listarcursos.jsp");
                    }
               
                
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
            try
                {
                   
                    String id = req.getParameter("id");
                    Long idL = Long.parseLong(id);
                    CursoDao dao = new CursoDao();
                   int res =  dao.excluir(idL);
                   
                   if(res > 0)
                    {
                        resp.sendRedirect("admin/listarcursos.jsp");
                    }
                     
                }catch(Exception e)
                    {
                        e.printStackTrace();
                         JOptionPane.showMessageDialog(null, e.getMessage());
                    }
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
