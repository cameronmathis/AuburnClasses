//Cameron Mathis
//clm0081
//hw1.cpp
//The program calculates the fatal amount of diet coke
//I received help from Chapter 1 and Chapter 2 slides.

#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    const double AMOUNT_OF_SWEETENER = 0.001;
    double mouseWeight = -99.99;
    double fatalAmountForMouse = -99.99;
    double friendWeight = -99.99;
    double fatalAmountForFriend = -99.99;

    while (mouseWeight == -99.99) {
        cout << "Please input the weight of the mouse in kg\n";
        cin >>  mouseWeight;

        if (mouseWeight <= 0) {
	    cout << "Input invalid\n";
 	    mouseWeight = -99.99;
	}
    }

    while (fatalAmountForMouse  == -99.99) {
    	cout << "Please input the fatal amount of sweetener for mouse in kg\n";
 	cin >> fatalAmountForMouse;

	if (fatalAmountForMouse < 0) {
	    cout << "Input invalid\n";
	    fatalAmountForMouse = -99.99;
	}
    }

    while (friendWeight == -99.99) {
    	cout << "Please input the weight of your dear friend in kg\n";
    	cin >> friendWeight;

	if (friendWeight <= 0) {
	    cout << "Invalid input\n";
	    friendWeight = -99.99;
        }
    }

    fatalAmountForFriend = (fatalAmountForMouse * friendWeight) / (mouseWeight * AMOUNT_OF_SWEETENER);

    cout << "The fatal amount of Coke for your freind is: " << fatalAmountForFriend << " kg\n";
}
