package org.jsp.registration_form.service;

import org.jsp.registration_form.dto.Student;
import org.jsp.registration_form.helper.MailHelper;
import org.jsp.registration_form.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    MailHelper mailHelper;

    public String register(Student student, BindingResult result, ModelMap map) {
        if (!student.getPassword().equals(student.getConfirmPassword()))
            result.rejectValue("confirmPassword", "error.confirmPassword",
                    "Password and Confirm Password does not match");
        if (repository.existsByEmail(student.getEmail()))
            result.rejectValue("email", "error.email", "Email already exists");

        if (result.hasErrors())
            return "register.html";

        else {
            repository.save(student);
            map.put("success", "Registered Successfully");
            mailHelper.sendEmail(student);
            return "register.html";
        }
    }
}
