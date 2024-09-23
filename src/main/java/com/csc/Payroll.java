package com.csc;

import java.util.Scanner;

public class Payroll {

  // Variables and Constants used across Payroll Functions
  double hours = 0;
  int dependents = 0;
  double inputRate = 16.78;

  // Constants and Variables for Taxes Function
  final double SOCSECTAX = 0.06;
  final double FEDTAX = 0.14;
  final double STATAX = 0.05;
  double socsecTax;
  double fedTax;
  double staTax;

  // Constants and Variables for Fees Function
  final double UNIONDUES = 10.00;
  final int INSURANCE3FEE = 35;
  final int INSURANCEFEE = 15;
  double insuranceFee;

  public Payroll(double hours, int dependents) {
    this.hours = hours;
    this.dependents = dependents;
  }

  // Calculation Function for the Hourly Rate
  public double HourRate() {
    double hourRate;
    if (hours > 40) {
      double overtime = hours - 40;
      hourRate = ((inputRate * hours) + (inputRate * overtime * 0.5)) / (hours);
    }
    else {
      hourRate = inputRate;
    }
    return hourRate;
  }

  // Calculation Function for Gross Earnings
  public double GrossEarnings() {
    double grossEarnings;
    if (hours > 40) {
      double overtime = hours - 40;
      grossEarnings = inputRate * (overtime * 0.5 + hours);
    }
    else {
      grossEarnings = (inputRate * hours);
    }
      return grossEarnings;
  } 

  //Calculation Function for Taxes
  public double Taxes() {
    double grossEarnings = GrossEarnings();
    socsecTax = grossEarnings * SOCSECTAX;
    fedTax = grossEarnings * FEDTAX;
    staTax = grossEarnings * STATAX;
    return socsecTax + fedTax + staTax;
  }

  //Calculation Function for Fees
  public double Fees() {
    insuranceFee = INSURANCEFEE;
    if (dependents >= 3) {
      insuranceFee = INSURANCE3FEE;
    }
    return insuranceFee + UNIONDUES;
  }

  // Calculation Function for Expenses
  public double Expenses() {
    double fees = Fees();
    double taxes = Taxes();
    return fees + taxes;
  }

  //Calculation Function for Net Pay
  public double NetPay() {
    double grossEarnings = GrossEarnings();
    double expenses = Expenses();
    return grossEarnings - expenses;
  }

public static void main(String[] args) {

  //Initial Prompting the User
  Scanner scanner = new Scanner(System.in);

  System.out.print("Enter the amount of hours worked this week: ");
  double hours = scanner.nextDouble();
  System.out.print("Enter the amount of dependents for insurance purposes: ");
  int dependents = scanner.nextInt();

  // New input for the User's hourly rate
  Payroll payroll = new Payroll(hours, dependents);
  System.out.print("Enter the hourly rate you earn: $");
  payroll.inputRate = scanner.nextDouble();
  
  //New Checker that the User's hourly rate is positive
  while (payroll.inputRate < 0) {
    System.out.print("Please input an hourly rate you earn that is $0.00 or greater: $");
    payroll.inputRate = scanner.nextDouble();
  }
  scanner.close();
  

  //Printing the results out
  System.out.println("Hours worked:        " + String.format("%4.2f", payroll.hours));
  System.out.println("Hourly rate:         " + String.format("$%5.2f", payroll.HourRate()));
  System.out.println("Gross earnings:      " + String.format("$%5.2f", payroll.GrossEarnings()));
  payroll.Taxes();
  payroll.Fees();
  System.out.println("Social Security Tax: " + String.format("$%5.2f", payroll.socsecTax));
  System.out.println("State Tax:           " + String.format("$%5.2f", payroll.staTax));
  System.out.println("Federal Tax:         " + String.format("$%5.2f", payroll.fedTax));

  // New check to ensure Fees are payed and User doesn't have a net negative
  if (payroll.NetPay() < 0) {
    double newNet = payroll.NetPay() + payroll.insuranceFee + payroll.UNIONDUES;
    System.out.println("Net pay:             " + String.format("$%5.2f", newNet));
    System.out.println("You still owe:");
    System.out.println("Insurance Fee        " + String.format("$%5.2f", payroll.insuranceFee));
    System.out.println("Union Dues:          " + String.format("$%5.2f", payroll.UNIONDUES));
  }
  else {
  System.out.println("Insurance Fee        " + String.format("$%5.2f", payroll.insuranceFee));
  System.out.println("Union Dues:          " + String.format("$%5.2f", payroll.UNIONDUES));
  System.out.println("Net pay:             " + String.format("$%5.2f", payroll.NetPay()));
  }
}
}
