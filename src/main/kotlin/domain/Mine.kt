package domain

object Mine : MapElement {
    private const val MINE_DISPLAY_CHAR = "*"

    override val displayCharacter = MINE_DISPLAY_CHAR

    override fun toString(): String {
        return displayCharacter
    }
}
