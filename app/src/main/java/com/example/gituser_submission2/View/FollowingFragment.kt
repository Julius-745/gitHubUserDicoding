package com.example.gituser_submission2.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gituser_submission2.Model.Gituser
import com.example.gituser_submission2.ViewModul.MainViewModel
import com.example.gituser_submission2.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {

    private lateinit var adapter: GitUserAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentFollowingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GitUserAdapter()
        adapter.notifyDataSetChanged()
        binding.viewFollowing.setHasFixedSize(true)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding.viewFollowing.layoutManager = LinearLayoutManager(requireContext())
        binding.viewFollowing.adapter = adapter

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        mainViewModel.setFollowing(Username)
        mainViewModel.getFollowing().observe(requireActivity(), { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
                showLoading(false)
            }
        })
        adapter.setOnItemClickCallback(object : GitUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Gituser) {
                showDetail(data)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showDetail(data: Gituser) {
        Username = data.username
        FollowerFragment.Username = data.username
        val intentDetail = Intent(requireContext(), DetailgitUser::class.java)
        intentDetail.putExtra(DetailgitUser.EXTRA_USER, data.username)
        startActivity(intentDetail)
    }

    companion object{
        var Username = "username"
    }
}
