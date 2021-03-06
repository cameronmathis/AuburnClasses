'''

    Created on Mar 5, 2021

    @author: Cameron Mathis
    
    This file contains all the code used to test identifying the developer.

'''

import unittest
import Tiles2048.info as info


class InfoTest(unittest.TestCase):

    def test100_010_ShouldReturnMyUserName(self):
        expectedResult = {'user': 'clm0081'}
        userParms = {'op': 'info'}
        actualResult = info._info(userParms)
        self.assertDictEqual(expectedResult, actualResult)