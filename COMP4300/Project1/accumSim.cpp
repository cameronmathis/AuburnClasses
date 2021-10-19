/*
COMP 4300
Cameron Mathis
Project 1
09/18/20
Accumulator Machine Simulation
*/

#include <cstdlib>
#include <stdio.h>
#include <iostream>
#include "accumMem.cpp"

using namespace std;

class Sim
{
public:
	Sim();										
	void run();									
private:
	int instruction_op();						
	mem_addr instruction_memory_address();		
	// This is the accumulator its self
	mem_addr internal_register;	
	// Program counter				
	mem_addr pc;			
	// Pointer to the current instruction					
	instruction *current_instruction;	
	// Memory object		
	Memory *mem;								
};

int main()
{	
	Sim *sim = new Sim();
	sim->run();
	return 0;
}

// This starts the simulator and initializes the memory
Sim::Sim()
{
	internal_register = 0;
	pc = text_top;
	mem = new Memory();
}

void Sim::run()
{
	bool more_instructions = true;
	while(more_instructions)
	{	// Reads next instruction and increments the program counter
		current_instruction = mem->read(pc);
		pc++;
		
		switch(instruction_op())
		{
			case 1:	// LOAD
			{
				mem_addr *data = mem->read(instruction_memory_address());
				internal_register = *data;
				break;
			}
			case 2:	// STORE
			{
				mem->write(instruction_memory_address(),internal_register);
				break;
			}
			case 3: // ADD
			{
				mem_addr *data = mem->read(instruction_memory_address());
				internal_register = internal_register + *data;
				break;
			}
			case 4:	// MULT
			{
				mem_addr *data = mem->read(instruction_memory_address());
				internal_register = internal_register * *data;
				break;
			}
			case 5:	// END
			{
				more_instructions = false;
				cout << std::dec << internal_register << " is in the internal_register." <<endl;
				break;
			}
			default:
				cout << "Error: There was an error with the execution of loaded instructions." << endl;
				break;
		}
	}
}

// Returns the operation code of the internal current instruction
int Sim::instruction_op()
{														
	instruction op_value;					
	op_value = *current_instruction;
	// Shifts all bits to the right 24
	op_value = op_value >> 24;
	return op_value;
}

// Returns the memory address of the internal current instruction
mem_addr Sim::instruction_memory_address()
{
	instruction memory_address;
	memory_address = *current_instruction;
	// Shifts all bits to the left 8
	memory_address = memory_address << 8;
	// Shifts all bits to the right 8
	memory_address = memory_address >> 8;
	return memory_address;
}
