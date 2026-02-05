/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gradesystem;

import java.util.Scanner;

//declare class
public class beforemof {
    //main method, starting #point of program
    public static void main(String[] args){
        
        //create scanner object
        //allows alues entered by user to be read
        Scanner scanner = new Scanner(System.in);
        
        //creating a variable, declare variable
        int score;
        int grade;
        String remark;

        //prompt user input
        System.out.print("Enter student score out of 100: ");
        //assign score and use scanner object to accept user input
        score = scanner.nextInt();


        //if statement
        if (score >= 80 && score <= 100) {
        grade = 1;
        remark = "D1";
        } else if (score >= 75) {
        grade = 2;
        remark = "D2";
        } else if (score >= 66) {
        grade = 3;
        remark = "C3";
        } else if (score >= 60) {
        grade = 4;
        remark = "C4";
        } else if (score >= 50) {
        grade = 5;
        remark = "C5";
        } else if (score >= 45) {
        grade = 6;
        remark = "C6";
        } else if (score >= 35) {
        grade = 7;
        remark = "P7";
        } else if (score >= 30) {
        grade = 8;
        remark = "P8";
        } else if(score >=0){
        grade = 9;
        remark = "F";
        } else{
        grade = 0;
        remark = "ungraded";
        }


        System.out.println("Score: " + score);
        System.out.println("Grade: " + grade);
        System.out.println("Remark: " + remark);


        scanner.close();

    
    }
    
}
