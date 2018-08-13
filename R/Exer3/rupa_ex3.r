AugCoeffMatrix = function(eqs) {
  deparsedEquations = list()
  deparsedUnknowns = list()
  unknownsList = list()
  unknowns = c(NULL)
  
  # deparse and split input into unknowns and equations
  for(equation in eqs) {
    deparsed = deparse(equation, width.cutoff = 500)
    
    deparsedUnknowns = c(deparsedUnknowns, deparsed[[1]])
    deparsedEquations = c(deparsedEquations, deparsed[[2]])
  }
  
  # get unknowns
  for(deparsedUnknown in deparsedUnknowns) {
    splittedUnknowns = strsplit(substring(deparsedUnknown, 11, nchar(deparsedUnknown) - 2), ", ")[[1]]
    
    for(splittedUnknown in splittedUnknowns) {
      if(!(splittedUnknown %in% unknownsList)) {
        unknownsList = c(unknownsList, splittedUnknown)
      }
    }
    
    for(unknown in unknownsList) {
      if(!(unknown %in% unknowns)) {
        unknowns = c(unknowns, unknown)
      }
    }
  }
  
  # get coefficients
  coefficientsWithUnknownsList = list()
  
  coefficientsList = list()
  
  # split terms
  for(deparsedEquation in deparsedEquations) {
    splitted = strsplit(deparsedEquation, "\\+")
    trimSplitted = c()
    
    for(item in splitted) {
      trimSplitted = c(trimSplitted, trimws(item))
    }
    
    coefficientsWithUnknownsList = c(coefficientsWithUnknownsList, list(trimSplitted))
  }
  
  # get actual coefficients
  equationsPartial = c(NULL)
  
  for(unknown in unknowns) {
    for(coefficientsWithUnknown in coefficientsWithUnknownsList) {
      
      term = grep(unknown, coefficientsWithUnknown, value=TRUE)
      coefficient = strsplit(term, " ")[[1]][1]
      
      if(!grepl("^-?(\\d*\\.)?\\d{1,}$", coefficient)) {
        coefficient = 1
      }
      else {
        coefficient = as.numeric(coefficient)
      }
      
      equationsPartial = c(equationsPartial, coefficient)
    }
  }
  
  # get constants
  constants = c(NULL)
  
  # get matching term with an integer pattern (no unknowns)
  for(coefficientsWithUnknown in coefficientsWithUnknownsList) {
    
    constants = c(constants, -as.numeric(grep("^-?(\\d*\\.)?\\d{1,}$", coefficientsWithUnknown, value=TRUE)))
  }
  
  # put all data in matrix
  equationMatrix = matrix(c(equationsPartial, constants), nrow=length(unknowns), ncol=length(unknowns) + 1)
  
  # assign names
  colnames(equationMatrix) = c(unknowns, "RHS")
  
  return(list(variables=unknowns, coeffMatrix=equationMatrix))
}

swap = function(m, r1, r2) {
  temp = m[r1,]
  
  m[r1,] = m[r2,]
  m[r2,] = temp
  
  return(m)
}

largestRow = function(m) {
  largest = NULL
  numberOfRows = length(m)
  i = 1
  
  while(i <= numberOfRows) {
    if(is.null(largest)) {
      largest = i
    }
    else {
      if(abs(m[i]) > abs(m[largest])) {
        largest = i
      }
    }
    
    i = i + 1
  }
  
  return(largest)
}

pivot = function(m, columnCurrent) {
  largest = largestRow(m[,columnCurrent])
  
  # check if current diagonal is not the largest on its column
  if(largest != columnCurrent) {
    m = swap(m, largest, columnCurrent)
  }
  
  return(m)
}

solveSystem = function(augmentedCoefficients) {
  numberOfRows = dim(augmentedCoefficients)[1]
  numberOfColumns = dim(augmentedCoefficients)[2]
  
  coefficients = augmentedCoefficients[,1:numberOfColumns - 1]
  rhs = augmentedCoefficients[,numberOfColumns]
  
  i = numberOfRows
  solution = c(NULL)
  
  # start at bottom equation
  while(i >= 1) {
    j = numberOfColumns - 1
    
    # multiply previous answers to corresponding coefficients
    for(answer in solution) {
      coefficients[i, j] = coefficients[i, j] * answer
      
      j = j - 1
    }
    
    solution = c(solution, rhs[i] / sum(coefficients[i,]))
    
    i = i - 1
  }
  
  return(solution)
}

Gaussian = function(equations, verbose=TRUE) {
  augmentedCoefficientsResult = AugCoeffMatrix(equations)
  augmentedCoefficients = augmentedCoefficientsResult$coeffMatrix
  variables = augmentedCoefficientsResult$variables
  
  currentRowIndex = 1
  numberOfRows = dim(augmentedCoefficients)[1]
  numberOfColumns = dim(augmentedCoefficients)[2]
  
  if(verbose) {
    print(augmentedCoefficients)
  }
  
  while(currentRowIndex <= numberOfRows - 1) {
    # pivot if applicable
    augmentedCoefficients = pivot(augmentedCoefficients, currentRowIndex)
    
    if(verbose) {
      print(augmentedCoefficients)
    }
    
    # iterate row below
    evalRowIndex = currentRowIndex + 1
    
    while(evalRowIndex <= numberOfRows) {
      # replace eval row with result from formula
      augmentedCoefficients[evalRowIndex,] = augmentedCoefficients[evalRowIndex,] - (augmentedCoefficients[evalRowIndex, currentRowIndex] / augmentedCoefficients[currentRowIndex, currentRowIndex] * augmentedCoefficients[currentRowIndex,])
      
      evalRowIndex = evalRowIndex + 1
      
      if(verbose) {
        print(augmentedCoefficients)
      }
    }
    
    currentRowIndex = currentRowIndex + 1
  }
  
  xs = solveSystem(augmentedCoefficients)
  names(xs) = variables
  
  return(
    list(
      forelem = augmentedCoefficients,
      xs = xs
    )
  )
}

normalize = function(m, n) {
  return(m/n)
}

GaussJordan = function(equations, verbose = TRUE) {
  augmentedCoefficientsResult = AugCoeffMatrix(equations)
  augmentedCoefficients = augmentedCoefficientsResult$coeffMatrix
  variables = augmentedCoefficientsResult$variables
  
  currentRowIndex = 1
  numberOfRows = dim(augmentedCoefficients)[1]
  numberOfColumns = dim(augmentedCoefficients)[2]
  
  if(verbose) {
    print(augmentedCoefficients)
  }
  
  while(currentRowIndex <= numberOfRows) {
    # pivot if applicable
    augmentedCoefficients = pivot(augmentedCoefficients, currentRowIndex)
    
    if(verbose) {
      print(augmentedCoefficients)
    }
    
    # normalize current row
    augmentedCoefficients[currentRowIndex,] = normalize(augmentedCoefficients[currentRowIndex,], augmentedCoefficients[currentRowIndex,currentRowIndex])
    
    if(verbose) {
      print(augmentedCoefficients)
    }
    
    # iterate row below
    evalRowIndex = 1
    
    while(evalRowIndex <= numberOfRows) {
      if(evalRowIndex != currentRowIndex) {
        # replace eval row with result from formula
        augmentedCoefficients[evalRowIndex,] = augmentedCoefficients[evalRowIndex,] - (augmentedCoefficients[evalRowIndex, currentRowIndex] / augmentedCoefficients[currentRowIndex, currentRowIndex] * augmentedCoefficients[currentRowIndex,])
        
        if(verbose) {
          print(augmentedCoefficients)
        }
      }
      
      evalRowIndex = evalRowIndex + 1
    }
    
    currentRowIndex = currentRowIndex + 1
  }
  
  xs = solveSystem(augmentedCoefficients)
  names(xs) = variables
  
  return(
    list(
      forelem = augmentedCoefficients,
      xs = xs
    )
  )
}