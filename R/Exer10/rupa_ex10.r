MullerMethod = function(f, x0, x1, x2, macheps, max, verbose=TRUE) {
  given_x0=x0
  given_x1=x1
  given_x2=x2
  ea=100
  iterations=0
  
  while(ea >= macheps && iterations != max) {
    y0 = f(x0)
    y1 = f(x1)
    y2 = f(x2)
    
    h0 = h(x0, x1)
    h1 = h(x1, x2)
    d0 = d(x0, x1, h0, f)
    d1 = d(x1, x2, h1, f)
    
    A = (d1 - d0)/(h1 + h0)
    B = A * h1 + d1
    C = f(x2)
    
    if(abs(B - sqrt(B^2 - 4 * A * C)) < abs(B + sqrt(B^2 - 4 * A * C))) {
      sign = "positive"
      form = abs(B + sqrt(B^2 - 4 * A * C))
    }
    else {
      sign = "negative"
      form = abs(B - sqrt(B^2 - 4 * A * C))
    }
    
    x3 = x2 - (2 * C) / form
    
    ea = abs((x3 - x2) / x3) * 100
    
    print(paste("Iteration", iterations + 1))
    print(paste("x0:", x0))
    print(paste("x1:", x1))
    print(paste("x2:", x2))
    print(paste("f(x0):", f(x0)))
    print(paste("f(x1):", f(x1)))
    print(paste("f(x2):", f(x2)))
    print(paste("A:", A))
    print(paste("B:", B))
    print(paste("C:", C))
    print(paste("x3:", x3))
    print(paste("ea:", ea))
    print("")
    
    x0 = x1
    x1 = x2
    x2 = x3
    
    iterations = iterations + 1
  }
  
  return(list(
    f=f,
    given_x0=given_x0,
    given_x1=given_x1,
    given_x2=given_x2,
    x3=x3,
    iterations=iterations,
    ea=ea
  ))
}

h = function(x0, x1) {
  return(x1 - x0)
}

d = function(x0, x1, h, f) {
  return((f(x1) - f(x0))/h)
}