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