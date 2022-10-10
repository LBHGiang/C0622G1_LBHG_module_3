package furama_resort.service.impl;

import furama_resort.model.Employee;
import furama_resort.model.Validation;
import furama_resort.repository.IEmployeeRepository;
import furama_resort.repository.impl.EmployeeRepository;
import furama_resort.service.IEmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService implements IEmployeeService {
    private IEmployeeRepository iEmployeeRepository = new EmployeeRepository();

    @Override
    public List<Employee> findAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public Map<String, String> save(Employee employee) {
        Map<String, String> errorMap = new HashMap<>();

        if (!Validation.checkNull(employee.getName())) {
            errorMap.put("name", "Tên không được để trống!");
        } else if (!Validation.checkName(employee.getName())) {
            errorMap.put("name", "Tên không đúng định dạng");
        }

        if (!Validation.checkNull(employee.getBirthday())) {
            errorMap.put("birthday", "Vui lòng chọn ngày sinh!");
        } else if (!Validation.checkDateAndAge(employee.getBirthday())) {
            errorMap.put("birthday", "Ngày sinh không đúng hoặc bạn chưa đủ 18 tuổi");
        }

        if (!Validation.checkNull(employee.getIdCard())) {
            errorMap.put("idCard", "CMND không được để trống!");
        } else if (!Validation.checkIdCard(employee.getIdCard())) {
            errorMap.put("idCard", "CMND không đúng định dạng. (Yêu cầu: 9 hoặc 12 chữ số)");
        }

        if (!Validation.checkNumber(employee.getSalary())) {
            errorMap.put("salary", "Lương phải lớn hơn 10000");
        }

        if (!Validation.checkNull(employee.getPhoneNumber())) {
            errorMap.put("phone", "SĐT không được để trống!");
        } else if (!Validation.checkPhone(employee.getPhoneNumber())) {
            errorMap.put("phone", "SĐT không đúng định dạng.");
        }

        if (!Validation.checkNull(employee.getEmail())) {
            errorMap.put("email", "Email không được để trống!");
        } else if (!Validation.checkEmail(employee.getEmail())) {
            errorMap.put("email", "Email không đúng định dạng");
        }

        if (!Validation.checkNull(employee.getAddress())) {
            errorMap.put("address", "Địa chỉ không được để trống!");
        } else if (!Validation.checkAddress(employee.getAddress())) {
            errorMap.put("address", "Địa chỉ không đúng định dạng (Yêu cầu: Tên Huyện - Tên Tỉnh)");
        }

        if (errorMap.size() == 0) {
            iEmployeeRepository.save(employee);
        }
        return errorMap;
    }

    @Override
    public Employee findById(int id) {
        return iEmployeeRepository.findById(id);
    }

    @Override
    public void update(int id, Employee employee) {
        iEmployeeRepository.update(id, employee);
    }

    @Override
    public void remove(int id) {
        iEmployeeRepository.remove(id);
    }

    @Override
    public List<Employee> searchEmployee(String searchName, int divisionId) {
        return iEmployeeRepository.searchEmployee("%" + searchName + "%", divisionId);
    }

    @Override
    public List<Employee> searchEmployee(String searchName) {
        return iEmployeeRepository.searchEmployee("%" + searchName + "%");
    }
}
