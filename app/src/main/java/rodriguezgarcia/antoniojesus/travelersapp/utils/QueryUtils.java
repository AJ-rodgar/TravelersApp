package rodriguezgarcia.antoniojesus.travelersapp.utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import rodriguezgarcia.antoniojesus.travelersapp.R;
import rodriguezgarcia.antoniojesus.travelersapp.data.Country;

public class QueryUtils {

    private static final String LOG_TAG = "QUERY UTILS";

    public static List<Country> extractFeatureFromJson(String response) {
        if (TextUtils.isEmpty(response)) {
            return null;
        }

        List<Country> countries = new ArrayList<>();

        Map<String, Integer> codeToFlag;
        codeToFlag = loadCodes();

        try {

            JSONArray countryArray = new JSONArray(response);

            for (int i = 0; i < countryArray.length(); i++) {
                JSONObject currentCountry = countryArray.getJSONObject(i);

                String name = currentCountry.getString("name");
                String capital = currentCountry.getString("capital");
                String region = currentCountry.getString("region");
                String code = currentCountry.getString("alpha3Code");

                JSONArray currencies = currentCountry.getJSONArray("currencies");
                JSONObject currencyObject = currencies.getJSONObject(0);
                String currency = currencyObject.getString("name");

                JSONArray languages = currentCountry.getJSONArray("languages");
                JSONObject languageObject = languages.getJSONObject(0);
                String language = languageObject.getString("name");

                double latitude = 0;
                double longitude = 0;

                JSONArray latlng = currentCountry.getJSONArray("latlng");
                //Hay un solo pais que no tiene latitud ni longitud, asi que he metido ese dato a mano
                if (latlng.length() != 0) {
                    latitude = latlng.getDouble(0);
                    longitude = latlng.getDouble(1);
                } else {
                    latitude = 19.2823181;
                    longitude = 166.647049;
                }


                int flag = 0;

                if (codeToFlag.keySet().contains(code)){
                    flag = codeToFlag.get(code);
                }

                int state = -1;

                Country country = new Country(name,region,capital,language, currency, longitude, latitude, state, flag);
                countries.add(country);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countries;
    }

    private static Map<String, Integer> loadCodes() {
        Map<String, Integer> codeToFlag = new TreeMap<>();

        codeToFlag.put("ABW", R.drawable.aruba);
        codeToFlag.put("AFG", R.drawable.afghanistan);
        codeToFlag.put("AGO", R.drawable.angola);
        codeToFlag.put("AIA", R.drawable.anguilla);
        codeToFlag.put("ALA", R.drawable.aland);
        codeToFlag.put("ALB", R.drawable.albania);
        codeToFlag.put("AND", R.drawable.andorra);
        codeToFlag.put("ARE", R.drawable.unitedarabemirates);
        codeToFlag.put("ARG", R.drawable.argentina);
        codeToFlag.put("ARM", R.drawable.armenia);
        codeToFlag.put("ASM", R.drawable.americansamoa);
        codeToFlag.put("ATA", R.drawable.antarctica);
        codeToFlag.put("ATF", R.drawable.frenchsouthern);
        codeToFlag.put("ATG", R.drawable.antiguaandbarbuda);
        codeToFlag.put("AUS", R.drawable.australia);
        codeToFlag.put("AUT", R.drawable.austria);
        codeToFlag.put("AZE", R.drawable.azerbaijan);
        codeToFlag.put("BDI", R.drawable.burundi);
        codeToFlag.put("BEL", R.drawable.belgium);
        codeToFlag.put("BEN", R.drawable.benin);
        codeToFlag.put("BES", R.drawable.bonaire);
        codeToFlag.put("BFA", R.drawable.burkinafaso);
        codeToFlag.put("BGD", R.drawable.bangladesh);
        codeToFlag.put("BGR", R.drawable.bulgaria);
        codeToFlag.put("BHR", R.drawable.bahrain);
        codeToFlag.put("BHS", R.drawable.bahamas);
        codeToFlag.put("BIH", R.drawable.bosniaandherzegovina);
        codeToFlag.put("BLM", R.drawable.blm);
        codeToFlag.put("BLR", R.drawable.belarus);
        codeToFlag.put("BLZ", R.drawable.belize);
        codeToFlag.put("BMU", R.drawable.bermuda);
        codeToFlag.put("BOL", R.drawable.bolivia);
        codeToFlag.put("BRA", R.drawable.brazil);
        codeToFlag.put("BRB", R.drawable.barbados);
        codeToFlag.put("BRN", R.drawable.brunei);
        codeToFlag.put("BTN", R.drawable.bhutan);
        codeToFlag.put("BVT", R.drawable.norway);
        codeToFlag.put("BWA", R.drawable.botswana);
        codeToFlag.put("CAF", R.drawable.centralafricanrepublic);
        codeToFlag.put("CAN", R.drawable.canada);
        codeToFlag.put("CCK", R.drawable.territoryofcocos);
        codeToFlag.put("CHE", R.drawable.switzerland);
        codeToFlag.put("CHL", R.drawable.chile);
        codeToFlag.put("CHN", R.drawable.china);
        codeToFlag.put("CIV", R.drawable.ivorycoast);
        codeToFlag.put("CMR", R.drawable.cameroon);
        codeToFlag.put("COD", R.drawable.democraticrepublicofthecongo);
        codeToFlag.put("COG", R.drawable.republicofthecongo);
        codeToFlag.put("COK", R.drawable.cookislands);
        codeToFlag.put("COL", R.drawable.colombia);
        codeToFlag.put("COM", R.drawable.comoros);
        codeToFlag.put("CPV", R.drawable.capeverde);
        codeToFlag.put("CRI", R.drawable.costarica);
        codeToFlag.put("CUB", R.drawable.cuba);
        codeToFlag.put("CUW", R.drawable.curazao);
        codeToFlag.put("CXR", R.drawable.christmasisland);
        codeToFlag.put("CYM", R.drawable.caymanislands);
        codeToFlag.put("CYP", R.drawable.cyprus);
        codeToFlag.put("CZE", R.drawable.czechrepublic);
        codeToFlag.put("DEU", R.drawable.germany);
        codeToFlag.put("DJI", R.drawable.djibouti);
        codeToFlag.put("DMA", R.drawable.dominica);
        codeToFlag.put("DNK", R.drawable.denmark);
        codeToFlag.put("DOM", R.drawable.dominicanrepublic);
        codeToFlag.put("DZA", R.drawable.algeria);
        codeToFlag.put("ECU", R.drawable.ecuador);
        codeToFlag.put("EGY", R.drawable.egypt);
        codeToFlag.put("ERI", R.drawable.eritrea);
        codeToFlag.put("ESH", R.drawable.westernsahara);
        codeToFlag.put("ESP", R.drawable.spain);
        codeToFlag.put("EST", R.drawable.estonia);
        codeToFlag.put("ETH", R.drawable.ethiopia);
        codeToFlag.put("FIN", R.drawable.finland);
        codeToFlag.put("FJI", R.drawable.fiji);
        codeToFlag.put("FLK", R.drawable.falklandislands);
        codeToFlag.put("FRA", R.drawable.france);
        codeToFlag.put("FRO", R.drawable.faroeislands);
        codeToFlag.put("FSM", R.drawable.federatedstatesofmicronesia);
        codeToFlag.put("GAB", R.drawable.gabon);
        codeToFlag.put("GBR", R.drawable.unitedkingdom);
        codeToFlag.put("GEO", R.drawable.georgia);
        codeToFlag.put("GGY", R.drawable.ggy);
        codeToFlag.put("GHA", R.drawable.ghana);
        codeToFlag.put("GIB", R.drawable.gibraltar);
        codeToFlag.put("GIN", R.drawable.guinea);
        codeToFlag.put("GLP", R.drawable.glp);
        codeToFlag.put("GMB", R.drawable.gambia);
        codeToFlag.put("GNB", R.drawable.guineabissau);
        codeToFlag.put("GNQ", R.drawable.equatorialguinea);
        codeToFlag.put("GRC", R.drawable.greece);
        codeToFlag.put("GRD", R.drawable.grenada);
        codeToFlag.put("GRL", R.drawable.greenland);
        codeToFlag.put("GTM", R.drawable.guatemala);
        codeToFlag.put("GUF", R.drawable.guf);
        codeToFlag.put("GUM", R.drawable.guam);
        codeToFlag.put("GUY", R.drawable.guyana);
        codeToFlag.put("HKG", R.drawable.hongkong);
        codeToFlag.put("HMD", R.drawable.hmd);
        codeToFlag.put("HND", R.drawable.honduras);
        codeToFlag.put("HRV", R.drawable.croatia);
        codeToFlag.put("HTI", R.drawable.haiti);
        codeToFlag.put("HUN", R.drawable.hungary);
        codeToFlag.put("IDN", R.drawable.indonesia);
        codeToFlag.put("IMN", R.drawable.isleman);
        codeToFlag.put("IND", R.drawable.india);
        codeToFlag.put("IOT", R.drawable.britishindianoceanterritory);
        codeToFlag.put("IRL", R.drawable.ireland);
        codeToFlag.put("IRN", R.drawable.iran);
        codeToFlag.put("IRQ", R.drawable.iraq);
        codeToFlag.put("ISL", R.drawable.iceland);
        codeToFlag.put("ISR", R.drawable.israel);
        codeToFlag.put("ITA", R.drawable.italy);
        codeToFlag.put("JAM", R.drawable.jamaica);
        codeToFlag.put("JEY", R.drawable.jersey);
        codeToFlag.put("JOR", R.drawable.jordan);
        codeToFlag.put("JPN", R.drawable.japan);
        codeToFlag.put("KAZ", R.drawable.kazakhstan);
        codeToFlag.put("KEN", R.drawable.kenya);
        codeToFlag.put("KGZ", R.drawable.kyrgyzstan);
        codeToFlag.put("KHM", R.drawable.cambodia);
        codeToFlag.put("KIR", R.drawable.kiribati);
        codeToFlag.put("KNA", R.drawable.saintkittsandnevis);
        codeToFlag.put("KOR", R.drawable.southkorea);
        codeToFlag.put("KWT", R.drawable.kuwait);
        codeToFlag.put("LAO", R.drawable.laos);
        codeToFlag.put("LBN", R.drawable.lebanon);
        codeToFlag.put("LBR", R.drawable.liberia);
        codeToFlag.put("LBY", R.drawable.libya);
        codeToFlag.put("LCA", R.drawable.saintlucia);
        codeToFlag.put("LIE", R.drawable.liechtenstein);
        codeToFlag.put("LKA", R.drawable.srilanka);
        codeToFlag.put("LSO", R.drawable.lesotho);
        codeToFlag.put("LTU", R.drawable.lithuania);
        codeToFlag.put("LUX", R.drawable.luxembourg);
        codeToFlag.put("LVA", R.drawable.latvia);
        codeToFlag.put("MAC", R.drawable.macau);
        codeToFlag.put("MAF", R.drawable.france);
        codeToFlag.put("MAR", R.drawable.morocco);
        codeToFlag.put("MCO", R.drawable.monaco);
        codeToFlag.put("MDA", R.drawable.moldova);
        codeToFlag.put("MDG", R.drawable.madagascar);
        codeToFlag.put("MDV", R.drawable.maldives);
        codeToFlag.put("MEX", R.drawable.mexico);
        codeToFlag.put("MHL", R.drawable.marshallislands);
        codeToFlag.put("MKD", R.drawable.northmacedonia);
        codeToFlag.put("MLI", R.drawable.mali);
        codeToFlag.put("MLT", R.drawable.malta);
        codeToFlag.put("MMR", R.drawable.myanmar);
        codeToFlag.put("MNE", R.drawable.montenegro);
        codeToFlag.put("MNG", R.drawable.mongolia);
        codeToFlag.put("MNP", R.drawable.northernmarianaislands);
        codeToFlag.put("MOZ", R.drawable.mozambique);
        codeToFlag.put("MRT", R.drawable.mauritania);
        codeToFlag.put("MSR", R.drawable.montserrat);
        codeToFlag.put("MTQ", R.drawable.mtq);
        codeToFlag.put("MUS", R.drawable.mauritius);
        codeToFlag.put("MWI", R.drawable.malawi);
        codeToFlag.put("MYS", R.drawable.malaysia);
        codeToFlag.put("MYT", R.drawable.myt);
        codeToFlag.put("NAM", R.drawable.namibia);
        codeToFlag.put("NCL", R.drawable.newcaledonia);
        codeToFlag.put("NER", R.drawable.niger);
        codeToFlag.put("NFK", R.drawable.norfolkisland);
        codeToFlag.put("NGA", R.drawable.nigeria);
        codeToFlag.put("NIC", R.drawable.nicaragua);
        codeToFlag.put("NIU", R.drawable.niue);
        codeToFlag.put("NLD", R.drawable.netherlands);
        codeToFlag.put("NOR", R.drawable.norway);
        codeToFlag.put("NPL", R.drawable.nepal);
        codeToFlag.put("NRU", R.drawable.nauru);
        codeToFlag.put("NZL", R.drawable.newzealand);
        codeToFlag.put("OMN", R.drawable.oman);
        codeToFlag.put("PAK", R.drawable.pakistan);
        codeToFlag.put("PAN", R.drawable.panama);
        codeToFlag.put("PCN", R.drawable.pitcairnislands);
        codeToFlag.put("PER", R.drawable.peru);
        codeToFlag.put("PHL", R.drawable.philippines);
        codeToFlag.put("PLW", R.drawable.palau);
        codeToFlag.put("PNG", R.drawable.papuanewguinea);
        codeToFlag.put("POL", R.drawable.poland);
        codeToFlag.put("PRI", R.drawable.puertorico);
        codeToFlag.put("PRK", R.drawable.northkorea);
        codeToFlag.put("PRT", R.drawable.portugal);
        codeToFlag.put("PRY", R.drawable.paraguay);
        codeToFlag.put("PSE", R.drawable.palestine);
        codeToFlag.put("PYF", R.drawable.frenchpolynesia);
        codeToFlag.put("QAT", R.drawable.qatar);
        codeToFlag.put("REU", R.drawable.france);
        codeToFlag.put("ROU", R.drawable.romania);
        codeToFlag.put("RUS", R.drawable.russia);
        codeToFlag.put("RWA", R.drawable.rwanda);
        codeToFlag.put("SAU", R.drawable.saudiarabia);
        codeToFlag.put("SDN", R.drawable.sudan);
        codeToFlag.put("SEN", R.drawable.senegal);
        codeToFlag.put("SGP", R.drawable.singapore);
        codeToFlag.put("SGS", R.drawable.southgeorgiaandthesouthsandwichislands);
        codeToFlag.put("SHN", R.drawable.unitedkingdom);
        codeToFlag.put("SJM", R.drawable.norway);
        codeToFlag.put("SLB", R.drawable.solomonislands);
        codeToFlag.put("SLE", R.drawable.sierraleone);
        codeToFlag.put("SLV", R.drawable.elsalvador);
        codeToFlag.put("SMR", R.drawable.sanmarino);
        codeToFlag.put("SOM", R.drawable.somalia);
        codeToFlag.put("SPM", R.drawable.spm);
        codeToFlag.put("SRB", R.drawable.serbia);
        codeToFlag.put("SSD", R.drawable.southsudan);
        codeToFlag.put("STP", R.drawable.saotomeandprincipe);
        codeToFlag.put("SUR", R.drawable.suriname);
        codeToFlag.put("SVK", R.drawable.slovakia);
        codeToFlag.put("SVN", R.drawable.slovenia);
        codeToFlag.put("SWE", R.drawable.sweden);
        codeToFlag.put("SWZ", R.drawable.eswatini);
        codeToFlag.put("SXM", R.drawable.sintmaarten);
        codeToFlag.put("SYC", R.drawable.seychelles);
        codeToFlag.put("SYR", R.drawable.syria);
        codeToFlag.put("TCA", R.drawable.turksandcaicosislands);
        codeToFlag.put("TCD", R.drawable.chad);
        codeToFlag.put("TGO", R.drawable.togo);
        codeToFlag.put("THA", R.drawable.thailand);
        codeToFlag.put("TJK", R.drawable.tajikistan);
        codeToFlag.put("TKL", R.drawable.tokelau);
        codeToFlag.put("TKM", R.drawable.turkmenistan);
        codeToFlag.put("TLS", R.drawable.easttimor);
        codeToFlag.put("TON", R.drawable.tonga);
        codeToFlag.put("TTO", R.drawable.trinidadandtobago);
        codeToFlag.put("TUN", R.drawable.tunisia);
        codeToFlag.put("TUR", R.drawable.turkey);
        codeToFlag.put("TUV", R.drawable.tuvalu);
        codeToFlag.put("TWN", R.drawable.taiwan);
        codeToFlag.put("TZA", R.drawable.tanzania);
        codeToFlag.put("UGA", R.drawable.uganda);
        codeToFlag.put("UKR", R.drawable.ukraine);
        codeToFlag.put("UMI", R.drawable.unitedstates);
        codeToFlag.put("URY", R.drawable.uruguay);
        codeToFlag.put("USA", R.drawable.unitedstates);
        codeToFlag.put("UZB", R.drawable.uzbekistan);
        codeToFlag.put("VAT", R.drawable.vaticancity);
        codeToFlag.put("VCT", R.drawable.saintvincentandthegrenadines);
        codeToFlag.put("VEN", R.drawable.venezuela);
        codeToFlag.put("VGB", R.drawable.britishvirginislands);
        codeToFlag.put("VIR", R.drawable.unitedstatesvirginislands);
        codeToFlag.put("VNM", R.drawable.vietnam);
        codeToFlag.put("VUT", R.drawable.vanuatu);
        codeToFlag.put("WLF", R.drawable.wallisandfutuna);
        codeToFlag.put("WSM", R.drawable.samoa);
        codeToFlag.put("YEM", R.drawable.yemen);
        codeToFlag.put("ZAF", R.drawable.southafrica);
        codeToFlag.put("ZMB", R.drawable.zambia);
        codeToFlag.put("ZWE", R.drawable.zimbabwe);

        return codeToFlag;
    }

    public static List<Country> fetchCountryData(String requestUrl) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Country> countries = extractFeatureFromJson(jsonResponse);

        return countries;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
