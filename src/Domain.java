import java.sql.SQLException;
import java.util.List;

import com.evgen.coupons.beans.Company;
import com.evgen.coupons.beans.Coupon;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.dao.CompanyDao;
import com.evgen.coupons.dao.CouponsDao;
import com.evgen.coupons.dao.CustomerDao;
import com.evgen.coupons.enums.CouponType;
import com.evgen.coupons.exceptions.ApplicationException;

public class Domain {

	    public static void main(String[] args) throws SQLException, ApplicationException {
	    	CustomerDao customerDao = new CustomerDao();
	    	CouponsDao couponsDao = new CouponsDao();
	    	CompanyDao companyDao = new CompanyDao();
	    	////5
//	    	Coupon couponAdd = new Coupon();
//	    	couponAdd.setId(23);
//			couponAdd.setTitle("Coupon from uman");
//			couponAdd.setStartDate("15.01.2018");
//			couponAdd.setEndDate("25.02.2018");
//			couponAdd.setAmount(11);
//			couponAdd.setPrice(20);
//			couponAdd.setType(CouponType.FOOD);
//			couponAdd.setMessage("yes");
//			couponAdd.setImage("IMAGE");
//			couponAdd.setCompanyId(34);
//			couponsDao.couponCreate(couponAdd);
	    	
//	    	Customer cust = new Customer();
//	    	cust.setCustomerName("Udo");
//	    	cust.setPassword("123123");
//
	    
	//_________________________________________________________________
		    
			try {
//	        	
//	       
//            List<Customer> customerList = customerDao.getAllCustomers();
//            System.out.println("Customers list:");
//            for (Customer c : customerList) {
//                System.out.println(c);
//            }
//            List<Company> companyList = companyDao.getAllCompanies();
//            System.out.println("Company List:");
//            for (Company comp : companyList) {
//                System.out.println(comp);
//            }
           
            List<Coupon> couponList = couponsDao.getAllCoupons();
            //Thread.sleep(10000);
	        System.out.println("Coupons list:");
            for (Coupon co : couponList) {
                System.out.println(co);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//////	    	
//	    
////            System.out.println("Couponim List:");
////            for (CouponType coup : CouponType.values()) {
////                System.out.println(coup.getName());
////	    }
}
	    
}