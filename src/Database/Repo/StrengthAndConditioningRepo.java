package Database.Repo;

import Database.Schema.StrengthAndConditioning;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class StrengthAndConditioningRepo {
    private StrengthAndConditioning strengthAndConditioning;
    private String whereClause = "";

    public StrengthAndConditioningRepo(){

        strengthAndConditioning = new StrengthAndConditioning();

    }


    public static String createTable(){
        return "CREATE TABLE " + StrengthAndConditioning.TABLE  + "("
                + StrengthAndConditioning.KEY_SessionId  + "   PRIMARY KEY,"
                + StrengthAndConditioning.KEY_SessionDate + " TEXT,"
                + StrengthAndConditioning.KEY_SessionTime + " TEXT)";
    }


    public int insert(StrengthAndConditioning strengthAndConditioning) {
        return 1;
    }



    public void delete( ) {
        
    }

}
