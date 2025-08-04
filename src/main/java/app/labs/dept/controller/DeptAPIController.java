package app.labs.dept.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import app.labs.dept.model.Dept;
import app.labs.dept.service.DeptService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // URI 규칙, Resource 위주로 서비스
@RequestMapping("api/depts") // uri 소문자로 계층형, 자원명을 복수형 명사로 지정
public class DeptAPIController {
	
	@Autowired
	DeptService deptService;
	
	// 기본 html
	@GetMapping(value= {"index", "/index"})
	public ModelAndView main() {
		
		ModelAndView mv = new ModelAndView("api/list");
		
		return mv;
	}
	
	@GetMapping("")
	public Map<String, Object> getAllDepts() {
		
		Map<String, Object> resultMap = new HashMap<>();
		// ResponseEntity: status code, headers, body를 설정가능.
		
		List<Dept> deptList = deptService.getDeptList();
		resultMap.put("deptList", deptList);
		
		return resultMap;
	}
	
	@GetMapping("/{deptno}")
	public Dept getDeptInfo(@PathVariable("deptno") int deptno) {

		return deptService.getDeptInfo(deptno);
	}
	
	@PostMapping("/")
	public Map<String, Object> insertDept(Dept dept) {
		log.debug(dept.toString());
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			deptService.insertDept(dept);
			resultMap.put("message", dept.getDeptNo() + "번 부서가 등록되었습니다.");
		} catch(RuntimeException e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}
	
	@PutMapping("/")
	public Map<String, Object> updateDept(Dept dept) {
		log.debug(dept.toString());
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			deptService.updateDept(dept);
			resultMap.put("message", dept.getDeptNo() + "번 부서가 수정되었습니다.");
		} catch(RuntimeException e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}
	
	@DeleteMapping("/{deptno}") // delete 메서드는 payload가 불가능하여 @PathVariable를 이용한다.
	public Map<String, Object> deleteDept(@PathVariable("deptno") int deptno) {
		log.debug(Integer.toString(deptno));
		
		Map<String, Object> resultMap = new HashMap<>();

		try {
			int cnt = deptService.deleteDept(deptno);
			
			if (cnt > 0) {
				resultMap.put("message", deptno + "번 부서가 삭제되었습니다.");
			} else {
				resultMap.put("message", "부서번호가 잘못되었습니다.");
			}
			
		} catch(RuntimeException e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}
	
}
