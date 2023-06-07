package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.FuncionarioDao;
import persistence.GenericDao;

@WebServlet("/loginServlet")
public class RealizarLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    GenericDao gDao = new GenericDao();
    FuncionarioDao fDao = new FuncionarioDao(gDao);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String tipoUsuario = request.getParameter("tipoUsuario");

        boolean autenticado = false;
        String erro = "";
        
		try {
			autenticado = fDao.procurarFuncionario(usuario, senha, tipoUsuario);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		if (autenticado) {
		    if ("ENFERMEIRO".equals(tipoUsuario) || "MEDICO".equals(tipoUsuario)) {
		        response.sendRedirect("menuProfissional.jsp");
		    } else if ("RH".equals(tipoUsuario)) {
		        response.sendRedirect("menuRh.jsp");
		    } else if ("ATENDENTE".equals(tipoUsuario)) {
		        response.sendRedirect("menuAtendente.jsp");
		    }
		} else {
		    erro = "Login inválido";
		    request.setAttribute("erro", erro);
		    request.getRequestDispatcher("index.jsp").forward(request, response);
		}
    }
}

