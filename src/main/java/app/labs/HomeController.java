package app.labs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.labs.ex01.TemplateBasicService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("home")	// 공통 url
public class HomeController {
	
	@GetMapping("/hello")
	public String getHome(Model model) {
		return "home/home";
	}
	
	@GetMapping("")
	public String index(Model model) {
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        
        String today = now.format(formatter);
        log.debug(today);
        
        model.addAttribute("today", today);
        
		return "home/helloworld";
	}
	
	@PostMapping("/msg")
	@ResponseBody
	public Map<String, String> getMsg(Model model){
		return Map.of("text", "안녕하세요");
	}
	
	@Autowired
	TemplateBasicService defaultTemplateBasicService;
	
	@GetMapping("/basic")
	public String getBasic(HttpServletRequest request, HttpSession session, Model model) {
		session.setAttribute("msg", "EL Test");
        request.setAttribute("a", 10);
        
        List<String> list = defaultTemplateBasicService.getList();
        Map<String, String> map = defaultTemplateBasicService.getMap();
        app.labs.ex01.UserAccount userAccount = defaultTemplateBasicService.getUserAccount();
        
        model.addAttribute("str", "안녕하세요.");
        model.addAttribute("msg", "<b>EL Test</b>");
        model.addAttribute("num", 3.14);
        model.addAttribute("list", list);
        model.addAttribute("map", map);
        model.addAttribute("user", userAccount);
		
		return "home/basic";
	}
	


}
