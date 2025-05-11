package org.cats.onlinebank.core.domain.util

interface UseCaseNoInput<Output> {
  suspend operator fun invoke(): Output
}
