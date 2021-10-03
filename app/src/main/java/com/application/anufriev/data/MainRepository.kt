package com.application.anufriev.data

import com.application.anufriev.R
import com.application.anufriev.domain.Film

class MainRepository {
    val filmsDataBase = listOf(
        Film("The Shawshank Redemption",
            R.drawable.poster10, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", 7.7f),
        Film("The Godfather",
            R.drawable.poster11, "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", 6.5f),
        Film("The Dark Knight",
            R.drawable.poster12, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", 9.1f),
        Film("Pulp Fiction",
            R.drawable.poster13, "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.", 3.7f),
        Film("Inception",
            R.drawable.poster14, "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", 2.4f),
        Film("Hamilton",
            R.drawable.poster15, "The real life of one of America's foremost founding fathers and first Secretary of the Treasury, Alexander Hamilton. Captured live on Broadway from the Richard Rodgers Theater with the original Broadway cast.", 5.8f),
        Film("Gisaengchung",
            R.drawable.poster16, "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.", 8.3f),
        Film("Interstellar", R.drawable.poster17, "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival."),
        Film("Joker",
            R.drawable.poster18, "In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.", 4.7f),
        Film("1917",
            R.drawable.poster19, "April 6th, 1917. As a regiment assembles to wage war deep in enemy territory, two soldiers are assigned to race against time and deliver a message that will stop 1,600 men from walking straight into a deadly trap.", 1.7f)
    )

}