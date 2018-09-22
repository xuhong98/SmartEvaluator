/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import ejb.session.singleton.dataInitialization;
import javax.ejb.Stateless;
import uitl.exceptions.InvalidLoginCredentialException;

/**
 *
 * @author hongxu
 */
@Stateless
public class AdminController implements AdminControllerLocal {

    @Override
    public Boolean login(String username, String password) throws InvalidLoginCredentialException {
        if ((dataInitialization.admin.getUsername().equals(username)) && (dataInitialization.admin.getPassword().equals(password))) {
            return true;
        } else {
            throw new InvalidLoginCredentialException("Invalid password!");
        }
    }

    @Override
    public double getQueryTime() {
        return dataInitialization.queryTimeList.get(dataInitialization.queryTimeList.lastIndexOf(this));
    }
}
