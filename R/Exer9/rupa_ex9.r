SecantMethod = function(f, x0, x1, macheps, max, verbose=TRUE) {
  given_x0 = x0
  given_x1 = x1
  ea = 100
  num_iterations = 0
  
  y0 = f(x0)
  y1 = f(x1)
  
  while(ea >= macheps && num_iterations != max) {
    x = x1 - (x1 - x0) * y1 / (y1 - y0)
    y = f(x)
    
    if(f(x) == 0) {
      return(x)
    }
    if(num_iterations != 0) {
      ea = abs((x - x_old) / x) * 100
    }
    
    if(verbose) {
      print(paste("Iteration", num_iterations + 1))
      print(paste("xi-1:", x0))
      print(paste("f(xi-1):", f(x0)))
      print(paste("xi:", x1))
      print(paste("f(xi):", f(x1)))
      print(paste("xi+1:", x))
      print(paste("f(xi+1):", f(x)))
      print(paste("ea:", ea))
      print(paste(""))
    }
    
    x0 = x1
    y0 = y1
    x1 = x
    y1 = y
    
    num_iterations = num_iterations + 1
    x_old = x
  }
  
  return(list(
    f=f,
    given_x0=given_x0,
    given_x1=given_x1,
    x=x,
    iterations=num_iterations,
    ea=ea
  ))
}