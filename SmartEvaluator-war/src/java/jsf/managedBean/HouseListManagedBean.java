/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedBean;

import ejb.session.singleton.dataInitialization;
import ejb.session.stateless.AdminControllerLocal;
import entity.House;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hongxu
 */
@ManagedBean
@ViewScoped
public class HouseListManagedBean implements Serializable{

    @EJB(name = "AdminControllerLocal")
    private AdminControllerLocal adminControllerLocal;

    private List<House> houseList;
    FacesContext context;
    HttpSession session;
    //private double queryTime;
    
    public HouseListManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        //queryTime = adminControllerLocal.getQueryTime();
        context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(true);
        houseList = (List<House>) session.getAttribute("houseList");
        System.out.println(houseList.size()+"   HOUSE LIST IS THIS BIG!");
    }
    
    public List<House> getHouseList() {

        return houseList;
    }

    public void setHouseList(List<House> houseList) {
        this.houseList = houseList;
    }
//
//    public double getQueryTime() {
//        return queryTime;
//    }
//
//    public void setQueryTime(double queryTime) {
//        this.queryTime = queryTime;
//    }
}
