package com.student.result.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.student.result.entity.Student;
import com.student.result.repository.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class DownloadController {

    @Autowired
    private StudentRepository repo;

    // @GetMapping("/marksheet/pdf/{id}")
    // public void downloadPdf(@PathVariable Long id, HttpServletResponse response)
    // throws Exception {

    // Student student = repo.findById(id)
    // .orElseThrow(() -> new RuntimeException("Student not found"));

    // response.setContentType("application/pdf");
    // response.setHeader("Content-Disposition",
    // "attachment; filename=marksheet_" + student.getId() + ".pdf");

    // MarksheetPDF.generate(student, response.getOutputStream());
    // }

    @GetMapping("/marksheet/pdf/{id}")
    public void downloadPdf(@PathVariable Long id, HttpServletResponse response) throws Exception {

        // Fetch student from database
        Student student = repo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        // Set response headers
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=marksheet_" + student.getId() + ".pdf");

        // Create PDF
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Add content
        document.add(new Paragraph("🎓 Student Marksheet"));
        document.add(new Paragraph("ID: " + student.getId()));
        document.add(new Paragraph("Name: " + student.getName()));
        document.add(new Paragraph("Roll No: " + student.getRollNo()));
        document.add(new Paragraph("Class: " + student.getStudentClass()));
        document.add(new Paragraph("Division: " + student.getDivision()));
        document.add(new Paragraph("School: " + student.getSchool()));

        // Close PDF
        document.close();
    }
}
