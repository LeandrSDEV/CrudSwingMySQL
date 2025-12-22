
package dao;

import model.Pessoa;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    public void salvar(Pessoa p){
        String sql = "INSERT INTO pessoa (nome, email) VALUES (?, ?)";
        
        try(Connection c = Conexao.conectar();
                PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.execute();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public List<Pessoa> listar() {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        
        try (Connection c = Conexao.conectar();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            
            while (rs.next()){
             lista.add(new Pessoa(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email")
             ));                   
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public void atualizar(Pessoa p){
        String sql = "UPDATE pessoa SET nome =?, email=? WHERE id=?";
        
        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)){
            
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setInt(3, p.getId());
            ps.execute();
                       
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void deletar(int id){
        String sql = "DELETE FROM pessoa WHERE id=?";
        
        try(Connection c = Conexao.conectar();
            PreparedStatement ps = c.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.execute();
        
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
