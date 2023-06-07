package control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Paciente;
import persistence.GenericDao;
import persistence.PacienteDao;

@WebServlet("/cadastrarPacienteServlet")
public class CadastrarPacienteServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	GenericDao gDao = new GenericDao();
	PacienteDao pDao = new PacienteDao(gDao);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("cadastrar".equals(action)) {
            Paciente paciente = new Paciente();

            // Obtenha os parâmetros do formulário
            paciente.setCPF(request.getParameter("cpf"));
            paciente.setNome(request.getParameter("nome"));
            paciente.setSobrenome(request.getParameter("sobrenome"));
            paciente.setNumero(Integer.parseInt(request.getParameter("numero")));
            paciente.setLogradouro(request.getParameter("logradouro"));
            paciente.setCEP(request.getParameter("cep"));
            paciente.setEstado(request.getParameter("estado"));
            paciente.setBairro(request.getParameter("bairro"));
            paciente.setTelefone(request.getParameter("telefone"));
            paciente.setSexo(request.getParameter("sexo"));
            paciente.setTipoProcedimento(request.getParameter("tipoProcedimento"));
            paciente.setNomeAcompanhante(request.getParameter("nomeAcompanhante"));
            paciente.setSobrenomeAcompanhante(request.getParameter("sobrenomeAcompanhante"));
            paciente.setTelefoneAcompanhante(request.getParameter("telefoneAcompanhante"));

            LocalDate data = LocalDate.parse(request.getParameter("nascimento"));
            paciente.setNascimento(data);


            if (paciente.getCPF().length() <= 10 || paciente.getCPF().length() >= 12) {
                request.setAttribute("erroCadastro", "CPF inválido!");
            } else {
                try {
                    pDao.inserirPaciente(paciente);
                } catch (ClassNotFoundException | SQLException e) {
                    String erro = e.getMessage();
                    request.setAttribute("erroCadastro", erro);
                }
            }

            request.getRequestDispatcher("cadastrarPaciente.jsp").forward(request, response);
            
        }else if ("atualizar".equals(action)) {
            Paciente paciente = new Paciente();

            // Obtenha os parâmetros do formulário
            paciente.setCPF(request.getParameter("cpf"));
            paciente.setNome(request.getParameter("nome"));
            paciente.setSobrenome(request.getParameter("sobrenome"));
            paciente.setNumero(Integer.parseInt(request.getParameter("numero")));
            paciente.setLogradouro(request.getParameter("logradouro"));
            paciente.setCEP(request.getParameter("cep"));
            paciente.setEstado(request.getParameter("estado"));
            paciente.setBairro(request.getParameter("bairro"));
            paciente.setTelefone(request.getParameter("telefone"));
            paciente.setSexo(request.getParameter("sexo"));
            paciente.setTipoProcedimento(request.getParameter("tipoProcedimento"));
            paciente.setNomeAcompanhante(request.getParameter("nomeAcompanhante"));
            paciente.setSobrenomeAcompanhante(request.getParameter("sobrenomeAcompanhante"));
            paciente.setTelefoneAcompanhante(request.getParameter("telefoneAcompanhante"));

            LocalDate data = LocalDate.parse(request.getParameter("nascimento"));
            paciente.setNascimento(data);

            String cpf = paciente.getCPF();
            boolean cpfExiste = false;

            try {
                cpfExiste = pDao.buscarPaciente(cpf);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("erroCadastro", "Erro ao buscar o paciente");
                request.getRequestDispatcher("cadastrarPaciente.jsp").forward(request, response);
                return;
            }

            if (!cpfExiste) {
                request.setAttribute("erroCadastro", "Paciente não encontrado");
                request.getRequestDispatcher("cadastrarPaciente.jsp").forward(request, response);
                return;
            }

            if (contemNumeros(paciente.getNome()) || contemNumeros(paciente.getSobrenome())
                    || contemNumeros(paciente.getLogradouro()) || contemNumeros(paciente.getBairro())) {
                request.setAttribute("erroCadastro", "Falha na atualização, verifique os campos e tente novamente.");
                request.getRequestDispatcher("cadastrarPaciente.jsp").forward(request, response);
                return;
            }

            try {
                pDao.atualizarPaciente(paciente);
                request.setAttribute("erroCadastro", "Paciente atualizado com sucesso");
                request.getRequestDispatcher("cadastrarPaciente.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("erroCadastro", "Erro ao atualizar o paciente");
                request.getRequestDispatcher("cadastrarPaciente.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("cadastrarPaciente.jsp");
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
