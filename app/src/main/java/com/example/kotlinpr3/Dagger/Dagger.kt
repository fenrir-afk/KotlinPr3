package com.example.kotlinpr3.Dagger

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.kotlinpr3.activities.MealActivity
import com.example.kotlinpr3.model.Meal
import com.example.kotlinpr3.model.MealSimple
import com.example.kotlinpr3.retrofit.MealApi
import com.example.kotlinpr3.room.MealDao
import com.example.kotlinpr3.room.MealDatabase
import com.example.kotlinpr3.room.MealRepository
import com.example.kotlinpr3.ui.FavoritsFragment
import com.example.kotlinpr3.ui.HomeFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,databases::class])
interface AppComponent{

    fun inject(homeFragment: HomeFragment)
    fun inject(favoritsFragment: FavoritsFragment)
    fun inject(mealActivity: MealActivity)

}
@Module(includes = [Lists::class])
class AppModule(private val application: Application){
    @Provides
    fun provideMealApi(): MealApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApi::class.java)
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application.applicationContext
}

@Module
class databases{
    @Provides
    fun provideMealRepository(
        dao: MealDao
    ): MealRepository = MealRepository(dao)

    @Provides
    fun provideDao(
        db: MealDatabase,
    ): MealDao = db.mealDao()

    @Provides
    fun providesMealDataBase(
        context: Context
    ): MealDatabase = MealDatabase.getInstance(context)
}
@Module
class Lists{
    @Provides
    fun provideMutableLiveDataMeal():MutableLiveData<Meal>{
        return MutableLiveData<Meal>()
    }
    @Provides
    fun provideMutableLiveDataListOfSimpleMeals(): MutableLiveData<List<MealSimple>> {
        return MutableLiveData<List<MealSimple>>()
    }
}