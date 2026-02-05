/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Students;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;

/**
 *
 * @author elisha
 */
public final class StudentForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StudentForm.class.getName());

    /**
     * Creates new form StudentForm
     */
    public StudentForm() {
        initComponents();
        
        loadYears();
        genderRadioGroup();
        departmentRadioGroup();
    }

    
//    Global variables declaration
    
    // Variables to store valid entered inputs
    String FirstName;
    String LastName;
    String Email;
    String Password;
    String DOB;
    String Gender;
    String Department;
    
    // Variable to store generated student ID
    String GeneratedID;
    
    ButtonGroup genderGroup = new ButtonGroup();
    ButtonGroup departmentGroup = new ButtonGroup();
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-+])(?=\\S+$).{8,20}$";
    String email;
    String comfirmEmail;
    
    String password;
    String comfirmPassword;
    
//    ID: 2026-00023
    public void generateID(){
       
        int currentYear = Year.now().getValue();
        
//        createing random rumber
        Random random = new Random();
        int min = 10000;
        int max = 99999;
        int randomNumber = random.nextInt(min, max);
        
        GeneratedID = String.valueOf(currentYear) + "-" + String.valueOf(randomNumber);
     
    }
    
    public void SaveToCSV() {

        String filePath = "src/data/students.csv";
        File csvFile = new File(filePath);

        try {
            File parentDir = csvFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            boolean fileExists = csvFile.exists();

            FileWriter writer = new FileWriter(csvFile, true);

            // write header ONLY once (when file did not exist)
            if (!fileExists) {
                writer.append("Student ID,First Name,Last Name,Email,Password,DOB,Gender,Department\n");
            }

            // always write data (submit button triggers this method)
            writer.append(
                GeneratedID + "," +
                FirstName + "," +
                LastName + "," +
                Email + "," +
                Password + "," +
                DOB + "," +
                Gender + "," +
                Department + "\n"
            );

            writer.flush();
            writer.close();

        } catch (IOException e) {
        }
    }
    
    
    public void clearAllFields() {

        // -------- TEXT FIELDS --------
        firstname_txt.setText("");
        lastname_txt.setText("");
        email_txt.setText("");
        emailComfirm_txt.setText("");
        password_txt.setText("");
        comfirmpassword_txt.setText("");

        // -------- COMBO BOXES --------
        yearCombo.setSelectedIndex(0);
        monthCombo.removeAllItems();
        monthCombo.addItem("Select Month");
        dayCombo.removeAllItems();
        dayCombo.addItem("Select Day");

        // -------- RADIO BUTTONS --------
        genderGroup.clearSelection();
        departmentGroup.clearSelection();

//        // -------- OUTPUT AREA --------
//        displayData_textArea.setText("");
}

    public void GetInputedData(){
        displayData_textArea.setText(
                "\n        Student ID:       "+GeneratedID+"\n"+
                "        First Name:       "+FirstName+"\n"+
                "        Last Name:        "+LastName+"\n"+
                "        Email Address:  "+Email+"\n"+
                "        Password:         "+Password+"\n"+
                "        D.O.Birth:         "+DOB+"\n"+
                "        Gender:            "+Gender+"\n"+
                "        Department:     "+Department+"\n"
        );
    }
    
//   CollectValidInputs
    public void collectValidInputs() {

        FirstName = firstname_txt.getText().trim();
        LastName  = lastname_txt.getText().trim();
        Email     = emailComfirm_txt.getText().trim();
        Password  = new String(comfirmpassword_txt.getPassword());

        String year  = yearCombo.getSelectedItem().toString();
        String month = monthCombo.getSelectedItem().toString();
        String day   = dayCombo.getSelectedItem().toString();

        DOB = day + "-" + month + "-" + year;

        if (maleRadioBtn.isSelected()) {
            Gender = "Male";
        } else if (femaleRadioBtn.isSelected()) {
            Gender = "Female";
        }


        if (civilRadioBtn.isSelected()) {
            Department = "Civil";
        } else if (cseRadioBtn.isSelected()) {
            Department = "Computer Science & Engineering";
        } else if (electricalRadioBtn.isSelected()) {
            Department = "Electrical";
        } else if (ecRadioBtn.isSelected()) {
            Department = "Electronics & Communication";
        } else if (mechanicalRadioBtn.isSelected()) {
            Department = "Mechanical";
        }
    }

    
    public boolean isValidPassword(){
        password = password_txt.getText();
        
        if(password==null){
            return false;
        }
        
        Pattern pattern  = Pattern.compile(PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    public boolean isValidEmail(){
        email = email_txt.getText();
        
        if(email == null){
            return false;
        }
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public void comfirmPassword(){
        password = password_txt.getText();
        comfirmPassword = comfirmpassword_txt.getText().trim();
        
        if(!password.equals(comfirmPassword)){
            JOptionPane.showMessageDialog(this, "Password Doesn't Match", "ERROR", JOptionPane.ERROR_MESSAGE);
            comfirmpassword_txt.requestFocus();
        }else{
          Password = comfirmPassword;
        }
    }
    
    public void comfirmEmail(){
        email = email_txt.getText();
        comfirmEmail = emailComfirm_txt.getText().trim();
        
        if(!email.equals(comfirmEmail)){
            JOptionPane.showMessageDialog(this, "Email Address does't Match", "ERROR", JOptionPane.ERROR_MESSAGE);
            emailComfirm_txt.requestFocus();
        }else {
             Email = comfirmEmail;
        }
    }
    
    public boolean isEmptyField(){
        boolean valid = true;

        if(firstname_txt.getText().isEmpty()){
            errorLabel6.setText("This field is required!");
            errorLabel6.setForeground(Color.RED);
            valid = false;
        } else {
            FirstName = firstname_txt.getText().trim();
            errorLabel6.setText("");
        }

        if(lastname_txt.getText().isEmpty()){
            errorLabel1.setText("This field is required!");
            errorLabel1.setForeground(Color.RED);
            valid = false;
        } else {
            LastName = lastname_txt.getText().trim();
            errorLabel1.setText("");
        }

        if(email_txt.getText().isEmpty()){
            errorLabel2.setText("This field is required!");
            errorLabel2.setForeground(Color.RED);
            valid = false;
        } else {
            errorLabel2.setText("");
        }

        if(emailComfirm_txt.getText().isEmpty()){
            errorLabel4.setText("This field is required!");
            errorLabel4.setForeground(Color.RED);
            valid = false;
        } else {
            errorLabel4.setText("");
        }

        if(password_txt.getText().isEmpty()){
            errorLabel5.setText("This field is required!");
            errorLabel5.setForeground(Color.RED);
            valid = false;
        } else {
            errorLabel5.setText("");
        }

        if(comfirmpassword_txt.getText().isEmpty()){
            errorLabel3.setText("This field is required!");
            errorLabel3.setForeground(Color.RED);
            valid = false;
        } else {
            errorLabel3.setText("");
        }

        if(yearCombo.getSelectedItem().equals("Select Year")
            || monthCombo.getSelectedItem().equals("Select Month")
            || dayCombo.getSelectedItem().equals("Select Day")){
            dob_error_label.setText("Please Select All Age Fields.");
            dob_error_label.setForeground(Color.RED);
            valid = false;
        } else {
            dob_error_label.setText("");
        }

        return valid;
    }

    
    public boolean checkSelectedStatus(ButtonGroup buttonGroup){
        ButtonModel selectedModel = buttonGroup.getSelection();
        
        return selectedModel!=null;
    }
    
//    Radio Buttons
    public boolean checkRadioButton(){
        boolean valid = true;

        if(!checkSelectedStatus(genderGroup)){
            genderError_label.setText("Please Select your Gender.");
            genderError_label.setForeground(Color.RED);
            valid = false;
        } else {
            genderError_label.setText("");
        }

        if(!checkSelectedStatus(departmentGroup)){
            departmentError_label.setText("Please Select at least one Department!");
            departmentError_label.setForeground(Color.RED);
            valid = false;
        } else {
            departmentError_label.setText("");
        }

        return valid;
    }

    
    public void genderRadioGroup(){
        genderGroup.add(maleRadioBtn);
        genderGroup.add(femaleRadioBtn);
    }
    
    public void departmentRadioGroup(){
        departmentGroup.add(civilRadioBtn);
        departmentGroup.add(cseRadioBtn);
        departmentGroup.add(electricalRadioBtn);
        departmentGroup.add(ecRadioBtn);
        departmentGroup.add(mechanicalRadioBtn);
    }
    
    public final void loadYears(){
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int endYear = 2010;
        int startYear = 1966;
        String yr;
        
        for(int i = startYear; i<=endYear; i++){
            yr = String.valueOf(i);
            yearCombo.addItem(yr);
        }
    }
    
    public void loadMonths(){
        String stringMonth;
        for(Month month : Month.values()){
            stringMonth = month.toString();
            monthCombo.addItem(stringMonth);
        }
    }
    
    String selectedYear;
    String selectedMonth;
    
    public void loadDays(String enteredmonth, String enteredYear){
        int month = 1;        
        int numberOfDays = 0;
        
        switch(enteredmonth){
            case "JANUARY" -> month = 1;
            case "FEBRUARY" -> month = 2;
            case "MARCH" -> month = 3;
            case "APRIL" -> month = 4;
            case "MAY" -> month = 5;
            case "JUNE" -> month = 6;
            case "JULY" -> month = 7;
            case "AUGUST" -> month = 8;
            case "SEPTEMBER" -> month = 9;
            case "OCTOBER" -> month = 10;
            case "NOVEMBER" -> month = 11;
            case "DECEMBER" -> month = 12;
            default -> {
            }
                    
        }
      
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(enteredYear), month);
        
        numberOfDays = yearMonth.lengthOfMonth();
        
        for(int j = 1; j<=numberOfDays; j++ ){
            dayCombo.addItem(String.valueOf(j));
        }  
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        heading = new javax.swing.JLabel();
        firstname_label = new javax.swing.JLabel();
        firstname_txt = new javax.swing.JTextField();
        lastname_label = new javax.swing.JLabel();
        email_txt = new javax.swing.JTextField();
        emailAddress_label = new javax.swing.JLabel();
        lastname_txt = new javax.swing.JTextField();
        comfirmEmailAddress_label = new javax.swing.JLabel();
        emailComfirm_txt = new javax.swing.JTextField();
        password_label = new javax.swing.JLabel();
        password_txt = new javax.swing.JPasswordField();
        comfirmpassword_label = new javax.swing.JLabel();
        comfirmpassword_txt = new javax.swing.JPasswordField();
        dob_label = new javax.swing.JLabel();
        yearCombo = new javax.swing.JComboBox<>();
        monthCombo = new javax.swing.JComboBox<>();
        dayCombo = new javax.swing.JComboBox<>();
        maleRadioBtn = new javax.swing.JRadioButton();
        femaleRadioBtn = new javax.swing.JRadioButton();
        department_label = new javax.swing.JLabel();
        civilRadioBtn = new javax.swing.JRadioButton();
        electricalRadioBtn = new javax.swing.JRadioButton();
        cseRadioBtn = new javax.swing.JRadioButton();
        mechanicalRadioBtn = new javax.swing.JRadioButton();
        ecRadioBtn = new javax.swing.JRadioButton();
        submit_btn = new javax.swing.JButton();
        cancel_btn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayData_textArea = new javax.swing.JTextArea();
        errorLabel6 = new javax.swing.JLabel();
        errorLabel1 = new javax.swing.JLabel();
        errorLabel2 = new javax.swing.JLabel();
        errorLabel3 = new javax.swing.JLabel();
        errorLabel4 = new javax.swing.JLabel();
        errorLabel5 = new javax.swing.JLabel();
        dob_error_label = new javax.swing.JLabel();
        genderError_label = new javax.swing.JLabel();
        departmentError_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        heading.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        heading.setText("New Student Registration Form");

        firstname_label.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        firstname_label.setText("Student First Name");

        firstname_txt.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N

        lastname_label.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        lastname_label.setText("Student Last Name");

        email_txt.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        email_txt.addActionListener(this::email_txtActionPerformed);
        email_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                email_txtKeyTyped(evt);
            }
        });

        emailAddress_label.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        emailAddress_label.setText("Email Address");

        lastname_txt.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        lastname_txt.addActionListener(this::lastname_txtActionPerformed);

        comfirmEmailAddress_label.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        comfirmEmailAddress_label.setText("Comfirm Email Address");

        emailComfirm_txt.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        emailComfirm_txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailComfirm_txtMouseClicked(evt);
            }
        });
        emailComfirm_txt.addActionListener(this::emailComfirm_txtActionPerformed);

        password_label.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        password_label.setText("Password");

        password_txt.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        password_txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                password_txtMouseClicked(evt);
            }
        });
        password_txt.addActionListener(this::password_txtActionPerformed);

        comfirmpassword_label.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        comfirmpassword_label.setText("Comfirm Password");

        comfirmpassword_txt.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        comfirmpassword_txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comfirmpassword_txtMouseClicked(evt);
            }
        });

        dob_label.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        dob_label.setText("Date of Birth");

        yearCombo.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        yearCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year" }));
        yearCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yearComboMouseClicked(evt);
            }
        });
        yearCombo.addActionListener(this::yearComboActionPerformed);

        monthCombo.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        monthCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month" }));
        monthCombo.addActionListener(this::monthComboActionPerformed);

        dayCombo.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        dayCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Day" }));

        maleRadioBtn.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        maleRadioBtn.setText("Male");

        femaleRadioBtn.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        femaleRadioBtn.setText("Female");

        department_label.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        department_label.setText("Department");

        civilRadioBtn.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        civilRadioBtn.setText("Civil");

        electricalRadioBtn.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        electricalRadioBtn.setText("Electrical");

        cseRadioBtn.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        cseRadioBtn.setText("Computer Science & Engineering");

        mechanicalRadioBtn.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        mechanicalRadioBtn.setText("Mechanical");

        ecRadioBtn.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        ecRadioBtn.setText("Electronics & Communication");

        submit_btn.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        submit_btn.setText("Submit");
        submit_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submit_btnMouseClicked(evt);
            }
        });

        cancel_btn.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        cancel_btn.setText("Cancel");
        cancel_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancel_btnMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel10.setText("Your Data is Below:");

        displayData_textArea.setEditable(false);
        displayData_textArea.setColumns(20);
        displayData_textArea.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        displayData_textArea.setRows(5);
        displayData_textArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(displayData_textArea);

        errorLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N

        errorLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N

        errorLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N

        errorLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N

        errorLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N

        errorLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(firstname_label)
                                    .addComponent(lastname_label)
                                    .addComponent(emailAddress_label)
                                    .addComponent(password_label)
                                    .addComponent(comfirmpassword_label)
                                    .addComponent(dob_label)
                                    .addComponent(department_label))
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(comfirmEmailAddress_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(emailComfirm_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(password_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comfirmpassword_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(monthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(maleRadioBtn)
                                        .addGap(29, 29, 29)
                                        .addComponent(femaleRadioBtn)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(errorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(errorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(errorLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(errorLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(dayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(errorLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(70, 70, 70))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lastname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(errorLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(firstname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(civilRadioBtn)
                                            .addComponent(cseRadioBtn)
                                            .addComponent(electricalRadioBtn)
                                            .addComponent(ecRadioBtn)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(submit_btn)
                                                .addGap(50, 50, 50)
                                                .addComponent(cancel_btn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(departmentError_label, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(mechanicalRadioBtn))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(224, 224, 224)
                                                .addComponent(dob_error_label, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(genderError_label, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(15, 15, 15))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(heading)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(heading)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstname_label)
                    .addComponent(firstname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastname_label)
                    .addComponent(lastname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(email_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailAddress_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comfirmEmailAddress_label))
                    .addComponent(emailComfirm_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(dob_label)
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(errorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(password_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addComponent(errorLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comfirmpassword_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comfirmpassword_label)))
                            .addComponent(password_label))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(monthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(errorLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(maleRadioBtn)
                                    .addComponent(femaleRadioBtn))
                                .addGap(16, 16, 16)
                                .addComponent(civilRadioBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(department_label)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cseRadioBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(electricalRadioBtn)
                        .addGap(18, 18, 18)
                        .addComponent(ecRadioBtn)
                        .addGap(18, 18, 18)
                        .addComponent(mechanicalRadioBtn)
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(departmentError_label, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(submit_btn)
                                    .addComponent(cancel_btn))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dob_error_label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(genderError_label, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addGap(83, 83, 83))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void yearComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearComboActionPerformed
        selectedYear = yearCombo.getSelectedItem().toString();
        loadMonths();
    }//GEN-LAST:event_yearComboActionPerformed

    private void monthComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthComboActionPerformed
        selectedMonth = monthCombo.getSelectedItem().toString();
        dayCombo.removeAllItems();
        dayCombo.addItem("Select Day");
        loadDays(selectedMonth, selectedYear);
    }//GEN-LAST:event_monthComboActionPerformed

    private void emailComfirm_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailComfirm_txtActionPerformed
        
    }//GEN-LAST:event_emailComfirm_txtActionPerformed

    private void email_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_txtActionPerformed
        boolean isValidEmail = isValidEmail();
        
        if(isValidEmail==false){
            JOptionPane.showMessageDialog(this, "Enter a valid Email Address", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_email_txtActionPerformed

    private void email_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_email_txtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_email_txtKeyTyped

    private void emailComfirm_txtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailComfirm_txtMouseClicked
        boolean isValidEmail = isValidEmail();
        
        if(isValidEmail==false){
            JOptionPane.showMessageDialog(this, "Enter a valid Email Address", "ERROR", JOptionPane.ERROR_MESSAGE);
            email_txt.requestFocus();
        }
    }//GEN-LAST:event_emailComfirm_txtMouseClicked

    private void password_txtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_password_txtMouseClicked
        comfirmEmail();
    }//GEN-LAST:event_password_txtMouseClicked

    private void comfirmpassword_txtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comfirmpassword_txtMouseClicked
        boolean isValidPassword = isValidPassword();
        
        if(isValidPassword == false){
            JOptionPane.showMessageDialog(this, "Enter a stronger Password.\n(8-20 characters, contain at least 1 letter and 1 digit)");
            password_txt.requestFocus();
        }
    }//GEN-LAST:event_comfirmpassword_txtMouseClicked

    private void yearComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yearComboMouseClicked
        comfirmPassword();
    }//GEN-LAST:event_yearComboMouseClicked

    private void cancel_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancel_btnMouseClicked
        this.dispose();
    }//GEN-LAST:event_cancel_btnMouseClicked

    private void lastname_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastname_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastname_txtActionPerformed

    private void submit_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submit_btnMouseClicked
        boolean fieldsOk = isEmptyField();
        boolean radiosOk = checkRadioButton();

        if(fieldsOk && radiosOk){
            comfirmEmail();
            comfirmPassword();
            generateID();
            collectValidInputs();
            GetInputedData();   // ✅ only runs if ALL checks pass
            SaveToCSV();
        } 
//        else {
//            displayData_textArea.setText(""); // optional: clear old data
//        }
    }//GEN-LAST:event_submit_btnMouseClicked

    private void password_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password_txtActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new StudentForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cancel_btn;
    private javax.swing.JRadioButton civilRadioBtn;
    private javax.swing.JLabel comfirmEmailAddress_label;
    private javax.swing.JLabel comfirmpassword_label;
    private javax.swing.JPasswordField comfirmpassword_txt;
    private javax.swing.JRadioButton cseRadioBtn;
    private javax.swing.JComboBox<String> dayCombo;
    private javax.swing.JLabel departmentError_label;
    private javax.swing.JLabel department_label;
    private javax.swing.JTextArea displayData_textArea;
    private javax.swing.JLabel dob_error_label;
    private javax.swing.JLabel dob_label;
    private javax.swing.JRadioButton ecRadioBtn;
    private javax.swing.JRadioButton electricalRadioBtn;
    private javax.swing.JLabel emailAddress_label;
    private javax.swing.JTextField emailComfirm_txt;
    private javax.swing.JTextField email_txt;
    private javax.swing.JLabel errorLabel1;
    private javax.swing.JLabel errorLabel2;
    private javax.swing.JLabel errorLabel3;
    private javax.swing.JLabel errorLabel4;
    private javax.swing.JLabel errorLabel5;
    private javax.swing.JLabel errorLabel6;
    private javax.swing.JRadioButton femaleRadioBtn;
    private javax.swing.JLabel firstname_label;
    private javax.swing.JTextField firstname_txt;
    private javax.swing.JLabel genderError_label;
    private javax.swing.JLabel heading;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lastname_label;
    private javax.swing.JTextField lastname_txt;
    private javax.swing.JRadioButton maleRadioBtn;
    private javax.swing.JRadioButton mechanicalRadioBtn;
    private javax.swing.JComboBox<String> monthCombo;
    private javax.swing.JLabel password_label;
    private javax.swing.JPasswordField password_txt;
    private javax.swing.JButton submit_btn;
    private javax.swing.JComboBox<String> yearCombo;
    // End of variables declaration//GEN-END:variables
}
