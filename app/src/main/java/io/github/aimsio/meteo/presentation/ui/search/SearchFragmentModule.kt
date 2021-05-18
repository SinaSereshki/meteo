package io.github.aimsio.meteo.presentation.ui.search

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.aimsio.meteo.di.ViewModelKey

@Module
internal abstract class SearchFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel
}