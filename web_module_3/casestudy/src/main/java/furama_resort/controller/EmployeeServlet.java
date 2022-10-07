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

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {
    private IEmployeeService employeeService = new EmployeeService();
    private EmployeeRepository employeeRepository = new EmployeeRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        List<Employee> facilities = this.employeeService.findAll();
        request.setAttribute("facilities", facilities);
        request.setAttribute("position", this.employeeRepository.findPosition());
        request.setAttribute("division", this.employeeRepository.findDivision());
        request.setAttribute("education", this.employeeRepository.findEducation());
        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\employee\\listEmployee.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("\\furama_resort\\employee\\createEmployee.jsp");
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

        this.employeeService.save(employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\employee\\createEmployee.jsp");
        request.setAttribute("message", "Thêm mới thành công!");
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
            request.setAttribute("message", "Chỉnh sửa thất bại! Vui lòng kiểm tra lại.");
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
            request.setAttribute("message", "Cập nhật thông tin sản phẩm thành công!");
        }
        listEmployees(request, response);
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeService.findById(id);
        if (employee == null) {
            request.setAttribute("message", "Xóa thất bại, vui lòng kiểm tra lại!");
        } else {
            this.employeeService.remove(id);
            request.setAttribute("message", "Xóa sản phẩm thành công!");
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
        List<Employee> facilities;

        String name = request.getParameter("name");
        double cost = Double.parseDouble(request.getParameter("cost"));
        int serviceTypeId = Integer.parseInt(request.getParameter("serviceType"));
        if (serviceTypeId == 0) {
            facilities = this.employeeService.findEmployee(name, cost);
        } else {
            facilities = this.employeeService.findEmployee(name, cost, serviceTypeId);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\employee\\listEmployee.jsp");
        request.setAttribute("facilities", facilities);
        request.setAttribute("position", this.employeeRepository.findPosition());
        request.setAttribute("division", this.employeeRepository.findDivision());
        request.setAttribute("education", this.employeeRepository.findEducation());

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    private void showFindForm(HttpServletRequest request, HttpServletResponse response) {
//        List<Employee> facilities = this.employeeService.findAll();
//        request.setAttribute("facilities", facilities);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort/employee/find.jsp");
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeService.findById(id);

        if (employee == null) {
            request.setAttribute("message", "Đối tượng không tồn tại!");
            listEmployees(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("\\furama_resort\\employee\\editEmployee.jsp");
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
