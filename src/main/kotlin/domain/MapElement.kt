package domain
interface MapElement {
    val character: String

    companion object {
        const val MINE_CHAR = "*"
        const val NON_MINE_CHAR = "C"
    }
}
