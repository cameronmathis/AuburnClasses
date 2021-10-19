/*
COMP 4300
Cameron Mathis
Project 1
09/18/20
Stack Machine Simulation
*/

#include <cstdlib>
#include <stdio.h>
#include <iostream>
#include "stackMem.cpp"

using namespace std;

class Sim
{
public:
	Sim();											
	void run();										
private:
	int instruction_op();							
	mem_addr instruction_memory_address();	
	// Points to the current OPEN spot on top of stack		
	mem_addr top_of_stack;			
	// Program counter, keeps up with where program is in memory				
	mem_addr pc;									
	// Pointer to current instruction
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

// This sets internal values and initilizes the Memory
Sim::Sim()
{
	top_of_stack = stack_top;
	pc = text_top;
	mem = new Memory();
}

void Sim::run()
{	// Simulates Stack Included functions are:  Push, Pop, Add, Mult, End
	bool more_instructions = true;					
	while(more_instructions)
	{	// Loads next instruction and increments the program counter
		current_instruction = mem->read(pc);
		pc++;
		
		switch(instruction_op())
		{
			case 1:	//PUSH
			{
				mem_addr *data = mem->read(instruction_memory_address());
				mem->write(top_of_stack, *data);
				top_of_stack++;
				break;
			}
			case 2:	// POP, top_of_stack points to NEXT empty space, so decrement it to find current value
			{
				top_of_stack--;
				mem_addr *data = mem->read(top_of_stack);
				cout << std::dec << *data << " was popped from the top of the sack." <<endl;
				break;
			}
			case 3: // ADD, top_of_stack points to NEXT empty space, so decrement it to find current value
			{
				top_of_stack--;
				mem_addr *first_data = mem->read(top_of_stack);
				top_of_stack--;
				mem_addr *second_data = mem->read(top_of_stack);
				mem_addr result = *first_data + *second_data;
				mem->write(top_of_stack, result);
				top_of_stack++;
				break;
			}
			case 4:	// MULT, top_of_stack points to NEXT empty space, so decrement it to find current value
			{
				top_of_stack--;
				mem_addr *first_data = mem->read(top_of_stack);
				top_of_stack--;
				mem_addr *second_data = mem->read(top_of_stack);
				mem_addr result = *first_data * *second_data;
				mem->write(top_of_stack, result);
				top_of_stack++;
				break;
			}
			case 5:	// END
			{
				more_instructions = false;
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
