/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.skmotors;

import java.util.Scanner;

public class Skmotors {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        String regNo;
        double vechileCost;
        double deposits;
        double expenses;

        System.out.print("Enter vehicle registration number: ");
        regNo = scanner.next();


        System.out.print("Enter vehicle cost: ");
        vechileCost = scanner.nextDouble();


        System.out.print("Enter total deposits made: ");
        deposits = scanner.nextDouble();


        System.out.print("Enter total expenses: ");
        expenses = scanner.nextDouble();

        //declare and initalize highest bid
        double highestBid = 0;

       //start for loop
        for (int i = 1; i <= 3; i++) {
        System.out.print("Enter bid amount from bidder " + i + ": ");
        double bid = scanner.nextDouble();

//compare bid
        if (bid > highestBid) {
        highestBid = bid;
        }
        }


        double balance = highestBid - deposits;
        double profitLoss = highestBid - (vechileCost + expenses);


        System.out.println("\nVehicle Registration: " + regNo);
        System.out.println("Highest Bid: " + highestBid);
        System.out.println("Balance to be paid: " + balance);


        if (profitLoss > 0) {
        System.out.println("Profit made: " + profitLoss);
        } else {
        System.out.println("Loss made: " + profitLoss);
        }


        scanner.close();
    }
}
