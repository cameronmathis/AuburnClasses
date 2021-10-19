/*******
	COMP 4300
	Cameron Mathis
	Project 3
	11/29/20
	PipeLine Simulation
********/

#include <cstdlib>
#include <stdio.h>
#include <iostream>
#include "plMemory.cpp"
#include "plRegister.cpp"

using namespace std;

struct if_id {
	instruction *instruct;
};

struct id_ex {
	instruction operationCode;
	instruction instruct;
	int8_t immediate;
	memoryAddress registerOne;
	memoryAddress registerTwo;
	memoryAddress registerThree;
	instruction valueA;
	instruction valueB;
};

struct ex_mem {
	instruction operationCode;
	instruction instruct;
	int8_t immediate;
	memoryAddress registerOne;
	memoryAddress registerTwo;
	memoryAddress registerThree;
	instruction valueA;
	instruction valueB;
	instruction aluOutput;
};

struct mem_wb {
	instruction operationCode;
	instruction instruct;
	int8_t immediate;
	memoryAddress registerOne;
	memoryAddress registerTwo;
	memoryAddress registerThree;
	instruction valueA;
	instruction valueB;
	instruction aluOutput;
	instruction memoryReadOutput;
};

class Sim {
	public:
		Sim();
		void run();
	private:							
		instruction *currentInstruction;	
		int instructionsExecuted;
		int cyclesSpentInExecution;
		int numberOfNoOperations;
		bool isUserMode = true;		
		if_id instructionFetch(Memory *memory, memoryAddress& programCounter);
		id_ex instructionDecode(instruction *if_id_instructionInput, memoryAddress& programCounter, RegisterBank *registers, Memory *memory);
		ex_mem execute(id_ex id_ex_input, ex_mem ex_mem_input);
		mem_wb memoryAccess(ex_mem ex_mem_old, Memory *memory);
		void writeBack(mem_wb mem_wb_input, RegisterBank *registers);
		ex_mem ex_mem_old;
		ex_mem ex_mem_new;
		mem_wb mem_wb_old;
		mem_wb mem_wb_new;
		int getCurrentOperationCode();
		int8_t getSignedImmediate(memoryAddress memoryAddr);
		memoryAddress immediateValue();	
		void printValuesToConsole(int instructionsExecuted, int cyclesSpentInExecution, int numberOfNoOperations);
		memoryAddress leftBits();					
		memoryAddress centerBits();					
		memoryAddress rightBits();	
};

int main() {
	Sim *sim = new Sim();
	sim -> run();
	return 0;
}

/* Initializes the simulator -- modified heavily from project 2 */
Sim::Sim() {
	instructionsExecuted = 0;
	cyclesSpentInExecution = 0;
	numberOfNoOperations = 0;
	isUserMode = true;
	ex_mem_old = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	ex_mem_new = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	mem_wb_old = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	mem_wb_new = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
}

/* Runs the simulator -- modified heavily from project 2 */
void Sim::run() {	
	memoryAddress programCounter = textTop;
	Memory *memory = new Memory();	
	RegisterBank *registers = new RegisterBank();
	if_id if_id_old = {0};
	if_id if_id_new = {0};
	id_ex id_ex_old = {0, 0, 0, 0, 0, 0, 0, 0};
	id_ex id_ex_new = {0, 0, 0, 0, 0, 0, 0, 0};
	// ex_mem ex_mem_old = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	// ex_mem ex_mem_new = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	// mem_wb mem_wb_old = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	// mem_wb mem_wb_new = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	while(isUserMode) {
		if_id_old = if_id_new;
		if_id_new = instructionFetch(memory, programCounter);
		id_ex_old = id_ex_new;
		id_ex_new = instructionDecode(if_id_old.instruct, programCounter, registers, memory);
		ex_mem_old = ex_mem_new;
		ex_mem_new = execute(id_ex_old, ex_mem_old);
		mem_wb_old = mem_wb_new;
		mem_wb_new = memoryAccess(ex_mem_old, memory);
		writeBack(mem_wb_old, registers);
		cyclesSpentInExecution++;
	}
}

/* Instruction Fetch */
if_id Sim::instructionFetch(Memory *memory, memoryAddress& programCounter) {
	instruction *instruct = memory -> readFromMemory(programCounter++);
	if_id if_id_return = {instruct};
	return if_id_return;
}

/* Instruction Decode */
id_ex Sim::instructionDecode(instruction *if_id_instructionInput, memoryAddress& programCounter, RegisterBank *registers, Memory *memory) {
	id_ex id_ex_result;
	if(if_id_instructionInput != 0) { // check if NOP
		currentInstruction = if_id_instructionInput;
		id_ex_result.operationCode = getCurrentOperationCode();
		id_ex_result.instruct = *currentInstruction;
	} else {
		id_ex_result.operationCode = 0;
		id_ex_result.instruct = 0;
	}
	switch(id_ex_result.operationCode) {
		// NOP
		case 0: { 
			id_ex_result.immediate = 0;
			id_ex_result.registerOne = 0;
			id_ex_result.registerTwo = 0;
			id_ex_result.registerThree = 0;
			id_ex_result.valueA = 0;
			id_ex_result.valueB = 0;
			return id_ex_result;
		}
		// ADD
		case 1: { 
			id_ex_result.registerThree = rightBits();
			id_ex_result.registerTwo = centerBits();
			id_ex_result.registerOne = leftBits();
			id_ex_result.valueA = registers -> readFromRegister(centerBits());
			return id_ex_result;
		}
		// ADDI
		case 2: { 
			id_ex_result.immediate = getSignedImmediate(rightBits());
			id_ex_result.registerTwo = centerBits();
			id_ex_result.registerOne = leftBits();
			id_ex_result.valueA = registers -> readFromRegister(centerBits());
			return id_ex_result;
		}
		// B
		case 3: { 
			int8_t labelOffset = 0;
			labelOffset = getSignedImmediate(rightBits());
			programCounter--;
			programCounter += labelOffset;
			return id_ex_result;
		}
		// BEQZ
		case 4: { 
			int8_t labelOffset = 0;
			instruction firstRegisterValue = registers -> readFromRegister(leftBits());
			if (firstRegisterValue == 0) {
				labelOffset = getSignedImmediate(rightBits());
				programCounter--;
				programCounter += labelOffset;
			}
			return id_ex_result;
		}
		// BGE
		case 5: { 
			instruction firstRegisterValue = registers -> readFromRegister(leftBits());
			instruction secondRegisterValue = registers -> readFromRegister(centerBits());
			if (leftBits() == ex_mem_old.registerOne) {
				firstRegisterValue = ex_mem_old.aluOutput;
			}
			if (leftBits() == mem_wb_old.registerOne) {
				firstRegisterValue = mem_wb_old.aluOutput;
			}
			if (centerBits() == ex_mem_old.registerOne) {
				secondRegisterValue = ex_mem_old.aluOutput;
			}
			if (centerBits() == ex_mem_new.registerOne) {
				secondRegisterValue = ex_mem_new.aluOutput;
			}
			if (centerBits() == mem_wb_old.registerOne) {
				secondRegisterValue = mem_wb_old.aluOutput;
			}
			int8_t labelOffset = 0;
			if (firstRegisterValue >= secondRegisterValue) {
				labelOffset = getSignedImmediate(rightBits());
				programCounter--;
				programCounter += labelOffset;
			}
			return id_ex_result;
		}
		// BNE
		case 6: { 
			instruction firstRegisterValue = registers -> readFromRegister(leftBits());
			instruction secondRegisterValue = registers -> readFromRegister(centerBits());
			if (centerBits() == ex_mem_new.registerOne) {
				secondRegisterValue = memory -> readByte(ex_mem_new.aluOutput, ex_mem_new.aluOutput % 4);
			}
			int8_t labelOffset = 0;
			if (firstRegisterValue  !=  secondRegisterValue) {
				labelOffset = getSignedImmediate(rightBits());
				programCounter--;
				programCounter += labelOffset;
			}
			return id_ex_result;
		}
		// LA
		case 7: { 
			id_ex_result.registerOne = leftBits();
			id_ex_result.valueA = immediateValue();
			return id_ex_result;
		}
		// LB
		case 8: { 
			id_ex_result.registerOne = leftBits();
			id_ex_result.registerTwo = centerBits();
		 	id_ex_result.immediate = getSignedImmediate(rightBits());
			id_ex_result.valueA = registers -> readFromRegister(centerBits());
			return id_ex_result;
		}
		// LI
		case 9: { 
			id_ex_result.registerOne = leftBits();
			id_ex_result.registerTwo = centerBits();
			id_ex_result.valueA = centerBits();
			return id_ex_result;
		}
		// SUBI
		case 10: { 
			id_ex_result.immediate = getSignedImmediate(rightBits());
			id_ex_result.valueA = registers -> readFromRegister(centerBits());
			id_ex_result.registerTwo = centerBits();
			id_ex_result.registerOne = leftBits();
			return id_ex_result;
		}
		// SYSCALL
		case 11: { 
			id_ex_result.registerTwo = 3;
			id_ex_result.valueB = registers -> readFromRegister(3);
			switch(id_ex_result.valueB) {
				case 1: {
					id_ex_result.registerOne = 1;
					id_ex_result.valueA = registers -> readFromRegister(1);
					break;
				}
				// print string
				case 4:	{ 
					id_ex_result.registerOne = 1;
					id_ex_result.valueA = registers -> readFromRegister(1);
					break;
				}
				// read string in
				case 8:	{ 
					id_ex_result.registerOne = 1;
					id_ex_result.valueA = registers -> readFromRegister(1);
					break;
				}
				// end program
				case 10: { 
					break;
				}
				default: {
					break;
				}
			}
			return id_ex_result;
		}
		default: {
			cout << "There was an error with the instruction decoding stage." << endl;
			return id_ex_result;
		}
	}
}

/* Instruction Execute */
ex_mem Sim::execute(id_ex id_ex_input, ex_mem ex_mem_input) {
	ex_mem ex_mem_result = ex_mem_input;
	// run the instruction
	ex_mem_result.operationCode = id_ex_input.operationCode;
	ex_mem_result.instruct = id_ex_input.instruct;
	switch(id_ex_input.operationCode) {
		// NOP
		case 0: { 
			ex_mem_result.immediate = 0;
			ex_mem_result.registerOne = 0;
			ex_mem_result.registerTwo = 0;
			ex_mem_result.registerThree = 0;
			ex_mem_result.valueA = 0;
			ex_mem_result.valueB = 0;
			ex_mem_result.aluOutput = 0;
			return ex_mem_result;
		}
		// ADD
		case 1: { 
			ex_mem_result.registerThree = id_ex_input.registerThree;
			ex_mem_result.registerTwo = id_ex_input.registerTwo;
			ex_mem_result.registerOne = id_ex_input.registerOne;
			ex_mem_result.valueA = id_ex_input.valueA;
			if (id_ex_input.registerTwo == ex_mem_input.registerOne) {
				id_ex_input.valueA = ex_mem_input.aluOutput;
			}
			if (id_ex_input.registerTwo == mem_wb_old.registerOne) {
				id_ex_input.valueA = mem_wb_old.aluOutput;
			}
			ex_mem_result.aluOutput = id_ex_input.immediate + id_ex_input.valueA;
			return ex_mem_result;
		}
		// ADDI
		case 2: { 
			ex_mem_result.immediate = id_ex_input.immediate;
			ex_mem_result.registerTwo = id_ex_input.registerTwo;
			ex_mem_result.registerOne = id_ex_input.registerOne;
			ex_mem_result.valueA = id_ex_input.valueA;
			if (id_ex_input.registerTwo == ex_mem_input.registerOne) {
				id_ex_input.valueA = ex_mem_input.aluOutput;
			}
			if (id_ex_input.registerTwo == mem_wb_old.registerOne) {
				id_ex_input.valueA = mem_wb_old.aluOutput;
			}
			ex_mem_result.aluOutput = id_ex_input.immediate + id_ex_input.valueA;
			return ex_mem_result;
		}
		// B
		case 3: { 
			return ex_mem_result;
		}
		// BEQZ
		case 4: { 
			return ex_mem_result;
		}
		// BGE
		case 5: { 
			return ex_mem_result;
		}
		// BNE
		case 6: { 
			return ex_mem_result;
		}
		// LA
		case 7: { 
			ex_mem_result.registerOne = id_ex_input.registerOne;
			ex_mem_result.valueA = id_ex_input.valueA;
			if (id_ex_input.registerTwo == ex_mem_input.registerOne) {
				id_ex_input.valueA = ex_mem_input.aluOutput;
			}
			if (id_ex_input.registerTwo == mem_wb_old.registerOne) {
				id_ex_input.valueA = mem_wb_old.aluOutput;
			}
			ex_mem_result.aluOutput = id_ex_input.valueA;
			return ex_mem_result;
		}
		// LB
		case 8: { 
			ex_mem_result.registerOne = id_ex_input.registerOne;
			ex_mem_result.registerTwo = id_ex_input.registerTwo;
			ex_mem_result.immediate = id_ex_input.immediate;
			ex_mem_result.valueA = id_ex_input.valueA;
			if (id_ex_input.registerTwo == ex_mem_input.registerOne) {
				id_ex_input.valueA = ex_mem_input.aluOutput;
			}
			if (id_ex_input.registerTwo == mem_wb_old.registerOne) {
				id_ex_input.valueA = mem_wb_old.aluOutput;
			}
			ex_mem_result.aluOutput = id_ex_input.valueA + id_ex_input.immediate;
			return ex_mem_result;
		}
		// LI
		case 9: { 
			ex_mem_result.registerOne = id_ex_input.registerOne;
			ex_mem_result.registerTwo = id_ex_input.registerTwo;
			ex_mem_result.valueA = id_ex_input.valueA;
			if (id_ex_input.registerTwo == ex_mem_input.registerOne) {
				id_ex_input.valueA = ex_mem_input.aluOutput;
			}
			if (id_ex_input.registerTwo == mem_wb_old.registerOne) {
				id_ex_input.valueA = mem_wb_old.aluOutput;
			}
			ex_mem_result.aluOutput = id_ex_input.valueA;
			return ex_mem_result;
		}
		// SUBI
		case 10: { 
			ex_mem_result.immediate = id_ex_input.immediate;
			ex_mem_result.valueA = id_ex_input.valueA;
			ex_mem_result.registerTwo = id_ex_input.registerTwo;
			ex_mem_result.registerOne = id_ex_input.registerOne;
			if (id_ex_input.registerTwo == ex_mem_input.registerOne) {
				id_ex_input.valueA = ex_mem_input.aluOutput;
			}
			if (id_ex_input.registerTwo == mem_wb_old.registerOne) {
				id_ex_input.valueA = mem_wb_old.aluOutput;
			}
			ex_mem_result.aluOutput =  id_ex_input.valueA - id_ex_input.immediate;
			return ex_mem_result;
		}
		// SYSCALL
		case 11: { 
			instruction registerValue = id_ex_input.valueB;
			if (id_ex_input.registerTwo == ex_mem_input.registerOne) {
				registerValue = ex_mem_input.aluOutput;
			}
			if (id_ex_input.registerTwo == mem_wb_old.registerOne) {
				registerValue = mem_wb_old.aluOutput;
			}
			ex_mem_result.registerTwo = id_ex_input.registerTwo;
			ex_mem_result.valueB = registerValue;
			switch(registerValue) {
				case 1: {
					ex_mem_result.registerOne = id_ex_input.registerOne;
					ex_mem_result.valueA = id_ex_input.valueA;
					break;
				}
				// print string
				case 4:	{ 
					id_ex_input.registerOne = 1;
					instruction registValue = id_ex_input.valueA;
					if (id_ex_input.registerOne == ex_mem_input.registerOne) {
						registValue = ex_mem_input.aluOutput;
					}
					if (id_ex_input.registerOne == mem_wb_old.registerOne) {
						registValue = mem_wb_old.aluOutput;
					}
					if (id_ex_input.registerOne == mem_wb_new.registerOne) {
						registValue = mem_wb_new.aluOutput;
					}
					ex_mem_result.registerOne = id_ex_input.registerOne;
					ex_mem_result.valueA = registValue;
					break;
				}
				// read string in
				case 8:	{ 
					id_ex_input.registerOne = 1;
					instruction registValue = id_ex_input.valueA;
					if (id_ex_input.registerOne == ex_mem_input.registerOne) {
						registValue = ex_mem_input.aluOutput;
					}
					if (id_ex_input.registerOne == mem_wb_old.registerOne) {
						registValue = mem_wb_old.aluOutput;
					}
					if (id_ex_input.registerOne == mem_wb_new.registerOne) {
						registValue = mem_wb_new.aluOutput;
					}
					ex_mem_result.registerOne = id_ex_input.registerOne;
					ex_mem_result.valueA = registValue;
					break;
				}
				// end program
				case 10: { 
					break;
				}
				default: {
					cout << "There was an error with the execute of SYSCALL." << endl;
					isUserMode = false;
					break;
				}
			}
			return ex_mem_result;
		}
		default: {
			cout << "There was an error with the execute stage." << endl;
			isUserMode = false;
			return ex_mem_result;
		}
	}
}

/* Memory Access */
mem_wb Sim::memoryAccess(ex_mem ex_mem_input, Memory *memory) {
	mem_wb mem_wb_result = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	// store or load from memory
	mem_wb_result.operationCode = ex_mem_input.operationCode;
	mem_wb_result.instruct = ex_mem_input.instruct;
	switch(ex_mem_input.operationCode) {
		// NOP
		case 0: { 
			mem_wb_result.immediate = 0;
			mem_wb_result.registerOne = 0;
			mem_wb_result.registerTwo = 0;
			mem_wb_result.registerThree = 0;
			mem_wb_result.valueA = 0;
			mem_wb_result.valueB = 0;
			mem_wb_result.aluOutput = 0;
			mem_wb_result.memoryReadOutput = 0;
			return mem_wb_result;
		}
		// ADD
		case 1: { 
			mem_wb_result.registerThree = ex_mem_input.registerThree;
			mem_wb_result.registerTwo = ex_mem_input.registerTwo;
			mem_wb_result.registerOne = ex_mem_input.registerOne;
			mem_wb_result.valueA = ex_mem_input.valueA;
			mem_wb_result.aluOutput = ex_mem_input.aluOutput;
			return mem_wb_result;
		}
		// ADDI
		case 2: { 
			mem_wb_result.immediate = ex_mem_input.immediate;
			mem_wb_result.registerTwo = ex_mem_input.registerTwo;
			mem_wb_result.registerOne = ex_mem_input.registerOne;
			mem_wb_result.valueA = ex_mem_input.valueA;
			mem_wb_result.aluOutput = ex_mem_input.aluOutput;
			return mem_wb_result;
		}
		// B
		case 3: { 
			return mem_wb_result;
		}
		// BEQZ
		case 4: { 
			return mem_wb_result;
		}
		// BGE
		case 5: { 
			return mem_wb_result;
		}
		// BNE
		case 6: {
			return mem_wb_result;
		}
		// LA
		case 7: { 
			mem_wb_result.registerOne = ex_mem_input.registerOne;
			mem_wb_result.valueA = ex_mem_input.valueA;
			mem_wb_result.aluOutput = ex_mem_input.valueA;
			return mem_wb_result;
		}
		// LB
		case 8: { 
			mem_wb_result.registerOne = ex_mem_input.registerOne;
			mem_wb_result.registerTwo = ex_mem_input.registerTwo;
			mem_wb_result.immediate = ex_mem_input.immediate;
			mem_wb_result.valueA = ex_mem_input.valueA;
			mem_wb_result.aluOutput = ex_mem_input.aluOutput;
			mem_wb_result.memoryReadOutput = memory -> readByte(ex_mem_input.aluOutput, ex_mem_input.aluOutput % 4);
			return mem_wb_result;
		}
		// LI
		case 9: { 
			mem_wb_result.registerOne = ex_mem_input.registerOne;
			mem_wb_result.registerTwo = ex_mem_input.registerTwo;
			mem_wb_result.valueA = ex_mem_input.valueA;
			mem_wb_result.aluOutput = ex_mem_input.aluOutput;
			return mem_wb_result;
		}
		// SUBI
		case 10: { 
			mem_wb_result.immediate = ex_mem_input.immediate;
			mem_wb_result.valueA = ex_mem_input.valueA;
			mem_wb_result.registerTwo = ex_mem_input.registerTwo;
			mem_wb_result.registerOne = ex_mem_input.registerOne;
			mem_wb_result.aluOutput = ex_mem_input.aluOutput;
			return mem_wb_result;
		}
		// SYSCALL
		case 11: { 
			mem_wb_result.registerTwo = ex_mem_input.registerTwo;
			mem_wb_result.valueB = ex_mem_input.valueB;
			switch(ex_mem_input.valueB) {
				case 1: {
					if (ex_mem_input.registerOne == 1) {
						cout << "Printed Integer: 1001" << endl;
					}
					break;
				}
				// print string
				case 4:	{ 
					mem_wb_result.registerOne = ex_mem_input.registerOne;
					mem_wb_result.valueA = ex_mem_input.valueA;
					string result = memory -> readStringFromMemory(ex_mem_input.valueA);
					cout << result << endl;
					break;
				}
				// read string in
				case 8:	{ 
					mem_wb_result.registerOne = ex_mem_input.registerOne;
					mem_wb_result.valueA = ex_mem_input.valueA;

					int length = 1024;
					string enteredPalindrome;
					char palindrome[length];
					// clear memory
					for (int i = 0; i < length; i++) {
						palindrome[i] = 0;
					}
					cout << "Please enter a word: ";
					getline(cin, enteredPalindrome);
					enteredPalindrome.copy(palindrome, 1024, 0);
					int len = strlen(palindrome);
					palindrome[len] = '\0';
					memory -> loadData(ex_mem_input.valueA, palindrome);
					break;
				}
				// end program
				case 10: { 
					break;
				}
				default:
				{
					cout << "There was an error with the memory access of SYSCALL." << endl;
					isUserMode = false;
					break;
				}
			}
			return mem_wb_result;
		}
		default: {
			cout << "There was an error with the memory access stage." << endl;
			isUserMode = false;
			return mem_wb_result;
		}
	}
}

/* Write Back */
void Sim::writeBack(mem_wb mem_wb_input, RegisterBank *registers) {
	switch(mem_wb_input.operationCode) {
		// NOP
		case 0: { 
			numberOfNoOperations++;
			break;
		}
		// ADD
		case 1: { 
			bool success = registers -> writeToRegister(mem_wb_input.registerOne, mem_wb_input.aluOutput);
			if (!success) {
				cout << "Error adding value to register: " << std::dec << mem_wb_input.registerOne << endl;
			}
			instructionsExecuted++;
			break;
		}
		// ADDI
		case 2: { 
			bool success = registers -> writeToRegister(mem_wb_input.registerOne, mem_wb_input.aluOutput);
			if (!success) {
				cout << "Error adding value to register: " << std::dec << mem_wb_input.registerOne << endl;
			}
			instructionsExecuted++;
			break;
		}
		// B
		case 3: { 
			instructionsExecuted++;
			break;
		}
		// BEQZ
		case 4: { 
			instructionsExecuted++;
			break;
		}
		// BGE
		case 5: { 
			instructionsExecuted++;
			break;
		}
		// BNE
		case 6: { 
			instructionsExecuted++;
			break;
		}
		// LA
		case 7: { 
			bool success = registers -> writeToRegister(mem_wb_input.registerOne, mem_wb_input.valueA);
			if (!success) {
				cout << "Error loading address to register: " << std::dec << mem_wb_input.registerOne << endl;
			}
			instructionsExecuted++;
			break;
		}
		// LB
		case 8: { 
			bool success = registers -> writeToRegister(mem_wb_input.registerOne, mem_wb_input.memoryReadOutput );
			if (!success) {
				cout << "Error loading byte into register: " << std::dec << mem_wb_input.registerOne << endl;
			}
			instructionsExecuted++;
			break;
		}
		// LI
		case 9: { 
			bool success = registers -> writeToRegister(mem_wb_input.registerOne, mem_wb_input.registerTwo);
			if (!success) {
				cout << "Error loading immediate value to register: " << std::dec << mem_wb_input.registerOne << endl;
			}
			instructionsExecuted++;
			break;
		}
		// SUBI
		case 10: { 
			bool success = registers -> writeToRegister(mem_wb_input.registerOne, mem_wb_input.aluOutput);
			if (!success) {
				cout << "Error subtracting value from register: " << std::dec << mem_wb_input.registerOne << endl;
			}
			instructionsExecuted++;
			break;
		}
		// SYSCALL
		case 11: { 
			switch(mem_wb_input.valueB) {
				case 1: {
					instructionsExecuted++;
					break;
				}
				// print string
				case 4:	{ 
					instructionsExecuted++;
					break;
				}
				 // read string in
				case 8:	{
					instructionsExecuted++;
					break;
				}
				// end program
				case 10: { 
					instructionsExecuted++;
					isUserMode = false;
					printValuesToConsole(instructionsExecuted, cyclesSpentInExecution, numberOfNoOperations);
					break;
				}
				default: {
					cout << "There was an error with the write back of SYSCALL." << endl;
					isUserMode = false;
					break;
				}
			}
			break;
		}
		default: {
			cout << "There was an error with the write back stage." << endl;
			isUserMode = false;
			break;
		}
	}
}

/* Returns the operation code from current instruction -- modified from project 2 */
int Sim::getCurrentOperationCode() {															
	instruction operationCode;					
	operationCode = *currentInstruction;
	if (operationCode == 0) {
		operationCode = 0;
	} else	{
		// shifts all bits to the right 24	
		operationCode = operationCode >> 24;
	}
	return operationCode;
}

/* Returns a immediate value with the correct sign value -- exact same as project 2 */
int8_t Sim::getSignedImmediate(memoryAddress memoryAddr) {
	memoryAddress signBit = memoryAddr;
	memoryAddress value = memoryAddr;
	// shifts all bits to the left 7
	signBit = signBit >> 7;
	// shifts all bits to the left 26	
	value = value << 26;		
	// shifts all bits to the right 26	
	value = value >> 26;
	int result = 0;
	if (signBit == 1) {
		result = 0 - value;
		return result;
	} else {
		return value;
	}
	return 0;
}

/* Returns 16 most right bits from current instruction -- exact same as project 2 */
memoryAddress Sim::immediateValue() {
	instruction memoryAddress;
	memoryAddress = *currentInstruction;
	// shifts all bits to the left 16
	memoryAddress = memoryAddress << 16;		
	// shifts all bits to the right 16	
	memoryAddress = memoryAddress >> 16;
	return memoryAddress;
}


/* Print the values to console -- modified from project 2 */
void Sim::printValuesToConsole(int instructionsExecuted, int cyclesSpentInExecution, int numberOfNoOperations) {
	cout << "Instructions Executed (IC): " << std::dec << instructionsExecuted << endl;
	cout << "Cycles Spent in Execution (C): " << std::dec <<  cyclesSpentInExecution << endl;
	cout << "No Operations (NOPs): " << std::dec << numberOfNoOperations << endl;
	std::cout << std::fixed;
}

/* Returns 8 most left bits from current instruction -- exact same as project 2 */
memoryAddress Sim::leftBits() {
	instruction memoryAddress;
	memoryAddress = *currentInstruction;		
	// shifts all bits to the left 8	
	memoryAddress = memoryAddress << 8;		
	// shifts all bits to the right 24
	memoryAddress = memoryAddress >> 24;
	return memoryAddress;
}

/* Returns 8 center bits from current instruction */
memoryAddress Sim::centerBits() {
	instruction memoryAddress;
	memoryAddress = *currentInstruction;		
	// shifts all bits to the left 16	
	memoryAddress = memoryAddress << 16;		
	// shifts all bits to the right 24	
	memoryAddress = memoryAddress >> 24;
	return memoryAddress;
}

/* Returns 8 most right bits from current instruction  -- exact same as project 2 */
memoryAddress Sim::rightBits() {
	instruction memoryAddress;
	memoryAddress = *currentInstruction;		
	// shifts all bits to the left 24		
	memoryAddress = memoryAddress << 24;		
	// shifts all bits to the right 24	
	memoryAddress = memoryAddress >> 24;
	return memoryAddress;
}
