import java.util.*;

public abstract class Subject {

   //Subject Pattern
   private List<Observer> ob = new ArrayList<Observer>();

   public void attach(Observer observer){
      ob.add(observer);
   }
   
   public void updateF(String message){
      for(Observer observer : ob){
         observer.update(this, message);
      }
   }
}
