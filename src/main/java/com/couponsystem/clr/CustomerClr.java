//package com.couponsystem.clr;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.couponsystem.beans.LoginForm;
//import com.couponsystem.enums.ClientType;
//import com.couponsystem.enums.CouponCategory;
//import com.couponsystem.rest.CustomerController;
//import com.couponsystem.security.LoginController;
//import com.couponsystem.service.LoginService;
//import com.couponsystem.utils.ClrUtils;
//
//@Component
//@Order(6)
//public class CustomerClr implements CommandLineRunner {
//
//	
//	private final CustomerController customerController;
//	private LoginService loginService;
//	private final LoginController loginController;
//
//	@Autowired
//	public CustomerClr(LoginController loginController, LoginService loginService, CustomerController customerController) {
//		super();
//		this.loginController = loginController;
//		this.loginService = loginService;
//		this.customerController = customerController;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
////    _  
////  _( )_
//// (_ o _)
////  (_,_)
////       _____           _                             ______          _     _____         _       
////      /  __ \         | |                            | ___ \        | |   |_   _|       | |      
////      | /  \/_   _ ___| |_ ___  _ __ ___   ___ _ __  | |_/ /___  ___| |_    | | ___  ___| |_ ___ 
////      | |   | | | / __| __/ _ \| '_ ` _ \ / _ \ '__| |    // _ \/ __| __|   | |/ _ \/ __| __/ __|
////      | \__/\ |_| \__ \ || (_) | | | | | |  __/ |    | |\ \  __/\__ \ |_    | |  __/\__ \ |_\__ \
////       \____/\__,_|___/\__\___/|_| |_| |_|\___|_|    \_| \_\___||___/\__|   \_/\___||___/\__|___/
//
//		ClrUtils.customerRestTests();
//
////		------------------------------------------------------------------------------------------------------------
//
//		ClrUtils.testSeparatedLine(" --------->>>>>>>> Testing Company Login:");
//
//		System.out.println("Going to test login exception - *WRONG* *Email*:");
//		LoginForm badEmailLoginForm = new LoginForm("BADcust@cust.com", "1111", ClientType.CUSTOMER);
//		System.out.println(loginController.login(badEmailLoginForm));
//
//		System.out.println();
//		System.out.println("Going to test login exception - *WRONG* *Password*:");
//		LoginForm badPasswordLoginForm = new LoginForm("cust1@cust.com", "1010", ClientType.CUSTOMER);
//		System.out.println(loginController.login(badPasswordLoginForm));
//
//
//		System.out.println();
//		System.out.println("Going to test GOOD customer login:");
//		LoginForm goodLoginForm = new LoginForm("cust1@cust.com", "1111", ClientType.CUSTOMER);
//		System.out.println(loginController.login(goodLoginForm));
//		String token = loginService.getTokenForClr();
//
////		------------------------------------------------------------------------------------------------------------
//
//		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test customerController.purchaseCoupon:");
//
//		System.out.println(customerController.purchaseCoupon(2, token));
//		System.out.println(customerController.purchaseCoupon(4, token));
//		System.out.println(customerController.purchaseCoupon(6, token));
//
//		ClrUtils.testSeparatedLine(
//				" --------->>>>>>>> Going to test *BAD REQUEST* for customerController.purchaseCoupon: (customer can't purchase the same coupon more then once)");
//
//		System.out.println(customerController.purchaseCoupon(2, token));
//
//		ClrUtils.testSeparatedLine(
//				" --------->>>>>>>> Going to test *BAD REQUEST* for customerController.purchaseCoupon: (customer can't purchase the coupon if amount<0)");
//
//		System.out.println(customerController.purchaseCoupon(1, token));
//
//		ClrUtils.testSeparatedLine(
//				" --------->>>>>>>> Going to test *BAD REQUEST* for customerController.purchaseCoupon: (customer can't purchase the coupon if the coupon has expired)");
//
//		System.out.println(customerController.purchaseCoupon(7, token));
//
//		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test customerController.getAllCustomerCoupons:");
//
//		System.out.println(customerController.getAllCustomerCoupons(token));
//
//		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test customerController.getAllCouponsByCategory:");
//
//		System.out.println(customerController.getAllCouponsByCategory(CouponCategory.RESTAURANT, token));
//
//		ClrUtils.testSeparatedLine(
//				" --------->>>>>>>> Going to test *BAD REQUEST* for customerController.getAllCouponsByCategory: (coupons from category type not found)");
//
//		System.out.println(customerController.getAllCouponsByCategory(CouponCategory.ELECTRICITY, token));
//
//		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test customerController.getAllCouponsUnderMaxPrice:");
//
//		System.out.println(customerController.getAllCouponsUnderMaxPrice(400, token));
//
//		ClrUtils.testSeparatedLine(
//				" --------->>>>>>>> Going to test *BAD REQUEST* for customerController.getAllCouponsUnderMaxPrice: (coupons under maxPrice not found)");
//
//		System.out.println(customerController.getAllCouponsUnderMaxPrice(22, token));
//
//		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test customerController.getCustomerDetails:");
//
//		System.out.println(customerController.getCustomerDetails(token));
//	}
//}
//