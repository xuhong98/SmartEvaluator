/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedBean;

import ejb.session.stateless.HomeBuyerControllerLocal;
import entity.HomeBuyer;
import entity.House;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author hongxu
 */     

@ManagedBean
@SessionScoped
public class HomeBuyerManagedBean implements Serializable{

    @EJB(name = "HomeBuyerControllerLocal")
    private HomeBuyerControllerLocal homeBuyerControllerLocal;

    private HomeBuyer homeBuyer;
    
    private List<House> houseList;
    
    public HomeBuyerManagedBean() {
        homeBuyer = new HomeBuyer();
    }

    public List<House> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<House> houseList) {
        this.houseList = houseList;
    }

    public HomeBuyer getHomeBuyer() {
        return homeBuyer;
    }

    public void setHomeBuyer(HomeBuyer homeBuyer) {
        this.homeBuyer = homeBuyer;
    }
    
    public void exploreHouse(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("name", homeBuyer.getName());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("age", homeBuyer.getAge());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("savings", homeBuyer.getSavings());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("income", homeBuyer.getMonthlyIncome());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("expense", homeBuyer.getMonthlyExpense());
        System.out.println("homeBuyer name: " + homeBuyer.getName());
        System.out.println("homeBuyer savings: " + homeBuyer.getSavings());
        houseList = homeBuyerControllerLocal.getHouseList(homeBuyer);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("houseList", houseList);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("houseSuggest.xhtml");
    }
}