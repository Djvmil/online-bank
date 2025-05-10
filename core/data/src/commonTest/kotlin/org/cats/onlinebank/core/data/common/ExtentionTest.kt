package org.cats.onlinebank.core.data.common

import java.io.FileNotFoundException
import java.net.URL

fun String.readFile(): String {
  val content: URL? = ClassLoader.getSystemResource(this)

  return content?.readText() ?: throw FileNotFoundException("file path: $this was not found")
}
