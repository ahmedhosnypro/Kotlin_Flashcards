    val lambda: (Long, Long) -> Long = { left: Long, right: Long ->
        if (left == right) {
            right
        } else {
            var multi = 1L
            for (i in left..right) {
                multi *= i
            }
            multi
        }
    }