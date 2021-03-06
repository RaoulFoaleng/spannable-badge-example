package examples.com.spannablebadges;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.LineHeightSpan;
import android.text.style.ReplacementSpan;

public class TagBadgeSpannable extends ReplacementSpan implements LineHeightSpan {

    private static int CORNER_RADIUS = 30;
    private final int textColor;
    private final int backgroundColor;
    private final int lineHeight;

    public TagBadgeSpannable(int lineHeight, int textColor, int backgroundColor) {
        super();
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.lineHeight = lineHeight;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        final float textSize = paint.getTextSize();
        final float textLength = x + measureText(paint, text, start, end);
        final float badgeHeight = textSize * 2.25f;
        final float textOffsetVertical = textSize * 1.45f;

        RectF badge = new RectF(x, y, textLength, y + badgeHeight);
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(badge, CORNER_RADIUS, CORNER_RADIUS, paint);

        paint.setColor(textColor);
        canvas.drawText(text, start, end, x, y + textOffsetVertical, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end));
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end);
    }

    @Override
    public void chooseHeight(CharSequence charSequence, int i, int i1, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        fontMetricsInt.bottom += lineHeight;
        fontMetricsInt.descent += lineHeight;
    }
}
