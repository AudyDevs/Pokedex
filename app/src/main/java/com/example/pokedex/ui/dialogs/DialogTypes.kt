package com.example.pokedex.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.pokedex.databinding.DialogTypesBinding

class DialogTypes(
    context: Context, private var onClickButtonType: (String) -> Unit
) : Dialog(context) {

    private val binding = DialogTypesBinding.inflate(layoutInflater)

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initListeners()
        show()
    }

    private fun initListeners() {
        binding.apply {
            ivAllTypes.setOnClickListener {
                onClickButtonType("")
                dismiss()
            }
            ivIce.setOnClickListener {
                onClickButtonType("ice")
                dismiss()
            }
            ivFlying.setOnClickListener {
                onClickButtonType("flying")
                dismiss()
            }
            ivNormal.setOnClickListener {
                onClickButtonType("normal")
                dismiss()
            }
            ivRock.setOnClickListener {
                onClickButtonType("rock")
                dismiss()
            }
            ivFighting.setOnClickListener {
                onClickButtonType("fighting")
                dismiss()
            }
            ivGhost.setOnClickListener {
                onClickButtonType("ghost")
                dismiss()
            }
            ivFairy.setOnClickListener {
                onClickButtonType("fairy")
                dismiss()
            }
            ivBug.setOnClickListener {
                onClickButtonType("bug")
                dismiss()
            }
            ivGround.setOnClickListener {
                onClickButtonType("ground")
                dismiss()
            }
            ivElectric.setOnClickListener {
                onClickButtonType("electric")
                dismiss()
            }
            ivWater.setOnClickListener {
                onClickButtonType("water")
                dismiss()
            }
            ivPsychic.setOnClickListener {
                onClickButtonType("psychic")
                dismiss()
            }
            ivPoison.setOnClickListener {
                onClickButtonType("poison")
                dismiss()
            }
            ivDragon.setOnClickListener {
                onClickButtonType("dragon")
                dismiss()
            }
            ivSteel.setOnClickListener {
                onClickButtonType("steel")
                dismiss()
            }
            ivGrass.setOnClickListener {
                onClickButtonType("grass")
                dismiss()
            }
            ivDark.setOnClickListener {
                onClickButtonType("dark")
                dismiss()
            }
            ivFire.setOnClickListener {
                onClickButtonType("fire")
                dismiss()
            }
        }
    }
}