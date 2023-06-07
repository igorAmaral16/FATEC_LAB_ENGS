package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.FuncionarioDao;
import persistence.GenericDao;
@WebServlet("/excluirFuncionarioServlet")
public class ExcluirFuncionarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    GenericDao gDao = new GenericDao();
	FuncionarioDao fDao = new FuncionarioDao(gDao);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        
        
        try {
            if (fDao.excluirFuncionario(cpf)) {
                request.setAttribute("mensagem", "Funcionário excluído com sucesso.");
            } else {
                request.setAttribute("mensagem", "Funcionário não encontrado ou erro durante a exclusão.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Ocorreu um erro ao excluir o funcionário.");
        }

        request.getRequestDispatcher("excluirFuncionario.jsp").forward(request, response);
    }
}

