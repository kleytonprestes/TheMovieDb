package base;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import base.api.ApiUtils;

/**
 * Created by kleyton on 17/01/18.
 */

public class Utils {

    public static void loadImage(final Context context, final String path, final ImageView imageView) {
        Picasso.with(context)
                .load(ApiUtils.URL_BASE_IMAGES + path)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context).load(ApiUtils.URL_BASE_IMAGES + path).networkPolicy(NetworkPolicy.NO_CACHE)
                                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                                .into(imageView);
                    }
                });
    }
}
