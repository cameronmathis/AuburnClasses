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
#include <fstream>
#include <string>

#define TEXT_LENGTH  100
#define	DATA_LENGTH  50
#define STACK_LENGTH  50

using namespace std;

typedef unsigned int uint32;
typedef uint32 mem_addr;
typedef uint32 instruction;

mem_addr text_top = 0x00100000;
mem_addr data_top = 0x00200000;
mem_addr stack_top = 0x00300000;

// Kernal data starts at mem_addr 0, ommited because it isn't used in this simulation
instruction text_segment[TEXT_LENGTH];
mem_addr data_segment[DATA_LENGTH];
mem_addr stack_segment[STACK_LENGTH];


class Memory
{
public:
	Memory();
	bool load_code(mem_addr memory_address_in);						
	bool load_data(mem_addr memory_address_in, mem_addr data);		
    bool write(mem_addr memory_address_in, mem_addr data);			
    mem_addr * read(mem_addr memory_address_in);					
private:
	int decode_address_bin(mem_addr memory_address_in);				
	int decode_address_index(mem_addr memory_address_in);		
	string trim(string& str);	
	// Internal counter for text_segment
	int text_next_open_memory_location;								
};

// Initialize memory and load from file
Memory::Memory()  													
{
	text_next_open_memory_location = -1;
	int hexidecimal;
	int hexidecimal1;
	int hexidecimal2;
	string line;
	string line1 = "0000000000";
	string line2 = "0";
	int i = 0;
	ifstream accum_file_code ("accumCode.txt");
	if (accum_file_code.is_open())
	{	
		while ( getline (accum_file_code,line))
		{	
			line = line.substr(0, line.size()-1);
			if (line.compare("") == 0)
			{	
				continue;
			}	
			if (line.compare(".text") == 0)
			{	
				i = 0; 
				continue;
			}
			if (line.compare(".data") == 0)
			{	
				i = 1; 
				continue;
			}
			// Text
			if (i == 0) 												
			{	// Store line as hexidecimal
				sscanf(line.data(),"%x", &hexidecimal);
				load_code(hexidecimal);
			}
			// Data
			else if (i == 1) 												
			{	
				for (int c = 0; c < 10; c++)
				{
					line1[c] = line[c];
					line2[0] = line[11];
				}
				// Store line1 as hexidecimal
				sscanf(line1.data(), "%x", &hexidecimal1);
				// Store line2 as hexidecimal
				hexidecimal2 = atoi(line2.c_str());
				load_data(hexidecimal1,hexidecimal2);
			}
		}
	}
	else{
		cout << "Error: Unable to open file."; 
	}
	accum_file_code.close();
}

// Loads memory from the .text section
bool Memory::load_code(mem_addr memory_address_in)
{
	text_next_open_memory_location++;					
	// Checks the memory length					
	if (text_next_open_memory_location < TEXT_LENGTH)						
	{ 	// Stores the instruction
		text_segment[text_next_open_memory_location] = memory_address_in;	
		return true;
	}
	else
	{	// No More memory open
		cout << "Error: Please expand space for Text Memory." << endl;
		return false;														
	}
}

// Loads memory from the .data section
bool Memory::load_data(mem_addr memory_address_in, mem_addr data)
{
	mem_addr memory_copy_index = memory_address_in;
	
	int memory_index = (int) decode_address_index(memory_copy_index);
	// Checks the memory length
	if (text_next_open_memory_location < DATA_LENGTH)						
	{	// Stores the data
		data_segment[memory_index] = data;									
		return true;
	}
	else
	{	// No More memory open
		cout << "Error: Please expand space for Data Memory." << endl;
		return false;														
	}
}

// Writes to stack section. Used by STORE.
bool Memory::write(mem_addr memory_address_in, mem_addr data)
{
	mem_addr memory_copy_bin = memory_address_in, memory_copy_index = memory_address_in;
	switch(decode_address_bin(memory_copy_bin))
	{
	case 1:
			cout << "Error: You do not have the correct user privileges to write to text segment." << endl;
			return false;
		break;
	case 2:
			cout << "Error: You do not have the correct user privileges to write to data segment." << endl;
			return false;
		break;
	case 3:
		{
			int memory_index = (int) decode_address_index(memory_copy_index);
			// Checks the memory length
			if (text_next_open_memory_location < STACK_LENGTH)						
			{	// Store the data in the stack
				stack_segment[memory_index] = data;									
				return true;
			}
			else
			{	// No more stack open
				cout << "Error: Please expand space for Stack Memory" << endl;
				return false;														
			}
		}
		break;
	default:	// Not in the current memory
			cout << "Error: You cannot write to that memory area." << endl;
			return false;															
		break;
	}
	cout << "Error: Memory write went wrong." << endl;
	return false;
}

// Reads memory based on given memory address. Used by LOAD, ADD, and MULT.
mem_addr * Memory::read(mem_addr memory_address_in )
{	
	mem_addr memory_copy_bin = memory_address_in, memory_copy_index = memory_address_in;
	switch(decode_address_bin(memory_copy_bin))
	{
	case 1:
		{
			int memory_index = (int) decode_address_index(memory_copy_index);	
			// Checks the text memory length								
			if (memory_index < TEXT_LENGTH)											
			{
				return &text_segment[memory_index];
			}
		}
		break;
	case 2:
		{
			int memory_index = (int) decode_address_index(memory_copy_index);
			// Checks the data memory length
			if (text_next_open_memory_location < DATA_LENGTH)						
			{
				return &data_segment[memory_index];									
			}
		}
		break;
	case 3:
		{
			int memory_index = (int) decode_address_index(memory_copy_index);
			// Checks the stack memory length
			if (text_next_open_memory_location < STACK_LENGTH)						
			{
				return &stack_segment[memory_index];									
			}
		}
		break;
	default:	// Not in the current memory space
			cout << "Error: Memory read is not within current memory." << endl;
			return &stack_top;														
		break;
	}
	cout << "Error: Memory read went wrong." << endl;
	return &stack_top;
}

// Decodes address into bin
int Memory::decode_address_bin(mem_addr memory_address_in)
{	// Shifts all bits to the left 7
	memory_address_in = memory_address_in << 7;
	// Shifts all bits to the right 27
	memory_address_in = memory_address_in >> 27;
	return memory_address_in;
	// 0 = kernal, 1 = text, 2 = data, 3 = stack, and (-1) = error
}

// Decodes address into array index
int Memory::decode_address_index(mem_addr memory_address_in)
{	// Shifts all bits to the left 15
	memory_address_in = memory_address_in << 15;
	// Shifts all bits to the right 15 
	memory_address_in = memory_address_in >> 15;
	return memory_address_in;
}

// Trim whitespace from a string
string Memory::trim(string& str)
{
    size_t first = str.find_first_not_of(' ');
    if (first == std::string::npos)
        return "";
    size_t last = str.find_last_not_of(' ');
    return str.substr(first, (last-first+1));
}
