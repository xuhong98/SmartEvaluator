/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import java.util.*;
import javax.ejb.Stateless;
import entity.HomeBuyer;
import entity.House;        
import java.util.ArrayList;
import ejb.session.singleton.dataInitialization;
import static ejb.session.singleton.dataInitialization.houseList;


@Stateless
public class HomeBuyerController implements HomeBuyerControllerLocal {
    ArrayList <House> returnHouseList;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public ArrayList <House> getHouseList(HomeBuyer buyer) throws Exception{
        returnHouseList =new ArrayList<>();
        
        double monthlyIncome = buyer.getMonthlyIncome()-buyer.getMonthlyExpense();
        
        int repaymentYear = buyer.getLoanRepaymentYear();
        int age=buyer.getAge();
        System.out.println("---------age " + age);
        System.out.println("---------year " + repaymentYear);
        if ((repaymentYear != 0) && (age+repaymentYear>=65)){
            throw new Exception("Sum of age and repaymentYear out of limit!");
        }
        double firstYearPayment =  monthlyIncome * (0.56 - 0.0041*(repaymentYear-5));
	    
	   // System.out.println("fyp: "+firstYearPayment);
	    
	double lastYearPayment = monthlyIncome * (0.57 - 0.002*(repaymentYear-5));
	    
	   // System.out.println("lyp: "+lastYearPayment);
	    
	double middleYearPayment = (firstYearPayment + lastYearPayment)/2;
	    
	   // System.out.println("myp: "+middleYearPayment);

	double totalInterest = 0;
	    
	    for(int i=0;i<repaymentYear;i++) {
	    	if(i==0) {
	    		totalInterest+=0.015;
	    	}
	    	else if(i==1) {
	    		totalInterest+=0.015*(repaymentYear-1)/repaymentYear;
	    	}
	    	else if(i==2) {
	    		totalInterest+=0.02*(repaymentYear-2)/repaymentYear;
	    	}
	    	else if(i>=3) {
	    		totalInterest+=0.025*(repaymentYear-i)/repaymentYear;
	    	}
	    }
	    
	   // System.out.println("ti: "+totalInterest);
	    
	double totalPayment = firstYearPayment*24+middleYearPayment*12+lastYearPayment*12*(repaymentYear-3);
	   
	double totalPrincipalPayment = totalPayment/(1+totalInterest);
	    
	double totalLoan = totalPrincipalPayment+buyer.getSavings();

	double totalPayable = buyer.getSavings()*0.8;    
        
        //System.out.print(dataInitialization.houseList.get(0).getAddress());
        
        for(int house = 0; house < dataInitialization.houseList.size(); house++){
            if(totalPayable-dataInitialization.houseList.get(house).getTotalPrice()>0
               && totalPayable-dataInitialization.houseList.get(house).getTotalPrice()<500000){
               returnHouseList.add(dataInitialization.houseList.get(house));
            }
        }
        
        //get info from buyer
        //logic function
        //get money
        //loop over houselist in datainit class
        //get house
//        System.out.print(dataInitialization.houseList);
        //find proper house
        return returnHouseList;
    }
   
}
