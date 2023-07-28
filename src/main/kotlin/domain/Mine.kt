package domain

class Mine private constructor(override val displayCharacter: String, override val location: Location) : MapElement {

    companion object {
        private const val MINE_DISPLAY_CHAR = "*"

        fun create(x: Int, y: Int): Mine {
            return Mine(MINE_DISPLAY_CHAR, Location(x, y))
        }
    }
}
