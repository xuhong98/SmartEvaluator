/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedBean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author hongxu
 */
@Named(value = "adminPageManagedBean")
@SessionScoped
public class AdminPageManagedBean implements Serializable {

    /**
     * Creates a new instance of AdminPageManagedBean
     */
    public AdminPageManagedBean() {
    }
    
}
