package org.cats.onlinebank.core.data.common

import io.ktor.http.Url
import kotlinx.io.files.FileNotFoundException

fun String.readFile(): String {
  val content: Url? = ClassLoader.getSystemResource(this)

  return content?.readText() ?: throw FileNotFoundException("file path: $this was not found")
}
