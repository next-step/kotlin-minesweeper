package domain

open class Finished : Status {
    override fun next() = this
    override fun isFinished() = true
}
