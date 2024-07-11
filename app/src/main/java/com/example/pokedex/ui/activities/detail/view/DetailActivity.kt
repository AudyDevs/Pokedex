package com.example.pokedex.ui.activities.detail.view

import android.graphics.drawable.GradientDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.core.extensions.backgroundColorType
import com.example.pokedex.core.extensions.formatIdPokemon
import com.example.pokedex.core.extensions.formatNamePokemon
import com.example.pokedex.core.extensions.iconPokemonType
import com.example.pokedex.core.extensions.textPokemonType
import com.example.pokedex.databinding.ActivityDetailBinding
import com.example.pokedex.di.Constants.LIMIT_POKEMON_LIST
import com.example.pokedex.domain.model.PokemonInfoModel
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.model.TypeModel
import com.example.pokedex.domain.state.PokemonInfoState
import com.example.pokedex.ui.activities.detail.viewmodel.DetailViewModel
import com.example.pokedex.ui.dialogs.Dialog3D
import com.example.pokedex.ui.dialogs.DialogError
import com.example.pokedex.ui.fragments.manager.FragmentsManager
import com.example.pokedex.ui.fragments.viewpager.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentsManager: FragmentsManager

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    private lateinit var image3D: String
    private lateinit var pokemonCries: String
    private lateinit var mediaPlayer: MediaPlayer
    private var preparedMediaPlayer = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()
    }

    private fun initUI() {
        initIntents()
        initListeners()
        initViewPager()
        initStateUI()
    }

    override fun onResume() {
        super.onResume()
        initViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (preparedMediaPlayer) {
            mediaPlayer.release()
        }
    }

    private fun initViewModel() {
        detailViewModel.getPokemonInfo()
    }

    private fun initIntents() {
        detailViewModel.pokemonID = intent?.extras?.getInt("idPokemon") ?: 0
        detailViewModel.namePokemon = intent?.extras?.getString("namePokemon") ?: ""
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.ivCries.setOnClickListener {
            startMediaPlayer()
        }
        binding.iv3d.setOnClickListener {
            if (image3D.isNotEmpty()) {
                Dialog3D(this, image3D)
            }
        }
        binding.ivPreviousPokemon.setOnClickListener {
            detailViewModel.pokemonID -= 1
            detailViewModel.getPokemonInfoByID()
        }
        binding.ivNextPokemon.setOnClickListener {
            detailViewModel.pokemonID += 1
            detailViewModel.getPokemonInfoByID()
        }
    }

    private fun startMediaPlayer() {
        if (pokemonCries.isNotEmpty()) {
            mediaPlayer = MediaPlayer().apply {
                setDataSource(applicationContext, Uri.parse(pokemonCries))
                prepare()
                start()
            }
            preparedMediaPlayer = true
        }
    }

    private fun initViewPager() {
        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Info"
                1 -> tab.text = "Stats"
                2 -> tab.text = "Defensas"
                3 -> tab.text = "Evolución"
            }
        }.attach()
    }

    private fun initStateUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.pokemonInfoState.collect { pokemonInfoState ->
                    when (pokemonInfoState) {
                        PokemonInfoState.Loading -> onLoading()
                        is PokemonInfoState.Success -> onSuccess(pokemonInfoState.pokemonInfo)
                        PokemonInfoState.Error -> onError()
                    }
                }
            }
        }
    }

    private fun onLoading() {
        showUI(false)
    }

    private fun showUI(show: Boolean) {
        if (show) {
            stopAnimation()
            binding.layoutName.isVisible = true
            binding.layoutType.isVisible = true
            binding.tabLayout.isVisible = true
            binding.ivIcon.isVisible = true
            binding.viewPager.isVisible = true
        } else {
            startAnimation()
            binding.layoutName.isVisible = false
            binding.layoutType.isVisible = false
            binding.tabLayout.isVisible = false
            binding.ivIcon.isVisible = false
            binding.viewPager.isVisible = false
        }
    }

    private fun onSuccess(pokemonInfo: PokemonModel?) {
        showUI(true)
        fragmentsManager.setPokemonInfo(pokemonInfo)
        changeUI(pokemonInfo)
    }

    private fun onError() {
        showUI(true)
        DialogError(this, onClickButtonError = {})
    }

    private fun startAnimation() {
        binding.ivLottieAnimation.isVisible = true
        binding.ivLottieAnimation.setAnimation(R.raw.loading_pokeball)
        binding.ivLottieAnimation.repeatCount = LottieDrawable.INFINITE
        binding.ivLottieAnimation.playAnimation()
    }

    private fun stopAnimation() {
        binding.ivLottieAnimation.isVisible = false
        binding.ivLottieAnimation.repeatCount = 0
        binding.ivLottieAnimation.pauseAnimation()
    }

    private fun changeUI(pokemonInfo: PokemonModel?) {
        changeColorUI(pokemonInfo)
        if (pokemonInfo != null) {
            if (pokemonInfo.pokemonInfoModel != null) {
                updatePokemonInfo(pokemonInfo.pokemonInfoModel)
            }
            if (pokemonInfo.typeModel != null) {
                updatePokemonType(pokemonInfo.typeModel)
            }
        }
    }

    private fun changeColorUI(pokemonInfo: PokemonModel?) {
        val typeModel = pokemonInfo?.typeModel?.filter { it.typeSlot == 1 }
        val backgroundColor = ContextCompat.getColor(this, R.color.background)
        val backgroundColorType =
            ContextCompat.getColor(this, typeModel?.first()?.name?.backgroundColorType()!!)
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(backgroundColorType, backgroundColor)
        )
        binding.main.background = gradientDrawable

        binding.tabLayout.setTabTextColors(
            ContextCompat.getColor(this, R.color.text),
            backgroundColorType
        )
        binding.tabLayout.setSelectedTabIndicatorColor(backgroundColorType)
    }

    private fun updatePokemonInfo(pokemonInfoModel: PokemonInfoModel) {
        binding.apply {
            tvIdText.text = pokemonInfoModel.id.formatIdPokemon()
            tvName.text = pokemonInfoModel.name.formatNamePokemon()
            Glide.with(this@DetailActivity).load(pokemonInfoModel.imageBig).into(ivIcon)
            image3D = pokemonInfoModel.image3D
            pokemonCries = if (pokemonInfoModel.latestCries.isNotEmpty()) {
                pokemonInfoModel.latestCries
            } else if (pokemonInfoModel.legacyCries.isNotEmpty()) {
                pokemonInfoModel.legacyCries
            } else {
                ""
            }
            when (detailViewModel.pokemonID) {
                1 -> ivPreviousPokemon.isVisible = false
                LIMIT_POKEMON_LIST -> ivNextPokemon.isVisible = false
                else -> {
                    ivPreviousPokemon.isVisible = true
                    ivNextPokemon.isVisible = true
                }
            }
        }
    }

    private fun updatePokemonType(typeModel: List<TypeModel>) {
        binding.apply {
            if (typeModel.size > 1) {
                ivType1.isVisible = true
                val type1 = typeModel[1].name
                ivType1.setCardBackgroundColor(
                    ContextCompat.getColor(
                        this@DetailActivity, type1.backgroundColorType()
                    )
                )

                binding.ivIconType1.setImageResource(type1.iconPokemonType())
                binding.ivTextType1.text = type1.textPokemonType()
            } else {
                ivType1.isVisible = false
            }
            val type2 = typeModel[0].name
            ivType2.setCardBackgroundColor(
                ContextCompat.getColor(
                    this@DetailActivity, type2.backgroundColorType()
                )
            )
            binding.ivIconType2.setImageResource(type2.iconPokemonType())
            binding.ivTextType2.text = type2.textPokemonType()
        }
    }
}