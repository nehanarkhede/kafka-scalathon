val capitals = Map("USA" -> "Washington DC", "China" -> "Beijing", "India" -> "New Delhi")
println("USA: " + capitals.get("USA"))
println("India: " + capitals.get("India"))
println("England: " + capitals.get("England"))

// try to get the values mapped to the keys
println("USA: " + capitals.get("USA").get)
println("USA: " + capitals.get("England").getOrElse("Not found"))

def getCapital(key: String) = {
	val capital = capitals.get(key)
	capital match {
		case Some(c) => println("Capital city = " + c)
		case None => println("Too bad, this country doesn't have a capital")
	}
}