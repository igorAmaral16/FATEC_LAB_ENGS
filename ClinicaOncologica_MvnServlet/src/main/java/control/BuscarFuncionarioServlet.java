package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Funcionario;
import persistence.FuncionarioDao;
import persistence.GenericDao;

@WebServlet("/buscarFuncionarioServlet")
public class BuscarFuncionarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    GenericDao gDao = new GenericDao();
	FuncionarioDao fDao = new FuncionarioDao(gDao);


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");

        try {
            List<Funcionario> funcionarios = fDao.buscarFuncionarioCompleto(cpf);

            if (!funcionarios.isEmpty()) {
                Funcionario funcionario = funcionarios.get(0);
                request.setAttribute("funcionario", funcionario);
            }else {
            	String erro = "FUNCIONÁRIO NÃO ENCONTRADO";
            	request.setAttribute("erro", erro);
            }

            request.getRequestDispatcher("buscarFuncionario.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

}
