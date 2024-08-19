/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
         try {
            String query = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(query);
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
         try {
            conn = new conectaDAO().connectDB();
            String query = "SELECT id, nome, valor, status FROM produtos";
            prep = conn.prepareStatement(query);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                Integer id = resultset.getInt("id");
                String nome = resultset.getString("nome");
                Integer valor = resultset.getInt("valor");
                String status = resultset.getString("status");

                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(id);
                produto.setNome(nome);
                produto.setValor(valor);
                produto.setStatus(status);
                listagem.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        }

        return listagem;
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
         try {
            conn = new conectaDAO().connectDB();
            String query = "SELECT id, nome, valor, status FROM produtos WHERE status = 'Vendido'";
            prep = conn.prepareStatement(query);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                Integer id = resultset.getInt("id");
                String nome = resultset.getString("nome");
                Integer valor = resultset.getInt("valor");
                String status = resultset.getString("status");

                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(id);
                produto.setNome(nome);
                produto.setValor(valor);
                produto.setStatus(status);
                listagem.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        }

        return listagem;
    }
    
    public void venderProduto(Integer id){
         try {
            conn = new conectaDAO().connectDB();
            String query = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            prep = conn.prepareStatement(query);
            prep.setInt(1, id);
            prep.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produtos: " + e.getMessage());
        }
    }
}

