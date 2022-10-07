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
            case "villa":
                listVillas(request, response);
                break;
            case "house":
                listHouses(request, response);
                break;
            case "room":
                listRooms(request, response);
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
        request.setAttribute("rentType", this.employeeRepository.findRentType());
        request.setAttribute("serviceType", this.employeeRepository.findServiceType());
        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\employee\\listEmployee.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listVillas(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> facilities = this.employeeService.findVilla();
        request.setAttribute("facilities", facilities);
        request.setAttribute("rentType", this.employeeRepository.findRentType());
        request.setAttribute("serviceType", this.employeeRepository.findServiceType());

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\employee\\listEmployee.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listHouses(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> facilities = this.employeeService.findHouse();
        request.setAttribute("facilities", facilities);
        request.setAttribute("rentType", this.employeeRepository.findRentType());
        request.setAttribute("serviceType", this.employeeRepository.findServiceType());

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\employee\\listEmployee.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listRooms(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> facilities = this.employeeService.findRoom();
        request.setAttribute("facilities", facilities);
        request.setAttribute("rentType", this.employeeRepository.findRentType());
        request.setAttribute("serviceType", this.employeeRepository.findServiceType());

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
        int employeeTypeId = Integer.parseInt(request.getParameter("serviceType"));
        Employee employee;
        switch (employeeTypeId) {
            case 1:
                employee = createVilla(request);
                break;
            case 2:
                employee = createHouse(request);
                break;
            case 3:
                employee = createRoom(request);
                break;
            default:
                employee = null;
        }
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

    private Employee createVilla(HttpServletRequest request) {
        String name = request.getParameter("name");
        int area = Integer.parseInt(request.getParameter("area"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        String standard = request.getParameter("standard");
        String description = request.getParameter("description");
        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
        int floors = Integer.parseInt(request.getParameter("floors"));
//        String employeeFree = request.getParameter("employeeFree");
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        int employeeTypeId = Integer.parseInt(request.getParameter("serviceType"));

        return new Employee(name, area, cost, maxPeople, rentTypeId, employeeTypeId, standard, description, poolArea, floors);
    }

    private Employee createHouse(HttpServletRequest request) {
        String name = request.getParameter("name");
        int area = Integer.parseInt(request.getParameter("area"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        String standard = request.getParameter("standard");
        String description = request.getParameter("description");
//        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
        int floors = Integer.parseInt(request.getParameter("floors"));
//        String employeeFree = request.getParameter("employeeFree");
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        int employeeTypeId = Integer.parseInt(request.getParameter("serviceType"));

        return new Employee(name, area, cost, maxPeople, rentTypeId, employeeTypeId, standard, description, floors);
    }

    private Employee createRoom(HttpServletRequest request) {
        String name = request.getParameter("name");
        int area = Integer.parseInt(request.getParameter("area"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
//        String standard = request.getParameter("standard");
//        String description = request.getParameter("description");
//        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
//        int floors = Integer.parseInt(request.getParameter("floors"));
        String employeeFree = request.getParameter("employeeFree");
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        int employeeTypeId = Integer.parseInt(request.getParameter("serviceType"));

        return new Employee(name, area, cost, maxPeople, rentTypeId, employeeTypeId, employeeFree);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        int area = Integer.parseInt(request.getParameter("area"));
//        double cost = Double.parseDouble(request.getParameter("cost"));
//        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
//        String standard = request.getParameter("standard");
//        String description = request.getParameter("description");
//        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
//        int floors = Integer.parseInt(request.getParameter("floors"));
//        String employeeFree = request.getParameter("employeeFree");
//        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
//        int employeeTypeId = Integer.parseInt(request.getParameter("employeeType"));

        Employee employee = this.employeeService.findById(id);
        if (employee == null) {
            request.setAttribute("message", "Chỉnh sửa thất bại! Vui lòng kiểm tra lại.");
        } else {
            int employeeTypeId = Integer.parseInt(request.getParameter("employeeTypeId"));
            switch (employeeTypeId) {
                case 1:
                    updateVilla(request, employee);
                    break;
                case 2:
                    updateHouse(request, employee);
                    break;
                case 3:
                    updateRoom(request, employee);
                    break;
                default:
                    employee = null;
            }
            this.employeeService.update(id, employee);
            request.setAttribute("message", "Cập nhật thông tin sản phẩm thành công!");
        }
        listEmployees(request, response);
    }

    private void updateVilla(HttpServletRequest request, Employee employee) {
        String name = request.getParameter("name");
        employee.setName(name);
        int area = Integer.parseInt(request.getParameter("area"));
        employee.setArea(area);
        double cost = Double.parseDouble(request.getParameter("cost"));
        employee.setCost(cost);
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        employee.setMaxPeople(maxPeople);
        String standard = request.getParameter("standard");
        employee.setStandard(standard);
        String description = request.getParameter("description");
        employee.setDescription(description);
        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
        employee.setPoolArea(poolArea);
        int floors = Integer.parseInt(request.getParameter("floors"));
        employee.setFloors(floors);
//        String employeeFree = request.getParameter("employeeFree");
//        employee.setEmployeeFree(employeeFree);
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        employee.setRentTypeId(rentTypeId);
        int employeeTypeId = Integer.parseInt(request.getParameter("employeeTypeId"));
        employee.setEmployeeTypeId(employeeTypeId);
    }

    private void updateHouse(HttpServletRequest request, Employee employee) {
        String name = request.getParameter("name");
        employee.setName(name);
        int area = Integer.parseInt(request.getParameter("area"));
        employee.setArea(area);
        double cost = Double.parseDouble(request.getParameter("cost"));
        employee.setCost(cost);
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        employee.setMaxPeople(maxPeople);
        String standard = request.getParameter("standard");
        employee.setStandard(standard);
        String description = request.getParameter("description");
        employee.setDescription(description);
//        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
//        employee.setPoolArea(poolArea);
        int floors = Integer.parseInt(request.getParameter("floors"));
        employee.setFloors(floors);
//        String employeeFree = request.getParameter("employeeFree");
//        employee.setEmployeeFree(employeeFree);
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        employee.setRentTypeId(rentTypeId);
        int employeeTypeId = Integer.parseInt(request.getParameter("employeeTypeId"));
        employee.setEmployeeTypeId(employeeTypeId);
    }

    private void updateRoom(HttpServletRequest request, Employee employee) {
        String name = request.getParameter("name");
        employee.setName(name);
        int area = Integer.parseInt(request.getParameter("area"));
        employee.setArea(area);
        double cost = Double.parseDouble(request.getParameter("cost"));
        employee.setCost(cost);
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        employee.setMaxPeople(maxPeople);
//        String standard = request.getParameter("standard");
//        employee.setStandard(standard);
//        String description = request.getParameter("description");
//        employee.setDescription(description);
//        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
//        employee.setPoolArea(poolArea);
//        int floors = Integer.parseInt(request.getParameter("floors"));
//        employee.setFloors(floors);
        String employeeFree = request.getParameter("employeeFree");
        employee.setEmployeeFree(employeeFree);
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        employee.setRentTypeId(rentTypeId);
        int employeeTypeId = Integer.parseInt(request.getParameter("employeeTypeId"));
        employee.setEmployeeTypeId(employeeTypeId);
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
        request.setAttribute("rentType", this.employeeRepository.findRentType());
        request.setAttribute("serviceType", this.employeeRepository.findServiceType());

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
            request.setAttribute("message", "Sản phẩm không tồn tại!");
            listEmployees(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("\\furama_resort\\employee\\editEmployee.jsp");
            request.setAttribute("employee", employee);
            request.setAttribute("rentType", this.employeeRepository.findRentType());
            request.setAttribute("serviceType", this.employeeRepository.findServiceType());
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
