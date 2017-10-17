package com.evgen.coupons.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.evgen.coupons.dao.CouponsDao;
import com.evgen.coupons.enums.ErrorType;
import com.evgen.coupons.exceptions.ApplicationException;

import java.util.TimerTask;

public class CouponDailyExpirationRemover extends TimerTask implements Runnable  {
	public void run() {
		CouponsDao couponsDao = new CouponsDao();
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String formattedString = localDate.format(formatter);		
		String date = formattedString;
		try {
			couponsDao.couponDeleteExpired(date);
		} catch (ApplicationException e) {
			try {
				throw new ApplicationException(e, ErrorType.SCHEDULE_ERROR);
			} catch (ApplicationException e1) {
				System.err.println("Scheduler error");
				e1.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		Thread remover = new Thread(new CouponDailyExpirationRemover());
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 1);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		// every night at 1am run remover
		Timer timer = new Timer();
		timer.schedule(new CouponDailyExpirationRemover(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period:
																										// 1
																										// day

		remover.start();
	}
}