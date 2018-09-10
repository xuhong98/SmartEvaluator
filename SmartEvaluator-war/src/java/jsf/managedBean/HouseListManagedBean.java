/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedBean;

import entity.House;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hongxu
 */
@ManagedBean
@RequestScoped
public class HouseListManagedBean implements Serializable{

    public List<House> houseList;
    FacesContext context;
    HttpSession session;
    
    public HouseListManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(true);
        houseList = (List<House>) session.getAttribute("houseList");
    }
    
    public List<House> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<House> houseList) {
        this.houseList = houseList;
    }
    
    
}
