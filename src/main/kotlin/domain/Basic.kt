package domain

class Basic : Cell() {
    var isOpen: Boolean = false
        private set
    var count = 0
        private set

    fun addCount(count: Int) {
        this.count += count
    }

    fun open() {
        this.isOpen = true
    }
}
