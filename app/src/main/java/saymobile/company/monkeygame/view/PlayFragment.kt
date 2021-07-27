package saymobile.company.monkeygame.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.w3c.dom.Text
import saymobile.company.monkeygame.R
import saymobile.company.monkeygame.databinding.FragmentPlayBinding
import saymobile.company.monkeygame.databinding.LostGameDialogBinding
import saymobile.company.monkeygame.viewmodel.PlayFragmentViewModel


class PlayFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentPlayBinding? = null
//    private var _dialogBinding: LostGameDialogBinding? = null
    private val binding get() = _binding!!
//    private val dialogBinding get() = _dialogBinding!!
    private lateinit var viewModel: PlayFragmentViewModel
    private var currentNumber: Int = 0
    private var levelSize: Int = 6

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayBinding.inflate(inflater, container, false)
//        _dialogBinding = LostGameDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("DESTROYED")
        _binding = null
//        _dialogBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PlayFragmentViewModel::class.java)
        val tempList = viewModel.chooseSquares(6)
        preStart(tempList)


    }

    override fun onClick(v: View?) {
        //checks to see if number is correct
        //Also make changes to textView appearance
        if (v is TextView){
            val blocNumber = v.text.toString().toInt()
            if(blocNumber == (currentNumber + 1)){
                v.background = ResourcesCompat.getDrawable(resources, R.drawable.primary_color_square, null)
                v.setTextColor(ResourcesCompat.getColor(resources, R.color.justTransparent, null))
                currentNumber += 1
                v.isClickable = false
            }else{
                v.background = ResourcesCompat.getDrawable(resources, R.drawable.accent_button, null)

            }
        }
    }



    private fun startGame(numberList: ArrayList<Int>){

        for(i in numberList){
            val bloc = binding.blockGridLayout.getChildAt(i)
            bloc.background = ResourcesCompat.getDrawable(resources, R.drawable.game_squares, null)
            bloc.setOnClickListener(this)
        }

        val timer = object : CountDownTimer(3500, 1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timer.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                if (currentNumber != levelSize){
                    showLostDialog()
                }
            }

        }
        timer.start()


    }

    private fun preStart(numberList: ArrayList<Int>){
        var count = 1
        for(i in numberList){
           val bloc =  binding.blockGridLayout.getChildAt(i)
            if (bloc is TextView){
                bloc.text = count.toString()
                bloc.setTextColor(ResourcesCompat.getColor(resources, R.color.customWhite, null))
                count += 1
            }
        }
        val timer = object: CountDownTimer(3000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                println(millisUntilFinished * 1000)
            }

            override fun onFinish() {
                startGame(numberList)
            }

        }
        timer.start()
    }


    private fun showLostDialog(){
        activity?.let {
            val dialogView: LostGameDialogBinding = LostGameDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

            dialogView.goHomeDialogButton.setOnClickListener {
                dialog.dismiss()
                findNavController().navigateUp()
            }
            dialog.setContentView(dialogView.root)
            dialog.show()


        }
    }

    private fun resetGame(){

    }






}