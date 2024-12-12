package org.jsp.registration_form.helper;

import org.jsp.registration_form.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;

@Component
public class MailHelper {

    @Autowired
    JavaMailSender sender;

    @Autowired
    TemplateEngine engine;

    public void sendEmail(Student student) {
        MimeMessage message=sender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);
        try{
        helper.setFrom("saishkulkarni7@jsp.com","Head Master");
       
        Context context=new Context();
        context.setVariable("student", student);

        String text=engine.process("email-template.html", context);
       
        helper.setText(text,true);
        
        helper.setSubject("Registration Successful");
        helper.setTo(student.getEmail());
        }catch(Exception e) {
            e.printStackTrace();
        }
        sender.send(message);
    }
    
}
