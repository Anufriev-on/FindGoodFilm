package com.application.anufriev

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment() {

val filmsDataBase = listOf(
    Film("Wheel of Time", R.drawable.poster10, "Set in a high fantasy world where magic exists, but only some can access it, a woman named Moiraine crosses paths with five young men and women. This sparks a dangerous, world-spanning journey. Based on the book series by Robert Jordan."),
    Film("The Auschwitz Report", R.drawable.poster11, "This is the true story of Freddy and Walter - two young Slovak Jews, who were deported to Auschwitz in 1942. On 10 April 1944, after meticulous planning and with the help and the resilience of their inmates, they manage to escape. While the inmates, they had left behind, courageously stand their ground against the Nazi officers, the two men are driven on by the hope that their evidence could save lives. Emaciated and hurt, they make their way through the mountains back to Slovakia. With the help of chance encounters, they finally manage to cross the border and meet the resistance and The Red Cross. They compile a detailed report about the systematic genocide at the camp. However, with Nazi propaganda and international liaisons still in place, their account seems to be too harrowing to believe."),
    Film("The Chair", R.drawable.poster12, "At a major university, the first woman of color to become chair tries to meet the dizzying demands and high expectations of a failing English department."),
    Film("Gutterbee", R.drawable.poster13, "Gutterbee is a character driven comedy about sausages and friendship. Set in small-town America, it's a story about two hopeless dreamers who join forces in a quest to erect the ultimate German sausage restaurant. Gutterbee is also a social satire about the nexus of identity fear, where religion becomes an intellectual cul-de-sac, and racism, homophobia and intolerance reign supreme."),
    Film("The Hype", R.drawable.poster14, "Set in the colliding worlds of streetwear, business and culture where fashion visionaries must elevate their designs and entrepreneurial sense to avoid elimination while remaining authentic to their style."),
    Film("Language Lessons", R.drawable.poster15, "A Spanish teacher and her student develop an unexpected friendship."),
    Film("Marvel Studios: Assembled", R.drawable.poster16, "Assembled is a comprehensive documentary series that chronicles the creation of Marvel Studios' thrilling new shows and theatrical releases. Assembled is an immersive, and in-depth examination of the next phase of the MCU."),
    Film("Masters of the Universe: Revelation", R.drawable.poster17, "The war for Eternia begins again in what may be the final battle between He-Man and Skeletor. A new animated series from writer-director Kevin Smith"),
    Film("Nine Perfect Strangers", R.drawable.poster18, "Adaptation of Liane Moriarty's novel."),
    Film("100 Foot Wave", R.drawable.poster19, "Follows the decade-long odyssey of big-wave pioneer Garrett McNamara who, after visiting a small fishing village in Portugal, helped push the sport beyond the realm of imagination."),

    )



lateinit var filmsAdapter: FilmListRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //находим наш RV
        main_recycler.apply {





            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
                override fun click(film: Film) {
                    (requireActivity() as MainActivity).launchDetailsFragment(film)
                }
            })


            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)

        }

//Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)



        search_view.setOnClickListener {
            search_view.isIconified = false
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                //Фильтруем список на поискк подходящих сочетаний
                val result = filmsDataBase.filter {
                    //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                    it.title.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                filmsAdapter.addItems(result)
                return true
            }
        })








    }
}