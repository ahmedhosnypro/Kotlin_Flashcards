fun addUser(userMap: Map<String, String>, login: String, password: String): MutableMap<String, String> {
    val ret = userMap.toMutableMap()
    if (!userMap.containsKey(login)) {
        ret[login] = password
    } else {
        println("User with this login is already registered!")
    }

    return ret
}