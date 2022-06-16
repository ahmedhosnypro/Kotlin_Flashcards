fun bill(priceList: Map<String, Int>, shoppingList: MutableList<String>): Int {
    return shoppingList.stream().filter {
        priceList.containsKey(it)
    }.mapToInt {
        priceList.getOrDefault(it, 0)
    }.sum()
}