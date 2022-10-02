package tw.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tw.com.springboot.dao.DepartmentDao;
import tw.com.springboot.dao.EmployeeDao;
import tw.com.springboot.entities.Department;
import tw.com.springboot.entities.Employee;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查詢所有員工返回列表頁面
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

        //放在請求域中
        model.addAttribute("emps", employees);

        return "emp/list";
    }

    //來到員工添加頁面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //查出所有的部門，在頁面顯示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        //來到添加頁面
        return "emp/add";
    }
}
