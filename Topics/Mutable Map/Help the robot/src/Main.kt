fun helpingTheRobot(purchases: Map<String, Int>, addition: Map<String, Int>): MutableMap<String, Int> {
    val result = mutableMapOf<String, Int>()
    result.putAll(purchases)
    addition.forEach { (k, v) -> result[k] = purchases.getOrDefault(k, 0) + v }
    return result
}