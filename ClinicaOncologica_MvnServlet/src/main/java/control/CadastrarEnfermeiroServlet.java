package control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProfissionalSaude;
import persistence.FuncionarioDao;
import persistence.GenericDao;
import persistence.ProfissionalSaudeDao;

@WebServlet("/cadastrarEnfermeiroServlet")
public class CadastrarEnfermeiroServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	GenericDao gDao = new GenericDao();
	ProfissionalSaudeDao pDao = new ProfissionalSaudeDao(gDao);
	FuncionarioDao fDao = new FuncionarioDao(gDao);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("cadastrar".equals(action)) {
            ProfissionalSaude func = new ProfissionalSaude();

            // Obtenha os parâmetros do formulário
            func.setCPF(request.getParameter("cpf"));
            func.setNome(request.getParameter("nome"));
            func.setSobrenome(request.getParameter("sobrenome"));
            func.setNumero(Integer.parseInt(request.getParameter("numero")));
            func.setLogradouro(request.getParameter("logradouro"));
            func.setCEP(request.getParameter("cep"));
            func.setEstado(request.getParameter("estado"));
            func.setBairro(request.getParameter("bairro"));
            func.setLogin(request.getParameter("login"));
            func.setSenha(request.getParameter("senha"));
            func.setTipoUsuario(request.getParameter("tipoUsuario"));
            func.setRG(request.getParameter("rg"));
            func.setValidador(Integer.parseInt(request.getParameter("coren")));

            LocalDate data = LocalDate.parse(request.getParameter("nascimento"));
            func.setNascimento(data);

            String validador = String.valueOf(func.getValidador());

            if (func.getValidador() < 0 || validador.length() < 6
                    || func.getCPF().length() <= 10 || func.getCPF().length() >= 12) {
                request.setAttribute("erroCadastro", "COREN ou CPF inválido!");
            } else {
                try {
                    pDao.inserirFuncionario(func);
                    request.setAttribute("erroCadastro", "Funcionário cadastrado com sucesso!");
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("erroCadastro", e.getMessage());
                }
            }

            request.getRequestDispatcher("cadastrarEnfermeiro.jsp").forward(request, response);
            
        }else if ("atualizar".equals(action)) {
            ProfissionalSaude func = new ProfissionalSaude();

            // Obtenha os parâmetros do formulário
            func.setCPF(request.getParameter("cpf"));
            func.setNome(request.getParameter("nome"));
            func.setSobrenome(request.getParameter("sobrenome"));
            func.setNumero(Integer.parseInt(request.getParameter("numero")));
            func.setLogradouro(request.getParameter("logradouro"));
            func.setCEP(request.getParameter("cep"));
            func.setEstado(request.getParameter("estado"));
            func.setBairro(request.getParameter("bairro"));
            func.setLogin(request.getParameter("login"));
            func.setSenha(request.getParameter("senha"));
            func.setTipoUsuario(request.getParameter("tipoUsuario"));
            func.setRG(request.getParameter("rg"));
            func.setValidador(Integer.parseInt(request.getParameter("coren")));

            LocalDate data = LocalDate.parse(request.getParameter("nascimento"));
            func.setNascimento(data);

            String cpf = func.getCPF();
            boolean cpfExiste = false;

            try {
                cpfExiste = pDao.buscarFuncionario(cpf);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("erroCadastro", "Erro ao buscar o funcionário");
                request.getRequestDispatcher("cadastrarEnfermeiro.jsp").forward(request, response);
                return;
            }

            if (!cpfExiste) {
                request.setAttribute("erroCadastro", "Funcionário não encontrado");
                request.getRequestDispatcher("cadastrarEnfermeiro.jsp").forward(request, response);
                return;
            }

            if (contemNumeros(func.getNome()) || contemNumeros(func.getSobrenome())
                    || contemNumeros(func.getLogradouro()) || contemNumeros(func.getBairro())) {
                request.setAttribute("erroCadastro", "Falha na atualização, verifique os campos e tente novamente.");
                request.getRequestDispatcher("cadastrarEnfermeiro.jsp").forward(request, response);
                return;
            }

            if (func.getValidador() < 0 || String.valueOf(func.getValidador()).length() < 6) {
                request.setAttribute("erroCadastro", "COREN inválido!");
                request.getRequestDispatcher("cadastrarEnfermeiro.jsp").forward(request, response);
                return;
            }

            try {
                pDao.atualizarFuncionario(func);
                request.setAttribute("erroCadastro", "Funcionário atualizado com sucesso");
                request.getRequestDispatcher("cadastrarEnfermeiro.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("erroCadastro", "Erro ao atualizar o funcionário");
                request.getRequestDispatcher("cadastrarEnfermeiro.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("cadastrarEnfermeiro.jsp");
        }
	}
	private static boolean contemNumeros(String str) {
	        for (int i = 0; i < str.length(); i++) {
	            if (Character.isDigit(str.charAt(i))) {
	                return true;
	            }
	        }
	        return false;
	}
}
