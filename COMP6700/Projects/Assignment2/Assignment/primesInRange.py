def primesInRange(lowBound, highBound):  
    # check lowBound for valid entries
    if (not isinstance(lowBound, int)) or (lowBound <= 1) or (lowBound > 1000): 
        return None
    # check highBound for valid entries
    elif (not isinstance(highBound, int)) or (highBound <= 1) or (highBound > 1000):
        return None
    # checks that lowBound is not greater than highBound
    elif (lowBound > highBound):
        return None
    
    # declare empty list
    primes = []
    # get all primes in range inclusive
    for num in range(lowBound, highBound + 1):
        prime = True
        for i in range(2, num):
            if (num%i == 0):
                prime = False
                break;
        if prime:
            primes.append(num)
    
    # return list of primes in range
    return primes