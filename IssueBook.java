/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MY HP
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form issueBook
     */
    public IssueBook() {
        initComponents();
    }
    //to fetch book details from database and display it to bookdetails panel
    public void getBookDetails(){
        int bookId= Integer.parseInt(txt_bookId.getText());
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms?zeroDateTimeBehavior=convertToNull","root","");
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id= ?");
            
            pst.setInt(1,bookId);
            ResultSet rs=pst.executeQuery();
            
            if(rs.next()){
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));
            }
            else{
                lbl_bookError.setText("Invalid book id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //to fetch students details from database and display it to students details panel

    /**
     *
     */
    public void getStudentDetails(){
        int studentId = Integer.parseInt(txt_studentId.getText());
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms?zeroDateTimeBehavior=convertToNull","root","");
            PreparedStatement pst = con.prepareStatement("select * from student_details where student_id= ?");
            
            pst.setInt(1,studentId);
            ResultSet rs=pst.executeQuery();
            
            if(rs.next()){
                lbl_studentId.setText(rs.getString("student_id"));
                lbl_studentName.setText(rs.getString("name"));
                lbl_course.setText(rs.getString("course"));
                lbl_branch.setText(rs.getString("branch"));
            }
            else{
                lbl_studentError.setText("Invalid Student Id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // Insert issue bok details to database
    public boolean issuebook(){
        boolean isIssued =false;
 
        int bookId= Integer.parseInt(txt_bookId.getText());
        int studentId= Integer.parseInt(txt_studentId.getText());
        String bookName= lbl_bookName.getText();
        String studentName = lbl_studentName.getText();
        
        java.util.Date uIssueDate = date_IssueDate.getDatoFecha();
        java.util.Date uDueDate = date_dueDate.getDatoFecha();
        
        long l1 = uIssueDate.getTime();
        long l2 = uDueDate.getTime();
        
        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms?zeroDateTimeBehavior=convertToNull","root","");
            String sql ="insert into issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status) values(?,?,?,?,?,?,?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1,bookId);
            pst.setString(2,bookName);
            pst.setInt(3,studentId);
            pst.setString(4,studentName);
            pst.setDate(5,sIssueDate);
            pst.setDate(6,sDueDate);
            pst.setString(7,"pending");
            
            int rowCount = pst.executeUpdate();
            if(rowCount>0){
                isIssued = true;
            }else{
                isIssued = false;
            }
          
           
        }catch (Exception e){
            e.printStackTrace();
    }
        
    return isIssued;   
    }
    //updating book count
    public void updateBookCount() throws ClassNotFoundException{
       int bookId= Integer.parseInt(txt_bookId.getText());
    try{
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms?zeroDateTimeBehavior=convertToNull","root","");
         String sql= "update book_details set quantity = quantity -1 where book_id = ?";
         PreparedStatement pst = con.prepareStatement(sql);
         pst.setInt(1,bookId);
         
         int rowCount = pst.executeUpdate();
         
            if(rowCount > 0){
                 JOptionPane.showMessageDialog(this,"book count updated");
                 int initialCount=Integer.parseInt(lbl_quantity.getText());
                 lbl_quantity.setText(Integer.toString(initialCount -1));
            }else{
                 JOptionPane.showMessageDialog(this,"can't update book count ");
            }
         
        }catch(Exception  e){
        e.printStackTrace();
        } 
    }
    //check whether book is issued or not
    public boolean isAlreadyIssued(){
        boolean isAlreadyIssued = false;
        int bookId= Integer.parseInt(txt_bookId.getText());
        int studentId= Integer.parseInt(txt_studentId.getText());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms?zeroDateTimeBehavior=convertToNull","root","");
            
            String sql= "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,bookId);
            pst.setInt(2,studentId);
            pst.setString(3,"pending");
            
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                isAlreadyIssued = true;
            }else{
                 isAlreadyIssued = false;
            }
            
           
         
          }catch (Exception e){
              e.printStackTrace();
        }
        return isAlreadyIssued;
    }
    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main1 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        jPanel12 = new javax.swing.JPanel();
        date_dueDate = new rojeru_san.componentes.RSDateChooser();
        date_IssueDate = new rojeru_san.componentes.RSDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panel_main = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lbl_studentError = new javax.swing.JLabel();
        lbl_studentId = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lbl_bookError = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main1.setBackground(new java.awt.Color(255, 255, 255));
        panel_main1.setForeground(new java.awt.Color(255, 255, 255));
        panel_main1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 51, 51));
        jLabel37.setText("Book id :");
        panel_main1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 400, 100, 30));

        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 51)));
        txt_bookId.setForeground(new java.awt.Color(0, 51, 51));
        txt_bookId.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_bookId.setPlaceholder("Enter Book id...");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        panel_main1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 400, 350, -1));

        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_studentId.setForeground(new java.awt.Color(0, 51, 51));
        txt_studentId.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_studentId.setPlaceholder("Enter Student Id...");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        txt_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIdActionPerformed(evt);
            }
        });
        panel_main1.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 470, 350, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel1.setText("  Issue Book");
        panel_main1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 240, 300, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("Student id :");
        panel_main1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 470, 120, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Issue Date :");
        panel_main1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 550, 120, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("Due Date :");
        panel_main1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 630, 120, 30));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle2.setText("Issue Book");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        panel_main1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 710, 310, 80));

        jPanel12.setBackground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_main1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 320, 480, 5));

        date_dueDate.setBackground(new java.awt.Color(255, 0, 51));
        date_dueDate.setForeground(new java.awt.Color(255, 51, 51));
        date_dueDate.setColorBackground(new java.awt.Color(255, 0, 0));
        date_dueDate.setColorForeground(new java.awt.Color(255, 0, 51));
        date_dueDate.setPlaceholder("Select Due Date");
        panel_main1.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 630, 370, -1));

        date_IssueDate.setBackground(new java.awt.Color(255, 0, 51));
        date_IssueDate.setForeground(new java.awt.Color(255, 51, 51));
        date_IssueDate.setColorBackground(new java.awt.Color(255, 0, 0));
        date_IssueDate.setColorForeground(new java.awt.Color(255, 0, 51));
        date_IssueDate.setPlaceholder("Select Issued Date");
        panel_main1.add(date_IssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 550, 370, -1));

        jPanel5.setBackground(new java.awt.Color(102, 102, 255));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("X");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_main1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 0, 40, 40));

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setForeground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(51, 51, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 270, 5));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Branch :");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 100, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Name :");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 80, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Course :");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 100, 30));

        lbl_branch.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 170, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/AddNewBookIcons/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel25.setText("  Student Details");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 290, 90));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Id :");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 60, 30));

        lbl_studentError.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_studentError.setForeground(new java.awt.Color(255, 255, 51));
        jPanel7.add(lbl_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, 280, 30));

        lbl_studentId.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_studentId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.add(lbl_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 150, 30));

        lbl_studentName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_studentName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 180, 30));

        lbl_course.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 520, 180, 30));

        panel_main.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 310, 810));

        jPanel9.setBackground(new java.awt.Color(255, 0, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 280, 5));

        lbl_quantity.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 580, 160, 30));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Name :");
        jPanel9.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 80, 30));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Author :");
        jPanel9.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 90, 30));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Id :");
        jPanel9.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 70, 30));

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));

        jLabel2.setBackground(new java.awt.Color(51, 0, 255));
        jLabel2.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel2.setText("Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 50));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Quantity :");
        jPanel9.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 150, 30));

        lbl_bookError.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(255, 255, 51));
        jPanel9.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, 280, 30));

        lbl_bookId.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_bookId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 200, 30));

        lbl_author.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 190, 30));

        lbl_bookName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, 200, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jframe/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel29.setText("  Book Details");
        jPanel9.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 260, -1));

        panel_main.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 310, 810));

        panel_main1.add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(-200, 10, 1080, -1));

        getContentPane().add(panel_main1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-270, 0, 1430, 820));

        setSize(new java.awt.Dimension(1159, 817));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if(lbl_quantity.getText().equals("0")){
            JOptionPane.showMessageDialog(this,"book is not available .");
        }else{
            if(isAlreadyIssued() == false){
                
              if (issuebook() == true){
                 JOptionPane.showMessageDialog(this,"book issued successfully");
                  try {
                      updateBookCount();
                  } catch (ClassNotFoundException ex) {
                      Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }else{
                 JOptionPane.showMessageDialog(this,"can't  update issue book");
              }
            }else{
                JOptionPane.showMessageDialog(this,"this student already has this book");
            }  
        }
        
       
         
        
       
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        if( !txt_bookId.getText().equals("")){
            getBookDetails();
        }
        
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
        if( !txt_studentId.getText().equals("")){
            getStudentDetails();
        }
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel11MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new IssueBook().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_IssueDate;
    private rojeru_san.componentes.RSDateChooser date_dueDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentError;
    private javax.swing.JLabel lbl_studentId;
    private javax.swing.JLabel lbl_studentName;
    private javax.swing.JPanel panel_main;
    private javax.swing.JPanel panel_main1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables

   
}
