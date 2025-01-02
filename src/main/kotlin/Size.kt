data class Size(
    val width: Int,
    val height: Int
) {
    init {
        require(height in MIN_SIZE..MAX_SIZE) {
            "지뢰판의 높이는 1보다 크거나 같고 100보다 작거나 같아야 합니다."
        }

        require(width in MIN_SIZE..MAX_SIZE) {
            "지뢰판의 너비는 1보다 크거나 같고 100보다 작거나 같아야 합니다."
        }
    }

    companion object {
        private const val MIN_SIZE = 1
        private const val MAX_SIZE = 100
    }
}
