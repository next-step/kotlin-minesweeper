package domain

sealed class Block(val mark: String) {
    init {
        require(mark.length == MARK_LENGTH) { "mark의 허용 길이는 1입니다." }
    }

    override fun toString(): String = mark

    companion object {
        const val MARK_LENGTH = 1
    }
}
