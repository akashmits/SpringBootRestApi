package com.springboot.interview.basic.servicetest;

import com.springboot.interview.basic.entities.Customer;
import com.springboot.interview.basic.entities.OrderCount;
import com.springboot.interview.basic.iservice.ICustomerService;
import com.springboot.interview.basic.iservice.IEmailService;
import com.springboot.interview.basic.pojo.CustomerType;
import com.springboot.interview.basic.repositories.OrderCountRepository;
import com.springboot.interview.basic.service.CheckCounterAndUpdateMemberShip;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CheckCounterAndUpdateMembershipTest {

    @Mock
    private IEmailService emailService;

    @Mock
    private OrderCountRepository orderCountRepository;

    @Mock
    private ICustomerService customerService;

    @InjectMocks
    private CheckCounterAndUpdateMemberShip checkCounterAndUpdateMemberShip;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNewCustomerOrderCount() {
        Long custId=10L;
        String emailId= "newEmail@gmail.com";

        when(orderCountRepository.findByCustId(custId)).thenReturn(Optional.empty());

        checkCounterAndUpdateMemberShip.incrementCounterAndNotify(custId,emailId);
        ArgumentCaptor<OrderCount> captor = ArgumentCaptor.forClass(OrderCount.class);

        verify(orderCountRepository).save(captor.capture());
        OrderCount orderCount = captor.getValue();

        assert orderCount.getCustId().equals(custId);
        assert orderCount.getCounter()==1L;

    }
    @Test
    void testNewCustomerOrderCount1() {
        Long custId = 1L;
        String email = "test@example.com";

        when(orderCountRepository.findByCustId(custId)).thenReturn(Optional.empty());

        checkCounterAndUpdateMemberShip.incrementCounterAndNotify(custId, email);

        ArgumentCaptor<OrderCount> captor = ArgumentCaptor.forClass(OrderCount.class);
        verify(orderCountRepository).save(captor.capture());

        OrderCount saved = captor.getValue();
        assert saved.getCustId().equals(custId);
        assert saved.getCounter() == 1L;
    }

    @Test
    void testIncrementTo9_SendGoldMail(){
        Long custId = 2L;
        String email ="user@example.com";
        OrderCount orderCount = new OrderCount();

        orderCount.setCustId(custId);
        orderCount.setCounter(8L);

        when(orderCountRepository.findByCustId(custId)).thenReturn(Optional.of(orderCount));
        checkCounterAndUpdateMemberShip.incrementCounterAndNotify(custId,email);
        verify(emailService).sendEmail(email,"You are 1 order away for gold membership");
        verify(orderCountRepository).save(any(OrderCount.class));

    }

    @Test
    void testIncrementTo19_SendPremiumMail(){
        Long custId=3L;
        String email = "user@example.com";
        OrderCount orderCount = new OrderCount();
        orderCount.setCustId(custId);
        orderCount.setCounter(18L);

        when(orderCountRepository.findByCustId(custId)).thenReturn(Optional.of(orderCount));
        checkCounterAndUpdateMemberShip.incrementCounterAndNotify(custId,email);
        verify(emailService).sendEmail(email,"You are 1 order away for premium membership");
        verify(orderCountRepository).save(any(OrderCount.class));

    }

    @Test
    void testIncrementTo10_UpgradeToGold() {
        Long custId = 4L;
        String email ="goldcust@gmail.com";

        OrderCount orderCount = new OrderCount();
        orderCount.setCustId(custId);
        orderCount.setCounter(9L);

        Customer customer = new Customer();
        customer.setCustomerType("REGULAR");
        customer.setId(custId);


        when(orderCountRepository.findByCustId(custId)).thenReturn(Optional.of(orderCount));
        when(customerService.customer(custId)).thenReturn(Optional.of(customer));
       // when(customerService.customer(any(Customer.class))).thenReturn(any());
        checkCounterAndUpdateMemberShip.incrementCounterAndNotify(custId,email);

        assert customer.getCustomerType().equals(CustomerType.GOLD.toString());
        //verify(customerService.customer(custId),times(1)).
        verify(customerService).customer(customer);
        verify(orderCountRepository).save(orderCount);



    }

    @Test
    void testIncrementTo20_UpgradeToPremium() {
        Long custId=6L;
        String emailId="akash@gmail.com";

        OrderCount orderCount = new OrderCount();
        orderCount.setCustId(custId);
        orderCount.setCounter(19L);

        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.GOLD.name());

        when(orderCountRepository.findByCustId(custId)).thenReturn(Optional.of(orderCount));
        when(customerService.customer(custId)).thenReturn(Optional.of(customer));

        checkCounterAndUpdateMemberShip.incrementCounterAndNotify(custId,emailId);

        assert customer.getCustomerType().equals(CustomerType.PREMIUM.toString());

        verify(orderCountRepository).save(orderCount);
        verify(customerService).customer(customer);
    }



}
