package agencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

import agencia.db.IConexao;
import agencia.model.Viagem;

public class ViagemDAO {

	private IConexao conn;

	public ViagemDAO (IConexao conn) {
		this.conn = conn;
	}
	
	public void criarViagem (Viagem viagem) {
		String sql = "INSERT INTO viagem (id, origem, destino, data_ida, data_volta, descricao, preco) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
			pst.setInt(1, viagem.getId());
			pst.setString(2, viagem.getOrigem());
			pst.setString(3, viagem.getDestino());
			pst.setDate(4, java.sql.Date.valueOf(viagem.getDataIda()));
			pst.setDate(5, java.sql.Date.valueOf(viagem.getDataVolta()));
			pst.setString(6, viagem.getDescricao());
			pst.setDouble(7, viagem.getPreco());
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Viagem findByID (Integer id) {
		String sql = "SELECT * FROM viagem WHERE id = ?";
		Viagem temp = null;
		try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				temp = new Viagem(rs.getInt("id"), rs.getString("origem"), rs.getString("destino"), rs.getDate("data_ida").toLocalDate(), rs.getDate("data_volta").toLocalDate(), rs.getString("descricao"), rs.getDouble("preco")); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	public void atualizarViagem (Viagem viagem) {
		String sql = "UPDATE viagem SET origem = ?, destino = ?, data_ida = ?, data_volta = ?, descricao = ?, preco = ? WHERE id = ?";
		try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
			pst.setString(1, viagem.getOrigem());
			pst.setString(2, viagem.getDestino());
			pst.setDate(3, java.sql.Date.valueOf(viagem.getDataIda()));
			pst.setDate(4, java.sql.Date.valueOf(viagem.getDataVolta()));
			pst.setString(5, viagem.getDescricao());
			pst.setDouble(6, viagem.getPreco());
			pst.setInt(7, viagem.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletarViagem (Viagem viagem) {
		String sql = "DELETE FROM viagem WHERE id = ?";
		try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
			pst.setInt(1, viagem.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void criarViagemComScanner() {
		Scanner scanner = new Scanner(System.in);
	
		System.out.println("Informe o ID da viagem:");
		int id = scanner.nextInt();
	
		scanner.nextLine();
	
		System.out.println("Informe a origem da viagem:");
		String origem = scanner.nextLine();
	
		System.out.println("Informe o destino da viagem:");
		String destino = scanner.nextLine();
	
		System.out.println("Informe a data de ida da viagem (Formato: yyyy-MM-dd):");
		String dataIdaStr = scanner.next();
		LocalDate dataIda = LocalDate.parse(dataIdaStr);
	
		System.out.println("Informe a data de volta da viagem (Formato: yyyy-MM-dd):");
		String dataVoltaStr = scanner.next();
		LocalDate dataVolta = LocalDate.parse(dataVoltaStr);
	
		scanner.nextLine();
	
		System.out.println("Informe a descrição da viagem:");
		String descricao = scanner.nextLine();
	
		System.out.println("Informe o preço da viagem:");
		double preco = scanner.nextDouble();
	
		scanner.nextLine();
	
		Viagem novaViagem = new Viagem(id, origem, destino, dataIda, dataVolta, descricao, preco);
		criarViagem(novaViagem);
	}
	

	public void consultarViagemPorIDComScanner() {
		Scanner scanner = new Scanner(System.in);
	
		System.out.println("Informe o ID da viagem que deseja consultar:");
		int id = scanner.nextInt();
	
		Viagem viagemEncontrada = findByID(id);
	
		if (viagemEncontrada != null) {
			System.out.println("Viagem encontrada: " + viagemEncontrada);
		} else {
			System.out.println("Viagem não encontrada.");
		}
	}
	
	public void atualizarViagemComScanner() {
		Scanner scanner = new Scanner(System.in);
	
		System.out.println("Informe o ID da viagem que deseja atualizar:");
		int id = scanner.nextInt();
	
		// Consumir a quebra de linha
		scanner.nextLine();
	
		Viagem viagemExistente = findByID(id);
	
		if (viagemExistente != null) {
			System.out.println("Informe a nova origem da viagem:");
			String origem = scanner.nextLine();
	
			System.out.println("Informe o novo destino da viagem:");
			String destino = scanner.nextLine();
	
			System.out.println("Informe a nova data de ida da viagem (Formato: yyyy-MM-dd):");
			String dataIdaStr = scanner.next();
			LocalDate dataIda = LocalDate.parse(dataIdaStr);
	
			System.out.println("Informe a nova data de volta da viagem (Formato: yyyy-MM-dd):");
			String dataVoltaStr = scanner.next();
			LocalDate dataVolta = LocalDate.parse(dataVoltaStr);
	
			scanner.nextLine();
	
			System.out.println("Informe a nova descrição da viagem:");
			String descricao = scanner.nextLine();
	
			System.out.println("Informe o novo preço da viagem:");
			double preco = scanner.nextDouble();
	
			scanner.nextLine();
	
			Viagem viagemAtualizada = new Viagem(id, origem, destino, dataIda, dataVolta, descricao, preco);
			atualizarViagem(viagemAtualizada);
		} else {
			System.out.println("Viagem não encontrada.");
		}
	}
	

	public void deletarViagemComScanner() {
		Scanner scanner = new Scanner(System.in);
	
		System.out.println("Informe o ID da viagem que deseja deletar:");
		int id = scanner.nextInt();
	
		Viagem viagemExistente = findByID(id);
	
		if (viagemExistente != null) {
			deletarViagem(viagemExistente);
			System.out.println("Viagem deletada com sucesso.");
		} else {
			System.out.println("Viagem não encontrada.");
		}
	}
	
	
}
