//object LearningScala2 {
  // Flow control
  
  // If / else syntax
  if (1 > 3) println("Impossible!") else println("The world makes sense.")

  if (1 > 3) {
  	println("Impossible!")
  } else {
  	println("The world makes sense.")
  }
  
  // Matching - like switch in other languages:
  val number = 3
  number match {
  	case 1 => println("One")
  	case 2 => println("Two")
  	case 3 => println("Three")
  	case _ => println("Something else")
 	}
 	
 	// For loops
 	for (x <- 1 to 4) {
 		val squared = x * x
 		println(squared)
 	}

  // While loops
  var x = 10
  while (x >= 0) {
  	println(x)
  	x -= 1
  }

  x = 0
  do { println(x); x+=1 } while (x <= 10)
                                                  
   // Expressions
   // "Returns" the final value in a block automatically
   
   {val x = 10; x + 20}

	 println({val x = 10; x + 20})
	 
	 // EXERCISE
	 // Write some code that prints out the first 10 values of the Fibonacci sequence.
	 // This is the sequence where every number is the sum of the two numbers before it.
	 // So, the result should be 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
	 var first = 0
	 var second = 1
	 println(first) //first value
	 println(second)//second value
	 //starting with third value
	 for (x <- 3 to 10) {
	 			val value = first + second
	 			first = second
	 			second = value
	 			println(value)
	 }

//One line solution
val fibs: Stream[Int] = 0 #:: fibs.scanLeft(1)(_ + _)
fibs.take(10).toList
//}