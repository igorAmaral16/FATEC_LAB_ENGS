package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.EvolucaoDao;
import persistence.GenericDao;
@WebServlet("/excluirEvolucaoServlet")
public class ExcluirEvolucaoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    GenericDao gDao = new GenericDao();
	EvolucaoDao eDao = new EvolucaoDao(gDao);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String atendimento = request.getParameter("atendimento");

	    // Chame o método de exclusão da evolução
	    boolean excluido = false;
		try {
			excluido = eDao.excluirEvolucao(atendimento);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // Encaminhe a requisição para uma página JSP que mostrará a mensagem apropriada
	    String mensagem;
	    if (excluido) {
	        mensagem = "Evolução excluída com sucesso!";
	    } else {
	        mensagem = "Falha ao excluir a evolução. Verifique o atendimento informado.";
	    }

	    request.setAttribute("mensagem", mensagem);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("excluirEvolucao.jsp");
	    dispatcher.forward(request, response);
	}

}

