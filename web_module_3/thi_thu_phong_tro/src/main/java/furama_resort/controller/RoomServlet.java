package furama_resort.controller;

import furama_resort.model.Room;
import furama_resort.repository.impl.RoomRepository;
import furama_resort.service.IRoomService;
import furama_resort.service.impl.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RoomServlet", urlPatterns = "/rooms")
public class RoomServlet extends HttpServlet {
    private IRoomService roomService = new RoomService();
    private RoomRepository roomRepository = new RoomRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createRoom(request, response);
                break;
            case "edit":
                updateEmployee(request, response);
                break;
            case "delete":
                deleteRoom(request, response);
                break;
            case "find":
                searchEmployee(request, response);
                break;
            default:
                listRooms(request, response);
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
                listRooms(request, response);
                break;
        }
    }

    private void listRooms(HttpServletRequest request, HttpServletResponse response) {
        List<Room> rooms = this.roomService.findAll();
        request.setAttribute("rooms", rooms);
        request.setAttribute("rentType", this.roomRepository.findRentType());
        RequestDispatcher dispatcher = request.getRequestDispatcher("rooms_management\\list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("\\rooms_management\\create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createRoom(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
//        String birthday = request.getParameter("birthday");
//        String idCard = request.getParameter("idCard");
//        double salary = Double.parseDouble(request.getParameter("salary"));
        String phoneNumber = request.getParameter("phoneNumber");
        String rentDay = request.getParameter("rentDay");
        int payMethod = Integer.parseInt(request.getParameter("payMethod"));
        String description = request.getParameter("description");

//        int educationDegreeId = Integer.parseInt(request.getParameter("educationDegreeId"));
//        int divisionId = Integer.parseInt(request.getParameter("divisionId"));
        Room room = new Room(name, phoneNumber, rentDay, payMethod, description);


        Map<String, String> error = this.roomService.save(room);
        if (error.size() != 0) {
            request.setAttribute("message", "Thêm mới thất bại");
            request.setAttribute("error", error);
            request.setAttribute("room", room);
            request.setAttribute("rentType", this.roomRepository.findRentType());
//            request.setAttribute("division", this.roomRepository.findDivision());
//            request.setAttribute("education", this.roomRepository.findEducation());
        } else {
            request.setAttribute("message", "Thêm mới thành công!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("rooms_management\\create.jsp");
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
        Room room = this.roomService.findById(id);
        if (room == null) {
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

//            Room roomEdit = new Room(id, name, birthday, idCard, salary, phoneNumber, email, address, positionId, educationDegreeId, divisionId);
            Room roomEdit = null;

            this.roomService.update(id, roomEdit);
            request.setAttribute("message", "Cập nhật thông tin thành công!");
        }
        listRooms(request, response);
    }


    private void deleteRoom(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Room room = this.roomService.findById(id);
        if (room == null) {
            request.setAttribute("message", "Xóa thất bại, vui lòng kiểm tra lại!");
        } else {
            this.roomService.remove(id);
            request.setAttribute("message", "Xóa thành công!");
        }
        listRooms(request, response);
    }

    private void viewEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Room room = this.roomService.findById(id);
        RequestDispatcher dispatcher;
        if (room == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("room", room);
            dispatcher = request.getRequestDispatcher("furama_resort/room/view.jsp");
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
        List<Room> rooms;

//        String name = request.getParameter("name");
//        int divisionId = Integer.parseInt(request.getParameter("divisionId"));
//        if (divisionId == 0) {
//            rooms = this.roomService.searchEmployee(name);
//        } else {
//            rooms = this.roomService.searchEmployee(name, divisionId);
//        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\room\\list.jsp");
//        request.setAttribute("rooms", rooms);
//        request.setAttribute("position", this.roomRepository.findPosition());
//        request.setAttribute("division", this.roomRepository.findDivision());
//        request.setAttribute("education", this.roomRepository.findEducation());
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Room room = this.roomService.findById(id);

        if (room == null) {
            request.setAttribute("message", "Đối tượng không tồn tại!");
            listRooms(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("\\furama_resort\\room\\edit.jsp");
            request.setAttribute("room", room);
            request.setAttribute("position", this.roomRepository.findRentType());
//            request.setAttribute("division", this.roomRepository.findDivision());
//            request.setAttribute("education", this.roomRepository.findEducation());
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
