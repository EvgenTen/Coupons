//package com.evgen.coupons.utils;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class TimerT extends TimerTask implements Runnable {
//	public void run() {
//
//System.out.println("HI " );
//
//
//    } 
//  
//		  public static void main(String[] args) {
//				
//				Thread remover = new Thread(new TimerT());
//				Calendar today = Calendar.getInstance();
//				today.set(Calendar.HOUR_OF_DAY, 1);
//				today.set(Calendar.MINUTE, 0);
//				today.set(Calendar.SECOND, 0);
//
//				// every night at 2am you run your task
//				Timer timer = new Timer();
//				timer.schedule(new TimerT(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
//
//				remover.start();
//		  }
//			
//	}