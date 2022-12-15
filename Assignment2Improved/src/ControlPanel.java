import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ControlPanel extends JFrame implements Composite{
   private static ControlPanel instance = new ControlPanel();

   private JPanel panel1;
   private JButton addGroup, addUser, totalUser, totalGroup, showPositive, showMessage, openView, idValid, updatedUser;
   private JTextArea uname;
   private JTextArea gname;
   private JTree coconutTree = new JTree();

   private User currentUser;
   private ArrayList<DefaultMutableTreeNode> use;
   private static ArrayList<User> users = new ArrayList<User>();
   private static ArrayList<String> username = new ArrayList<String>();
   private ArrayList<String> groupname = new ArrayList<String>();
   private ArrayList<UserView> v;
   

   private int  totalMessage = 0;
   private int percentagePos = 0;
   private int posTotal = 0;

   //Implements singleton pattern
   public static ControlPanel getInstance(){
      if(instance == null){
         instance = new ControlPanel();
      }
      return instance;
   }

   public static ArrayList<User> getUsers(){
      return (ArrayList<User>) users;
   }

   public static ArrayList<String> getUsername(){
      return (ArrayList<String>) username;
   }

   //Implements composite pattern
   @Override
   public void display()
   {
      setVisible(true);
      initComponents();
      
   }
   
   private void initComponents(){
      this.setSize(700, 700);
      this.setTitle("Mini-Twitter (0)<");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(null);
      Border border = BorderFactory.createLineBorder(new Color(0x2E8BC0), 2);

      coconutTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Root")));
      coconutTree.setBounds(20, 20, 270, 620);
      this.add(coconutTree);


      uname = new JTextArea();
      uname.setBounds(310, 20, 170, 80);
      this.add(uname);

      gname = new JTextArea();
      gname.setBounds(310, 120, 170, 80);
      this.add(gname);

      idValid = new JButton("Id Validation");
      idValid.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(null, "Invalid Id: ", "", 1, null);
         }
      });
      idValid.setBounds(310, 320, 170, 80);
      this.add(idValid);

      updatedUser = new JButton("Last Updated User");
      updatedUser.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(null, "Last Updated User: ", "", 1, null);
         }
      });
      updatedUser.setBounds(500, 320, 170, 80);
      this.add(updatedUser);

      //Button to add a user
      addUser = new JButton("Add User");
      addUser.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            User newUser = new User(uname.getText(), System.currentTimeMillis());
            users.add(newUser);
            DefaultTreeModel model = (DefaultTreeModel) coconutTree.getModel();
            DefaultMutableTreeNode sNode = (DefaultMutableTreeNode) coconutTree.getLastSelectedPathComponent();
            DefaultMutableTreeNode anotherOne = new DefaultMutableTreeNode(uname.getText());

            if(sNode != null){
               if(!uname.getText().trim().equals("")){
                  model.insertNodeInto(anotherOne, sNode, sNode.getChildCount());
                  model.reload();
               }
            }
            else{
               sNode = (DefaultMutableTreeNode) model.getRoot();
               if(!uname.getText().trim().equals("")){
                  model.insertNodeInto(anotherOne, sNode, sNode.getChildCount());
                  model.reload();
               }
            }
            username.add(uname.getText());
         }
      });
      addUser.setBounds(500, 20, 170, 80);
      this.add(addUser);

      //Button to add a group
      addGroup = new JButton("Add Group");
      addGroup.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            DefaultTreeModel model = (DefaultTreeModel) coconutTree.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            model.insertNodeInto(new DefaultMutableTreeNode(gname.getText()), root, root.getChildCount());
            groupname.add(gname.getText());
         }
      });
      addGroup.setBounds(500, 120, 170, 80);
      this.add(addGroup);

      //Button to open user window
      openView = new JButton("Open User View");
      openView.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            DefaultMutableTreeNode sNode = (DefaultMutableTreeNode) coconutTree.getLastSelectedPathComponent();
            String name = (String) sNode.getUserObject();
            User thisUser = new User(name, System.currentTimeMillis());
            UserView selected = new UserView(thisUser);
            selected.display();
         }
      });
      openView.setBounds(310, 220, 360, 80);
      this.add(openView);

      //Shows total users
      totalUser = new JButton("Show User Total");
      totalUser.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            String text = AdminPanel.allUsers(username);
            JOptionPane.showMessageDialog(null, text, "", 1, null);
         }
      });
      totalUser.setBounds(310, 460, 170, 80);
      this.add(totalUser);

      //Shows total groups
      totalGroup = new JButton("Show Group Total");
      totalGroup.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            String text = AdminPanel.allGroups(groupname);
            JOptionPane.showMessageDialog(null, text, "", 1, null);
         }
      });
      totalGroup.setBounds(500, 460, 170, 80);
      this.add(totalGroup);

      //Shows total messages published
      showMessage = new JButton("Show Messages Total");
      showMessage.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            totalMessage = AdminPanel.totalMessages(username, users, totalMessage);
            JOptionPane.showMessageDialog(null, "Total Message: ", "", 1, null);
            totalMessage = 0;
         }
      });
      showMessage.setBounds(310, 560, 170, 80);
      this.add(showMessage);

      //Shows percentage of positive messages
      showPositive = new JButton("Show Positive Percentage");
      showPositive.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent per){
            percentagePos = AdminPanel.totalPositive(username, users, totalMessage, posTotal, percentagePos);
            JOptionPane.showMessageDialog(null, "Positive Percentage messages: " + percentagePos * 100 + "%", "", 1, null);
            totalMessage = 0;
            posTotal = 0;
         }
      });
      showPositive.setBounds(500, 560, 170, 80);
      this.add(showPositive);
   }
  
}
