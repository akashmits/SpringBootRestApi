package com.springboot.interview.basic.service;

import com.springboot.interview.basic.iservice.IEmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService
{
    @Override
    public void sendEmail(String emailId, String msg) {
        System.out.println("Email Sent. EmailId"+emailId+", Message :"+msg);
    }
}
