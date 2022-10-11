package tw.com.springboot.mapper;

import org.apache.ibatis.annotations.*;
import tw.com.springboot.bean.Department;


//指定這是一個操作數據庫的mapper
//@Mapper//當Mapper類太多時，可以在springboot主配置上加上@MapperScan
public interface DepartmentMapper {

    @Select("select * from springboot_dept where id = #{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from springboot_dept where id = #{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")//對Department類中的id屬性進行自增宣告
    @SelectKey(resultType = Integer.class, keyProperty = "id",
            before = true, statement = "select springboot_dept_seq.nextval as id from dual")//oracle自增
    @Insert("insert into springboot_dept(id,dept_name) values (#{id},#{deptName})")
    public int insertDept(Department department);

    @Update("update springboot_dept set dept_name = #{deptName} where id = #{id}")
    public int updateDept(Department department);
}
