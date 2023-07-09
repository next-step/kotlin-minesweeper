package domain

object EmptyElement : MapElement {
    private const val EMPTY_ELEMENT_CHAR = "C"

    override val displayCharacter = EMPTY_ELEMENT_CHAR

    override fun toString(): String {
        return displayCharacter
    }
}
