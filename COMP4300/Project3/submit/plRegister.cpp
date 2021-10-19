/*******
	COMP 4300
	Cameron Mathis
	Project 3
	11/29/20
	Register Simulation -- exact same as project 2
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

/* Writes to registers -- exact same as project 2 */
bool RegisterBank::writeToRegister(registerAddress registerAddressIndex, registerAddress data) {
	if (registerAddressIndex <= REGISTER_LENGTH) {
		registers[registerAddressIndex] = data;
		return true;
	}
	cout << "Error with register write." << endl;
	return false;
}


/* Reads from registers -- exact same as project 2 */
registerAddress RegisterBank::readFromRegister(registerAddress registerAddressIndex ) {
	if (registerAddressIndex <= REGISTER_LENGTH) {
		return registers[registerAddressIndex];
	}
	cout << "Error with register read." << endl;
	return 0;
}
