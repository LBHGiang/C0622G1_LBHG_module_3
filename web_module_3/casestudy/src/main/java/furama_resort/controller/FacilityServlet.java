package furama_resort.controller;

import furama_resort.model.Facility;
import furama_resort.repository.impl.FacilityRepository;
import furama_resort.service.IFacilityService;
import furama_resort.service.impl.FacilityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FacilityServlet", urlPatterns = "/facilities")
public class FacilityServlet extends HttpServlet {
    private IFacilityService facilityService = new FacilityService();
    private FacilityRepository facilityRepository = new FacilityRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createFacility(request, response);
                break;
            case "edit":
                updateFacility(request, response);
                break;
            case "delete":
                deleteFacility(request, response);
                break;
            case "find":
                searchFacility(request, response);
                break;
            default:
                listFacilities(request, response);
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
                viewFacility(request, response);
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
                listFacilities(request, response);
                break;
        }
    }

    private void listFacilities(HttpServletRequest request, HttpServletResponse response) {
        List<Facility> facilities = this.facilityService.findAll();
        request.setAttribute("facilities", facilities);
        request.setAttribute("rentType", this.facilityRepository.findRentType());
        request.setAttribute("serviceType", this.facilityRepository.findServiceType());
        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\facility\\listFacility.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listVillas(HttpServletRequest request, HttpServletResponse response) {
        List<Facility> facilities = this.facilityService.findVilla();
        request.setAttribute("facilities", facilities);
        request.setAttribute("rentType", this.facilityRepository.findRentType());
        request.setAttribute("serviceType", this.facilityRepository.findServiceType());

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\facility\\listFacility.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listHouses(HttpServletRequest request, HttpServletResponse response) {
        List<Facility> facilities = this.facilityService.findHouse();
        request.setAttribute("facilities", facilities);
        request.setAttribute("rentType", this.facilityRepository.findRentType());
        request.setAttribute("serviceType", this.facilityRepository.findServiceType());

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\facility\\listFacility.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listRooms(HttpServletRequest request, HttpServletResponse response) {
        List<Facility> facilities = this.facilityService.findRoom();
        request.setAttribute("facilities", facilities);
        request.setAttribute("rentType", this.facilityRepository.findRentType());
        request.setAttribute("serviceType", this.facilityRepository.findServiceType());

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\facility\\listFacility.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("\\furama_resort\\facility\\createFacility.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFacility(HttpServletRequest request, HttpServletResponse response) {
        int facilityTypeId = Integer.parseInt(request.getParameter("serviceType"));
        Facility facility;
        switch (facilityTypeId) {
            case 1:
                facility = createVilla(request);
                break;
            case 2:
                facility = createHouse(request);
                break;
            case 3:
                facility = createRoom(request);
                break;
            default:
                facility = null;
        }
        this.facilityService.save(facility);
        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\facility\\createFacility.jsp");
        request.setAttribute("message", "Thêm mới thành công!");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Facility createVilla(HttpServletRequest request) {
        String name = request.getParameter("name");
        int area = Integer.parseInt(request.getParameter("area"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        String standard = request.getParameter("standard");
        String description = request.getParameter("description");
        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
        int floors = Integer.parseInt(request.getParameter("floors"));
//        String facilityFree = request.getParameter("facilityFree");
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        int facilityTypeId = Integer.parseInt(request.getParameter("serviceType"));

        return new Facility(name, area, cost, maxPeople, rentTypeId, facilityTypeId, standard, description, poolArea, floors);
    }

    private Facility createHouse(HttpServletRequest request) {
        String name = request.getParameter("name");
        int area = Integer.parseInt(request.getParameter("area"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        String standard = request.getParameter("standard");
        String description = request.getParameter("description");
//        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
        int floors = Integer.parseInt(request.getParameter("floors"));
//        String facilityFree = request.getParameter("facilityFree");
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        int facilityTypeId = Integer.parseInt(request.getParameter("serviceType"));

        return new Facility(name, area, cost, maxPeople, rentTypeId, facilityTypeId, standard, description, floors);
    }

    private Facility createRoom(HttpServletRequest request) {
        String name = request.getParameter("name");
        int area = Integer.parseInt(request.getParameter("area"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
//        String standard = request.getParameter("standard");
//        String description = request.getParameter("description");
//        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
//        int floors = Integer.parseInt(request.getParameter("floors"));
        String facilityFree = request.getParameter("facilityFree");
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        int facilityTypeId = Integer.parseInt(request.getParameter("serviceType"));

        return new Facility(name, area, cost, maxPeople, rentTypeId, facilityTypeId, facilityFree);
    }

    private void updateFacility(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        int area = Integer.parseInt(request.getParameter("area"));
//        double cost = Double.parseDouble(request.getParameter("cost"));
//        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
//        String standard = request.getParameter("standard");
//        String description = request.getParameter("description");
//        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
//        int floors = Integer.parseInt(request.getParameter("floors"));
//        String facilityFree = request.getParameter("facilityFree");
//        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
//        int facilityTypeId = Integer.parseInt(request.getParameter("facilityType"));

        Facility facility = this.facilityService.findById(id);
        if (facility == null) {
            request.setAttribute("message", "Chỉnh sửa thất bại! Vui lòng kiểm tra lại.");
        } else {
            int facilityTypeId = Integer.parseInt(request.getParameter("facilityTypeId"));
            switch (facilityTypeId) {
                case 1:
                    updateVilla(request, facility);
                    break;
                case 2:
                    updateHouse(request, facility);
                    break;
                case 3:
                    updateRoom(request, facility);
                    break;
                default:
                    facility = null;
            }
            this.facilityService.update(id, facility);
            request.setAttribute("message", "Cập nhật thông tin sản phẩm thành công!");
        }
        listFacilities(request, response);
    }

    private void updateVilla(HttpServletRequest request, Facility facility) {
        String name = request.getParameter("name");
        facility.setName(name);
        int area = Integer.parseInt(request.getParameter("area"));
        facility.setArea(area);
        double cost = Double.parseDouble(request.getParameter("cost"));
        facility.setCost(cost);
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        facility.setMaxPeople(maxPeople);
        String standard = request.getParameter("standard");
        facility.setStandard(standard);
        String description = request.getParameter("description");
        facility.setDescription(description);
        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
        facility.setPoolArea(poolArea);
        int floors = Integer.parseInt(request.getParameter("floors"));
        facility.setFloors(floors);
//        String facilityFree = request.getParameter("facilityFree");
//        facility.setFacilityFree(facilityFree);
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        facility.setRentTypeId(rentTypeId);
        int facilityTypeId = Integer.parseInt(request.getParameter("facilityTypeId"));
        facility.setFacilityTypeId(facilityTypeId);
    }

    private void updateHouse(HttpServletRequest request, Facility facility) {
        String name = request.getParameter("name");
        facility.setName(name);
        int area = Integer.parseInt(request.getParameter("area"));
        facility.setArea(area);
        double cost = Double.parseDouble(request.getParameter("cost"));
        facility.setCost(cost);
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        facility.setMaxPeople(maxPeople);
        String standard = request.getParameter("standard");
        facility.setStandard(standard);
        String description = request.getParameter("description");
        facility.setDescription(description);
//        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
//        facility.setPoolArea(poolArea);
        int floors = Integer.parseInt(request.getParameter("floors"));
        facility.setFloors(floors);
//        String facilityFree = request.getParameter("facilityFree");
//        facility.setFacilityFree(facilityFree);
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        facility.setRentTypeId(rentTypeId);
        int facilityTypeId = Integer.parseInt(request.getParameter("facilityTypeId"));
        facility.setFacilityTypeId(facilityTypeId);
    }

    private void updateRoom(HttpServletRequest request, Facility facility) {
        String name = request.getParameter("name");
        facility.setName(name);
        int area = Integer.parseInt(request.getParameter("area"));
        facility.setArea(area);
        double cost = Double.parseDouble(request.getParameter("cost"));
        facility.setCost(cost);
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        facility.setMaxPeople(maxPeople);
//        String standard = request.getParameter("standard");
//        facility.setStandard(standard);
//        String description = request.getParameter("description");
//        facility.setDescription(description);
//        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
//        facility.setPoolArea(poolArea);
//        int floors = Integer.parseInt(request.getParameter("floors"));
//        facility.setFloors(floors);
        String facilityFree = request.getParameter("facilityFree");
        facility.setFacilityFree(facilityFree);
        int rentTypeId = Integer.parseInt(request.getParameter("rentType"));
        facility.setRentTypeId(rentTypeId);
        int facilityTypeId = Integer.parseInt(request.getParameter("facilityTypeId"));
        facility.setFacilityTypeId(facilityTypeId);
    }

    private void deleteFacility(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Facility facility = this.facilityService.findById(id);
        if (facility == null) {
            request.setAttribute("message", "Xóa thất bại, vui lòng kiểm tra lại!");
        } else {
            this.facilityService.remove(id);
            request.setAttribute("message", "Xóa sản phẩm thành công!");
        }
        listFacilities(request, response);
    }

    private void viewFacility(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Facility facility = this.facilityService.findById(id);
        RequestDispatcher dispatcher;
        if (facility == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("facility", facility);
            dispatcher = request.getRequestDispatcher("furama_resort/facility/view.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchFacility(HttpServletRequest request, HttpServletResponse response) {
        List<Facility> facilities;

        String name = request.getParameter("name");
        double cost = Double.parseDouble(request.getParameter("cost"));
        int serviceTypeId = Integer.parseInt(request.getParameter("serviceType"));
        if (serviceTypeId == 0) {
            facilities = this.facilityService.findFacility(name, cost);
        } else {
            facilities = this.facilityService.findFacility(name, cost, serviceTypeId);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort\\facility\\listFacility.jsp");
        request.setAttribute("facilities", facilities);
        request.setAttribute("rentType", this.facilityRepository.findRentType());
        request.setAttribute("serviceType", this.facilityRepository.findServiceType());

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    private void showFindForm(HttpServletRequest request, HttpServletResponse response) {
//        List<Facility> facilities = this.facilityService.findAll();
//        request.setAttribute("facilities", facilities);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("furama_resort/facility/find.jsp");
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
        Facility facility = this.facilityService.findById(id);

        if (facility == null) {
            request.setAttribute("message", "Sản phẩm không tồn tại!");
            listFacilities(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("\\furama_resort\\facility\\editFacility.jsp");
            request.setAttribute("facility", facility);
            request.setAttribute("rentType", this.facilityRepository.findRentType());
            request.setAttribute("serviceType", this.facilityRepository.findServiceType());
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
