package tw.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    //員工添加
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        System.out.println("員工添加:" + employee);
        employeeDao.save(employee);

        return "redirect:/emps";
    }

    //來到修改頁面，查出當前員工，在頁面回顯
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        //查出所有的部門，在頁面顯示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        //回到修改頁面(add是一個添加修改的二合一頁面)
        return "emp/add";
    }
}
