package silk.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * A RadioButton that automatically sets its typeface to Roboto Light (a thinner version of Roboto). The font is loaded
 * from the library's assets so it will work on any version of Android.
 *
 * @author Aidan Follestad
 */
public class SilkRadioButton extends RadioButton {

    public SilkRadioButton(Context context) {
        super(context);
        init(context);
    }

    public SilkRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SilkRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        if (isInEditMode()) return;
        try {
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
            setTypeface(tf);
        } catch (RuntimeException e) {
            throw new RuntimeException("Make sure you copied the 'assets' folder from Silk to your own project; " + e.getMessage());
        }
    }
}
