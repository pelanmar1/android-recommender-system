package com.example.pedrolanzagorta.realmtest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.pedrolanzagorta.realmtest.R;
import com.example.pedrolanzagorta.realmtest.controllers.ProductsController;
import com.example.pedrolanzagorta.realmtest.controllers.RecommenderSystemController;
import com.example.pedrolanzagorta.realmtest.controllers.UsersController;
import com.example.pedrolanzagorta.realmtest.models.Product;
import com.example.pedrolanzagorta.realmtest.models.RatingRecord;
import com.example.pedrolanzagorta.realmtest.models.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnGoToSignIn,btnGoToLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoToSignIn = (Button) findViewById(R.id.btnGoToSignIn);
        btnGoToLogIn = (Button) findViewById(R.id.btnGoToLogIn);
        registerSampleProducts();
        registerSampleUsers();
        List<Product> productList = ProductsController.getProductsSortedByID();
        for(Product product:productList)
            System.out.println(product.getName());


    }

    public void goToLogIn(View view) {
       startActivity(new Intent(this, LogInActivity.class));

    }
    public void goToSignIn(View view)  {
        startActivity(new Intent(this, SignInActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.configErase) {
            UsersController.deleteAllUsers();
            ProductsController.deleteAllProducts();
            Toast.makeText(MainActivity.this,"Database successfully erased",Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.usersList){
            startActivity(new Intent(this,UserMainPageActivity.class));
        }
        return true;
    }

    public void registerSampleProducts() {
        if (ProductsController.countProducts() == 0) {
            ProductsController.addProduct(new Product("Monty Python And The Holly Grail", "https://d13yacurqjgara.cloudfront.net/users/204898/screenshots/1491795/attachments/223601/Monty1.jpg"));
            ProductsController.addProduct(new Product("The Lord of the Rings: The Fellowship of the Ring", "http://www.gstatic.com/tv/thumb/movieposters/28828/p28828_p_v8_as.jpg"));
            ProductsController.addProduct(new Product("Breaking Dawn", "https://fanart.tv/fanart/movies/50620/movieposter/the-twilight-saga-breaking-dawn---part-2-53c39704de23f.jpg"));
            ProductsController.addProduct(new Product("Shrek", "http://vignette3.wikia.nocookie.net/shrek/images/7/7c/Shrek_Poster_02.jpg/revision/latest?cb=20150119045951"));
            ProductsController.addProduct(new Product("Batman: The Dark Knight", "http://www.freedesign4.me/wp-content/gallery/posters/free-movie-film-poster-the_dark_knight_movie_poster.jpg"));
            ProductsController.addProduct(new Product("SCREAM", "https://fanart.tv/fanart/movies/4232/movieposter/scream-536d281d454f2.jpg"));
            ProductsController.addProduct(new Product("The Hunger Games", "http://www.hungergamesdwtc.net/wp-content/uploads/2014/02/The-Hunger-Games-Poster.jpg"));
            ProductsController.addProduct(new Product("Harry Potter and the Order of the Phoenix", "http://www.freedesign4.me/wp-content/gallery/posters/free-movie-film-poster-harry-potter-phoenix.jpg"));
            ProductsController.addProduct(new Product("Finding Nemo", "http://assets.fontsinuse.com/static/use-media-items/17/16316/full-900x1300/56702cc2/pva6ds.jpeg?resolution=0"));
            ProductsController.addProduct(new Product("Eclipe", "http://api.ning.com/files/jsxW24cFoEif2t5wdaSG*2mRX0wN1pAtaaD*wFupS-yRs8lYnDFV*BjZJL3Y-V3cAkBMNprRc-sYkGEX-r0al1p5wpH0kBRm/7UoOHjmRoOBGsm6k724IMq8saAo.jpg"));
            ProductsController.addProduct(new Product("The Lord of the Rings: The Two Towers", "http://www.gstatic.com/tv/thumb/movieposters/30793/p30793_p_v8_am.jpg"));
            ProductsController.addProduct(new Product("The Fault In Our Stars", "http://s3-ec.buzzfed.com/static/2013-12/enhanced/webdr03/17/19/enhanced-buzz-wide-27489-1387325176-9.jpg"));
            ProductsController.addProduct(new Product("Nightmare On Elm Street", "http://www.superiorpics.com/movie_pictures/mp/2010_A_Nightmare_on_Elm_Street/2010_a_nightmare_on_elm_street_poster_002.jpg"));
            ProductsController.addProduct(new Product("Alien", "http://www.pxleyes.com/images/contests/movie-poster-recreation/fullsize/movie-poster-recreation-52953fe575c29.jpg"));
            ProductsController.addProduct(new Product("Shawshank Redemption", "http://ayay.co.uk/movies/poster/classic/the-shawshank-redemption.jpg"));
            ProductsController.addProduct(new Product("Good Will Hunting", "http://cineblog.net/wp-content/uploads/EL-INDOMABLE-WILL-HUNTING-Gus-Van-Sant-1997-El-indomable-Will-Hunting.jpg"));
            ProductsController.addProduct(new Product("Scent Of A Women", "https://sakashy.files.wordpress.com/2012/10/682-scent-of-a-woman-pp-682.jpg"));
            ProductsController.addProduct(new Product("A Beautiful Mind", "https://danhairfield.files.wordpress.com/2012/02/4de18ba494cb3201b4382d781f02f75d.jpg"));
            ProductsController.addProduct(new Product("Forest Gump", "https://1001scribbles.files.wordpress.com/2012/04/forrest-gump-poster1.jpg"));
            ProductsController.addProduct(new Product("Predators", "http://cdn.collider.com/wp-content/uploads/predators_movie_poster_international_03.jpg"));
            ProductsController.addProduct(new Product("Halloween", "http://imgs.abduzeedo.com/files/articles/horrormovies/hh1.jpeg"));
            ProductsController.addProduct(new Product("SAW", "http://img08.deviantart.net/2c6a/i/2010/076/e/e/saw_movie_poster_by_jamesrandom.jpg"));
            ProductsController.addProduct(new Product("The Conjuring", "http://www.stevenvanlijnden.com/wp-content/uploads/2015/01/The-Conjuring-Poster.jpg"));
            ProductsController.addProduct(new Product("Paranormal Activity", "http://projectdeadpost.com/wp-content/uploads/2013/04/paranormalactivity_poster.jpg"));
            ProductsController.addProduct(new Product("Killers", "http://www.filmofilia.com/wp-content/uploads/2010/05/killers_poster.jpg"));
            ProductsController.addProduct(new Product("Bride Wars", "http://vignette1.wikia.nocookie.net/scratchpad/images/f/fd/2009_-_Bride_Wars_Movie_Poster_1.jpg/revision/latest?cb=20131112160233"));
            ProductsController.addProduct(new Product("Dear John", "http://www.impawards.com/2010/posters/dear_john_xlg.jpg"));
            ProductsController.addProduct(new Product("How To Lose A Guy In 10 Days", "https://i.jeded.com/i/how-to-lose-a-guy-in-10-days.17692.jpg"));
            ProductsController.addProduct(new Product("The Proposal", "http://www.movpins.com/big/MV5BMTU1MzY1ODIyNV5BMl5BanBnXkFtZTcwNDU4NTE3Mg/the-proposal-(2009).jpg"));
            ProductsController.addProduct(new Product("Pretty Woman", "http://moviefiles.alphacoders.com/355/poster-355.jpg"));
            ProductsController.addProduct(new Product("The King's Speech", "http://cdn.collider.com/wp-content/uploads/the-kings-speech-movie-poster1.jpg"));
            ProductsController.addProduct(new Product("DJANGO UNCHAINED", "https://frompage2screen.files.wordpress.com/2012/10/duc_intl_1sht_lk1_ds_silent.jpg"));
            ProductsController.addProduct(new Product("Inglorious Bastards", "http://vignette4.wikia.nocookie.net/inglouriousbasterds/images/c/c3/Inglourious_Basterds_poster.jpg/revision/latest?cb=20131226131149"));
            ProductsController.addProduct(new Product("Pulp Fiction", "http://www.swotti.com/tmp/swotti/cacheCHVSCCBMAWN0AW9URW50ZXJ0YWLUBWVUDC1NB3ZPZXM=/imgPulp%20fiction1.jpg"));
            ProductsController.addProduct(new Product("Cloudy With a Chance Of Meatballs", "http://reelgirl.com/wp-content/uploads/2013/01/cloudy-with-a-chance-of-meatballs-movie-poster1.jpg"));
            ProductsController.addProduct(new Product("Madagascar 3: Europe's Most Wanted", "http://www.impawards.com/2012/posters/madagascar_three_ver4_xlg.jpg"));
            ProductsController.addProduct(new Product("Ice Age: The Meltdown", "https://fanart.tv/fanart/movies/950/movieposter/ice-age---the-meltdown-52a89da5da857.jpg"));
            ProductsController.addProduct(new Product("Liar Liar", "https://fanart.tv/fanart/movies/1624/movieposter/liar-liar-54d3777ec20e3.jpg"));
            ProductsController.addProduct(new Product("Just Go With It", "http://www.impawards.com/2011/posters/just_go_with_it_xlg.jpg"));
            ProductsController.addProduct(new Product("Dumb And Dumber To", "http://4.bp.blogspot.com/-w-yryFCcJXk/U5Wi1cbjhgI/AAAAAAAAACg/3GcgZyu9jso/s1600/Dumb+and+Dumber+2+new+posters+(2).jpg"));
            ProductsController.addProduct(new Product("The Hangover: Part II", "http://cdn.collider.com/wp-content/uploads/the-hangover-part-2-movie-poster-01.jpg"));
            ProductsController.addProduct(new Product("SPY", "http://www.flickeringmyth.com/wp-content/uploads/2015/03/spy-poster.jpg"));
            ProductsController.addProduct(new Product("Matrix", "http://www.freemovieposters.net/posters/matrix_the_1999_3131_poster.jpg"));
            ProductsController.addProduct(new Product("Jurassic Park", "https://thefilmstage.com/wp-content/uploads/2014/08/jurassic_park_25.jpg"));
            ProductsController.addProduct(new Product("Star Wars VII", "http://a.dilcdn.com/bl/wp-content/uploads/sites/6/2015/10/star-wars-force-awakens-official-poster.jpg"));
        }
    }
    public void registerSampleUsers(){
        long numUsers = UsersController.countUsers();
        if(numUsers==0) {
            double[] userComedy = {1.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, -1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0};
            double[] userTerror = {-1.0, 0.0, -1.0, -1.0, 0.0, 1.0, 0.0, 0.0, -1.0, -1.0, 0.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 0.0, 0.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 0.0, 0.0, 0.0};
            double[] userAction = {-1.0, 1.0, 0.0, -1.0, 1.0, 0.0, 1.0, 1.0, -1.0, 0.0, 1.0, -1.0, 0.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0};
            double[] userChickflicks = {0.0, -1.0, 1.0, 0.0, 0.0, -1.0, 1.0, 1.0, 0.0, 1.0, -1.0, 1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, -1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, -1.0, 0.0};
            double[] userDrama = {1.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, 0.0, -1.0, 0.0, -1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0};
            double[] userKids = {0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0, 0.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
            UsersController.registerUser(new User("User 1", "comedylover@gmail.com", "comedy"));
            UsersController.registerUser(new User("User 2", "terrorlover@gmail.com", "terror"));
            UsersController.registerUser(new User("User 3", "actionlover@gmail.com", "action"));
            UsersController.registerUser(new User("User 4", "chickflickslover@gmail.com", "girly"));
            UsersController.registerUser(new User("User 5", "dramalover@gmail.com", "drama"));
            UsersController.registerUser(new User("User 6", "kids@gmail.com", "kids"));




            long productNumber= ProductsController.countProducts();
            RatingRecord ratingRecord;
            List<User> tempUserList = UsersController.getUsersSortedById();
            List<Product> tempProdList = ProductsController.getProductsSortedByID();
            for (int i = 0; i < productNumber; i++) {
                if(userComedy[i]!=0) {
                    ratingRecord = new RatingRecord(tempUserList.get(0), tempProdList.get(i), (int)userComedy[i]);
                    RecommenderSystemController.addRecordToUserHistory(tempUserList.get(0), ratingRecord);
                }
                if(userTerror[i]!=0) {
                    ratingRecord = new RatingRecord(tempUserList.get(1), tempProdList.get(i), (int)userTerror[i]);
                    RecommenderSystemController.addRecordToUserHistory(tempUserList.get(1), ratingRecord);
                }
                if(userAction[i]!=0) {
                    ratingRecord = new RatingRecord(tempUserList.get(2), tempProdList.get(i), (int)userAction[i]);
                    RecommenderSystemController.addRecordToUserHistory(tempUserList.get(2), ratingRecord);
                }
                if(userChickflicks[i]!=0) {
                    ratingRecord = new RatingRecord(tempUserList.get(3), tempProdList.get(i), (int)userChickflicks[i]);
                    RecommenderSystemController.addRecordToUserHistory(tempUserList.get(3), ratingRecord);
                }
                if(userDrama[i]!=0) {
                    ratingRecord = new RatingRecord(tempUserList.get(4), tempProdList.get(i), (int)userDrama[i]);
                    RecommenderSystemController.addRecordToUserHistory(tempUserList.get(4), ratingRecord);
                }
                if(userKids[i]!=0) {
                    ratingRecord = new RatingRecord(tempUserList.get(5), tempProdList.get(i), (int)userKids[i]);
                    RecommenderSystemController.addRecordToUserHistory(tempUserList.get(5), ratingRecord);
                }

            }
        }
    }
}
