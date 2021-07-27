package saymobile.company.monkeygame.viewmodel

import androidx.lifecycle.ViewModel
import saymobile.company.monkeygame.R

class PlayFragmentViewModel: ViewModel(){


    fun chooseSquares(numsToGen: Int): ArrayList<Int>{
        //Make list with numbers 1-24
        val organisedList = ArrayList<Int>()
        for(i in 0..23){
            organisedList.add(i)
        }
        //Shuffle the numbers and grab the first X elements of the shuffled list
        val shuffled = organisedList.shuffled()
        val numberList = ArrayList<Int>()
        for (number in shuffled){
            if(numberList.size < numsToGen){
                numberList.add(number)
            }
        }
        return numberList
    }

//    fun getNumbers(levelNum: Int): ArrayList<Int>{
//        val numbers = ArrayList<Int>()
//        for (i in 0 until levelNum){
//            numbers.add(i)
//        }
//        return numbers
//    }

//    fun getViewId(number: Int): Int{
//        return when(number){
//            1 -> R.id.one
//            2 -> R.id.two
//            3 -> R.id.three
//            4 -> R.id.four
//            5 -> R.id.five
//            6 -> R.id.six
//            7 -> R.id.seven
//            8 -> R.id.eight
//            9 -> R.id.nine
//            10 -> R.id.ten
//            11 -> R.id.eleven
//            12 -> R.id.twelve
//            13 -> R.id.thirteen
//            14 -> R.id.fourteen
//            15 -> R.id.fifteen
//            16 -> R.id.sixteen
//            17 -> R.id.seventeen
//            18 -> R.id.eighteen
//            19 -> R.id.nineteen
//            20 -> R.id.twenty
//            21 -> R.id.twentyone
//            22 -> R.id.twentytwo
//            23 -> R.id.twentythree
//            else -> R.id.twentyfour
//        }
//    }


}