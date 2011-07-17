/************************* Tuples **********************/

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

val arr: Array[String] = new Array(3)
arr = new Array(2)
arr(0) = "Hello"
arr
var mutArr: Array[String] = new Array(5)
mutArr = new Array(3)

/************************* Functions **********************/

def add(x: Int, y: Int):Int = { x + y }
def incr(x: Int): Int = { x + 1 }
val add = (x: Int, y: Int) => x + y
val incr = (x: Int) => x + 1
object Increment {
	def apply(x: Int) = x + 1
}
Increment(1)

/************************* Type inference **********************/

/************************* Default arguments **********************/

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