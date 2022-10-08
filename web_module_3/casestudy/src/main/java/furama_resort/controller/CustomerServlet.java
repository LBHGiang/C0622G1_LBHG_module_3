package furama_resort.controller;

import furama_resort.model.Customer;
import furama_resort.repository.impl.CustomerRepository;
import furama_resort.service.ICustomerService;
import furama_resort.service.impl.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private ICustomerService customerService = new CustomerService();
    private CustomerRepository customerRepository = new CustomerRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createCustomer(request, response);
                break;
            case "createByModal":
                createCustomerByModal(request, response);
                break;
            case "edit":
                updateCustomer(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            case "find":
                searchCustomer(request, response);
                break;
            default:
                listCustomers(request, response);
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
                viewCustomer(request, response);
                break;
            default:
                listCustomers(request, response);
                break;
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customers = this.customerService.findAll();
        request.setAttribute("customers", customers);
        request.setAttribute("customerType", this.customerRepository.getCustomerType());
        request.setAttribute("gender", this.customerRepository.getGender());
        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\customer\\list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("\\furama_resort\\customer\\create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = getCustomer(request);
        this.customerService.save(customer);

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\customer\\create.jsp");
        request.setAttribute("message", "Thêm mới thành công!");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCustomerByModal(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = getCustomer(request);
        this.customerService.save(customer);
        request.setAttribute("message", "Thêm mới thành công!");
        listCustomers(request, response);
    }

    private Customer getCustomer(HttpServletRequest request) {
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String idCard = request.getParameter("idCard");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int customerTypeId = Integer.parseInt(request.getParameter("customerTypeId"));

        return new Customer(name, birthday, gender, idCard, phoneNumber, email, address, customerTypeId);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        if (customer == null) {
            request.setAttribute("message", "Chỉnh sửa thất bại! Vui lòng kiểm tra lại.");
        } else {
         Customer customerNew = getCustomer(request);
            this.customerService.update(id, customerNew);
            request.setAttribute("message", "Cập nhật thông tin thành công!");
        }
        listCustomers(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        if (customer == null) {
            request.setAttribute("message", "Xóa thất bại, vui lòng kiểm tra lại!");
        } else {
            this.customerService.remove(id);
            request.setAttribute("message", "Xóa nhân viên thành công!");
        }
        listCustomers(request, response);
    }

    private void viewCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if (customer == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("customer", customer);
            dispatcher = request.getRequestDispatcher("furama_resort/customer/view.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchCustomer(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customers;

        String name = request.getParameter("name");
        int customerTypeId = Integer.parseInt(request.getParameter("customerTypeId"));
        if (customerTypeId == 0) {
            customers = this.customerService.searchCustomer(name);
        } else {
            customers = this.customerService.searchCustomer(name, customerTypeId);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\customer\\list.jsp");
        request.setAttribute("customers", customers);
        request.setAttribute("customerType", this.customerRepository.getCustomerType());
        request.setAttribute("gender", this.customerRepository.getGender());
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}