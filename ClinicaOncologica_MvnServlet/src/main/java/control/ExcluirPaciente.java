package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.GenericDao;
import persistence.PacienteDao;
@WebServlet("/excluirPacienteServlet")
public class ExcluirPaciente extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    GenericDao gDao = new GenericDao();
	PacienteDao pDao = new PacienteDao(gDao);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        
        
        try {
            if (pDao.buscarPaciente(cpf) == false) {
            	request.setAttribute("mensagem", "Paciente não encontrado ou erro durante a exclusão.");
            } else {
                pDao.excluirPaciente(cpf);
                request.setAttribute("mensagem", "Paciente excluído com sucesso.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Ocorreu um erro ao excluir o Paciente.");
        }

        request.getRequestDispatcher("excluirPaciente.jsp").forward(request, response);
    }
}

