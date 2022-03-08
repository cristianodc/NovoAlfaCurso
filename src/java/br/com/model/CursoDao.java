/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.model;

import br.com.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            return null;
        }
    
}
