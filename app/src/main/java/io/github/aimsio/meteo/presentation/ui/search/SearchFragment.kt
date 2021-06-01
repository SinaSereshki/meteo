package io.github.aimsio.meteo.presentation.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import io.github.aimsio.meteo.R
import io.github.aimsio.meteo.databinding.SearchFragmentBinding
import io.github.aimsio.meteo.presentation.ui.MainNavigator
import me.meikiem.pixabay.presentation.extension.observe
import me.meikiem.pixabay.presentation.extension.viewModelProvider
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var navigator: MainNavigator

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var currentAdapter: CurrentAdapter

    private lateinit var viewModel: SearchViewModel

    private lateinit var binding: SearchFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(factory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observe(viewModel.getNews){

        }
    }

    private fun initUI() {
        with(binding.rvNews) {
            layoutManager =
                GridLayoutManager(requireContext(), resources.getInteger(R.integer.span_count))
//            adapter = currentAdapter
            setHasFixedSize(true)
        }
    }
}