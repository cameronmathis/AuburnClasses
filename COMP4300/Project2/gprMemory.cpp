/*******
	COMP 4300
	Cameron Mathis
	Project 2 - Attempt 2
	11/03/20
	Memory Simulation
********/

#include <iostream>
#include <fstream>
#include <math.h>
#include <cstring>

#define TEXT_LENGTH  50
#define	DATA_LENGTH  50
#define STACK_LENGTH  50

using namespace std;

typedef uint32_t memoryAddress;
typedef uint32_t instruction;

memoryAddress textTop = 0x00001000;
memoryAddress dataTop = 0x00002000;
memoryAddress stackTop = 0x00003000;

instruction textSegment[TEXT_LENGTH];
memoryAddress dataSegment[DATA_LENGTH];
memoryAddress stackSegment[STACK_LENGTH];

class Memory {
	public:
		Memory();
		bool loadCode(memoryAddress memoryAddressIndex);					
    	bool loadData(memoryAddress memoryAddressIndex, char stringToBeStored[]);				
    	memoryAddress * readFromMemory(memoryAddress memoryAddressIndex);
    	string readStringFromMemory(memoryAddress memoryAddress);					
    	memoryAddress readByte(memoryAddress memoryAddressIndex, int byte);		
	private:
		int decodeAddressBin(memoryAddress memoryAddressIndex);				
		int decodeAddressIndex(memoryAddress memoryAddressIndex);	
		// Counter for textSegment		
		int textNextOpenMemoryLocation;								
		int getLengthOfString(memoryAddress memoryAddressIndex, int maxLength);
		memoryAddress getMemoryByte(instruction dataInput, int byteNumber);			
		memoryAddress memoryByteString(instruction dataInput, int byteNumber);  
};

/*******
	Class Definition 
********/

/* Initialize memory -- modified from project 1 */
Memory::Memory() {
	textNextOpenMemoryLocation = -1;
	int hexidecimalOne;
	int hexidecimalTwo;
	int hexidecimalThree;
	string lineOne;
	string lineTwo = "0000000000";
	char dataArray[DATA_LENGTH];
	memset(dataArray, '\0', sizeof(dataArray) / sizeof(dataArray[0]));
	int i = 0;
	ifstream gprFileCode("palindrome.s");
	if (gprFileCode.is_open()) {
		while (getline(gprFileCode, lineOne)) {
			if (lineOne == "") {
				continue;
			}
			if (lineOne == ".text") {
				continue;
			}
			if (lineOne == ".data") {
				i = 1; 
				continue;
			}	
			// Text
			if (i == 0)	{	// Store lineOne as hexidecimal
				sscanf(lineOne.data(),"%x", &hexidecimalOne);
				loadCode(hexidecimalOne);
			}
			// Data
			if (i == 1) {	
				for (int ch = 0; ch < 10; ch++) {
					lineTwo[ch] = lineOne[ch];
				} // Store lineTwo as hexidecimal
				sscanf(lineTwo.data(), "%x", &hexidecimalTwo);
				// Store lineThree as hexidecimal
				hexidecimalTwo = std::stoi(lineTwo.c_str(), 0, 16);
				for (int ch = 0; ch < DATA_LENGTH - 1; ch++) {
					dataArray[ch] = lineOne[ch+11];
				}
				loadData(hexidecimalTwo, dataArray);
			}	
		}
	} else {
		cout << "Error: Unable to open file."; 
	}
	gprFileCode.close();
}

/* Loads from .text section -- exact same as project 1 */
bool Memory::loadCode(memoryAddress memoryAddressIndex) {
	textNextOpenMemoryLocation++;
	// Checks memory length										
	if (textNextOpenMemoryLocation < TEXT_LENGTH) {	// Stores instruction
		textSegment[textNextOpenMemoryLocation] = memoryAddressIndex;	
		return true;
	} else { // No More memory open
		cout << "Error: Please expand space for Text Memory." << endl;
		return false;														
	}
}

/* Write the given data string to memory -- modified from project 1 */
bool Memory::loadData(memoryAddress memoryAddressIndex, char stringToBeStored[]) {
	if (decodeAddressBin(memoryAddressIndex) == 2) {
		int dataIndex = decodeAddressIndex(memoryAddressIndex);
		// Checks the memory length
		if (dataIndex < DATA_LENGTH) {
			// Fill data segment with 0s
			memset(&dataSegment[dataIndex], 0, strlen(stringToBeStored)+5);
			// Store string in data segment
			memcpy(&dataSegment[dataIndex], stringToBeStored,  strlen(stringToBeStored)+1);
			return true;									
		}
		return false;
	}
	cout << "There was an error loading your data into memory." << endl;
	return false;
}

/* Read the memory at the given memory address -- exact same as project 1 */
memoryAddress * Memory::readFromMemory(memoryAddress memoryAddressIndex) {	
	memoryAddress memoryCopyBin = memoryAddressIndex;
	memoryAddress memoryCopyIndex = memoryAddressIndex;
	switch(decodeAddressBin(memoryCopyBin)) {
		case 1: {
			int memoryIndex = (int) decodeAddressIndex(memoryCopyIndex);	
			// Checks text memory length								
			if (memoryIndex < TEXT_LENGTH)	{									
				return &textSegment[memoryIndex];
			}
		} break;
		case 2: {
			int memoryIndex = (int) decodeAddressIndex(memoryCopyIndex);
			// Checks data memory length
			if (memoryIndex < DATA_LENGTH)	{										
				return &dataSegment[memoryIndex];									
			}
		} break;
		case 3: {
			int memoryIndex = (int) decodeAddressIndex(memoryCopyIndex);
			// Checks stack memory length
			if (memoryIndex < STACK_LENGTH) {									
				return &stackSegment[memoryIndex];									
			}
		} break;
		default: {
			cout << "Error: Memory read is not within current memory." << endl;
			return &stackTop;														
		} break;
	}
	cout << "Error: Memory read went wrong." << endl;
	return &stackTop;
}

/* Decodes address into bin -- modified from project 1 */
int Memory::decodeAddressBin(memoryAddress memoryAddressIndex) {		
	// Shifts all bits to the left	16													
	memoryAddressIndex = memoryAddressIndex << 16;
	// Shifts all bits to the right 28
	memoryAddressIndex = memoryAddressIndex >> 28;
	return memoryAddressIndex;
	// 0 = kernal, 1 = text, 2 = data, 3 = stack, and (-1) = error
}

/* Decodes address into array index -- modified from project 1 */
int Memory::decodeAddressIndex(memoryAddress memoryAddressIndex) {
	// Shifts all bits to the left 20														
	memoryAddressIndex = memoryAddressIndex << 20;
	// Shifts all bits to the right 20
	memoryAddressIndex = memoryAddressIndex >> 20;
	return memoryAddressIndex;
}

/* Read a string from memory */
string Memory::readStringFromMemory(memoryAddress memoryAddress) {	
	switch(decodeAddressBin(memoryAddress)) {
		case 1: { // TEXT  
			cout << "There was an error loading your string into memory." << endl;
			return "Error";
		}
		case 2: { // DATA
			int dataIndex = decodeAddressIndex(memoryAddress);
			char *dataOutput;
			dataOutput = (char*) malloc(getLengthOfString(memoryAddress, 2000));
			if (dataIndex < DATA_LENGTH) {
				memcpy(dataOutput, &dataSegment[dataIndex], getLengthOfString(memoryAddress, 2000));
				return string(dataOutput);									
			}
			return "Error";
		}
		case 3: { // STACK
			int dataIndex = decodeAddressIndex(memoryAddress);
			char *dataOutput;
			dataOutput = (char*) malloc(getLengthOfString(memoryAddress, 2000));
			if (dataIndex < STACK_LENGTH) {
				memcpy(dataOutput, &stackSegment[dataIndex], getLengthOfString(memoryAddress, 2000));
				return string(dataOutput);									
			}
			return "Error";
		}
		default: {
			cout << "There was an error loading your string into memory." << endl;
			return "Error";
		}
	}
	cout << "There was an error loading your string into memory." << endl;
	return "Error";
}

/* Used to read a byte at the given address */
memoryAddress Memory::readByte(memoryAddress memoryAddressIndex, int byte) {
	memoryAddress memoryCopyBin = memoryAddressIndex;
	memoryAddress memoryCopyIndex = memoryAddressIndex;
	int memoryIndex = (int) floor(decodeAddressIndex(memoryCopyIndex) / 4.0);
	memoryAddress memoryValue = 0;
	switch(decodeAddressBin(memoryCopyBin)) {
		case 1: { // Checks text memory length			
			if (memoryIndex < TEXT_LENGTH) {
				memoryValue = textSegment[memoryIndex];
			}
		} break;
		case 2: { // Checks data memory length
			if (memoryIndex < DATA_LENGTH)	{
				memoryValue = dataSegment[memoryIndex];									
			}
		} break;
		case 3: { // Checks stack memory length
			if (memoryIndex < STACK_LENGTH) {
				memoryValue = stackSegment[memoryIndex];									
			}
		} break;
		default: {
			cout << "Error: Memory read is not within current memory." << endl;
			memoryValue = stackTop;													
		} break;
	}
	return memoryByteString(memoryValue, byte + 1);
}

/* Finds the end of a string in memory */
int Memory::getLengthOfString(memoryAddress memoryAddressIndex, int maxLength) {
	switch(decodeAddressBin(memoryAddressIndex)) {
		case 1: { // TEXT 
			cout << "There was an error finding the length of your string in memory." << endl;
			return 0;
		}
		case 2: { // DATA
			int dataIndex = decodeAddressIndex(memoryAddressIndex);
			// Checks data memory length
			if (dataIndex < DATA_LENGTH) {
				int length = 0;
				bool isEndFound = false;
				memoryAddress currentByte = 0;
				while(!isEndFound && length < maxLength) {
					currentByte = getMemoryByte(dataSegment[dataIndex], 1 + (length%4));
					if(0 == currentByte) {
						isEndFound = true;
					}
					length++;
				}
				return length--;
			}
			return 0;
		}
		case 3: { // STACK
			int dataIndex = decodeAddressIndex(memoryAddressIndex);
			// Checks stack memory length
			if (dataIndex < STACK_LENGTH) {
				int length = 0;
				bool isEndFound = false;
				memoryAddress currentByte = 0;
				while(!isEndFound && length < maxLength) {
					currentByte = getMemoryByte(stackSegment[dataIndex], 1 + (length%4));
					if(0 == currentByte) {
						isEndFound = true;
					}
					length++;
				}
				return length--;
			}
			return 0;
		}
		default: {
			cout << "There was an error finding the length of your string in memory." << endl;
			return 0;
		}
	}
	cout << "There was an error finding the length of your string in memory." << endl;
	return 0;
}

/* Returns a byte inside the instruction */
memoryAddress Memory::getMemoryByte(instruction dataInput, int byteNumber) {																					
	if (byteNumber < 5 && byteNumber > 0) {
		byteNumber--;
		instruction data;
		data = dataInput;
		data = data << 8*byteNumber;
		data = data >> 24;
		return data;
	}
	cout << "Error reading from memory." << endl;
	return 0;
} 

/* Returns a byte for a string */
memoryAddress Memory::memoryByteString(instruction dataInput, int byteNumber) {																					
	if (byteNumber < 5 && byteNumber > 0) {
		byteNumber--;
		switch(byteNumber)	{ // Had to be done because of big Indian to little Indian flip
			case 0: {
				byteNumber = 3;
				break;
			}
			case 1: {
				byteNumber = 2;
				break;
			}
			case 2: {
				byteNumber = 1;
				break;
			}
			case 3: {
				byteNumber = 0;
				break;
			}
			default: {
				break;
			}
		}
		instruction data;
		data = dataInput;
		data = data << 8*byteNumber;
		data = data >> 24;
		return data;
	}
	cout << "Error reading byte string from memory." << endl;
	return 0;
} 
