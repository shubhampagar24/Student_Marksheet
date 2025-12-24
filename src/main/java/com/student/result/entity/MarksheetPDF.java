package com.student.result.entity;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.OutputStream;

public class MarksheetPDF {

    public static void generate(Student student, OutputStream out) throws Exception {

        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter.getInstance(document, out);
        document.open();

        /* ================== FONTS ================== */
        Font schoolFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.RED);
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10);
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font tableHeader = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);

        /* ================== HEADER ================== */
        Paragraph resultCard = new Paragraph("RESULT CARD\n\n", titleFont);
        resultCard.setAlignment(Element.ALIGN_CENTER);
        document.add(resultCard);

        Paragraph schoolName = new Paragraph(
                "AL MUDASSIR PUBLIC HIGH SCHOOL\n", schoolFont);
        schoolName.setAlignment(Element.ALIGN_CENTER);
        document.add(schoolName);

        Paragraph reg = new Paragraph(
                "Registration No: 719/E.D.O\nChak # 32/W.B Vehari\n\n",
                normalFont);
        reg.setAlignment(Element.ALIGN_CENTER);
        document.add(reg);

        /* ================== STUDENT INFO ================== */
        PdfPTable info = new PdfPTable(4);
        info.setWidthPercentage(100);
        info.setSpacingAfter(10);

        info.addCell(infoCell("Name:", boldFont));
        info.addCell(infoCell(student.getName(), normalFont));
        info.addCell(infoCell("Class:", boldFont));
        info.addCell(infoCell(student.getStudentClass(), normalFont));

        info.addCell(infoCell("Roll No:", boldFont));
        info.addCell(infoCell(student.getRollNo(), normalFont));
        info.addCell(infoCell("Division:", boldFont));
        info.addCell(infoCell(student.getDivision(), normalFont));

        document.add(info);

        /* ================== SUBJECT TABLE ================== */
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[] { 6, 30, 15, 15, 20 });

        headerCell(table, "Sr#", tableHeader);
        headerCell(table, "SUBJECT", tableHeader);
        headerCell(table, "Full Marks", tableHeader);
        headerCell(table, "Marks Obtained", tableHeader);
        headerCell(table, "Observation", tableHeader);

        String[] subjects = {
                "Islamiyat", "Urdu", "English", "Math",
                "Social Studies", "Science", "Arabic",
                "History", "Geography", "Home Economics", "Drawing"
        };

        int sr = 1;
        for (String sub : subjects) {
            table.addCell(dataCell(String.valueOf(sr++)));
            table.addCell(dataCell(sub));
            table.addCell(dataCell("100"));
            table.addCell(dataCell(" "));
            table.addCell(dataCell(" "));
        }

        document.add(table);

        /* ================== OBSERVATION ================== */
        Paragraph obs = new Paragraph(
                "\nObservation:\nA: Attendance\nB: Home Work\nC: Hand Writing\nD: Cleanliness\n",
                normalFont);
        document.add(obs);

        /* ================== FOOTER ================== */
        PdfPTable footer = new PdfPTable(2);
        footer.setWidthPercentage(100);
        footer.setSpacingBefore(20);

        PdfPCell teacher = new PdfPCell(new Phrase("Teacher's Signature", boldFont));
        PdfPCell principal = new PdfPCell(new Phrase("Principal", boldFont));

        teacher.setBorder(Rectangle.NO_BORDER);
        principal.setBorder(Rectangle.NO_BORDER);
        principal.setHorizontalAlignment(Element.ALIGN_RIGHT);

        footer.addCell(teacher);
        footer.addCell(principal);

        document.add(footer);

        document.close();
    }

    /* ================== HELPERS ================== */

    private static PdfPCell infoCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private static void headerCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        table.addCell(cell);
    }

    private static PdfPCell dataCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
}
