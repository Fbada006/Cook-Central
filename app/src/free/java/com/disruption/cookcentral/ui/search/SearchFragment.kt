package com.disruption.cookcentral.ui.search

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.disruption.cookcentral.R
import com.disruption.cookcentral.databinding.FragmentSearchBinding
import com.disruption.cookcentral.utils.Resource
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var mBinding: FragmentSearchBinding
    private val mSearchViewModel: SearchViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        mBinding.searchView.setOnQueryTextListener(this)

        val adapter = SearchedRecipesAdapter()
        initRv(adapter)

        mSearchViewModel.searchedRecipes.observe(viewLifecycleOwner, Observer { recipeResponseResource ->
            when (recipeResponseResource.status) {
                Resource.Status.SUCCESS -> if (recipeResponseResource.data != null
                        && recipeResponseResource.data.results.isNotEmpty()) {
                    adapter.submitList(recipeResponseResource.data.results)
                    mBinding.progressBar.visibility = View.GONE
                    mBinding.infoText.visibility = View.GONE
                } else {
                    mBinding.progressBar.visibility = View.GONE
                    mBinding.infoText.visibility = View.VISIBLE
                    mBinding.infoText.text = getString(R.string.no_data_to_display)
                }
                Resource.Status.ERROR -> {
                    mBinding.progressBar.visibility = View.GONE
                    mBinding.infoText.visibility = View.VISIBLE
                    mBinding.infoText.text = getString(R.string.error_has_occurred, recipeResponseResource.message)
                }
                Resource.Status.LOADING -> {
                    mBinding.progressBar.visibility = View.VISIBLE
                    mBinding.infoText.visibility = View.GONE
                }
            }
        })
        return mBinding.root
    }

    private fun initRv(adapter: SearchedRecipesAdapter) {
        val recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        if (!TextUtils.isEmpty(query)) {
            mSearchViewModel.searchRecipe(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        requireActivity().findViewById<View>(R.id.adView).visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        requireActivity().findViewById<View>(R.id.adView).visibility = View.VISIBLE
    }
}