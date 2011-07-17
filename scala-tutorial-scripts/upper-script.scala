class Upper {
  def upper(strings: String*): Seq[String] = {
	strings.map(s => s.toUpperCase())
  }
}

val up = new Upper
println(up.upper("A", "First", "Scala", "Program"))