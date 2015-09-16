package jp.test.adinte.imagepixel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    Bitmap bitmap;
    int pixels[];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new BitmapTest2View(this));
    }

    class BitmapTest2View extends View {
        public BitmapTest2View(Context context) {
            super(context);

            // リソースから Bitmap を取得
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);

            // もし編集不可なら、編集可能な Bitmap を複製
            if (!bitmap.isMutable()) {
                bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            }

            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            pixels = new int[width * height];

            // Bitmap から Pixel を取得
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

            for (int i=0; i<pixels.length; i++) {
                int red = (pixels[i] & 0x00FF0000) >> 16;
                int green = (pixels[i] & 0x0000FF00) >> 8;
                int blue = (pixels[i] & 0x000000FF);
                if(red >=150 && green >= 200 && blue >= 0){
                    pixels[i]= Color.BLUE;
                }
                if(red >=255 && green >= 255 && blue >= 255){
                    pixels[i]= Color.WHITE;
                }
            }
            // Bitmap に Pixel を設定
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        }

        @Override
        public void onDraw(Canvas canvas) {
            // Bitmap の描画
            canvas.drawBitmap(bitmap, 0, 0, new Paint());
        }
    }
}
