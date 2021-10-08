package com.maihao.littlerecipes.fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maihao.littlerecipes.R;
import com.maihao.littlerecipes.activity.MainActivity;
import com.maihao.littlerecipes.adapter.MainViewAdapter;
import com.maihao.littlerecipes.database.RecipeDataSQLHelper;
import com.maihao.littlerecipes.databinding.FragmentChooseRecipesBinding;
import com.maihao.littlerecipes.model.RecipeData;
import com.maihao.littlerecipes.util.RecyclerItemTouchHelper;
import com.maihao.littlerecipes.util.Utility;
import com.maihao.littlerecipes.viewmodel.QueryDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseRecipesFragment extends Fragment {

    RecyclerView mRecyclerView;

    FloatingActionButton button;

    FragmentChooseRecipesBinding binding;

    List<QueryDataViewModel> viewModels = new ArrayList<>();

    RecipeDataSQLHelper sqlHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 初始化data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose_recipes, container, false);

        // 初始化数据库
        sqlHelper = new RecipeDataSQLHelper(getContext(), "Recipes.db", null, 6);

        // 注释掉的这个方法在非数据库储存时使用
//        initData();

        // 从数据库中请求数据
        queryRecipes();

        // 设置RecyclerView
        mRecyclerView = binding.recipesRecyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MainViewAdapter adapter = new MainViewAdapter(getActivity(), viewModels, sqlHelper);
        mRecyclerView.setAdapter(adapter);

        // 设置Button
        button = binding.recipeAddButton;
        if (getActivity() instanceof MainActivity) {
            button.setVisibility(View.VISIBLE);
            ItemTouchHelper.Callback callback = new RecyclerItemTouchHelper(adapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(mRecyclerView);
        } else {
            button.setVisibility(View.INVISIBLE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });

        return binding.getRoot();
    }

    private void queryRecipes() {

        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Recipes", null);
        if (cursor.moveToFirst()) {
            do {
                RecipeData data  = new RecipeData();
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
                @SuppressLint("Range") String ingredients = cursor.getString(cursor.getColumnIndex("ingredients"));
                @SuppressLint("Range") String procedure = cursor.getString(cursor.getColumnIndex("procedure"));
                data.setTitle(title);
                data.setContent(content);
                data.setImageId(imageId);
                data.setIngredients(ingredients);
                data.setProcedure(procedure);
                QueryDataViewModel viewModel = new QueryDataViewModel();
                viewModel.title.setValue(data.getTitle());
                viewModel.content.setValue(data.getContent());
                viewModel.imageId.setValue(data.getImageId());
                viewModel.imageSrc.setValue(data.getImageSrc());
                viewModel.ingredients.setValue(data.getIngredients());
                viewModel.procedures.setValue(data.getProcedure());
                viewModels.add(viewModel);
            } while (cursor.moveToNext());
        }
        cursor.close();


    }

















    private void initData() {

        String[] titles = {
                "Thai Beef Salad",
                "Greek Salad I",
                "Wedge Salad with Elegant Blue Cheese Dressing",
                "Chef John's Classic Macaroni Salad",
                "Very Easy Fruit Salad",
                "Black Bean and Corn Salad I",
                "Black Bean and Corn Salad II",
                "Asian Coleslaw",
                "Chef John's Ranch Dressing",
                "Strawberry Spinach Salad I"};

        String[] contents = {
                "A colorful, tangy salad that brings out the best in Thai cuisine and spices.",
                "This is an incredibly good Greek salad recipe, nice and tangy and even better in the summer when you use fresh vegetables!",
                "This a great salad to have for a simple lunch or to serve with a nice dinner. Fresh ingredients make this salad very elegant and the wedge presentation makes it a great starter for a special occasion dinner. It is best to chill the dressing for 24 hours prior to serving.",
                "Whether it's sitting next to some smoky ribs or just a humble hot dog, this deli-style macaroni salad will always be a crowd favorite, as long as you pay attention to a few key details.",
                "In a hurry? Make this fruit salad in about 10 minutes. You can add or subtract different fruit according to your taste and what is in season.",
                "This salad is very colorful and includes a very tasty lime dressing.",
                "This salad is very colorful.",
                "A great twist on cabbage salad. The peanut butter in the dressing is the secret.",
                "It's been ages since I made homemade ranch dressing, and I'd forgotten how much better it is than the bottled stuff.",
                "Someone brought this salad to a pot luck dinner and I had to have the recipe. I have made it many, many times since then and I have been asked for the recipe every time I bring it somewhere. It is also a great way to get kids to eat spinach!"
        };

        int[] imageIds = {
                R.drawable.recipe_1,
                R.drawable.recipe_2,
                R.drawable.recipe_3,
                R.drawable.recipe_4,
                R.drawable.recipe_5,
                R.drawable.recipe_6,
                R.drawable.recipe_7,
                R.drawable.recipe_8,
                R.drawable.recipe_9,
                R.drawable.recipe_10,
        };

        String[] imageSrcs = {
                "@drawable/recipe_1",
                "@drawable/recipe_2",
                "@drawable/recipe_3",
                "@drawable/recipe_4",
                "@drawable/recipe_5",
                "@drawable/recipe_6",
                "@drawable/recipe_7",
                "@drawable/recipe_8",
                "@drawable/recipe_9",
                "@drawable/recipe_10",
        };

        String[] ingredients = {
                " 2 tablespoons sesame seeds\n" +
                        "\n" +
                        "@ 1 tablespoon poppy seeds\n" +
                        "\n" +
                        "@ ½ cup white sugar\n" +
                        "\n" +
                        "@ ½ cup olive oil\n" +
                        "\n" +
                        "@ ¼ cup distilled white vinegar\n" +
                        "\n" +
                        "@ ¼ teaspoon paprika\n" +
                        "\n" +
                        "@ ¼ teaspoon Worcestershire sauce\n" +
                        "\n" +
                        "@ 1 tablespoon minced onion\n" +
                        "\n" +
                        "@ 10 ounces fresh spinach - rinsed, dried and torn into bite-size pieces\n" +
                        "\n" +
                        "@ 1 quart strawberries - cleaned, hulled and sliced\n" +
                        "\n" +
                        "@ ¼ cup almonds, blanched and slivered",
                " 1 head romaine lettuce- rinsed, dried and chopped\n" +
                        "\n" +
                        "@ 1 red onion, thinly sliced\n" +
                        "\n" +
                        "@ 1 (6 ounce) can pitted black olives\n" +
                        "\n" +
                        "@ 1 green bell pepper, chopped\n" +
                        "\n" +
                        "@ 1 red bell pepper, chopped\n" +
                        "\n" +
                        "@ 2 large tomatoes, chopped\n" +
                        "\n" +
                        "@ 1 cucumber, sliced\n" +
                        "\n" +
                        "@ 1 cup crumbled feta cheese\n" +
                        "\n" +
                        "@ 6 tablespoons olive oil\n" +
                        "\n" +
                        "@ 1 teaspoon dried oregano\n" +
                        "\n" +
                        "@ 1 lemon, juiced\n" +
                        "\n" +
                        "@ ground black pepper to taste",
                " ½ pound crumbled blue cheese\n" +
                        "  \n" +
                        "@ ¼ cup sour cream\n" +
                        "  \n" +
                        "@ ⅓ cup buttermilk\n" +
                        "\n" +
                        "@ ½ cup mayonnaise\n" +
                        "\n" +
                        "@ ¼ cup red wine vinegar\n" +
                        "\n" +
                        "@ 1 tablespoon extra-virgin olive oil\n" +
                        "\n" +
                        "@ 1½ tablespoons white sugar\n" +
                        "\n" +
                        "@ 1 clove garlic, minced\n" +
                        "\n" +
                        "@ ground black pepper to taste\n" +
                        "\n" +
                        "@ 1 head iceberg lettuce, cut into 8 wedges\n" +
                        "\n" +
                        "@ 2 roma tomatoes, diced\n" +
                        "\n" +
                        "@ 1 small red onion, thinly sliced\n" +
                        "\n" +
                        "@ ½ pound crumbled blue cheese",
                " 1 cup mayonnaise (Optional)\n" +
                        "\n" +
                        "@ ¼ cup white vinegar\n" +
                        "\n" +
                        "@ 2 tablespoons Dijon mustard\n" +
                        "\n" +
                        "@ 2 teaspoons kosher salt, or more to taste\n" +
                        "\n" +
                        "@ ½ teaspoon ground black pepper\n" +
                        "\n" +
                        "@ ⅛ teaspoon cayenne pepper\n" +
                        "\n" +
                        "@ 1 tablespoon white sugar, or more to taste\n" +
                        "\n" +
                        "@ 1 cup finely diced celery\n" +
                        "\n" +
                        "@ ¾ cup diced red bell pepper\n" +
                        "\n" +
                        "@ ½ cup grated carrot\n" +
                        "\n" +
                        "@ ½ cup chopped green onions, white and light parts\n" +
                        "\n" +
                        "@ ¼ cup diced jalapeno pepper\n" +
                        "\n" +
                        "@ ¼ cup diced poblano pepper\n" +
                        "\n" +
                        "@ 1 (16 ounce) package uncooked elbow macaroni\n" +
                        "\n" +
                        "@ 1 tablespoon mayonnaise (Optional)\n" +
                        "\n" +
                        "@ 1 tablespoon water (Optional)",
                " 1 pint strawberries - cleaned, hulled and sliced\n" +
                        "\n" +
                        "@ 1 pound seedless grapes, halved\n" +
                        "\n" +
                        "@ 3 kiwis, peeled and sliced\n" +
                        "\n" +
                        "@ 3 bananas, sliced\n" +
                        "\n" +
                        "@ 1 (21 ounce) can peach pie filling",
                " ⅓ cup fresh lime juice\n" +
                        "\n" +
                        "@ ½ cup olive oil\n" +
                        "\n" +
                        "@ 1 clove garlic, minced\n" +
                        "\n" +
                        "@ 1 teaspoon salt\n" +
                        "\n" +
                        "@ ⅛ teaspoon ground cayenne pepper\n" +
                        "\n" +
                        "@ 2 (15 ounce) cans black beans, rinsed and drained\n" +
                        "\n" +
                        "@ 1½ cups frozen corn kernels\n" +
                        "\n" +
                        "@ 1 avocado - peeled, pitted and diced\n" +
                        "\n" +
                        "@ 1 red bell pepper, chopped\n" +
                        "\n" +
                        "@ 2 tomatoes, chopped\n" +
                        "\n" +
                        "@ 6 green onions, thinly sliced\n" +
                        "\n" +
                        "@ ½ cup chopped fresh cilantro (Optional)",
                " ½ cup mayonnaise\n" +
                        "\n" +
                        "@ 1 tablespoon lemon juice\n" +
                        "\n" +
                        "@ ¼ teaspoon ground black pepper\n" +
                        "\n" +
                        "@ 2 cups chopped, cooked chicken meat\n" +
                        "\n" +
                        "@ ½ cup blanched slivered almonds\n" +
                        "\n" +
                        "@ 1 stalk celery, chopped",
                " 6 tablespoons rice wine vinegar\n" +
                        "\n" +
                        "@ 6 tablespoons vegetable oil\n" +
                        "\n" +
                        "@ 5 tablespoons creamy peanut butter\n" +
                        "\n" +
                        "@ 3 tablespoons soy sauce\n" +
                        "\n" +
                        "@ 3 tablespoons brown sugar\n" +
                        "\n" +
                        "@ 2 tablespoons minced fresh ginger root\n" +
                        "\n" +
                        "@ 1½ tablespoons minced garlic\n" +
                        "\n" +
                        "@ 5 cups thinly sliced green cabbage\n" +
                        "\n" +
                        "@ 2 cups thinly sliced red cabbage\n" +
                        "\n" +
                        "@ 2 cups shredded napa cabbage\n" +
                        "\n" +
                        "@ 2 red bell peppers, thinly sliced\n" +
                        "\n" +
                        "@ 2 carrots, julienned\n" +
                        "\n" +
                        "@ 6 green onions, chopped\n" +
                        "\n" +
                        "@ ½ cup chopped fresh cilantro",
                " 1⅓ cups mayonnaise\n" +
                        "\n" +
                        "@ ⅓ cup sour cream\n" +
                        "\n" +
                        "@ ⅓ cup buttermilk\n" +
                        "\n" +
                        "@ 1 tablespoon minced fresh Italian parsley\n" +
                        "\n" +
                        "@ 2 teaspoons sliced fresh chives\n" +
                        "\n" +
                        "@ ½ teaspoon dried dill weed\n" +
                        "\n" +
                        "@ ¼ teaspoon dried tarragon\n" +
                        "\n" +
                        "@ ¼ teaspoon garlic powder\n" +
                        "\n" +
                        "@ ¼ teaspoon onion powder\n" +
                        "\n" +
                        "@ ¼ teaspoon freshly ground black pepper\n" +
                        "\n" +
                        "@ 1 pinch cayenne pepper\n" +
                        "\n" +
                        "@ 1 pinch salt\n" +
                        "\n" +
                        "@ 2 drops Worcestershire sauce",
                " 2 tablespoons sesame seeds\n" +
                        "\n" +
                        "@ 1 tablespoon poppy seeds\n" +
                        "\n" +
                        "@ ½ cup white sugar\n" +
                        "\n" +
                        "@ ½ cup olive oil\n" +
                        "\n" +
                        "@ ¼ cup distilled white vinegar\n" +
                        "\n" +
                        "@ ¼ teaspoon paprika\n" +
                        "\n" +
                        "@ ¼ teaspoon Worcestershire sauce\n" +
                        "\n" +
                        "@ 1 tablespoon minced onion\n" +
                        "\n" +
                        "@ 10 ounces fresh spinach - rinsed, dried and torn into bite-size pieces\n" +
                        "\n" +
                        "@ 1 quart strawberries - cleaned, hulled and sliced\n" +
                        "\n" +
                        "@ ¼ cup almonds, blanched and slivered"
        };

        String[] procedures = {
                " **Step 1**\n" +
                        "\n" +
                        "  In a large bowl, stir together the green onions, lemon grass, cilantro, mint leaves, lime juice, fish sauce, chili sauce and sugar until well combined and the sugar is dissolved. Adjust the flavor, if desired, by adding more sugar and/or fish sauce. Set aside.\n" +
                        "\n" +
                        "- **Step 2**\n" +
                        "\n" +
                        "  Cook the steak over high heat on a preheated grill for approximately 4-6 minutes on each side, until it is cooked medium. Do not overcook the meat! Remove from heat and slice into thin strips. Add the meat and its juices to the sauce and refrigerate, tightly covered, for at least 3 hours.\n" +
                        "\n" +
                        "- **Step 3**\n" +
                        "\n" +
                        "  Tear the lettuce into bite size pieces and place in a salad bowl. Arrange the cucumber on top of the lettuce, and then pour the meat and sauce over. Top with the cherry tomatoes and garnish with fresh cilantro leaves.",
                " **Step 1**\n" +
                        "\n" +
                        "  In a large salad bowl, combine the Romaine, onion, olives, bell peppers, tomatoes, cucumber and cheese.\n" +
                        "\n" +
                        "- **Step 2**\n" +
                        "\n" +
                        "  Whisk together the olive oil, oregano, lemon juice and black pepper. Pour dressing over salad, toss and serve.\n",
                " **Step 1**\n" +
                        "\n" +
                        "  Combine 1/2 pound blue cheese, sour cream, buttermilk, mayonnaise, vinegar, olive oil, sugar, garlic, and pepper in a bowl; blend using a hand mixer; chill until serving.\n" +
                        "\n" +
                        "- **Step 2**\n" +
                        "\n" +
                        "  Build the salad by placing 1 lettuce wedge on each of 8 plates. Drizzle equal amounts of dressing over each wedge. Scatter tomatoes, onion, and 1/2 pound blue cheese over each salad.",
                " **Step 1**\n" +
                        "\n" +
                        "  Whisk 1 cup mayonnaise, vinegar, Dijon mustard, salt, black pepper, and cayenne pepper together in a bowl until well blended; whisk in sugar. Stir in celery, red bell pepper, carrot, onions, and jalapeno and poblano peppers. Refrigerate until macaroni is ready to dress.\n" +
                        "\n" +
                        "- **Step 2**\n" +
                        "\n" +
                        "  Bring a large pot of well salted water to a boil. Cook elbow macaroni in the boiling water, stirring occasionally until cooked through, 8 to 10 minutes. Drain but do not rinse. Allow macaroni to drain in a colander about 5 minutes, shaking out moisture from time to time. Pour macaroni into large bowl; toss to separate and cool to room temperature. Macaroni should be sticky.\n" +
                        "\n" +
                        "- **Step 3**\n" +
                        "\n" +
                        "  Pour dressing over macaroni and stir until dressing is evenly distributed. Cover with plastic wrap. Refrigerate at least 4 hours or, ideally, overnight to allow dressing to absorb into the macaroni.\n" +
                        "\n" +
                        "- **Step 4**\n" +
                        "\n" +
                        "  Stir salad before serving. Mix 1 tablespoon mayonnaise and 1 tablespoon water into salad for fresher look.",
                " **Step 1**\n" +
                        "\n" +
                        "  In a large bowl, combine the strawberries, grapes, kiwis, and bananas. Gently mix in peaches. Chill for 1 hour before serving.",
                " **Step 1**\n" +
                        "\n" +
                        "  Place lime juice, olive oil, garlic, salt, and cayenne pepper in a small jar. Cover with lid, and shake until ingredients are well mixed.\n" +
                        "\n" +
                        "- **Step 2**\n" +
                        "\n" +
                        "  In a salad bowl, combine beans, corn, avocado, bell pepper, tomatoes, green onions, and cilantro. Shake lime dressing, and pour it over the salad. Stir salad to coat vegetables and beans with dressing, and serve.\n",
                " **Step 1**\n" +
                        "\n" +
                        "  Place almonds in a frying pan. Toast over medium-high heat, shaking frequently. Watch carefully, as they burn easily.\n" +
                        "\n" +
                        "- **Step 2**\n" +
                        "\n" +
                        "  In a medium bowl, mix together mayonnaise, lemon juice, and pepper. Toss with chicken, almonds, and celery.",
                " **Step 1**\n" +
                        "\n" +
                        "  In a medium bowl, whisk together the rice vinegar, oil, peanut butter, soy sauce, brown sugar, ginger, and garlic.\n" +
                        "\n" +
                        "- **Step 2**\n" +
                        "\n" +
                        "  In a large bowl, mix the green cabbage, red cabbage, napa cabbage, red bell peppers, carrots, green onions, and cilantro. Toss with the peanut butter mixture just before serving.",
                " **Step 1**\n" +
                        "\n" +
                        "  Combine mayonnaise, sour cream, and buttermilk in a large bowl. Stir in parsley, chives, dill, tarragon, garlic powder, onion powder, black pepper, cayenne pepper, salt, and Worcestershire sauce until combined.\n" +
                        "\n" +
                        "- **Step 2**\n" +
                        "\n" +
                        "  Cover and refrigerate for 4 hours to overnight. Season with salt and black pepper to taste.",
                " **Step 1**\n" +
                        "\n" +
                        "  In a medium bowl, whisk together the sesame seeds, poppy seeds, sugar, olive oil, vinegar, paprika, Worcestershire sauce and onion. Cover, and chill for one hour.\n" +
                        "\n" +
                        "- **Step 2**\n" +
                        "\n" +
                        "  In a large bowl, combine the spinach, strawberries and almonds. Pour dressing over salad, and toss. Refrigerate 10 to 15 minutes before serving.",
        };

        for (int i = 0; i < 10; i++) {
            RecipeData data = new RecipeData(titles[i], contents[i]);
            data.setImageId(imageIds[i]);
            data.setIngredients(ingredients[i]);
            data.setProcedure(procedures[i]);
            Utility.putRecipeData(data, sqlHelper);
        }
        queryRecipes();
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}