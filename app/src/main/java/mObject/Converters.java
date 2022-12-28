package mObject;

import androidx.room.TypeConverter;

import java.util.Date;

//Room提供了基本類型到包裝類型的轉換，但是不允許實體類型之間的引用，使用類型轉換
public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
