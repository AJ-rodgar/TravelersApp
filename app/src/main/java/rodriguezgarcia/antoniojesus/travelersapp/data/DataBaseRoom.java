package rodriguezgarcia.antoniojesus.travelersapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class}, version = 5, exportSchema = false)
public abstract class DataBaseRoom extends RoomDatabase {

    public abstract CountryDAO countryDAO();
    private static DataBaseRoom INSTANCE = null;

    public static DataBaseRoom getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataBaseRoom.class, "list_countries.db").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
