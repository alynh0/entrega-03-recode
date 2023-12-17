package agencia.application;

import java.util.Scanner;

import agencia.dao.ClienteDAO;
import agencia.dao.ReservaDAO;
import agencia.dao.ViagemDAO;
import agencia.db.ConexaoMysql;
import agencia.db.IConexao;

public class Application {

	public static void main(String[] args) {
        IConexao conexao = new ConexaoMysql();
        ViagemDAO viagemDAO = new ViagemDAO(conexao);
        ClienteDAO clienteDAO = new ClienteDAO(conexao);
        ReservaDAO reservaDAO = new ReservaDAO(conexao);

        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("\n***** Menu da Agência de Viagens *****");
            System.out.println("1. Gerenciar Viagens");
            System.out.println("2. Gerenciar Clientes");
            System.out.println("3. Gerenciar Reservas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    menuViagens(scanner, viagemDAO, reservaDAO);
                    break;
                case 2:
                    menuClientes(scanner, clienteDAO, reservaDAO);
                    break;
                case 3:
                    menuReservas(scanner, reservaDAO);
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);

        scanner.close();
    }

    private static void menuViagens(Scanner scanner, ViagemDAO viagemDAO, ReservaDAO reservaDAO) {
        int escolha;

        do {
            System.out.println("\n***** Menu de Gerenciamento de Viagens *****");
            System.out.println("1. Listar Viagens");
            System.out.println("2. Criar Viagem");
            System.out.println("3. Atualizar Viagem");
            System.out.println("4. Excluir Viagem");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
					viagemDAO.consultarViagemPorIDComScanner();
                    break;
                case 2:
                    viagemDAO.criarViagemComScanner();
                    break;
                case 3:
                    viagemDAO.atualizarViagemComScanner();
                    break;
                case 4:
                    viagemDAO.deletarViagemComScanner();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);
    }

    private static void menuClientes(Scanner scanner, ClienteDAO clienteDAO, ReservaDAO reservaDAO) {
        int escolha;

        do {
            System.out.println("\n***** Menu de Gerenciamento de Clientes *****");
            System.out.println("1. Listar Clientes");
            System.out.println("2. Criar Cliente");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Excluir Cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    clienteDAO.consultarClientePorIDComScanner();
                    break;
                case 2:
                    clienteDAO.criarClienteComScanner();
                    break;
                case 3:
                    clienteDAO.atualizarClienteComScanner();
                    break;
                case 4:
                    clienteDAO.deletarClienteComScanner();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);
    }

    private static void menuReservas(Scanner scanner, ReservaDAO reservaDAO) {
        int escolha;

        do {
            System.out.println("\n***** Menu de Gerenciamento de Reservas *****");
            System.out.println("1. Listar Reservas");
            System.out.println("2. Criar Reserva");
            System.out.println("3. Atualizar Reserva");
            System.out.println("4. Excluir Reserva");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    reservaDAO.consultarReservaPorIDComScanner();
                    break;
                case 2:
                    reservaDAO.criarReservaComScanner();
                    break;
                case 3:
                    reservaDAO.atualizarReservaComScanner();
                    break;
                case 4:
                    reservaDAO.deletarReservaComScanner();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);
    }

}
