//Cameron Mathis
//clm0081
//hw4.cpp
//The program calculates the best strategy for Aaron.
//I received help from class slides.
#include <fstream>
#include <iostream>
#include <cstdlib> //for exit()
#include <stdlib.h>
#include <assert.h>
 
using namespace std;

const int MAX_SIZE = 100;

int readfile(int inputArray[], ifstream& inStream);
int sort(int inputArray1[], int inputArray1_size, int inputArray2[], int inputArray2_size, int outputArray[]);
void writefile(int outputArray[], int outputArray_size);

int main()
{
    string inputArray1_name;
    int inputArray1_size = 0;
    int inputArray1 [MAX_SIZE];
    string inputArray2_name;
    int inputArray2_size = 0;
    int inputArray2[MAX_SIZE];
    int outputArray_size = 0;
    int outputArray[MAX_SIZE];

    ifstream inStream;

    //Prompt user for file 1 name.
    cout << "*** Welcome to Cameron Mathis's sorting program ***\n";
    cout << "Enter the first input file name: ";
    cin >>  inputArray1_name;

    //Read in file 1.
    inStream.open((char*) inputArray1_name.c_str());
    inputArray1_size = readfile(inputArray1, inStream);
    
    //Print out components of file 1.
    cout << "The list of " << inputArray1_size << " numbers in the file " << inputArray1_name << " is:\n";
    for (int i = 0; i < inputArray1_size; i++) {
	cout << inputArray1[i] << endl;
    }

    //Prompt user for file 2 name.
    cout << "\nEnter the second input file name: ";
    cin >> inputArray2_name;
    
    //Read in file 2.
    inStream.open((char*) inputArray2_name.c_str());
    inputArray2_size = readfile(inputArray2, inStream);

    //Print out components of file 2.
    cout << "The list of " << inputArray2_size << " numbers in the file " << inputArray2_name << " is:\n";
    for (int i = 0; i < inputArray2_size; i++) {
        cout << inputArray2[i] << endl;
    }

    outputArray_size = sort(inputArray1, inputArray1_size, inputArray2, inputArray2_size, outputArray);

    //Print out components of merged files.
    cout << "\nThe sorted list of " << outputArray_size << " numbers is: ";
    for (int i = 0; i < outputArray_size; i++) {
        cout << outputArray[i] << " ";
    }
    cout << endl;

    writefile(outputArray, outputArray_size);

    return 0;
}

/* Input: inputArray[] indicates array entered
 *	  instream indicates something
 * Return: array_size indicates the size of the array
 */
int readfile(int inputArray[], ifstream& inStream) {
    int array_size = 0;

    if (inStream.fail()) {
        cout << "Input file opening failed.\n";
        exit(1);
    }
    while(!inStream.eof()) {
        inStream >> inputArray[array_size];
        array_size++;
    }
    inStream.close();

    return array_size;
}

/* Input: inputArray1[] indicates the first input array entered
 * 	  inputArray1_size indicates the size of the first array entered
 * 	  inputArray2[] indicates the second array entered
 * 	  inputArray2_size indicates the size of the second array entered
 * 	  outputArray[] indicates the last array entered
 * Return: outputArray_size indicates the size of the array that is outputed
 */
int sort(int inputArray1[], int inputArray1_size, int inputArray2[], int inputArray2_size, int (outputArray)[MAX_SIZE]) {
    int index1 = 0;
    int index2 = 0;
    int index = 0;

    while ((index1 < inputArray1_size) && (index2 < inputArray2_size)) {
	if (inputArray1[index1] < inputArray2[index2]) {
	    outputArray[index] = inputArray1[index1];
	    index1++;
	} else {
	    outputArray[index] = inputArray2[index2];
            index2++;
	}
	index++;
    }
    
    if (index1 < inputArray1_size) {
	for (int i = index1; i < inputArray1_size; i++) {
            outputArray[index] = inputArray1[i];
	    index++;
	}
    } else {
        for (int i = index2; i < inputArray2_size; i++) {
            outputArray[index] = inputArray2[i];
            index++;
        }
    }

    return index;
}

/* Input: outputArray[] indiates the output array
 * 	  outputArray_size indicates the size of the array that is outputed
 * Return: nothing
 */
void writefile(int outputArray[], int outputArray_size) {
    ofstream myfile("output.txt");

    if (myfile.is_open()) {
    	for(int i = 0; i < outputArray_size; i++) {
            myfile << outputArray[i] << "\n";
        }
        myfile.close();
    } else {
	cout << "Unable to open file";
    }
}
