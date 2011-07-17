/************************* Tuples **********************/

/* How many times have you wanted to return 2 values from a method ? In languages like Java, you’d have to create another class that holds the values and then return an object of that class. 
*  Scala does this cleanly through tuples. 
*  Tuples represent grouping of objects
*  Syntax is comma-separated list of items surrounded by parenthesis
*  Tuple instances are immutable, 1st class values, so you can assign them to variables, pass them as values and return them from methods
*/

def tupleator(x1: Any, x2: Any, x3: Any) = (x1, x2, x3)

val t = tupleator("Hello", 11, 2.5)
println("Print the whole tuple " + t)
println("Print the 1st item " + t._1)
println("Print the 2nd item " + t._2)
println("Print the last item " + t._3)

val (t1, t2, t3) = tupleator(42, "puzzle", "!")
println(t1 + " " + t2 + t3)

// alternate syntax for tuples
1 -> 2
Tuple2(1, 2)
Pair(1, 2)

/************************* Immutability **********************/

/* Immutable variables leads to better object oriented design and is consistent with pure functional programming 
*  Only specifies whether the reference can be changed (var) or not (val)
*  An immutable val must be initialized where it is declared.
*  Scala requires you to initialize a var when it is declared. You can later keep assigning new values to it
*/

val arr: Array[String] = new Array(3)
arr = new Array(2)
arr(0) = "Hello"
arr
var mutArr: Array[String] = new Array(5)
mutArr = new Array(3)

/************************* Functions **********************/

/* Scala has 1st class functions. Not only can you define functions and call them, but you can write down functions as unnamed literals and pass them around as values. 
*  A function literal (like add) is compiled into a class that when instantiated at run-time is a function value.
*  Function values are like objects, so you can store them in variables if you like. 
*  The => designates that this function converts the thing on the left to the thing on the right
*/

def add(x: Int, y: Int):Int = { x + y }
def incr(x: Int): Int = { x + 1 }
val add = (x: Int, y: Int) => x + y
val incr = (x: Int) => x + 1
object Increment {
	def apply(x: Int) = x + 1
}
Increment(1)

/************************* Type inference **********************/

/* Scala supports type inference. The compiler can discern quite a bit of type information from the context, without explicit type annotations.
Say, we don’t care if map was an instance of Map. It could just be HashMap.
*/

/************************* Default arguments **********************/

/* Scala lets you define default values for some or all of the arguments to a method
*  Named arguments basically allow you to specify the name of the argument while calling a method 
*  Named arguments - why is this useful ?
*  First, if you choose good names for your arguments, then your calls to those methods document each argument with a name.
*  Lets see a few examples - Say you want to pass in a separator other than the default value of " ". The function call for that would look like this. N
*  Now, say that this function took 2 more arguments of the type String. So you see that the function calls in that case would soon become confusing to the reader
*  As long as you picked descriptive names for your arguments, this would lead to readable code
*/

def joiner(strings: List[String], separator: String): String = {
	strings.mkString(separator)
}

def joiner(strings: List[String]): String = {
	strings.mkString(" ")
}

println(joiner(List("Hello", "World")))

def joinerWithDefault(strings: List[String], separator: String = " ") = {
	strings.mkString(separator)	
}

println(joiner(List("Hello", "World")))

/************************* Options **********************/

/* Most languages have a special keyword or object that’s assigned to reference variables when there’s nothing else for them to refer to. In Java, it is null, in Ruby, its nil. 
*  But code is ugly with nulls, you randomly run into NPEs. That happens since its not easy to guess whether or not a null check should be applied to a variable before using it.
*  Also, null is a keyword, not an object, and thus it's illegal to call any methods on it.
*  To be more consistent with the goal of making everything an object, Scala encourages you to use the Option type for variables and function return values when they may or may not refer to a value. 
*  When there is no value, use None, an object that is a subclass of Option. When there is a value, use Some, that just wraps the value
*/

val capitals = Map("USA" -> "Washington DC", "China" -> "Beijing", "India" -> "New Delhi")
println("USA: " + capitals.get("USA"))
println("India: " + capitals.get("India"))
println("England: " + capitals.get("England"))

// try to get the values mapped to the keys
println("USA: " + capitals.get("USA").get)
println("USA: " + capitals.get("England").getOrElse("Not found"))

def getCapitalInUppercase(key: String) = {
	val capital = capitals.get(key)
	capital match {
		case Some(c) => println("Capital city = " + c.toUpperCase)
		case None => println("Too bad, this country doesn't have a capital")
	}
}