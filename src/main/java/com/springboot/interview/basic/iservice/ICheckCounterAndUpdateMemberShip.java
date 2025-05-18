package com.springboot.interview.basic.iservice;

public interface ICheckCounterAndUpdateMemberShip {

    public void incrementCounterAndNotify(Long custId,String emailId);

    public void incrementCounter(Long custId);
}
