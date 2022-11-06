package com.example.homework_2

import com.example.homework_2.R
import com.example.homework_2.Affirmation

class Datasource() {

    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.test),
            Affirmation(R.string.affirmation2, R.drawable.test),
            Affirmation(R.string.affirmation3, R.drawable.test),
            Affirmation(R.string.affirmation4, R.drawable.test),
            Affirmation(R.string.affirmation5, R.drawable.test),
            Affirmation(R.string.affirmation6, R.drawable.test),
            Affirmation(R.string.affirmation7, R.drawable.test),
            Affirmation(R.string.affirmation8, R.drawable.test),
            Affirmation(R.string.affirmation9, R.drawable.test),
            Affirmation(R.string.affirmation10, R.drawable.test)
        )
    }
}