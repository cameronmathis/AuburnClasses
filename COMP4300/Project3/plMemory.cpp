/*******
	COMP 4300
	Cameron Mathis
	Project 3
	11/29/20
	Memory Simulation -- almost exactly the same as project 2
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
		// counter for textSegment		
		int textNextOpenMemoryLocation;								
		int getLengthOfString(memoryAddress memoryAddressIndex, int maxLength);
		memoryAddress getMemoryByte(instruction dataInput, int byteNumber);			
		memoryAddress memoryByteString(instruction dataInput, int byteNumber);  
};

/*******
	Class Definition 
********/

/* Initialize memory -- modified from project 1 & alsmost exactly the same as project 2 */
Memory::Memory() {
	string partiallyAssembledCodeFile = "lab3c.s"; // this is the file containing the partially assembled code
	textNextOpenMemoryLocation = -1;
	int hexidecimalOne;
	int hexidecimalTwo;
	string lineOne;
	string lineTwo = "0000000000";
	char dataArray[DATA_LENGTH];
	memset(dataArray, '\0', sizeof(dataArray) / sizeof(dataArray[0]));
	int i = 0;
	ifstream gprFileCode(partiallyAssembledCodeFile);
	if (gprFileCode.is_open()) {
		while (getline(gprFileCode, lineOne)) {
			lineOne.erase(lineOne.find_last_not_of("\n\r") + 1); // this is the only line different from project 2
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
			// .text
			if (i == 0)	{	// store lineOne as hexidecimal
				sscanf(lineOne.data(),"%x", &hexidecimalOne);
				loadCode(hexidecimalOne);
			}
			// .data
			if (i == 1) {	
				for (int ch = 0; ch < 10; ch++) {
					lineTwo[ch] = lineOne[ch];
				} // store lineTwo as hexidecimal
				sscanf(lineTwo.data(), "%x", &hexidecimalTwo);
				// store lineThree as hexidecimal
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

/* Loads from .text section -- exact same as project 1 & 2 */
bool Memory::loadCode(memoryAddress memoryAddressIndex) {
	textNextOpenMemoryLocation++;
	// checks memory length										
	if (textNextOpenMemoryLocation < TEXT_LENGTH) {	// stores instruction
		textSegment[textNextOpenMemoryLocation] = memoryAddressIndex;	
		return true;
	} else { // no more memory open
		cout << "Error: Please expand space for Text Memory." << endl;
		return false;														
	}
}

/* Write the given data string to memory -- modified from project 1 & exact same as project 2 */
bool Memory::loadData(memoryAddress memoryAddressIndex, char stringToBeStored[]) {
	if (decodeAddressBin(memoryAddressIndex) == 2) {
		int dataIndex = decodeAddressIndex(memoryAddressIndex);
		// checks the memory length
		if (dataIndex < DATA_LENGTH) {
			// fill data segment with 0s
			memset(&dataSegment[dataIndex], 0, strlen(stringToBeStored)+5);
			// store string in data segment
			memcpy(&dataSegment[dataIndex], stringToBeStored,  strlen(stringToBeStored)+1);
			return true;									
		}
		return false;
	}
	cout << "There was an error loading your data into memory." << endl;
	return false;
}

/* Read the memory at the given memory address -- exact same as project 1 & 2 */
memoryAddress* Memory::readFromMemory(memoryAddress memoryAddressIndex) {	
	memoryAddress memoryCopyBin = memoryAddressIndex;
	memoryAddress memoryCopyIndex = memoryAddressIndex;
	switch(decodeAddressBin(memoryCopyBin)) {
		case 1: {
			int memoryIndex = (int) decodeAddressIndex(memoryCopyIndex);	
			// checks text memory length								
			if (memoryIndex < TEXT_LENGTH)	{									
				return &textSegment[memoryIndex];
			}
		} break;
		case 2: {
			int memoryIndex = (int) decodeAddressIndex(memoryCopyIndex);
			// checks data memory length
			if (memoryIndex < DATA_LENGTH)	{										
				return &dataSegment[memoryIndex];									
			}
		} break;
		case 3: {
			int memoryIndex = (int) decodeAddressIndex(memoryCopyIndex);
			// checks stack memory length
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

/* Decodes address into bin -- modified from project 1 & exact same as project 2 */
int Memory::decodeAddressBin(memoryAddress memoryAddressIndex) {		
	// shifts all bits to the left	16													
	memoryAddressIndex = memoryAddressIndex << 16;
	// shifts all bits to the right 28
	memoryAddressIndex = memoryAddressIndex >> 28;
	return memoryAddressIndex;
	// 0 = kernal, 1 = text, 2 = data, 3 = stack, and (-1) = error
}

/* Decodes address into array index -- modified from project 1 & exact same as project 2 */
int Memory::decodeAddressIndex(memoryAddress memoryAddressIndex) {
	// shifts all bits to the left 20														
	memoryAddressIndex = memoryAddressIndex << 20;
	// shifts all bits to the right 20
	memoryAddressIndex = memoryAddressIndex >> 20;
	return memoryAddressIndex;
}

/* Read a string from memory -- exact same as project 2 */
string Memory::readStringFromMemory(memoryAddress memoryAddress) {	
	switch(decodeAddressBin(memoryAddress)) {
		// TEXT 
		case 1: {  
			cout << "There was an error loading your string into memory." << endl;
			return "Error";
		}
		 // DATA
		case 2: {
			int dataIndex = decodeAddressIndex(memoryAddress);
			char *dataOutput;
			dataOutput = (char*) malloc(getLengthOfString(memoryAddress, 2000));
			if (dataIndex < DATA_LENGTH) {
				memcpy(dataOutput, &dataSegment[dataIndex], getLengthOfString(memoryAddress, 2000));
				return string(dataOutput);									
			}
			return "Error";
		}
		// STACK
		case 3: { 
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

/* Used to read a byte at the given address -- exact same as project 2 */
memoryAddress Memory::readByte(memoryAddress memoryAddressIndex, int byte) {
	memoryAddress memoryCopyBin = memoryAddressIndex;
	memoryAddress memoryCopyIndex = memoryAddressIndex;
	int memoryIndex = (int) floor(decodeAddressIndex(memoryCopyIndex) / 4.0);
	memoryAddress memoryValue = 0;
	switch(decodeAddressBin(memoryCopyBin)) {
		case 1: { // checks text memory length			
			if (memoryIndex < TEXT_LENGTH) {
				memoryValue = textSegment[memoryIndex];
			}
		} break;
		case 2: { // checks data memory length
			if (memoryIndex < DATA_LENGTH)	{
				memoryValue = dataSegment[memoryIndex];									
			}
		} break;
		case 3: { // checks stack memory length
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

/* Finds the end of a string in memory -- exact same as project 2 */
int Memory::getLengthOfString(memoryAddress memoryAddressIndex, int maxLength) {
	switch(decodeAddressBin(memoryAddressIndex)) {
		// TEXT 
		case 1: { 
			cout << "There was an error finding the length of your string in memory." << endl;
			return 0;
		}
		// DATA
		case 2: { 
			int dataIndex = decodeAddressIndex(memoryAddressIndex);
			// checks data memory length
			if (dataIndex < DATA_LENGTH) {
				int length = 0;
				bool isEndFound = false;
				memoryAddress currentByte = 0;
				while(!isEndFound && length < maxLength) {
					currentByte = getMemoryByte(dataSegment[dataIndex], 1 + (length % 4));
					if(0 == currentByte) {
						isEndFound = true;
					}
					length++;
				}
				return length--;
			}
			return 0;
		}
		// STACK
		case 3: { 
			int dataIndex = decodeAddressIndex(memoryAddressIndex);
			// checks stack memory length
			if (dataIndex < STACK_LENGTH) {
				int length = 0;
				bool isEndFound = false;
				memoryAddress currentByte = 0;
				while(!isEndFound && length < maxLength) {
					currentByte = getMemoryByte(stackSegment[dataIndex], 1 + (length % 4));
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

/* Returns a byte inside the instruction -- exact same as project 2 */
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

/* Returns a byte for a string -- exact same as project 2 */
memoryAddress Memory::memoryByteString(instruction dataInput, int byteNumber) {																					
	if (byteNumber < 5 && byteNumber > 0) {
		byteNumber--;
		switch(byteNumber) {
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
