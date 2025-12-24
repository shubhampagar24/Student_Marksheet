package com.student.result.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.result.services.MarksheetService;
import com.student.result.services.StudentService;

import jakarta.servlet.http.HttpSession;

import com.student.result.entity.Marksheet;
import com.student.result.entity.Student;
import com.student.result.repository.MarksheetRepository;
import com.student.result.repository.StudentRepository;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MarksheetRepository marksheetRepository;

    @Autowired
    private MarksheetService marksheetService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/student")
    public String studentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/marks/" + student.getId();
    }

    @GetMapping("/marks/{id}")
    public String marksForm(@PathVariable Long id, Model model) {
        Marksheet m = new Marksheet();
        m.setStudent(studentRepository.findById(id).get());
        model.addAttribute("marksheet", m);
        return "marks-form";
    }

    @PostMapping("/saveMarks")
    public String saveMarks(@ModelAttribute Marksheet marksheet) {
        marksheetService.calculateResult(marksheet);
        marksheetRepository.save(marksheet);
        return "redirect:/marksheet/" + marksheet.getId();
    }

    @GetMapping("/marksheet/{id}")
    public String viewMarksheet(@PathVariable Long id, Model model) {
        model.addAttribute("m", marksheetRepository.findById(id).get());
        return "marksheet";
    }

    @GetMapping("/students")
    public String viewStudents(HttpSession session, Model model) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "OK";
    }

}
