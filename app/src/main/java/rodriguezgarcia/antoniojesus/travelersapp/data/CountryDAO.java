package rodriguezgarcia.antoniojesus.travelersapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import rodriguezgarcia.antoniojesus.travelersapp.model.Country;

@Dao
public interface CountryDAO {

    @Insert
    public String insertCountry(Country country);

    @Update
    public int updateCountry(Country country);

    @Query("SELECT name FROM countries WHERE name = :state")
    public String getCountry(String state);


}
