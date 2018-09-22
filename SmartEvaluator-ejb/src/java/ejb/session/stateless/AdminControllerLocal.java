/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import javax.ejb.Local;
import uitl.exceptions.InvalidLoginCredentialException;

/**
 *
 * @author hongxu
 */
@Local
public interface AdminControllerLocal {
    public Boolean login(String username, String password) throws InvalidLoginCredentialException;
}
