package day_6

import java.io.File

const val fileName = "src/main/resources/day_6/input"

fun main(args: Array<String>) {
    println(getTransmissionStart())
    println(getMessageStart())
}

fun getTransmissionStart(): Int {
    val lines = readFileAsLinesUsingUseLines(fileName)
    lines.forEach { line ->
        if (line.isNotEmpty()) {
            val lineChars = line.toCharArray()
            for (i in lineChars.indices) {
                var seequence:MutableSet<Char> = mutableSetOf()
                for (ii in i until i + 4) {
                    seequence.add(lineChars[ii])
                    if (seequence.size == 4) {
                        return ii + 1
                    }
                }
            }

        }
    }
    return 0
}

fun getMessageStart(): Int {
    val lines = readFileAsLinesUsingUseLines(fileName)
    lines.forEach { line ->
        if (line.isNotEmpty()) {
            val lineChars = line.toCharArray()
            for (i in lineChars.indices) {
                var seequence:MutableSet<Char> = mutableSetOf()
                for (ii in i until i + 14) {
                    seequence.add(lineChars[ii])
                    if (seequence.size == 14) {
                        return ii + 1
                    }
                }
            }

        }
    }
    return 0
}


fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
