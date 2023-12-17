package agencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import agencia.db.IConexao;
import agencia.model.Cliente;

public class ClienteDAO {
    
    private IConexao conn;

    public ClienteDAO (IConexao conn) {
        this.conn = conn;
    }

    public void criarCliente (Cliente cliente) {
        String sql = "INSERT INTO cliente (id, cpf, nome, email, password, telefone) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setInt(1, cliente.getId());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getNome());
            pst.setString(4, cliente.getEmail());
            pst.setString(5, cliente.getPassword());
            pst.setString(6, cliente.getTelefone());
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cliente findByID (Integer id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        Cliente temp = null;
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                temp = new Cliente(rs.getInt("id"), rs.getString("cpf"), rs.getString("nome"), rs.getString("email"), rs.getString("password"), rs.getString("telefone")); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void atualizarCliente (Cliente cliente) {
        String sql = "UPDATE cliente SET cpf = ?, nome = ?, email = ?, password = ?, telefone = ? WHERE id = ?";
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setString(1, cliente.getCpf());
            pst.setString(2, cliente.getNome());
            pst.setString(3, cliente.getEmail());
            pst.setString(4, cliente.getPassword());
            pst.setString(5, cliente.getTelefone());
            pst.setInt(6, cliente.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletarCliente (Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setInt(1, cliente.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void criarClienteComScanner() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Informe o ID do cliente:");
        int id = scanner.nextInt();
    
        scanner.nextLine();
    
        System.out.println("Informe o CPF do cliente:");
        String cpf = scanner.nextLine();
    
        System.out.println("Informe o nome do cliente:");
        String nome = scanner.nextLine();
    
        System.out.println("Informe o email do cliente:");
        String email = scanner.nextLine();
    
        System.out.println("Informe a senha do cliente:");
        String senha = scanner.nextLine();
    
        System.out.println("Informe o telefone do cliente:");
        String telefone = scanner.nextLine();
    
        Cliente novoCliente = new Cliente(id, cpf, nome, email, senha, telefone);
        criarCliente(novoCliente);
    }
    
    

    public void consultarClientePorIDComScanner() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Informe o ID do cliente que deseja consultar:");
        int id = scanner.nextInt();
    
        Cliente clienteEncontrado = findByID(id);
    
        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado: " + clienteEncontrado);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
    
    public void atualizarClienteComScanner() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Informe o ID do cliente que deseja atualizar:");
        int id = scanner.nextInt();
    
        scanner.nextLine();
    
        Cliente clienteExistente = findByID(id);
    
        if (clienteExistente != null) {
            System.out.println("Informe o novo CPF do cliente:");
            String cpf = scanner.next();
    
            System.out.println("Informe o novo nome do cliente:");
            // Consumir a quebra de linha
            scanner.nextLine();
            String nome = scanner.nextLine();
    
            System.out.println("Informe o novo email do cliente:");
            String email = scanner.next();
    
            System.out.println("Informe a nova senha do cliente:");
            String senha = scanner.next();
    
            System.out.println("Informe o novo telefone do cliente:");
            String telefone = scanner.next();
    
            Cliente clienteAtualizado = new Cliente(id, cpf, nome, email, senha, telefone);
            atualizarCliente(clienteAtualizado);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
    
    
    public void deletarClienteComScanner() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Informe o ID do cliente que deseja deletar:");
        int id = scanner.nextInt();
    
        Cliente clienteExistente = findByID(id);
    
        if (clienteExistente != null) {
            deletarCliente(clienteExistente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

}
