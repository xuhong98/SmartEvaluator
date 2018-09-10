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
/**
 *
 * @author hongxu
 */
@Stateless
public class HomeBuyerController implements HomeBuyerControllerLocal {
    ArrayList <House> returnHouseList;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public ArrayList <House> getHouseList(HomeBuyer buyer){
        returnHouseList =new ArrayList<>();
        
        System.out.print(dataInitialization.houseList.get(0).getAddress());
        
        for(int house = 0; house < dataInitialization.houseList.size(); house++){
            if(buyer.getSavings()-dataInitialization.houseList.get(house).getTotalPrice()>0
               && buyer.getSavings()-dataInitialization.houseList.get(house).getTotalPrice()<100000){
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
        System.out.print(returnHouseList.size());
        for(int h = 0;h<returnHouseList.size();h++){
            System.out.print("1111111111111"+returnHouseList.get(h).getHouseName());
        }
        return returnHouseList;
    }
   
}
