/*******
	COMP 4300
	Cameron Mathis
	Project 2 - Attempt 2
	11/03/20
	Register Simulation
********/

#include <iostream>

#define REGISTER_LENGTH  32

using namespace std;

typedef uint32_t registerAddress;

registerAddress registers[REGISTER_LENGTH];

class RegisterBank {
	public:
		RegisterBank();
    	bool writeToRegister(registerAddress registerAddressIndex, registerAddress data);			
    	registerAddress readFromRegister(registerAddress registerAddressIndex);												
};

RegisterBank::RegisterBank() {}

/* Writes to registers */
bool RegisterBank::writeToRegister(registerAddress registerAddressIndex, registerAddress data) {
	if (registerAddressIndex <= REGISTER_LENGTH) {
		registers[registerAddressIndex] = data;
		return true;
	}
	cout << "Error with register write." << endl;
	return false;
}

/* Reads from registers */
registerAddress RegisterBank::readFromRegister(registerAddress registerAddressIndex ) {
	if (registerAddressIndex <= REGISTER_LENGTH) {
		return registers[registerAddressIndex];
	}
	cout << "Error with register read." << endl;
	return 0;
}
