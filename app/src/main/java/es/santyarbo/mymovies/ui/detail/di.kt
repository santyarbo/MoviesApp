package es.santyarbo.mymovies.ui.detail

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import es.santyarbo.mymovies.di.Movie

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @Movie
    fun provideMovie(savedStateHandle: SavedStateHandle) =
        MovieDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).movie

}