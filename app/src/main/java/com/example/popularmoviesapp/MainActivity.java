package com.example.popularmoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    ArrayList<Movie> movies = JsonUtils.extractMovieFromJSON("{\n" +
            "    \"page\": 1,\n" +
            "    \"total_results\": 10000,\n" +
            "    \"total_pages\": 500,\n" +
            "    \"results\": [\n" +
            "        {\n" +
            "            \"popularity\": 440.675,\n" +
            "            \"vote_count\": 2801,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg\",\n" +
            "            \"id\": 419704,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/5BwqwxMEjeFtdknRV792Svo0K1v.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Ad Astra\",\n" +
            "            \"genre_ids\": [\n" +
            "                18,\n" +
            "                878\n" +
            "            ],\n" +
            "            \"title\": \"Ad Astra\",\n" +
            "            \"vote_average\": 5.9,\n" +
            "            \"overview\": \"The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.\",\n" +
            "            \"release_date\": \"2019-09-17\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 428.036,\n" +
            "            \"vote_count\": 3730,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/s5HQf2Gb3lIO2cRcFwNL9sn1o1o.jpg\",\n" +
            "            \"id\": 335988,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/1n00NlOGRFZVs8coBxyZLm5l4EC.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Transformers: The Last Knight\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                12,\n" +
            "                878,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"Transformers: The Last Knight\",\n" +
            "            \"vote_average\": 6,\n" +
            "            \"overview\": \"Autobots and Decepticons are at war, with humans on the sidelines. Optimus Prime is gone. The key to saving our future lies buried in the secrets of the past, in the hidden history of Transformers on Earth.\",\n" +
            "            \"release_date\": \"2017-06-16\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 324.592,\n" +
            "            \"vote_count\": 12099,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/5vHssUeVe25bMrof1HyaPyWgaP.jpg\",\n" +
            "            \"id\": 245891,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/lvjRFFyNLdaMWIMYQvoebeO1JlF.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"John Wick\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"John Wick\",\n" +
            "            \"vote_average\": 7.2,\n" +
            "            \"overview\": \"Ex-hitman John Wick comes out of retirement to track down the gangsters that took everything from him.\",\n" +
            "            \"release_date\": \"2014-10-22\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 298.621,\n" +
            "            \"vote_count\": 7189,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/c9XxwwhPHdaImA2f1WEfEsbhaFB.jpg\",\n" +
            "            \"id\": 351286,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/3s9O5af2xWKWR5JzP2iJZpZeQQg.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Jurassic World: Fallen Kingdom\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                12,\n" +
            "                878\n" +
            "            ],\n" +
            "            \"title\": \"Jurassic World: Fallen Kingdom\",\n" +
            "            \"vote_average\": 6.5,\n" +
            "            \"overview\": \"Three years after the demise of Jurassic World, a volcanic eruption threatens the remaining dinosaurs on the isla Nublar, so Claire Dearing, the former park manager, recruits Owen Grady to help prevent the extinction of the dinosaurs once again.\",\n" +
            "            \"release_date\": \"2018-06-06\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 297.915,\n" +
            "            \"vote_count\": 2262,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/y95lQLnuNKdPAzw9F9Ab8kJ80c3.jpg\",\n" +
            "            \"id\": 38700,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/upUy2QhMZEmtypPW3PdieKLAHxh.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Bad Boys for Life\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                80,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"Bad Boys for Life\",\n" +
            "            \"vote_average\": 7,\n" +
            "            \"overview\": \"Marcus and Mike are forced to confront new threats, career changes, and midlife crises as they join the newly created elite team AMMO of the Miami police department to take down the ruthless Armando Armas, the vicious leader of a Miami drug cartel.\",\n" +
            "            \"release_date\": \"2020-01-15\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 276.839,\n" +
            "            \"vote_count\": 1230,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg\",\n" +
            "            \"id\": 338762,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/ocUrMYbdjknu2TwzMHKT9PBBQRw.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Bloodshot\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                878\n" +
            "            ],\n" +
            "            \"title\": \"Bloodshot\",\n" +
            "            \"vote_average\": 7.2,\n" +
            "            \"overview\": \"After he and his wife are murdered, marine Ray Garrison is resurrected by a team of scientists. Enhanced with nanotechnology, he becomes a superhuman, biotech killing machine\\u2014'Bloodshot'. As Ray first trains with fellow super-soldiers, he cannot recall anything from his former life. But when his memories flood back and he remembers the man that killed both him and his wife, he breaks out of the facility to get revenge, only to discover that there's more to the conspiracy than he thought.\",\n" +
            "            \"release_date\": \"2020-02-20\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 276.492,\n" +
            "            \"vote_count\": 2684,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/nrsx0jEaBgXq4PWo7SooSnYJTv.jpg\",\n" +
            "            \"id\": 274855,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/79X8JkGxzc1tJMi0KxUSaPLooVg.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Geostorm\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                878,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"Geostorm\",\n" +
            "            \"vote_average\": 5.8,\n" +
            "            \"overview\": \"After an unprecedented series of natural disasters threatened the planet, the world's leaders came together to create an intricate network of satellites to control the global climate and keep everyone safe. But now, something has gone wrong: the system built to protect Earth is attacking it, and it becomes a race against the clock to uncover the real threat before a worldwide geostorm wipes out everything and everyone along with it.\",\n" +
            "            \"release_date\": \"2017-10-13\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 258.636,\n" +
            "            \"vote_count\": 1871,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/4gpW4MkRI0BB17GHOJzXq0WamDP.jpg\",\n" +
            "            \"id\": 300671,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/hY8myWhjSOUeeiPskhOfwwgaCwc.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"13 Hours: The Secret Soldiers of Benghazi\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                36,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"13 Hours: The Secret Soldiers of Benghazi\",\n" +
            "            \"vote_average\": 7.1,\n" +
            "            \"overview\": \"An American Ambassador is killed during an attack at a U.S. compound in Libya as a security team struggles to make sense out of the chaos.\",\n" +
            "            \"release_date\": \"2016-01-13\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 239.338,\n" +
            "            \"vote_count\": 499,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/6vaqTxkRnoGwjeLqyZbVuVR7Snv.jpg\",\n" +
            "            \"id\": 443791,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/fYPiQewg7ogbzro2XcCTACSB2KC.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Underwater\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                27,\n" +
            "                878,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"title\": \"Underwater\",\n" +
            "            \"vote_average\": 6.4,\n" +
            "            \"overview\": \"After an earthquake destroys their underwater station, six researchers must navigate two miles along the dangerous, unknown depths of the ocean floor to make it to safety in a race against time.\",\n" +
            "            \"release_date\": \"2020-01-08\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 237.952,\n" +
            "            \"vote_count\": 2513,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg\",\n" +
            "            \"id\": 495764,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/jiqD14fg7UTZOT6qgvzTmfRYpWI.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                35,\n" +
            "                80\n" +
            "            ],\n" +
            "            \"title\": \"Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)\",\n" +
            "            \"vote_average\": 7.1,\n" +
            "            \"overview\": \"Harley Quinn joins forces with a singer, an assassin and a police detective to help a young girl who had a hit placed on her after she stole a rare diamond from a crime lord.\",\n" +
            "            \"release_date\": \"2020-02-05\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 231.517,\n" +
            "            \"vote_count\": 854,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/36nD4aDvoFMewiHBnIcGo3vVcd6.jpg\",\n" +
            "            \"id\": 14128,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/4ErXZZgpXPxDcCLnnLXysK03ss9.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Cinderella II: Dreams Come True\",\n" +
            "            \"genre_ids\": [\n" +
            "                16,\n" +
            "                14,\n" +
            "                10749,\n" +
            "                10751\n" +
            "            ],\n" +
            "            \"title\": \"Cinderella II: Dreams Come True\",\n" +
            "            \"vote_average\": 5.9,\n" +
            "            \"overview\": \"As a newly crowned princess, Cinderella quickly learns that life at the Palace - and her royal responsibilities - are more challenging than she had imagined. In three heartwarming tales, Cinderella calls on her animal friends and her Fairy Godmother to help as she brings her own grace and charm to her regal role and discovers that being true to yourself is the best way to make your dreams come true.\",\n" +
            "            \"release_date\": \"2002-02-23\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 205.958,\n" +
            "            \"vote_count\": 841,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/f4aul3FyD3jv3v4bul1IrkWZvzq.jpg\",\n" +
            "            \"id\": 508439,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/xFxk4vnirOtUxpOEWgA1MCRfy6J.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Onward\",\n" +
            "            \"genre_ids\": [\n" +
            "                12,\n" +
            "                16,\n" +
            "                35,\n" +
            "                14,\n" +
            "                10751\n" +
            "            ],\n" +
            "            \"title\": \"Onward\",\n" +
            "            \"vote_average\": 8,\n" +
            "            \"overview\": \"In a suburban fantasy world, two teenage elf brothers embark on an extraordinary quest to discover if there is still a little magic left out there.\",\n" +
            "            \"release_date\": \"2020-02-29\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 200.303,\n" +
            "            \"vote_count\": 1738,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/8ZX18L5m6rH5viSYpRnTSbb9eXh.jpg\",\n" +
            "            \"id\": 619264,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/3tkDMNfM2YuIAJlvGO6rfIzAnfG.jpg\",\n" +
            "            \"original_language\": \"es\",\n" +
            "            \"original_title\": \"El hoyo\",\n" +
            "            \"genre_ids\": [\n" +
            "                18,\n" +
            "                878\n" +
            "            ],\n" +
            "            \"title\": \"The Platform\",\n" +
            "            \"vote_average\": 7.2,\n" +
            "            \"overview\": \"A mysterious place, an indescribable prison, a deep hole. An unknown number of levels. Two inmates living on each level. A descending platform containing food for all of them. An inhuman fight for survival, but also an opportunity for solidarity\\u2026\",\n" +
            "            \"release_date\": \"2019-11-08\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 189.675,\n" +
            "            \"id\": 1572,\n" +
            "            \"video\": false,\n" +
            "            \"vote_count\": 3731,\n" +
            "            \"vote_average\": 7.1,\n" +
            "            \"title\": \"Die Hard: With a Vengeance\",\n" +
            "            \"release_date\": \"1995-05-19\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Die Hard: With a Vengeance\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"backdrop_path\": \"\\/aJCEQFFXNcfg5YneJzTG15qzxF7.jpg\",\n" +
            "            \"adult\": false,\n" +
            "            \"overview\": \"New York detective John McClane is back and kicking bad-guy butt in the third installment of this action-packed series, which finds him teaming with civilian Zeus Carver to prevent the loss of innocent lives. McClane thought he'd seen it all, until a genius named Simon engages McClane, his new \\\"partner\\\" -- and his beloved city -- in a deadly game that demands their concentration.\",\n" +
            "            \"poster_path\": \"\\/ivpXGcsPZglyYrN65THWSD7JM3q.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 180.022,\n" +
            "            \"vote_count\": 3741,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/db32LaOibwEliAmSL2jjDF6oDdj.jpg\",\n" +
            "            \"id\": 181812,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/jOzrELAzFxtMx2I4uDGHOotdfsS.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Star Wars: The Rise of Skywalker\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                12,\n" +
            "                878\n" +
            "            ],\n" +
            "            \"title\": \"Star Wars: The Rise of Skywalker\",\n" +
            "            \"vote_average\": 6.5,\n" +
            "            \"overview\": \"The surviving Resistance faces the First Order once again as the journey of Rey, Finn and Poe Dameron continues. With the power and knowledge of generations behind them, the final battle begins.\",\n" +
            "            \"release_date\": \"2019-12-18\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 178.447,\n" +
            "            \"vote_count\": 1946,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg\",\n" +
            "            \"id\": 454626,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Sonic the Hedgehog\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                16,\n" +
            "                35,\n" +
            "                878,\n" +
            "                10751\n" +
            "            ],\n" +
            "            \"title\": \"Sonic the Hedgehog\",\n" +
            "            \"vote_average\": 7.4,\n" +
            "            \"overview\": \"Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world\\u2019s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.\",\n" +
            "            \"release_date\": \"2020-02-12\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 176.327,\n" +
            "            \"vote_count\": 3529,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/k1bhUW7XM5X0yD3iewAEvloFBEo.jpg\",\n" +
            "            \"id\": 76285,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/3NK02PLJSs01SY1hsXUAcqbG3WP.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Percy Jackson: Sea of Monsters\",\n" +
            "            \"genre_ids\": [\n" +
            "                12,\n" +
            "                14,\n" +
            "                10751\n" +
            "            ],\n" +
            "            \"title\": \"Percy Jackson: Sea of Monsters\",\n" +
            "            \"vote_average\": 5.9,\n" +
            "            \"overview\": \"In their quest to confront the ultimate evil, Percy and his friends battle swarms of mythical creatures to find the mythical Golden Fleece and to stop an ancient evil from rising.\",\n" +
            "            \"release_date\": \"2013-08-07\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 174.933,\n" +
            "            \"vote_count\": 2873,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/bB42KDdfWkOvmzmYkmK58ZlCa9P.jpg\",\n" +
            "            \"id\": 512200,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/hreiLoPysWG79TsyQgMzFKaOTF5.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Jumanji: The Next Level\",\n" +
            "            \"genre_ids\": [\n" +
            "                12,\n" +
            "                35,\n" +
            "                14\n" +
            "            ],\n" +
            "            \"title\": \"Jumanji: The Next Level\",\n" +
            "            \"vote_average\": 6.8,\n" +
            "            \"overview\": \"As the gang return to Jumanji to rescue one of their own, they discover that nothing is as they expect. The players will have to brave parts unknown and unexplored in order to escape the world\\u2019s most dangerous game.\",\n" +
            "            \"release_date\": \"2019-12-04\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 166.575,\n" +
            "            \"vote_count\": 13578,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/D6e8RJf2qUstnfkTslTXNTUAlT.jpg\",\n" +
            "            \"id\": 102899,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/kvXLZqY0Ngl1XSw7EaMQO0C1CCj.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"Ant-Man\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                12,\n" +
            "                878\n" +
            "            ],\n" +
            "            \"title\": \"Ant-Man\",\n" +
            "            \"vote_average\": 7.1,\n" +
            "            \"overview\": \"Armed with the astonishing ability to shrink in scale but increase in strength, master thief Scott Lang must embrace his inner-hero and help his mentor, Doctor Hank Pym, protect the secret behind his spectacular Ant-Man suit from a new generation of towering threats. Against seemingly insurmountable obstacles, Pym and Lang must plan and pull off a heist that will save the world.\",\n" +
            "            \"release_date\": \"2015-07-14\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"popularity\": 156.302,\n" +
            "            \"vote_count\": 770,\n" +
            "            \"video\": false,\n" +
            "            \"poster_path\": \"\\/z4A6mFOLTMZAhCSPRyrtzG0SPbd.jpg\",\n" +
            "            \"id\": 475303,\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"\\/6fkqwqLEcDZOEAnBBfKAniwNxtx.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"A Rainy Day in New York\",\n" +
            "            \"genre_ids\": [\n" +
            "                35,\n" +
            "                10749\n" +
            "            ],\n" +
            "            \"title\": \"A Rainy Day in New York\",\n" +
            "            \"vote_average\": 6.6,\n" +
            "            \"overview\": \"Two young people arrive in New York to spend a weekend, but once they arrive they're met with bad weather and a series of adventures.\",\n" +
            "            \"release_date\": \"2019-07-26\"\n" +
            "        }]}");

    RecyclerView recyclerView;
    MovieAdapter mAdapter;
    private final String api_key = "51ef7ace762b5d09cd2b2a5930bfde3c";

    public MainActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Picasso.get().setLoggingEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        mAdapter = new MovieAdapter(this, this,movies);
        recyclerView.setAdapter(mAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);

        recyclerView.setLayoutManager(gridLayoutManager);

/*        Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
        builder.listener(new Picasso.Listener() {

            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }});*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.sort_order, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_best_rated) {
            Toast.makeText(this,"Best Rated",Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.menu_most_popular) {
            Toast.makeText(this,"Most Popular",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Class destinationClass = DetailsActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("movie", movie);
        startActivity(intentToStartDetailActivity);
    }

}
