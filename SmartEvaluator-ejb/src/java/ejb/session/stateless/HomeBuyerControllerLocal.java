/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import javax.ejb.Local;
import entity.House;
import java.util.ArrayList;
import entity.HomeBuyer;

/**
 *
 * @author hongxu
 */
@Local
public interface HomeBuyerControllerLocal {
    public ArrayList <House> getHouseList(HomeBuyer buyer);
}
