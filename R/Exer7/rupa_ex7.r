# Sample run

# x = formatMatrixN(c(7,11,1,0,0,0,0,77,10,8,0,1,0,0,0,80,1,0,0,0,1,0,0,9,0,1,0,0,0,1,0,6,-150,-175,0,0,0,0,1,0),8,columnNames = c('x1', 'x2', 'S1', 'S2', 'S3', 'S4', 'Z', 'Answer'))

# Simplex(x)

formatMatrixN = function(x, n, columnNames = c(), rowNames = c()) {
  mat = matrix(x, ncol = n, byrow = TRUE)
  
  colnames(mat) = columnNames
  
  return(mat)
}

# check if column is an active
checkActive = function(vec) {
  hasOne = FALSE
  
  for(i in 1:length(vec)) {
    if(vec[i] > 1 || vec[i] < 0) {
      return(FALSE)
    }
    else if(vec[i] == 1 && !hasOne) {
      hasOne = TRUE
    }
    else if(vec[i] == 1 && hasOne) {
      return(FALSE)
    }
  }
  
  return(TRUE)
}

# returns the index of element 1
whereIsOne = function(vector) {
  for(i in 1:length(vector)) {
    if(vector[i] == 1) {
      return(i)
    }
  }
  return(0)
}

# returns a key value pair of the basic solution
basicSolution = function(mat) {
  solution = c()
  
  matCol = ncol(mat)
  
  for(i in 1:(matCol - 1)) {
    solution = c(solution, labels(mat)[2][[1]][i])
    
    if(checkActive(mat[,i])) {
      solution = c(solution, mat[whereIsOne(mat[,i]), matCol])
    }
    else {
      solution = c(solution, 0)
    }
  }
  
  return(formatMatrixN(solution, 2))
}

rowHasNegative = function(vector) {
  for(i in 1:length(vector)) {
    if(vector[i] < 0) {
      return(TRUE)
    }
  }
  return(FALSE)
}

whereIsLargestNegative = function(vector) {
  largest = NULL
  
  for(i in 1:length(vector)) {
    if(vector[i] > -1) {
      next
    }
    
    if(is.null(largest) || vector[i] < vector[largest]) {
      largest = i
    }
  }
  
  return(largest)
}

testRatio = function(mat) {
  smallest = NULL
  
  for(i in 1:nrow(mat)) {
    if(mat[i, 1] > 0 && mat[i, 2] > 0) {
      if(is.null(smallest) || mat[i, 2] / mat[i, 1] < mat[smallest, 2] / mat[smallest, 1]) {
        smallest = i
      }
    }
    else {
      next
    }
  }
  
  return(smallest)
}

selectPivotElement = function(mat) {
  rowNum = nrow(mat)
  colNum = ncol(mat)
  
  # select pivot column
  pivotColumn = whereIsLargestNegative(mat[rowNum, -colNum])
  
  # select pivot element
  pivotRow = testRatio(mat[-rowNum, c(pivotColumn, colNum)])
  
  return(c(pivotRow, pivotColumn))
}

Simplex = function(mat, verbose=FALSE) {
  rowNum = nrow(mat)
  colNum = ncol(mat)
  solution = NULL
  
  while(TRUE) {
    solution = basicSolution(mat)
    
    if(verbose) {
      print(solution)
    }
    
    # check for negative values in bottom row
    if(rowHasNegative(mat[rowNum,-colNum])) {
      # select pivot element
      pivotElementIndex = selectPivotElement(mat)
      
      # normalize
      mat[pivotElementIndex[1],] = mat[pivotElementIndex[1],] / mat[pivotElementIndex[1], pivotElementIndex[2]]
      
      # clear pivot column
      for(i in 1:rowNum) {
        if(i != pivotElementIndex[1]) {
          mat[i,] = mat[i,] - mat[pivotElementIndex[1],] * mat[i, pivotElementIndex[2]]
        }
      }
      
      if(verbose) {
        print(mat)
      }
    }
    else {
      break
    }
  }
  
  return(list(
    solution = solution,
    table = mat
  ))
}

# Sample run

# x = formatMatrixN(c(7,11,1,0,0,0,0,77,10,8,0,1,0,0,0,80,1,0,0,0,1,0,0,9,0,1,0,0,0,1,0,6,-150,-175,0,0,0,0,1,0),8,columnNames = c('x1', 'x2', 'S1', 'S2', 'S3', 'S4', 'Z', 'Answer'))

# Simplex(x)