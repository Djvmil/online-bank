package org.cats.onlinebank.domain.util

interface UseCaseNoInput<Output> {
  suspend operator fun invoke(): Output
}
