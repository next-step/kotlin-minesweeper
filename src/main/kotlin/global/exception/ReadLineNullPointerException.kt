package global.exception

class ReadLineNullPointerException : RuntimeException() {
    override val message: String = MESSAGE

    companion object {
        private const val MESSAGE = "입력된 값이 null 입니다"
    }
}
