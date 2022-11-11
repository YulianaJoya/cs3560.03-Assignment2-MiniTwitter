import java.util.*;

public class AdminPanel {
   
   //Shows total groups
   public static String allGroups(ArrayList<String> gNames){
      String txt = "Total Groups: " + gNames.size();
      return txt;
   }

   //Shows total users
   public static String allUsers(ArrayList<String> uNames){
      String txt = "Total Users: " + uNames.size();
      return txt;
   }
   
   //Shows total messages posted
   public static int totalMessages(ArrayList<String> usNames, ArrayList<User> users, int tMessage){
      MessageVisitor reg = new MessageVisitor();
      for(int i = 0; i < usNames.size(); i++){
         users.get(i).accept(reg);
         tMessage += reg.regMessage.size();
      }

      return tMessage;
   }

   //Shows total percentage of positive messages
   public static int totalPositive(ArrayList<String> usNames, ArrayList<User> users, int tMessage, int posTxt, int percent){
      PositiveMessageVisitor pos = new PositiveMessageVisitor();
      MessageVisitor reg = new MessageVisitor();
      
      for(int i = 0; i < usNames.size(); i++){
         users.get(i).accept(pos);
         users.get(i).accept(reg);
         tMessage += reg.regMessage.size();
         posTxt += pos.posMessage.size();
      }
      if(posTxt != 0 && tMessage != 0){
         percent = posTxt / tMessage;
      }

      return percent;
   }
}
