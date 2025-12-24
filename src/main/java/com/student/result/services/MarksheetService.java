package com.student.result.services;

import org.springframework.stereotype.Service;

import com.student.result.entity.Marksheet;

@Service
public class MarksheetService {

    public void calculateResult(Marksheet m) {

        int total = m.getMaths() + m.getScience() + m.getEnglish()
                + m.getSocial() + m.getComputer();

        double percentage = total / 5.0;

        m.setTotal(total);
        m.setPercentage(percentage);

        if (percentage >= 75)
            m.setGrade("A");
        else if (percentage >= 60)
            m.setGrade("B");
        else if (percentage >= 50)
            m.setGrade("C");
        else if (percentage >= 35)
            m.setGrade("D");
        else
            m.setGrade("F");

        if (m.getMaths() < 35 || m.getScience() < 35 || m.getEnglish() < 35 ||
                m.getSocial() < 35 || m.getComputer() < 35) {
            m.setResult("FAIL");
        } else {
            m.setResult("PASS");
        }
    }

}
