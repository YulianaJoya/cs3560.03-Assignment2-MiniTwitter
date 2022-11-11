import java.util.*;

public class PositiveMessageVisitor implements Visitor {
   protected ArrayList<String> posMessage = new ArrayList<String>();
   private static String[] positive = {"good", "great", "excellent", "brilliant", "perfect", "happy"};

   //Implements visitor pattern
   @Override
   public void addUser(User us)
   {
      posMessage = us.getPositiveMessages();
   }

   //Checks if a message is positive
   public static boolean checkPos(String text){
      for(int i = 0; i < positive.length; i++){
         if(text.contains(positive[i])){
            return true;
         }
         else{
            return false;
         }
      }
      return false;
   }
   
}
