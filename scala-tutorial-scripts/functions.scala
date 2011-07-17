def add(x: Int, y: Int):Int = { x + y }
def incr(x: Int): Int = { x + 1 }
add(2,3)
incr(1)
val add = (x: Int, y: Int) => x + y
add(4,5)
val incr = (x: Int) => x + 1
object Increment {
	def apply(x: Int) = x + 1
}
Increment(1)