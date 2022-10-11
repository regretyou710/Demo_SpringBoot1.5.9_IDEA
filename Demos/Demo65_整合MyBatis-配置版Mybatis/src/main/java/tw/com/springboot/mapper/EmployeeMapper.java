package tw.com.springboot.mapper;

import tw.com.springboot.bean.Employee;

//@Mapper或者@MapperScan將接口掃描裝配到容器中
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
