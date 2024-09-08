package com.app.eRegistrar.controllers;

import com.app.eRegistrar.models.Student;
import com.app.eRegistrar.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students/list";
    }

    @GetMapping("/new")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/new";  // Render the form for a new student
    }


    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);  // Save student via service
        return "redirect:/students/list";  // Redirect to the student list page after saving
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);  // Add student to the model
            return "students/edit";  // Render the edit.html template
        }
        return "redirect:/students/list";  // Redirect if the student does not exist
    }


    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student student) {
        student.setStudentId(id);
        studentService.saveStudent(student);
        return "redirect:/students/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students/list";
    }

    // Serve the search page
    @GetMapping("/search")
    public String searchPage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null) {
            List<Student> students = studentService.searchStudents(keyword);
            model.addAttribute("students", students);
        }
        return "students/search";  // This will render the search.html template
    }

}