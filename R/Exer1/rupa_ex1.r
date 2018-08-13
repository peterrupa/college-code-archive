# determines whether the given matrix is a square matrix or not
SquareMatrix = function(mat) {
  # get dimensions
  dimensions = dim(mat)
  
  return(dimensions[1] == dimensions[2])
}

# returns the matrix minor with respect to i (row) and j (column)
MatrixMinor = function(mat, i, j) {
  return(mat[-i, -j])
}

# returns the cofactor of a matrix with respect to i (row) and j (column)
MatrixCofactor = function(mat, i, j) {
  return((-1)^(i + j) * det(MatrixMinor(mat, i, j)))
}

# returns the adjoint of a given matrix
MatrixAdjoint = function(mat) {
  # check if input is a square matrix
  if(!SquareMatrix(mat)) {
    return(NA)
  }
  
  # get dimensions
  dimensions = dim(mat)
  
  # initialize output matrix
  out = matrix(nrow = dimensions[1], ncol = dimensions[2])
  
  # iterate every element
  for(i in 1:dimensions[1]) {
    for(j in 1:dimensions[2]) {
      out[i,j] = MatrixCofactor(mat, i, j)
    }
  }
  
  return(t(out))
}

# returns the inverse of a given matrix
MatrixInverse = function(mat) {
  # check if input is a square matrix
  if(!SquareMatrix(mat)) {
    return(NA)
  }
  
  return((1/det(mat)) * MatrixAdjoint(mat))
}