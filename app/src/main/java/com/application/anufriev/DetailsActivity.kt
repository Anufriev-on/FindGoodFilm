package com.application.anufriev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Получаем наш фильм из переданного бандла
        val film = intent.extras?.get("film") as Film


        //Устанавливаем заголовок
        details_toolbar.title = film.title
//Устанавливаем картинку
        details_poster.setImageResource(film.poster)
//Устанавливаем описание
        details_description.text = film.description

    }

}