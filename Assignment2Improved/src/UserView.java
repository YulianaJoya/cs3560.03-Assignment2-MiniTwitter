import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;




public class UserView extends JFrame implements Composite{
   private ArrayList<User> userL = ControlPanel.getUsers();
   private JButton followUser, postTweet;
   private JTextArea userId, tweetMessage, afterTweet, followedUser;
   private int index = 0;
   private User u;

   public UserView(User u){
      this.u = u;
      for(int i = 0; i < userL.size(); i++){
         if(userL.get(i).getId().equals(u.getId()));
         index = i;
         break;

      }
   }

   //Implements composite pattern
   public void display(){
      setVisible(true);
      start();
   }

   public void start(){


      this.setSize(700, 700);
      this.setTitle("User");
      this.setLayout(null);

      //Button to follow users
      followUser = new JButton("Follow User");
      followUser.setBounds(350, 20, 320, 80);
      followUser.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            followedUser.setText(followedUser.getText() + "\n -" + userId.getText());
         }
      });
      this.add(followUser);
      
      //Button to post a tweet
      postTweet = new JButton("Post Tweet");
      postTweet.setBounds(350, 350, 320, 80);
      postTweet.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            afterTweet.setText(afterTweet.getText() + "  "  + "\n  " + u.getId() + "-" + tweetMessage.getText());
            u.getMes(tweetMessage.getText());
         }
      });
      this.add(postTweet);

      //Text area to enter user to follow
      userId = new JTextArea();
      userId.setBounds(20, 20, 320, 80);
      this.add(userId);

      //Text area to write tweet
      tweetMessage = new JTextArea();
      tweetMessage.setBounds(20, 350, 320, 80);
      this.add(tweetMessage);

      afterTweet = new JTextArea();
      afterTweet.setText("News Feed");
      afterTweet.setBounds(20, 450, 560, 100);
      this.add(afterTweet);

      followedUser = new JTextArea();
      followedUser.setText("Following");
      followedUser.setBounds(20, 120, 560, 100);
      this.add(followedUser);

   }
}
