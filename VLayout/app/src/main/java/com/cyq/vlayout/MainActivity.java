package com.cyq.vlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //应用
    String[] ITEM_NAMES = {"天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "拍卖", "分类"};
    int[] IMG_URLS = {R.mipmap.ic_tian_mao, R.mipmap.ic_ju_hua_suan, R.mipmap.ic_tian_mao_guoji,
            R.mipmap.ic_waimai, R.mipmap.ic_chaoshi, R.mipmap.ic_voucher_center,
            R.mipmap.ic_travel, R.mipmap.ic_tao_gold, R.mipmap.ic_auction, R.mipmap.ic_classify};

    //    高颜值商品位
    int[] ITEM_URL = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4,
            R.mipmap.item5};
    int[] GRID_URL = {R.mipmap.flashsale1, R.mipmap.flashsale2, R.mipmap.flashsale3,
            R.mipmap.flashsale4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        mRecyclerView.setAdapter(new BannerAdapter(this));
    }


    class BannerAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {
        private Context mContext;

        public BannerAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new LinearLayoutHelper();
        }

        @NonNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new BaseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.vlayout_banner, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
                        ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("https://gw.alicdn.com/imgextra/i1/7/O1CN01nfNUEq1BvFJ6YLtPD_!!7-0-lubanu.jpg");
            arrayList.add("https://gw.alicdn.com/imgextra/i3/20/O1CN01UTTfyr1C1CSm4hzOv_!!20-0-lubanu.jpg");
            arrayList.add("https://gw.alicdn.com/imgextra/i3/106/O1CN01etvHvW1CeaWUFfTu9_!!106-0-lubanu.jpg");
            arrayList.add("https://gw.alicdn.com/imgextra/i3/20/O1CN01UTTfyr1C1CSm4hzOv_!!20-0-lubanu.jpg");
            arrayList.add("https://gw.alicdn.com/imgextra/i2/14/O1CN019L7VgZ1ByS5PJJpnO_!!14-0-lubanu.jpg");
            arrayList.add("https://img.alicdn.com/tps/i4/TB1YMbpeoKF3KVjSZFESutExFXa.jpg");
            // 绑定数据
            Banner mBanner = baseViewHolder.getView(R.id.banner);
            //设置banner样式
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置图片集合
            mBanner.setImages(arrayList);
            //设置banner动画效果
            mBanner.setBannerAnimation(Transformer.Default);
            //设置标题集合（当banner样式有显示title时）
            //        mBanner.setBannerTitles(titles);
            //设置自动轮播，默认为true
            mBanner.isAutoPlay(true);
            mBanner.setImageLoader(new GlideImageLoader());
            //设置轮播时间
            mBanner.setDelayTime(3000);
            //设置指示器位置（当banner模式中有指示器时）
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();

            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(getApplicationContext(), "banner点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}
