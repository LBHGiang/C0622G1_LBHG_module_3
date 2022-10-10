package furama_resort.service.impl;

import furama_resort.model.Room;
import furama_resort.repository.IRoomRepository;
import furama_resort.repository.impl.RoomRepository;
import furama_resort.service.IRoomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomService implements IRoomService {
    private IRoomRepository iRoomRepository = new RoomRepository();

    @Override
    public List<Room> findAll() {
        return iRoomRepository.findAll();
    }

    @Override
    public Map<String, String> save(Room room) {
        Map<String, String> errorMap = new HashMap<>();
//
//        if (!Validation.checkNull(room.getName())) {
//            errorMap.put("name", "Tên không được để trống!");
//        } else if (!Validation.checkName(room.getName())) {
//            errorMap.put("name", "Tên không đúng định dạng");
//        }
//
//        if (!Validation.checkNull(room.getRentDay())) {
//            errorMap.put("birthday", "Vui lòng chọn ngày sinh!");
//        } else if (!Validation.checkDateAndAge(room.getRentDay())) {
//            errorMap.put("birthday", "Ngày sinh không đúng hoặc bạn chưa đủ 18 tuổi");
//        }
//
//        if (!Validation.checkNull(room.getIdCard())) {
//            errorMap.put("idCard", "CMND không được để trống!");
//        } else if (!Validation.checkIdCard(room.getIdCard())) {
//            errorMap.put("idCard", "CMND không đúng định dạng. (Yêu cầu: 9 hoặc 12 chữ số)");
//        }
//
//        if (!Validation.checkNumber(room.getSalary())) {
//            errorMap.put("salary", "Lương phải lớn hơn 10000");
//        }
//
//        if (!Validation.checkNull(room.getPhoneNumber())) {
//            errorMap.put("phone", "SĐT không được để trống!");
//        } else if (!Validation.checkPhone(room.getPhoneNumber())) {
//            errorMap.put("phone", "SĐT không đúng định dạng.");
//        }
//
//        if (!Validation.checkNull(room.getEmail())) {
//            errorMap.put("email", "Email không được để trống!");
//        } else if (!Validation.checkEmail(room.getEmail())) {
//            errorMap.put("email", "Email không đúng định dạng");
//        }
//
//        if (!Validation.checkNull(room.getDescription())) {
//            errorMap.put("address", "Địa chỉ không được để trống!");
//        } else if (!Validation.checkAddress(room.getDescription())) {
//            errorMap.put("address", "Địa chỉ không đúng định dạng (Yêu cầu: Tên Huyện - Tên Tỉnh)");
//        }
//
        if (errorMap.size() == 0) {
            iRoomRepository.save(room);
        }
        return errorMap;
    }

    @Override
    public Room findById(int id) {
        return iRoomRepository.findById(id);
    }

    @Override
    public void update(int id, Room room) {
        iRoomRepository.update(id, room);
    }

    @Override
    public void remove(int id) {
        iRoomRepository.remove(id);
    }

    @Override
    public List<Room> searchEmployee( int searchId, String searchName, String searchPhoneNumber) {
        return iRoomRepository.searchEmployee(searchId, "%" + searchName + "%","%" + searchPhoneNumber + "%" );
    }
}
