package rodriguezgarcia.antoniojesus.travelersapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CountryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertCountry(Country country);

    @Update
    public int updateCountry(Country country);

    @Delete
    public int deleteCountry(Country country);

    @Query("SELECT * FROM countries WHERE name = :name")
    public Country getCountry(String name);

    @Query("SELECT * FROM COUNTRIES")
    public List<Country> getCountries();

    @Query("SELECT name FROM countries WHERE state = :state")
    public List<String> getCountriesByState(int state);

    @Query("SELECT state FROM countries WHERE name = :name")
    public int getState(String name);


}
