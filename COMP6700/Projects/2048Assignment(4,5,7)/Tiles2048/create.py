import random
import hashlib

'''

    Created on Mar 5, 2021

    @author: Cameron Mathis
    
    This file contains all the code used to create a starting grid.

'''

def _create(userParms):
    grid = _generateGrid()
    score = _calculateScore()
    integrity = _calculateIntegrity(grid, score)
    status = _calculateStatus()
    result = {'grid': grid, 'score': score, 'integrity': integrity, 'status': status}
    return result

def _generateGrid():
    numberOfCells = 16
    
    index1 = random.randint(0, numberOfCells - 1)
    index2 = random.randint(0, numberOfCells - 1)
    while (index1 == index2):
        index1 = random.randint(0, numberOfCells - 1)
        index2 = random.randint(0, numberOfCells - 1)
        
    gridString = ''
    for index in range(16):
        if (index == index1) or (index == index2):
            gridString += '2'
        else:
            gridString += '0'
            
    return gridString

def _calculateScore():
    return '0'

def _calculateIntegrity(grid, score):
    stringToHash = grid + '.' + score
    hashObject = hashlib.sha256(str(stringToHash).encode('utf-8'))
    hashResult = hashObject.hexdigest()
    return hashResult.upper()

def _calculateStatus():
    return 'ok'
