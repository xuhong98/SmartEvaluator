/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedBean;

import ejb.session.stateless.AdminControllerLocal;
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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import uitl.exceptions.InvalidLoginCredentialException;

/**
 *
 * @author hongxu
 */     

@ManagedBean
@SessionScoped
public class HomeBuyerManagedBean implements Serializable{

    @EJB(name = "AdminControllerLocal")
    private AdminControllerLocal adminControllerLocal;

    @EJB(name = "HomeBuyerControllerLocal")
    private HomeBuyerControllerLocal homeBuyerControllerLocal;

    private HomeBuyer homeBuyer;
    
    private List<House> houseList;
    
    private String adminUsername;
    
    private String adminPassword;
    
    private String married;
    
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

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    
    public void adminLogin(ActionEvent event) throws IOException{
        try {
            Boolean flag = adminControllerLocal.login(adminUsername, adminPassword);
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("adminPage.xhtml");
        } catch (InvalidLoginCredentialException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }
    
    public void exploreHouse(ActionEvent event) throws IOException {
        if(married.equals("true")){
            homeBuyer.setMarried(true);
        }else{
            homeBuyer.setMarried(false);
        }
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("name", homeBuyer.getName());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("age", homeBuyer.getAge());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("savings", homeBuyer.getSavings());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("income", homeBuyer.getMonthlyIncome());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("expense", homeBuyer.getMonthlyExpense());
        System.out.println("homeBuyer name: " + homeBuyer.getName());
        System.out.println("homeBuyer savings: " + homeBuyer.getSavings());
        System.out.println("homeBuyer year: " + homeBuyer.getLoanRepaymentYear());
        try{
            houseList = homeBuyerControllerLocal.getHouseList(homeBuyer);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("houseList", houseList);
            FacesContext.getCurrentInstance().getExternalContext().redirect("houseSuggest.xhtml");
        }catch(Exception ex){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Repayment years of loan cannot be larger than 65-age. Please choose again."));
        }
    }
}