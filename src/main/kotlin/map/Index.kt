package map

data class Index(
    val value: Int,
    val maxSize: Int,
) {
    companion object {
        fun create(
            value: Int,
            maxSize: Int,
        ): Index? = if (value in 0 until maxSize) Index(value, maxSize) else null
    }
}

// fun Int.toIndex() = Index(this)
