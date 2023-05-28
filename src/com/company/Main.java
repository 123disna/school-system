package com.company;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

import static java.lang.String.valueOf;

public class Main {

    public static void main(String[] args) {
           new smload();
           new login();
    }
}
//load page
class smload extends JFrame{
      JProgressBar load_bar=new JProgressBar();
      Border brd=BorderFactory.createLineBorder(Color.RED,2);

      //create constructor
      smload(){
          JLabel load_label=new JLabel();
          load_label.setIcon(new ImageIcon("C:\\Users\\Trio\\IdeaProjects\\sm system\\schoolload.png"));
          add(load_label);

          load_bar.setValue(0);
          load_bar.setStringPainted(true);
          load_bar.setBackground(Color.BLACK);
          load_bar.setForeground(Color.RED);
          load_bar.setBorder(brd);
          load_bar.setFont(new Font("Arial",Font.ITALIC,25));
          load_bar.setBounds(200,600,800,30);
          load_label.add(load_bar);


          setSize(1200,700);
          setResizable(false);
          setDefaultCloseOperation(EXIT_ON_CLOSE);
          setLocationRelativeTo(null);
          setVisible(true);
          fills();
          dispose();
      }
      //end of constructor

      public void fills(){
            int count=0;
            while(count<=100) {
                load_bar.setValue(count);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
                count++;
            }
            load_bar.setString("Done!");
      }
}

//login page
class login extends JFrame {
    JPanel back = new JPanel();
    JLabel login_label, user, password, error;
    JTextField txt_user;
    JPasswordField txt_pass;
    JButton cancel, login;

    Border brd = BorderFactory.createLineBorder(Color.RED, 2);

    login() {
        back.setBackground(Color.YELLOW);// background color


        login_label = new JLabel("Login");
        login_label.setForeground(Color.RED);
        login_label.setBounds(500, 10, 300, 80);
        login_label.setFont(new Font("Arial", Font.BOLD, 70));
        add(login_label);

        user = new JLabel("Username");
        user.setForeground(Color.black);
        user.setBounds(200, 200, 300, 60);
        user.setFont(new Font("Arial", Font.BOLD, 50));
        add(user);

        txt_user = new JTextField();
        txt_user.setBounds(600, 200, 500, 50);
        txt_user.setForeground(Color.RED);
        txt_user.setFont(new Font("Arial", Font.BOLD, 40));
        txt_user.setBackground(Color.black);
        txt_user.setBorder(brd);
        add(txt_user);

        password = new JLabel("Password");
        password.setForeground(Color.black);
        password.setBounds(200, 350, 300, 60);
        password.setFont(new Font("Arial", Font.BOLD, 50));
        add(password);

        txt_pass = new JPasswordField();
        txt_pass.setBounds(600, 350, 500, 50);
        txt_pass.setForeground(Color.RED);
        txt_pass.setFont(new Font("Arial", Font.BOLD, 40));
        txt_pass.setBackground(Color.black);
        txt_pass.setBorder(brd);
        add(txt_pass);

        error = new JLabel("Invalid username or password.");//database connect kalama danna
        error.setBounds(600, 450, 500, 60);
        error.setForeground(Color.RED);
        error.setFont(new Font("Arial", Font.BOLD, 30));
        error.setVisible(false);
        add(error);

        login = new JButton("Login");
        login.setBounds(500, 550, 150, 50);
        login.setBackground(Color.RED);
        login.setForeground(Color.white);
        login.setFont(new Font("Arial", Font.BOLD, 30));
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(800, 550, 150, 50);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Arial", Font.BOLD, 30));
        add(cancel);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                enter();
            }
        });

        add(back);
        setSize(1200, 700);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
//end of constructor

    void enter() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String qu="select * from admin where username=? and password=?";
            PreparedStatement prp= (PreparedStatement) con.prepareStatement(qu);
            prp.setString(1,txt_user.getText());
            prp.setString(2,txt_pass.getText());
            ResultSet rs=prp.executeQuery();
            if(rs.next()==true) {
                    dispose();
                    new teams();
                } else {
                    error.setVisible(true);
                    txt_pass.setText(null);
                    txt_user.setText(" ");
                }
            //con.close();
        }catch (ClassNotFoundException | SQLException en){
            System.err.println("Got an exception !");
            System.err.println(en.getMessage());
        }
    }
}
//menu page
class teams extends JFrame{
    JButton principal,children,teacher,logout;
    JLabel scl;
    JPanel back=new JPanel();
    Border brd = BorderFactory.createLineBorder(Color.black, 5);
    teams() {
        back.setBackground(Color.YELLOW);//for panel
        scl=new JLabel("SCHOOL");
        scl.setBounds(450,20,300,80);
        scl.setFont(new Font("Arial",Font.BOLD,70));
        scl.setForeground(Color.RED);
        add(scl);

        principal=new JButton();
        principal.setBounds(200,200,200,200);
        principal.setBorder(brd);
        principal.setBackground(Color.yellow);
        principal.setIcon(new ImageIcon("C:\\Users\\Trio\\IdeaProjects\\sm system\\principal.png"));
        add(principal);

        children=new JButton();
        children.setBounds(500,200,200,200);
        children.setBackground(Color.yellow);
        children.setBorder(brd);
        children.setIcon(new ImageIcon("C:\\Users\\Trio\\IdeaProjects\\sm system\\stds.png"));
        add(children);

        teacher=new JButton();
        teacher.setBounds(800,200,200,200);
        teacher.setBackground(Color.yellow);
        teacher.setBorder(brd);
        teacher.setIcon(new ImageIcon("C:\\Users\\Trio\\IdeaProjects\\sm system\\teach.png"));
        add(teacher);

        logout=new JButton("Log out");
        logout.setBounds(1000,600,100,40);
        logout.setBackground(Color.RED);
        logout.setForeground(Color.white);
        logout.setBorder(brd);
        logout.setFont(new Font("Arial",Font.BOLD,20));
        add(logout);

    principal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  dispose();
                  new principalPage();
            }
        });
       children.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new stdRecords();
            }
        });
        teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new teacherRecords();
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(back);
        setSize(1200, 700);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
//principal page
class principalPage extends JFrame{
           JPanel back=new JPanel();
           JLabel id,name,address,birth,age,gender,contact,princ,image;
           JTextField id_txt,name_txt,address_txt,birth_txt,age_txt,contact_txt;
           JComboBox gender_com,dur_com;
           JButton add_btn,search_btn,delete_btn,update_btn,clear_btn,menu_btn,selectImage_btn;
           Font font=new Font("Arial",Font.BOLD,30);
           Font f=new Font("Arial",Font.BOLD,20);
           Font btn_font=new Font("Arial",Font.BOLD,20);
           Border brd = BorderFactory.createLineBorder(Color.red, 2);
           byte []photo =null;
           String fileName=null;
           principalPage(){
               back.setBackground(Color.YELLOW);

               princ=new JLabel("PRINCIPAL");
               princ.setBounds(500,20,300,70);
               princ.setFont(new Font("Arial",Font.BOLD,50));
               princ.setForeground(Color.RED);
               add(princ);

               image=new JLabel();
               image.setBounds(550,100,150,150);
               image.setBorder(brd);
               add(image);

               selectImage_btn=new JButton("SelectImage");
               selectImage_btn.setBounds(550,260,150,30);
               selectImage_btn.setFont(new Font("Arial",Font.BOLD,20));
               selectImage_btn.setForeground(Color.white);
               selectImage_btn.setBackground(Color.BLUE);
               selectImage_btn.setBorder(brd);
               add(selectImage_btn);

               selectImage_btn.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       JFileChooser chooser = new JFileChooser();
                       chooser.showOpenDialog(null);
                       File file = chooser.getSelectedFile();
                       image.setIcon(new ImageIcon(file.toString()));
                       fileName = file.getAbsolutePath();
                       try {
                           File image = new File(fileName);
                           FileInputStream fis = new FileInputStream(image);
                           ByteArrayOutputStream bos = new ByteArrayOutputStream();
                           byte[] buf = new byte[1024];
                           for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                               bos.write(buf, 0, readNum);
                           }
                           photo = bos.toByteArray();
                       } catch (Exception er) {
                           er.getMessage();
                       }
                   }

               });

               id=new JLabel("NIC");
               id.setBounds(250,300,300,40);
               id.setFont(font);
               id.setForeground(Color.black);
               add(id);

               id_txt=new JTextField();
               id_txt.setBounds(550,300,350,30);
               id_txt.setFont(f);
               add(id_txt);

               name=new JLabel("NAME");
               name.setBounds(250,350,300,40);
               name.setFont(font);
               name.setForeground(Color.black);
               add(name);

               name_txt=new JTextField();
               name_txt.setBounds(550,350,350,30);
               name_txt.setFont(f);
               add(name_txt);

               address=new JLabel("ADDRESS");
               address.setBounds(250,400,300,40);
               address.setFont(font);
               address.setForeground(Color.black);
               add(address);

               address_txt=new JTextField();
               address_txt.setBounds(550,400,350,30);
               address_txt.setFont(f);
               add(address_txt);

               age=new JLabel("AGE");
               age.setBounds(250,450,300,40);
               age.setFont(font);
               age.setForeground(Color.black);
               add(age);

               age_txt=new JTextField();
               age_txt.setBounds(550,450,350,30);
               age_txt.setFont(f);
               add(age_txt);

               birth=new JLabel("BIRTHDAY");
               birth.setBounds(250,500,300,40);
               birth.setFont(font);
               birth.setForeground(Color.black);
               add(birth);

               birth_txt=new JTextField();
               birth_txt.setBounds(550,500,350,30);
               birth_txt.setFont(f);
               add(birth_txt);

               gender=new JLabel("GENDER");
               gender.setBounds(250,550,300,40);
               gender.setFont(font);
               gender.setForeground(Color.black);
               add(gender);

               String sex[]={" ","Female","Male","Intersex"};
               gender_com=new JComboBox(sex);
               gender_com.setBounds(550,550,150,30);
               gender_com.setFont(f);
               add(gender_com);

               contact=new JLabel("CONTACT NO.");
               contact.setBounds(250,600,300,40);
               contact.setFont(font);
               contact.setForeground(Color.black);
               add(contact);

               contact_txt=new JTextField();
               contact_txt.setBounds(550,600,350,30);
               contact_txt.setFont(f);
               add(contact_txt);

               String dura[]={" ","Present","Past","Present/past"};
               dur_com=new JComboBox(dura);
               dur_com.setBounds(950,600,150,30);
               dur_com.setFont(f);
               add(dur_com);

               add_btn=new JButton("ADD");
               add_btn.setBounds(1000,120,100,40);
               add_btn.setFont(btn_font);
               add_btn.setBackground(Color.BLACK);
               add_btn.setForeground(Color.white);
               add_btn.setBorder(brd);
               add(add_btn);
               add_btn.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {

                       add();
                   }
               });
              search_btn=new JButton("SEARCH");
               search_btn.setBounds(1000,170,100,40);
               search_btn.setFont(btn_font);
               search_btn.setBackground(Color.BLACK);
               search_btn.setForeground(Color.white);
               search_btn.setBorder(brd);
               add(search_btn);
               search_btn.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       searchPrinc();
                   }
               });
               update_btn=new JButton("UPDATE");
               update_btn.setBounds(1000,220,100,40);
               update_btn.setFont(btn_font);
               update_btn.setBackground(Color.BLACK);
               update_btn.setForeground(Color.white);
               update_btn.setBorder(brd);
               add(update_btn);
               update_btn.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       updatePrinc();
                   }
               });
               delete_btn=new JButton("DELETE");
               delete_btn.setBounds(1000,270,100,40);
               delete_btn.setFont(btn_font);
               delete_btn.setBackground(Color.BLACK);
               delete_btn.setForeground(Color.white);
               delete_btn.setBorder(brd);
               add(delete_btn);
               delete_btn.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       deletePrinc();
                   }
               });
               clear_btn=new JButton("CLEAR");
               clear_btn.setBounds(1000,370,100,40);
               clear_btn.setFont(btn_font);
               clear_btn.setBackground(Color.BLACK);
               clear_btn.setForeground(Color.white);
               clear_btn.setBorder(brd);
               add(clear_btn);
               clear_btn.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       id_txt.setText(" ");
                       name_txt.setText(" ");
                       address_txt.setText(" ");
                       gender_com.setSelectedItem(" ");
                       birth_txt.setText(" ");
                       dur_com.setSelectedItem(" ");
                       contact_txt.setText(" ");
                       age_txt.setText(" ");
                       image.setIcon(null);
                   }
               });
               menu_btn=new JButton("MENU");
               menu_btn.setBounds(1000,420,100,40);
               menu_btn.setFont(btn_font);
               menu_btn.setBackground(Color.BLACK);
               menu_btn.setForeground(Color.white);
               menu_btn.setBorder(brd);
               add(menu_btn);
               menu_btn.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       dispose();
                       new teams();
                   }
               });
               add(back);
               setSize(1200, 700);
               setResizable(true);
               setDefaultCloseOperation(EXIT_ON_CLOSE);
               setLocationRelativeTo(null);
               setVisible(true);
       }
    void add() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "insert into principal(nic,name,address,age,birthday,gender,contact,duration,image)" + "Values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            String Nic = id_txt.getText();
            String Name = name_txt.getText();
            String Address = address_txt.getText();
            String Age = age_txt.getText();
            long Birth = Date.parse(birth_txt.getText());
            java.sql.Date sBirth = new java.sql.Date(Birth);
            String Gender = (String) gender_com.getSelectedItem();
            String Contacts = contact_txt.getText();
            String Duration = (String) dur_com.getSelectedItem();
            prp.setString(1, Nic);
            prp.setString(2, Name);
            prp.setString(3, Address);
            prp.setString(4, Age);
            prp.setDate(5, sBirth);
            prp.setString(6, Gender);
            prp.setString(7, Contacts);
            prp.setString(8, Duration);
            prp.setBytes(9, photo);
            prp.execute();

            JOptionPane.showMessageDialog(null, "saved");
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void searchPrinc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "select * from principal where nic=?";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            String Id = id_txt.getText();
            prp.setString(1, Id);
            ResultSet rs = prp.executeQuery();
            if (rs.next()) {
                String val2 = rs.getString("name");
                name_txt.setText(val2);
                String val3 = rs.getString("address");
                address_txt.setText(val3);
                String val5 = rs.getString("age");
                age_txt.setText(val5);
                birth_txt.setText(String.format("%tD", rs.getDate("birthday")));
                String val7 = rs.getString("gender");
                gender_com.setSelectedItem(val7);
                contact_txt.setText(rs.getString("contact"));
                String val8 = rs.getString("duration");
                dur_com.setSelectedItem(val8);
                BufferedImage image1=ImageIO.read(rs.getBinaryStream("image"));
                image.setIcon(new ImageIcon(image1));
            }
            else{
                JOptionPane.showMessageDialog(null,"not here!");
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
    void updatePrinc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "UPDATE principal SET name=?,address=?,age=?,birthday=?,gender=?,contact=?,duration=?,image=? where nic=?";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            String Id = id_txt.getText();
            String Name = name_txt.getText();
            String Address = address_txt.getText();
            String Age = age_txt.getText();
            long Birth = Date.parse(birth_txt.getText());
            java.sql.Date sBirth = new java.sql.Date(Birth);
            String Gender = (String) gender_com.getSelectedItem();
            String Contacts = contact_txt.getText();
            String Duration = (String) dur_com.getSelectedItem();
            prp.setString(1, Name);
            prp.setString(2, Address);
            prp.setString(3, Age);
            prp.setDate(4, sBirth);
            prp.setString(5, Gender);
            prp.setString(6, Contacts);
            prp.setString(7,Duration);
            prp.setBytes(8,photo);
            prp.setString(9, Id);
            prp.executeUpdate();
            JOptionPane.showMessageDialog(null,"updated!");
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    void deletePrinc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "DELETE from principal where nic=?";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            String Id = id_txt.getText();
            prp.setString(1, Id);
            prp.execute();
            JOptionPane.showMessageDialog(null,"delete successful!");
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
//student enter page
class studentPage extends JFrame {
    JPanel back = new JPanel();
    JLabel id, name, address, grade, birth, age, gender, admissionDate, guardian, contact, stu;
    JTextField id_txt, name_txt, address_txt, birth_txt, age_txt, admissionDate_txt, guardian_txt, contact_txt;
    JComboBox grade_com, gender_com;
    JButton add_btn, search_btn, delete_btn, update_btn, clear_btn, menu_btn;
    Font font = new Font("Arial", Font.BOLD, 30);
    Font f = new Font("Arial", Font.BOLD, 20);
    Font btn_font = new Font("Arial", Font.BOLD, 20);
    Border brd = BorderFactory.createLineBorder(Color.red, 2);
    studentPage() {
        back.setBackground(Color.YELLOW);

        stu = new JLabel("STUDENT");
        stu.setBounds(500, 20, 300, 70);
        stu.setFont(new Font("Arial", Font.BOLD, 50));
        stu.setForeground(Color.RED);
        add(stu);

        id = new JLabel("ID");
        id.setBounds(250, 120, 300, 40);
        id.setFont(font);
        id.setForeground(Color.black);
        add(id);

        id_txt = new JTextField();
        id_txt.setBounds(550, 120, 350, 30);
        id_txt.setFont(f);
        add(id_txt);

        name = new JLabel("NAME");
        name.setBounds(250, 170, 300, 40);
        name.setFont(font);
        name.setForeground(Color.black);
        add(name);

        name_txt = new JTextField();
        name_txt.setBounds(550, 170, 350, 30);
        name_txt.setFont(f);
        add(name_txt);

        address = new JLabel("ADDRESS");
        address.setBounds(250, 220, 300, 40);
        address.setFont(font);
        address.setForeground(Color.black);
        add(address);

        address_txt = new JTextField();
        address_txt.setBounds(550, 220, 350, 30);
        address_txt.setFont(f);
        add(address_txt);

        grade = new JLabel("GRADE");
        grade.setBounds(250, 270, 300, 40);
        grade.setFont(font);
        grade.setForeground(Color.black);
        add(grade);

        String gradescl[] = {" ", "1A", "1B", "2A", "2B", "3A", "3B", "4A", "4B", "5A", "5B", "6A", "6B"};
        grade_com = new JComboBox(gradescl);
        grade_com.setBounds(550, 270, 70, 30);
        grade_com.setFont(f);
        add(grade_com);

        age = new JLabel("AGE");
        age.setBounds(250, 320, 300, 40);
        age.setFont(font);
        age.setForeground(Color.black);
        add(age);

        age_txt = new JTextField();
        age_txt.setBounds(550, 320, 350, 30);
        age_txt.setFont(f);
        add(age_txt);

        birth = new JLabel("BIRTHDAY");
        birth.setBounds(250, 370, 300, 40);
        birth.setFont(font);
        birth.setForeground(Color.black);
        add(birth);

        birth_txt = new JTextField();
        birth_txt.setBounds(550, 370, 350, 30);
        birth_txt.setFont(f);
        add(birth_txt);

        gender = new JLabel("GENDER");
        gender.setBounds(250, 420, 300, 40);
        gender.setFont(font);
        gender.setForeground(Color.black);
        add(gender);

        String sex[] = {" ", "Female", "Male", "Intersex"};
        gender_com = new JComboBox(sex);
        gender_com.setBounds(550, 420, 150, 30);
        gender_com.setFont(f);
        add(gender_com);

        admissionDate = new JLabel("ADMISSION DATE");
        admissionDate.setBounds(250, 470, 300, 40);
        admissionDate.setFont(font);
        admissionDate.setForeground(Color.black);
        add(admissionDate);

        admissionDate_txt = new JTextField();
        admissionDate_txt.setBounds(550, 470, 350, 30);
        admissionDate_txt.setFont(f);
        add(admissionDate_txt);

        guardian = new JLabel("GUARDIAN'S NAME");
        guardian.setBounds(250, 520, 300, 40);
        guardian.setFont(font);
        guardian.setForeground(Color.black);
        add(guardian);

        guardian_txt = new JTextField();
        guardian_txt.setBounds(550, 520, 350, 30);
        guardian_txt.setFont(f);
        add(guardian_txt);

        contact = new JLabel("CONTACT NO.");
        contact.setBounds(250, 570, 300, 40);
        contact.setFont(font);
        contact.setForeground(Color.black);
        add(contact);

        contact_txt = new JTextField();
        contact_txt.setBounds(550, 570, 350, 30);
        contact_txt.setFont(f);
        add(contact_txt);

        add_btn = new JButton("ADD");
        add_btn.setBounds(1000, 120, 100, 40);
        add_btn.setFont(btn_font);
        add_btn.setBackground(Color.BLACK);
        add_btn.setForeground(Color.white);
        add_btn.setBorder(brd);
        add(add_btn);

        search_btn = new JButton("SEARCH");
        search_btn.setBounds(1000, 170, 100, 40);
        search_btn.setFont(btn_font);
        search_btn.setBackground(Color.BLACK);
        search_btn.setForeground(Color.white);
        search_btn.setBorder(brd);
        add(search_btn);

        update_btn = new JButton("UPDATE");
        update_btn.setBounds(1000, 220, 100, 40);
        update_btn.setFont(btn_font);
        update_btn.setBackground(Color.BLACK);
        update_btn.setForeground(Color.white);
        update_btn.setBorder(brd);
        add(update_btn);

        delete_btn = new JButton("DELETE");
        delete_btn.setBounds(1000, 270, 100, 40);
        delete_btn.setFont(btn_font);
        delete_btn.setBackground(Color.BLACK);
        delete_btn.setForeground(Color.white);
        delete_btn.setBorder(brd);
        add(delete_btn);

        clear_btn = new JButton("CLEAR");
        clear_btn.setBounds(1000, 370, 100, 40);
        clear_btn.setFont(btn_font);
        clear_btn.setBackground(Color.BLACK);
        clear_btn.setForeground(Color.white);
        clear_btn.setBorder(brd);
        add(clear_btn);

        menu_btn = new JButton("MENU");
        menu_btn.setBounds(1000, 420, 100, 40);
        menu_btn.setFont(btn_font);
        menu_btn.setBackground(Color.BLACK);
        menu_btn.setForeground(Color.white);
        menu_btn.setBorder(brd);
        add(menu_btn);

        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                add();
            }
        });
        search_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchStd();
            }
        });
        clear_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id_txt.setText(" ");
                name_txt.setText(" ");
                address_txt.setText(" ");
                grade_com.setSelectedItem(" ");
                gender_com.setSelectedItem(" ");
                birth_txt.setText(" ");
                admissionDate_txt.setText(" ");
                guardian_txt.setText(" ");
                contact_txt.setText(" ");
                age_txt.setText(" ");

            }
        });
        update_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateStd();
            }
        });
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                deleteStd();
            }
        });
        menu_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new teams();
            }
        });
        add(back);
        setSize(1200, 700);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void add() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "insert into std_data(id,name,address,grade,age,birthday,gender,admission,guardian,contact)" + "Values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            int Id = Integer.parseInt(id_txt.getText());
            String Name = name_txt.getText();
            String Address = address_txt.getText();
            String Grade = (String) grade_com.getSelectedItem();
            String Age = age_txt.getText();
            long Birth = Date.parse(birth_txt.getText());
            java.sql.Date sBirth = new java.sql.Date(Birth);
            String Gender = (String) gender_com.getSelectedItem();
            long Admission = Date.parse(admissionDate_txt.getText());
            java.sql.Date Admisions = new java.sql.Date(Admission);
            String Guardian = guardian_txt.getText();
            String Contacts = contact_txt.getText();
            prp.setInt(1, Id);
            prp.setString(2, Name);
            prp.setString(3, Address);
            prp.setString(4, Grade);
            prp.setString(5, Age);
            prp.setDate(6, sBirth);
            prp.setString(7, Gender);
            prp.setDate(8, Admisions);
            prp.setString(9, Guardian);
            prp.setString(10, Contacts);
            prp.execute();

            JOptionPane.showMessageDialog(null, "saved");
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void searchStd() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "select * from std_data where id=?";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            int Id = Integer.parseInt(id_txt.getText());
            prp.setInt(1, Id);
            ResultSet rs = prp.executeQuery();
            if (rs.next()) {
                String val2 = rs.getString("name");
                name_txt.setText(val2);
                String val3 = rs.getString("address");
                address_txt.setText(val3);
                String val4 = rs.getString("grade");
                grade_com.setSelectedItem(val4);
                String val5 = rs.getString("age");
                age_txt.setText(val5);
                birth_txt.setText(String.format("%tD", rs.getDate("birthday")));
                String val7 = rs.getString("gender");
                gender_com.setSelectedItem(val7);
                admissionDate_txt.setText(String.format("%tD", rs.getDate("admission")));
                guardian_txt.setText(rs.getString("guardian"));
                contact_txt.setText(rs.getString("contact"));
            }
            else{
                JOptionPane.showMessageDialog(null,"not here!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void updateStd() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "UPDATE std_data SET name=?,address=?,grade=?,age=?,birthday=?,gender=?,admission=?,guardian=?,contact=? where id=?";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            int Id = Integer.parseInt(id_txt.getText());
            String Name = name_txt.getText();
            String Address = address_txt.getText();
            String Grade = (String) grade_com.getSelectedItem();
            String Age = age_txt.getText();
            long Birth = Date.parse(birth_txt.getText());
            java.sql.Date sBirth = new java.sql.Date(Birth);
            String Gender = (String) gender_com.getSelectedItem();
            long Admission = Date.parse(admissionDate_txt.getText());
            java.sql.Date Admisions = new java.sql.Date(Admission);
            String Guardian = guardian_txt.getText();
            String Contacts = contact_txt.getText();
            prp.setString(1, Name);
            prp.setString(2, Address);
            prp.setString(3, Grade);
            prp.setString(4, Age);
            prp.setDate(5, sBirth);
            prp.setString(6, Gender);
            prp.setDate(7, Admisions);
            prp.setString(8, Guardian);
            prp.setString(9, Contacts);
            prp.setInt(10, Id);
            prp.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void deleteStd() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "DELETE from std_data where id=?";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            int Id = Integer.parseInt(id_txt.getText());
            prp.setInt(1, Id);
            prp.execute();
            JOptionPane.showMessageDialog(null,"delete successful!");
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
//student records page
class stdRecords extends JFrame {
    JPanel back = new JPanel();
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);
    JScrollPane sp = new JScrollPane(table);
    JComboBox grades;
    JButton Add;
    Border brd = BorderFactory.createLineBorder(Color.BLACK, 2);
    int q;
    stdRecords() {
        back.setBackground(Color.YELLOW);
        String gradescl[] = {"All", "1A", "1B", "2A", "2B", "3A", "3B", "4A", "4B", "5A", "5B", "6A", "6B"};
        grades=new JComboBox(gradescl);
        grades.setBounds(550,50,100,40);
        grades.setFont(new Font("Arial",Font.BOLD,25));
        grades.setForeground(Color.BLUE);
        add(grades);

        Add=new JButton("Option");
        Add.setBounds(550,500,100,40);
        Add.setFont(new Font("Arial",Font.BOLD,25));
        Add.setForeground(Color.yellow);
        Add.setBorder(brd);
        Add.setBackground(Color.BLUE);
        add(Add);

        model.addColumn("id");
        model.addColumn("name");
        model.addColumn("address");
        model.addColumn("grade");
        model.addColumn("age");
        model.addColumn("birthday");
        model.addColumn("gender");
        model.addColumn("admission");
        model.addColumn("guardian");
        model.addColumn("contact");
        sp.setBounds(100, 150, 1000, 300);
        sp.setVisible(true);
        add(sp);
        showRec();

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new studentPage();
            }
        });
        add(back);
        setSize(1200, 700);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

void showRec() {

    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
        Statement stmt;
        stmt = con.createStatement();
        if(grades.getSelectedItem().equals("All")) {
            String query = "select * from std_data";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData stData = rs.getMetaData();
            q = stData.getColumnCount();
            model.setRowCount(0);
            while (rs.next()) {
                Vector columnData = new Vector();
                for (int i = 1; i <= q; i++) {
                    columnData.add(rs.getInt("id"));
                    columnData.add(rs.getString("name"));  //The column names you likes
                    columnData.add(rs.getString("address"));
                    columnData.add(rs.getString("grade"));
                    columnData.add(rs.getString("age"));
                    columnData.add(rs.getDate("birthday"));
                    columnData.add(rs.getString("gender"));
                    columnData.add(rs.getDate("admission"));
                    columnData.add(rs.getString("guardian"));
                    columnData.add(rs.getString("contact"));
                }
                model.addRow(columnData);

            }
        }
        else if(grades.getSelectedItem().equals("1A")) {
            String query = "select * from std_data where grade ='1A'";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData stData = rs.getMetaData();
            q = stData.getColumnCount();
            model.setRowCount(0);
            while (rs.next()) {
                Vector columnData = new Vector();
                for (int i = 1; i <= q; i++) {
                    columnData.add(rs.getInt("id"));     //The column names of database
                    columnData.add(rs.getString("name"));
                    columnData.add(rs.getString("address"));
                    columnData.add(rs.getString("grade"));
                    columnData.add(rs.getString("age"));
                    columnData.add(rs.getDate("birthday"));
                    columnData.add(rs.getString("gender"));
                    columnData.add(rs.getDate("admission"));
                    columnData.add(rs.getString("guardian"));
                    columnData.add(rs.getString("contact"));
                }
                model.addRow(columnData);

            }
        }
        con.close();
    } catch (SQLException | ClassNotFoundException throwables) {
        throwables.printStackTrace();
       }
   }
}
//teacher page
class teacherPage extends JFrame{
    JPanel back=new JPanel();
    JLabel id,name,address,birth,age,gender,contact,teach,image;
    JTextField id_txt,name_txt,address_txt,birth_txt,age_txt,contact_txt;
    JComboBox gender_com,dur_com;
    JButton add_btn,search_btn,delete_btn,update_btn,clear_btn,menu_btn,selectImage_btn;
    Font font=new Font("Arial",Font.BOLD,30);
    Font f=new Font("Arial",Font.BOLD,20);
    Font btn_font=new Font("Arial",Font.BOLD,20);
    Border brd = BorderFactory.createLineBorder(Color.red, 2);
    byte []photo=null;
    String fileName=null;
    teacherPage(){
        back.setBackground(Color.YELLOW);

        teach=new JLabel("TEACHER");
        teach.setBounds(500,20,300,70);
        teach.setFont(new Font("Arial",Font.BOLD,50));
        teach.setForeground(Color.RED);
        add(teach);

        image =new JLabel();
        image.setBounds(550,100,150,150);
        image.setBorder(brd);
        add(image);

        selectImage_btn=new JButton("SelectImage");
        selectImage_btn.setBounds(550,260,150,30);
        selectImage_btn.setFont(new Font("Arial",Font.BOLD,20));
        selectImage_btn.setForeground(Color.white);
        selectImage_btn.setBackground(Color.BLUE);
        selectImage_btn.setBorder(brd);
        add(selectImage_btn);

        id=new JLabel("NIC");
        id.setBounds(250,300,300,40);
        id.setFont(font);
        id.setForeground(Color.black);
        add(id);

        id_txt=new JTextField();
        id_txt.setBounds(550,300,350,30);
        id_txt.setFont(f);
        add(id_txt);

        name=new JLabel("NAME");
        name.setBounds(250,350,300,40);
        name.setFont(font);
        name.setForeground(Color.black);
        add(name);

        name_txt=new JTextField();
        name_txt.setBounds(550,350,350,30);
        name_txt.setFont(f);
        add(name_txt);

        address=new JLabel("ADDRESS");
        address.setBounds(250,400,300,40);
        address.setFont(font);
        address.setForeground(Color.black);
        add(address);

        address_txt=new JTextField();
        address_txt.setBounds(550,400,350,30);
        address_txt.setFont(f);
        add(address_txt);

        age=new JLabel("AGE");
        age.setBounds(250,450,300,40);
        age.setFont(font);
        age.setForeground(Color.black);
        add(age);

        age_txt=new JTextField();
        age_txt.setBounds(550,450,350,30);
        age_txt.setFont(f);
        add(age_txt);

        birth=new JLabel("BIRTHDAY");
        birth.setBounds(250,500,300,40);
        birth.setFont(font);
        birth.setForeground(Color.black);
        add(birth);

        birth_txt=new JTextField();
        birth_txt.setBounds(550,500,350,30);
        birth_txt.setFont(f);
        add(birth_txt);

        gender=new JLabel("GENDER");
        gender.setBounds(250,550,300,40);
        gender.setFont(font);
        gender.setForeground(Color.black);
        add(gender);

        String sex[]={" ","Female","Male","Intersex"};
        gender_com=new JComboBox(sex);
        gender_com.setBounds(550,550,150,30);
        gender_com.setFont(f);
        add(gender_com);

        contact=new JLabel("CONTACT NO.");
        contact.setBounds(250,600,300,40);
        contact.setFont(font);
        contact.setForeground(Color.black);
        add(contact);

        contact_txt=new JTextField();
        contact_txt.setBounds(550,600,350,30);
        contact_txt.setFont(f);
        add(contact_txt);

        add_btn=new JButton("ADD");
        add_btn.setBounds(1000,120,100,40);
        add_btn.setFont(btn_font);
        add_btn.setBackground(Color.BLACK);
        add_btn.setForeground(Color.white);
        add_btn.setBorder(brd);
        add(add_btn);
        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                add();
            }
        });
        search_btn=new JButton("SEARCH");
        search_btn.setBounds(1000,170,100,40);
        search_btn.setFont(btn_font);
        search_btn.setBackground(Color.BLACK);
        search_btn.setForeground(Color.white);
        search_btn.setBorder(brd);
        add(search_btn);
        search_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchTeach();
            }
        });
        update_btn=new JButton("UPDATE");
        update_btn.setBounds(1000,220,100,40);
        update_btn.setFont(btn_font);
        update_btn.setBackground(Color.BLACK);
        update_btn.setForeground(Color.white);
        update_btn.setBorder(brd);
        add(update_btn);
        update_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateTeach();
            }
        });
        delete_btn=new JButton("DELETE");
        delete_btn.setBounds(1000,270,100,40);
        delete_btn.setFont(btn_font);
        delete_btn.setBackground(Color.BLACK);
        delete_btn.setForeground(Color.white);
        delete_btn.setBorder(brd);
        add(delete_btn);
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                deleteTeach();
            }
        });
        clear_btn=new JButton("CLEAR");
        clear_btn.setBounds(1000,370,100,40);
        clear_btn.setFont(btn_font);
        clear_btn.setBackground(Color.BLACK);
        clear_btn.setForeground(Color.white);
        clear_btn.setBorder(brd);
        add(clear_btn);
        clear_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id_txt.setText(" ");
                name_txt.setText(" ");
                address_txt.setText(" ");
                gender_com.setSelectedItem(" ");
                birth_txt.setText(" ");
                contact_txt.setText(" ");
                age_txt.setText(" ");
                image.setIcon(null);
            }
        });
        menu_btn=new JButton("MENU");
        menu_btn.setBounds(1000,420,100,40);
        menu_btn.setFont(btn_font);
        menu_btn.setBackground(Color.BLACK);
        menu_btn.setForeground(Color.white);
        menu_btn.setBorder(brd);
        add(menu_btn);
        menu_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new teams();
            }
        });
        selectImage_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser=new JFileChooser();
                chooser.showOpenDialog(null);
                File file=chooser.getSelectedFile();
                image.setIcon(new ImageIcon(file.toString()));
                fileName=file.getAbsolutePath();
                try{
                    File image=new File(fileName);
                    FileInputStream fis = new FileInputStream(image);
                    ByteArrayOutputStream bos=new ByteArrayOutputStream();
                    byte []buf=new byte[1024];
                    for(int readnum;(readnum=fis.read(buf))!=-1;){
                        bos.write(buf,0,readnum);
                    }
                    photo=bos.toByteArray();
                }catch(Exception error){
                    error.getMessage();
                }

            }
        });
        add(back);
        setSize(1200, 700);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    void add() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "insert into teacher_data(nic,name,address,age,birthday,gender,contact,image)" + "Values(?,?,?,?,?,?,?,?)";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            String Nic=id_txt.getText();
            String Name = name_txt.getText();
            String Address = address_txt.getText();
            String Age = age_txt.getText();
            long Birth = Date.parse(birth_txt.getText());
            java.sql.Date sBirth = new java.sql.Date(Birth);
            String Gender = (String) gender_com.getSelectedItem();
            String Contacts = contact_txt.getText();
            prp.setString(1, Nic);
            prp.setString(2, Name);
            prp.setString(3, Address);
            prp.setString(4, Age);
            prp.setDate(5, sBirth);
            prp.setString(6, Gender);
            prp.setString(7, Contacts);
            prp.setBytes(8,photo);
            prp.execute();

            JOptionPane.showMessageDialog(null, "saved");
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    void searchTeach() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "select * from teacher_data where nic=?";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            String Id = id_txt.getText();
            prp.setString(1, Id);
            ResultSet rs = prp.executeQuery();
            if (rs.next()) {
                String val2 = rs.getString("name");
                name_txt.setText(val2);
                String val3 = rs.getString("address");
                address_txt.setText(val3);
                String val5 = rs.getString("age");
                age_txt.setText(val5);
                birth_txt.setText(String.format("%tD", rs.getDate("birthday")));
                String val7 = rs.getString("gender");
                gender_com.setSelectedItem(val7);
                contact_txt.setText(rs.getString("contact"));
                BufferedImage image1= ImageIO.read(rs.getBinaryStream("image"));
                image.setIcon(new ImageIcon(image1));

            }
            else{
                JOptionPane.showMessageDialog(null,"not here!");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    void updateTeach() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "UPDATE teacher_data SET name=?,address=?,age=?,birthday=?,gender=?,contact=?,image=? where nic=?";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            String Id = id_txt.getText();
            String Name = name_txt.getText();
            String Address = address_txt.getText();
            String Age = age_txt.getText();
            long Birth = Date.parse(birth_txt.getText());
            java.sql.Date sBirth = new java.sql.Date(Birth);
            String Gender = (String) gender_com.getSelectedItem();
            String Contacts = contact_txt.getText();
            prp.setString(1, Name);
            prp.setString(2, Address);
            prp.setString(3, Age);
            prp.setDate(4, sBirth);
            prp.setString(5, Gender);
            prp.setString(6, Contacts);
            prp.setBytes(7,photo);
            prp.setString(8, Id);
            prp.executeUpdate();
            JOptionPane.showMessageDialog(null,"updated!");
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (Exception er){
            er.getMessage();
        }
    }
    void deleteTeach() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            String query = "DELETE from teacher_data where nic=?";
            PreparedStatement prp = (PreparedStatement) con.prepareStatement(query);
            String Id = id_txt.getText();
            prp.setString(1, Id);
            prp.execute();
            JOptionPane.showMessageDialog(null,"delete successful!");
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
class teacherRecords extends JFrame{
    JPanel back = new JPanel();
    JLabel teach;
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);
    JScrollPane sp = new JScrollPane(table);
    JComboBox grades;
    JButton Add;
    Border brd = BorderFactory.createLineBorder(Color.BLACK, 2);
    int q;
    teacherRecords() {
        back.setBackground(Color.YELLOW);
        teach=new JLabel("TEACHERS.");
        teach.setBounds(450,20,350,70);
        teach.setFont(new Font("Arial",Font.BOLD,60));
        teach.setForeground(Color.RED);
        add(teach);

        Add=new JButton("Option");
        Add.setBounds(550,500,100,40);
        Add.setFont(new Font("Arial",Font.BOLD,25));
        Add.setForeground(Color.yellow);
        Add.setBorder(brd);
        Add.setBackground(Color.BLUE);
        add(Add);

        model.addColumn("id");        //The column names you likes
        model.addColumn("name");
        model.addColumn("address");
        model.addColumn("age");
        model.addColumn("birthday");
        model.addColumn("gender");
        model.addColumn("contact");
        model.addColumn("image");
        sp.setBounds(100, 150, 1000, 300);
        sp.setVisible(true);
        add(sp);
        showRec();

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new teacherPage();
            }
        });
        add(back);
        setSize(1200, 700);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void showRec() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginsc", "root", "");
            Statement stmt;
            stmt = con.createStatement();
                String query = "select * from teacher_data";
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData stData = rs.getMetaData();
                q = stData.getColumnCount();
                model.setRowCount(0);
                while (rs.next()) {
                    Vector columnData = new Vector();
                    for (int i = 1; i <= q; i++) {
                        columnData.add(rs.getString("nic"));  //database names include " "
                        columnData.add(rs.getString("name"));
                        columnData.add(rs.getString("address"));
                        columnData.add(rs.getString("age"));
                        columnData.add(rs.getDate("birthday"));
                        columnData.add(rs.getString("gender"));
                        columnData.add(rs.getString("contact"));
                        columnData.add(rs.getBytes("image"));
                    }
                    model.addRow(columnData);

                }

            con.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}



