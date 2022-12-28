package mObject;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = { Member.class }, version = 1,exportSchema = false) //exportSchema: 表示是否生成資料庫結構檔案
@TypeConverters({Converters.class})
public abstract class DB extends RoomDatabase {
    private static final String DB_NAME = "JDaily.db";
    private static volatile DB instance;
    public static synchronized DB getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }
    private static DB create(final Context context) {
        return Room.databaseBuilder(
                context,
                DB.class,
                DB_NAME).addMigrations().build();
    }
    public abstract MemberDao getMemberDao();
}
