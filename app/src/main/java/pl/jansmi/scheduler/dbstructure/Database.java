package pl.jansmi.scheduler.dbstructure;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import pl.jansmi.scheduler.dbstructure.daos.ArrangementDao;
import pl.jansmi.scheduler.dbstructure.daos.CategoryDao;
import pl.jansmi.scheduler.dbstructure.daos.DisciplineDao;
import pl.jansmi.scheduler.dbstructure.daos.IngredientDao;
import pl.jansmi.scheduler.dbstructure.daos.IngredientMealJoinDao;
import pl.jansmi.scheduler.dbstructure.daos.InvitationDao;
import pl.jansmi.scheduler.dbstructure.daos.MealArrangementJoinDao;
import pl.jansmi.scheduler.dbstructure.daos.MealDao;
import pl.jansmi.scheduler.dbstructure.daos.MeasureDao;
import pl.jansmi.scheduler.dbstructure.daos.PracticeDao;
import pl.jansmi.scheduler.dbstructure.daos.StudyDao;
import pl.jansmi.scheduler.dbstructure.daos.SubjectDao;
import pl.jansmi.scheduler.dbstructure.daos.TagArrangementJoinDao;
import pl.jansmi.scheduler.dbstructure.daos.TagDao;
import pl.jansmi.scheduler.dbstructure.daos.TargetDao;
import pl.jansmi.scheduler.dbstructure.daos.TaskDao;
import pl.jansmi.scheduler.dbstructure.daos.UserDao;
import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Discipline;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dbstructure.entities.Invitation;
import pl.jansmi.scheduler.dbstructure.entities.Meal;
import pl.jansmi.scheduler.dbstructure.entities.Measure;
import pl.jansmi.scheduler.dbstructure.entities.Practice;
import pl.jansmi.scheduler.dbstructure.entities.Study;
import pl.jansmi.scheduler.dbstructure.entities.Subject;
import pl.jansmi.scheduler.dbstructure.entities.Tag;
import pl.jansmi.scheduler.dbstructure.entities.Target;
import pl.jansmi.scheduler.dbstructure.entities.Task;
import pl.jansmi.scheduler.dbstructure.entities.User;
import pl.jansmi.scheduler.dbstructure.relations.IngredientMealJoin;
import pl.jansmi.scheduler.dbstructure.relations.MealArrangementJoin;
import pl.jansmi.scheduler.dbstructure.relations.TagArrangementJoin;

@androidx.room.Database(entities = {Arrangement.class, Category.class, Discipline.class, Ingredient.class,
                                    Invitation.class, Meal.class, Measure.class, Practice.class,
                                    Study.class, Subject.class, Tag.class, Target.class, Task.class,
                                    User.class, IngredientMealJoin.class, MealArrangementJoin.class,
                                    TagArrangementJoin.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {

    // TODO: think about changing Localization to Subject (of Study) - then declare localization as Task field
    public abstract ArrangementDao arrangements();
    public abstract CategoryDao categories();
    public abstract DisciplineDao disciplines();
    public abstract IngredientDao ingredients();
    public abstract InvitationDao invitations();
    public abstract MealDao meals();
    public abstract MeasureDao measures();
    public abstract PracticeDao practices();
    public abstract StudyDao studies();
    public abstract SubjectDao subjects();
    public abstract TagDao tags();
    public abstract TargetDao targets();
    public abstract TaskDao tasks();
    public abstract UserDao users();
    public abstract IngredientMealJoinDao ingredientMealJoin();
    public abstract MealArrangementJoinDao mealArrangementJoin();
    public abstract TagArrangementJoinDao tagArrangementJoin();

    public static Database build(Context context) {
        return Room.databaseBuilder(context, Database.class, "db").allowMainThreadQueries().build();
    }

}
