
package ec.edu.espe.model;

import java.util.Date;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class Penalty {
private Date date;
private float amount;
  
public Penalty(Date date,float amount){
    this.date=date;
    this.amount=amount;
}
public float getAmount(){
    return amount;
}
public String getDetails(){
    return date.toString()+"Multa de "+amount;
}
}
