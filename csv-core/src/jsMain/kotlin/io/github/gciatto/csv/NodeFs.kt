@file:JsModule("fs")
@file:JsNonModule

package io.github.gciatto.csv

external fun readFileSync(path: String, options: dynamic = definedExternally): String

external fun writeFileSync(file: String, data: String, options: dynamic = definedExternally)
