//object LearningScala3 {
  // Functions
  
  // Format is def <function name>(parameter name: type...) : return type = { expression }
  // Don't forget the = before the expression!
  def squareIt(x: Int) : Int = {
  	x * x
  }
  
  def cubeIt(x: Int): Int = {x * x * x}
  
  println(squareIt(2))
  
  println(cubeIt(2))
  
  // Functions can take other functions as parameters
  
  def transformInt(x: Int, f: Int => Int) : Int = {
  	f(x)
  }
  
  val result = transformInt(2, cubeIt)
  println (result)
  
  // "Lambda functions", "anonymous functions", "function literals"
  // You can declare functions inline without even giving them a name
  // This happens a lot in Spark.
  transformInt(3, x => x * x * x)
  
  transformInt(10, x => x / 2)
  
  transformInt(2, x => {val y = x * 2; y * y})
  
  // This is really important!
  
  // EXERCISE
  // Strings have a built-in .toUpperCase method. For example, "foo".toUpperCase gives you back FOO.
  // Write a function that converts a string to upper-case, and use that function of a few test strings.
  // Then, do the same thing using a function literal instead of a separate, named function.
  
//}