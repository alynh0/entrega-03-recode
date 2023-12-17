package agencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

import agencia.db.IConexao;
import agencia.model.Cliente;
import agencia.model.Reserva;
import agencia.model.Viagem;

public class ReservaDAO {

    private IConexao conn;

    public ReservaDAO(IConexao conn) {
        this.conn = conn;
    }

    public void criarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (id, id_viagem, id_cliente, data_reserva) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setInt(1, reserva.getId());
            pst.setInt(2, reserva.getViagem().getId());
            pst.setInt(3, reserva.getCliente().getId());
            pst.setDate(4, java.sql.Date.valueOf(reserva.getDataReserva()));
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Reserva findByID(Integer id) {
        String sql = "SELECT * FROM reserva WHERE id = ?";
        Reserva temp = null;
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int viagemId = rs.getInt("id_viagem");
                int clienteId = rs.getInt("id_cliente");
                LocalDate dataReserva = rs.getDate("data_reserva").toLocalDate();
                Viagem viagem = obterViagemPorID(viagemId);
                Cliente cliente = obterClientePorID(clienteId);

                temp = new Reserva(rs.getInt("id"), viagem, cliente, dataReserva);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void atualizarReserva(Reserva reserva) {
        String sql = "UPDATE reserva SET id_viagem = ?, id_cliente = ?, data_reserva = ? WHERE id = ?";
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setInt(1, reserva.getViagem().getId());
            pst.setInt(2, reserva.getCliente().getId());
            pst.setDate(3, java.sql.Date.valueOf(reserva.getDataReserva()));
            pst.setInt(4, reserva.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletarReserva(Reserva reserva) {
        String sql = "DELETE FROM reserva WHERE id = ?";
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setInt(1, reserva.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void criarReservaComScanner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da reserva:");
        int id = scanner.nextInt();

        System.out.println("Informe o ID da viagem associada à reserva:");
        int idViagem = scanner.nextInt();
        Viagem viagem = obterViagemPorID(idViagem);

        System.out.println("Informe o ID do cliente associado à reserva:");
        int idCliente = scanner.nextInt();
        Cliente cliente = obterClientePorID(idCliente);

        System.out.println("Informe a data da reserva (Formato: yyyy-MM-dd):");
        String dataReservaStr = scanner.next();
        LocalDate dataReserva = LocalDate.parse(dataReservaStr);

        Reserva novaReserva = new Reserva(id, viagem, cliente, dataReserva);
        criarReserva(novaReserva);
    }

    public void consultarReservaPorIDComScanner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da reserva que deseja consultar:");
        int id = scanner.nextInt();

        Reserva reservaEncontrada = findByID(id);

        if (reservaEncontrada != null) {
            System.out.println("Reserva encontrada: " + reservaEncontrada);
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    public void atualizarReservaComScanner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da reserva que deseja atualizar:");
        int id = scanner.nextInt();

        Reserva reservaExistente = findByID(id);

        if (reservaExistente != null) {
            System.out.println("Informe o novo ID da viagem associada à reserva:");
            int idViagem = scanner.nextInt();
            Viagem viagemAtualizada = obterViagemPorID(idViagem);

            System.out.println("Informe o novo ID do cliente associado à reserva:");
            int idCliente = scanner.nextInt();
            Cliente clienteAtualizado = obterClientePorID(idCliente);

            System.out.println("Informe a nova data da reserva (Formato: yyyy-MM-dd):");
            String dataReservaStr = scanner.next();
            LocalDate dataReservaAtualizada = LocalDate.parse(dataReservaStr);

            Reserva reservaAtualizada = new Reserva(id, viagemAtualizada, clienteAtualizado, dataReservaAtualizada);
            atualizarReserva(reservaAtualizada);
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    public void deletarReservaComScanner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID da reserva que deseja deletar:");
        int id = scanner.nextInt();

        Reserva reservaExistente = findByID(id);

        if (reservaExistente != null) {
            deletarReserva(reservaExistente);
            System.out.println("Reserva deletada com sucesso.");
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    private Viagem obterViagemPorID(int idViagem) {
        ViagemDAO viagemDAO = new ViagemDAO(conn);
        return viagemDAO.findByID(idViagem);
    }

    private Cliente obterClientePorID(int idCliente) {
        ClienteDAO clienteDAO = new ClienteDAO(conn);
        return clienteDAO.findByID(idCliente);
    }
}
