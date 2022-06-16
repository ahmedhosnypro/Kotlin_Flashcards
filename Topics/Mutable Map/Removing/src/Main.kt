fun removing(currentMap: MutableMap<Int, String>, value: String): MutableMap<Int, String> {
    val ret = currentMap.toMutableMap()
    currentMap.keys.stream().filter { k -> currentMap[k] == value }.forEach { k -> ret.remove(k) }
    return ret

}