package furama_resort.controller;

import furama_resort.model.Employee;
import furama_resort.repository.impl.EmployeeRepository;
import furama_resort.service.IEmployeeService;
import furama_resort.service.impl.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {
    private IEmployeeService employeeService = new EmployeeService();
    private EmployeeRepository employeeRepository = new EmployeeRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createEmployee(request, response);
                break;
            case "edit":
                updateEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            case "find":
                searchEmployee(request, response);
                break;
            default:
                listEmployees(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "view":
                viewEmployee(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listEmployees(request, response);
                break;
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees = this.employeeService.findAll();
        request.setAttribute("employees", employees);
        request.setAttribute("position", this.employeeRepository.findPosition());
        request.setAttribute("division", this.employeeRepository.findDivision());
        request.setAttribute("education", this.employeeRepository.findEducation());
        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\employee\\list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("\\furama_resort\\employee\\create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createEmployee(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String idCard = request.getParameter("idCard");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int positionId = Integer.parseInt(request.getParameter("positionId"));
        int educationDegreeId = Integer.parseInt(request.getParameter("educationDegreeId"));
        int divisionId = Integer.parseInt(request.getParameter("divisionId"));
        Employee employee = new Employee(name, birthday, idCard, salary, phoneNumber, email, address, positionId, educationDegreeId, divisionId);

        Map<String,String> error = this.employeeService.save(employee);
        if (error.size() != 0) {
            request.setAttribute("message", "Th??m m???i th???t b???i");
            request.setAttribute("error", error);
            request.setAttribute("employee", employee);
            request.setAttribute("position", this.employeeRepository.findPosition());
            request.setAttribute("division", this.employeeRepository.findDivision());
            request.setAttribute("education", this.employeeRepository.findEducation());
        } else {
            request.setAttribute("message", "Th??m m???i th??nh c??ng!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\employee\\create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeService.findById(id);
        if (employee == null) {
            request.setAttribute("message", "Ch???nh s???a th???t b???i! Vui l??ng ki???m tra l???i.");
        } else {
            String name = request.getParameter("name");
            String birthday = request.getParameter("birthday");
            String idCard = request.getParameter("idCard");
            double salary = Double.parseDouble(request.getParameter("salary"));
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            int positionId = Integer.parseInt(request.getParameter("positionId"));
            int educationDegreeId = Integer.parseInt(request.getParameter("educationDegreeId"));
            int divisionId = Integer.parseInt(request.getParameter("divisionId"));

            Employee employeeEdit = new Employee(id, name, birthday, idCard, salary, phoneNumber, email, address, positionId, educationDegreeId, divisionId);
            this.employeeService.update(id, employeeEdit);
            request.setAttribute("message", "C???p nh???t th??ng tin th??nh c??ng!");
        }
        listEmployees(request, response);
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeService.findById(id);
        if (employee == null) {
            request.setAttribute("message", "X??a th???t b???i, vui l??ng ki???m tra l???i!");
        } else {
            this.employeeService.remove(id);
            request.setAttribute("message", "X??a nh??n vi??n th??nh c??ng!");
        }
        listEmployees(request, response);
    }

    private void viewEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeService.findById(id);
        RequestDispatcher dispatcher;
        if (employee == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("employee", employee);
            dispatcher = request.getRequestDispatcher("furama_resort/employee/view.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchEmployee(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees;

        String name = request.getParameter("name");
        int divisionId = Integer.parseInt(request.getParameter("divisionId"));
        if (divisionId == 0) {
            employees = this.employeeService.searchEmployee(name);
        } else {
            employees = this.employeeService.searchEmployee(name, divisionId);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\employee\\list.jsp");
        request.setAttribute("employees", employees);
        request.setAttribute("position", this.employeeRepository.findPosition());
        request.setAttribute("division", this.employeeRepository.findDivision());
        request.setAttribute("education", this.employeeRepository.findEducation());
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeService.findById(id);

        if (employee == null) {
            request.setAttribute("message", "?????i t?????ng kh??ng t???n t???i!");
            listEmployees(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("\\furama_resort\\employee\\edit.jsp");
            request.setAttribute("employee", employee);
            request.setAttribute("position", this.employeeRepository.findPosition());
            request.setAttribute("division", this.employeeRepository.findDivision());
            request.setAttribute("education", this.employeeRepository.findEducation());
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
