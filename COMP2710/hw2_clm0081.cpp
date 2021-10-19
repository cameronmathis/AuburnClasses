//Cameron Mathis
////clm0081
////hw2.cpp
////The program calculates the loan amount left and payment due
////I received help from class slides.
#include <iostream>

using namespace std;

int main()
{
    int n = -1;
    int month = 0;
    double balance = -99;
    double payment= -99;
    double rate= -99;
    double interest= -99;
    double principal = -99;
    double totalInterest = 0;

    cout.setf(ios::fixed);
    cout.setf(ios::showpoint);
    cout.precision(2);
    
    while (n == -1) {
        while (balance == -99) {
            cout << "Loan Amount: ";
            cin >> balance;
	    if (balance < 0) {
	        cout << "Invalid Input!\n";
	        balance = -99;
	    }
        }
        while (rate == -99) {
    	    cout << "Interest Rate (\% per year): ";
 	    cin >> rate;
	    rate = rate/12;
	    if (rate < 0) {
	        cout << "Invalid Input!\n";
	        rate = -99;
	    }
        }
        while (payment == -99) {
            cout << "Monthly Payments: ";
            cin >> payment;
	    if (payment < 0) {
                cout << "Invalid Input!\n";
	        payment = -99;
	    }
	    cout << "\n";
    	}

    	interest = balance * .01 * rate;
    	if (interest > payment) {
	    cout << "Payment not enough to cover interest. Please enter new payment information.\n\n";
	    balance = -99;
	    rate = -99;
	    payment = -99;
    	} else {
	    n = 1;
	}
    }

    cout << "******************************************************\n";
    cout << "        Amoritization Table\n";
    cout << "******************************************************\n";
    cout << "Month\tBalance\t\tPayment\tRate\tInterest Principal\n";
    if (balance > 999.999) {
        cout << "0\t$" << balance << "\tN/A\tN/A\tN/A\t N/A\n";
    } else {
        cout << "0\t$" << balance << "\t\tN/A\tN/A\tN/A\t N/A\n";
    }
    
    while (balance > 0) {
	month = ++month;
        interest = balance * .01 * rate;
	totalInterest += interest;
	if (balance < payment) {
	    payment = balance + interest;
	    principal = balance;
            balance = 0;
	} else {
	    principal = payment - interest;
            balance -= principal;
	}
	if (balance > 999.999) {
	    cout << month << "\t$" << balance << "\t$" << payment << "\t";
	} else {
	    cout << month << "\t$" << balance << "\t\t$" << payment << "\t";
	}
	cout.precision(1);
	cout << rate <<"\t$";
	cout.precision(2);
	cout << interest << "\t $" << principal << "\n";
    }

    cout << "******************************************************\n\n\n";
    cout << "It takes " << month << " months to pay off the loan.\n";
    cout << "Total interest paid is: $" << totalInterest << "\n"; 
}
