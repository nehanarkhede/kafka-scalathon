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