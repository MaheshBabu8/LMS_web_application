package com.example.project.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.entity.Comments;
import com.example.project.entity.Course;
import com.example.project.entity.Lessons;
import com.example.project.entity.Users;
import com.example.project.service.CommentService;
import com.example.project.service.StudentService;
import com.example.project.service.TrainerService;
import com.example.project.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/skillup")
public class SkillUpController {
	UserService userService;
	TrainerService trainerService;
	CommentService commentService;
	StudentService studentService;
	
	@Autowired
	public SkillUpController(UserService userService,TrainerService trainerService,CommentService commentService,StudentService studentService) {
		this.userService = userService;
		this.commentService=commentService;
		this.trainerService = trainerService;
		this.studentService=studentService;
	}
	
	
	@GetMapping("/viewLesson")
	public String viewLesson(@RequestParam("lessonId")int lessonId,Model model,HttpSession session) {
	// Users user = (Users) session.getAttribute("loggedInUser");
	Lessons lesson = studentService.getLesson(lessonId);
	// Extract the YouTube video id from the URL
	String youtubeUrl = lesson.getLink();
	String videoId = youtubeUrl.substring(youtubeUrl.indexOf("=") + 1);
	lesson.setLink(videoId);
	model.addAttribute("lesson",lesson);
	List<Comments> commentsList=commentService.commentList();
	model.addAttribute("comments",commentsList);

	return "myLesson";

	}
	
	
	
	@GetMapping("/fetchCourses")
	public String fetchCourses(Model model, HttpSession session) {
		Users loggedUser=(Users)session.getAttribute("loggedInUser");
		String email=loggedUser.getEmail();
		Users user=userService.getUser(email);

	List<Course> courseList=user.getCourses();

	model.addAttribute("courseList",courseList);

	return "myCourses";

	}
	
	
	
	
	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(@RequestParam("amount") int amount,@RequestParam("email") String email,@RequestParam("courseId") int courseId) {

	// System.out.println(amount+email+courseId);
	Order order=null;
	try {
	RazorpayClient razorpay=new RazorpayClient("rzp_test_ANiFbfIjOIx3EF", "jgHc0g5bAebbeilwWX1uXP45");
	JSONObject orderRequest = new JSONObject();
	orderRequest.put("amount", amount*100); // amount in the smallest currency unit
	orderRequest.put("currency", "INR");
	orderRequest.put("receipt", "order_rcptid_11");
	order = razorpay.orders.create(orderRequest);
	attachCourse(email, courseId);
	} catch (RazorpayException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	finally {
	return order.toString();
	}

	}
	
	private void attachCourse(String email, int courseId) {
		Course course=trainerService.getCourse(courseId);
		Users user =userService.getUser(email);
		user.getCourses().add(course);
		course.getUserList().add(user);
		userService.updateUser(user);
		trainerService.saveCourse(course);
		
	}
	
	
	
	
	
	

	@GetMapping("/purchaseCourse")
	public String purchaseCourse(Model model,HttpSession session)
	{
		Users user = (Users) session.getAttribute("loggedInUser");
		List<Course> courseList = trainerService.courseList();
		
		model.addAttribute("courseList", courseList);
		model.addAttribute("loggedInUser", user);
		return "purchaseCourseList";
		
	}
	
	
	
	
	
	
	
	
	
	
	//returning addlesson form to add store/save lessons 
	@GetMapping("/addLesson")
	public String addLesson()
	{
		return "addLesson";
	}
	
	//showing courses to UI
	@GetMapping("/showCourses")
	public String showCourses(Model model)
	{
		java.util.List<Course> courses = trainerService.courseList();
		
		model.addAttribute("courseList", courses);
		return "courses";
	}
	
	
	
	
	
	//storing lessons to courses-values are taking from addLesson form
	@PostMapping("/lesson")
	public String lesson(@RequestParam("courseId") int courseId,@RequestParam("lessonId")int lessonId,@RequestParam("lessonName")String lessonName,@RequestParam("topics")String topics,@RequestParam("link")String link)
	{
		Course course = trainerService.getCourse(courseId);
		Lessons lesson = new Lessons(lessonId, lessonName, topics, link,course);
		
		trainerService.addLesson(lesson);
		
		course.getLessons().add(lesson);
		
		trainerService.saveCourse(course);
		
		return "trainerHome";
	}
	
	
	
	
	
	@GetMapping("/createCourse")
	public String createcourse()
	{
		return "createcourse";
	}
	
	
	
	
	
	@PostMapping("/addCourse")
	public String addCourse(@RequestParam("courseId") int id,@RequestParam("courseName") String name,@RequestParam("coursePrice") int price)
	{
		Course course = new Course();
		course.setCourseId(id);
		course.setCourseName(name);
		course.setCoursePrice(price);
		Course c = trainerService.addCourse(course);
		if(c!=null)
		{
			return "trainerHome";
		}
		else
		{
			return "createcoursefail";
		}
	}
	
	


@PostMapping("/validate")
public String validate(@RequestParam String email,@RequestParam String password,HttpSession session)
{
	if(userService.checkEmail(email))
	{
		boolean val=userService.validate(email, password);
		if(val==true)
		{
			Users user = userService.getUser(email);
			session.setAttribute("loggedInUser", user);
			if(userService.getUserRole(email).equals("trainer"))
			{
				return "trainerHome";
			}
			else
			{
				return "studentHome";
			}
		}
		else
		{
			System.out.println("incorrect credentials");
			return "newLogin";
		}
	}
	else
	{
		return "newLogin";
	}
	
	
}





@GetMapping("/register")
public String register()
{
	return "newRegistration";
}

@GetMapping("/login")
public String login()
{
	return "newLogin";
}





@GetMapping("/loginsuccess")
public String loginsuccess(@RequestParam("emaill") String email)
{
	boolean emailExists=userService.checkEmail(email);
	if(emailExists==true)
	{
		return "loginSuccess";
	}
	else
	{
		return "loginFailed";
	}
	
}






@GetMapping("/registerFail")
public String registerFail()
{
	return "registerfail";
}



	@PostMapping("/adduser")
	public String adduser(@RequestParam("name")String name, @RequestParam("email") String email,@RequestParam("password") String password , @RequestParam("role") String role)
	{
		boolean emailExists=userService.checkEmail(email);
		 if(emailExists==false)
		 {
			Users users=new Users();
			users.setName(name);
			users.setEmail(email);
			users.setPassword(password);
			users.setRole(role);
			
			userService.addUser(users);
			
			System.out.println("user registered successfully");
			return "redirect:/skillup/login";
			
		 }
		 else
		 {
			 System.out.println("user already registered");
			 return "redirect:/skillup/registerFail";
		 }
		 
	}

}