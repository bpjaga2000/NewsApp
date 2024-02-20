package `in`.bpj4.newsapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.bpj4.newsapp.data.local.NewsDao
import `in`.bpj4.newsapp.data.local.NewsDatabase
import `in`.bpj4.newsapp.data.local.NewsTypeConverter
import `in`.bpj4.newsapp.data.manager.LocalUserManagerImpl
import `in`.bpj4.newsapp.data.remote.NewsApi
import `in`.bpj4.newsapp.data.repository.NewsRepositoryImpl
import `in`.bpj4.newsapp.domain.manager.LocalUserManager
import `in`.bpj4.newsapp.domain.repository.NewsRepository
import `in`.bpj4.newsapp.domain.usecases.appentry.AppEntryUseCases
import `in`.bpj4.newsapp.domain.usecases.appentry.ReadAppEntry
import `in`.bpj4.newsapp.domain.usecases.appentry.SaveAppEntry
import `in`.bpj4.newsapp.domain.usecases.news.DeleteArticle
import `in`.bpj4.newsapp.domain.usecases.news.GetNews
import `in`.bpj4.newsapp.domain.usecases.news.NewsUseCases
import `in`.bpj4.newsapp.domain.usecases.news.SearchNews
import `in`.bpj4.newsapp.domain.usecases.news.SelectArticle
import `in`.bpj4.newsapp.domain.usecases.news.SelectArticles
import `in`.bpj4.newsapp.domain.usecases.news.UpsertArticle
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases {
        return AppEntryUseCases(ReadAppEntry(localUserManager), SaveAppEntry(localUserManager))
    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().client(OkHttpClient.Builder().build())
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository, newsDao: NewsDao): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
            selectArticle = SelectArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun providesNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(application, NewsDatabase::class.java, "NEWS_DB_NAME")
            .addTypeConverter(NewsTypeConverter()).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao {
        return newsDatabase.newsDao
    }

}