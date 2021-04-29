/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import packageentity.inscription;
import java.util.List;

/**
 *
 * @author ali
 */
public interface IinscripService {
    
         public void createEvenement(inscription e);

    public List<inscription> getAll();

    public void update(inscription e,int id);

    public void delete(int id);
    
}
