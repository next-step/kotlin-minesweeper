package domain

class Mine : MapElement {

    override val displayCharacter = MINE_DISPLAY_CHAR

    companion object {
        private const val MINE_DISPLAY_CHAR = "*"
    }
}
