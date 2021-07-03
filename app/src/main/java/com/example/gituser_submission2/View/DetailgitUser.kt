package com.example.gituser_submission2.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.gituser_submission2.R
import com.example.gituser_submission2.ViewModul.DetailViewModel
import com.example.gituser_submission2.databinding.ActivityDetailgitUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailgitUser : AppCompatActivity() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailgitUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailgitUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

       detailViewModel = ViewModelProvider(
           this,
           ViewModelProvider.NewInstanceFactory()
       ).get(DetailViewModel::class.java)

        val username = intent.getStringExtra(EXTRA_USER)
        username?.let { setData(it) }

        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabslayout)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TABLAYOUT_TITLE[position])

        }.attach()
    }

    private fun setData(username: String) {
        detailViewModel.setDetailgitUser(username)
        detailViewModel.getDetailgitUser().observe(this, { userData ->
            if (userData != null) {
                with(binding) {
                    Glide.with(this@DetailgitUser)
                        .load(userData.avatar)
                        .into(imgAvatar)
                    supportActionBar?.title = username
                    txtUsername.text = userData.username
                    tvName.text = userData.name
                    txtCompany.text = userData.company
                    txtRepository.text = userData.repository
                    txtLocation.text = userData.location
                    txtFollowers.text = userData.followers
                    txtFollowing.text = userData.following
                    showLoading(false)
                }
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()

    }

    companion object {
        const val EXTRA_USER = "extra_user"
        private val TABLAYOUT_TITLE = intArrayOf(
            R.string.following,
            R.string.followers
        )
    }
}