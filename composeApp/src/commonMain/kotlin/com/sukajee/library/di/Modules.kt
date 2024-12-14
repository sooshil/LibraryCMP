package com.sukajee.library.di

import com.sukajee.library.book.data.network.KtorRemoteBookDataSource
import com.sukajee.library.book.data.network.RemoteBookDataSource
import com.sukajee.library.book.data.repository.DefaultRepository
import com.sukajee.library.book.domain.BookRepository
import com.sukajee.library.book.presentation.SelectedBookViewModel
import com.sukajee.library.book.presentation.book_detail.BookDetailViewModel
import com.sukajee.library.book.presentation.book_list.BookListViewModel
import com.sukajee.library.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single {
        HttpClientFactory.create(
            engine = get()
        )
    }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultRepository).bind<BookRepository>()

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)
}