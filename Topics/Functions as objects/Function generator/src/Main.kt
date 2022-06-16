fun generate(functionName: String): (Int) -> Int {
    return when (functionName) {
        "identity" -> { x: Int -> x }
        "half" -> { x: Int -> x / 2 }
        else -> { _: Int -> 0 } // includes "zero"
    }
}