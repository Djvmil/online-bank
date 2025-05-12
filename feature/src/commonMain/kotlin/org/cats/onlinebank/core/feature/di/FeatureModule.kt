package org.cats.onlinebank.core.feature.di

import org.cats.onlinebank.core.feature.ui.detail.DetailViewModel
import org.cats.onlinebank.core.feature.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
  viewModel { HomeViewModel(get(), get()) }
  viewModel { DetailViewModel(get(), get(),get()) }
}
