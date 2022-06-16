fun summator(map: Map<Int, Int>): Int {
    return map.keys.stream().filter {
        it % 2 == 0
    }
        .mapToInt {
            map.getOrDefault(it, 0)
        }.sum()
}