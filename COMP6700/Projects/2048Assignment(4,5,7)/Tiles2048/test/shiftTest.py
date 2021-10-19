'''

    Created on Mar 23, 2021

    @author: Cameron Mathis
    
    This file contains all the code used to test the shifting of a grid.

'''

import unittest
import Tiles2048.shift as shift

class CreateTest(unittest.TestCase):

    # 100 shift
    #    Desired level of confidence:    boundary value analysis
    #    Input-output Analysis
    #        inputs:
    #        outputs:
    #    Happy path analysis:
    #        output:
    #    Sad path analysis:
    #        output:
    #
    # Happy path
    def test_shift_110_ShouldCalculateIntegrity(self):
        expectedResult = '7CD5E3DEAB08FCAE8F64433DC4A63CC922571EBF60EE1D1938ADCD415FB760E5'
        actualResult = shift._calculateIntegrity('0020000020000000', '0')
        self.assertEquals(expectedResult, actualResult)
        
        
    def test_shift_120_ShouldShiftGridDown(self):
        userParms = {'op': 'shift', 'grid': '0020000020000000', 'score': '0', 'direction': 'down', 'integrity': ' 7CD5E3DEAB08FCAE8F64433DC4A63CC922571EBF60EE1D1938ADCD415FB760E5'}
        expectedResult = {'grid': '0000004000002020', 'score': '0', 'integrity': '0DA3DEE7C5D13224BA4937CCF213B29C57676C36CDFE1C5CFC86ED069C644A17', 'status': 'ok'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['grid'][12:13], actualResult['grid'][12:13])
        self.assertEquals(expectedResult['grid'][14:15], actualResult['grid'][14:15])
        
    def test_shift_130_ShouldShiftGridDownDefault(self):
        userParms = {'op': 'shift', 'grid': '0020000020000000', 'score': '0', 'direction': '', 'integrity': ' 7CD5E3DEAB08FCAE8F64433DC4A63CC922571EBF60EE1D1938ADCD415FB760E5'}
        expectedResult = {'grid': '0000004000002020', 'score': '0', 'integrity': '0DA3DEE7C5D13224BA4937CCF213B29C57676C36CDFE1C5CFC86ED069C644A17', 'status': 'ok'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['grid'][12:13], actualResult['grid'][12:13])
        self.assertEquals(expectedResult['grid'][14:15], actualResult['grid'][14:15])
        
    def test_shift_140_ShouldShiftGridUp(self):
        userParms = {'op': 'shift', 'grid': '2222444488881616160', 'score': '9600', 'direction': 'up', 'integrity': ' 66457746F0596CEE48B4FA4FA9C57A8A56A917F5B42F2600F12CD4266B9098BE'}
        expectedResult = {'grid': '2222444488881616164', 'score': '9600', 'integrity': 'D0322C9B4DCAE2E0001F7BF3F24EFFE875038EA2A81660F77D14C29D7D960685', 'status': 'lose'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['grid'][0:18], actualResult['grid'][0:18])
        
    def test_shift_150_ShouldShiftGridLeft(self):
        userParms = {'op': 'shift', 'grid': '1024102400000000000000', 'score': '129024', 'direction': 'left', 'integrity': ' 18FF0FE71EB8CCFA82556511578B321D0B69A8E2FD5202EBD3A949EB35CB3C45'}
        expectedResult = {'grid': '2048000000000000002', 'score': '131072', 'integrity': '51A7C485E859A94F5FFCCB25C682A66D8671ABB9AF1682756233C17499B4DE68', 'status': 'win'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['grid'][0:4], actualResult['grid'][0:4])
        
    def test_shift_160_ShouldShiftGridRight(self):
        userParms = {'op': 'shift', 'grid': '1024102400000000000000', 'score': '129024', 'direction': 'right', 'integrity': ' 18FF0FE71EB8CCFA82556511578B321D0B69A8E2FD5202EBD3A949EB35CB3C45'}
        expectedResult = {'grid': '0002048000000004000', 'score': '131072', 'integrity': '34897DADCCC51E0FBC3A89B6DC6FAB371EE8E67267CD0B82F071D446C66B43D6', 'status': 'win'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['grid'][3:7], actualResult['grid'][3:7])
    
    def test_shift_170_ShouldSetWinStatus(self):
        userParms = {'op': 'shift', 'grid': '1024102400000000000000', 'score': '129024', 'direction': 'left', 'integrity': ' 18FF0FE71EB8CCFA82556511578B321D0B69A8E2FD5202EBD3A949EB35CB3C45'}
        expectedResult = {'grid': '2048000000000000002', 'score': '131072', 'integrity': '51A7C485E859A94F5FFCCB25C682A66D8671ABB9AF1682756233C17499B4DE68', 'status': 'win'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['status'], actualResult['status'])
        
    def test_shift_180_ShouldSetLoseStatus(self):
        userParms = {'op': 'shift', 'grid': '2222444488881616160', 'score': '9600', 'direction': 'up', 'integrity': ' 66457746F0596CEE48B4FA4FA9C57A8A56A917F5B42F2600F12CD4266B9098BE'}
        expectedResult = {'grid': '2222444488881616164', 'score': '9600', 'integrity': 'D0322C9B4DCAE2E0001F7BF3F24EFFE875038EA2A81660F77D14C29D7D960685', 'status': 'ok'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['status'], actualResult['status'])
        
    def test_shift_190_ShouldCalculateScore(self):
        userParms = {'op': 'shift', 'grid': '1024102400000000000000', 'score': '129024', 'direction': 'left', 'integrity': ' 18FF0FE71EB8CCFA82556511578B321D0B69A8E2FD5202EBD3A949EB35CB3C45'}
        expectedResult = {'grid': '2048000000000000002', 'score': '131072', 'integrity': '51A7C485E859A94F5FFCCB25C682A66D8671ABB9AF1682756233C17499B4DE68', 'status': 'win'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['score'], actualResult['score'])
        
    def test_shift_200_ShouldTestNominalGrid(self):
        userParms = {'op': 'shift', 'grid': '2481632641282565121024220000', 'score': '100', 'direction': 'left', 'integrity': ' F0AD8DA405854A37EAE778AEC53E3963E2AE02B2E71979ACB209FA8C4C76E2A3'}
        expectedResult = {'grid': 'str: 24816326412825121024400200', 'score': '104', 'integrity': ' 75B840AF594C54FB91A023CB169FD275611D21C2C84FA6CEB1DBD47EAFE3C966', 'status': 'ok'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['score'], actualResult['score'])
        
    def test_shift_210_ShouldShiftGridDownOmittedDirection(self):
        userParms = {'op': 'shift', 'grid': '0020000020000000', 'score': '0', 'integrity': ' 7CD5E3DEAB08FCAE8F64433DC4A63CC922571EBF60EE1D1938ADCD415FB760E5'}
        expectedResult = {'grid': '0000004000002020', 'score': '0', 'integrity': '0DA3DEE7C5D13224BA4937CCF213B29C57676C36CDFE1C5CFC86ED069C644A17', 'status': 'ok'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['grid'][12:13], actualResult['grid'][12:13])
        self.assertEquals(expectedResult['grid'][14:15], actualResult['grid'][14:15])
        
    def test_shift_220_ShouldCheckIfShiftIsPossible(self):
        userParms = {'op': 'shift', 'grid': '2222222222222222', 'score': '4', 'direction': 'down', 'integrity': ' 253F2481B895597D337489A7FD3F8B85E132694BBE8371948CD676F9AA545604'}
        expectedResult = {'grid': '4000000044444444',' integrity': 'F532C84E1666502605AF8CE59F8AD0BAE7E6FB3E3F558D5237277A2F016A9FD6', 'score': '36', 'status': 'ok'}
        actualResult = shift._shift(userParms)
        self.assertEquals(expectedResult['score'], actualResult['score'])
        self.assertEquals(expectedResult['grid'][8:15], actualResult['grid'][8:15])
    
    # Dev path
    def test_shift_610_ShouldMapGridToMatrix(self):
        userParms = {'op': 'shift', 'grid': '0020000020000000', 'score': '0', 'direction': 'down', 'integrity': ' 7CD5E3DEAB08FCAE8F64433DC4A63CC922571EBF60EE1D1938ADCD415FB760E5'}
        expectedResult = [ [ '0' for column in range(4) ] for row in range(4) ]
        expectedResult[0][2] = '2'
        expectedResult[2][0] = '2'
        actualResult = shift._mapGridToMatrix(userParms['grid'])
        self.assertEquals(actualResult, expectedResult)
        
    def test_shift_620_ShouldMatrixToString(self):
        expectedResult = '0020000020000000'
        gridAsMatrix = [ [ '0' for column in range(4) ] for row in range(4) ]
        gridAsMatrix[0][2] = '2'
        gridAsMatrix[2][0] = '2'
        actualResult = shift._mapMatrixToString(gridAsMatrix)
        self.assertEquals(actualResult, expectedResult)
        
    # Sad path
    def test_shift_910_ShouldValidateNoGrid(self):
        userParms = {'op': 'shift', 'grid': '', 'score': '4', 'direction': 'down', 'integrity': ' 2A2EF0D1BEA22B9D6AB67C482BFF954F93F6A3617EF052E11DD8776BFFB7325A'}
        expectedResult = { 'status': 'error: missing grid'}
        actualResult = shift._shift(userParms)
        self.assertEquals(actualResult, expectedResult)
    
    def test_shift_920_ShouldValidateGrid(self):
        userParms = {'op': 'shift', 'grid': '000000402440202a', 'score': '4', 'direction': 'down', 'integrity': ' 2A2EF0D1BEA22B9D6AB67C482BFF954F93F6A3617EF052E11DD8776BFFB7325A'}
        expectedResult = { 'status': 'error: invalid grid'}
        actualResult = shift._shift(userParms)
        self.assertEquals(actualResult, expectedResult)
        
    def test_shift_930_ShouldValidateGrid(self):
        userParms = {'op': 'shift', 'grid': '2248161632010245120000052', 'score': '4', 'direction': 'down', 'integrity': ' 9CE182F636152306A87BC22CDA94C8607A925E584FCF34F5896B393ACCAFD6EF'}
        expectedResult = { 'status': 'error: invalid grid'}
        actualResult = shift._shift(userParms)
        self.assertEquals(actualResult, expectedResult)
    
    def test_shift_940_ShouldValidateGrid(self):
        userParms = {'op': 'shift', 'grid': '00000040244', 'score': '4', 'direction': 'down', 'integrity': ' 2A2EF0D1BEA22B9D6AB67C482BFF954F93F6A3617EF052E11DD8776BFFB7325A'}
        expectedResult = { 'status': 'error: invalid grid'}
        actualResult = shift._shift(userParms)
        self.assertEquals(actualResult, expectedResult)
        
    def test_shift_950_ShouldValidateScore(self):
        userParms = {'op': 'shift', 'grid': '0000004024402020', 'score': '33', 'direction': 'down', 'integrity': ' 1875F39BCE84620F9B3273BE921EFF1E541FEAEE10EBBF0858DB30ADF10BE2A9'}
        expectedResult = { 'status': 'error: invalid score'}
        actualResult = shift._shift(userParms)
        self.assertEquals(actualResult, expectedResult)
        
    def test_shift_960_ShouldValidateIntegrity(self):
        userParms = {'op': 'shift', 'grid': '0000004024402020', 'score': '4', 'direction': 'down', 'integrity': ' B942E8D41B41814866B32EA9C9A3A4205ABA77148D86741D1EFE765BE6FEAADB'}
        expectedResult = { 'status': 'error: bad integrity value'}
        actualResult = shift._shift(userParms)
        self.assertEquals(actualResult, expectedResult)
        
    def test_shift_970_ShouldCheckIfShiftIsPossible(self):
        userParms = {'op': 'shift', 'grid': '248163264128256248163264128256', 'score': '9600', 'direction': 'down', 'integrity': ' E57CC76136B59F8F687EA8EDE132F8AA63D3F77E690080133885AAC5AE5EE549 '}
        expectedResult = { 'status': 'error: no shift possible'}
        actualResult = shift._shift(userParms)
        self.assertEquals(actualResult, expectedResult)
        
    def test_shift_980_ShouldValidateDictionary(self):
        userParms = {'op': 'shift', 'score': '4', 'direction': 'down', 'integrity': ' 2A2EF0D1BEA22B9D6AB67C482BFF954F93F6A3617EF052E11DD8776BFFB7325A'}
        expectedResult = { 'status': 'error: omitted grid'}
        actualResult = shift._shift(userParms)
        self.assertEquals(actualResult, expectedResult)
        