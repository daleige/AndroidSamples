package com.chenyangqi.surfaceview.frame_anim

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import com.chenyangqi.surfaceview.R

/**
 * @author ChenYangQi
 * @describe
 * @time 2022/03/31 17:28
 */
class Util {

    companion object {
        fun getResources(context: Context): AnimationDrawable {
            val duration = 60
            val anim = AnimationDrawable()
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_000), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_001), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_002), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_003), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_004), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_005), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_006), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_007), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_008), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_009), duration)

            anim.addFrame(context.resources.getDrawable(R.mipmap.img_010), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_011), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_012), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_013), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_014), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_015), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_016), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_017), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_018), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_019), duration)

            anim.addFrame(context.resources.getDrawable(R.mipmap.img_020), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_021), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_022), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_023), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_024), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_025), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_026), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_027), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_028), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_029), duration)

            anim.addFrame(context.resources.getDrawable(R.mipmap.img_030), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_031), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_032), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_033), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_034), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_035), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_036), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_037), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_038), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_039), duration)

            anim.addFrame(context.resources.getDrawable(R.mipmap.img_040), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_041), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_042), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_043), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_044), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_045), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_046), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_047), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_048), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_049), duration)

            anim.addFrame(context.resources.getDrawable(R.mipmap.img_050), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_051), duration)
            anim.addFrame(context.resources.getDrawable(R.mipmap.img_052), duration)

            anim.isOneShot = false
            return anim
        }

        fun getSourceId(): IntArray {
            return intArrayOf(
                R.mipmap.img_000,
                R.mipmap.img_001,
                R.mipmap.img_002,
                R.mipmap.img_003,
                R.mipmap.img_004,
                R.mipmap.img_005,
                R.mipmap.img_006,
                R.mipmap.img_007,
                R.mipmap.img_008,
                R.mipmap.img_009,

                R.mipmap.img_010,
                R.mipmap.img_011,
                R.mipmap.img_012,
                R.mipmap.img_013,
                R.mipmap.img_014,
                R.mipmap.img_015,
                R.mipmap.img_016,
                R.mipmap.img_017,
                R.mipmap.img_018,
                R.mipmap.img_019,

                R.mipmap.img_020,
                R.mipmap.img_021,
                R.mipmap.img_022,
                R.mipmap.img_023,
                R.mipmap.img_024,
                R.mipmap.img_025,
                R.mipmap.img_026,
                R.mipmap.img_027,
                R.mipmap.img_028,
                R.mipmap.img_029,

                R.mipmap.img_030,
                R.mipmap.img_031,
                R.mipmap.img_032,
                R.mipmap.img_033,
                R.mipmap.img_034,
                R.mipmap.img_035,
                R.mipmap.img_036,
                R.mipmap.img_037,
                R.mipmap.img_038,
                R.mipmap.img_039,

                R.mipmap.img_040,
                R.mipmap.img_041,
                R.mipmap.img_042,
                R.mipmap.img_043,
                R.mipmap.img_044,
                R.mipmap.img_045,
                R.mipmap.img_046,
                R.mipmap.img_047,
                R.mipmap.img_048,
                R.mipmap.img_049,

                R.mipmap.img_050,
                R.mipmap.img_051,
                R.mipmap.img_052,
            )
        }
    }
}