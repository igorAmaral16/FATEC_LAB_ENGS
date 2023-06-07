package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Paciente;
import persistence.GenericDao;
import persistence.PacienteDao;

@WebServlet("/listarPacientes")
public class ListarPacientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GenericDao gDao = new GenericDao();
	PacienteDao pDao = new PacienteDao(gDao);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String erro = "";
    	
        try {
            List<Paciente> pacientes = pDao.listarPacientes();
            System.out.println(pacientes);
            if(pacientes != null) {
            request.setAttribute("pacientes", pacientes);
            request.getRequestDispatcher("listarPacientes.jsp").forward(request, response);
            }else {
            	request.setAttribute("erro", erro);
            	request.getRequestDispatcher("listarPacientes.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            erro = "Erro para listar" + e.getMessage();
            
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("listarPacientes.jsp").forward(request, response);
        }
    }
}
