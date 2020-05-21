package com.zyu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.SystemClock;
import android.util.AttributeSet;

import com.aigestudio.wheelpicker.WheelPicker;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Date;
import java.util.List;

public class ReactWheelCurvedPicker extends WheelPicker {

    private final EventDispatcher mEventDispatcher;
    private List<Object> mValueData;
    public int pickerState;
    public ReactWheelCurvedPicker(ReactContext reactContext) {
        super(reactContext);
        mEventDispatcher = reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener() {
            @Override
            public void onWheelScrolled(int offset) {
            }

            @Override
            public void onWheelSelected(int index) {
                if (mValueData != null && index < mValueData.size()) {
                    mEventDispatcher.dispatchEvent(
                            new ItemSelectedEvent(getId(), mValueData.get(index)));
                }
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
                pickerState = state;
            }
        });
    }

//     @Override
//     protected void onDraw(Canvas canvas) {
//         super.onDraw(canvas);
//
//         Paint paint = new Paint();
//         paint.setColor(Color.WHITE);
//         int colorFrom = 0x00FFFFFF;//Color.BLACK;
//         int colorTo = Color.WHITE;
//         LinearGradient linearGradientShader = new LinearGradient(rectCurItem.left, rectCurItem.top, rectCurItem.right/2, rectCurItem.top, colorFrom, colorTo, Shader.TileMode.MIRROR);
//         paint.setShader(linearGradientShader);
//         canvas.drawLine(rectCurItem.left, rectCurItem.top, rectCurItem.right, rectCurItem.top, paint);
//         canvas.drawLine(rectCurItem.left, rectCurItem.bottom, rectCurItem.right, rectCurItem.bottom, paint);
//     }

//     @Override
//     public void setSelectedItemPosition(int index) {
//         super.setSelectedItemPosition(index);
//         unitDeltaTotal = 0;
// 		mHandler.post(this);
//     }

    public void setValueData(List<Object> data) {
        mValueData = data;
    }

    public int getState() {
        return pickerState;
    }
}

class ItemSelectedEvent extends Event<ItemSelectedEvent> {

    public static final String EVENT_NAME = "wheelCurvedPickerPageSelected";

    private final Object mValue;

    protected ItemSelectedEvent(int viewTag, Object value) {
        super(viewTag);
        mValue = value;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();

        Class mValueClass = mValue.getClass();
        if (mValueClass == Integer.class) {
            eventData.putInt("data", (Integer) mValue);
        } else if (mValueClass == String.class) {
            eventData.putString("data", mValue.toString());
        }

        return eventData;
    }
}
