import unittest
import Assignment.primesInRange as primesInRange

class PrimeInRangeTest(unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

# Happy path test
    def test100_ShouldDeterminePrimesOfLowerBounds(self):
        lowBound = 2
        highBound = 10
        expectedResult = [2, 3, 5, 7]
        actualResult = primesInRange.primesInRange(lowBound, highBound)
        self.assertListEqual(expectedResult, actualResult)
        
    def test101_ShouldDeterminePrimesDeclaredInParameter(self):
        expectedResult = [953, 967, 971, 977, 983, 991, 997]
        actualResult = primesInRange.primesInRange(lowBound=950, highBound=1000)
        self.assertListEqual(expectedResult, actualResult)
        
    def test102_ShouldDeterminePrimesOfUpperBounds(self):
        lowBound = 950
        highBound = 1000
        expectedResult = [953, 967, 971, 977, 983, 991, 997]
        actualResult = primesInRange.primesInRange(lowBound, highBound)
        self.assertListEqual(expectedResult, actualResult)
        
    def test103_ShouldDeterminePrimesOfSameNumber(self):
        lowBound = 11
        highBound = 11
        expectedResult = [11]
        actualResult = primesInRange.primesInRange(lowBound, highBound)
        self.assertListEqual(expectedResult, actualResult)
        
    def test104_ShouldDetermineNoPrimesInRange(self):
        lowBound = 34
        highBound = 35
        expectedResult = []
        actualResult = primesInRange.primesInRange(lowBound, highBound)
        self.assertListEqual(expectedResult, actualResult)
        
# Sad Paths
    def test900_ShouldTestNonIntegerLowBound(self):
        expectedResult = None
        actualResult = primesInRange.primesInRange('a', 10)
        self.assertEqual(expectedResult, actualResult)
    def test901_ShouldTestLowBoundNotGT1(self):
        expectedResult = None
        actualResult = primesInRange.primesInRange(1, 10)
        self.assertEqual(expectedResult, actualResult)
    def test902_ShouldTestLowBoundGT1000(self):
        expectedResult = None
        actualResult = primesInRange.primesInRange(1001, 1001)
        self.assertEqual(expectedResult, actualResult)
    def test903_ShouldTestNoLowBound(self):
        expectedResult = None
        actualResult = primesInRange.primesInRange()
        self.assertEqual(expectedResult, actualResult)
    def test904_ShouldTestNonIntegerHighBound(self):
        expectedResult = None
        actualResult = primesInRange.primesInRange(2, 'a')
        self.assertEqual(expectedResult, actualResult)
    def test905_ShouldTestHighBoundNotGT1(self):
        expectedResult = None
        actualResult = primesInRange.primesInRange(1, 1)
        self.assertEqual(expectedResult, actualResult)
    def test906_ShouldTestHighBoundGT1000(self):
        expectedResult = None
        actualResult = primesInRange.primesInRange(1000, 1001)
        self.assertEqual(expectedResult, actualResult)
    def test907_ShouldTestNoHighBound(self):
        expectedResult = None
        actualResult = primesInRange.primesInRange(2)
        self.assertEqual(expectedResult, actualResult)
    def test908_ShouldTestLowBoundGTHighBound(self):
        expectedResult = None
        actualResult = primesInRange.primesInRange(20, 10)
        self.assertEqual(expectedResult, actualResult)
        