package com.company.project.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class QuartzMain {



    public static void main(String[] args){

      Map<Integer,Integer> hostqueuetime = new HashMap<>();


      TimeThread timeThread = new TimeThread();
      timeThread.setCount(20);
      timeThread.start();
    }

}
