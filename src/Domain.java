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
	    	
//Coupon Create:
//	    	Coupon couponAdd = new Coupon();
//	    	couponAdd.setId(4);
//			couponAdd.setTitle("Coupon from 235");
//			couponAdd.setStartDate("15.01.2018");
//			couponAdd.setEndDate("25.02.2018");
//			couponAdd.setAmount(122);
//			couponAdd.setPrice(12.5f);
//			couponAdd.setType(CouponType.TRAVELLING);
//			couponAdd.setMessage("phpmyadmin");
//			couponAdd.setImage("IMAGE");
//			couponAdd.setCompanyId(7);
//			couponsDao.couponCreate(couponAdd);
//Company Create:	    	
//	    	Company com = new Company(9,"Adidas", "1234567", "dfdf@dgdf.com");
//	    	com.setId(2);
//	    	com.setCompanyName("Kamboja");
//	    	com.setEmail("email@mail.ru");
//	    	com.setPassword("123456789");
//	    	companyDao.companyCreate(com);
//Customer Create:    	
//	     	Customer cust = new Customer(11,"Name", "Pass");
//	     	cust.setId(5);
//	    	cust.setCustomerName("Mark");
//	    	cust.setPassword("504624");
//	    	customerDao.customerCreate(cust);
	    	
//_________________________________________________________________

//Coupon Update:
	    	Coupon couponUpd = new Coupon();
	    			//("Glida Barvazzi", "23.05.2018", "23.06.2018", 100, "Very Tasty", 9.99f, "BIG IMAGE", 11, CouponType.CAMPING);
	    	
			couponUpd.setTitle("Glida Barvazzi");
			couponUpd.setStartDate("15.01.2018");
			couponUpd.setEndDate("23.06.2018");
			couponUpd.setAmount(100);
			couponUpd.setPrice(12.5f);
			couponUpd.setType(CouponType.CAMPING);
			couponUpd.setMessage("phpmyadmin");
			couponUpd.setImage("IMAGE");
			couponUpd.setCompanyId(7);
		//	couponsDao.couponCreate(couponUpd);
	    	couponUpd.setId(3);
	    	couponsDao.couponUpdate(couponUpd);
	    	
	    	//	    	________________________________
		    
			try {
       
//            List<Customer> customerList = customerDao.getAllCustomers();
//            System.out.println("Customers list:");
//            for (Customer c : customerList) {
//                System.out.println(c);
//            }
//           
//            List<Company> companyList = companyDao.getAllCompanies();
//            System.out.println("Company List:");
//            for (Company comp : companyList) {
//                System.out.println(comp);
//            }
//           
//            List<Coupon> couponList = couponsDao.getAllCoupons();
//            //Thread.sleep(10000);
//	        System.out.println("Coupons list:");
//            for (Coupon co : couponList) {   	
//                System.out.println(co);
//            }
//Get All by Company:
//				List<Coupon> couponList = couponsDao.couponGetByCompany(3);
//		        System.out.println("Coupons list:");
//	            for (Coupon co : couponList) {   	
//	                System.out.println(co);
//	            }
//Get Coupon by TYPE:	
//	            List<Coupon> couponList = couponsDao.couponGetByType(CouponType.TRAVELLING);
//		        System.out.println("Coupons list:");
//	            for (Coupon co : couponList) {   	
//	                System.out.println(co);
//	            }
//Get Coupon by Customer:	
//	            List<Coupon> couponList = couponsDao.couponGetByCustomer(1);
//		        System.out.println("Coupons list:");
//	            for (Coupon co : couponList) {   	
//	                System.out.println(co);
//	            }
//				________________________________
// DELETE:      
//           Coupon coupon = new Coupon();
//           coupon.setId(3);
//           couponsDao.couponDeleteById(coupon);
			
//			 Company company = new Company();
//			 company.setId(11);
//			 companyDao.companyDeleteById(company);
			
//			 Customer customer = new Customer();
//			 customer.setId(9);
//			 customerDao.customerDeleteById(customer);
// Get By ID:
//           Coupon coupon = couponsDao.couponGetById((long)4);
//           System.out.println(coupon);
			 
				

            
        } catch (Exception e) {
            e.printStackTrace();
        }

}
	    
}
