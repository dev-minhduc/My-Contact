package com.devjava.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devjava.domain.Contact;
import com.devjava.service.ContactService;

//Đây là 1 controller
@Controller
public class ContactController {
	
	//inject ContactService vào ContactController
	@Autowired
	private ContactService contactService;
	
	//Xác định phương thức index sẽ nhận các request có HTTP method là GET và URI pattern là /contact
	@GetMapping("/contact")
	public String index(Model model) {
		model.addAttribute("contacts", contactService.findAll());	
		return "list";		
	}
	
	//Phương thức index() được truyền vào 1 tham số có kiểu dữ liệu là Model. Model có nhiệm vụ truyền dữ liệu từ Controller cho View. Ở đây mình sẽ lấy ra danh sách các liên hệ thông qua contactService.findAll(). Sau đó gắn danh sách này vào Model thông qua phương thức addAttribute(), contacts chính là tên biến đại diện cho danh sách mà ta sẽ dùng ở View sau này.
	//Phương thức index() trả về 1 String, từ String này Spring sẽ suy ra View nào sẽ nhận dữ liệu từ Controller: return "list";" => view là list.html
	
	//Phương thức tạo mới
	@GetMapping("/contact/create")
	public String create(Model model) {
	    model.addAttribute("contact", new Contact());
	    return "form";
	}
	
	//Phương thức lưu
	@PostMapping("/contact/save") //Request gửi lên HTTP method là POST
	//Bật validation sử dụng @Valid.
	public String save(@Valid Contact contact, BindingResult result, RedirectAttributes redirect) {
	    if (result.hasErrors()) {
	        return "form";
	        //Nếu xảy ra lỗi quay lại form
	    }
	    //Còn không nó thực hiện lưu và hiển thị ra thông báo và return lại index
	    contactService.save(contact);
	    redirect.addFlashAttribute("success", "Saved contact successfully!");
	    return "redirect:/contact";
	}
	
	//Phương thức sửa
	//Tham số PathVariable int id lấy ID của contact từ đường dẫn rồi gán vào biến id. từ id sẽ liên hệ bằng phương thức findOne() của ContactService, chuyển sang form.html
	@GetMapping("/contact/{id}/edit")
	public String edit(@PathVariable int id, Model model) {
	    model.addAttribute("contact", contactService.findOne(id));
	    return "form";
	}
	
	//Phương thức xóa
	@GetMapping("/contact/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirect) {
	    contactService.delete(id);
	    redirect.addFlashAttribute("success", "Deleted contact successfully!");
	    return "redirect:/contact";
	 }
	
	//Phương thức tìm kiếm
	//Do sử form sử dụng method là GET nên giá trị input sẽ hiển thị URL. Dùng @RequestParam để lấy tham số trên URL
	@GetMapping("/contact/search")
	public String search(@RequestParam("q") String q, Model model) {
	    if (q.equals("")) {
	        return "redirect:/contact";
	        //Nếu như chuỗi tìm kiếm rỗng nó quay lại về trang chủ
	    }

	    model.addAttribute("contacts", contactService.search(q));
	    return "list";
	}
}
