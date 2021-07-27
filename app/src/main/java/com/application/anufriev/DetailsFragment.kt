package com.application.anufriev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment



import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
   // override fun onCreate(savedInstanceState: Bundle?) {
   //     super.onCreate(savedInstanceState)
      //  setContentView(R.layout.fragment_details)


   // }







     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            //  setContentView(R.layout.fragment_details)


         //Получаем наш фильм из переданного бандла
         val film = arguments?.get("film") as Film

         //Устанавливаем заголовок
         details_toolbar.title = film.title
//Устанавливаем картинку
         details_poster.setImageResource(film.poster)
//Устанавливаем описание
         details_description.text = film.description


    }


}


