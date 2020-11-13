package com.example.webmasters.converters;

import androidx.databinding.InverseMethod;

/**
 * SharedConverters contains all the general-purpose two-way data binding converters.
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public class SharedConverters {

    /**
     * intToFloat is mainly used to convert SeekBar bindable float back to its original format.
     * @param floatValue (float)
     * @return (int)
     */
    @InverseMethod("intToFloat")
    public static int floatToInt(float floatValue) {
        return (int)(floatValue * 100);
    }

    /**
     * floatToInt is mainly used to convert a float to SeekBar bindable format.
     * @param intValue (int)
     * @return (float)
     */
    @InverseMethod("floatToInt")
    public static float intToFloat(int intValue) {
        return intValue / 100f;
    }

}

