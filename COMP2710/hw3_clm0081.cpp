//Cameron Mathis
//clm0081
//hw3.cpp
//The program calculates the best strategy for Aaron.
//I received help from class slides.
#include <iostream>
#include <stdlib.h>
#include <assert.h>
#include <ctime>
 
using namespace std;

bool at_least_two_alive(bool A_alive, bool B_alive, bool C_alive);
void Aaron_shoots1 (bool& B_alive, bool& C_alive);
void Bob_shoots (bool& A_alive, bool& C_alive);
void Charlie_shoots (bool& A_alive, bool& B_alive);
void Aaron_shoots2 (bool& B_alive, bool& C_alive);
void test_at_least_two_alive(void);

int main()
{
    //keep track of whether or not each player is alive
    bool AaronAlive = true;
    bool BobAlive = true;
    bool CharlieAlive = true;

    //keep track of the total number of wins for each player
    int Awins1 = 0;
    int Bwins1 = 0;
    int Cwins1 = 0;
    int Awins2 = 0;
    int Bwins2 = 0;
    int Cwins2 = 0;

    // Use current time as seed for random generator 
    srand(time(0));

    // Set decimal place to 2
    cout.setf(ios::fixed);
    cout.setf(ios::showpoint);
    cout.precision(2);

    cout << "*** Welcome to the Duel Simulator ***\n";
    
    test_at_least_two_alive();

    cout << "Press Enter to continue...";
    cin.get();

    cout << "Ready to test strategy 1 (run 10000 times):\n";
    cout << "Press Enter to continue...";
    cin.get();

    for (int i = 0; i < 10000; i++) {
        while (at_least_two_alive(AaronAlive, BobAlive, CharlieAlive)) {
            if (AaronAlive) {
	        Aaron_shoots1(BobAlive, CharlieAlive);
	    }
	    if (!at_least_two_alive(AaronAlive, BobAlive, CharlieAlive)) {
	        if (AaronAlive) {
		    Awins1 += 1;
		    break;
       	        }
	        else if (BobAlive) {
                    Bwins1 += 1;
		    break;
                }
		else if (CharlieAlive) {
		    Cwins1 += 1;
		    break;
		}
	    }
	    if (BobAlive) {
	        Bob_shoots(AaronAlive, CharlieAlive);
	    }
	    if (!at_least_two_alive(AaronAlive, BobAlive, CharlieAlive)) {
                if (AaronAlive) {
                    Awins1 += 1;
                    break;
                }   
                else if (BobAlive) {
                    Bwins1 += 1;
                    break;
                }
                else if (CharlieAlive) {
                    Cwins1 += 1;
                    break;
                }
            }
	    if (CharlieAlive) {
	        Charlie_shoots(AaronAlive, BobAlive);
	    }
	    if (!at_least_two_alive(AaronAlive, BobAlive, CharlieAlive)) {
                if (AaronAlive) {
                    Awins1 += 1;
                    break;
                }   
                else if (BobAlive) {
                    Bwins1 += 1;
                    break;
                }
                else if (CharlieAlive) {
                    Cwins1 += 1;
                    break;
                }
            }
	}
	AaronAlive = true;
	BobAlive = true;
	CharlieAlive = true;
    }
    cout << "Aaron won " << Awins1 << "/10000 duels or " << (double)Awins1/100 << "\%\n";
    cout << "Bob won " << Bwins1 << "/10000 duels or " << (double)Bwins1/100 << "\%\n";
    cout << "Charlie won " << Cwins1 << "/10000 duels or " << (double)Cwins1/100 << "\%\n\n";

    cout << "Ready to test strategy 2 (run 10000 times):\n";
    cout << "Press Enter to continue...";
    cin.get();

    for (int i = 0; i < 10000; i++) {
        while (at_least_two_alive(AaronAlive, BobAlive, CharlieAlive)) {
            if (AaronAlive) {
                Aaron_shoots2(BobAlive, CharlieAlive);
            }
            if (!at_least_two_alive(AaronAlive, BobAlive, CharlieAlive)) {
                if (AaronAlive) {
                    Awins2 += 1;
                    break;
                }
                else if (BobAlive) {
                    Bwins2 += 1;
                    break;
                }
                else if (CharlieAlive) {
                    Cwins2 += 1;
                    break;
                }
            }
            if (BobAlive) {
                Bob_shoots(AaronAlive, CharlieAlive);
            }
            if (!at_least_two_alive(AaronAlive, BobAlive, CharlieAlive)) {
                if (AaronAlive) {
                    Awins2 += 1;
                    break;
                }
                else if (BobAlive) {
                    Bwins2 += 1;
                    break;
                }
                else if (CharlieAlive) {
                    Cwins2 += 1;
                    break;
                }
            }
            if (CharlieAlive) {
                Charlie_shoots(AaronAlive, BobAlive);
            }
            if (!at_least_two_alive(AaronAlive, BobAlive, CharlieAlive)) {
                if (AaronAlive) {
                    Awins2 += 1;
                    break;
                }
                else if (BobAlive) {
                    Bwins2 += 1;
                    break;
                }
                else if (CharlieAlive) {
                    Cwins2 += 1;
                    break;
                }
            }
        }
        AaronAlive = true;
        BobAlive = true;
        CharlieAlive = true;
    }
    cout << "Aaron won " << Awins2 << "/10000 duels or " << (double)Awins2/100 << "\%\n";
    cout << "Bob won " << Bwins2 << "/10000 duels or " << (double)Bwins2/100 << "\%\n";
    cout << "Charlie won " << Cwins2 << "/10000 duels or " << (double)Cwins2/100 << "\%\n\n";

    if (Awins2 > Awins1) {
	cout << "Strategy 2 is better than strategy 1.\n";
    }
    else if (Awins1 > Awins2) {
	cout << "Strategy 1 is better than strategy 2.\n";
    }
    else if (Awins1 == Awins2) {
	cout << "Stategy 1 is equal to strategy 2.\n";
    }
}

/* Input: A_alive indicates whether Aaron is alive
 * 	  B_alive indicates whether Bob is alive
 * 	  C_alive indicates whether Charlie is alive
 * Return: true if at least two are alive otherwise return false
 */
bool at_least_two_alive(bool A_alive, bool B_alive, bool C_alive)
{
    if (A_alive && B_alive) {
	return true;
    }
    else if (A_alive && C_alive) {
	return true;
    }
    else if (B_alive && C_alive) {
	return true;
    }
    else {
	return false;
    }
}

/* Strategy 1: Use call by reference
 * Input: B_alive indicates whether Bob is alive or dead
 * 	  C_alive indicates whether Charlie is alive or dead
 * Return: Change B_alive into false if Bob is killed
 * 	   Change C_alive into false if Charlie is killed
 */
void Aaron_shoots1 (bool& B_alive, bool& C_alive)
{
    bool hitTarget = false;
    int shoot_target_result = rand()%100;

    if (shoot_target_result <= 33.333) {
	hitTarget = true;
    }
    
    if (hitTarget && C_alive) {
	C_alive = false;
    }
    else if (hitTarget && B_alive) {
	B_alive = false;
    }
}

/* Call by reference
 * Input: A_alive indicates whether Aaron is alive or dead
 *        C_alive indicates whether Charlie is alive or dead
 * Return: Change A_alive into false if Aaron is killed
 * 	   Change C_alive into false if Charlie is killed
 */
void Bob_shoots (bool& A_alive, bool& C_alive)
{
    bool hitTarget = false;
    int shoot_target_result = rand()%100;

    if (shoot_target_result <= 50) {
        hitTarget = true;
    }

    if (hitTarget && C_alive) {
        C_alive = false;
    }
    else if (hitTarget && A_alive) {
        A_alive = false;
    }
}

/* Call by reference
 * Input: A_alive indicates whether Aaron is alive or dead
 *        B_alive indicates whether Bob is alive or dead
 * Return: Change A_alive into false if Aaron is killed
 *         Change B_alive into false if Bob is killed
 */
void Charlie_shoots (bool& A_alive, bool& B_alive)
{
    if (B_alive) {
        B_alive = false;
    }
    else if (A_alive) {
        A_alive = false;
    }
}

/* Strategy 2: Use call by reference
 * Input: B_alive indicates whether Bob is alive or dead
 *        C_alive indicates whether Charlie is alive or dead
 * Return: Change B_alive into false if Bob is killed
 *         Change C_alive into false if Charlie is killed
 */
void Aaron_shoots2 (bool& B_alive, bool& C_alive)
{
    bool hitTarget = false;
    int shoot_target_result = rand()%100;

    if (shoot_target_result <= 33.333) {
        hitTarget = true;
    }

    if (B_alive && C_alive) {
	B_alive = true;
	C_alive = true;
    }
    else if (hitTarget && C_alive) {
        C_alive = false;
    }
    else if (hitTarget && B_alive) {
        B_alive = false;
    }
}

void test_at_least_two_alive(void)
{
    cout << "Unit Testing 1: Function - at_least_two_alive()\n";

    cout << "    Case 1: Aaron alive, Bob alive, Charlie alive\n";
    assert(true == at_least_two_alive(true, true, true));
    cout << "    Case passed ...\n";

    cout << "    Case 2: Aaron dead, Bob alive, Charlie alive\n";
    assert(true == at_least_two_alive(false, true, true));
    cout << "    Case passed ...\n";

    cout << "    Case 3: Aaron alive, Bob dead, Charlie alive\n";
    assert(true == at_least_two_alive(true, false, true));
    cout << "    Case passed ...\n";

    cout << "    Case 4: Aaron alive, Bob alive, Charlie dead\n";
    assert(true == at_least_two_alive(true, true, false));
    cout << "    Case passed ..\n";

    cout << "    Case 5: Aaron dead, Bob dead, Charlie alive\n";
    assert(false == at_least_two_alive(false, false, true));
    cout << "    Case passed ...\n";

    cout << "    Case 6: Aaron dead, Bob alive, Charlie dead\n";
    assert(false == at_least_two_alive(false, true, false));
    cout << "    Case passed ...\n";

    cout << "    Case 7: Aaron alive, Bob dead, Charlie dead\n";
    assert(false == at_least_two_alive(true, false, false));
    cout << "    Case passed ...\n";

    cout << "    Case 8: Aaron dead, Bob dead, Charlie dead\n";
    assert(false == at_least_two_alive(false, false, false));
    cout << "    Case passed ..\n";
}
