package io.bloco.template.data.models

data class Counter(val value: Int) {
    operator fun plus(increment: Int): Counter {
        return Counter(value + increment)
    }

    operator fun minus(decrement: Int): Counter {
        return Counter(value - decrement)
    }

    override fun toString(): String {
        return value.toString()
    }
}