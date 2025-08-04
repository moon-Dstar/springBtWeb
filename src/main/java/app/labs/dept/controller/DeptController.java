package app.labs.dept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.labs.dept.model.Dept;
import app.labs.dept.service.DeptService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("dept")
public class DeptController {
	
	@Autowired
	DeptService deptService;
	
	@GetMapping(value= {"", "/", "main"})
	public String main(Model model) {
		model.addAttribute("serverTime", "서버시간");
		return "dept/main";
	}
	
	@GetMapping("count")
	public String getDeptCount(@RequestParam(value="deptno", required=false, defaultValue="0") int deptno, Model model) {
		if (deptno==0) {
			model.addAttribute("count", deptService.getDeptCount());
		} else {
			model.addAttribute("count", deptService.getDeptCount(deptno));
		}
		return "dept/main";
		
	}
	
	@GetMapping("list")
	public String getAllDepts(Model model) {
		
		List<Dept> deptList = deptService.getDeptList();
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("dept", new Dept());
		
		return "dept/list";
	}
	
	@GetMapping("list/{deptno}")
	public String getDeptInfo(@PathVariable("deptno") int deptno, Model model) {
		
		List<Dept> deptList = deptService.getDeptList();
		Dept dept = deptService.getDeptInfo(deptno);
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("dept", dept);
		
		return "dept/list";
	}
	
	@PostMapping("insert")
	public String insertDept(Dept dept, RedirectAttributes redirectAttributes) {
		log.debug(dept.toString());
		
		try {
			deptService.insertDept(dept);
			redirectAttributes.addFlashAttribute("message", dept.getDeptNo() + "번 부서가 등록되었습니다.");
		} catch(RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/dept/list";
	}
	
	@PostMapping("update")
	public String updateDept(Dept dept, RedirectAttributes redirectAttributes) {
		log.debug(dept.toString());
		
		try {
			deptService.updateDept(dept);
			redirectAttributes.addFlashAttribute("message", dept.getDeptNo() + "번 부서가 수정되었습니다.");
		} catch(RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/dept/list";
	}
	
	@PostMapping("delete")
	public String deleteDept(Dept dept, Model model, RedirectAttributes redirectAttributes) {
		log.debug(dept.toString());
		
		try {
			int cnt = deptService.deleteDept(dept.getDeptNo());
			if (cnt > 0) {
				redirectAttributes.addFlashAttribute("message", dept.getDeptNo() + "번 부서가 삭제되었습니다.");
				return "redirect:/dept/list";
			} else {
				redirectAttributes.addFlashAttribute("message", "부서번호가 잘못되었습니다.");	
				// model.addAttribute("dept", deptService.getDeptInfo(dept.getDeptNo()));
				// return "dept/list";
				return "redirect:/dept/list" + dept.getDeptNo();
			}
		} catch(RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/dept/list";
		}
		
	}
	
	
	
	
	
	
	
}
