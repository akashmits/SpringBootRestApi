package com.springboot.interview.basic.service;

import com.springboot.interview.basic.entities.Order;
import com.springboot.interview.basic.entities.OrderCount;
import com.springboot.interview.basic.iservice.ICheckCounterAndUpdateMemberShip;
import com.springboot.interview.basic.iservice.IEmailService;
import com.springboot.interview.basic.repositories.OrderCountRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckCounterAndUpdateMemberShip implements ICheckCounterAndUpdateMemberShip {

    private IEmailService emailService;
    private OrderCountRepository orderCountRepository;


    public CheckCounterAndUpdateMemberShip(EmailService emailService,OrderCountRepository orderCountRepository){
        this.emailService=emailService;
        this.orderCountRepository=orderCountRepository;
    }

    @Override
    @Async
    public void incrementCounterAndNotify(Long custId,String emailId) {
       Optional<OrderCount> optionalOrderCount = orderCountRepository.findByCustId(custId);

       if(optionalOrderCount.isPresent()){
           OrderCount orderCount1=optionalOrderCount.get();
           String msg="";
           if(orderCount1.getCounter()+1==9){
               msg= "You are 1 order away for gold membership";
               emailService.sendEmail(emailId,msg);
           }else if(orderCount1.getCounter()+1==19){
               msg= "You are 1 order away for premium membership";
               emailService.sendEmail(emailId,msg);
           }
           orderCount1.setCounter(orderCount1.getCounter()+1);
           orderCountRepository.save(orderCount1);
       }else{
           OrderCount orderCount1= new OrderCount();
           orderCount1.setCustId(custId);
           orderCount1.setCounter(1l);
           orderCountRepository.save(orderCount1);
       }
    }

    @Override
    public void incrementCounter(Long custId) {

    }
}
