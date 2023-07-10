package domain

class EmptyElement : MapElement {
    override val displayCharacter = EMPTY_ELEMENT_CHAR

    companion object {
        private const val EMPTY_ELEMENT_CHAR = "C"
    }
}
