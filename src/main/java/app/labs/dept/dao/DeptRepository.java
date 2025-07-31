package app.labs.dept.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.labs.dept.model.Dept;

@Mapper
public interface DeptRepository {
	int getDeptCount();
    int getDeptCount(@Param("deptno") int deptno);
    
    List<Dept> getDeptList();
    Dept getDeptInfo(int deptno);
    
    void insertDept(Dept dept);
    int deleteDept(@Param("deptno") int deptno);
    void updateDept(Dept dept);

}
