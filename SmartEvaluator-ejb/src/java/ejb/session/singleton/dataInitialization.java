/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.HomeBuyerControllerLocal;
import entity.Admin;
import entity.HomeBuyer;
import entity.House;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import jxl.Workbook;
import jxl.Sheet;
/**
 *
 * @author xuhong
 */
@Singleton
@LocalBean
@Startup
public class dataInitialization {
    @EJB(name = "HomeBuyerControllerLocal")
    private HomeBuyerControllerLocal homeBuyerControllerLocal;
//    @PersistenceContext(unitName = "SmartEvaluator-ejbPU")
//    private EntityManager em;
    public static ArrayList<House> houseList=new ArrayList<>();
    public static ArrayList<HomeBuyer> houseBuyerInfo = new ArrayList<HomeBuyer>();
    public static ArrayList<Double> queryTimeList = new ArrayList<>();
    public static Admin admin = new Admin("admin", "password");        
    
    public dataInitialization() {
        
    }

    @PostConstruct
    public void postConstruct() {
        System.out.print("Start!");
        
//        if (em.find(House.class, 1l) == null) {
//            System.out.print("HAHA");
//            loadHouseData();
//        }
        loadHouseData();
    }
    
    private void loadHouseData(){
        System.out.print("Start handling data");
        File file=new File("/Users/hongxu/Desktop/SmartEvaluator/housingInfo.xls");
        try{
            InputStream stream=new FileInputStream(file.getAbsolutePath());
            Workbook wb=Workbook.getWorkbook(stream);
            Sheet sheet=wb.getSheet(0);
            for (int i=0;i<sheet.getRows();i++){
                 
                String houseName=sheet.getCell(0, i).getContents();
                String Address=sheet.getCell(1,i).getContents();
                Long Price=Long.parseLong(sheet.getCell(2,i).getContents());
                Long unitPrice=(long)Float.parseFloat(sheet.getCell(3,i).getContents());
                Long area=Long.parseLong(sheet.getCell(4,i).getContents());
                String link = sheet.getCell(5,i).getContents();
                Long longitute=new Long("0");
                Long latitute=new Long("0");
                //System.out.print(houseName);
                House house=new House(Address,houseName,link,Price,unitPrice,longitute,latitute,area);
                houseList.add(house);
            }
            System.out.print("Finished!!!!!");
        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(houseList);
        System.out.print("Finished!");
        System.out.print(houseList.get(0).getAddress());
    }

//    public void persist(Object object) {
//        em.persist(object);
//    }
//    
    
  

    
}
