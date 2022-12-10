package day_10

import java.io.File

const val fileName = "src/main/resources/day_10/input"

fun main(args: Array<String>) {
    println(getRegisterSums())
}

fun getRegisterSums(): Int {
    val lines = readFileAsLinesUsingUseLines(fileName)
    var register = 1
    var queue: MutableList<Int> = mutableListOf()
    queue.add(0)

    lines.forEach { line ->
        var values = line.split(' ')
        when (values[0]) {
            "noop" -> queue.add(0)
            "addx" -> {
                queue.add(values[1].toInt())
                queue.add(0)
            }
        }
    }

    var currentPixel = 0
    queue.forEach { value ->

        if (checkPixel(currentPixel, register)) {
            print('#')
        } else {
            print('.')
        }

        if (currentPixel == 39) {
            currentPixel = 0
            print("\n")

        } else {
            currentPixel++
        }

        register += value
    }

    print("\n")
    return 0
}

fun checkPixel(currentPixel: Int, register: Int): Boolean {
    when (currentPixel) {
        0 -> {
            for (i in 0 until 3) {
                if (register == i) {
                    return true
                }
            }
        }
        39 -> {
            for (i in 37 until 39) {
                if (register == i) {
                    return true
                }
            }
        }
        else -> {
            for (i in currentPixel - 1 .. currentPixel + 1) {
                if (register == i) {
                    return true
                }
            }
        }
    }
    return false
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
