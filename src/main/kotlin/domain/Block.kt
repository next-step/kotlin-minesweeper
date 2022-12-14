package domain

sealed class Block(
    val coordinate: Coordinate,
    val desc: String
) {

    init {
        require(desc.length == DESC_LENGTH) { "desc는 1글자만 가능해요." }
    }

    companion object {
        private const val DESC_LENGTH = 1
    }
}
