package usermanagment.web;
import java.io.OutputStream;

import jakarta.servlet.RequestDispatcher;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import usermanagment.dao.UserDao;
import usermanagment.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
        userDao = new UserDao();
    }
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/view":
                    viewUser(request, response);
                    break;
                case "/generate-pdf":
                    generatePdfAndRespond(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	public void listUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List < User > listUser = userDao.getAllUser();
		        request.setAttribute("listUser", listUser);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		        dispatcher.forward(request, response);
		    }

	public void showNewForm(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		        dispatcher.forward(request, response);
		    }

		    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        User existingUser = userDao.getUser(id);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		        request.setAttribute("user", existingUser);
		        dispatcher.forward(request, response);

		    }
		    private void viewUser(HttpServletRequest request, HttpServletResponse response)
				    throws SQLException, ServletException, IOException {
				        int id = Integer.parseInt(request.getParameter("id"));
				        User existingUser = userDao.getUser(id);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
				        request.setAttribute("user", existingUser);
				        dispatcher.forward(request, response);

				 }
		    public void insertUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		    	
		        String name = request.getParameter("name");
		        String email = request.getParameter("email");
		        String contact = request.getParameter("contact");
		        User newUser = new User(name, email, contact);
		        userDao.saveUser(newUser);
		        response.sendRedirect("list");
		    }

		    public void updateUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        String name = request.getParameter("name");
		        String email = request.getParameter("email");
		        String contact = request.getParameter("contact");

		        User user = new User(id, name, email, contact);
		        userDao.updateUser(user);
		        response.sendRedirect("list");
		    }

		    public void deleteUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        userDao.deleteUser(id);
		        response.sendRedirect("list");
		    }
		    private void generatePdfAndRespond(HttpServletRequest request, HttpServletResponse response)
		            throws SQLException, IOException {
		        List<User> listUser = userDao.getAllUser();

		        // Set the response content type
		        response.setContentType("application/pdf");

		        // Set the header to force download
		        response.setHeader("Content-Disposition", "attachment; filename=userList.pdf");

		        try (OutputStream out = response.getOutputStream()) {
		            generatePdf(listUser, out);
		            out.flush();
		        } catch (Exception e) {
		            e.printStackTrace();
		            // Handle exception appropriately
		            response.getWriter().write("Error generating PDF");
		        }
		    }

		    private void generatePdf(List<User> userList, OutputStream outputStream) {
		        Document document = new Document();

		        try {
		            PdfWriter.getInstance(document, outputStream);
		            document.open();

		            PdfPTable table = new PdfPTable(5);
		            table.setWidthPercentage(100);

		            addTableHeader(table);
		            addRows(table, userList);

		            document.add(table);
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            document.close();
		        }
		    }

		    private void addTableHeader(PdfPTable table) {
		        String[] headers = {"ID", "Name", "Email", "Contact", "Actions"};
		        for (String header : headers) {
		            PdfPCell cell = new PdfPCell(new Paragraph(header));
		            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            table.addCell(cell);
		        }
		    }

		    private void addRows(PdfPTable table, List<User> userList) {
		        for (User user : userList) {
		            table.addCell(String.valueOf(user.getId()));
		            table.addCell(user.getName());
		            table.addCell(user.getEmail());
		            table.addCell(user.getContact());
		            // Add actions as needed
		            table.addCell("");
		        }
		    }

}
