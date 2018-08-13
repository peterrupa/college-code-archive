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

# x sub index
x = function(mat, index) {
  return(mat[index + 1, 1])
}

# evaluates function given x
fx = function(mat, x) {
  i = 1
  
  while(i <= nrow(mat)) {
    if(mat[i, 1] == x) {
      return(mat[i, 2])
    }
    
    i = i + 1
  }
}

# b with start and end x
bform = function(mat, xstart, xend) {
  if(xstart - xend == 1) {
    return((fx(mat, x(mat, xstart)) - fx(mat, x(mat, xend))) / (x(mat, xstart) - x(mat, xend)))
  }
  else {
    return((bform(mat, xstart, xend + 1) - bform(mat, xstart - 1, xend)) / (x(mat, xstart) - x(mat, xend)))
  }
}

# general b function
b = function(mat, index) {
  if(index == 0) {
    return(mat[1, 2])
  }
  else {
    return(bform(mat, index, 0))
  }
}

# be sure x follows the mat format
NDD = function(mat) {
  order = nrow(mat) - 1
  
  # get coefficients
  as = list()
  
  for(i in c(0:order)) {
    as = c(as, b(mat, i))
  }
  
  # formulate function
  f = paste("function(x) ", toString(b(mat, 0)))
  
  for(i in c(1:order)) {
    term = toString(b(mat, i))
    
    for(j in c(0:(i - 1))) {
      term = paste(term, "*(x-", x(mat, j), ")", sep = "")
    }
    
    f = paste(f, "+", term, sep = "")
  }
  
  return(list(
    as = as,
    f = eval(parse(text = f))
  ))
}