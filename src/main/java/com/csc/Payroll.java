package com.csc;

import java.util.Scanner;

public class Payroll {

  // Variables and Constants used across Payroll Functions
  double hours = 0;
  int dependents = 0;
  final double HOURRATE = 16.78;

  // Constants for Taxes Function
  final double SOCSECTAX = 0.06;
  final double FEDTAX = 0.14;
  final double STATAX = 0.05;

  // Constants for Fees Function
  final double UNIONDUES = 10.00;
  final int INSURANCE3FEE = 35;
  final int INSURANCEFEE = 15;

  public Payroll(double hours, int dependents) {
    this.hours = hours;
    this.dependents = dependents;
  }

  // Calculation Function for the Hourly Rate
  public double HourRate() {
    double hourRate;
    if (hours > 40) {
      double overtime = hours - 40;
      hourRate = ((HOURRATE * hours) + (HOURRATE * overtime * 0.5)) / (hours);
    }
    else {
      hourRate = HOURRATE;
    }
    return hourRate;
  }

  // Calculation Function for Gross Earnings
  public double GrossEarnings() {
    double grossEarnings;
    if (hours > 40) {
      double overtime = hours - 40;
      grossEarnings = HOURRATE * (overtime * 0.5 + hours);
    }
    else {
      grossEarnings = (HOURRATE * hours);
    }
      return grossEarnings;
  } 

  //Calculation Function for Taxes
  public double Taxes() {
    double grossEarnings = GrossEarnings();
    double socsecTax = grossEarnings * SOCSECTAX;
    double fedTax = grossEarnings * FEDTAX;
    double staTax = grossEarnings * STATAX;
    return socsecTax + fedTax + staTax;
  }

  //Calculation Function for Fees
  public double Fees() {
    double insuranceFee = INSURANCEFEE;
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
  scanner.close();

  Payroll payroll = new Payroll(hours, dependents);

  //Printing the results out
  System.out.println("Hours worked:   " + String.format("%5.2f", payroll.hours));
  System.out.println("Hourly rate:    " + String.format("$%5.2f", payroll.HourRate()));
  System.out.println("Gross earnings: " + String.format("$%5.2f", payroll.GrossEarnings()));
  System.out.println("Total expenses: " + String.format("$%5.2f", payroll.Expenses()));
  System.out.println("Net pay:        " + String.format("$%5.2f", payroll.NetPay()));
}
}
