package tw.com.springboot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tw.com.springboot.pojo.Employee;
import tw.com.springboot.pojo.EmployeeExample;

public interface EmployeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    int countByExample(EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    int deleteByExample(EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    int insert(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    int insertSelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    List<Employee> selectByExample(EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    Employee selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    int updateByPrimaryKeySelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SPRINGBOOT_EMP
     *
     * @mbggenerated Mon Oct 10 09:02:47 CST 2022
     */
    int updateByPrimaryKey(Employee record);
}