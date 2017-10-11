package com.evgen.coupons.utils;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import com.evgen.coupons.beans.Coupon;
import com.evgen.coupons.dao.CompanyDao;
import com.evgen.coupons.dao.CouponsDao;
import com.evgen.coupons.dao.CustomerDao;

public class CouponDailyExpirationRemover implements Runnable{
	
		private boolean quit;
		private final CustomerDao customerDao;
		private final CompanyDao companyDao;
		private final CouponsDao couponsDao;
		private final TimeUnit tuPeriod;
		private final  long periodValue;
		private Date lastRun=null;
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while(!quit){
				Date today=new Date(GregorianCalendar.getInstance().getTimeInMillis());
				if(lastRun==null || CouponDbHelper.computeDetailedDateDiff(today, lastRun).get(tuPeriod)>periodValue) {
					try {
						for(Coupon outdatedCoupon: couponsDao.realAllOutdated(today)) {
							customerDao.deleteAllCoupons(outdatedCoupon.getID()); //Delete from all customers
							companyDao.deleteAllCoupons(outdatedCoupon.getID()); //Delete from company
							couponsDao.delete(outdatedCoupon);						  //Delete the entity record
							lastRun=today;
							Thread.sleep(1000);
						}
					} catch (InterruptedException | CouponSystemException e) {
						System.out.println("DailyExpirationRoutine interrupted by force, quit flag="+this.quit);
					}				
				}
			}
			
		}
		/**
		 * @throws CouponSystemException 
		 * 
		 */
		public DailyCouponExpirationTask(TimeUnit tuPeriod,long periodValue) throws CouponSystemException {
			super();
			quit=false;
			this.customerDao=new CustomerCouponDBDAO();
			this.companyDao=new CompanyCouponDBDAO();
			this.couponsDao=new CouponDBDAO();
			this.tuPeriod=tuPeriod;
			this.periodValue=periodValue;
		}

		
		/**
		 * Gracefully stops the task infinite thread
		 */
		public void stopTask(){
			quit=true;
		}
	

}
