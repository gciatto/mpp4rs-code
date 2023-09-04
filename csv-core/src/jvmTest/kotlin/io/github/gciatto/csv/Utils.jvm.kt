package io.github.gciatto.csv

import java.io.File

actual fun createTempFile(name: String, content: String): String {
    val (n, e) = name.splitExtension()
    val file = File.createTempFile(n, e)
    file.writeText(content)
    return file.absolutePath
}
