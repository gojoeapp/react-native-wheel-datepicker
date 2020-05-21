package com.zyu;

import android.graphics.Color;

import com.aigestudio.wheelpicker.WheelPicker;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author <a href="mailto:lesliesam@hotmail.com"> Sam Yu </a>
 */
public class ReactWheelCurvedPickerManager extends SimpleViewManager<ReactWheelCurvedPicker> {

    private static final String REACT_CLASS = "WheelCurvedPicker";

    private static final int DEFAULT_TEXT_SIZE = 25 * 2;
    private static final int DEFAULT_ITEM_SPACE = 14 * 2;

    @Override
    protected ReactWheelCurvedPicker createViewInstance(ThemedReactContext reactContext) {
        ReactWheelCurvedPicker picker = new ReactWheelCurvedPicker(reactContext);
        picker.setItemTextColor(Color.LTGRAY);
        picker.setSelectedItemTextColor(Color.WHITE);
        picker.setItemTextSize(DEFAULT_TEXT_SIZE);
        picker.setItemSpace(DEFAULT_ITEM_SPACE);

        return picker;
    }

    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                ItemSelectedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onValueChange")
        );
    }

    @ReactProp(name="data")
    public void setData(ReactWheelCurvedPicker picker, ReadableArray items) {
        if (picker != null) {
            ArrayList<Object> valueData = new ArrayList<>();
            ArrayList<String> labelData = new ArrayList<>();
            for (int i = 0; i < items.size(); i ++) {
                ReadableMap itemMap = items.getMap(i);

                if (itemMap.getType("value") == ReadableType.String) {
                    valueData.add(itemMap.getString("value"));
                } else if (itemMap.getType("value") == ReadableType.Number) {
                    valueData.add(itemMap.getInt("value"));
                }

                labelData.add(itemMap.getString("label"));
            }
            picker.setValueData(valueData);
            picker.setData(labelData);
        }
    }

    @ReactProp(name="selectedIndex")
    public void setSelectedIndex(ReactWheelCurvedPicker picker, int index) {
        if (picker != null && picker.getState() == AbstractWheelPicker.SCROLL_STATE_IDLE) {
            picker.setSelectedItemPosition(index);
            picker.invalidate();
        }
    }

    @ReactProp(name="itemTextColor", customType = "Color")
    public void setItemTextColor(ReactWheelCurvedPicker picker, Integer color) {
        if (picker != null) {
            picker.setSelectedItemTextColor(color);
            picker.setItemTextColor(color);
        }
    }

    @ReactProp(name="itemTextSize")
    public void setItemTextSize(ReactWheelCurvedPicker picker, int size) {
        if (picker != null) {
            picker.setItemTextSize((int) PixelUtil.toPixelFromDIP(size));
        }
    }

    @ReactProp(name="itemSpace")
    public void setItemSpace(ReactWheelCurvedPicker picker, int space) {
        if (picker != null) {
            picker.setItemSpace((int) PixelUtil.toPixelFromDIP(space));
        }
    }

    @ReactProp(name="cyclic")
    public void setCyclic(ReactWheelCurvedPicker picker, boolean isCyclic) {
        if (picker != null) {
            picker.setCyclic(isCyclic);
        }
    }

    @ReactProp(name="curtain")
    public void setCurtain(ReactWheelCurvedPicker picker, boolean haveCurtain) {
        if (picker != null) {
            picker.setCurtain(haveCurtain);
        }
    }

    @ReactProp(name="atmospheric")
    public void setAtmospheric(ReactWheelCurvedPicker picker, boolean haveAtmospheric) {
        if (picker != null) {
            picker.setAtmospheric(haveAtmospheric);
        }
    }

    @ReactProp(name="curved")
    public void setCurved(ReactWheelCurvedPicker picker, boolean isCurved) {
        if (picker != null) {
            picker.setCurved(isCurved);
        }
    }

    @ReactProp(name="selectedItemTextColor", customType = "Color")
    public void setSelectedItemTextColor(ReactWheelCurvedPicker picker, Integer color) {
        if (picker != null) {
            picker.setSelectedItemTextColor(color);
        }
    }

    @ReactProp(name="indicator")
    public void setIndicator(ReactWheelCurvedPicker picker, boolean hasIndicator) {
        if (picker != null) {
            picker.setIndicator(hasIndicator);
        }
    }

    @ReactProp(name="indicatorColor", customType = "Color")
    public void setIndicatorColor(ReactWheelCurvedPicker picker, Integer color) {
        if (picker != null) {
            picker.setIndicatorColor(color);
        }
    }

    @ReactProp(name="indicatorSize")
    public void setIndicatorSize(ReactWheelCurvedPicker picker, int size) {
        if (picker != null) {
            picker.setIndicatorSize((int) PixelUtil.toPixelFromDIP(size));
        }
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
