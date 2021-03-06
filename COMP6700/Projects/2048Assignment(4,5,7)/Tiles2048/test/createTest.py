'''

    Created on Mar 5, 2021

    @author: Cameron Mathis
    
    This file contains all the code used to test the creation of a starting grid.

'''

import unittest
import Tiles2048.create as create

class CreateTest(unittest.TestCase):

    # 100 create
    #    Desired level of confidence:    boundary value analysis
    #    Input-output Analysis
    #        inputs:    python dictionary containing the op value and size of the grid
    #        outputs:   python dictionary containing grid, score, integrity, and status
    #    Happy path analysis:
    #        output:
    #
    # Happy path
    def test_create_110_ShouldGenerateGrid(self):
        userParms = {'op': 'create', 'size': '4'}
        expectedResult = {'grid': '0020000020000000', 'score': '0', 'integrity': '7CD5E3DEAB08FCAE8F64433DC4A63CC922571EBF60EE1D1938ADCD415FB760E5', 'status': 'ok'}
        actualResult = create._create(userParms)
        passed = False
        if (len(expectedResult['grid']) == len(actualResult['grid'])):
            numberOfTwos = 0
            for char in expectedResult['grid']:
                if (char == '2'):
                    numberOfTwos += 1
        if (numberOfTwos == 2):
            passed = True
        self.assertEquals(passed, True)
        
    def test_create_120_ShouldCalculateScore(self):
        userParms = {'op': 'create', 'size': '4'}
        expectedResult = {'grid': '0020000020000000', 'score': '0', 'integrity': '7CD5E3DEAB08FCAE8F64433DC4A63CC922571EBF60EE1D1938ADCD415FB760E5', 'status': 'ok'}
        actualResult = create._create(userParms)
        self.assertEquals(expectedResult['score'], actualResult['score'])
        
    def test_create_130_ShouldCalculateIntegrity(self):
        expectedResult = '7CD5E3DEAB08FCAE8F64433DC4A63CC922571EBF60EE1D1938ADCD415FB760E5'
        actualResult = create._calculateIntegrity('0020000020000000', '0')
        self.assertEquals(expectedResult, actualResult)
        
    def test_create_140_ShouldCalculateStatus(self):
        userParms = {'op': 'create', 'size': '4'}
        expectedResult = {'grid': '0020000020000000', 'score': '0', 'integrity': '7CD5E3DEAB08FCAE8F64433DC4A63CC922571EBF60EE1D1938ADCD415FB760E5', 'status': 'ok'}
        actualResult = create._create(userParms)
        self.assertEquals(expectedResult['status'], actualResult['status'])
        