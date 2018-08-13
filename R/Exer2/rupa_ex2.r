AugCoeffMatrix = function(eqs) {
  deparsedEquations = list()
  deparsedUnknowns = list()
  unknownsList = list()
  unknowns = c(NULL)
  
  # deparse and split input into unknowns and equations
  for(equation in eqs) {
    deparsed = deparse(equation)
    
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
      
      if(!grepl("^-?\\d$", coefficient)) {
        coefficient = 1
      }
      else {
        coefficient = as.integer(coefficient)
      }
      
      equationsPartial = c(equationsPartial, coefficient)
    }
  }
  
  # get constants
  constants = c(NULL)
  
  # get matching term with an integer pattern (no unknowns)
  for(coefficientsWithUnknown in coefficientsWithUnknownsList) {
    print(coefficientsWithUnknown)
    
    constants = c(constants, -as.integer(grep("^-?\\d{1,}$", coefficientsWithUnknown, value=TRUE)))
  }
  
  # put all data in matrix
  equationMatrix = matrix(c(equationsPartial, constants), nrow=length(unknowns), ncol=length(unknowns) + 1)
  
  # assign names
  colnames(equationMatrix) = c(unknowns, "RHS")
  
  return(list(variables=unknowns, coeffMatrix=equationMatrix))
}