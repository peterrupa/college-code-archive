BisectionMethod = function(f, a, b, macheps, max, verbose=TRUE) {
  if(f(a) * f(b) < 0) {
    given_a = a
    given_b = b
    ea = 100
    num_iterations = 0
    c_old = NULL
    
    while(ea > macheps && num_iterations != max) {
      c = (a + b) / 2
      
      if(verbose) {
        print(paste("Iteration", num_iterations + 1))
        print(paste("Xl:", a))
        print(paste("f(a):", f(a)))
        print(paste("Xu:", b))
        print(paste("f(b):", f(b)))
        print(paste("c: (", a, " + ", b, " ) / 2"))
        print(paste("c:", c))
        print(paste("f(c):", f(c)))
      }
      
      if(f(c) == 0) {
        break
      }
      
      if(f(c) < 0 && f(a) > 0) {
        b = c
      }
      else {
        a = c
      }
      
      if(num_iterations != 0) {
        ea = abs(c - c_old) / c * 100
        
        if(verbose) {
          print(paste("ea:", ea))
          print("")
        }
      }
      
      c_old = c
      
      num_iterations = num_iterations + 1
    }
    
    return(list(
      f=f,
      given_a=given_a,
      given_b=given_b,
      c=c,
      iterations=num_iterations,
      ea=ea
    ))
  }
  else {
    return("Error")
  }
}

FalsePositionMethod = function(f, a, b, macheps, max, verbose=TRUE) {
  if(f(a) * f(b) < 0) {
    given_a = a
    given_b = b
    ea = 100
    num_iterations = 0
    c_old = NULL
    
    while(ea > macheps && num_iterations != max) {
      c = (b * f(a) - a * f(b)) / (f(a) - f(b))
      
      if(verbose) {
        print(paste("Iteration", num_iterations + 1))
        print(paste("Xl:", a))
        print(paste("f(Xl):", f(a)))
        print(paste("Xu:", b))
        print(paste("f(Xu):", f(b)))
        print(paste("Xr: (", b, " * ", f(a), " - ", a, " * ", f(b), ") / (", f(a), " - ", f(b), ")"))
        print(paste("Xr:", c))
        print(paste("f(Xr):", f(c)))
      }
      
      if(f(c) == 0) {
        break
      }
      
      if(f(c) < 0 && f(a) > 0) {
        b = c
      }
      else {
        a = c
      }
      
      if(num_iterations != 0) {
        ea = abs(c - c_old) / c * 100
        
        if(verbose) {
          print(paste("ea:", ea))
          print("")
        }
      }
      
      c_old = c
      
      num_iterations = num_iterations + 1
    }
    
    return(list(
      f=f,
      given_a=given_a,
      given_b=given_b,
      c=c,
      iterations=num_iterations,
      ea=ea
    ))
  }
  else {
    return("Error")
  }
}