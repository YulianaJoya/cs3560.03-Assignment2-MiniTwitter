import java.util.*;

public class User extends Subject implements Observer{
   private String id;
   private long creationTime;
   private long lastUpdate;
   private ArrayList<String> messages;
   private ArrayList<String> positiveMessages;
   private ArrayList<String> newsFeed = new ArrayList<>(Arrays.asList());
   private ArrayList<User> followers;
   private ArrayList<User> following;
   private ArrayList<User> u;

   User(String id, long creationTime){
       this.id = id;
       followers = new ArrayList<User>();
       following = new ArrayList<User>();
       messages = new ArrayList<String>();
       u = new ArrayList<User>();
   }

   public long getCreationTime(){
      return creationTime;
   }

   public long getLastUpdate(){
      return lastUpdate;
   }

   public void setUpdateTime(){
      lastUpdate = System.currentTimeMillis();
   }
   public void setId(String id){
       this.id = id;
   }

   public String getId(){
       return id;
   }

   public void Followed(User id){
      following.add(id);
      id.attach(this);
   }

   public void setU(User us){
      u.add(us);
   }

   public void getFollowed(User id){
      followers.add(id);
   }
   

   public ArrayList<User> getFollowing(){
      return following;
   }

   public ArrayList<String> getMessages(){
       return messages;
   }

   public ArrayList<String> getPositiveMessages(){
       return positiveMessages;
   }

   public void getMes(String m){
      messages.add(m);
   }

   //Implements visitor pattern
   public void accept(Visitor vis){
      vis.addUser(this);
   }

   public void tweet(String text){
      messages.add(text);
      newsFeed.add(this.id + " - " + text);
      updateF(text);
   }

   //Implements observer pattern
   @Override
   public void update(Subject subject, String text){
       messages.add(0, ((User)subject).getId() + " - " + text);
       
   }

   
}
