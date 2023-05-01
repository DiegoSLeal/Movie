package com.movie.di

import androidx.navigation.NavController
import com.movie.data.db.database.GenreDatabase
import com.movie.data.db.database.MovieDatabase
import com.movie.data.remote.retrofit.HttpClientRetrofit
import com.movie.data.remote.retrofit.HttpClientRetrofitInterface
import com.movie.data.remote.service.GenreService
import com.movie.data.remote.service.MovieService
import com.movie.data.repository.GenreRepository
import com.movie.data.repository.GenreRepositoryInterface
import com.movie.data.repository.MovieRepository
import com.movie.data.repository.MovieRepositoryInterface
import com.movie.ui.moviedetails.MovieDetailsViewModel
import com.movie.ui.movie.MovieViewModel
import com.movie.util.GlideUtil
import com.movie.util.GlideUtilInterface
import com.movie.util.SearchMovieTitleUtil
import com.movie.util.SearchMovieTitleUtilInterface
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val Module = module {

    factory<HttpClientRetrofitInterface> { HttpClientRetrofit(androidApplication = androidApplication()) }
    factory { get<HttpClientRetrofitInterface>().startRetrofit(MovieService::class.java) }
    factory { get<HttpClientRetrofitInterface>().startRetrofit(GenreService::class.java) }
    factory { MovieDatabase.getInstance(androidContext()).movieDao }
    factory { GenreDatabase.getInstance(androidContext()).genreDao }
    factory<MovieRepositoryInterface> {
        MovieRepository(get(), get())
    }
    factory<GenreRepositoryInterface> {
        GenreRepository(get(), get())
    }
    factory<GlideUtilInterface> {
        GlideUtil()
    }
    factory<SearchMovieTitleUtilInterface> {
        SearchMovieTitleUtil()
    }
    viewModel { (navController: NavController) ->
        MovieViewModel(
            get(), navController = navController, get()
        )
    }
    viewModel { (listGenre: List<Int>) ->
        MovieDetailsViewModel(
            get(),
            get(),
            listGenre = listGenre
        )
    }
}
