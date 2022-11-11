import java.util.*;

public class MessageVisitor implements Visitor {
   ArrayList<String> regMessage = new ArrayList<String>();

   //Implements visitor pattern
   @Override
   public void addUser(User us)
   {
      regMessage = us.getMessages();
   }

}
