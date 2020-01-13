package pl.jansmi.scheduler.dbstructure;

import android.content.Context;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Discipline;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dbstructure.entities.Meal;
import pl.jansmi.scheduler.dbstructure.entities.User;
import pl.jansmi.scheduler.dbstructure.relations.IngredientMealJoin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DatabaseTest {
    private Database db;

    @Before
    public void setUp() throws RuntimeException {
        Context appContext = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(appContext, Database.class).build();
    }

    @Test
    public void testExampleEntityInsert() throws RuntimeException {
        User usr = new User("user", "password");
        usr.setBirthday(new Date(1998, 8, 13));
        usr.setSex(User.SEX_MALE);
        usr.setWeight(80.0f);
        usr.setHeight(188);
        usr.setKcalPerDayTarget(2000);

        db.users().insert(usr);
        User fetched = db.users().getByName("jansmi");

        assertEquals(fetched.getId(), usr.getId());
        assertEquals(fetched.getName(), usr.getName());
        assertEquals(fetched.getHeight(), usr.getHeight());
    }

    @Test
    public void testExampleEntityUpdate() throws RuntimeException {
        Category cat = new Category("Breakfast");
        cat.setOrder(50);

        db.categories().insert(cat);
        cat.setName("Lunch");
        db.categories().update(cat);
        Category fetched = db.categories().getById(cat.getId());

        assertEquals(fetched.getName(), cat.getName());
        assertEquals(fetched.getOrder(), cat.getOrder());
    }

    @Test
    public void testExampleEntityDelete() throws RuntimeException {
        Discipline dis = new Discipline("Running");
        dis.setKcalPerMinute(11);

        db.disciplines().insert(dis);
        db.disciplines().delete(dis);

        assertNull(db.disciplines().getById(dis.getId()));
    }

    @Test
    public void testIngredientMealJoin() throws RuntimeException {
        Category cat = new Category("Breakfast");
        Meal meal = new Meal("Scrambled eggs", cat.getId());
        Ingredient ing = new Ingredient("Egg");
        IngredientMealJoin join = new IngredientMealJoin(ing.getId(), meal.getId(), 2);

        db.categories().insert(cat);
        db.meals().insert(meal);
        db.ingredients().insert(ing);
        db.ingredientMealJoin().insert(join);

        List<Ingredient> ings = db.ingredients().getByMealId(meal.getId());

        assertNotNull(ings);
        assertEquals(ings.get(0).getId(), ing.getId());
    }

    @After
    public void tearDown() throws RuntimeException {
        db.close();
    }
}