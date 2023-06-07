package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evolucao;
import persistence.EvolucaoDao;
import persistence.GenericDao;

@WebServlet("/cadastrarEvolucaoServlet")
public class CadastrarEvolucaoServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	GenericDao gDao = new GenericDao();
	EvolucaoDao eDao = new EvolucaoDao(gDao);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("cadastrar".equals(action)) {
            Evolucao evolucao = new Evolucao();
            String atendimento = gerarAtendimento();

            // Obtenha os parâmetros do formulário
            evolucao.setAtendimento(atendimento);
            evolucao.setPaciente(request.getParameter("paciente"));
            evolucao.setTipoEvolucao(request.getParameter("tipoEvolucao"));
            evolucao.setProfissionalSaude(Integer.parseInt(request.getParameter("profissionalSaude")));
            
                try {
                    eDao.inserirEvolucao(evolucao);
                    request.setAttribute("erroCadastro", "Evolucao cadastrada com sucesso!");
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("erroCadastro", e.getMessage());
                }

            request.getRequestDispatcher("cadastrarEvolucao.jsp").forward(request, response);
            
        }else if ("atualizar".equals(action)) {
            Evolucao evolucao = new Evolucao();
            String atendimento = "";
            
            evolucao.setAtendimento(request.getParameter("atendimento"));
            evolucao.setPaciente(request.getParameter("paciente"));
            evolucao.setTipoEvolucao(request.getParameter("tipoEvolucao"));
            evolucao.setProfissionalSaude(Integer.parseInt(request.getParameter("profissionalSaude")));
            
            atendimento = evolucao.getAtendimento();
            System.out.println(atendimento);
            boolean atendimentoExiste = false;

            try {
            	atendimentoExiste = eDao.buscarEvolucao(atendimento);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("erroCadastro", "Erro ao buscar a evolução");
                request.getRequestDispatcher("cadastrarEvolucao.jsp").forward(request, response);
                return;
            }

            if (!atendimentoExiste) {
                request.setAttribute("erroCadastro", "Evolução não encontrada");
                request.getRequestDispatcher("cadastrarEvolucao.jsp").forward(request, response);
                return;
            }

            try {
                eDao.atualizarEvolucao(evolucao);
                request.setAttribute("erroCadastro", "Evolução atualizada com sucesso");
                request.getRequestDispatcher("cadastrarEvolucao.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("erroCadastro", "Erro ao atualizar a evolução");
                request.getRequestDispatcher("cadastrarEvolucao.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("cadastrarEvolucao.jsp");
        }
	}
	
	private String gerarAtendimento() {
	    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    StringBuilder sb = new StringBuilder();

	    Random random = new Random();
	    for (int i = 0; i < 6; i++) {
	        int index = random.nextInt(caracteres.length());
	        char randomChar = caracteres.charAt(index);
	        sb.append(randomChar);
	    }

	    return sb.toString();
	}
}
