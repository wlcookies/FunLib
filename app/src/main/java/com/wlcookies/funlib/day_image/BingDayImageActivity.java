package com.wlcookies.funlib.day_image;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.http.EasyHttp;
import com.hjq.http.config.RequestApi;
import com.hjq.http.listener.HttpCallback;
import com.wlcookies.funlib.BaseActivity;
import com.wlcookies.funlib.R;
import com.wlcookies.funlib.databinding.ActivityBingDayImageBinding;
import com.wlcookies.funlib.entity.DayImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 必应每日一图API
 */
public class BingDayImageActivity extends BaseActivity {
    private static final String TAG = "BingDayImageActivity";
    private ActivityBingDayImageBinding mActivityBingDayImageBinding;
    private List<DayImage> mDayImages;
    private BaseQuickAdapter<DayImage, BaseViewHolder> mBaseQuickAdapter;

    public static Intent newInstance(Context context) {
        return new Intent(context, BingDayImageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityBingDayImageBinding = ActivityBingDayImageBinding.inflate(getLayoutInflater());
        setContentView(mActivityBingDayImageBinding.getRoot());

        mDayImages = new ArrayList<>();

        mBaseQuickAdapter = new BaseQuickAdapter<DayImage, BaseViewHolder>(R.layout.activity_bing_day_image_item, mDayImages) {
            @Override
            protected void convert(@NonNull BaseViewHolder holder, DayImage dayImage) {
                holder.setText(R.id.title_tv, dayImage.getTitle());
                ImageView imageIv = holder.getView(R.id.image_iv);
                Glide.with(BingDayImageActivity.this)
                        .load(dayImage.getUrl())
                        .into(imageIv);
            }
        };

        mActivityBingDayImageBinding.dataRcl.setLayoutManager(new GridLayoutManager(this, 2));
        mActivityBingDayImageBinding.dataRcl.setAdapter(mBaseQuickAdapter);
        mBaseQuickAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                openWebPage(mDayImages.get(position).getCopyrightlink());
            }
        });

        EasyHttp.get(this)
                .api(new RequestApi("/HPImageArchive.aspx?format=js&idx=0&n=8&mkt=zh-CN"))
                .request(new HttpCallback<String>(this) {
                    @Override
                    public void onSucceed(String result) {
                        super.onSucceed(result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray images = jsonObject.getJSONArray("images");
                            for (int i = 0; i < images.length(); i++) {
                                JSONObject imagesJSONObject = images.getJSONObject(i);
                                String url = (String) imagesJSONObject.get("url");
                                String copyright = (String) imagesJSONObject.get("copyright");
                                String copyrightlink = (String) imagesJSONObject.get("copyrightlink");
                                String title = (String) imagesJSONObject.get("title");
                                Log.d(TAG, "https://cn.bing.com" + url);
                                mDayImages.add(new DayImage(
                                        "https://cn.bing.com" + url,
                                        copyrightlink,
                                        title,
                                        copyright
                                ));
                            }
                            mBaseQuickAdapter.notifyItemRangeChanged(0, mDayImages.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}