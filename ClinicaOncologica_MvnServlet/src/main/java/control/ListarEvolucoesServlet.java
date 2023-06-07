package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evolucao;
import persistence.EvolucaoDao;
import persistence.GenericDao;

@WebServlet("/listarEvolucoes")
public class ListarEvolucoesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GenericDao gDao = new GenericDao();
	EvolucaoDao eDao = new EvolucaoDao(gDao);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String erro = "";
    	
        try {
            List<Evolucao> evolucoes = eDao.listarEvolucoes();
            System.out.println(evolucoes);
            if(evolucoes != null) {
            request.setAttribute("evolucoes", evolucoes);
            request.getRequestDispatcher("listarEvolucoes.jsp").forward(request, response);
            }else {
            	request.setAttribute("erro", erro);
            	request.getRequestDispatcher("listarEvolucoes.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            erro = "Erro para listar" + e.getMessage();
            
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("listarEvolucoes.jsp").forward(request, response);
        }
    }
}
