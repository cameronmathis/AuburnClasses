import random
import hashlib

'''

    Created on Mar 23, 2021

    @author: Cameron Mathis
    
    This file contains all the code used to shift a given grid.

'''

# declare global variable score
score = 0

'''
Main shift method

returns dictionary
'''
def _shift(userParms):
    # validate the userParms dictionary
    if not isinstance(_isDictionaryValid(userParms), bool):
        return _isDictionaryValid(userParms)
    
    # check is there is a grid
    if (userParms['grid'] == ''):
        return { 'status': 'error: missing grid'}
    # check that the grid is a string
    if (not isinstance(userParms['grid'], str)):
        return { 'status': 'error: invalid grid'}
    
    # map the grid to a matrix
    originalGridAsMatrix =_mapGridToMatrix(userParms['grid'])
    # if the grid is invalid then return an error
    if (originalGridAsMatrix == { 'status': 'error: invalid grid'}):
        return originalGridAsMatrix
    
    # validate score
    if (not _isScoreValid(userParms['score'])):
        return { 'status': 'error: invalid score'}
    # set score value to given score
    global score 
    score = int(userParms['score'])
    
    # check if a shift is possible
    if (not _isShiftPossible(originalGridAsMatrix)):
        return { 'status': 'error: no shift possible'}
    
    # validate integrity
    givenIntegrity = userParms['integrity']
    calculatedIntegrity = _calculateIntegrity(userParms['grid'], userParms['score'])
    if (givenIntegrity.strip() != calculatedIntegrity):
        return { 'status': 'error: bad integrity value'}
    
    # shift the grid as a matrix
    if 'direction' not in userParms:
        userParms['direction'] = 'down'
    shiftedGridAsMatrix = _shiftGrid(userParms['direction'], originalGridAsMatrix)
    # if the direction is invalid then return an error
    if (shiftedGridAsMatrix == { 'status': 'error: invalid direction'}):
        return shiftedGridAsMatrix
    # map the grid back to a string
    shiftedGridAsString = _mapMatrixToString(shiftedGridAsMatrix)
    
    # calculate the new integrity
    integrity = _calculateIntegrity(shiftedGridAsString, str(score))
    
    # calculate the status
    status = _calculateStatus(shiftedGridAsMatrix)
    
    result = {'grid': shiftedGridAsString, 'score': str(score), 'integrity': integrity, 'status': status}
    
    return result

'''
Determines if a dictionary contains all the possible keys

returns True if valid and error if not
'''
def _isDictionaryValid(dictionary):
    if 'grid' not in dictionary:
        return { 'status': 'error: omitted grid'}
    elif 'score' not in dictionary:
        return { 'status': 'error: omitted score'}
    elif 'integrity' not in dictionary:
        return { 'status': 'error: omitted integrity'}
    
    return True

'''
Maps the given grid to a matrix

returns matrix
'''
def _mapGridToMatrix(gridAsString):
    gridAsMatrix = [[0 for column in range(4)] for row in range(4)]

    # get a list of all numbers that could be on the grid
    validNumbers = ['0', '2', '4', '8', '16', '32', '64', '128', '256', '512', '1024', '2048']

    firstIndex = 0
    secondIndex = 1
    numberOfTilesAdded = 0
    for row in range(4):
        for column in range(4):
            tileToEvaluate = gridAsString[firstIndex:secondIndex]
            while (not tileToEvaluate in validNumbers):
                secondIndex += 1
                if (secondIndex - firstIndex > 5 or secondIndex >= len(gridAsString)):
                    return { 'status': 'error: invalid grid'}
                tileToEvaluate = gridAsString[firstIndex:secondIndex]
            if (tileToEvaluate in validNumbers):
                if tileToEvaluate == '2':
                    if gridAsString[firstIndex:secondIndex + 2] == "256":
                        gridAsMatrix[row][column] = tileToEvaluate
                        firstIndex = secondIndex + 2
                        secondIndex = firstIndex + 1
                        numberOfTilesAdded += 1
                    else:
                        gridAsMatrix[row][column] = tileToEvaluate
                        firstIndex = secondIndex
                        secondIndex = firstIndex + 1
                        numberOfTilesAdded += 1
                else:
                    gridAsMatrix[row][column] = tileToEvaluate
                    firstIndex = secondIndex
                    secondIndex = firstIndex + 1
                    numberOfTilesAdded += 1
            elif (secondIndex == 16):
                return { 'status': 'error: invalid grid'}

    if (numberOfTilesAdded != 16):
        return { 'status': 'error: invalid grid'}
    
    return gridAsMatrix

'''
Determines if a shift is possible on the given grid

returns boolean
'''
def _isShiftPossible(gridAsMatrix):
    isShiftPossible = False
    
    # check the matrix for blank tiles
    for row in range(4):
        for column in range(4):
            if (gridAsMatrix[row][column] == '0'):
                isShiftPossible = True
                return isShiftPossible
    
    # check the matrix for matching horizontal tiles
    for row in range(4):
        for column in range(3):
            if (gridAsMatrix[row][column] == gridAsMatrix[row][column + 1]):
                isShiftPossible = True
                return isShiftPossible        
            
    # check the matrix for matching vertical tiles
    for row in range(3):
        for column in range(0):
            if (gridAsMatrix[row][column] == gridAsMatrix[row + 1][column]):
                isShiftPossible = True
                return isShiftPossible   
                
    return isShiftPossible

'''
Validates the given score

returns boolean
'''
def _isScoreValid(score):
    # score has to be a string
    if (not isinstance(score, str)):
        return False
    
    # score had to be of type int
    if (not _isStringAnInt(score)):
        return False
    
    # score has to be even
    if ((int(score) % 2) != 0):
        return False
    
    # score has to be .GE 0
    if (int(score) < 0):
        return False
    
    return True

'''
Calculates the integrity

returns string
'''
def _calculateIntegrity(grid, score):
    stringToHash = grid + '.' + score
    hashObject = hashlib.sha256(str(stringToHash).encode('utf-8'))
    hashResult = hashObject.hexdigest()
    return hashResult.upper()

'''
Determines if given string is an int

returns boolean
'''
def _isStringAnInt(string):
    try: 
        int(string)
        return True
    except ValueError:
        return False
    
'''
Shifts the given grid and keeps up with score

returns matrix
'''
def _shiftGrid(direction, gridAsMatrix):
    # set directions to lower case
    direction = direction.lower()
    
    # shift in given direction
    if (direction == 'down') or (direction == ''):
        shiftedGrid = _transposeGrid(gridAsMatrix)
        shiftedGrid = _reverseGrid(shiftedGrid)
        shiftedGrid = _compressGrid(shiftedGrid)
        shiftedGrid = _mergeGrid(shiftedGrid)
        shiftedGrid = _compressGrid(shiftedGrid)
        shiftedGrid = _reverseGrid(shiftedGrid)
        shiftedGrid = _transposeGrid(shiftedGrid)
    elif (direction == 'up'):
        shiftedGrid = _transposeGrid(gridAsMatrix)
        shiftedGrid = _compressGrid(shiftedGrid)
        shiftedGrid = _mergeGrid(shiftedGrid)
        shiftedGrid = _compressGrid(shiftedGrid)
        shiftedGrid = _transposeGrid(shiftedGrid)
    elif (direction == 'left'):
        shiftedGrid = _compressGrid(gridAsMatrix)
        shiftedGrid = _mergeGrid(shiftedGrid)
        shiftedGrid = _compressGrid(shiftedGrid)
    elif (direction == 'right'):
        shiftedGrid = _reverseGrid(gridAsMatrix)
        shiftedGrid = _compressGrid(shiftedGrid)
        shiftedGrid = _mergeGrid(shiftedGrid)
        shiftedGrid = _compressGrid(shiftedGrid)
        shiftedGrid = _reverseGrid(shiftedGrid)
    else:
        return {'status': 'error: invalid direction'}
    
    # randomly place the new tile
    tileNumber = random.randint(0, 1)
    if (tileNumber == 0):
        tileNumber = '2'
    else:
        tileNumber = '4'
    rowIndex = random.randint(0, 3)
    columnIndex = random.randint(0, 3)
    while (shiftedGrid[rowIndex][columnIndex] != '0'):
        rowIndex = random.randint(0, 3)
        columnIndex = random.randint(0, 3)
    shiftedGrid[rowIndex][columnIndex] = tileNumber
         
    return shiftedGrid


'''
Compresses all tiles on the given grid

returns matrix
'''
def _compressGrid(gridAsMatrix):
    shiftedGrid = [['0' for column in range(4)] for row in range(4)]     
    
    for row in range(4):
        position = 0
        for column in range(4):
            if (gridAsMatrix[row][column] != '0'):
                shiftedGrid[row][position] = gridAsMatrix[row][column]
                position += 1
                
    return shiftedGrid

'''
Merges all tiles on the given grid

returns matrix
'''
def _mergeGrid(gridAsMatrix):
    for row in range(4):
        for column in range(3):
            if (gridAsMatrix[row][column] == gridAsMatrix[row][column + 1]) and (gridAsMatrix[row][column] != '0'):
                gridAsMatrix[row][column] = str(int(gridAsMatrix[row][column]) + int(gridAsMatrix[row][column]))
                global score
                score = score + int(gridAsMatrix[row][column])
                gridAsMatrix[row][column + 1] = '0'
                
    return gridAsMatrix

'''
Flips all tiles on the given grid

returns matrix
'''
def _reverseGrid(gridAsMatrix):
    shiftedGrid=[]
    
    for row in range(4):
        shiftedGrid.append([])
        for column in range(4):
            shiftedGrid[row].append(gridAsMatrix[row][3 - column])
            
    return shiftedGrid

'''
Swaps all rows and columns on the given grid

returns matrix
'''
def _transposeGrid(gridAsMatrix):
    shiftedGrid=[['0' for column in range(4)] for row in range(4)]
    
    for row in range(4):
        for column in range(4):
            shiftedGrid[row][column] = gridAsMatrix[column][row]
            
    return shiftedGrid

'''
Maps given grid as matrix to a string

returns string
'''
def _mapMatrixToString(gridAsMatrix):
    gridAsString = ''

    for row in range(4):
        for column in range(4):
            gridAsString += gridAsMatrix[row][column]

    return gridAsString

'''
Calculates the status of the given grid

returns string
'''
def _calculateStatus(gridAsMatrix):
    doesContainEmptyCell = False
    
    for row in range(4):
        for column in range(4):
            if (gridAsMatrix[row][column] == '0'):
                doesContainEmptyCell = True
            elif (gridAsMatrix[row][column] == '2048'):
                return 'win'

    if (not doesContainEmptyCell and not _isShiftPossible(gridAsMatrix)):
        return 'lose'

    return 'ok'
