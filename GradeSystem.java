/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gradesystem;

import java.util.Scanner;

//declare the class
public class GradeSystem {
    public static void main(String[] args) {
        
        int score;
        int grade;
        String remark;
        int count = 0;
        
        //create variable using an array for count of each student grade 1-9
        //create one array variable
        int[] gradeCount = new int[10];
        
        Scanner scanner = new Scanner(System.in);
        
        //while loop which runs 5tyms
        while(count<=5){
        System.out.print("Enter student score out of 100: "+(count+1)+": ");
            //assign score and use scanner object to read data entered and store it in score
            score = scanner.nextInt();
        
            if (score >=80 && score <= 100) {
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
            } else if (score >= 0) {
            grade = 9;
            remark = "F";
            } else{
              grade = 0;
              remark = "ungraded";
            }
            
            //count grade
            if(grade >=1 && grade <=9){
                //increase grade count
                gradeCount[grade]++;
            }
            
            System.out.println("Student"+(count+1)+"Score; "+score+"Grade; "+grade+"Remark; "+remark);
            
            count++;
        }
        System.out.println("--------Grade Summary--------");
        for(int i = 1; i<=9; i++){
        System.out.println("Grade " + i + ": " + gradeCount[i] + " student(s)");
        }
        //System.out.println("Score: " + score);
        //System.out.println("Grade: " + grade);
        //System.out.println("Remark: " + remark);


        scanner.close();
    }
}
