//Cameron Mathis
//clm0081
//hw5.cpp
//The program is a trvia game.
//I received help from class slides and from the lab help hours.
#include <fstream>
#include <iostream>
#include <cstdlib> //for exit()
#include <stdlib.h>
#include <assert.h>
#include <string>
#include <sstream>
//#define UNIT_TESTING
 
using namespace std;

struct TriviaNode {
    string question;
    string answer;
    int score;
    TriviaNode* next;
};

TriviaNode* initializeList();
TriviaNode* addNode(string question, string answer, int score, TriviaNode* node);
int askQuestion(TriviaNode* node, int number);

TriviaNode* Trivia = initializeList();
int totalScore = 0;

/* Input: nothing
 * Return: first three questions
 */
TriviaNode* initializeList() {
    TriviaNode* A = new TriviaNode;
    TriviaNode* B = new TriviaNode;
    TriviaNode* C = new TriviaNode;
    A->question = "How long was the shortest war on record? (Hint: how many minutes)"; 
    A->answer = "38";
    A->score = 100;
    A->next = B;
    B->question = "What was the Bank of America's original name? (Hint: Bank of Italy or Bank of Germany)";
    B->answer = "Bank of Italy";
    B->score = 50;
    B->next = C;
    C->question = "What is the best-selling video game of all time? (Hint: Minecraft or Tetris)"; 
    C->answer = "Tetris"; 
    C->score = 20;
    C->next = NULL;
    return A;
}

/* Input: question indicates the question
 *        answer indicates the answer
 *	  score idicates the score
 * Return: list
 */
TriviaNode* addNode(string question, string answer, int score, TriviaNode* node) {
    TriviaNode* tail = new TriviaNode;
    tail->question = question;
    tail->answer = answer;
    tail->score = score;
    tail->next = NULL;
    
    TriviaNode* temp = node;

    while (temp->next != NULL) {
        temp = temp->next;
    }

    temp->next = tail;
}

/* Input: starting node
 *        number of nodes
 * Return: nothing
 */
int askQuestion(TriviaNode* node, int number) {
    TriviaNode* temp = node;
    int count = 1;
    while (temp->next != NULL) {
        temp = temp->next;
        count++;
    }
    if (number < 1) {
        cout << "Warning - The number of trivia to be asked must be equal to or larger than 1.\n";
	return 1;
    } else if (number > count) {
	cout << "Warning - There are only " << count << " trivia in the list.\n";
        return 1;
    }
    for (int i = 1; i <= number; i++) {
    	string input;
    	cout << "Question: " << node->question << "\nAnswer: ";
    	getline(cin, input);
    	if (node->answer == input) {
	    cout << "Your answer is correct. You recieve " << node->score << " points.\n";
            totalScore += node->score;
    	} else {
	    cout << "Your answer is wrong. The correct answer is: " << node->answer << "\n";
    	}
	node = node->next;
        cout << "Your total points: " << totalScore << "\n\n";
    }
    return 0;
}

#ifdef UNIT_TESTING
int main() {
    int x;
    cout << "\n*** This is a debug version ***\n";

    cout << "Unit Test Case 1: Ask no questions. The program should give a warning message.\n";
    x = askQuestion(Trivia, 0);
    if (x == 0) {
	cout << "Case 1 passed...\n\n";
    } else {
        cout << "\n";
    } 

    cout << "Unit Test Case 2.1: Ask 1 question in the linked list. The tester enters an correct answer.\n";
    x = askQuestion(Trivia, 1);
    if (x == 0) {
        cout << "Case 2.1 passed...\n\n";
    } else {
        cout << "\n";
    }

    cout << "Unit Test Case 2.2: Ask 1 question in the linked list. The tester enters an incorrect answer.\n";
    x = askQuestion(Trivia, 1);
    if (x == 0) {
        cout << "Case 2.2 passed...\n\n";
    } else {
        cout << "\n";
    }

    cout << "Unit Test Case 3.1: Ask all questions in the linked list. The tester enters an correct answers.\n";
    x = askQuestion(Trivia, 3);
    if (x == 0) {
        cout << "Case 3.1 passed...\n\n";
    } else {
        cout << "\n";
    }

    cout << "Unit Test Case 3.2: Ask all questions in the linked list. The tester enters an incorrect answers.\n";
    x = askQuestion(Trivia, 3);
    if (x == 0) {
        cout << "Case 3.2 passed...\n\n";
    } else {
        cout << "\n";
    }

    cout << "Unit Test Case 4: Ask 5 questions. The program should give a warning message.\n";
    x = askQuestion(Trivia, 5);
    if (x == 0) {
        cout << "Case 4 passed...\n\n";
    } else {
        cout << "\n";
    }

    cout << "\n*** End of the Debug Version ***\n";
}
#else
int main() {
    int number = 3;
    string cont;
    cout << "\n*** Welcome to Cameron's trivia quiz game ***\n";
    do  {
        string q;
        string a;
        string s;
        cout << "Enter a question: ";
        getline(cin, q);
        cout << "Enter an answer: ";
        getline(cin, a);
        cout << "Enter award points: ";
        getline(cin, s);
	stringstream geek(s);
        int sc = 0;
        geek >> sc;
        cout << "Continue? (Yes/No): ";
        getline(cin, cont);
        addNode(q, a, sc, Trivia);
        number++;
    } while (cont == "Yes");
    cout << "\n";

    askQuestion(Trivia, number);

    cout << "*** Thank you for playing the trivia quiz game. Goodbye! ***\n";
    return(0);
}
#endif
