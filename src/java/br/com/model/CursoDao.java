/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.model;

import br.com.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {
    private Connection conn;
    
    public CursoDao() 
    {
       this.conn  = Util.getConnection();
    }
    
    public int insert(Curso obj)
        {
             String sql = "Insert into cursos(nome,resumo,descricao,valor)"
                        + "values(?,?,?,?)";
             int res = 0;
             try{
                 PreparedStatement stmt = this.conn.prepareStatement(sql);
                 stmt.setString(1, obj.getNome());
                 stmt.setString(2, obj.getResumo());
                 stmt.setString(3, obj.getDescricao());
                 stmt.setFloat(4, obj.getValor());
                  res = stmt.executeUpdate();
                 stmt.close();
                 conn.close();
                
                }catch(Exception e)
                    {
                        e.printStackTrace();
                    }  
               
                
                return res;
        }
    
        public int atualizar(Curso obj)
            {
                String sql = "update cursos set nome=?,resumo=?,descricao=?,valor=?"
                        + "where id=?";
             int res = 0;
             try{
                 PreparedStatement stmt = this.conn.prepareStatement(sql);
                 stmt.setString(1, obj.getNome());
                 stmt.setString(2, obj.getResumo());
                 stmt.setString(3, obj.getDescricao());
                 stmt.setFloat(4, obj.getValor());
                  stmt.setLong(5, obj.getId());
                  res = stmt.executeUpdate();
                 stmt.close();
                 conn.close();
                
                }catch(Exception e)
                    {
                        e.printStackTrace();
                    }  
               
                
                return res;
            }
        public int excluir(long id)
            {
                String sql = "delete from cursos where id=?";
             int res = 0;
             try{
                 PreparedStatement stmt = this.conn.prepareStatement(sql);
                  stmt.setLong(1, id);
                  res = stmt.executeUpdate();
                 stmt.close();
                 conn.close();
                
                }catch(Exception e)
                    {
                        e.printStackTrace();
                    }  
               
                
                return res;
            }
        public List<Curso> listar()
        {
            ArrayList<Curso> lista = new ArrayList<Curso>();
            
            try
                {
                    String sql = "Select * from cursos";
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                   ResultSet rset = stmt.executeQuery();
                   while(rset.next())
                    {
                        Curso c = new Curso();
                        c.setId(rset.getLong("id"));
                        c.setNome(rset.getString("nome"));
                        c.setResumo(rset.getString("resumo"));
                        c.setDescricao(rset.getString("descricao"));
                        c.setValor(rset.getFloat("valor"));
                        lista.add(c);
                    }
                   stmt.close();
                   this.conn.close();
                }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
            
            
            return lista;
        }
        
        public Curso buscar(String id)
        {
            Long idL = Long.parseLong(id);
            Curso curso = null;
            
            try
                {
                    String sql = "Select * from cursos where id=?";
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    stmt.setLong(1, idL);
                   ResultSet rset = stmt.executeQuery();
                   while(rset.next())
                    {
                        curso = new Curso();
                        curso.setId(rset.getLong("id"));
                        curso.setNome(rset.getString("nome"));
                        curso.setResumo(rset.getString("resumo"));
                        curso.setDescricao(rset.getString("descricao"));
                        curso.setValor(rset.getFloat("valor"));
                        
                    }
                   stmt.close();
                   this.conn.close();
                }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
            
            
            return curso;
        }
    
}
