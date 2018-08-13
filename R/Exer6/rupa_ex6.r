# takes an input c() (alternating x y values) and converts it to matrix
# sample:
# x  y
# 1  1
# 2  4
# 3  6
# formatMatrix(c(1, 1, 2, 4, 3, 6))
formatMatrix = function(x) {
  return(matrix(x, ncol = 2, byrow = TRUE))
}

# same as formatMatrix but for 3 columns
formatMatrix2 = function(x) {
  return(matrix(x, ncol = 3, byrow = TRUE))
}

formatMatrixN = function(x, n) {
  return(matrix(x, ncol = n, byrow = TRUE))
}

# sort matrix based on first column
sortMatrix = function(x) {
  return(x[order(x[,1]),])
}

# L function
L = function(i, matrix, order) {
  outputExpression = ""
  
  for(j in 1:(order + 1)) {
    if(j != i) {
      outputExpression = paste(outputExpression, "((", sep="")
      outputExpression = paste(outputExpression, "x-", toString(matrix[j, 1]), sep="")
      outputExpression = paste(outputExpression, ")/(", sep="")
      outputExpression = paste(outputExpression, toString(matrix[i, 1]), "-", toString(matrix[j, 1]), sep="")
      outputExpression = paste(outputExpression, "))", sep="")
    }
    else {
      outputExpression = paste(outputExpression, "1", sep="")
    }
    if(j != order + 1) {
      outputExpression = paste(outputExpression, "*", sep="")
    }
  }
  
  return(outputExpression)
}

Lagrange = function(matrix) {
  # sort matrix
  matrix = sortMatrix(matrix)
  
  outputFunction = "function(x) "
  
  order = nrow(matrix) - 1
  
  # iterate per terms
  for(i in 1:(order + 1)) {
    outputFunction = paste(outputFunction, L(i, matrix, order), "*", toString(matrix[i, 2]), sep="")
    
    if(i != order + 1) {
      outputFunction = paste(outputFunction, " + ", sep="")
    }
  }
  
  return(list(
    f = eval(parse(text = outputFunction))
  ))
}

# given a matrix and an x value, sorts the matrix according to their closeness to x
sortCloseness = function(matrix, x) {
  points = c()
  
  for(i in 1:nrow(matrix)) {
    points = c(points, matrix[i,1], matrix[i,2], abs(x - matrix[i,1]))
  }
  
  points = sortMatrix(formatMatrix2(points))
  
  return(points[,-3])
}

# P function
P = function(i, k, x, matrix) {
  if(k == 0) {
    return(matrix[i, 2])
  }
  
  numerator = ((x - matrix[i, 1]) * P(i + 1, k - 1, x, matrix)) + ((matrix[i + k, 1] - x) * P(i, k - 1, x, matrix))
  
  denominator = matrix[i + k, 1] - matrix[i, 1]
  
  return(numerator/denominator)
}

Neville = function(matrix, x, verbose = FALSE) {
  # sort by closeness
  matrix = sortCloseness(matrix, x)
  
  if(verbose) {
    plot(matrix)
    lines(matrix)
    
    table = c()
    
    for(i in 1:nrow(matrix)) {
      for(k in 0:(nrow(matrix) - 1)) {
        if(k <= (nrow(matrix) - i)) {
          table = c(table, P(i, k, x, matrix))
        }
        else {
          table = c(table, "--")
        }
      }
    }
    
    print(formatMatrixN(table, nrow(matrix)))
  }
  
  return(list(
    fx=P(1, nrow(matrix) - 1, x, matrix)
  ))
}

x = formatMatrix(c(1995,68349452,2000,75505061,2005,82079348,2010,87940171,2015,93440274))

# Neville(x, 2, verbose=TRUE)
# 
# a = c()
# 
# for(i in c(1995,2000,2005,2010,2015,2004)) {
#   a = c(a, i, Neville(x, i)$fx)
# }
# 
# a = sortMatrix(formatMatrix(a))
# 
# lines(a,col="red")
# points(a,col="red")