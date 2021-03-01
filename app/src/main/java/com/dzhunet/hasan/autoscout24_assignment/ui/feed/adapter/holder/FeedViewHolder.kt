package com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dzhunet.hasan.autoscout24_assignment.R
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel
import com.dzhunet.hasan.autoscout24_assignment.databinding.ItemFeedBinding
import com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter.FeedItemPhotoSliderAdapter
import com.dzhunet.hasan.autoscout24_assignment.utils.*
import java.text.NumberFormat
import java.util.*

class FeedViewHolder(
    private val context: Context,
    private val onItemClick: (FeedResultModel) -> Unit,
    private val binding: ItemFeedBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    private val feedPhotoSliderAdapter = FeedItemPhotoSliderAdapter()

    init {
        binding.viewPagerSliderImages.adapter = feedPhotoSliderAdapter
    }

    fun onBind(item: FeedResultModel) {
        setupSlider(item)

        binding.textViewCarTitle.text = context.getString(
                R.string.car_title_placeholder,
                item.make,
                item.model,
                item.modelline.orEmpty()
            )
        binding.textViewCarPrice.text = item.price.asCurrency
        binding.textViewCarDescription.text = item.description

        item.firstRegistration?.let { firstRegistration ->
            binding.textViewFirstRegistration.text = firstRegistration
        }.orElse {
            binding.linearLayoutFirstRegistrationContainer.gone()
        }

        item.fuel?.let { fuelType ->
            binding.textViewCarFuel.text = fuelType
        }.orElse {
            binding.linearLayoutFuelContainer.gone()
        }

        item.mileage?.let { mileage ->
            binding.textViewCarMileage.text = context.getString(
                R.string.mileage_placeholder,
                NumberFormat.getInstance(Constants.DEFAULT_LOCALE).format(mileage)
            )
        }.orElse {
            binding.linearLayoutMileageContainer.gone()
        }

        item.seller?.let {
            binding.textViewSellerDetails.text =
                context.getString(R.string.seller_details_placeholder, it.type, it.phone, it.city)
        }.orElse {
            binding.linearLayoutSellerContainer.gone()
        }

        itemView.setOnClickListener {
            onItemClick.invoke(item)
        }
    }

    private fun setupSlider(item: FeedResultModel) {
        item.images?.let { images ->
            feedPhotoSliderAdapter.submitList(images)
            setSliderPage(1, images)
            binding.viewPagerSliderImages.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    setSliderPage(position + 1, images)
                }
            })
        }.orElse {
            binding.constraintLayoutSliderContainer.gone()
            binding.imageViewNoPhoto.visible()
        }
    }

    private fun setSliderPage(
        position: Int,
        images: MutableList<FeedResultModel.Image>
    ) {
        binding.imageViewImagesCounter.text = context.getString(
            R.string.slider_photo_count_placeholder,
            position.toString(),
            (images.size).toString()
        )
    }
}